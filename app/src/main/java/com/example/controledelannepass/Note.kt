package com.example.controledelannepass

class Note {
   public lateinit var id :String
   public lateinit var text :String

   //Constructeur pour des notes existantes
    constructor(id:String , text:String){
        this.id = id
        this.text = text
    }

    //Constructeur vide pour la création d'une nouvelle note
    constructor(){
        this.id = ""
        this.text = ""
    }


}