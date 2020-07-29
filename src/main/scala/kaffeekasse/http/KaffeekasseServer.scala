package kaffeekasse
package http

import scala.concurrent.ExecutionContext.global

import cats.implicits._
import cats.effect.ConcurrentEffect
import cats.effect.ContextShift
import cats.effect.Timer
import org.http4s.server.blaze.BlazeServerBuilder

import kaffeekasse.modules.HttpApi
import kaffeekasse.modules.Algebras
import cats.effect.ExitCode

object KaffeekasseServer {

  def stream[F[_]: ConcurrentEffect: Timer: ContextShift] =
    for {
      algebras <- Algebras.make[F]()
      httpApi  <- HttpApi.make(algebras)
      _ <-
        BlazeServerBuilder[F](global)
          .bindHttp(8080, "0.0.0.0")
          .withHttpApp(httpApi.httpApp)
          .serve
          .compile
          .drain
    } yield ExitCode.Success

}
