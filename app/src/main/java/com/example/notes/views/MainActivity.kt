package com.example.notes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.models.Note
import com.example.notes.models.NoteModel
import com.example.notes.presenter.MainPresenter
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), IMainActivity{

    private val presenter = MainPresenter(this, NoteModel())

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.loadNotes()

        initialAdapter()

    }

    private fun initialAdapter(){
        recyclerView = binding.recyclerView
        adapter = NoteAdapter()
        recyclerView.adapter = adapter

        adapter.setList(myNote())
    }

    //something test
    fun myNote(): ArrayList<Note>{
        val notesList = ArrayList<Note>()

        val note1 = Note(1, "Matvey", "MSGFSOGFFOP", Date(), Date())
        notesList.add(note1)
//
//        val note2 = Note(2, "Pasha", "1231231", Date(), Date())
//        notesList.add(note2)
//
//        val note3 = Note(3, "Arsenij", "hgllldfd", Date(), Date())
//        notesList.add(note3)
//
//        val note4 = Note(4, "notes", "ya tupoi", Date(), Date())
//        notesList.add(note4)

        return notesList
    }

    override fun showNotes(notes: List<Note>) {
        TODO("Not yet implemented")
    }


}