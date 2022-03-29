package com.newlist.alfanotes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.newlist.alfanotes.R
import com.newlist.alfanotes.databinding.ActivityMainBinding
import com.newlist.alfanotes.utilits.APP_ACTIVITY
import com.newlist.alfanotes.utilits.AppPreferences

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var mNavController: NavController
    private var binding: ActivityMainBinding? = null // может быть null, так как после закрытия активити нужно удалять ссылку на binding
    val mBinding get() = binding!! // точно не null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)

        AppPreferences.getPreferences(APP_ACTIVITY)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null // чтобы не бьло утечки памяти
    }
}