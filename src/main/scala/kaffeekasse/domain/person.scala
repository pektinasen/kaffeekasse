package kaffeekasse
package domain

import io.estatico.newtype.macros._
import java.util.UUID
import squants.market.Money

object types {

  @newtype case class Email(value: String)
  @newtype case class PersonId(value: UUID)

  case class Person(id: PersonId, email: Email, amount: Money)
}
