package com.bakaenko.facerecognition.ml

import com.bakaenko.facerecognition.features.persons.list.data.model.PersonResponse
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel

interface FaceRecognitionService {

    suspend fun orderImagesByFacePresentPercentage(images: List<PersonResponse>): List<PersonModel>
}