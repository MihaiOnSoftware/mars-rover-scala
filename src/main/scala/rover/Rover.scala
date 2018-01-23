package rover

import movement.orientation.Orientation
import world.location.Location

case class Rover(location: Location, orientation: Orientation) {
  def move: Rover = copy(location = orientation move location)
  def turnRight: Rover = copy(orientation = Orientation.next(orientation))
  def turnLeft: Rover = copy(orientation = Orientation.previous(orientation))
  def intersect(other: Rover): Boolean = location == other.location
}
