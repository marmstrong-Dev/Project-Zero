import com.data.User

import scala.io._
import com.tools.Authenticator.{login, logout, register}
import com.menus.AddEmployee.add_menu
import com.menus.EmployeeLookup.lookup_menu
import com.menus.EditInfo.edit_menu

object ProjectZero {
  var isLoggedIn = false
  var loggedInUser = new User()

  // Produces Welcome Banner and Project Info
  def welcome_screen(): Unit = {
    print("\u001b[H")
    println(s"${AnsiColor.BOLD}Welcome to HR Hero${AnsiColor.RESET}\n")

    for(i <- 0 to 20) {
      print("*")
    }

    println("\n\nSign In or Create an Account\n1.) Login\n2.) New User\n3.) Exit")
    val selection = StdIn.readLine()

    if(selection == "1") {
      loggedInUser = login()

      if(loggedInUser.userId == 0) {
        println("Invalid Login")
      }
      else {
        isLoggedIn = true
      }
    }
    else if(selection == "2") {
      register()
    }
    else if(selection == "3") {
      logout()
    }
    else {
      println("Invalid Selection")
    }
  }

  // Gets Menu Selection and Routes Accordingly
  def menu_selection(): Unit = {
    print("\u001b[H")
    val menu =
      """
        |1. ) Add Employee or Department
        |2. ) Lookup Employee
        |3. ) Edit My Info
        |4. ) Logout and Exit
        |""".stripMargin

    println(menu)
    println("What would you like to do?")
    val selection = StdIn.readLine()

    val router = selection match {
      case "1" => add_menu()
      case "2" => lookup_menu()
      case "3" => edit_menu(loggedInUser)
      case "4" => logout()
      case _ => menu_selection()
    }

    router
  }

  def main(args: Array[String]): Unit = {
    while(!isLoggedIn) {
      welcome_screen()
    }

    while(isLoggedIn) {
      menu_selection()
    }
  }
}
