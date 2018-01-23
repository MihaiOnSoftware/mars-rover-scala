package input.basic

import InputHandlers.{Instructions, Plateaus, Rovers}
import movement.instruction.Instruction.Instruction
import movement.instruction.InstructionReader
import movement.orientation.Orientation
import org.scalatest.FlatSpec
import rover.{Rover, RoverReader}
import world.location.Location
import world.plateau.{Plateau, PlateauReader}

import scala.util.{Success, Try}

class InputHandlersTest extends FlatSpec {
  
  implicit object FakePlateauReader extends PlateauReader[Any] {
    override def apply(plateau: Any): Try[Plateau] = Success(plateau.asInstanceOf[Plateau])
  }
  
  val testPlateau = Plateau(Location(5, 5))
  "plateau builder" should "apply the plateau reader if there is a first input on that first input" in {
    assert(
      Plateaus[Any](Seq(testPlateau, Location(4, 5))) ===
        Success(testPlateau))
  }
  
  it should "return a failure if there is no first input" in {
    assert(Plateaus[Any](Seq()).isFailure, "plateau builder didn't fail on an empty list")
  }
  
  implicit object FakeRoverReader extends RoverReader[Any] {
    override def apply(rover: Any): Try[Rover] = Success(rover.asInstanceOf[Rover])
  }
  
  val testRover1 = Rover(Location(3, 3), Orientation.North)
  val testRover2 = Rover(Location(3, 3), Orientation.South)
  val testRover3 = Rover(Location(3, 4), Orientation.South)
  "rover builder" should "apply the rover reader to every second input" in {
    assert(
      Rovers[Any](Seq(testPlateau, testRover1, Location(4, 5), testRover3)) ===
        Success(Seq(testRover1, testRover3)))
  }
  
  it should "return a failure if any rovers intersect" in {
    assert(
      Rovers[Any](Seq(testPlateau, testRover1, Location(4, 5), testRover2)).isFailure,
      "rovers overlapping should cause a failure")
  }
  
  it should "return a failure if there are no appropriate inputs" in {
    assert(
      Rovers[Any](Seq(testPlateau)).isFailure,
      "rovers input missing should cause a failure")
  }
  
  implicit object FakeInstructionReader extends InstructionReader[Any] {
    override def apply(actions: Any): Try[Instruction] = Success(actions.asInstanceOf[Instruction])
  }
  
  val testInstruction: Instruction = _ => _ => identity
  
  "instruction builder" should "apply the instruction reader to every odd input but not the first" in {
    assert(
      Instructions[Any](
        Seq(testPlateau, testRover1, testInstruction, testRover2, testInstruction)) ===
          Success(Seq(testInstruction, testInstruction)))
  }
  
  it should "fail if there are an even number of inputs" in {
    assert(Instructions[Any](
      Seq(testPlateau, testRover1, testInstruction, testRover2)).isFailure,
      "instruction builder didn't fail on an even number of inputs")
  }
}
