package com.menus

import scala.io.StdIn._
import com.tools.Validator._
import com.data.{Department, Employee}

// Menu Object For Adding Employees
object AddEmployee {
  // Construct Menu and Routing
  def add_menu(): Unit = {
    print("\u001b[H")
    println("\nEmployee(1) or Department(2)?")

    val selection = readLine()

    if(selection == "1") {
      add_single()
    }
    else if(selection == "2") {
      add_department()
    }
    else {
      println("Invalid Selection")
    }
  }

  // Add Department And Supervisor
  def add_department(): Unit = {
    println("\nAdd New Department\n")

    val candidateDepartment = new Department()

    println("Enter Department Name")
    candidateDepartment.departmentName = readLine()

    val lookupEmployee = new Employee()
    lookupEmployee.get_all_employees()

    println("\nEnter ID For Supervisor")
    try {
      candidateDepartment.departmentSupervisor = readInt()
    }
    catch {
      case e => println("Error")
    }

    if(check_department(candidateDepartment.departmentSupervisor)) {
      candidateDepartment.add_department()

      lookupEmployee.get_employee(candidateDepartment.departmentSupervisor)
      lookupEmployee.employeeDepartment = candidateDepartment.departmentId
      lookupEmployee.edit_employee()
    }
    else {
      println(errorMsg)
    }
    readLine()
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

    print("\n")
    val deptList = new Department()
    deptList.get_all_departments()

    println("Enter Employee Department ID")
    candidateEmployee.employeeDepartment = readInt()

    var checkList = Array[String](candidateEmployee.employeeFirstName, candidateEmployee.employeeLastName)
    if(check_for_nulls(checkList) && validate_email(candidateEmployee.employeeEmailAddress) && validate_phone(candidateEmployee.employeePhoneNum)) {
      candidateEmployee.add_employee()
    }
    else {
      println(errorMsg)
    }
    readLine()
  }
}
