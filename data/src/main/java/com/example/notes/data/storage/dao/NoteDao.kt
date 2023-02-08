package com.example.notes.data.storage.dao

import androidx.room.*
import com.example.notes.data.storage.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getCurrentNote(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNote(id: Int)
}