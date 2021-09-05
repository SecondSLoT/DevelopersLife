package com.secondslot.seloustev.domain.usecase

import android.util.Log
import com.secondslot.seloustev.data.repository.RepositoryImpl
import com.secondslot.seloustev.data.repository.model.PictureItem
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(private val repository: RepositoryImpl) {

    suspend fun execute(category: String, index: Int): List<PictureItem> {
        Log.d("myLogs", "Requesting category $category, index $index")
        return repository.getPictures(category, index)
    }
}