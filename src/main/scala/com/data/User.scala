package com.data

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}
import com.data.DbCon.{dbUrl, username, password}

// Model Class for Users
class User (
           var userId: Int,
           var userFirstName: String,
           var userLastName: String,
           var userEmailAddress: String,
           var userPassword: String ) {

  // Auxiliary Constructor Used For Login
  def this(userEmailAddress: String, userPassword: String) = {
    this(0, "", "", userEmailAddress, userPassword)
  }

  def this() = {this(0, "", "", "", "")}

  def lookup_user(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val lookupQuery = statement.executeQuery(
        s"""
        SELECT * FROM Users
        WHERE user_email_address = "${this.userEmailAddress}";
        """)

      val enteredPassword = this.userPassword

      while ( lookupQuery.next() ) {
        this.userId = lookupQuery.getInt("user_id")
        this.userFirstName = lookupQuery.getString("user_first_name")
        this.userLastName = lookupQuery.getString("user_last_name")
        this.userEmailAddress = lookupQuery.getString("user_email_address")
        this.userPassword = lookupQuery.getString("user_password")
      }

      if(enteredPassword != this.userPassword) {
        this.userId = 0
      }

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }


  }

  def add_user(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val addQuery = statement.executeUpdate(s"""
        INSERT INTO Users (
          user_first_name,
          user_last_name,
          user_email_address,
          user_password )
        VALUES (
          "${this.userFirstName}",
          "${this.userLastName}",
          "${this.userEmailAddress}",
          "${this.userPassword}"
        );"""
      )

      println("New User Created")
      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def edit_user(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val lookupQuery = statement.executeUpdate(
        s"""
        UPDATE Users SET
          user_first_name = "${this.userFirstName}",
          user_last_name = "${this.userLastName}",
          user_email_address = "${this.userEmailAddress}",
          user_password = "${this.userPassword}"
        WHERE user_id = ${this.userId};
        """)

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  def del_user(): Unit = {

  }
}
