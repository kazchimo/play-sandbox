import slick.codegen.SourceCodeGenerator

name := """play-sandbox"""
organization := "com.example"

version := "1.0-SNAPSHOT"

// 環境変数の設定
envFileName in ThisBuild := ".env"

lazy val codegen = taskKey[Unit]("generate slick table code")
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(codegen := {
    SourceCodeGenerator.main(
      Array(
        "slick.jdbc.PostgresProfile",
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost:5432/play_sandbox",
        "app",
        "infrastructure.tables",
        "postgres",
        sys.env("DbPass"),
        "true",
        "slick.codegen.SourceCodeGenerator",
        "true"
      )
    )
  })

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(ehcache, ws, specs2 % Test, guice, evolutions)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.6"
libraryDependencies += "io.swagger" %% "swagger-play2" % "1.7.1"

// slick系の依存
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "com.typesafe.play" %% "play-slick" % "4.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2",
  "org.postgresql" % "postgresql" % "42.1.4",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2"
)
