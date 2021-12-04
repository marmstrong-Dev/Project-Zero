package com.tools

import scala.io.StdIn._
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.data.User

// Used to Handle Authentication and Authorization of Users
object Authenticator {
  def register(): Unit = {
    var uInfo = new Array[String](5)

    println("Please Enter First Name:")
    uInfo(0) = readLine()
    println("Please Enter Last Name:")
    uInfo(0) = readLine()
    println("Please Enter Email Address:")
    uInfo(0) = readLine()
    println("Please Create a Password:")
    uInfo(0) = readLine()
    println("Please Confirm Password:")
    uInfo(0) = readLine()

    var candidateUser = new User(0, uInfo(0), uInfo(1), uInfo(2), uInfo(3))
  }

  def login(): Unit = {
    println("Please Log In")
    var userName = readLine()
    var userPass = readLine()

    var loginCandidate = new User(userName, userPass)
  }

  def logout(): Unit = {
    println("Goodbye")
    System.exit(0)
  }

  def auth_switch(): Unit = {

  }
}
