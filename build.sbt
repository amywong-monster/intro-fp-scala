name := "intro-fp-scala"

version := "0.1"

scalaVersion := "2.13.3"

lazy val zioVersion = "1.0.0-RC21-2"

libraryDependencies ++=  Seq(
  "dev.zio" %% "zio",
  "dev.zio" %% "zio-streams",
).map(_ % zioVersion)

