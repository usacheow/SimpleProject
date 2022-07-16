package com.usacheow.coreui

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File
import java.util.Calendar

private const val PublicAppDirectoryName = "SA"

class FileHelper(private val context: Context) {

    fun deleteFile(uri: Uri?) {
        uri ?: return
        context.contentResolver.delete(uri, null, null)
    }

    fun createPublicFile(type: FileType): Uri {
        fun createPublicFileViaFileProvider(type: FileType): Uri {
            val file = createFile(generateName(type), getPublicDir(type))
            return FileProvider.getUriForFile(context, getFileProviderAuthority(), file)
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun createPublicFileViaMediaStore(type: FileType): Uri? {
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.ImageColumns.DISPLAY_NAME, generateName(type))
                put(MediaStore.Images.ImageColumns.MIME_TYPE, generateMimeType(type))
                put(MediaStore.Images.ImageColumns.RELATIVE_PATH, "${type.publicDirectory}/$PublicAppDirectoryName")
            }
            return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            createPublicFileViaMediaStore(type) ?: createPublicFileViaFileProvider(type)
        } else {
            createPublicFileViaFileProvider(type)
        }
    }

    fun createPrivateFile(type: FileType): Uri {
        val file = createFile(generateName(type), getPrivateDir(type))
        return FileProvider.getUriForFile(context, getFileProviderAuthority(), file)
    }

    fun createInternalFile(type: FileType): Uri {
        val file = createFile(generateName(type), getFilesDir(type))
        return FileProvider.getUriForFile(context, getFileProviderAuthority(), file)
    }

    fun createInternalCacheFile(type: FileType): Uri {
        val file = createTempFile(generateName(type), getCacheDir(type))
        return FileProvider.getUriForFile(context, getFileProviderAuthority(), file)
    }

    private fun getFileProviderAuthority() = "${context.packageName}.fileprovider"

    private fun getPublicDir(type: FileType) = File(
        Environment.getExternalStoragePublicDirectory(type.publicDirectory),
        PublicAppDirectoryName,
    ).apply { mkdir() }
    private fun getPrivateDir(type: FileType) = context.getExternalFilesDir(type.privatePath)
    private fun getFilesDir(type: FileType) = context.filesDir
    private fun getCacheDir(type: FileType) = context.cacheDir

    private fun createFile(name: String, dir: File?) = File(dir, name)
    private fun createTempFile(name: String, dir: File?) = File.createTempFile(name, null, dir)

    private fun getRandomId() = Calendar.getInstance().timeInMillis
    private fun generateName(type: FileType) = when (type) {
        FileType.Pictures -> "image_" + getRandomId() + ".jpeg"
        FileType.Videos -> "video_" + getRandomId() + ".mp4"
    }

    private fun generateMimeType(type: FileType) = when (type) {
        FileType.Pictures -> "image/jpeg"
        FileType.Videos -> "video/mp4"
    }
}

enum class FileType(
    val privatePath: String,
    val publicDirectory: String,
) {

    Pictures("Pictures", Environment.DIRECTORY_PICTURES),
    Videos("Videos", Environment.DIRECTORY_MOVIES),
}