package com.data

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}
import com.data.DbCon.{dbUrl, password, username}

import scala.collection.mutable.ArrayBuffer

// Model Class for Departments
class Department (
                 var departmentId: Int,
                 var departmentName: String,
                 var departmentSupervisor: Int
                 ){
  def this() = {this(0, "", 0)}

  // Add New Department to DB
  def add_department(): Unit ={
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val addDeptQuery = statement.executeUpdate(
      s"""
      INSERT INTO Departments(department_name, department_supervisor)
      VALUES("${this.departmentName}", ${this.departmentSupervisor});
      """)

      val getIdQuery = statement.executeQuery(s"SELECT department_id FROM Departments WHERE department_name = '${this.departmentName}';")

      while(getIdQuery.next()) {
        this.departmentId = getIdQuery.getInt("department_id")
      }

      println("New Department Created")
      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  // Get List of All Departments
  def get_all_departments(): Unit ={
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val getAllDeptsQuery = statement.executeQuery(s"SELECT * FROM Departments;")

      while(getAllDeptsQuery.next()) {
        println(getAllDeptsQuery.getInt("department_id") + ".) " + getAllDeptsQuery.getString("department_name"))
      }

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  // Get Department Info For Single Employee
  def get_department_info(): Unit = {
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val getDeptQuery = statement.executeQuery(
        s"""
        SELECT department_supervisor, department_name, employee_first_name, employee_last_name FROM Departments LEFT JOIN Employees
        ON Departments.department_supervisor = Employees.employee_id
        WHERE department_id = ${this.departmentId};
        """)

      while(getDeptQuery.next()) {
        this.departmentSupervisor = getDeptQuery.getInt("department_supervisor")
        this.departmentName = getDeptQuery.getString("department_name")

        var fName = ""
        var lName = ""

        if(getDeptQuery.getString("employee_first_name") != null) {
          fName = getDeptQuery.getString("employee_first_name")
          lName = getDeptQuery.getString("employee_last_name")
        }

        println(
          s"""|${this.departmentName}
          |Supervisor: ${fName} ${lName}
          |""".stripMargin)
      }

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  // Null Supervisor For Dept if Supervisor Changes Dept
  def edit_supervisor(opt: Char, newId: Int): Unit ={
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val nullSupervisorQuery = statement.executeUpdate(s"UPDATE Departments SET department_supervisor = null WHERE department_id = ${this.departmentId};")

      if(opt == 'y' || opt == 'Y') {
        val updateSupervisor = statement.executeUpdate(s"UPDATE Departments SET department_supervisor = ${this.departmentSupervisor} WHERE department_id = ${newId};")
      }

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  def get_department_supervisors(): Array[Int] ={
    val supervisorsList = new ArrayBuffer[Int]()

    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()
      val lookupSupervisorsQuery = statement.executeQuery(s"SELECT department_supervisor FROM Departments;")

      while(lookupSupervisorsQuery.next()) {
        supervisorsList += lookupSupervisorsQuery.getInt("department_supervisor")
      }

      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }

    return supervisorsList.toArray
  }

  // Delete Department From DB
  def del_department(): Unit ={
    try {
      val con: Connection = DriverManager.getConnection(dbUrl, username, password)
      val statement = con.createStatement()

      val delDeptQuery = statement.executeUpdate(s"DELETE FROM Departments WHERE department_id = ${this.departmentId};")

      println(s"Department ID ${this.departmentId} Deleted")
      con.close()
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }
}
