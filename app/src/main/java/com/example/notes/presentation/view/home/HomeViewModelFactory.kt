package com.example.notes.presentation.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.notes.domain.usecase.GetAllNotesUseCase

class HomeViewModelFactory(private val getAllNotesUseCase: GetAllNotesUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras) =
        HomeViewModel(getAllNotesUseCase) as T
}