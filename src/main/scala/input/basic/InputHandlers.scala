package input.basic

import movement.instruction.Instruction.Instruction
import movement.instruction.InstructionReader
import rover.{Rover, RoverReader}
import world.plateau.{Plateau, PlateauReader}

import scala.util.{Failure, Success, Try}

private [basic] object InputHandlers {
  object Plateaus {
    def apply[T](inputs: Seq[T])(implicit plateauReader: PlateauReader[T]): Try[Plateau] =
      inputs.headOption map plateauReader.apply getOrElse Failure(new RuntimeException("missing plateau input"))
  }
  
  object Rovers {
    def apply[T](inputs: Seq[T])(implicit roverReader: RoverReader[T]): Try[Seq[Rover]] = Try {
      if(inputs.tail.isEmpty) {
        throw new RuntimeException("missing rover input")
      }
      val roverInputs = takeFirsts(inputs.drop(1))
      roverInputs.tail.foldLeft(Seq(roverReader(roverInputs.head).get)) { (rovers, input) =>
        val newRover = roverReader(input).get
        if(rovers.exists(newRover.intersect)) {
          throw new RuntimeException("new rovers cannot be in the same spot as existing rovers")
        }
        rovers :+ roverReader(input).get
      }
    }
  }
  
  object Instructions {
    def apply[T](inputs: Seq[T])(implicit instructionReader: InstructionReader[T]):
    Try[Seq[Instruction]] = Try {
      takeSeconds(inputs.drop(1)) map { input =>
        instructionReader(input).get
      }
    } transform(Success(_), _ => Failure(new RuntimeException("missing instructions")))
  }
  
  private def takeOneOfTwos[T](whichPair: Seq[T] => T)(values: Seq[T]): Seq[T] =
    values.grouped(2).map(whichPair).toVector
  private def takeFirsts[T] = takeOneOfTwos[T](_.head)(_)
  private def takeSeconds[T] = takeOneOfTwos[T](_.last)(_)
  
}
