ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "scalasample"
  )

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.12"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"

libraryDependencies += "dev.zio" %% "zio" % "2.0.21"

libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.21"
