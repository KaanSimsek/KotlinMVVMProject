package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.DataBaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel@Inject constructor(private val dataBaseHelper: DataBaseHelper): ViewModel() {


    fun listUserBtn() : MutableList<String>{
        return userListToStringList(dataBaseHelper.readData())
    }

    private fun userListToStringList(list: MutableList<Merchant>): ArrayList<String> {
        val arr: ArrayList<String> = ArrayList()
        for (i in 0..list.size-1){
            arr.add(list.get(i).toString())
        }
        return arr
    }

}