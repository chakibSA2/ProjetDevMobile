package com.example.controledelannepass

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceManager {
    companion object {
        //Sauvegarde l'ajout d'une nouvelle Note
        fun save(context : Context, note: Note) {
            val Notes = ArrayList<Note>()
            Notes.addAll(retrieve(context))
            Notes.add(note)
            val sharedPref = context.getSharedPreferences("Nom", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = gson.toJson(Notes)
            sharedPref.edit().putString("notes", json).apply()
        }

        //Sauvegarde la modification d'une Note
        fun saveAfterModify(context: Context, note: Note, position: Int) {
            val Notes = ArrayList<Note>()
            Notes.addAll(retrieve(context))
            Notes[position] = note
            val sharedPref = context.getSharedPreferences("Nom", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = gson.toJson(Notes)
            sharedPref.edit().putString("notes", json).apply()
        }

        //Sauvegarde la suppression d'une Note
        fun saveAfterRemove(context: Context, position: Int) {
            val Notes = ArrayList<Note>()
            Notes.addAll(retrieve(context))
            Notes.removeAt(position)
            val sharedPref = context.getSharedPreferences("Nom", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = gson.toJson(Notes)
            sharedPref.edit().putString("notes", json).apply()
        }

        //Récupère les Notes sauvegardés
        fun retrieve(context: Context) : ArrayList<Note> {
            val sharedPref = context.getSharedPreferences("Nom", Context.MODE_PRIVATE)
            val json = sharedPref.getString("notes", "")
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Note>>() {}.type
            return gson.fromJson(json, type) ?: ArrayList()
        }
    }
}