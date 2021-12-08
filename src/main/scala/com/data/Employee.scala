package com.data

import com.data.DbCon.{dbUrl, password, username}
import java.sql.{Connection, DriverManager, SQLException}

// Model Class for Employees
class Employee (
               var employeeId: Int,
               var employeeFirstName: String,
               var employeeLastName: String,
               var employeeEmailAddress: String,
               var employeePhoneNum: String ) {

  def this(employeeFirstName: String, employeeLastName: String) = {
    this(0, employeeFirstName, employeeLastName, "", "")
  }

  def this() = {this(0, "", "", "", "")}

  def add_employee(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val addQuery = statement.executeUpdate(
        s"""
      INSERT INTO Employees(
        employee_first_name,
        employee_last_name,
        employee_email_address,
        employee_phone_number)
      VALUES (
        "${this.employeeFirstName}",
        "${this.employeeLastName}",
        "${this.employeeEmailAddress}",
        "${this.employeePhoneNum}"
      );""")

      println("New Employee Created")
      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def get_employee(lookupId: Int): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val lookupAllQuery = statement.executeQuery(s"SELECT * FROM Employees WHERE employee_id = ${lookupId};")

      while ( lookupAllQuery.next() ) {
        this.employeeId = lookupAllQuery.getInt("employee_id")
        this.employeeFirstName = lookupAllQuery.getString("employee_first_name")
        this.employeeLastName = lookupAllQuery.getString("employee_last_name")
        this.employeeEmailAddress = lookupAllQuery.getString("employee_email_address")
        this.employeePhoneNum = lookupAllQuery.getString("employee_phone_number")
      }

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def get_all_employees(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val lookupBatchQuery = statement.executeQuery("SELECT * FROM Employees;")

      while(lookupBatchQuery.next()) {
        println("\n" + lookupBatchQuery.getString("employee_id") + ".) ")
        println(lookupBatchQuery.getString("employee_first_name") + " " + lookupBatchQuery.getString("employee_last_name"))
        println(lookupBatchQuery.getString("employee_email_address"))
        println(lookupBatchQuery.getString("employee_phone_number"))
      }

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def edit_employee(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val modQuery = statement.executeUpdate(
        s"""
        UPDATE Employees SET
          employee_first_name = "${this.employeeFirstName}",
          employee_last_name = "${this.employeeLastName}",
          employee_email_address = "${this.employeeEmailAddress}",
          employee_phone_number = "${this.employeePhoneNum}"
        WHERE employee_id = ${this.employeeId};
        """)

      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }

  def del_employee(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val delQuery = statement.executeUpdate(s"DELETE FROM Employees WHERE employee_id = ${this.employeeId};")

      println(s"Employee ${this.employeeId } Deleted")
      con.close()
    } catch {
      case e:SQLException => e.printStackTrace()
    }
  }
}
