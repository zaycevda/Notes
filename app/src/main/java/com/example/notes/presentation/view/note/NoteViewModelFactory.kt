package com.example.notes.presentation.view.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.GetCurrentNoteUseCase
import com.example.notes.domain.usecase.SaveNoteUseCase
import com.example.notes.domain.usecase.UpdateNoteUseCase

class NoteViewModelFactory(
    private val getCurrentNoteUseCase: GetCurrentNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        NoteViewModel(
            saveNoteUseCase,
            getCurrentNoteUseCase,
            updateNoteUseCase,
            deleteNoteUseCase
        ) as T

}