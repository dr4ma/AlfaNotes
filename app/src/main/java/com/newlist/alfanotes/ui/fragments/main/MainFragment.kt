package com.newlist.alfanotes.ui.fragments.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.newlist.alfanotes.R
import com.newlist.alfanotes.databinding.FragmentMainBinding
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.utilits.APP_ACTIVITY
import com.newlist.alfanotes.utilits.AppPreferences
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!
    private lateinit var mViewModel: MainFragmentViewModel //m добавляется, когда не имеем доступа к полю из других классов
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        btn_add_note.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewFragment)
        }

        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list =
                it.asReversed() // перевернули лист, чтобы заметки добавлялись наверх, а не вниз
            mAdapter.setList(list)
        }
        mViewModel.allNotes.observe(this, mObserverList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                mViewModel.signOut()
                AppPreferences.setInitUser(false)
                APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    companion object {
        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.mNavController.navigate(
                R.id.action_mainFragment_to_nodeFragment,
                bundle
            )
        }
    }
}