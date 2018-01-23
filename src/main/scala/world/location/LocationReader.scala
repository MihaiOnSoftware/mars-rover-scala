package world.location

import scala.util.Try

trait LocationReader[T] {
  def apply(x: T, y: T): Try[Location]
}

object LocationReader {
  
  object LocationStringReader extends LocationReader[String] {
    def apply(x: String, y: String): Try[Location] = Try(Location(x.toInt, y.toInt))
  }
  
}
