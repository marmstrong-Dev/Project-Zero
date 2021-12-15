package com.menus

import com.data.{Department, Employee, Note}
import scala.io.StdIn.readLine
import com.tools.Validator._
import scala.collection.mutable.ArrayBuffer

object EmployeeLookup {
  // Menu For Employee Lookup
  def lookup_menu(): Unit = {
    print("\u001b[H")

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
    readLine()
  }

  // Find Employee By Id
  def find_employee(): Unit ={
    println("\nEnter ID")
    val idLookup = readLine()

    if(check_int_validity(idLookup)) {
      val lookupEmployee = new Employee()
      lookupEmployee.get_employee(idLookup.toInt)

      if(lookupEmployee.employeeId == 0){
        println("Employee Not Found")
        readLine()
        return
      }

      print("\n" + lookupEmployee.employeeId + ".) ")
      println(lookupEmployee.employeeFirstName + " " + lookupEmployee.employeeLastName )
      println(lookupEmployee.employeeEmailAddress)
      println(lookupEmployee.employeePhoneNum )

      val isSupervisor = pull_dept(lookupEmployee.employeeDepartment, lookupEmployee.employeeId)
      pull_notes(lookupEmployee.employeeId)

      println("\nWould You Like to Edit? (y or n)")
      val toEdit = readLine()

      if(toEdit.toUpperCase() == "Y") {
        println("\n1.) Edit Employee Information\n2.) Attach Note")

        if(!isSupervisor) {
          println("3.) Delete Employee")
        }

        val editSelector = readLine()

        if(editSelector == "1") {
          print("\u001b[H")
          make_mods(lookupEmployee, isSupervisor)
        }
        else if(editSelector == "2") {
          attach_note(lookupEmployee.employeeId)
        }
        else if(editSelector == "3" && !isSupervisor) {
          confirm_delete(lookupEmployee)
        }
        else {
          println("Invalid Selection")
          readLine()
        }
      }
      else {
        println("Exiting")
      }
    }
    else {
      println("Invalid ID")
      readLine()
    }
  }

  // Print Information For Employee Department
  def pull_dept(deptId: Int, empId: Int): Boolean = {
    val deptGetter = new Department()

    if(deptId != 0) {
      deptGetter.departmentId = deptId
      deptGetter.get_department_info()
    }

    if(deptGetter.departmentSupervisor == empId) {
      return true
    }
    else {
      return false
    }
  }

  // Pull List Of All Employee Notes
  def pull_notes(id: Int): Unit = {
    val notesList = new Note()
    notesList.noteEmployee = id
    notesList.get_notes()
  }

  // Text To Confirm Deletion of Employee Record
  def confirm_delete(delCandidate: Employee): Unit = {
    println("\n" + delCandidate.employeeId + ".) " + delCandidate.employeeFirstName + " " + delCandidate.employeeLastName)
    println("Are You Sure You Want To Delete This Employee? (y or n)")
    val confirm = readLine()

    if(confirm.toLowerCase() == "y") {
      delCandidate.del_employee()
    }
  }

  // Make Changes to Employee Records
  def make_mods(changeEmp: Employee, sup: Boolean): Unit = {
    println("\nEdit Employee\n")

    val editBuffer = new ArrayBuffer[String]()

    println("Enter First Name")
    editBuffer += readLine()
    println("Enter Last Name")
    editBuffer += readLine()
    println("Enter Email Address")
    editBuffer += readLine()
    println("Enter Phone Number (xxx-xxx-xxxx)")
    editBuffer += readLine()

    val deptList = new Department()
    deptList.get_all_departments()

    println("Enter Department ID")
    editBuffer += readLine()

    println("Is He/She The Supervisor (y or n)?")
    val isSup = readLine()

    val checkList = editBuffer.toArray

    if(check_for_nulls(checkList) && check_int_validity(checkList(4)) && validate_phone(checkList(3)) && validate_email(checkList(2))) {
      val updateDept = new Department()

      if(isSup.toLowerCase() == "y") {
        updateDept.departmentSupervisor = changeEmp.employeeId
      }

      if(checkList(4).toInt != changeEmp.employeeDepartment) {
        updateDept.departmentId = changeEmp.employeeDepartment
      }

      updateDept.edit_supervisor(isSup(0), checkList(4).toInt)

      changeEmp.employeeFirstName = checkList(0)
      changeEmp.employeeLastName = checkList(1)
      changeEmp.employeeEmailAddress = checkList(2)
      changeEmp.employeePhoneNum = checkList(3)
      changeEmp.employeeDepartment = checkList(4).toInt

      changeEmp.edit_employee()
      println("Employee Information Updated")
    }
    else {
      println(errorMsg)
      readLine()
    }
  }

  // Attach Note to Employee
  def attach_note(attachId: Int): Unit = {
    println("\nAdd Note\n")

    println("Note: ")
    val txt = readLine()

    val addedNote = new Note()
    addedNote.noteEmployee = attachId
    addedNote.noteText = txt

    addedNote.add_note()
  }
}
