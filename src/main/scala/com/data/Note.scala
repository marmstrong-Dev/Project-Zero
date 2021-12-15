package com.data

import com.data.DbCon.{dbUrl, password, username}
import java.sql.{Connection, DriverManager, SQLException}

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

  // Add New Note to DB
  def add_note(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val addNoteQuery = statement.executeUpdate(
      s"""
        INSERT INTO Notes(note_employee, note_text)
        VALUES(${this.noteEmployee}, "${this.noteText}");
        """
      )

      println(s"Note Added to Employee #: ${this.noteEmployee}")
      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def get_notes(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val getNotesQuery = statement.executeQuery(s"SELECT * FROM Notes WHERE note_employee = ${this.noteEmployee} ORDER BY note_created DESC;")

      while (getNotesQuery.next()) {
        println("\n" + getNotesQuery.getTimestamp("note_created"))
        println(getNotesQuery.getString("note_text") + "\n")
      }

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def del_all_notes(): Unit ={
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val delAllQuery = statement.executeUpdate(s"DELETE FROM Notes WHERE note_employee = ${noteEmployee};")

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }
}
