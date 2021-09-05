package com.secondslot.seloustev

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.secondslot.seloustev.data.db.AppDatabase
import com.secondslot.seloustev.data.db.dao.PictureDao
import com.secondslot.seloustev.data.db.model.PictureEntity
import com.secondslot.seloustev.data.repository.model.PictureItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var pictureDao: PictureDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        pictureDao = db.pictureDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetPictures() {
        val picture1 = PictureEntity(
            1,
            "top",
            1,
            "desc1",
            "gifURL1",
            "previewURL1"
        )
        val picture2 = PictureEntity(
            2,
            "top",
            2,
            "desc2",
            "gifURL2",
            "previewURL2")

        val pictures = listOf(picture1, picture2)

        runBlocking {
            pictureDao.insertAll(pictures)
            val dbPictures = pictureDao.getPictures("top")
            assertEquals(pictures, dbPictures)
        }
    }
}