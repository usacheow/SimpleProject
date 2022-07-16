package com.usacheow.basesources

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    operator fun invoke(count: Int) = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        arrayOf(MediaStore.Images.Media._ID),
        null,
        null,
        MediaStore.Images.Media._ID + " DESC",
    )?.use { cursor ->
        generateSequence {
            if (cursor.moveToNext()) cursor else null
        }.map {
            val idColumn = it.getColumnIndex(MediaStore.Images.Media._ID)
            val id = it.getLong(idColumn)
            ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
        }.take(count).toList()
    } ?: emptyList()
}