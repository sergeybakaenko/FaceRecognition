package com.bakaenko.facerecognition.features.data.di

import com.bakaenko.facerecognition.features.data.remotedatasource.PersonsListRemoteDataSource
import com.bakaenko.facerecognition.features.data.remotedatasource.PersonsListRemoteDataSourceImpl
import com.bakaenko.facerecognition.features.data.repository.PersonsListRepository
import com.bakaenko.facerecognition.features.data.repository.PersonsListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providePersonsListRepository(remoteDataSource: PersonsListRemoteDataSource): PersonsListRepository {
        return PersonsListRepositoryImpl(remoteDataSource)
    }
}