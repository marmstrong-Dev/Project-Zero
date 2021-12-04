package com.data

// Model Class for Users
class User (
           var userId: Int,
           var userFirstName: String,
           var userLastName: String,
           var userEmailAddress: String,
           var userPassword: String ) {

  // Auxiliary Constructor Used For Login
  def this(userEmailAddress: String, userPassword: String) = {
    this(0, "", "", userEmailAddress, userPassword)
  }

  def add_user(): Unit = {

  }

  def edit_user(): Unit = {

  }
}
