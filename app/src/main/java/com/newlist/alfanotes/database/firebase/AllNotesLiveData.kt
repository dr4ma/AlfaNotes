package com.newlist.alfanotes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.utilits.NAME
import com.newlist.alfanotes.utilits.TEXT

class AllNotesLiveData: LiveData<List<AppNote>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var Model : AppNote
    private var listModels = arrayListOf<AppNote>()
    private val mFireStoreReference = FirebaseFirestore.getInstance().collection(mAuth.currentUser?.uid.toString())

    override fun onActive() {
        mFireStoreReference.addSnapshotListener { snapshot, error ->
            if (error != null){
                return@addSnapshotListener
            }
            if(snapshot != null){
                for(doc in snapshot){
                    Model = AppNote()
                    Model.name = doc.get(NAME).toString()
                    Model.text = doc.get(TEXT).toString()
                    listModels.add(Model)
                }
                value = listModels
            }
        }
        super.onActive()
    }

    override fun onInactive() {
        listModels.clear()
        super.onInactive()
    }
}