package rover

import movement.orientation.OrientationReader
import movement.orientation.OrientationReader._
import world.location.LocationReader
import world.location.LocationReader.LocationStringReader

import scala.util.{Failure, Success, Try}

trait RoverReader[T] {
  def apply(rover: T): Try[Rover]
}

object RoverReader {
  
  implicit object RoverStringReader extends RoverReader[String] {
    
    val locationReader: LocationReader[String] = LocationStringReader
    val orientationReader: OrientationReader[String] = OrientationStringReader
    
    override def apply(rover: String): Try[Rover] = {
      val strings = Try(rover.split(" ").toSeq)
      
      strings collect { case Seq(x, y, orientation) =>
        (locationReader(x, y), orientationReader(orientation))
      } collect { case (Success(location), Some(orientation)) =>
        Rover(location, orientation)
      } transform(Success(_), _ => Failure(new RuntimeException("malformed rover string")))
    }
  }
  
}
