package com.example.mvvmkotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mvvmkotlin.databinding.FragmentUserBinding


class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserDataViewModel by activityViewModels()
    companion object {
        fun newInstance() = UserDataFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userModeEnter.setOnClickListener {
            var password=binding.userPasswordUserMode.text.toString()
            var id=binding.userIdUserMode.text.toString()
        }
    }
}