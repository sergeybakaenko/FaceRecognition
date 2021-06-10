package com.bakaenko.facerecognition.features.presentation.epoxy

import android.graphics.BitmapFactory
import android.system.Os.open
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakaenko.facerecognition.R
import com.bakaenko.facerecognition.base.BaseEpoxyHolder
import com.bakaenko.facerecognition.databinding.ItemPersonBinding
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.DatagramChannel.open

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
                imagePath?.let { image ->
                    val stream = root.context.assets.open((image))
                    personImage.setImageBitmap(BitmapFactory.decodeStream(stream))
                }
            }
        }
    }
}
