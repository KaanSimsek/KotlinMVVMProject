package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteUserFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteUserFragment()
    }

    private val viewModel: DeleteUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.delete_user_fragment, container, false)
        view.findViewById<Button>(R.id.backFromDeleteUser).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deleteUserFragment_to_adminFragment)
        }

        view.findViewById<Button>(R.id.deleteUserBtn).setOnClickListener {
            viewModel.deleteUserBtn(view.findViewById<EditText>(R.id.deleteWithTerminalET).text.toString())
        }
        return view
    }

}