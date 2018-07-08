package com.giphy.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchModel(val data: List<DataItem>?) : Parcelable

@Parcelize
data class DataItem(val images: Images,
                    val id: String = "") : Parcelable

@Parcelize
data class Images(val original: Original) : Parcelable

@Parcelize
data class Original(val mp4: String = "",
                    val size: String = "",
                    val frames: String = "",
                    val width: String = "",
                    val mp4Size: String = "",
                    val webp: String = "",
                    val webpSize: String = "",
                    val url: String = "",
                    val height: String = "") : Parcelable
