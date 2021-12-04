package com.data

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

  def this() = {
    this(0, "", "", "", "")
  }

  def add_employee(): Unit = {

  }

  def edit_employee(): Unit = {

  }
}
