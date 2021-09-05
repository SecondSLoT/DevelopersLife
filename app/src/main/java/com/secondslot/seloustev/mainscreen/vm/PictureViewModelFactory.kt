package com.secondslot.seloustev.mainscreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PictureViewModelFactory(private val category: String)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PictureViewModel::class.java)) {
            return PictureViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}