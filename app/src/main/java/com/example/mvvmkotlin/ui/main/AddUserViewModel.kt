package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.DataBaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AddUserViewModel @Inject constructor(private val dataBaseHelper: DataBaseHelper): ViewModel() {


    fun addUserBtn(merchant: Merchant):Boolean{
        var merch = merchant.isNewUserValid(merchant)
        if(merch.name.length!=0){
            if (dataBaseHelper.insertData(merch)){
                return true
            }
        }
        return false
    }


}