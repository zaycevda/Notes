package com.example.notes.di

import com.example.notes.presentation.view.home.HomeFragment
import com.example.notes.presentation.view.note.NoteFragment
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
    fun injectNoteFragment(noteFragment: NoteFragment)
    fun injectHomeFragment(homeFragment: HomeFragment)
}