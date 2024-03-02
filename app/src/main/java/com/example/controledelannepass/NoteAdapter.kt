package com.example.controledelannepass

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class NoteAdapter(private  val data : List<Note>, private val deleteNote : (Int)->Unit ): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val noteName: Button = itemView.findViewById(R.id.noteVisualisationButton)
        val deleteNote: ImageView = itemView.findViewById(R.id.deleteNoteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_name_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "trace data:" + data)
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemText = data[position]
        holder.noteName.text = itemText.id
        holder.deleteNote.setOnClickListener {
            deleteNote(position)
        }
    }


}