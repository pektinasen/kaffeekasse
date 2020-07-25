package kaffeekasse
package http

import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.json.circe._

import domain._
import domain.types._
import http.json._

object endpoints {

  private[endpoints] val base = endpoint.errorOut(stringBody)

  val helloEndpoint: Endpoint[(String), String, Message, Nothing] =
    base.get
      .in("hello" / path[String]("name"))
      .out(jsonBody[Message])

  val people: Endpoint[Unit, String, List[Person], Nothing] =
    base.get
      .description("Gets all people")
      .in("people")
      .out(jsonBody[List[Person]])

  val increment: Endpoint[Unit, String, Unit, Nothing] =
    base.post
      .in("people" / "increment")
      .out(emptyOutput)
}
