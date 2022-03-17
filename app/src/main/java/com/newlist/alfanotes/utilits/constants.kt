package com.newlist.alfanotes.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.newlist.alfanotes.database.DataBaseRepository
import com.newlist.alfanotes.ui.activity.MainActivity

lateinit var APP_ACTIVITY:MainActivity
lateinit var REPOSITORY: DataBaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val NAME = "name"
const val TEXT = "text"
lateinit var EMAIL:String
lateinit var PASSWORD:String

