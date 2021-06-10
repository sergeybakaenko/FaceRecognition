package com.bakaenko.facerecognition.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyHolder

abstract class BaseEpoxyHolder<VB : ViewBinding> : EpoxyHolder() {

    var binding: VB? = null

    abstract fun inflate(view: View): VB

    fun withBinding(block: (VB.() -> Unit)?): VB {
        val bindingAfterRunning: VB? = binding?.apply { block?.invoke(this) }
        return bindingAfterRunning
            ?: error("Accessing binding outside of lifecycle: ${this::class.java.simpleName}")
    }

    override fun bindView(itemView: View) {
        binding = inflate(itemView)
    }
}