package com.example.mvvmkotlin.ui.main

import java.text.SimpleDateFormat
import java.util.*

class Merchant(name:String? = "",password:String? = "",terminalID : String? = "",trID : String? = "",deviceType : String? = "",registerDate : String? = "") {
    lateinit var name : String
    lateinit var password : String
    lateinit var terminalID : String
    lateinit var trID : String
    lateinit var deviceType : String
    lateinit var registerDate : String
    lateinit var id : String
    val specialChars= ArrayList<Char>()
    init {
        this.name = name!!
        this.password = password!!
        this.terminalID = terminalID!!
        this.trID = trID!!
        this.deviceType = deviceType!!
        this.registerDate = registerDate!!
    }

    fun isNewUserValid(user:Merchant) : Merchant{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)
        if(checkPassword(user.password) && checkTerminal(user.terminalID) && !checkDeviceType(user.terminalID).equals("False") && user.name.length!=0 && user.trID.length!=0){
            return Merchant(user.name,user.password,user.terminalID,user.trID,checkDeviceType(user.terminalID),formatedDate)
        }
        return Merchant()
    }

    fun loginOfUser() : Boolean{
        return false
    }
    private fun checkPassword(password : String):Boolean{
        specialCharInit()
        if(password.length<=18 && password.length>=6){
            var upper=0
            var lower=0
            var number=0
            var special=0
            for (i in 0..password.length-1){
                if(password.get(i).isUpperCase())
                    upper=1
                else if(password.get(i).isLowerCase())
                    lower=1
                else if(password.get(i).isDigit())
                    number=1
                else if(specialChars.contains(password.get(i)))
                    special=1
            }
            return upper * lower * number * special==1
        }
        else
            return false
    }
    private fun checkTerminal(id : String):Boolean{
        if(id.length==12){
            if(id.contains("AS"))
                return true
            else if(id.contains("AT"))
                return true
            else if(id.contains("AX"))
                return true
            else if(id.contains("AU"))
                return true
        }
        return false
    }
    private fun checkDeviceType(id : String):String{
        if(id.contains("AS"))
            return "220TR"
        else if(id.contains("AT"))
            return "300TR"
        else if(id.contains("AX"))
            return "400TR"
        else if(id.contains("AU"))
            return "1000TR"
        return "False"
    }
    private fun specialCharInit(){
        specialChars.add('.')
        specialChars.add(',')
        specialChars.add('-')
        specialChars.add('?')
        specialChars.add('_')
        specialChars.add('@')
        specialChars.add('>')
        specialChars.add('<')
        specialChars.add('|')
        specialChars.add(';')
        specialChars.add(':')
        specialChars.add('+')
        specialChars.add('*')
        specialChars.add('/')
        specialChars.add('~')
        specialChars.add('"')
        specialChars.add('é')
        specialChars.add('^')
        specialChars.add('#')
        specialChars.add('$')
        specialChars.add('(')
        specialChars.add(')')
        specialChars.add('[')
        specialChars.add(']')
        specialChars.add('{')
        specialChars.add('}')
        specialChars.add('=')
        specialChars.add('&')
        specialChars.add('´')
        specialChars.add('¨')
    }
}