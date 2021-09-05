package com.secondslot.seloustev.data.repository

import android.util.Log
import com.secondslot.seloustev.DevelopersLifeApplication
import com.secondslot.seloustev.data.api.Fetcher
import com.secondslot.seloustev.data.db.dao.PictureDao
import com.secondslot.seloustev.data.db.model.PictureEntity
import com.secondslot.seloustev.data.repository.mapper.EntityToItemMapper
import com.secondslot.seloustev.data.repository.mapper.ItemToEntityMapper
import com.secondslot.seloustev.data.repository.mapper.ResponseToItemMapper
import com.secondslot.seloustev.di.ApplicationScope
import com.secondslot.seloustev.domain.Repository
import com.secondslot.seloustev.data.repository.model.PictureItem
import javax.inject.Inject

private const val TAG = "Repository"

@ApplicationScope
class RepositoryImpl @Inject constructor(private val pictureDao: PictureDao) : Repository {

    private val elementsOnPage: Int = 5

    init {
        DevelopersLifeApplication.getComponent().injectRepository(this)
    }

    override suspend fun savePictures(pictureEntityList: List<PictureEntity>) {
        pictureDao.insertAll(pictureEntityList)
        Log.d(TAG, "Saving data to database")
    }

    override suspend fun getPictures(category: String, index: Int): List<PictureItem> {

        val dbData = EntityToItemMapper.map(pictureDao.getPictures(category, index))

        return if (dbData.isEmpty()) {
            val fetcher = Fetcher(category, index / elementsOnPage)
            val remoteData = ResponseToItemMapper.map(fetcher.fetchPictures())
            // Save data from server to Db
            if (remoteData.isNotEmpty()) {
                savePictures(ItemToEntityMapper.map(remoteData, category, index))
            }
            return remoteData
        } else {
            dbData
        }
    }
}