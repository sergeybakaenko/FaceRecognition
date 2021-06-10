package com.bakaenko.facerecognition.features.persons.details.data.models

import android.graphics.Rect
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonDetailsModel(val faceRect: Rect?, val name: String, val imagePath: String?): Parcelable