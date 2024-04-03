package com.example.notes.views
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.models.Note
import com.example.notes.models.NoteModel
import com.example.notes.presenter.IMainPresenter
import com.example.notes.presenter.MainPresenter
import java.util.*

import androidx.recyclerview.widget.ItemTouchHelper



class MainActivity : AppCompatActivity(), IMainActivity{

    //РАБОТАЕТ КОГДА МЕНЯЕШЬ ВЕРСИЮ СДК
    private lateinit var presenter: IMainPresenter

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, NoteModel())

        initialAdapter()
        initialSwipeItem()
        showEditTextDialog()

    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    private fun initialAdapter() {
        recyclerView = binding.recyclerView
        adapter = NoteAdapter(this)
        recyclerView.adapter = adapter

        adapter.setList(myNote())

    }

    private fun initialSwipeItem(){
        val itemTouchHelper = ItemTouchHelper(SwipeItem(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }



    fun myNote():List<Note>{
        return presenter.simpleTest().getAllNotes()
    }

    override fun showNotes(notes: List<Note>) {
        adapter.setList(notes)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun showEditTextDialog(){
        val fab: View = findViewById(R.id.addNoteFAB)
        fab.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_note_layout, null)
            val editTextTitle = dialogLayout.findViewById<EditText>(R.id.et_editTextTitle)
            val editTextText = dialogLayout.findViewById<EditText>(R.id.et_editTextText)

            builder.setTitle("Write Some")
            builder.setPositiveButton("OK"){dialog, which ->
                presenter.addNote(
                    Note(
                        presenter.getLastId(),
                        editTextTitle.text.toString(),
                        editTextText.text.toString(),
                        Date(),
                        Date()
                    )
                )
                Log.d("testAddingNotes", "Note [${presenter.getLastId() - 1}] (${editTextTitle.text}, ${editTextText.text}) added")
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                Log.d("Main", "NegativeButtonClicked")
            }
            builder.setView(dialogLayout)
            builder.show()
        }
    }
}
