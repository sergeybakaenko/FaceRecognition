package com.bakaenko.facerecognition.features.data.repository

import com.bakaenko.facerecognition.features.data.ml.FaceRecognitionService
import com.bakaenko.facerecognition.features.data.model.PersonsListModel
import com.bakaenko.facerecognition.features.data.remotedatasource.PersonsListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PersonsListRepositoryImpl(
    private val personsList: PersonsListRemoteDataSource,
    private val faceRecognitionService: FaceRecognitionService
) : PersonsListRepository {

    override fun getPersonsList(): Flow<PersonsListModel> {
        return personsList.getPersonsList().map {
            PersonsListModel(faceRecognitionService.orderImagesByFacePresentPercentage(it))
        }
    }
}