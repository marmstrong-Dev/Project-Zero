package com.tools

import scala.io.{StdIn, Source}
import  io.circe.Json
import com.data.Employee

object Importer {
  def batch_menu(): Unit ={
    println("\nPlease Enter Path to File")
    val enteredPath = StdIn.readLine()
    val readJson = Source.fromFile(enteredPath).getLines().mkString("\n")
    val parsedJson = Json.fromString(readJson)
    val cur = parsedJson.hcursor

    val firstNames = cur.downField("employee_first_name").withFocus(_.mapString(_.toString)).top
    println(firstNames)
  }

  def read_file(): Unit ={
    /*
        val parsedJ = parse(readJson).getOrElse(Json.Null)

      /*implicit val employeeDecoder = Decoder.forProduct4(
          "employee_first_name",
          "employee_last_name",
          "employee_email_address",
          "employee_phone_number"
        )()*/
        val cur = parsedJ.hcursor

        val decoderList = Decoder[List[Employee]].prepare(
          _.downField("employee_first_name")
            .downField("employee_last_name")
            .downField("employee_email_address")
            .downField("employee_phone_number")
        )
        val res = parser.decode(readJson)(decoderList)
        val fName = cur.downField("employee_first_name").downField("employee_last_name").as[List[String]]

        println(res)*/
  }
}
