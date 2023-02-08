package com.example.notes.domain.usecase

import com.example.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(noteId: Int) = noteRepository.deleteNote(noteId = noteId)
}