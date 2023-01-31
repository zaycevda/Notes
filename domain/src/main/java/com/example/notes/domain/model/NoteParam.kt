package com.example.notes.domain.model

data class NoteParam(
    val title: String,
    val description: String?,
    val date: String?,
    val id: Int = 0
)