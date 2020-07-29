package kaffeekasse
package modules

import algebras._
import cats.implicits._
import cats.effect.Sync

object Algebras {

  def make[F[_]: Sync](): F[Algebras[F]] =
    for {
      people <- InMemoryPeople.make[F]()
    } yield new Algebras[F](people)
}

final class Algebras[F[_]] private (
  val people: People[F]
)
