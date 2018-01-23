package rover

import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.RoverWriter.RoverStringWriter
import world.location.Location

class RoverStringWriterTest extends FlatSpec {
  "writing a rover" should "output it's location and orientation as a string" in {
    val testRover = Rover(Location(1, 5), Orientation.West)
    assert(RoverStringWriter.out(testRover) === "1 5 W")
  }
}
