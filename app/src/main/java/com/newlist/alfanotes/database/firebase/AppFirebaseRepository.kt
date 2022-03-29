package com.newlist.alfanotes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.newlist.alfanotes.database.DataBaseRepository
import com.newlist.alfanotes.model.AppNote
import com.newlist.alfanotes.utilits.*

class AppFirebaseRepository: DataBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val mFireStoreReference = FirebaseFirestore.getInstance()

    override val allNotes: LiveData<List<AppNote>>
        get() = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val mapNote = hashMapOf<String, Any>()
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mFireStoreReference.collection(mAuth.currentUser?.uid.toString()).document(note.name).set(mapNote).addOnCompleteListener { task ->
            if(task.isSuccessful){
                onSuccess()
            }
            else{
                showToast(task.exception.toString())
            }
        }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        mFireStoreReference.collection(mAuth.currentUser?.uid.toString()).document(note.name).delete().addOnCompleteListener {task ->
            if(task.isSuccessful){
                onSuccess()
            }
            else{
                showToast(task.exception.toString())
            }
        }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {

        if (mAuth.currentUser != null){
            onSuccess()
        }
        else{
            mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            mFireStoreReference.collection(mAuth.currentUser?.uid.toString())
                            onSuccess()
                        }
                        .addOnFailureListener {
                            onFail(it.message.toString())
                        }
                }
        }

    }

    override fun signOut() {
        mAuth.signOut()
    }
}