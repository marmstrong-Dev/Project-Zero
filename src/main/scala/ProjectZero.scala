import scala.io._
import com.data.DbCon._

object ProjectZero {
  // Produces Welcome Banner and Project Info
  def welcome_screen(): Unit = {
    println(s"${AnsiColor.BOLD}Welcome to HR Hero${AnsiColor.RESET}\n")

    for(i <- 0 to 20)
    {print("*")}
  }

  // Gets Menu Selection and Routes Accordingly
  def menu_selection(): Unit = {
    var menu =
      """
        |1. ) Add Employee
        |2. ) Lookup Employee
        |3. ) Edit My Info
        |4. ) Logout and Exit
        |""".stripMargin

    println(menu)
    println("What would you like to do.")
    val selection = StdIn.readLine()
  }

  def main(args: Array[String]): Unit = {
    open_connection()
  }
}
