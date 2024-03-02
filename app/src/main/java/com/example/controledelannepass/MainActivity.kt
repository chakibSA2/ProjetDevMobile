package com.example.controledelannepass

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // récuperation des notes existantes pour les afficher dans le recycler
    private val Notes = mutableListOf<Note>()
    //On instancie l'adapter en tant que variable de l'activité pour l'utiliser dans les fonctions
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //on récupére les élements déclarer dans le layout
        val buttonAdd = findViewById<FloatingActionButton>(R.id.floatingActionButtonAddNote)
        val recycler = findViewById<RecyclerView>(R.id.recyclerMainActivity)

        adapter = NoteAdapter(Notes) { position ->
            deleteNote(position)
        }

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)


        buttonAdd.setOnClickListener {
            //On affiche la consultation/modification de cette note
            val intent = Intent(this, NoteModificationActivity::class.java)

            startActivity(intent)
            val id = intent.getStringExtra("ID")
            val text = intent.getStringExtra("TEXT")

            Log.d(TAG, "ID trace :" + id)

            if (id != null && text != null) {
                //On créer une note pour pour pouvoir l'ajouter à la liste de notes
                var note = Note(id, text)
                addNote(note)
            }

        }

    }

    private fun deleteNote (position : Int) {
        Notes.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun addNote(note: Note) {
        Notes.add(note)
        adapter.notifyDataSetChanged()//(Notes.size - 1)
    }
}