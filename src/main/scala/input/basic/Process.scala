package input.basic

import input.basic.InputHandlers.{Instructions, Plateaus, Rovers}
import movement.instruction.InstructionReader
import rover.RichRovers._
import rover.{Rove, RoverReader, RoverWriter}
import world.plateau.PlateauReader

import scala.util.Try

private [basic] object Process {
  def apply[Input, Output](inputs: Seq[Input])
    (implicit plateauReader: PlateauReader[Input],
      roverReader: RoverReader[Input],
      instructionReader: InstructionReader[Input],
      write: RoverWriter[Output]): Try[Seq[Output]] = {
    
    for {
      plateau  <- Plateaus(inputs)
      rovers <- Rovers(inputs)
      instructions <- Instructions(inputs)
    } yield {
      Rove the plateau using (rovers following instructions) and write.out
    }
    
  }
}
