package com.usacheow.featurehello.presentation.fragment

import android.Manifest
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraX
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysisConfig
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.arePermissionsGranted
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.R
import com.usacheow.featurehello.di.HelloComponent
import kotlinx.android.synthetic.main.fragment_camera.captureButton
import kotlinx.android.synthetic.main.fragment_camera.viewFinder
import java.io.File
import java.nio.ByteBuffer
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val PERMISSIONS_DENIED_MESSAGE = "Permissions not granted by the user."
private const val REQUEST_CODE_PERMISSIONS = 10
private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

class CameraFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_camera

    private val executor = Executors.newSingleThreadExecutor()

    companion object {
        fun newInstance() = CameraFragment()
    }

    override fun inject(diProvider: DiProvider) {
        HelloComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        checkPermissions {
            ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateViewFinderTransform()
        }
    }

    private fun startCamera() {
        val preview = createPreview()
        val imageCapture = createImageCapture()
        val imageAnalysis = createImageAnalysis()

        CameraX.bindToLifecycle(this, preview, imageCapture, imageAnalysis)
    }

    private fun createPreview(): Preview {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()

        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener {
            with(viewFinder.parent as ViewGroup) {
                removeView(viewFinder)
                addView(viewFinder, 0)
            }
            viewFinder.surfaceTexture = it.surfaceTexture
            updateViewFinderTransform()
        }

        return preview
    }

    private fun createImageCapture(): ImageCapture {
        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
        }.build()

        val imageCapture = ImageCapture(imageCaptureConfig)
        captureButton.setOnClickListener {
            val file = File(requireContext().externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
            imageCapture.takePicture(file, executor, object : ImageCapture.OnImageSavedListener {

                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String,
                    exc: Throwable?
                ) {
                    val msg = "Photo capture failed: $message"
                    viewFinder.post {
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onImageSaved(file: File) {
                    val msg = "Photo capture succeeded: ${file.absolutePath}"
                    viewFinder.post {
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        return imageCapture
    }

    private fun createImageAnalysis(): ImageAnalysis {
        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()

        val imageAnalysis = ImageAnalysis(analyzerConfig)
        imageAnalysis.setAnalyzer(executor, LuminosityAnalyzer())

        return imageAnalysis
    }

    private fun updateViewFinderTransform() {
        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        val rotationDegrees = when (viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }.toFloat()

        val matrix = Matrix()
        matrix.postRotate(-rotationDegrees, centerX, centerY)

        viewFinder.setTransform(matrix)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            checkPermissions {
                Toast.makeText(requireContext(), PERMISSIONS_DENIED_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermissions(onDeniedAction: () -> Unit) {
        if (requireContext().arePermissionsGranted(REQUIRED_PERMISSIONS)) {
            viewFinder.post { startCamera() }
        } else {
            onDeniedAction()
        }
    }
}

class LuminosityAnalyzer : ImageAnalysis.Analyzer {
    private var lastAnalyzedTimestamp = 0L

    /**
     * Helper extension function used to extract a byte array from an
     * image plane buffer
     */
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    override fun analyze(image: ImageProxy, rotationDegrees: Int) {
        val currentTimestamp = System.currentTimeMillis()
        // Calculate the average luma no more often than every second
        if (currentTimestamp - lastAnalyzedTimestamp >=
            TimeUnit.SECONDS.toMillis(1)) {
            // Since format in ImageAnalysis is YUV, image.planes[0]
            // contains the Y (luminance) plane
            val buffer = image.planes[0].buffer
            // Extract image data from callback object
            val data = buffer.toByteArray()
            // Convert the data into an array of pixel values
            val pixels = data.map { it.toInt() and 0xFF }
            // Compute average luminance for the image
            val luma = pixels.average()
            // Log the new luma value
            Log.d("CameraXApp", "Average luminosity: $luma")
            // Update timestamp of last analyzed frame
            lastAnalyzedTimestamp = currentTimestamp
        }
    }
}