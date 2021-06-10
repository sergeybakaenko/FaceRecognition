package com.bakaenko.facerecognition.features.persons.list.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(val name: String, @Json(name = "img") val image: String?): Parcelable