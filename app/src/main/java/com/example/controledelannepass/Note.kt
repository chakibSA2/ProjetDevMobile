package com.example.controledelannepass

import java.util.Date

//La classe est vide car il n'y a pas de traitements
//il s'agit d'un modéle de donnée pour une note
class Note
    (
    var id: String,
    var text: String,
    var creationDate: Date,
    var isPinned: Boolean,
    var isImportant: Boolean = false
) {

}
