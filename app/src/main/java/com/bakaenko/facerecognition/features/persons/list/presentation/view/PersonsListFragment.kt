package com.bakaenko.facerecognition.features.persons.list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bakaenko.facerecognition.base.BaseFragment
import com.bakaenko.facerecognition.databinding.FragmentPersonsListBinding
import com.bakaenko.facerecognition.features.persons.list.data.model.Person
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonsListModel
import com.bakaenko.facerecognition.features.persons.list.presentation.epoxy.PersonItemHolder_
import com.bakaenko.facerecognition.features.persons.list.presentation.viewmodel.PersonsListViewModel
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
                    PersonItemHolder_()
                        .id(it.name)
                        .name(it.name)
                        .imagePath(it.image)
                        .openDetails {
                            root.findNavController().navigate(
                                PersonsListFragmentDirections.openPersonDetails(
                                    it.face?.boundingBox!!,
                                    Person(it.name, it.image)
                                )
                            )
                        }
                        .addTo(this)
                }
            }
        }
    }
}