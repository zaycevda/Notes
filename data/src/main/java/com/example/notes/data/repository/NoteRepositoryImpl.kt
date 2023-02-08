package com.example.notes.data.repository

import android.content.Context
import com.example.notes.data.storage.database.AppDatabase
import com.example.notes.data.storage.entity.Note
import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class NoteRepositoryImpl(private val context: Context) : NoteRepository {

    override fun getAllNotes(): Single<List<NoteParam>> {
        return Single.create { subscriber ->
            context.let {
                val list = AppDatabase.getDatabase(it).noteDao().getAllNotes()
                    .map { note -> toNoteParam(note) }
                subscriber.onSuccess(list)
            }
        }
    }

    override fun getCurrentNote(noteId: Int): Single<NoteParam> {
        return Single.create { subscriber ->
            context.let {
                val note =
                    toNoteParam(AppDatabase.getDatabase(it).noteDao().getCurrentNote(noteId))
                subscriber.onSuccess(note)
            }
        }
    }

    override fun saveNote(param: NoteParam, date: String): Completable {
        return Completable.fromAction {
            context.let {
                AppDatabase.getDatabase(it).noteDao().addNote(toNote(param = param, date = date))
            }
        }
    }

    override fun updateNote(param: NoteParam, date: String, noteId: Int): Completable {
        return Completable.fromAction {
            context.let {
                AppDatabase.getDatabase(it).noteDao()
                    .updateNote(toNote(param = param, date = date, noteId = noteId))
            }
        }
    }

    override fun deleteNote(noteId: Int): Completable {
        return Completable.fromAction {
            context.let {
                AppDatabase.getDatabase(it).noteDao().deleteNote(id = noteId)
            }
        }
    }

    private fun toNote(param: NoteParam, date: String, noteId: Int = 0) =
        Note(title = param.title, description = param.description, date = date, id = noteId)

    private fun toNoteParam(note: Note) =
        NoteParam(title = note.title, description = note.description, id = note.id, date = note.date)
}