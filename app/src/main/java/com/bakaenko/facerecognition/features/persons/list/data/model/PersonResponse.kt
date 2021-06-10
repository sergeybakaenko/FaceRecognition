package com.bakaenko.facerecognition.features.persons.list.data.model

import com.squareup.moshi.Json

data class PersonResponse(val name: String, @Json(name = "img") val image: String?)