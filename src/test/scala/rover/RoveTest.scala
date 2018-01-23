package rover

import movement.instruction.Instruction
import movement.instruction.Instruction.Instruction
import movement.orientation.Orientation
import org.scalatest.FlatSpec
import world.location.Location
import world.plateau.Plateau

class RoveTest extends FlatSpec {
  
  val testInstruction1: Instruction = Instruction.TurnLeft
  val testInstruction2: Instruction = Instruction.Move
  val testInstructions = Seq(testInstruction1, testInstruction2)
  
  val testRover1: Rover = Rover(Location(0, 0), Orientation.North)
  val testRover2: Rover = Rover(Location(3, 3), Orientation.East)
  val testPlateau: Plateau = Plateau(Location(3, 4))
  
  
  "Running the rovers" should "run the instructions of each rover on itself" in {
    val testRovers = Seq(testRover1, testRover2)
    assert(new Rove(testPlateau).using(testRovers zip testInstructions) === Seq(
      testInstruction1(testPlateau)(Seq())(testRover1),
      testInstruction2(testPlateau)(Seq())(testRover2)))
  }
  
  val testRover3: Rover = Rover(Location(3, 2), Orientation.North)
  it should "pass the other rovers to the instructions" in {
    val testRovers = Seq(testRover2, testRover3)
    assert(new Rove(testPlateau).using(testRovers zip testInstructions) !== Seq(
      testInstruction1(testPlateau)(Seq())(testRover2),
      testInstruction2(testPlateau)(Seq())(testRover3)))
  
    assert(new Rove(testPlateau).using(testRovers zip testInstructions) === Seq(
      testInstruction1(testPlateau)(Seq(testRover3))(testRover2),
      testInstruction2(testPlateau)(Seq(testRover2))(testRover3)))
  }
}
