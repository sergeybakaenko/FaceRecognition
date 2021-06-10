package com.bakaenko.facerecognition.features.persons.list.data.di

import android.content.Context
import com.bakaenko.facerecognition.ml.FaceRecognitionService
import com.bakaenko.facerecognition.ml.FaceRecognitionServiceImpl
import com.bakaenko.facerecognition.features.persons.list.data.remotedatasource.PersonsListRemoteDataSource
import com.bakaenko.facerecognition.features.persons.list.data.remotedatasource.PersonsListRemoteDataSourceImpl
import com.bakaenko.facerecognition.features.persons.list.data.repository.PersonsListRepository
import com.bakaenko.facerecognition.features.persons.list.data.repository.PersonsListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonsListModule {

    @Singleton
    @Provides
    fun providePersonsListRemoteDataSource(): PersonsListRemoteDataSource {
        return PersonsListRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providePersonsListRepository(remoteDataSource: PersonsListRemoteDataSource, faceRecognitionService: FaceRecognitionService): PersonsListRepository {
        return PersonsListRepositoryImpl(remoteDataSource, faceRecognitionService)
    }

    @Singleton
    @Provides
    fun provideFaceRecognition(@ApplicationContext context: Context): FaceRecognitionService {
        return FaceRecognitionServiceImpl(context)
    }
}