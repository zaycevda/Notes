package com.example.notes.domain.usecase

import com.example.notes.domain.repository.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    fun execute() = noteRepository.getAllNotes()
}