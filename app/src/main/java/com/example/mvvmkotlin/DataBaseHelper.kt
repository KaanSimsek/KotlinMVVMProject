package com.example.mvvmkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.mvvmkotlin.ui.main.Bank
import com.example.mvvmkotlin.ui.main.Merchant
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


val database_name="DataBaseForApp"
const val user_table = "Users"

val col_id="ID"
val col_name="Name"
val col_password="Password"
val col_TerminalId="TerminalID"
val col_DeviceType="DeviceType"
val col_trID="TrID"
val col_registerDate="RegisterDate"
val col_bankName="BankName"
val col_bankID="BankID"
val col_bankActiveOrNot="BankActiveOrNot"
val col_userList="UserList"

class DataBaseHelper @Inject constructor(@ApplicationContext private val context: Context):SQLiteOpenHelper(context, database_name, null, 3){
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = " CREATE TABLE " + user_table + "(" +
                col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_name + " VARCHAR(256)," +
                col_password + " VARCHAR(256)," +
                col_TerminalId + " VARCHAR(256)," +
                col_DeviceType + " VARCHAR(256)," +
                col_trID + " VARCHAR(256)," +
                col_registerDate + " VARCHAR(256))"
        db?.execSQL(createTable)

        onUpgrade(db,2,3)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (oldVersion < 3) {
            db?.execSQL("ALTER TABLE " + user_table + " ADD COLUMN " + col_bankName +" VARCHAR(250)")
            db?.execSQL("ALTER TABLE " + user_table + " ADD COLUMN " + col_bankActiveOrNot + " VARCHAR(250)")
        }

    }
    fun insertData(user: Merchant):Boolean{

        val db=this.writableDatabase
        val cv= ContentValues()
        cv.put(col_name, user.getName())
        cv.put(col_password, user.getPassword())
        cv.put(col_TerminalId, user.getTerminalID())
        cv.put(col_DeviceType, user.getDeviceType())
        cv.put(col_trID, user.getTerminalID())
        cv.put(col_registerDate, user.getRegisterDate())
        cv.put(col_bankName,"")
        cv.put(col_bankActiveOrNot,"")
        var result=db.insert(user_table, null, cv)
        if(result==(1).toLong()){
            Toast.makeText(context, "Unsuccess", Toast.LENGTH_LONG).show()
            return true
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
        }
        db.close()
        return false
    }
    fun readData(): MutableList<Merchant> {
        var listOfTable : MutableList<Merchant> = ArrayList()
        val db = this.readableDatabase
        var question = "Select * from " + user_table
        var res = db.rawQuery(question, null)
        if(res.moveToFirst()){
            do {
                var merchant = Merchant()
                merchant.setTerminalID( res.getString(res.getColumnIndex(col_id)))
                merchant.setName(res.getString(res.getColumnIndex(col_name)))
                merchant.setPassword(res.getString(res.getColumnIndex(col_password)))
                merchant.setTerminalID(res.getString(res.getColumnIndex(col_TerminalId)))
                merchant.setDeviceType( res.getString(res.getColumnIndex(col_DeviceType)))
                merchant.setTRID(res.getString(res.getColumnIndex(col_trID)))
                merchant.setRegisterDate(res.getString(res.getColumnIndex(col_registerDate)))
                listOfTable.add(merchant)
            }while (res.moveToNext())
        }

        res.close()
        db.close()
        return listOfTable
    }
    fun deleteData(terminalID: String):Boolean{
        var db = this.writableDatabase
        val success= db.delete(user_table, col_TerminalId + "=?", arrayOf(terminalID))
        db.close()
        if(Integer.parseInt("$success")==-1){
            Toast.makeText(context,"User can not deleted",Toast.LENGTH_SHORT).show()
        }
        return Integer.parseInt("$success")!=-1
    }

    fun addBankToUser(userTerminalID : String,bank : Bank) {
        var db = this.writableDatabase
        val cv= ContentValues()
        var query = "Select * from " + user_table
        var res = db.rawQuery(query,null)
        var merchant= Merchant("","","","","","")
        var bankUser : String = ""
        var userBankActive: String=""
        var userBankList =ArrayList<String>()
        var userBankActiveList = ArrayList<String>()
        if (res.moveToFirst()){
            do {
                if(res.getString(res.getColumnIndex(col_TerminalId)).equals(userTerminalID)){
                    merchant.setTerminalID( res.getString(res.getColumnIndex(col_id)))
                    merchant.setName(res.getString(res.getColumnIndex(col_name)))
                    merchant.setPassword(res.getString(res.getColumnIndex(col_password)))
                    merchant.setTerminalID(res.getString(res.getColumnIndex(col_TerminalId)))
                    merchant.setDeviceType( res.getString(res.getColumnIndex(col_DeviceType)))
                    merchant.setTRID(res.getString(res.getColumnIndex(col_trID)))
                    merchant.setRegisterDate(res.getString(res.getColumnIndex(col_registerDate)))
                    bankUser=res.getString(res.getColumnIndex(col_bankName))
                    userBankActive=res.getString(res.getColumnIndex(col_bankActiveOrNot))
                }
            }while (res.moveToNext())
        }
        userBankList = arrayToArrayList(bankUser.split(','))
        userBankActiveList = arrayToArrayList(userBankActive.split(','))
        userBankActiveList.add("false")
        if(userBankList.contains(bank.bankName)){
            Toast.makeText(context,"This bank is in users list thus you can not add it.", Toast.LENGTH_LONG).show()
            return
        }
        else {
            if (userBankList.size == 0) {
                bankUser += bank.bankName
                userBankActive += "false"
            } else {
                bankUser += "," + bank.bankName
                userBankActive += ",false"
            }
            cv.put(col_name, merchant.getName())
            cv.put(col_password, merchant.getPassword())
            cv.put(col_TerminalId, merchant.getTerminalID())
            cv.put(col_DeviceType, merchant.getDeviceType())
            cv.put(col_trID, merchant.getTerminalID())
            cv.put(col_registerDate, merchant.getRegisterDate())
            cv.put(col_bankName, bankUser)
            cv.put(col_bankActiveOrNot, userBankActive)
            val success = db.update(user_table, cv, col_TerminalId + "=?", arrayOf(userTerminalID)).toLong()
            db.close()
            if (Integer.parseInt("$success") == -1)
                Toast.makeText(context, "Merchant ID is wrong", Toast.LENGTH_LONG).show()
        }
    }

    fun arrayToArrayList(array : List<String> ) : ArrayList<String>{
        var arrayList=ArrayList<String>()
        for (i in 0..array.size-1){
            arrayList.add(array[i])
        }
        return arrayList
    }

    fun deleteBankToUser(userTerminalID: String,bank: Bank){
        var db = this.writableDatabase
        val cv= ContentValues()
        var query = "Select * from " + user_table
        var res = db.rawQuery(query,null)
        var merchant= Merchant("","","","","","")
        var bankUser : String = ""
        var userBankActive: String=""
        var userBankList =ArrayList<String>()
        var userBankActiveList = ArrayList<String>()
        if (res.moveToFirst()){
            do {
                if(res.getString(res.getColumnIndex(col_TerminalId)).equals(userTerminalID)){
                    merchant.setTerminalID( res.getString(res.getColumnIndex(col_id)))
                    merchant.setName(res.getString(res.getColumnIndex(col_name)))
                    merchant.setPassword(res.getString(res.getColumnIndex(col_password)))
                    merchant.setTerminalID(res.getString(res.getColumnIndex(col_TerminalId)))
                    merchant.setDeviceType( res.getString(res.getColumnIndex(col_DeviceType)))
                    merchant.setTRID(res.getString(res.getColumnIndex(col_trID)))
                    merchant.setRegisterDate(res.getString(res.getColumnIndex(col_registerDate)))
                    bankUser=res.getString(res.getColumnIndex(col_bankName))
                    userBankActive=res.getString(res.getColumnIndex(col_bankActiveOrNot))
                }
            }while (res.moveToNext())
        }
        userBankList = arrayToArrayList(bankUser.split(','))
        userBankActiveList = arrayToArrayList(userBankActive.split(','))

        if(userBankList.contains(bank.bankName)){
            userBankActiveList.removeAt(userBankList.indexOf(bank.bankName))
            userBankList.remove(bank.bankName)
            cv.put(col_name, merchant.getName())
            cv.put(col_password, merchant.getPassword())
            cv.put(col_TerminalId, merchant.getTerminalID())
            cv.put(col_DeviceType, merchant.getDeviceType())
            cv.put(col_trID, merchant.getTerminalID())
            cv.put(col_registerDate, merchant.getRegisterDate())
            cv.put(col_bankName,convertListToStr(userBankList))
            cv.put(col_bankActiveOrNot,convertListToStr(userBankActiveList))
            val success=db.update(user_table,cv, col_TerminalId + "=?", arrayOf(userTerminalID)).toLong()
            db.close()
            if( Integer.parseInt("$success") !=-1)
                Toast.makeText(context,"Successfuly deleted", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ID is wrong", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"This bank is not in users list thus you can not delete it.",
                Toast.LENGTH_LONG).show()
            return
        }
    }
    fun convertListToStr(list:ArrayList<String>):String{
        var str=""
        for (i in 0..list.size-1){
            if(i==1){
                str+=list.get(i)
            }
            else if(i==0)
                continue
            else{
                str= str + "," + list.get(i)
            }
        }
        return str
    }
    //fun findTheIndexOfElement

    fun activateBanksOrDeactivateBanks(userTerminalID : String,banks:ArrayList<String>,active : Boolean) {
        var db = this.writableDatabase
        val cv = ContentValues()
        var query = "Select * from " + user_table
        var res = db.rawQuery(query, null)
        var merchant = Merchant("", "", "", "", "", "")
        var bankUser: String = ""
        var userBankActive: String = ""
        var userBankList = ArrayList<String>()
        var userBankActiveList = ArrayList<String>()
        if (res.moveToFirst()) {
            do {
                if (res.getString(res.getColumnIndex(col_TerminalId)).equals(userTerminalID)) {
                    merchant.setTerminalID( res.getString(res.getColumnIndex(col_id)))
                    merchant.setName(res.getString(res.getColumnIndex(col_name)))
                    merchant.setPassword(res.getString(res.getColumnIndex(col_password)))
                    merchant.setTerminalID(res.getString(res.getColumnIndex(col_TerminalId)))
                    merchant.setDeviceType( res.getString(res.getColumnIndex(col_DeviceType)))
                    merchant.setTRID(res.getString(res.getColumnIndex(col_trID)))
                    merchant.setRegisterDate(res.getString(res.getColumnIndex(col_registerDate)))
                    bankUser = res.getString(res.getColumnIndex(col_bankName))
                    userBankActive = res.getString(res.getColumnIndex(col_bankActiveOrNot))
                }
            } while (res.moveToNext())
        }
        Toast.makeText(context,bankUser, Toast.LENGTH_LONG).show()
        userBankList = arrayToArrayList(bankUser.split(','))
        userBankActiveList = arrayToArrayList(userBankActive.split(','))
        if (active) {
            for (i in 0..banks.size - 1) {
                if (userBankList.contains(banks.get(i))) {
                    if (userBankActiveList.get(userBankList.indexOf(banks.get(i))).equals("false"))
                        userBankActiveList.set(userBankList.indexOf(banks.get(i)), "true")
                    else {
                        Toast.makeText(context, "This bank is already active", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        else{
            for (i in 0..banks.size - 1) {
                if (userBankList.contains(banks.get(i))) {
                    if (userBankActiveList.get(userBankList.indexOf(banks.get(i))).equals("true"))
                        userBankActiveList.set(userBankList.indexOf(banks.get(i)), "false")
                    else
                        Toast.makeText(context, "This bank is already deactive", Toast.LENGTH_LONG).show()
                }

            }
        }

        cv.put(col_name, merchant.getName())
        cv.put(col_password, merchant.getPassword())
        cv.put(col_TerminalId, merchant.getTerminalID())
        cv.put(col_DeviceType, merchant.getDeviceType())
        cv.put(col_trID, merchant.getTerminalID())
        cv.put(col_registerDate, merchant.getRegisterDate())
        cv.put(col_bankName,bankUser)
        cv.put(col_bankActiveOrNot,convertListToStr(userBankActiveList))

        val success=db.update(user_table,cv, col_TerminalId + "=?", arrayOf(userTerminalID)).toLong()
        db.close()
        if( Integer.parseInt("$success") !=-1)
            Toast.makeText(context,"Successfuly changed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"ID is wrong", Toast.LENGTH_LONG).show()
    }
    fun checkWhetherUserInDatabase(id:String,password:String) : Boolean{
        var db = this.readableDatabase

        var query = "Select * from " + user_table
        var res = db.rawQuery(query, null)
        if (res.moveToFirst()) {
            do {
                if(res.getString(res.getColumnIndex(col_TerminalId)).equals(id)) {
                    if (res.getString(res.getColumnIndex(col_password)).equals(password)) {
                        db.close()
                        return true
                    }
                }
            } while (res.moveToNext())
        }
        db.close()
        return false
    }

    fun returnListOfUserData(id: String,password: String) : ArrayList<String>{
        var arr= ArrayList<String>()
        var db = this.readableDatabase
        var query = "Select * from " + user_table
        var res = db.rawQuery(query, null)
        if (res.moveToFirst()) {
            do {
                if(res.getString(res.getColumnIndex(col_TerminalId)).equals(id)) {
                    if (res.getString(res.getColumnIndex(col_password)).equals(password)){
                        arr.add(res.getString(res.getColumnIndex(col_id)))
                        arr.add(res.getString(res.getColumnIndex(col_name)))
                        arr.add(res.getString(res.getColumnIndex(col_password)))
                        arr.add(res.getString(res.getColumnIndex(col_TerminalId)))
                        arr.add(res.getString(res.getColumnIndex(col_DeviceType)))
                        arr.add(res.getString(res.getColumnIndex(col_trID)))
                        arr.add(res.getString(res.getColumnIndex(col_registerDate)))
                        arr.add(res.getString(res.getColumnIndex(col_bankName)))
                        arr.add(res.getString(res.getColumnIndex(col_bankActiveOrNot)))
                    }
                }
            } while (res.moveToNext())
        }
        db.close()
        return arr
    }
}