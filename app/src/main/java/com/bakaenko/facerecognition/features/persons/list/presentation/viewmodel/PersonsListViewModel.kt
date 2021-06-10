package com.bakaenko.facerecognition.features.persons.list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonsListModel
import com.bakaenko.facerecognition.features.persons.list.data.repository.PersonsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsListViewModel @Inject constructor(private val repository: PersonsListRepository) : ViewModel() {

    private val _personsLiveData = MutableLiveData<PersonsListModel>()
    val personLiveData: LiveData<PersonsListModel>
        get() = _personsLiveData


    init {
        getPersonsList()
    }

    private fun getPersonsList() {
        viewModelScope.launch {
            repository.getPersonsList().collect {
                _personsLiveData.postValue(it)
            }
        }
    }
}