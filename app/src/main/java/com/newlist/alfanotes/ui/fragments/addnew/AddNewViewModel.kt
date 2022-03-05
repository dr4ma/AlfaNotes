package com.newlist.alfanotes.ui.fragments.addnew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(note : AppNote, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }
}