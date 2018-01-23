package rover

trait RoverWriter[T] {
  def out(rover: Rover): T
}

object RoverWriter {
  
  implicit object RoverStringWriter extends RoverWriter[String] {
    override def out(rover: Rover): String = s"${rover.location.x} ${rover.location.y} ${rover.orientation.code}"
  }
  
}
