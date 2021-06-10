package com.bakaenko.facerecognition.ml

import com.bakaenko.facerecognition.features.persons.list.data.model.Person
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonsListModel

interface FaceRecognitionService {

    suspend fun orderImagesByFacePresentPercentage(images: List<Person>): List<PersonsListModel.PersonModel>
}