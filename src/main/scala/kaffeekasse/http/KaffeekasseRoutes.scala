package kaffeekasse

package http

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

import sttp.tapir.server.http4s._
import programs._
import cats.effect.ContextShift

object KaffeekasseRoutes {

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }


  import domain._
  import endpoints._

  private[this] def hello[F[_]: Sync](name: String, H: HelloWorld[F]): F[Either[String, Message]] =
    for {
      greeting <- H.hello(HelloWorld.Name(name))
    } yield Message(greeting.greeting).asRight

  def helloWorldRoutes[F[_]: Sync: ContextShift](
    H: HelloWorld[F],
    serverOptions: Http4sServerOptions[F]
  ): HttpRoutes[F] = {
    helloEndpoint.toRoutes(x => hello(x, H))
  }
}
