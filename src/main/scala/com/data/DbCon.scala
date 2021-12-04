package com.data

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}

object DbCon {
  val dbUrl = "jdbc:mysql://localhost:3306/ProjectZero"
  val username = "proj_zero"
  val password = ""

  def open_connection(): Unit = {
    try {
      var con: Connection = DriverManager.getConnection(dbUrl, username, password)

      val statement = con.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM Users;")

      while ( resultSet.next() ) {
        val userId = resultSet.getString("user_id")
        val userFirst = resultSet.getString("user_first_name")
        val userLast = resultSet.getString("user_last_name")
        val userEmail = resultSet.getString("user_email_address")
        val userPass = resultSet.getString("user_password")

        println("user = " + userEmail + ", " + userId)
      }

      con.close()
    } catch {
      case e => e.printStackTrace()
    }
  }

  def close_connection(): Unit = {

  }
}
