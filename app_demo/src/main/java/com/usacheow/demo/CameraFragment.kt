package com.usacheow.demo

import android.Manifest
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.OrientationEventListener
import android.view.Surface
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.system.arePermissionsGranted
import com.usacheow.coreui.utils.system.checkPermissions
import com.usacheow.coreui.utils.view.doWithTransition
import kotlinx.android.synthetic.main.fragment_camera.captureButton
import kotlinx.android.synthetic.main.fragment_camera.container
import kotlinx.android.synthetic.main.fragment_camera.switchButton
import kotlinx.android.synthetic.main.fragment_camera.viewFinder
import java.io.File
import java.nio.ByteBuffer
import java.util.ArrayDeque
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val PERMISSIONS_DENIED_MESSAGE = "Permissions not granted by the user."
private const val REQUEST_CODE_PERMISSIONS = 10
private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

private const val RATIO_4_3_VALUE = 4.0 / 3.0
private const val RATIO_16_9_VALUE = 16.0 / 9.0

typealias LumaListener = (luma: Double) -> Unit

class CameraFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_camera

    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalysis: ImageAnalysis? = null
    private var camera: Camera? = null

    private lateinit var cameraExecutor: ExecutorService

    private val orientationEventListener by lazy {
        object : OrientationEventListener(requireContext()) {
            override fun onOrientationChanged(orientation: Int) {
                if (abs(orientation - switchButton.rotation) >= 3) {
                    (container as ViewGroup).doWithTransition {
                        switchButton.rotation = orientation.toFloat()
                    }
                }

                val rotation: Int = when (orientation) {
                    in 45..134 -> Surface.ROTATION_270
                    in 135..224 -> Surface.ROTATION_180
                    in 225..314 -> Surface.ROTATION_90
                    else -> Surface.ROTATION_0
                }

                imageCapture?.targetRotation = rotation
                imageAnalysis?.targetRotation = rotation
            }
        }
    }

    companion object {
        fun newInstance() = CameraFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        cameraExecutor = Executors.newSingleThreadExecutor()
        orientationEventListener.enable()

        checkPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS) {
            viewFinder.post {
                updateCameraUi()
                bindCameraUseCases()
            }
        }
    }

    override fun clearViews() {
        cameraExecutor.shutdown()
        orientationEventListener.disable()
    }

    private fun updateCameraUi() {
        captureButton.setOnClickListener {
            imageCapture?.let { imageCapture ->
                val file = File(requireContext().externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
                val metadata = ImageCapture.Metadata().apply {
                    isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
                }
                val outputOptions = ImageCapture.OutputFileOptions.Builder(file)
                    .setMetadata(metadata)
                    .build()

                imageCapture.takePicture(outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exception: ImageCaptureException) {
                        val msg = "Photo capture failed: ${exception.message}"
                        viewFinder.post {
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = output.savedUri ?: Uri.fromFile(file)
                        // Implicit broadcasts will be ignored for devices running API level >= 24
                        // so if you only target API level 24+ you can remove this statement
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                            requireActivity().sendBroadcast(
                                Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri)
                            )
                        }

                        // If the folder selected is an external media directory, this is
                        // unnecessary but otherwise other apps will not be able to access our
                        // images unless we scan them using [MediaScannerConnection]
                        val mimeType = MimeTypeMap.getSingleton()
                            .getMimeTypeFromExtension(savedUri.toFile().extension)
                        MediaScannerConnection.scanFile(
                            context,
                            arrayOf(savedUri.toFile().absolutePath),
                            arrayOf(mimeType)
                        ) { _, uri -> }
                    }
                })
            }
        }

        switchButton.setOnClickListener {
            lensFacing = if (CameraSelector.LENS_FACING_FRONT == lensFacing) {
                CameraSelector.LENS_FACING_BACK
            } else {
                CameraSelector.LENS_FACING_FRONT
            }
            bindCameraUseCases()
        }
    }

    private fun bindCameraUseCases() {
        val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
        val rotation = viewFinder.display.rotation

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            preview = Preview.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
//                .setCaptureProcessor()
//                .setImageInfoProcessor()
                .build()


            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
//                .setCaptureMode()
//                .setMaxCaptureStages()
//                .setFlashMode()
//                .setCaptureProcessor()
//                .setCaptureBundle()
                .build()

            imageAnalysis = ImageAnalysis.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
//                .setTargetResolution(Size())
//                .setBackpressureStrategy()
//                .setImageQueueDepth()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma -> })
                }

            cameraProvider.unbindAll()
            try {
                camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, imageAnalysis)
                addFocusControl()
                val surfaceProvider = viewFinder.createSurfaceProvider(camera?.cameraInfo)
                preview?.setSurfaceProvider(surfaceProvider)
            } catch (exc: Exception) {
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    private fun addFocusControl() {
        val cameraControl = camera?.cameraControl
        val factory = SurfaceOrientedMeteringPointFactory(100f, 100f)
        val point = factory.createPoint(100f, 100f)
        val action = FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
            .addPoint(point, FocusMeteringAction.FLAG_AE)
            .setAutoCancelDuration(5, TimeUnit.SECONDS)
            .build()

        val future = cameraControl?.startFocusAndMetering(action)
        future?.addListener(Runnable {
            val result = future.get()
            // process the result
        }, cameraExecutor)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (requireContext().arePermissionsGranted(REQUIRED_PERMISSIONS)) {
                viewFinder.post {
                    updateCameraUi()
                    bindCameraUseCases()
                }
            } else {
                Toast.makeText(requireContext(), PERMISSIONS_DENIED_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class LuminosityAnalyzer(listener: LumaListener? = null) : ImageAnalysis.Analyzer {

    private val frameRateWindow = 8
    private val frameTimestamps = ArrayDeque<Long>(5)
    private val listeners = ArrayList<LumaListener>().apply { listener?.let { add(it) } }
    private var lastAnalyzedTimestamp = 0L
    var framesPerSecond: Double = -1.0
        private set

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    /**
     * Analyzes an image to produce a result.
     *
     * <p>The caller is responsible for ensuring this analysis method can be executed quickly
     * enough to prevent stalls in the image acquisition pipeline. Otherwise, newly available
     * images will not be acquired and analyzed.
     *
     * <p>The image passed to this method becomes invalid after this method returns. The caller
     * should not store external references to this image, as these references will become
     * invalid.
     *
     * @param image image being analyzed VERY IMPORTANT: Analyzer method implementation must
     * call image.close() on received images when finished using them. Otherwise, new images
     * may not be received or the camera may stall, depending on back pressure setting.
     *
     */
    override fun analyze(image: ImageProxy) {
        if (listeners.isEmpty()) {
            image.close()
            return
        }

        val currentTime = System.currentTimeMillis()
        frameTimestamps.push(currentTime)

        while (frameTimestamps.size >= frameRateWindow) {
            frameTimestamps.removeLast()
        }

        val timestampFirst = frameTimestamps.peekFirst() ?: currentTime
        val timestampLast = frameTimestamps.peekLast() ?: currentTime

        framesPerSecond = 1.0 / (
            (timestampFirst - timestampLast) / frameTimestamps.size.coerceAtLeast(1).toDouble()
            ) * 1000.0

        // Analysis could take an arbitrarily long amount of time
        // Since we are running in a different thread, it won't stall other use cases

        lastAnalyzedTimestamp = frameTimestamps.first

        // Since format in ImageAnalysis is YUV, image.planes[0] contains the luminance plane
        val buffer = image.planes[0].buffer

        // Extract image data from callback object
        val data = buffer.toByteArray()

        // Convert the data into an array of pixel values ranging 0-255
        val pixels = data.map { it.toInt() and 0xFF }

        // Compute average luminance for the image
        val luma = pixels.average()

        // Call all listeners with new value
        listeners.forEach { it(luma) }

        image.close()
    }
}