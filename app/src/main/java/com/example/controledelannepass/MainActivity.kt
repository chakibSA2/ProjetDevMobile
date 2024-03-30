package com.example.controledelannepass

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //initialisation de l'adapter, on lui donne cet activité comme context
        adapter = NoteAdapter(Notes,this) { position ->
            deleteNote(position)
        }

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        buttonAdd.setOnClickListener {
            //On affiche la consultation/modification de cette note
            val intent = Intent(this, NoteModificationActivity::class.java)
            startActivity(intent)
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mes notes"


    }


    private fun deleteNote(position: Int) {
        Notes.removeAt(position)
        //On appel une fonction du Manager qui va supprimer la Note du cache
        PreferenceManager.saveAfterRemove(this, position)
        adapter.notifyItemRemoved(position)
    }

    override fun onResume() {
        super.onResume()
        Notes.clear()
        //On récupère la liste des notes dans le cache grâce à une méthode centralisé du manager
        Notes.addAll(PreferenceManager.retrieve(this))
        adapter.notifyDataSetChanged()
    }
}