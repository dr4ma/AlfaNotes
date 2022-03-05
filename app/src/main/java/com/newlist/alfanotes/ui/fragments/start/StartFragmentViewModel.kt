package com.newlist.alfanotes.ui.fragments.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.newlist.alfanotes.database.room.AppRoomDao
import com.newlist.alfanotes.database.room.AppRoomDatabase
import com.newlist.alfanotes.database.room.AppRoomRepository
import com.newlist.alfanotes.utilits.APP_ACTIVITY
import com.newlist.alfanotes.utilits.REPOSITORY
import com.newlist.alfanotes.utilits.TYPE_ROOM

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type:String, onSuccess:() -> Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }

}