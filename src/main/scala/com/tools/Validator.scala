package com.tools

object Validator {
  def check_for_nulls(strs: List[String]): Boolean = {
    var isValid = true

    for(i <- 0 until strs.length) {
      if(strs(i) == "" || strs(i) == null) {
        isValid = false
      }
    }

    return isValid
  }

  def validate_email(email: String): Boolean = {
    var isValid = true

    if(!email.contains("@")) {
      isValid = false
    }

    return isValid
  }
}
