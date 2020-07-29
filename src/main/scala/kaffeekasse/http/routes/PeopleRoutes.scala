package kaffeekasse.http.routes

import sttp.tapir._
import sttp.tapir.server.http4s._
import kaffeekasse.http.endpoints
import kaffeekasse.domain.types.Person
import org.http4s.HttpRoutes
import cats.effect.Sync
import cats.effect.ContextShift
import cats.Applicative
import cats.implicits._
import kaffeekasse.algebras.People

final class PeopleRoutes[F[_]: Sync: ContextShift](val people: People[F])(implicit
  serverOptions: Http4sServerOptions[F]
) {
  private def allPeople(): F[Either[String, List[Person]]] = people.findAll.map(_.asRight)
  val routes: HttpRoutes[F] = endpoints.people.toRoutes(_ => allPeople())
}
