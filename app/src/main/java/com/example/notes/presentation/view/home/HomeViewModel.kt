package com.example.notes.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.usecase.GetAllNotesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val getAllNotesUseCase: GetAllNotesUseCase) : ViewModel() {

    private val _note = MutableLiveData<List<NoteParam>>()
    val note: LiveData<List<NoteParam>> = _note

    fun getAll() {
        getAllNotesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { listNoteParam ->
                _note.value = listNoteParam
            }
    }
}