package com.example.notes.views
import android.app.Application
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.models.Note
import com.example.notes.models.NoteModel
import com.example.notes.presenter.IMainPresenter
import com.example.notes.presenter.MainPresenter
import java.util.*

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.notes.db.NoteDatabase
import com.example.notes.db.repository.NoteRepository
import java.time.DayOfWeek


class MainActivity : AppCompatActivity(), IMainActivity{

    //РАБОТАЕТ КОГДА МЕНЯЕШЬ ВЕРСИЮ СДК
    private lateinit var presenter: IMainPresenter

    private lateinit var repository: NoteRepository

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView


   // @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, NoteModel())

        initialDatabase()
        initialAdapter()
        initialSwipeItem()
        initialFAB()
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    private fun initialAdapter() {
        recyclerView = binding.recyclerView
        adapter = NoteAdapter(this, this)
        recyclerView.adapter = adapter

        //adapter.setList(myNote())
        getAllNotes().observe(this
        ) { listNotes ->
            adapter.setList(listNotes)
        }
    }

    private fun initialSwipeItem(){
        val itemTouchHelper = ItemTouchHelper(SwipeItem(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun initialFAB(){
        val fab: View = findViewById(R.id.addNoteFAB)
        fab.setOnClickListener{
            showEditTextDialog()
        }
    }

    fun initialDatabase(){
        val context = this.application
        val daoNote = NoteDatabase.getInstance(context)?.getNoteDao()
        repository = NoteRepository(daoNote!!)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return repository.allNotes
    }

    fun myNote():List<Note>{
        return presenter.simpleTest().getAllNotes()
    }

    override fun showNotes(notes: List<Note>) {
        adapter.setList(notes)
    }

    fun addNotes(note: Note){
        presenter.addNote(note)
    }

    override fun deleteNotes(noteId: Int){
        presenter.deleteNote(noteId)
    }

    override fun editNotes(note: Note){
        presenter.editNote(note)
    }

    override fun showEditTextDialog(noteId: Int?, title: String?, text: String?){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.edit_note_layout, null)
        val editTextTitle = dialogLayout.findViewById<EditText>(R.id.et_editTextTitle)
        val editTextText = dialogLayout.findViewById<EditText>(R.id.et_editTextText)

        builder.setTitle("Write Note")

        if (noteId == null){
            builder.setPositiveButton("OK"){dialog, which ->
                addNotes(Note(
                    presenter.getLastId(),
                    editTextTitle.text.toString(),
                    editTextText.text.toString(),
                    Date().toString(),
                    Date().toString()
                ))
                Log.d("testAddingNotes", "Note [${presenter.getLastId() - 1}] (${editTextTitle.text}, ${editTextText.text}) added")
                }
            }
        else {
            editTextTitle.setText(title)
            editTextText.setText(text)
            builder.setPositiveButton("OK") { dialog, which ->
                editNotes(
                    Note(
                    noteId,
                    editTextTitle.text.toString(),
                    editTextText.text.toString(),
                        Date().toString(),
                        Date().toString()
                        )
                    )
                }
            }

        builder.setNegativeButton("Cancel"){dialog, which ->
            Log.d("Main", "NegativeButtonClicked")
            }
        builder.setView(dialogLayout)
        builder.show()
    }

}
