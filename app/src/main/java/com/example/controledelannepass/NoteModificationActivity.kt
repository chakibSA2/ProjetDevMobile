package com.example.controledelannepass

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class NoteModificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_modification_layout)

        val champTitre = findViewById<TextInputEditText>(R.id.champNoteTitre)
        val champNote = findViewById<TextInputEditText>(R.id.champNoteText)
        val boutonToMain = findViewById<Button>(R.id.backToMainButton)

        //ajouter bouton submit, faire l'intent contient ID et TEXT dans le onCreate du mainActivity

        boutonToMain.setOnClickListener {
            if (id == "") {
                id = "note 1"
            }
            val intent = Intent(this, MainActivity::class.java)
            Log.d(ContentValues.TAG, "ID trace :" + id)
            intent.apply {
                putExtra("ID", id)
                putExtra("TEXT", text)
            }
            startActivity(intent)
        }
    }
}