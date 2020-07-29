package kaffeekasse

import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.OpenAPI
import sttp.tapir.openapi.circe.yaml._

import http.endpoints._
import kaffeekasse.http.KaffeekasseServer

object Main extends IOApp {

  def run(args: List[String]) =
    // IO {
    //   val docs: OpenAPI =
    //     List(
    //       people,
    //       addPerson,
    //       increment,
    //       decrement
    //     ).toOpenAPI("Emmys Kaffeekasse", "1.0")
    //   println(docs.toYaml)
    // }.map(_ => ExitCode.Success)
    KaffeekasseServer.stream[IO]
}
