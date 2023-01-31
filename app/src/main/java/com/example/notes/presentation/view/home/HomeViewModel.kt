package com.example.notes.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.usecase.GetAllNotesUseCase

class HomeViewModel(private val getAllNotesUseCase: GetAllNotesUseCase) : ViewModel() {

    private val _note= MutableLiveData<List<NoteParam>>()
    val note: LiveData<List<NoteParam>> = _note

    fun getAll() {
        _note.value = getAllNotesUseCase.execute()
    }
}