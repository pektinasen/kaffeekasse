package kaffeekasse

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import http._

object Main extends IOApp {

  def run(args: List[String]) =
    KaffeekasseServer.stream[IO].compile.drain.as(ExitCode.Success)
}
