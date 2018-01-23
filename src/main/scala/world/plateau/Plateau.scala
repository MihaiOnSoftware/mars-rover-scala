package world.plateau

import rover.Rover
import world.location.Location
import world.plateau.Plateau.Start

case class Plateau(end: Location) {
  def bound(rover: Rover): Rover = {
    rover.copy( location = rover.location.bound(Start, end))
  }
}

object Plateau {
  val Start = Location(0, 0)
}
