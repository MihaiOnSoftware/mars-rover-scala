package rover

import movement.orientation.Orientation
import org.scalatest.FlatSpec
import world.location.Location

class RoverTest extends FlatSpec {
  val testRover = Rover(Location(0, 0), Orientation.North)
  
  "moving the rover" should "move it in the direction defined by the orientation" in {
    assert(testRover.move === testRover.copy(location = Location(0, 1)))
  }
  
  "turning the rover left" should "change it's orientation from North to West" in {
    assert(testRover.turnLeft === testRover.copy(orientation = Orientation.West))
  }
  
  "turning the rover right" should "change it's orientation from North to East" in {
    assert(testRover.turnRight === testRover.copy(orientation = Orientation.East))
  }
}
