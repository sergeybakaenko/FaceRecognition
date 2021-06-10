package com.bakaenko.facerecognition.features.persons.details.presentation.view

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bakaenko.facerecognition.base.BaseFragment
import com.bakaenko.facerecognition.databinding.FragmentPersonDetailsBinding
import com.bakaenko.facerecognition.utils.drawRectangle
import com.bakaenko.facerecognition.utils.visibleIf

class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>() {

    private val args: PersonDetailsFragmentArgs by navArgs()

    override fun bind(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) = FragmentPersonDetailsBinding.inflate(layoutInflater, container, attachToRoot)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {

            val imagePath = args.peronDetailsInfo.imagePath
            val faceRect = args.peronDetailsInfo.faceRect

            if (imagePath != null && faceRect != null) {
                val stream = root.context.assets.open((imagePath))
                val bitmap = BitmapFactory.decodeStream(stream).drawRectangle(faceRect)
                personImage.setImageBitmap(bitmap)
            }

            val isFaceAvailable = imagePath != null && faceRect != null
            personImage.visibleIf(isFaceAvailable)
            errorText.visibleIf(!isFaceAvailable)
            personName.text = args.peronDetailsInfo.name
        }
    }
}
