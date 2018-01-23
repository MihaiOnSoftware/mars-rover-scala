package movement.instruction

import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.Rover
import world.location.Location
import world.plateau.Plateau

class InstructionTest extends FlatSpec {
  
  val testRover: Rover = Rover(Location(3, 3), Orientation.East)
  val testPlateau: Plateau = Plateau(Location(4, 4))
  
  "the turn left instruction" should "ignore the plateau and other rovers and turn the rover to the left" in {
    assert(Instruction.TurnLeft(null)(null)(testRover) === testRover.turnLeft)
  }
  
  "the turn right instruction" should "ignore the plateau and other rovers and turn the rover to the right" in {
    assert(Instruction.TurnRight(null)(null)(testRover) === testRover.turnRight)
  }
  
  "the move instruction" should "only allow the rover to move if there is room on the plateau" in {
    assert(Instruction.Move(Plateau(Location(3, 4)))(Seq())(testRover) === testRover)
    assert(Instruction.Move(testPlateau)(Seq())(testRover) === testRover.move)
  }
  
  it should "only allow the rover to move if there isn't another rover in the new spot" in {
    assert(Instruction.Move(testPlateau)(Seq(Rover(Location(4, 3), Orientation.North)))(testRover) === testRover)
    assert(Instruction.Move(testPlateau)(Seq(Rover(Location(4, 4), Orientation.North)))(testRover) === testRover.move)
  }
}
