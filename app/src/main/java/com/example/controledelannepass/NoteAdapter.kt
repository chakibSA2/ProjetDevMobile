package com.example.controledelannepass

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

//Adapter pour le recycler qui affiche la liste des notes
class NoteAdapter(
    private val data: MutableList<Note>,
    //private var dataFiltered: MutableList<Note> = data,
    private val context: Context,
    private val deleteNote: (Int) -> Unit,

) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteName: Button = itemView.findViewById(R.id.noteVisualisationButton)
        val deleteNote: ImageView = itemView.findViewById(R.id.deleteNoteButton)

        // L'icône d'épinglage à ajouter dans votre layout
        val pinNote: ImageView = itemView.findViewById(R.id.pinNoteButton)

        // Optimisation pour éviter de multiples appels findViewById
        val noteCreationDate: TextView = itemView.findViewById(R.id.noteCreationDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_name_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = data[position]
        holder.noteName.text = note.id
        holder.noteCreationDate.text = "Created: ${
            SimpleDateFormat(
                "dd/MM/yyyy hh:mm",
                Locale.getDefault()
            ).format(note.creationDate)
        }"

        // Mettre à jour l'icône d'épinglage en fonction de l'état de la note
        holder.pinNote.setImageResource(if (note.isPinned) R.drawable.ic_pinned else R.drawable.ic_unpinned)

        // Gérer le clic sur l'icône d'épinglage
        // Dans la fonction onClickListener pour l'icône d'épinglage
        holder.pinNote.setOnClickListener {
            note.isPinned = !note.isPinned
            PreferenceManager.saveAfterPin(context, note, position)
            sortNotes()
            notifyDataSetChanged()
        }

        // Gérer la suppression de la note
        holder.deleteNote.setOnClickListener {
            deleteNote(position)
        }

        // Gérer le clic pour la visualisation/modification de la note
        holder.noteName.setOnClickListener {
            val intent = Intent(context, NoteModificationActivity::class.java).apply {
                putExtra("ID", note.id)
                putExtra("text", note.text)
                putExtra("position", position)
            }
            startActivity(context, intent, null)
        }
    }

    // Implémentation de la fonction de filtrage pour la fonctionalité de recherche des notes
    //par nom
    /*override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                dataFiltered = if (charSearch.isEmpty()) {
                    data
                } else {
                    val resultList = ArrayList<Note>()
                    for (row in data) {
                        if (row.id.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFiltered = results?.values as MutableList<Note>
                notifyDataSetChanged()
            }
        }
    }
*/
    // Une fonction pour trier les notes avec les épinglées en haut
    private fun sortNotes() {
        data.sortWith(compareByDescending<Note> { it.isPinned }.thenBy { it.creationDate })
    }
}