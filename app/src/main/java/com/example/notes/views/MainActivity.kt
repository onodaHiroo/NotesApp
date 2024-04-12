package com.example.notes.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.db.NoteDatabase
import com.example.notes.db.repository.INoteRepository
import com.example.notes.db.repository.NoteRepository
import com.example.notes.models.Note
import com.example.notes.presenter.IMainPresenter
import com.example.notes.presenter.MainPresenter
import java.util.*


class MainActivity : AppCompatActivity(), IMainActivity{

    //РАБОТАЕТ КОГДА МЕНЯЕШЬ ВЕРСИЮ СДК
    private lateinit var presenter: IMainPresenter

    private lateinit var repository: INoteRepository

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView


   // @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //presenter = MainPresenter(this, NoteModel())
       presenter = MainPresenter(this, NoteRepository(NoteDatabase.getInstance(this.application)?.getNoteDao()!!))

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
            showEditTextDialog(Note(0,"${0}","${0}",Date().toString(),Date().toString()), add = true)
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

//    fun myNote():List<Note>{
//        return presenter.simpleTest().getAllNotes()
//    }

    override fun showNotes(notes: List<Note>) {
        adapter.setList(notes)
    }

    fun addNotes(note: Note){
        presenter.insertNote(note){}
    }

    override fun deleteNotes(note: Note) {
        presenter.deleteNote(note){}
    }

    override fun editNotes(note: Note){
        presenter.editNote(note){}
    }

    override fun showEditTextDialog(note: Note, title: String?, text: String?, add: Boolean){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.edit_note_layout, null)
        val editTextTitle = dialogLayout.findViewById<EditText>(R.id.et_editTextTitle)
        val editTextText = dialogLayout.findViewById<EditText>(R.id.et_editTextText)

        builder.setTitle("Write Note")

        editTextTitle.setText(title)
        editTextText.setText(text)

        if (!add)
            builder.setPositiveButton("OK") { dialog, which ->
                editNotes(
                    Note(
                        id = note.id,
                        title = editTextTitle.text.toString(),
                        text = editTextText.text.toString(),
                        changeDate = Date().toString(),
                        createDate = note.createDate
                    )
                )
            }
        else{
            builder.setPositiveButton("OK") { dialog, which ->
                addNotes(
                    Note(
                        id = 0,
                        title = editTextTitle.text.toString(),
                        text = editTextText.text.toString(),
                        changeDate = Date().toString(),
                        createDate = Date().toString(),
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
