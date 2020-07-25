package kaffeekasse
package http

import cats.Applicative
import io.circe._
import io.circe.generic.semiauto._
import io.circe.refined._
import io.estatico.newtype._
import io.estatico.newtype.ops._
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf
import sttp.tapir._

import domain.types._

object json extends JsonCodecs {
  implicit def deriveEntityEncoder[F[_]: Applicative, A: Encoder]: EntityEncoder[F, A] = jsonEncoderOf[F, A]
}

private[http] trait JsonCodecs {

  // TODO this is still dark magic to me and I have no idea how this works.
  // ----- Coercible codecs -----
  implicit def coercibleDecoder[A: Coercible[B, *], B: Decoder]: Decoder[A] =
    Decoder[B].map(_.coerce[A])

  implicit def coercibleEncoder[A: Coercible[B, *], B: Encoder]: Encoder[A] =
    Encoder[B].contramap(_.repr.asInstanceOf[B])

  implicit def coercibleKeyDecoder[A: Coercible[B, *], B: KeyDecoder]: KeyDecoder[A] =
    KeyDecoder[B].map(_.coerce[A])

  implicit def coercibleKeyEncoder[A: Coercible[B, *], B: KeyEncoder]: KeyEncoder[A] =
    KeyEncoder[B].contramap[A](_.repr.asInstanceOf[B])

  implicit def coercibleSchema[
    A: Coercible[B, *],
    B: Schema,
  ]: Schema[A] = {
    val bSchema = implicitly[Schema[B]]
    Schema[A](bSchema.schemaType, bSchema.isOptional, bSchema.description, bSchema.format)
  }

  implicit val personDecoder: Decoder[Person] = deriveDecoder[Person]
  implicit val personEncoder: Encoder[Person] = deriveEncoder[Person]
}
