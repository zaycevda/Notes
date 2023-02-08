package com.example.notes.domain.repository

import com.example.notes.domain.model.NoteParam
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NoteRepository {

    fun getAllNotes(): Single<List<NoteParam>>

    fun getCurrentNote(noteId: Int): Single<NoteParam>?

    fun saveNote(param: NoteParam, date: String): Completable

    fun updateNote(param: NoteParam, date: String, noteId: Int): Completable

    fun deleteNote(noteId: Int): Completable
}