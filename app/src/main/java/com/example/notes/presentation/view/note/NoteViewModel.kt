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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class NoteViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getCurrentNoteUseCase: GetCurrentNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _note = MutableLiveData<NoteParam>()
    val note: LiveData<NoteParam> = _note

    val currentDate: String by lazy {
        getDate()
    }

    fun get(noteId: Int) {
        getCurrentNoteUseCase.execute(noteId)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { note ->
                _note.value = NoteParam(
                    title = note.title,
                    description = note.description,
                    date = note.date
                )
            }
    }

    fun save(title: String, description: String) {
        val noteParam = NoteParam(
            title = title,
            description = description,
            date = currentDate
        )
        saveNoteUseCase.execute(param = noteParam, date = currentDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _note.value = noteParam
            }
    }

    fun update(title: String, description: String, noteId: Int) {
        val noteParam = NoteParam(
            title = title,
            description = description,
            date = currentDate
        )
        updateNoteUseCase.execute(param = noteParam, date = currentDate, noteId = noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _note.value = noteParam
            }
    }

    fun delete(noteId: Int) {
        deleteNoteUseCase.execute(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {
        val spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        spf.timeZone = TimeZone.getDefault()
        return spf.format(Date())
    }
}