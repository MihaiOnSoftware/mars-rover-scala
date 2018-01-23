package movement.orientation

import org.scalatest.FlatSpec
import world.location.Location

class MovementsTest extends FlatSpec {
  val startingLocation = Location(0, 0)
  
  "moving up" should "increase the y of the location by 1" in {
    assert(Movements.moveForward(startingLocation) === Location(0, 1))
  }
  
  "moving down" should "decrease the y of the location by 1" in {
    assert(Movements.moveBackward(startingLocation) === Location(0, -1))
  }
  
  "moving right" should "increase the x of the location by 1" in {
    assert(Movements.moveRight(startingLocation) === Location(1, 0))
  }
  
  "moving left" should "decrease the x of the location by 1" in {
    assert(Movements.moveLeft(startingLocation) === Location(-1, 0))
  }
}
