package com.data

class User (
             var userFirstName: String,
             var userLastName: String,
             var userEmailAddress: String,
             var userPassword: String ) {

  // Auxiliary Constructor Used For Login
  def this(userEmailAddress: String, userPassword: String) = {
    this("", "", userEmailAddress, userPassword)
  }

  def add_user(): Unit = {

  }

  def edit_user(): Unit = {

  }
}
