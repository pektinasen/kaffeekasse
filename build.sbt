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
        // "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
        "org.http4s" %% "http4s-circe" % Http4sVersion,
        "org.http4s" %% "http4s-dsl" % Http4sVersion,
        "io.circe" %% "circe-core" % CirceVersion,
        "io.circe" %% "circe-generic" % CirceVersion,
        // "io.circe" %% "circe-refined" % CirceVersion,
        "com.softwaremill.sttp.tapir" %% "tapir-core" % TapirVersion,
        "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % TapirVersion,
        "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % TapirVersion,
        "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % TapirVersion,
        "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % TapirVersion,
        // "com.github.ghostdogpr" %% "caliban" % CalibanVersion,
        // "com.github.ghostdogpr" %% "caliban-http4s" % CalibanVersion,
        // "com.github.ghostdogpr" %% "caliban-cats" % CalibanVersion,
        // "is.cir" %% "ciris" % CirisVersion,
        // "is.cir" %% "ciris-refined" % CirisVersion,
        // "is.cir" %% "ciris-squants" % CirisVersion,
        // "org.scalameta" %% "munit" % "0.7.10" % Test,
        // "org.scalacheck" %% "scalacheck" % "1.14.3" % "test",
        "ch.qos.logback" % "logback-classic" % LogbackVersion,
        // "io.chrisdavenport" %% "log4cats-slf4j" % "1.1.1"
      ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
  )

object versions {
  val scalaDom = "0.9.3"
  val scalajsReact = "1.1.0"
  val scalaCSS = "0.5.3"
  val log4js = "1.4.10"
  val autowire = "0.2.6"
  val booPickle = "1.2.6"
  val diode = "1.1.2"
  val uTest = "0.4.7"

  val react = "15.6.1"
  val jQuery = "1.11.1"
  val bootstrap = "3.3.6"
  val chartjs = "2.1.3"

  val scalajsScripts = "1.0.0"
}

/** Dependencies only used by the JS project (note the use of %%% instead of %%) */
lazy val scalajsDependencies =
  Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
    "io.suzaku" %%% "diode" % versions.diode,
    "io.suzaku" %%% "diode-react" % versions.diode,
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
    "com.lihaoyi" %%% "utest" % versions.uTest % Test
  )

/** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
lazy val jsDependencies =
  Seq(
    "org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
    "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js",
    "org.webjars" % "chartjs" % versions.chartjs / "Chart.js" minified "Chart.min.js",
    "org.webjars" % "log4javascript" % versions.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
  )

val frontend = (project in file("frontend"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= scalajsDependencies,
    jsDependencies ++= jsDependencies
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes", // Explain type errors in more detail.
  "-feature",
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  // "-Xfatal-warnings",
  "-Ymacro-annotations",
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals", // Warn if a local definition is unused.
  "-Ywarn-unused:params", // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates", // Warn if a private member is unused.
  "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
)

scalacOptions in (Compile, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")

Global / cancelable := false
