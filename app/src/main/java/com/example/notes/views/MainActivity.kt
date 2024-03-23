package com.example.notes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.models.INoteList
import com.example.notes.models.Note
import com.example.notes.models.NoteModel
import com.example.notes.presenter.IMainPresenter
import com.example.notes.presenter.MainPresenter
import java.util.*
//import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), IMainActivity{

    //РАБОТАЕТ КОГДА МЕНЯЕШЬ ВЕРСИЮ СДК
    lateinit var presenter: IMainPresenter

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, NoteModel())
        initialAdapter()





    }

    private fun initialAdapter() {
        recyclerView = binding.recyclerView
        adapter = NoteAdapter(this)
        recyclerView.adapter = adapter
        //adapter.setList(myNote())

        adapter.setList(myNote2())
    }

    //something test
    fun myNote(): ArrayList<Note>{
        val notesList = ArrayList<Note>()

        val note1 = Note(1, "Matvey", "Krutoi", Date(), Date())
        notesList.add(note1)

        return notesList
    }

    fun myNote2():List<Note>{
        return presenter.simpleTest().getAllNotes()
    }

    override fun showNotes(notes: List<Note>) {
        //adapter.setList(notes)

    }


}