package com.example.mvvmkotlin.ui.main

import android.R
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.DataBaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel@Inject constructor(private val dataBaseHelper: DataBaseHelper): ViewModel() {


    fun listUserBtn() : MutableList<String>{
        return userListToStringList(dataBaseHelper.readData())
    }

    private fun userListToStringList(list: MutableList<Merchant>): ArrayList<String> {
        var arr: ArrayList<String> = ArrayList()
        for (i in 0..list.size-1){
            var str=""
            str += list.get(i).id +"."+
                    list.get(i).name+"  "+
                    list.get(i).password + "  " +
                    list.get(i).terminalID + "  " +
                    list.get(i).deviceType + "  " +
                    list.get(i).trID + "  " +
                    list.get(i).registerDate
            arr.add(str)

        }
        return arr
    }

}