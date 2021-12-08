package com.menus

import scala.io.StdIn._
import com.tools.Validator._
import com.tools.Importer._
import com.data.Employee

// Menu Object For Adding Employees
object AddEmployee {
  // Construct Menu and Routing
  def add_menu(): Unit = {
    println("\nSingle(1) or Batch(2)?")

    val selection = readLine()

    if(selection == "1") {
      add_single()
    }
    else if(selection == "2") {
      add_batch()
    }
    else {
      println("Invalid Selection")
    }
  }

  // Add CSV or JSON Batch of Employees
  def add_batch(): Unit = {

  }

  // Add One Individual Employee
  def add_single(): Unit = {
    println("\nAdd New Employee\n")

    val candidateEmployee = new Employee()

    println("Enter Employee First Name")
    candidateEmployee.employeeFirstName = readLine()
    println("Enter Employee First Name")
    candidateEmployee.employeeLastName = readLine()
    println("Enter Employee Email Address")
    candidateEmployee.employeeEmailAddress = readLine()
    println("Enter Employee Phone Number (xxx-xxx-xxxx)")
    candidateEmployee.employeePhoneNum = readLine()

    var checkList = Array[String](candidateEmployee.employeeFirstName, candidateEmployee.employeeLastName)
    if(check_for_nulls(checkList) && validate_email(candidateEmployee.employeeEmailAddress) && validate_phone(candidateEmployee.employeePhoneNum)) {
      candidateEmployee.add_employee()
    }
    else {
      println(errorMsg)
    }
  }
}
