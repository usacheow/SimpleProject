package com.usacheow.coreui.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import java.util.LinkedHashSet

class PhotoVideoPicker : ActivityResultContract<PhotoVideoPicker.Arg, List<Uri>>() {

    sealed class Arg(val type: Type, val maxItems: Int)

    class OnlyPhotos(maxItems: Int = 1) : Arg(Type.IMAGES_ONLY, maxItems)
    class OnlyVideos(maxItems: Int = 1) : Arg(Type.VIDEO_ONLY, maxItems)
    class All(maxItems: Int = 1) : Arg(Type.VIDEO_ONLY, maxItems)

    enum class Type {
        IMAGES_ONLY, VIDEO_ONLY, IMAGES_AND_VIDEO
    }

    override fun createIntent(context: Context, input: Arg): Intent {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Intent(MediaStore.ACTION_PICK_IMAGES).apply {
                if (input.maxItems > 1) putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, input.maxItems)

                if (input.type == Type.IMAGES_ONLY) {
                    type = "image/*"
                } else if (input.type == Type.VIDEO_ONLY) {
                    type = "video/*"
                }
            }
        } else {
            Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "*/*"

                if (input.maxItems > 1) putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

                when (input.type) {
                    Type.IMAGES_ONLY -> putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*"))
                    Type.VIDEO_ONLY -> putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("video/*"))
                    Type.IMAGES_AND_VIDEO -> putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
                }
            }
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): List<Uri> {
        return if (resultCode != Activity.RESULT_OK || intent == null) emptyList()
        else getClipDataUris(intent)
    }

    private fun getClipDataUris(intent: Intent): List<Uri> {
        val resultSet = LinkedHashSet<Uri?>()

        resultSet += intent.data

        intent.clipData?.let { clipData ->
            resultSet += (0 until (clipData.itemCount)).map {
                clipData.getItemAt(it)?.uri
            }
        }

        return resultSet.filterNotNull()
    }
}