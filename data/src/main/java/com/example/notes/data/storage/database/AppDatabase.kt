package com.example.notes.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.data.storage.dao.NoteDao
import com.example.notes.data.storage.entity.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        var noteDatabase: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (noteDatabase == null) {
                noteDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "notes.database"
                ).build()
            }
            return noteDatabase!!
        }
    }

    abstract fun noteDao(): NoteDao
}