package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R

class addUserFragment : Fragment() {

    companion object {
        fun newInstance() = addUserFragment()
    }

    private val viewModel: AddUserViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.add_user_fragment, container, false)

        view.findViewById<Button>(R.id.add).setOnClickListener {
            if(viewModel.addUserBtn(Merchant(view.findViewById<EditText>(R.id.userName).text.toString()
                    ,view.findViewById<EditText>(R.id.userPassword).text.toString()
                    ,view.findViewById<EditText>(R.id.userTerminalID).text.toString()
                    ,view.findViewById<EditText>(R.id.userTC).text.toString()
                    ,""
                    ,""))){
                Log.v("Data","True")
            }
            else{
                Log.v("Data","False")
            }
            //viewModel.addUserBtn()
        }
        view.findViewById<Button>(R.id.backFromUAdd).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addUserFragment_to_adminFragment)
        }
        return view
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}