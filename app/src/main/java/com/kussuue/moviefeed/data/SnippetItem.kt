package com.kussuue.moviefeed.data

import com.squareup.moshi.Json

data class SnippetItem (
    val title: String?,
    @Json(name = "channelTitle") val channelName: String?,
    @Json(name = "publishedAt") val publishDate: String,
    val thumbnails: ThumbnailsItem?
)