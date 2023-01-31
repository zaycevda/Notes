package com.example.notes.presentation.view.note

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.GetCurrentNoteUseCase
import com.example.notes.domain.usecase.SaveNoteUseCase
import com.example.notes.domain.usecase.UpdateNoteUseCase
import java.text.SimpleDateFormat
import java.util.*

class NoteViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getCurrentNoteUseCase: GetCurrentNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel(){

    private val _note = MutableLiveData<NoteParam>()
    val note: LiveData<NoteParam> = _note

    private val currentDate: String by lazy {
        getDate()
    }

    fun get(noteId: Int) {
        val currentNote = getCurrentNoteUseCase.execute(noteId)
        _note.value = NoteParam(
            title = currentNote!!.title,
            description = currentNote.description,
            date = currentNote.date
        )
    }

    fun update(title: String, description: String, noteId: Int) {
        _note.value = NoteParam(
            title = title,
            description = description,
            date = currentDate
        )
        updateNoteUseCase.execute(
            param = _note.value!!,
            date = currentDate,
            noteId = noteId
        )
    }

    fun save(title: String, description: String) {
        _note.value = NoteParam(
            title = title,
            description = description,
            date = currentDate
        )
        saveNoteUseCase.execute(
            param = _note.value!!,
            date = currentDate
        )
    }

    fun delete(noteId: Int) {
        deleteNoteUseCase.execute(noteId)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {
        val spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        spf.timeZone = TimeZone.getDefault()
        return spf.format(Date())
    }
}