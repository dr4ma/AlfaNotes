package com.newlist.alfanotes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newlist.alfanotes.model.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getAppRoomDao(): AppRoomDao

    companion object {
        @Volatile //не должна кэшироватся, чтобы получать актуальные значения
        private var database: AppRoomDatabase? = null

        @Synchronized // запред на обращение нескольких экземпляров одновременно
        fun getInstance(context: Context): AppRoomDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                return database as AppRoomDatabase
            } else {
                return database as AppRoomDatabase
            }
        }
    }
}