package com.bakaenko.facerecognition.features.persons.list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bakaenko.facerecognition.base.BaseFragment
import com.bakaenko.facerecognition.base.BaseProps
import com.bakaenko.facerecognition.base.value
import com.bakaenko.facerecognition.databinding.FragmentPersonsListBinding
import com.bakaenko.facerecognition.features.persons.details.data.models.PersonDetailsModel
import com.bakaenko.facerecognition.features.persons.list.data.model.PersonModel
import com.bakaenko.facerecognition.features.persons.list.presentation.epoxy.personItemHolder
import com.bakaenko.facerecognition.features.persons.list.presentation.viewmodel.PersonsListViewModel
import com.bakaenko.facerecognition.utils.visibleIf
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

        viewModel.props.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(props: BaseProps<List<PersonModel>>) {
        withBinding {
            personsRecycler.visibleIf(props is BaseProps.Data)
            progressBar.visibleIf(props is BaseProps.Loading)

            when (props) {
                is BaseProps.Data -> {
                    personsRecycler.withModels {
                        props.value.forEach {
                            personItemHolder {
                                id(it.name)
                                name(it.name)
                                imagePath(it.image)
                                openDetails {
                                    root.findNavController().navigate(
                                        PersonsListFragmentDirections.openPersonDetails(
                                            PersonDetailsModel(
                                                it.face?.boundingBox,
                                                it.name,
                                                it.image
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                else -> handleErrors(props)
            }
        }
    }
}