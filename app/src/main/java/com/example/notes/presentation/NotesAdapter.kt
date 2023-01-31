package com.example.notes.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.domain.model.NoteParam

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var listener: OnItemClickListener? = null
    var notesList = emptyList<NoteParam>()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        with(holder.binding) {
            noteTitleTextView.text = note.title
            noteDescriptionTextView.text = note.description
            noteDateTextView.text = note.date
        }
        holder.itemView.setOnClickListener {
            listener?.onClicked(note.id)
        }
    }

    fun setData(arrList: List<NoteParam>) {
        notesList = arrList
    }

    override fun getItemCount() = notesList.size

    interface OnItemClickListener {
        fun onClicked(noteId: Int)
    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }
}