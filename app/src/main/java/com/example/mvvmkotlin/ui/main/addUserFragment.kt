package com.example.mvvmkotlin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import com.example.mvvmkotlin.databinding.AddUserFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addUserFragment : Fragment() {

    companion object {
        fun newInstance() = addUserFragment()
    }

    private val viewModel: AddUserViewModel by activityViewModels()
    private lateinit var binding : AddUserFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding= AddUserFragmentBinding.inflate(layoutInflater)
        return binding.root
        /*view.findViewById<Button>(R.id.add).setOnClickListener {
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
        }
        binding.backFromUAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addUserFragment_to_adminFragment)
        }
        /*view.findViewById<Button>(R.id.backFromUAdd).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addUserFragment_to_adminFragment)
        }*/
        return view*/
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            if(viewModel.addUserBtn(Merchant(binding.userName.text.toString(),binding.userPassword.text.toString(),binding.userTerminalID.text.toString(),binding.userTC.text.toString()))){
                Log.v("Data","True")
            }
            else{
                Log.v("Data","False")
            }
        }
        binding.backFromUAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addUserFragment_to_adminFragment)
        }
    }

}