package kaffeekasse
package domain

import io.estatico.newtype.macros._

object types {

  @newtype case class PersonName(value: String)

  case class Person(name: PersonName)
}
