package com.newlist.alfanotes.ui.fragments.addnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.newlist.alfanotes.R
import com.newlist.alfanotes.databinding.FragmentAddNewBinding
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.utilits.APP_ACTIVITY
import com.newlist.alfanotes.utilits.showToast
import kotlinx.android.synthetic.main.fragment_add_new.*

class AddNewFragment : Fragment() {

    private var binding : FragmentAddNewBinding? = null
    private val mBinding get() = binding!!
    private lateinit var mViewModel:AddNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(AddNewViewModel::class.java)
        btn_add_note.setOnClickListener {
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            if(name.isEmpty()){
                showToast(getString(R.string.toast_enter_name))
            }
            else{
                mViewModel.insert(AppNote(name = name, text = text)){
                }
                APP_ACTIVITY.mNavController.navigate(R.id.action_addNewFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}