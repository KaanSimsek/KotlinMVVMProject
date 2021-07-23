package com.example.mvvmkotlin.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class listUserFragment : Fragment() {

    companion object {
        fun newInstance() = listUserFragment()
    }

    private val viewModel: ListUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.list_user_fragment, container, false)
        val activity = activity as Context
        var listOfMerchant=viewModel.listUserBtn()
        val adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,listOfMerchant)
        view.findViewById<ListView>(R.id.usersList).adapter=adapter

        view.findViewById<Button>(R.id.backButtonForList).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_listUserFragment_to_adminFragment)
        }
        return view
    }


}