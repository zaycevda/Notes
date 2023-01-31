package com.example.notes.domain.usecase

import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.repository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(param: NoteParam, date: String) {
        noteRepository.saveNote(param = param, date = date)
    }
}