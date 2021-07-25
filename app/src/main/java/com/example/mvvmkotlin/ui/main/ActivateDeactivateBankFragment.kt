package com.example.mvvmkotlin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mvvmkotlin.R
import com.example.mvvmkotlin.databinding.ActivateDeactivateBankFragmentBinding

class ActivateDeactivateBankFragment : Fragment() {

    companion object {
        fun newInstance() = ActivateDeactivateBankFragment()
    }

    private val viewModel: BankTransactionViewModel by activityViewModels()
    private lateinit var binding: ActivateDeactivateBankFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=ActivateDeactivateBankFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bankNames = ArrayList<String>()
        binding.activateBankBtn.setOnClickListener {
            if(binding.yapikrediCheckBox.isChecked)
                bankNames.add("Yapıkredi")
            if (binding.denizBankCheckBox.isChecked)
                bankNames.add("DenizBank")
            if (binding.isBankasiCheckBox.isChecked)
                bankNames.add("İşBankası")
            if (binding.vakifBankCheckBox.isChecked)
                bankNames.add("VakıfBank")
            if(bankNames.size!=0)
                viewModel.activateDeactivateBankBtn(bankNames,binding.takeTerminalIdActivateBank.text.toString(),true)
        }
        binding.deActivateBankBtn.setOnClickListener {
            if(binding.yapikrediCheckBox.isChecked)
                bankNames.add("Yapıkredi")
            if (binding.denizBankCheckBox.isChecked)
                bankNames.add("DenizBank")
            if (binding.isBankasiCheckBox.isChecked)
                bankNames.add("İşBankası")
            if (binding.vakifBankCheckBox.isChecked)
                bankNames.add("VakıfBank")
            if(bankNames.size!=0)
                viewModel.activateDeactivateBankBtn(bankNames,binding.takeTerminalIdActivateBank.text.toString(),false)
        }
        binding.backFromActivateBank.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_activateDeactivateBankFragment_to_adminFragment)
        }
    }


}