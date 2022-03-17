package com.newlist.alfanotes.ui.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.newlist.alfanotes.utilits.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOut()
    }
}