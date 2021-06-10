package com.bakaenko.facerecognition.features.data.repository

import com.bakaenko.facerecognition.features.data.model.PersonsListModel
import kotlinx.coroutines.flow.Flow

interface PersonsListRepository {

    fun getPersonsList(): Flow<PersonsListModel>
}