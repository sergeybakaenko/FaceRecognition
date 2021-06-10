package com.bakaenko.facerecognition.features.persons.list.presentation.viewmodel

import android.util.Log
import com.bakaenko.facerecognition.base.BaseProps.*
import com.bakaenko.facerecognition.base.BaseViewModel
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel
import com.bakaenko.facerecognition.features.persons.list.data.repository.PersonsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@HiltViewModel
class PersonsListViewModel @Inject constructor(private val repository: PersonsListRepository) :
    BaseViewModel<List<PersonModel>>() {

    init {
        _props.postValue(Loading())
        getPersonsList()
    }

    private fun getPersonsList() {
        launchIO {
            repository.getPersonsList().collect {
                val propsModel = when {
                    it.isNotEmpty() -> Data(it)
                    else -> Empty()
                }
                _props.postValue(propsModel)
            }
        }
    }
}