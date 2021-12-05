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
}
