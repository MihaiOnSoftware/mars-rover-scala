package movement.instruction

import movement.instruction.Instruction.Instruction
import rover.Rover

case class InstructedRovers(rovers: Seq[Rover], instructions: Seq[Instruction]) {
  def +(rover: Rover, instruction: Instruction): InstructedRovers = {
    copy(rovers :+ rover, instructions :+ instruction)
  }
}
