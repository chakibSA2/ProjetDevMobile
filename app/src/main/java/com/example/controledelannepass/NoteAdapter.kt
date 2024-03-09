package com.example.controledelannepass

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//Adapter pour le recycler qui affiche la liste des notes
class NoteAdapter(private  val data : List<Note>, private val context: Context, private val deleteNote : (Int)->Unit): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val noteName: Button = itemView.findViewById(R.id.noteVisualisationButton)
        val deleteNote: ImageView = itemView.findViewById(R.id.deleteNoteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_name_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.noteName.text = item.id
        // Format and set the creation date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
        val dateText = "Created: ${dateFormat.format(item.creationDate)}"
        holder.itemView.findViewById<TextView>(R.id.noteCreationDate).text = dateText

        val itemText = data[position]
        holder.noteName.text = itemText.id

        //Pour pouvoir supprimer la bonne note on utilise la fonction ainsi à travers un bouton
        holder.deleteNote.setOnClickListener {
            deleteNote(position)
        }

        //Affichage d'une note pour la consultation/modification
        holder.noteName.setOnClickListener {
            val intent = Intent(context, NoteModificationActivity::class.java)

            //On passe les informations de la note au travers de l'intent
            intent.apply {
                intent.putExtra("ID", itemText.id)
                intent.putExtra("text", itemText.text)
                intent.putExtra("position", position)
            }
            startActivity(context, intent, null)
        }
    }


}