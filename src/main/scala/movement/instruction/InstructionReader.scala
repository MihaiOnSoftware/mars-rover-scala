package movement.instruction

import movement.instruction.Instruction._

import scala.util.{Failure, Success, Try}

trait InstructionReader[T] {
  def apply(actions: T): Try[Instruction]
}

object InstructionReader {
  
  implicit object InstructionStringReader extends InstructionReader[String] {
    
    override def apply(instructions: String): Try[Instruction] = Try {
      instructions.tail.foldLeft[Instruction](instructionMap(instructions.head)) { (instruction, char) =>
        val newInstruction = instructionMap(char)
        context => instruction(context) andThen newInstruction(context)
      }
    } transform(Success(_), _ => Failure(new RuntimeException("malformed instructions")))
    
    private def instructionMap: Map[Char, Instruction] = Map(
      'L' -> TurnLeft,
      'R' -> TurnRight,
      'M' -> Move
    )
  }
  
}
