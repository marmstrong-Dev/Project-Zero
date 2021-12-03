package com.data

class Employee (
                 var employeeFirstName: String,
                 var employeeLastName: String,
                 var employeeEmailAddress: String,
                 var employeePhoneNum: String ) {

  def this(employeeFirstName: String, employeeLastName: String) = {
    this(employeeFirstName, employeeLastName, "", "")
  }

  def this() = {
    this("", "", "", "")
  }

  def add_employee(): Unit = {

  }

  def edit_employee(): Unit = {

  }
}
