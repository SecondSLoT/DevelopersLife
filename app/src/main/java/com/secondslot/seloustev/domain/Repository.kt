package com.secondslot.seloustev.domain

import com.secondslot.seloustev.data.db.model.PictureEntity
import com.secondslot.seloustev.data.repository.model.PictureItem

interface Repository {

    suspend fun savePictures(pictureEntityList: List<PictureEntity>)

    suspend fun getPictures(category: String, index: Int): List<PictureItem>
}