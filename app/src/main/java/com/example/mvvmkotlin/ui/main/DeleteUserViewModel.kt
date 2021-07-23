package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.DataBaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteUserViewModel @Inject constructor(private val dataBaseHelper: DataBaseHelper): ViewModel() {
    fun deleteUserBtn(merchantID: String):Boolean{
        return dataBaseHelper.deleteData(merchantID)
    }
}