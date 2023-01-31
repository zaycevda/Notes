package com.example.notes.data.repository

import android.content.Context
import com.example.notes.data.storage.database.AppDatabase
import com.example.notes.data.storage.entity.Note
import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NoteRepositoryImpl(private val context: Context?) : NoteRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun getAllNotes(): List<NoteParam> = runBlocking {
        context.let {
            AppDatabase.getDatabase(context!!).noteDao().getAllNotes()
                .map { note -> toNoteParam(note) }
        }
    }

    override fun getCurrentNote(noteId: Int) = runBlocking {
        context?.let {
            toNoteParam(AppDatabase.getDatabase(it).noteDao().getCurrentNote(noteId))
        }
    }

    override fun saveNote(param: NoteParam, date: String) {
        launch {
            context?.let {
                AppDatabase.getDatabase(it).noteDao().addNote(toNote(param = param, date = date))
            }
        }
    }

    override fun deleteNote(noteId: Int) {
        launch {
            context?.let {
                AppDatabase.getDatabase(it).noteDao().deleteNote(id = noteId)
            }
        }
    }

    override fun updateNote(param: NoteParam, date: String, noteId: Int) {
        launch {
            context?.let {
                AppDatabase.getDatabase(it).noteDao()
                    .updateNote(toNote(param = param, date = date, noteId = noteId))
            }
        }
    }

    private fun toNote(param: NoteParam, date: String, noteId: Int = 0) =
        Note(title = param.title, description = param.description, date = date, id = noteId)

    private fun toNoteParam(note: Note) =
        NoteParam(title = note.title, description = note.description, id = note.id, date = note.date)
}