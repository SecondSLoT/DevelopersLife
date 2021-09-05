package com.secondslot.seloustev.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secondslot.seloustev.data.db.dao.PictureDao
import com.secondslot.seloustev.data.db.model.PictureEntity

@Database(entities = [PictureEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val pictureDao: PictureDao
}