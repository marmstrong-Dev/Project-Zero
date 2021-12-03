import scala.io._

object ProjectZero {
  def welcome_screen(): Unit = {
    println(s"${AnsiColor.BOLD}Welcome to HR Hero${AnsiColor.RESET}\n")

    for(i <- 0 to 20)
    {print("*")}

    println("\n Please Log In")
  }

  def menu_selection(): Unit = {
    
  }

  def main(args: Array[String]): Unit = {
    welcome_screen()
  }
}
