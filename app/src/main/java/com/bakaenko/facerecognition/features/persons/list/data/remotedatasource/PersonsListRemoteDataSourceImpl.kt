package com.bakaenko.facerecognition.features.persons.list.data.remotedatasource

import com.bakaenko.facerecognition.features.persons.list.data.model.PersonResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PersonsListRemoteDataSourceImpl : PersonsListRemoteDataSource {

    override fun getPersonsList(): Flow<List<PersonResponse>> {
        val result = javaClass.getResource("/assets/apple_execs.json")?.readText()

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, PersonResponse::class.java)
        val adapter: JsonAdapter<List<PersonResponse>> = moshi.adapter(type)
        val people = result?.let { adapter.fromJson(it) } ?: listOf()

        return flow { emit(people) }
    }
}