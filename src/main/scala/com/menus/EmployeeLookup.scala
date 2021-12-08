package com.menus

import com.data.Employee
import scala.io.StdIn.readLine
import com.tools.Validator.check_int_validity

object EmployeeLookup {
  def lookup_menu(): Unit = {
    println("1.) Lookup User \n2.) Print All Employees")
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

  def find_employee(): Unit ={
    println("Enter ID")
    val idLookup = readLine()

    if(check_int_validity(idLookup)) {
      val lookupEmployee = new Employee()
      lookupEmployee.get_employee(idLookup.toInt)

      print(lookupEmployee.employeeId + ".) ")
      println(lookupEmployee.employeeFirstName + " " + lookupEmployee.employeeLastName )
      println(lookupEmployee.employeeEmailAddress)
      println(lookupEmployee.employeePhoneNum)

      println("\nWould You Like to Edit? (y or n)")
    }
    else {
      println("Invalid ID")
    }
  }

  def print_list(): Unit = {
    val listGetter = new Employee()
    listGetter.get_all_employees()
  }
}
