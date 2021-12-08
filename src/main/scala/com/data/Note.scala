package com.data

// Model Class for Notes
class Note(
         var noteId: Int,
         var noteEmployee: Int,
         var noteText: String,
         var noteCreated: Any
         ) {
  def this() {
    this(0, 0, "", "")
  }

  def add_note(): Unit = {

  }

  def edit_note(): Unit = {

  }

  def del_note(): Unit ={

  }
}
