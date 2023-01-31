package com.example.notes.domain.repository

import com.example.notes.domain.model.NoteParam

interface NoteRepository {

    fun getAllNotes(): List<NoteParam>

    fun getCurrentNote(noteId: Int): NoteParam?

    fun saveNote(param: NoteParam, date: String)

    fun deleteNote(noteId: Int)

    fun updateNote(param: NoteParam, date: String, noteId: Int)
}