package com.bakaenko.facerecognition.features.persons.list.data.repository

import com.bakaenko.facerecognition.features.persons.list.data.model.PersonsListModel
import kotlinx.coroutines.flow.Flow

interface PersonsListRepository {

    fun getPersonsList(): Flow<PersonsListModel>
}