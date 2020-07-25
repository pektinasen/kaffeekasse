val Http4sVersion = "0.21.6"
val CirceVersion = "0.13.0"
val Specs2Version = "4.10.0"
val LogbackVersion = "1.2.3"
val CatsVersion = "2.2.0"
val TapirVersion = "0.16.7"
val SttpVersion = "2.1.0"
val CalibanVersion = "0.9.0"
val CirisVersion = "1.1.1"

lazy val root = (project in file("."))
  .settings(
    organization := "ch.gennri",
    name := "kaffeekasse",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "io.estatico" %% "newtype" % "0.4.3",
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-core" % CirceVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "io.circe" %% "circe-refined" % CirceVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-core" % TapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % TapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % TapirVersion,
      "com.github.ghostdogpr" %% "caliban" % CalibanVersion,
      "com.github.ghostdogpr" %% "caliban-http4s" % CalibanVersion,
      "com.github.ghostdogpr" %% "caliban-cats" % CalibanVersion,
      "is.cir" %% "ciris" % CirisVersion,
      "is.cir" %% "ciris-refined" % CirisVersion,
      "is.cir" %% "ciris-squants" % CirisVersion,
      "org.scalameta" %% "munit" % "0.7.10" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.3" % "test",
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "io.chrisdavenport" %% "log4cats-slf4j" % "1.1.1"
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-feature",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)

Global / cancelable := false