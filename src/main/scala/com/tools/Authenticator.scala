package com.tools

import scala.io.StdIn._
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.tools.Validator._
import com.data.User

// Used to Handle Authentication and Authorization of Users
object Authenticator {
  // Create New User
  def register(): Unit = {
    var uInfo = new Array[String](5)

    println("Please Enter First Name:")
    uInfo(0) = readLine()
    println("Please Enter Last Name:")
    uInfo(1) = readLine()
    println("Please Enter Email Address:")
    uInfo(2) = readLine()
    println("Please Create a Password:")
    uInfo(3) = readLine()
    println("Please Confirm Password:")
    uInfo(4) = readLine()

    if(check_password(uInfo(3), uInfo(4)) && check_for_nulls(uInfo) && validate_email(uInfo(2))) {
      var candidateUser = new User(0, uInfo(0), uInfo(1), uInfo(2), uInfo(3))

      candidateUser.add_user()
    }
    else {
      println(Validator.errorMsg)
    }
  }

  // Authenticate User
  def login(): User = {
    println("\nPlease Log In\n")

    println("Enter Email Address")
    val userName = readLine()
    println("Enter Password")
    val userPass = readLine()

    var loginCandidate = new User(userName, userPass)
    loginCandidate.lookup_user()

    return loginCandidate
  }

  def logout(): Unit = {
    println("Goodbye")
    System.exit(0)
  }

  def auth_switch(): Unit = {

  }
}
