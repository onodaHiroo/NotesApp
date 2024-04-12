package com.example.notes.presenter

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.db.repository.INoteRepository
import com.example.notes.models.INoteList
import com.example.notes.models.Note
import com.example.notes.views.IMainActivity
import com.example.notes.views.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryPresenter(private var view: IMainActivity?, private val repository: INoteRepository):IMainPresenter, ViewModel(){

    lateinit var bufferModel: INoteList

    override fun loadNotes() {
        repository.allNotes.observe(view as MainActivity, Observer { notes ->
                view?.showNotes(notes)
        })
    }

    override fun insertNote(note: Note, onSuccess:() -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertNote(note){
                onSuccess()
            }
        }
    }

    override fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(note){
                onSuccess()
            }
        }
    }

    override fun editNote(note: Note, onSuccess:() -> Unit) {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note){
                onSuccess()
            }
        }
    }



    override fun getNoteById(id: Int) {

    }

    override fun simpleTest(): INoteList {
        return bufferModel.simpleTest()
    }

    override fun detach() { view = null }


    //delete in future
    override fun addNote(note: Note) {

    }

    override fun deleteNote(noteId: Int) {

    }

    override fun editNote(note: Note) {
    }


    override fun getLastId(): Int {
        return 0
    }
}