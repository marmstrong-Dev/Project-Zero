package com.tools

object Validator {
  var errorMsg = ""

  def check_for_match(s1: String, s2: String): Boolean = {
    var isValid = false

    if(s1 == s2) {
      isValid = true
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
        errorMsg = "Please Fill All Fields"
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
