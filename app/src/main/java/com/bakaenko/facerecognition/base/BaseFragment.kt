package com.bakaenko.facerecognition.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bakaenko.facerecognition.R

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = getInflatedView(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun getInflatedView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        this.binding = bind(inflater, container, attachToRoot)
        return binding?.root!!
    }

    abstract fun bind(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB

    open fun setupViews() {
        // Override if needed
    }

    fun withBinding(block: (VB.() -> Unit)?): VB {
        val bindingAfterRunning: VB? = binding?.apply { block?.invoke(this) }
        return bindingAfterRunning
            ?: error("Accessing binding outside of lifecycle: ${this::class.java.simpleName}")
    }

    fun <T> handleErrors(baseState: BaseProps<T>) {
        when (baseState) {
            is Error -> showToast(baseState.message)
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(
            context, message
                ?: requireContext().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT
        ).show()
    }
}
