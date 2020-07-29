package kaffeekasse.modules

import cats.implicits._
import cats.effect._
import kaffeekasse.http.routes._
import org.http4s.server.Router
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.HttpApp

object HttpApi {

  def make[F[_]: Sync: ContextShift](
    algebras: Algebras[F],
  ): F[HttpApi[F]] =
    Sync[F].delay(
      new HttpApi[F](
        algebras,
      )
    )
}

final class HttpApi[F[_]: Sync: ContextShift] private (
  algebras: Algebras[F]
) {

  private val peopleRoutes = new PeopleRoutes[F](algebras.people).routes

  private val routes: HttpRoutes[F] = peopleRoutes

  val httpApp: HttpApp[F] = routes.orNotFound
}
