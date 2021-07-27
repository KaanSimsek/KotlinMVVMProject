package com.example.mvvmkotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mvvmkotlin.databinding.UserDataFragmentBinding

class UserDataFragment : Fragment() {

    companion object {
        fun newInstance() = UserDataFragment()
    }

    private val viewModel: UserDataViewModel by activityViewModels()
    private lateinit var binding:UserDataFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=UserDataFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding
    }
}