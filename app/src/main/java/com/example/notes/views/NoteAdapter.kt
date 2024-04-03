package com.example.notes.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R

import com.example.notes.models.Note
import java.util.Collections.emptyList

class NoteAdapter(private val context: Context, private val activity: IMainActivity): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view)
    private var noteList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return NoteViewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = noteList[position].title
        holder.itemView.findViewById<TextView>(R.id.text).text = noteList[position].text
        holder.itemView.findViewById<TextView>(R.id.create_date).text = noteList[position].createDate.toString()
        holder.itemView.findViewById<TextView>(R.id.change_date).text = noteList[position].changeDate.toString()
        holder.itemView.setOnClickListener{
            Toast.makeText(context, "${position}", Toast.LENGTH_SHORT).show()
            changeNote(position, noteList[position].title, noteList[position].text)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:  List<Note>){
        noteList = list
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        activity.deleteNotes(position)
    }

    fun changeNote(position: Int, title: String, text: String){
        activity.showEditTextDialog(position, title, text)
    }
}