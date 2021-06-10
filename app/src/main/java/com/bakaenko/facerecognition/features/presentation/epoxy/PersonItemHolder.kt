package com.bakaenko.facerecognition.features.presentation.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakaenko.facerecognition.R
import com.bakaenko.facerecognition.base.BaseEpoxyHolder
import com.bakaenko.facerecognition.databinding.ItemPersonBinding

@EpoxyModelClass
abstract class PersonItemHolder : EpoxyModelWithHolder<PersonItemHolder.Holder>() {

    override fun getDefaultLayout(): Int = R.layout.item_person

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    var imagePath: String? = null

    class Holder : BaseEpoxyHolder<ItemPersonBinding>() {
        override fun inflate(view: View) = ItemPersonBinding.bind(view)
    }

    override fun bind(holder: Holder) {
        holder.binding?.let {
            with(it) {
                personName.text = name
            }
        }
    }
}
