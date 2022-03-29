package com.newlist.alfanotes.ui.fragments.node

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.newlist.alfanotes.R
import com.newlist.alfanotes.databinding.FragmentMainBinding
import com.newlist.alfanotes.databinding.FragmentNodeBinding
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.ui.fragments.main.MainAdapter
import com.newlist.alfanotes.ui.fragments.main.MainFragmentViewModel
import com.newlist.alfanotes.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_main.*

class NodeFragment : Fragment() {

    private var binding: FragmentNodeBinding? = null
    private val mBinding get() = binding!!
    private lateinit var mViewModel: NoteFragmentViewModel //m добавляется, когда не имеем доступа к полю из других классов
    private lateinit var mCurrentNote : AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNodeBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        binding?.apply {
            noteName.text = mCurrentNote.name
            noteText.text = mCurrentNote.text
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.node_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote){
                }
                APP_ACTIVITY.mNavController.navigate(R.id.action_nodeFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}