package com.dicoding.jpsubtiga.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvshowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tvshowId")
        var tvshowId: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "category")
        var category : String,

        @ColumnInfo(name = "synopsis")
        var synopsis: String,

        @ColumnInfo(name = "imagePoster")
        var imagePoster: String,

        @ColumnInfo(name = "bookmarked")
        var bookmarked: Boolean = false
)
