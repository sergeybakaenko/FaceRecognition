package com.bakaenko.facerecognition.features.data.ml

import com.bakaenko.facerecognition.features.data.model.Person
import com.bakaenko.facerecognition.features.data.model.PersonsListModel
import com.google.mlkit.vision.face.Face

interface FaceRecognitionService {

    suspend fun orderImagesByFacePresentPercentage(images: List<Person>): List<PersonsListModel.PersonModel>
}