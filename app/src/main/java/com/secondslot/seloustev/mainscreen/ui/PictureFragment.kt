package com.secondslot.seloustev.mainscreen.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.request.RequestListener
import com.secondslot.seloustev.databinding.FragmentPictureBinding
import com.secondslot.seloustev.data.repository.model.PictureItem
import com.secondslot.seloustev.extentions.loadImage
import com.secondslot.seloustev.mainscreen.vm.PictureViewModel
import com.secondslot.seloustev.mainscreen.vm.PictureViewModelFactory

private const val TAG = "PictureFragment"
private const val ARG_POSITION = "position"

class PictureFragment() : Fragment() {

    private lateinit var viewModel: PictureViewModel

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = requireNotNull(_binding)

    companion object {
        fun newInstance(position: Int): PictureFragment =
            PictureFragment().apply {
                arguments = bundleOf(
                    ARG_POSITION to position
                )
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = arguments.let { tabNumber ->
            when (tabNumber?.getInt(ARG_POSITION)) {
                0 -> "latest"
                1 -> "hot"
                else -> "top"
            }
        }

        val viewModelFactory = PictureViewModelFactory(category)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PictureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setListeners() = binding.run {

        buttonNext.setOnClickListener { viewModel.onButtonNextPressed() }

        buttonBack.setOnClickListener { viewModel.onButtonBackPressed() }

        errorMessage.buttonRetry.setOnClickListener { viewModel.onButtonRetryPressed() }
    }

    private fun setObservers() {

        viewModel.pictureItemLiveData.observe(viewLifecycleOwner) { pictureItem ->
            showPicture(pictureItem, viewModel.requestListener)
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            onLoadingStateReceived(isLoading)
        }

        viewModel.isErrorLiveData.observe(viewLifecycleOwner) { isError ->
            if (isError) onErrorReceived()
        }

        viewModel.enableBtnBackLiveData.observe(viewLifecycleOwner) {
            binding.buttonBack.isEnabled = it
        }

        viewModel.enableBtnNextLiveData.observe(viewLifecycleOwner) {
            binding.buttonNext.isEnabled = it
        }
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun onErrorReceived() {
        binding.run {
            errorMessage.root.visibility = View.VISIBLE
        }
    }

    private fun showPicture(
        pictureItem: PictureItem,
        requestListener: RequestListener<Drawable>
    ) {
        Log.d(TAG, "showPicture(), gifURL = ${pictureItem.gifURL}")
        binding.imageView.loadImage(pictureItem.gifURL, requestListener)
        binding.descriptionTextView.text = pictureItem.description
    }

    private fun showSpinner(isLoading: Boolean) {

        if (isLoading) {
            binding.run {
                errorMessage.root.visibility = View.GONE
                frameLayout.visibility = View.INVISIBLE
                progressCircular.root.visibility = View.VISIBLE
            }
        } else {
            binding.run {
                progressCircular.root.visibility = View.GONE
                frameLayout.visibility = View.VISIBLE
            }
        }
    }
}