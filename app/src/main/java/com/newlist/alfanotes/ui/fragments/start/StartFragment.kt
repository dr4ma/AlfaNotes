package com.newlist.alfanotes.ui.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.newlist.alfanotes.R
import com.newlist.alfanotes.databinding.FragmentStartBinding
import com.newlist.alfanotes.utilits.*
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val mBinding get() = binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        btn_room.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM){
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        mBinding.btnFirebase.setOnClickListener {
            binding?.apply {
                inputEmail.visibility = View.VISIBLE
                inputPassword.visibility = View.VISIBLE
                btnLogin.visibility = View.VISIBLE
                btnLogin.setOnClickListener {
                    val inputEmail = inputEmail.text.toString()
                    val inputPassword = inputPassword.text.toString()
                    if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                        EMAIL = inputEmail
                        PASSWORD = inputPassword
                        mViewModel.initDatabase(TYPE_FIREBASE){
                            APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
                        }
                    }
                    else{
                        showToast(getString(R.string.input_password_and_email))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}