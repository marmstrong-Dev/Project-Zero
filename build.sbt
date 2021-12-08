name := "ProjectZero"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % "0.14.1")

libraryDependencies += "com.github.t3hnar" %% "scala-bcrypt" % "4.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.25"
