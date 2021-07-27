package com.example.mvvmkotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import com.example.mvvmkotlin.databinding.DeleteBankFromUserFragmentBinding

class DeleteBankFromUserFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteBankFromUserFragment()
    }

    private val viewModel: BankTransactionViewModel by activityViewModels()
    private lateinit var binding: DeleteBankFromUserFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DeleteBankFromUserFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bankName=""
        var bankID=""
        binding.buttonForDelBank.setOnClickListener {
            if(bankName.length>0){
                viewModel.deleteBankBtn(Bank(bankName,bankID),binding.getTerminalIdOfUserDel.text.toString())
            }

        }

        binding.backFDeleteBank.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deleteBankFromUserFragment3_to_adminFragment)
        }

        binding.yapiKrediDelRadio.setOnClickListener {
            bankName = "Yapıkredi"
            bankID = "0"
        }

        binding.denizBankDelRadio.setOnClickListener {
            bankName = "DenizBank"
            bankID = "1"
        }

        binding.isBankasiDelRadio.setOnClickListener {
            bankName = "İşBankası"
            bankID = "2"
        }

        binding.vakifBankDelRadio.setOnClickListener {
            bankName = "VakıfBank"
            bankID = "3"
        }
    }


}