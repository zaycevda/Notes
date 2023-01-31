package com.example.notes.di

import android.content.Context
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNoteRepository(context: Context): NoteRepository =
        NoteRepositoryImpl(context)
}