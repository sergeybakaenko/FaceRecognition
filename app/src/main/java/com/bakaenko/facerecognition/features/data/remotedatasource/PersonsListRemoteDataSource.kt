package com.bakaenko.facerecognition.features.data.remotedatasource

import com.bakaenko.facerecognition.features.data.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonsListRemoteDataSource {

    fun getPersonsList(): Flow<List<Person>>
}