package kaffeekasse
package algebras

import java.util.UUID

import cats._
import cats.effect._
import squants.market._

import domain.types._

trait People[F[_]] {
  def findAll: F[List[Person]]
  def create(email: Email): F[Unit]
}

object InMemoryPeople {

  def make[F[_]: Sync](): F[People[F]] =
    Sync[F].delay {
      new InMemoryPeople[F]()
    }
}

final class InMemoryPeople[F[_]: Applicative] private () extends People[F] {

  val people = scala.collection.mutable.ListBuffer.from(
    List(
      Person(PersonId(UUID.randomUUID()), Email("saskia@emmy-sharing.de"), EUR(0)),
      Person(PersonId(UUID.randomUUID()), Email("antonia@emmy-sharing.de"), EUR(0))
    )
  )
  def findAll: F[List[Person]] = Applicative[F].pure(List.from(people))

  def create(email: Email): F[Unit] = {
    people.append(Person(PersonId(UUID.randomUUID()), email, EUR(0)))
    Applicative[F].pure(())
  }

}
