package com.example.notes.domain.usecase

import com.example.notes.domain.model.NoteParam
import com.example.notes.domain.repository.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(param: NoteParam, date: String?, noteId: Int) {
        noteRepository.updateNote(param = param, date = date, noteId = noteId)
    }
}