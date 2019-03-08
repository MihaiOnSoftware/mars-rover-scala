package rover

import movement.instruction.InstructedRovers
import movement.instruction.Instruction.Instruction

object RichRovers {
  
  implicit class RichRovers(rovers: Seq[rover.Rover]) {
    def following(instructions: Seq[Instruction]): InstructedRovers = InstructedRovers(rovers, instructions)
    def and[T](func: Rover => T): Seq[T] = rovers map func
  }
  
}
