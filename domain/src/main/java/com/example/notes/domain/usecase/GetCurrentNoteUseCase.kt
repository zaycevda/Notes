package com.example.notes.domain.usecase

import com.example.notes.domain.repository.NoteRepository

class GetCurrentNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(noteId: Int) = noteRepository.getCurrentNote(noteId = noteId)
}