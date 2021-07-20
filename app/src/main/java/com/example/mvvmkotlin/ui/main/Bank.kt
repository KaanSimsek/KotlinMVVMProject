package com.example.mvvmkotlin.ui.main

class Bank(bankName : String? = "", bankID : String? = "") {
    lateinit var bankID : String
    lateinit var bankName: String
    var mode : Boolean = false

    init {
        this.bankID= bankID!!
        this.bankName= bankName!!
    }

}