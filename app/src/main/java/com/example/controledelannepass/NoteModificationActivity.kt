package com.example.controledelannepass


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Date

class NoteModificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_modification_layout)

        //Récupération des éléments nécéssaire au traitement et à l'affichage
        val champTitre = findViewById<TextInputEditText>(R.id.champNoteTitre)
        val champNote = findViewById<TextInputEditText>(R.id.champNoteText)
        val boutonToMain = findViewById<Button>(R.id.backToMainButton)

        //Initialisation de ces variables quand on modifie une note au lieu d'en créer une nouvelle
        val idExterne = intent.getStringExtra("ID")
        val textExterne = intent.getStringExtra("text")
        val position = intent.getIntExtra("position", -1)

        //Si on modifie une note on initialise les champs avec les valeurs de la note
        if (idExterne != null && textExterne != null) {
            champTitre.setText(idExterne)
            champNote.setText(textExterne)
        }

        //Retour à l'activité principal qui sauvegarde la note en cours
        boutonToMain.setOnClickListener {
            var id = champTitre.text.toString()
            var text = champNote.text.toString()

            //Si l'on n'a pas donné de titre à la note, le titre est note 1 par défaut
            if (id == "") {
                id = "note 1"
            }

            val note = Note(id, text, Date())

            //Si position = -1 c'est qu'on ajoute une nouvelle Note, le manager a donc des fonctions de sauvegarde
            //différentes pour les deux cas. Mais la sauveguarde ce fait directement dans le cache.
            if (position != -1) {
                PreferenceManager.saveAfterModify(this, note, position)
            }
            else {
                PreferenceManager.save(this, note)
            }

            //L'activité se fini et on retourne à l'activité principale.
            finish()
        }
    }
}