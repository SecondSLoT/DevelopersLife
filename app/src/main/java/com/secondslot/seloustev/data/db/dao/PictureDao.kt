package com.secondslot.seloustev.data.db.dao

import androidx.room.*
import com.secondslot.seloustev.data.db.model.PictureEntity

@Dao
interface PictureDao {

    @Query("SELECT * FROM picture_info " +
    "WHERE category == (:category) AND position >= (:index) ORDER BY position")
    suspend fun getPictures(category: String, index: Int = 0): List<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pictureList: List<PictureEntity>)
}