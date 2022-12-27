package com.example.deliverynationclone.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeliveryHomeViewModel @Inject constructor() : ViewModel() {

    private var menuResource = 0

    fun setMenu(menuResource: Int) {
        this.menuResource = menuResource
    }
    fun getMenu() = menuResource

}