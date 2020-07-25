package kaffeekasse
package http

import sttp.tapir._
import sttp.tapir.json.circe._
import io.circe.generic.auto._
import domain.types._

import kaffeekasse.http.json._

object People {

  val peopleListing: Endpoint[Unit, String, List[Person], Nothing] =
    endpoint.get
      .in("people")
      .errorOut(stringBody)
      .out(jsonBody[List[Person]])

}
