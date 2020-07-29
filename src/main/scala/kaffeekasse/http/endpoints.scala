package kaffeekasse
package http

import java.util.UUID

import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.json.circe._

import domain._
import domain.types._
import http.json._
import sttp.model.StatusCode

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

  val addPerson =
    base.post
      .description("add new Person to the Kaffekass")
      .in("people")
      .in(jsonBody[CreatePerson])
      .out(statusCode(StatusCode.Created).description("successful created"))

  val increment: Endpoint[(UUID), String, Unit, Nothing] =
    base.post
      .description("increments amount for this person by 1")
      .in("people" / path[UUID]("id") / "increment")
      .out(emptyOutput)

  val decrement: Endpoint[(UUID), String, Unit, Nothing] =
    base.post
      .description("decrements the amount for this person by 1")
      .in("people" / path[UUID]("id") / "decrement")
      .out(emptyOutput)

  object admin {
    val privileged = endpoint.in("admin").in(header[String]("X-AUTH-TOKEN"))

    val updatePerson = ???
    val updateAmountForPerson = ???
  }

}
