package com.newlist.alfanotes.database.room

import androidx.lifecycle.LiveData
import com.newlist.alfanotes.database.DataBaseRepository
import com.newlist.alfanotes.model.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao) : DataBaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
    }
}