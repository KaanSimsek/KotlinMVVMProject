package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.DataBaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BankTransactionViewModel @Inject constructor(private val dataBaseHelper: DataBaseHelper):ViewModel() {


    fun addBankBtn(bank: Bank,terminalID: String){
        dataBaseHelper.addBankToUser(terminalID,bank)
    }

    fun deleteBankBtn(bank:Bank,terminalID: String){
        dataBaseHelper.deleteBankToUser(terminalID,bank)
    }

    fun activateDeactivateBankBtn(banks:ArrayList<String>,terminalId:String,boolean: Boolean){
        dataBaseHelper.activateBanksOrDeactivateBanks(terminalId,banks,boolean)
    }

}