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
}
