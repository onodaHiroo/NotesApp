package com.example.notes.views

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeItem(dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    private lateinit var mNoteAdapter: NoteAdapter

    constructor(adapter: NoteAdapter) : this(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        mNoteAdapter = adapter
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
       val position = viewHolder.adapterPosition
        //val currentNote = viewHolder.itemView
        mNoteAdapter.deleteNote(position)
    }
}