package movement.instruction

import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.Rover
import world.location.Location

class InstructedRoversTest extends FlatSpec {

  "adding a rover and instructions" should "add the rover and instruction to the instructed rovers" in {
    val firstRover = Rover(Location(0, 0), Orientation.North)
    val current = InstructedRovers(Seq(firstRover), Seq(Instruction.Move))

    val secondRover = firstRover.copy(location = Location(2, 2))
    val expected = InstructedRovers(Seq(firstRover, secondRover), Seq(Instruction.Move, Instruction.TurnRight))
    assert(current + (secondRover, Instruction.TurnRight) === expected)
  }
}
