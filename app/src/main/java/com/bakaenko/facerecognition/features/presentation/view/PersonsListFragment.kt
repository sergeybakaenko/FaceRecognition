package com.bakaenko.facerecognition.features.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bakaenko.facerecognition.base.BaseFragment
import com.bakaenko.facerecognition.databinding.FragmentPersonsListBinding
import com.bakaenko.facerecognition.features.data.model.PersonsListModel
import com.bakaenko.facerecognition.features.presentation.epoxy.PersonItemHolder
import com.bakaenko.facerecognition.features.presentation.epoxy.PersonItemHolder_
import com.bakaenko.facerecognition.features.presentation.viewmodel.PersonsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonsListFragment : BaseFragment<FragmentPersonsListBinding>() {

    private val viewModel: PersonsListViewModel by viewModels()

    override fun bind(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) = FragmentPersonsListBinding.inflate(layoutInflater, container, attachToRoot)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.personLiveData.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(model: PersonsListModel) {
        withBinding {

            personsRecycler.withModels {
                model.items.forEach {
                    PersonItemHolder_().id(it.name).name(it.name).imagePath(it.image).addTo(this)
                }
            }
        }
    }
}