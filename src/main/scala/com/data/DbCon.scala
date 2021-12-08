package com.data

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}
import com.data.{Employee, Note, User}

// Database Configuration
object DbCon {
  val dbUrl = "jdbc:mysql://localhost:3306/ProjectZero"
  val username = "proj_zero"
  val password = "Nevada123#"

  // Open New MySQL Connection
  def open_connection(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)

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
      case e:SQLException => e.printStackTrace()
    }
  }

  // Find a Single DB Entry
  def lookup_single(singleQuery: String, objectName: String): Any = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val singleLookupStatement = con.createStatement()
      val singleLookupResults = singleLookupStatement.executeQuery(singleQuery)

      singleLookupResults.next()

      var builder = objectName match {
        case "Employee" => new Employee(
          singleLookupResults.getString("employee_id").toInt,
          singleLookupResults.getString("employee_first_name"),
          singleLookupResults.getString("employee_last_name"),
          singleLookupResults.getString("employee_email_address"),
          singleLookupResults.getString("employee_phone_number")
        )
        case "User" => new User(
          singleLookupResults.getString("user_id").toInt,
          singleLookupResults.getString("user_first_name"),
          singleLookupResults.getString("user_last_name"),
          singleLookupResults.getString("user_email_address"),
          singleLookupResults.getString("user_password")
        )
        case "Note" => new Note(
          singleLookupResults.getString("note_id").toInt,
          singleLookupResults.getString("note_employee").toInt,
          singleLookupResults.getString("note_text"),
          singleLookupResults.getString("note_created")
        )
        case _ => "Invalid Identifier"
      }

      con.close()

      return builder
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def lookup_batch(): Unit = {

  }

  def add_batch(): Unit = {

  }

  def add_single(tableName: String, dataSet: Array[String]): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val addSingleQuery = statement.executeUpdate(s"INSERT INTO ${tableName} VALUES")

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  // Find DB Object Type
  def get_object(oType: String): Any = {
    val identifier = oType match {
      case "Employee" => new Employee()
      case "User" => new User()
      case "Note" => new Note()
      case _ => "Invalid Identifier"
    }

    return identifier
  }
}
