package com.example.notes.presentation.view.note

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.app.App
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.utils.DEFAULT_NOTE_ID
import javax.inject.Inject

class NoteFragment : Fragment() {

    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory
    private var binding: FragmentNoteBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.applicationContext as App).appComponent.injectNoteFragment(this)
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        val noteId = requireArguments().getInt(NOTE_ID)
        binding?.apply {
            deleteNoteButton.setOnClickListener {
                alertDialog(noteId = noteId)
            }
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            if (noteId != DEFAULT_NOTE_ID) {
                viewModel.note.observe(viewLifecycleOwner) {
                    titleEditText.setText(it.title)
                    descriptionEditText.setText(it.description)
                    dateText.text = it.date
                }
                viewModel.get(noteId)

                deleteNoteButton.visibility = View.VISIBLE
                confirmButton.setOnClickListener {
                    if (titleEditText.text.toString().isEmpty())
                        Toast.makeText(
                            context,
                            "Поле \"Название\" обязательно для заполнения",
                            Toast.LENGTH_SHORT
                        ).show()
                    else if (
                        viewModel.note.value?.title == titleEditText.text.toString()
                        && viewModel.note.value?.description == descriptionEditText.text.toString())
                        findNavController().popBackStack()
                    else {
                        dateText.text = viewModel.currentDate
                        viewModel.update(
                            title = titleEditText.text.toString(),
                            description = descriptionEditText.text.toString(),
                            noteId = noteId
                        )
                        findNavController().popBackStack()
                    }
                }
            } else {
                dateText.text = viewModel.currentDate
                descriptionEditText.setText("")
                confirmButton.setOnClickListener {
                    if (titleEditText.text.toString().isEmpty())
                        Toast.makeText(
                            context,
                            "Поле \"Название\" обязательно для заполнения",
                            Toast.LENGTH_SHORT
                        ).show()
                    else {
                        viewModel.save(
                            title = titleEditText.text.toString(),
                            description = descriptionEditText.text.toString()
                        )
                        findNavController().popBackStack()
                    }
                }
            }
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun alertDialog(noteId: Int) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog)
        dialog.findViewById<TextView>(R.id.yesButton).setOnClickListener {
            viewModel.delete(noteId)
            findNavController().popBackStack()
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.noButton).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    companion object {
        const val NOTE_ID = "note_id"
    }
}