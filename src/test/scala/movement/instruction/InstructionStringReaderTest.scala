package movement.instruction

import movement.instruction.Instruction._
import movement.instruction.InstructionReader.InstructionStringReader
import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.Rover
import world.location.Location
import world.plateau.Plateau

class InstructionStringReaderTest extends FlatSpec {
  val testRover = Rover(Location(1, 1), Orientation.all.head)
  val testPlateau = Plateau(Location(5, 5))
  val testContext = Context(testPlateau, Seq())

  "reading an instruction list" should
    "return a success of the expected value if the list contains only known string instructions" in {
    assert(InstructionStringReader("LMRRL").isSuccess, "the instruction was not a success")

  }

  it should "result in an instruction that works as expected" in {
    val expectedInstruction =
      TurnLeft(testContext) andThen
        Move(testContext) andThen
        TurnRight(testContext) andThen
        TurnRight(testContext) andThen
        TurnLeft(testContext)
    assert(InstructionStringReader("LMRRL").get.apply(testContext)(testRover) === expectedInstruction(testRover))
  }

  it should "return a failure otherwise" in {
    assert(
      InstructionStringReader("random").isFailure,
      "the string random did not fail to parse as an instruction")
  }
}
