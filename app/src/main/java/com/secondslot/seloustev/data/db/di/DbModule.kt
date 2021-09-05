package com.secondslot.seloustev.data.db.di

import android.content.Context
import androidx.room.Room
import com.secondslot.seloustev.data.db.AppDatabase
import com.secondslot.seloustev.data.db.dao.PictureDao
import com.secondslot.seloustev.di.ApplicationScope
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "app-database"

@Module
class DbModule(private val context: Context) {

    @ApplicationScope
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun providePictureDao(db: AppDatabase): PictureDao {
        return db.pictureDao
    }
}