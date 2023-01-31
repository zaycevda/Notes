package com.example.notes.presentation.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.presentation.NotesAdapter
import com.example.notes.R
import com.example.notes.app.App
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.domain.model.NoteParam
import com.example.notes.presentation.view.note.NoteFragment
import com.example.notes.utils.DEFAULT_NOTE_ID
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var binding: FragmentHomeBinding
    private var notesArr = ArrayList<NoteParam>()
    private val onClicked = object : NotesAdapter.OnItemClickListener {
        override fun onClicked(noteId: Int) {
            findNavController().navigate(
                R.id.action_homeFragment_to_noteFragment,
                bundleOf(NoteFragment.NOTE_ID to noteId)
            )
        }
    }
    private val adapter by lazy { NotesAdapter() }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            homeViewModelFactory
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity?.applicationContext as App).appComponent.injectHomeFragment(this)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.setHasFixedSize(true)
            viewModel.note.observe(viewLifecycleOwner) {
                adapter.setData(it)
                notesArr = it as ArrayList<NoteParam>
                adapter.notesList = it
            }
            viewModel.getAll()
            adapter.setOnClickListener(onClicked)
            recyclerView.adapter = adapter
            addNoteButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_noteFragment,
                    bundleOf(NoteFragment.NOTE_ID to DEFAULT_NOTE_ID)
                )
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {

                    val tempArr = ArrayList<NoteParam>()

                    for (arr in notesArr) {
                        if (arr.description?.lowercase(Locale.getDefault())
                                ?.contains(newText.toString()) == true
                        )
                            tempArr.add(arr)
                        else if (arr.title.lowercase(Locale.getDefault())
                                .contains(newText.toString())
                        )
                            tempArr.add(arr)
                    }
                    adapter.setData(tempArr)
                    adapter.notifyDataSetChanged()
                    return true
                }
            })
        }
        return binding.root
    }
}