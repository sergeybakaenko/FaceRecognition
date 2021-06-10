package com.bakaenko.facerecognition.features.persons.list.data.repository

import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel
import kotlinx.coroutines.flow.Flow

interface PersonsListRepository {

    fun getPersonsList(): Flow<List<PersonModel>>
}