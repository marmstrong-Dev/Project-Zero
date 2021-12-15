package com.menus

import com.data.User
import com.tools.Validator.{validate_email, check_password, errorMsg}
import scala.io.StdIn.readLine

object EditInfo {
  var candidateUser = new User()

  def edit_menu(modUser: User): Unit = {
    print("\u001b[H")
    println(s"User ID: ${modUser.userId}")
    println(s"User Name: ${modUser.userFirstName} ${modUser.userLastName}")
    println(s"User Email Address: ${modUser.userEmailAddress}")

    candidateUser = modUser

    println(
      """
        |What Would You Like To Do?
        |1.) Update Name
        |2.) Update Email Address
        |3.) Reset Password
        |Press Any Other Key To Return to Menu
        |""".stripMargin)

    val option = readLine()

    val updateRouter = option match {
      case "1" => update_name()
      case "2" => update_email()
      case "3" => reset_password()
      case _ => "Invalid Selection"
    }
  }

  def update_name(): Unit ={
    println("\nPlease Enter First Name:")
    val fName = readLine()
    println("\nPlease Enter Last Name:")
    val lName = readLine()

    candidateUser.userFirstName = fName
    candidateUser.userLastName = lName
    candidateUser.edit_user()

    println("\nUser Info Changed")
  }

  def update_email(): Unit ={
    println("\nPlease Enter New Email Address:")
    val eAdd = readLine()

    if(validate_email(eAdd)) {
      candidateUser.userEmailAddress = eAdd
      candidateUser.edit_user()

      println("\nEmail Address Updated")
    }
    else {
      println(errorMsg)
    }
  }

  def reset_password(): Unit = {
    println("Please Enter New Password:")
    val p1 = readLine()
    println("\nRetype New Password to Confirm:")
    val p2 = readLine()

    if(check_password(p1, p2)) {
      candidateUser.userPassword = p1
      candidateUser.edit_user()

      println("\nPassword Reset Successful")
    }
    else {
      println(errorMsg)
    }
  }
}
