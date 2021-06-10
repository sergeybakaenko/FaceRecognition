package com.bakaenko.facerecognition.features.persons.list.data.remotedatasource

import com.bakaenko.facerecognition.features.persons.list.data.model.PersonResponse
import kotlinx.coroutines.flow.Flow

interface PersonsListRemoteDataSource {

    fun getPersonsList(): Flow<List<PersonResponse>>
}