package com.tools

import com.data.{Department, User}

object Validator {
  var errorMsg = ""

  def check_password(s1: String, s2: String): Boolean = {
    var isValid = false

    if(s1 == s2) {
      if(s1.length >= 8) {
        isValid = true
      }
      else {
        errorMsg = "Password Must Be At Least 8 Characters"
      }
    }
    else {
      errorMsg = "Passwords Do Not Match"
    }

    return isValid
  }

  def check_int_validity(numCheck: String): Boolean = {
    var isValid = true

    for(i <- 0 until numCheck.length) {
      if(!numCheck(i).isDigit) {
        isValid = false
      }
    }

    return isValid
  }

  def check_for_nulls(strs: Array[String]): Boolean = {
    var isValid = true

    for(i <- 0 until strs.length) {
      if(strs(i) == "" || strs(i) == null) {
        isValid = false
      }
      else {
        errorMsg = "Please Fill In All Fields"
      }
    }

    return isValid
  }

  def validate_email(email: String): Boolean = {
    var isValid = false

    if(email.contains("@")) {
      isValid = true
    }
    else {
      errorMsg = "Invalid Email Address"
    }

    return isValid
  }

  def check_if_exists(checkingEmail: String, pass: String): Boolean ={
    var isValid = true

    val lookupUser = new User(checkingEmail, pass)
    lookupUser.find_user()

    if(lookupUser.userId != 0) {
      isValid = false
      errorMsg = "User Account Already Exists"
    }

    return isValid
  }

  def check_department(empId: Int): Boolean ={
    var isValid = true

    val deptChecker = new Department()
    val checkList = deptChecker.get_department_supervisors()

    for(i <- 0 until checkList.length) {
      if(checkList(i) == empId) {
        isValid = false
      }
      else {
        errorMsg = "Invalid Supervisor Selection"
      }
    }

    return isValid
  }

  def validate_phone(pNum: String): Boolean = {
    var isValid = false

    val stripDashes = pNum.replace("-","")
    val trimmedNum = stripDashes.forall(_.isDigit)

    if(pNum.length == 12 && trimmedNum && stripDashes.length == 10) {
      isValid = true
    }
    else {
      errorMsg = "Invalid Phone Number"
    }

    return isValid
  }
}
