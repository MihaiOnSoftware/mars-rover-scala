package rover

import movement.instruction.Instruction.Instruction

object RichRovers {
  
  implicit class RichRovers(rovers: Seq[Rover]) {
    def following(instructions: Seq[Instruction]): Seq[(Rover, Instruction)] = rovers zip instructions
    def and[T](func: Rover => T): Seq[T] = rovers map func
  }
  
}
