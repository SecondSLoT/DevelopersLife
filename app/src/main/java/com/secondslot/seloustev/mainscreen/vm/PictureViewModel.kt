package com.secondslot.seloustev.mainscreen.vm

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.secondslot.seloustev.DevelopersLifeApplication
import com.secondslot.seloustev.data.repository.model.PictureItem
import com.secondslot.seloustev.domain.usecase.GetPicturesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PictureViewModel"

class PictureViewModel(var category: String) : ViewModel() {

    @Inject
    lateinit var getPicturesUseCase: GetPicturesUseCase

    private var pictures: MutableList<PictureItem> = mutableListOf()
    private var position: Int = 0
    private var lockNewRequests: Boolean = false

    private var _pictureItemLiveData = MutableLiveData<PictureItem>()
    val pictureItemLiveData get() = _pictureItemLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData get() = _isLoadingLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData get() = _isErrorLiveData

    private val _enableBtnBackLiveData = MutableLiveData<Boolean>()
    val enableBtnBackLiveData get() = _enableBtnBackLiveData

    private val _enableBtnNextLiveData = MutableLiveData<Boolean>()
    val enableBtnNextLiveData get() = _enableBtnNextLiveData

    val requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {

            Log.d(TAG, "onLoadFailed()")
            onResultError()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {

            Log.d(TAG, "onResourceReady()")
            isLoading(false)
            enableButtonNext(true)
            return false
        }
    }

    init {
        DevelopersLifeApplication.getComponent().injectPictureViewModel(this)
        getPictures()
        if (position == 0) enableButtonBack(false)
    }

    private fun getPicture() {
        isLoading(true)
        if (position == pictures.size - 1 || pictures.isEmpty()) getPictures(position + 1)
        if (position < pictures.size) {
            _pictureItemLiveData.value = pictures[position]
        }
    }

    private fun getPictures(index: Int = 0) {
        if (!lockNewRequests) {
            lockNewRequests = true

            viewModelScope.launch {
                try {
                    val list = getPicturesUseCase.execute(category, index)
                    if (list.isNullOrEmpty()) {
                        Log.d(TAG, "Received data it empty")
                        onResultError()
                    } else {
                        Log.d(TAG, "Result success")
                        onResultSuccess(list)
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "Error receiving pictures data")
                    onResultError()
                }
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        _isLoadingLiveData.value = isLoading
    }

    private fun onResultSuccess(t: List<PictureItem>) {
        pictures.addAll(t)
        getPicture()
        lockNewRequests = false
    }

    private fun onResultError() {
        Log.d(TAG, "onResultError()")
        isLoading(false)
        _isErrorLiveData.value = true
        enableButtonNext(false)
        lockNewRequests = false
    }

    private fun onResultErrorComplete() {
        _isErrorLiveData.value = false
    }

    private fun enableButtonNext(isEnable: Boolean) {
        _enableBtnNextLiveData.value = isEnable
    }

    private fun enableButtonBack(isEnable: Boolean) {
        _enableBtnBackLiveData.value = isEnable
    }

    fun onButtonNextPressed() {
        // If user stands on the last position of loaded list, button won't work
        // until new portion of data loaded
        if (position != pictures.size) {
            position++
            enableButtonBack(true)
            Log.d(TAG, "position = $position")
            getPicture()
        }
    }

    fun onButtonBackPressed() {
        position--
        if (position == 0) enableButtonBack(false)
        enableButtonNext(true)
        Log.d(TAG, "position = $position")
        onResultErrorComplete()
        getPicture()
    }

    fun onButtonRetryPressed() {
        getPicture()
    }
}