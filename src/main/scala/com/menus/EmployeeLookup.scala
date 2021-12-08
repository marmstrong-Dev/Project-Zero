package com.menus

import com.data.Employee
import scala.io.StdIn.readLine
import com.tools.Validator._
import scala.collection.mutable.ArrayBuffer

object EmployeeLookup {
  // Menu For Employee Lookup
  def lookup_menu(): Unit = {
    println("\n1.) Lookup User \n2.) Print All Employees")
    val lookupSelection = readLine()

    if(lookupSelection == "1") {
      find_employee()
    }
    else if(lookupSelection == "2") {
      print_list()
    }
    else {
      println("Invalid Selection")
    }
  }

  // Print Full List of All Employees
  def print_list(): Unit = {
    val listGetter = new Employee()
    listGetter.get_all_employees()
  }

  // Find Employee By Id
  def find_employee(): Unit ={
    println("\nEnter ID")
    val idLookup = readLine()

    if(check_int_validity(idLookup)) {
      val lookupEmployee = new Employee()
      lookupEmployee.get_employee(idLookup.toInt)

      print("\n" + lookupEmployee.employeeId + ".) ")
      println(lookupEmployee.employeeFirstName + " " + lookupEmployee.employeeLastName )
      println(lookupEmployee.employeeEmailAddress)
      println(lookupEmployee.employeePhoneNum)

      println("\nWould You Like to Edit? (y or n)")
      val toEdit = readLine()

      if(toEdit.toUpperCase() == "Y") {
        println("\n1.) Edit Employee Information\n2.) Attach Note")
        val editSelector = readLine()

        if(editSelector == "1") {
          make_mods(lookupEmployee)
        }
        else if(editSelector == "2") {
          attach_note(lookupEmployee.employeeId)
        }
        else {
          println("Invalid Selection")
        }
      }
      else {
        println("Exiting")
      }
    }
    else {
      println("Invalid ID")
    }
  }

  // Make Changes to Employee Records
  def make_mods(changeEmp: Employee): Unit = {
    println("\nEdit Employee")

    val editBuffer = new ArrayBuffer[String]()

    println("Enter First Name")
    editBuffer += readLine()
    println("Enter Last Name")
    editBuffer += readLine()
    println("Enter Email Address")
    editBuffer += readLine()
    println("Enter Phone Number")
    editBuffer += readLine()

    val checkList = editBuffer.toArray

    if(check_for_nulls(checkList) && validate_phone(checkList(3)) && validate_email(checkList(2))) {
      changeEmp.employeeFirstName = checkList(0)
      changeEmp.employeeLastName = checkList(1)
      changeEmp.employeeEmailAddress = checkList(2)
      changeEmp.employeePhoneNum = checkList(3)

      changeEmp.edit_employee()
      println("Employee Information Updated")
    }
    else {
      println(errorMsg)
    }
  }

  // Attach Note to Employee
  def attach_note(attachId: Int): Unit = {
    println("Add Note")
  }
}
