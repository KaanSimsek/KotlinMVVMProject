package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import com.example.mvvmkotlin.databinding.AddBankToUserFragmentBinding

class addBankToUser : Fragment() {

    companion object {
        fun newInstance() = addBankToUser()
    }
    private lateinit var binding:AddBankToUserFragmentBinding
    private val viewModel: BankTransactionViewModel  by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding=AddBankToUserFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bankName=""
        var bankID=""
        binding.yapiKrediAddRadio.setOnClickListener {
            bankName = "Yapıkredi"
            bankID = "0"
        }
        binding.denizBankAddRadio.setOnClickListener {
            bankName = "DenizBank"
            bankID = "1"
        }
        binding.isBankasiAddRadio.setOnClickListener {
            bankName = "İşBankası"
            bankID = "2"
        }
        binding.vakifBankAddRadio.setOnClickListener {
            bankName = "VakıfBank"
            bankID = "3"
        }
        binding.buttonForAddBank.setOnClickListener {
            viewModel.addBankBtn(Bank(bankName,bankID),binding.getTerminalIdOfUser.text.toString())
        }
        binding.backFAddBank.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addBankToUser_to_adminFragment)
        }
    }

}