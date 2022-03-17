package com.newlist.alfanotes.database

import androidx.lifecycle.LiveData
import com.newlist.alfanotes.model.AppNote

interface DataBaseRepository {

    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note:AppNote, onSuccess:() -> Unit)
    suspend fun delete(note:AppNote, onSuccess:() -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit){} // пустая реализация нужна для необязательного имплеминтирования
    fun signOut(){}
}