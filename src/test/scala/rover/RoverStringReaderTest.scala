package rover

import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.RoverReader.RoverStringReader
import world.location.Location

import scala.util.Success

class RoverStringReaderTest extends FlatSpec {
  "reading a rover" should "return a success of that rover if the string can be parsed into a location and orientation" in {
    assert(RoverStringReader("1 2 N") === Success(Rover(Location(1, 2), Orientation.North)))
  }
  
  it should "return a failure otherwise" in {
    assert(RoverStringReader("1 2 asd").isFailure, "1 2 asd did not result in a failure")
    assert(RoverStringReader("1 2 N W").isFailure, "1 2 N W did not result in a failure")
    assert(RoverStringReader("A 2 N").isFailure, "A 2 N did not result in a failure")
  }
}
