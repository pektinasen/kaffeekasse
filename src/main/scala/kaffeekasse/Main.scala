package kaffeekasse

import cats.effect.{ExitCode, IO, IOApp}
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.OpenAPI
import sttp.tapir.openapi.circe.yaml._
import http.endpoints._

object Main extends IOApp {

  def run(args: List[String]) =
    IO {
      val docs: OpenAPI = people.toOpenAPI("Emmys Kaffeekasse", "1.0")
      println(docs.toYaml)
    }.map(_ => ExitCode.Success)
  // KaffeekasseServer.stream[IO].compile.drain.as(ExitCode.Success)
}
