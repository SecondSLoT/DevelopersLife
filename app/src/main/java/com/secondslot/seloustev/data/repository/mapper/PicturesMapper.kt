package com.secondslot.seloustev.data.repository.mapper

import com.secondslot.seloustev.core.mapper.BaseMapper
import com.secondslot.seloustev.core.mapper.ToEntityMapper
import com.secondslot.seloustev.data.api.model.PictureResponse
import com.secondslot.seloustev.data.db.model.PictureEntity
import com.secondslot.seloustev.data.repository.model.PictureItem

object ResponseToItemMapper : BaseMapper<List<PictureResponse>, List<PictureItem>> {

    override fun map(type: List<PictureResponse>?): List<PictureItem> {
        return type?.map {
            PictureItem(
                id = it.id,
                description = it.description,
                gifURL = it.gifURL,
                previewURL = it.previewURL,
            )
        } ?: emptyList()
    }
}

object EntityToItemMapper : BaseMapper<List<PictureEntity>, List<PictureItem>> {

    override fun map(type: List<PictureEntity>?): List<PictureItem> {
        return type?.map {
            PictureItem(
                id = it.id,
                description = it.description,
                gifURL = it.gifURL,
                previewURL = it.previewURL
            )
        } ?: emptyList()
    }
}

object ItemToEntityMapper : ToEntityMapper<List<PictureItem>, List<PictureEntity>> {

    override fun map(
        type: List<PictureItem>?,
        category: String,
        startIndex: Int
    ): List<PictureEntity> {
        var count = startIndex

        return type?.map {
            val pictureEntity = PictureEntity(
                id = it.id,
                category = category,
                index = count,
                description = it.description,
                gifURL = it.gifURL,
                previewURL = it.previewURL
            )
            count++
            pictureEntity
        } ?: emptyList()
    }
}