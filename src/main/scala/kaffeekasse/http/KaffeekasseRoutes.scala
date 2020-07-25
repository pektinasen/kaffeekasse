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

  case class Message(message: String)
  import sttp.tapir._
  import sttp.tapir.json.circe._
  import io.circe.generic.auto._

  val helloEndpoint: Endpoint[(String), String, Message, Nothing] =
    endpoint.get
      .in("hello" / path[String]("name"))
      .errorOut(stringBody)
      .out(jsonBody[Message])

  def helloWorldRoutes[F[_]: Sync: ContextShift: Timer](
    H: HelloWorld[F]
  )(implicit serverOptions: Http4sServerOptions[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    helloEndpoint.toRoutes(name =>
      for {
        greeting <- H.hello(HelloWorld.Name(name))
      } yield Message(greeting.greeting).asRight
    )
  }
}
