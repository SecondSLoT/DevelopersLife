package com.secondslot.seloustev.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_info")
data class PictureEntity(
    // Id which is assigned to picture on server
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "position") val index: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "gifURL") val gifURL: String,
    @ColumnInfo(name = "previewURL") val previewURL: String
)
