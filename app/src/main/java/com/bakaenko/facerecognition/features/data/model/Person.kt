package com.bakaenko.facerecognition.features.data.model

import com.squareup.moshi.Json

class Person(val name: String, @Json(name = "img") val image: String?)