package com.example.notes.di

import android.content.Context
import com.example.notes.domain.usecase.*
import com.example.notes.presentation.view.home.HomeViewModelFactory
import com.example.notes.presentation.view.note.NoteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideNoteViewModelFactory(
        getCurrentNoteUseCase: GetCurrentNoteUseCase,
        saveNoteUseCase: SaveNoteUseCase,
        deleteNoteUseCase: DeleteNoteUseCase,
        updateNoteUseCase: UpdateNoteUseCase
    ) = NoteViewModelFactory(
        getCurrentNoteUseCase,
        saveNoteUseCase,
        deleteNoteUseCase,
        updateNoteUseCase
    )

    @Provides
    fun provideHomeViewModelFactory(getAllNotesUseCase: GetAllNotesUseCase) =
        HomeViewModelFactory(getAllNotesUseCase)

}