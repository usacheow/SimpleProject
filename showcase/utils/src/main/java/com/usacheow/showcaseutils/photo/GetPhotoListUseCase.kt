package com.usacheow.showcaseutils.photo

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class GetPhotoListUseCase(
    private val context: Context,
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

val getPhotoListUseCaseDiModule by DI.Module {
    bindSingleton { GetPhotoListUseCase(instance()) }
}