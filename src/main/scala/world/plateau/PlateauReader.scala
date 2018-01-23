package world.plateau

import world.location.LocationReader
import world.location.LocationReader._

import scala.util.{Failure, Success, Try}

trait PlateauReader[T] {
  def apply(plateau: T): Try[Plateau]
}

object PlateauReader {
  
  implicit object PlateauStringReader extends PlateauReader[String] {
    override def apply(plateau: String): Try[Plateau] =
      (Try(plateau.split(" ").toSeq) collect {
        case Seq(x, y) => locationReader(x, y) map Plateau.apply
      }).flatten transform(Success(_), _ => Failure(new RuntimeException("malformed plateau string")))
    
    val locationReader: LocationReader[String] = LocationStringReader
  }
  
}
