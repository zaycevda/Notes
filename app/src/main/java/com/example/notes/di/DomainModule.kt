package com.example.notes.di

import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAllNotesUseCase(noteRepository: NoteRepository) =
        GetAllNotesUseCase(noteRepository)

    @Provides
    fun provideGetCurrentNoteUseCase(noteRepository: NoteRepository) =
        GetCurrentNoteUseCase(noteRepository)

    @Provides
    fun provideSaveNoteUseCase(noteRepository: NoteRepository) =
        SaveNoteUseCase(noteRepository)

    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository) =
        DeleteNoteUseCase(noteRepository)

    @Provides
    fun provideUpdateNoteUseCase(noteRepository: NoteRepository) =
        UpdateNoteUseCase(noteRepository)
}