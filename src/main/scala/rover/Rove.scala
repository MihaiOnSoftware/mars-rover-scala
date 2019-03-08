package rover

import movement.instruction.Instruction.Instruction
import movement.instruction.{Context, InstructedRovers}
import world.plateau.Plateau

import scala.annotation.tailrec

class Rove(plateau: Plateau) {
  val using: InstructedRovers => Seq[Rover] = { instructedRovers =>
    val rovers = instructedRovers.rovers
    val instructions = instructedRovers.instructions
    val context = Context(plateau, rovers)
    runRovers(instructions, context)
  }
  
  @tailrec
  private def runRovers(instructions: Seq[Instruction],
                        context: Context): Seq[Rover] = {
    if(instructions.isEmpty) context.rovers
    else runRovers(
      instructions.tail,
      runRover(instructions.head, context)
    )
  }
  
  private def runRover(instruction: Instruction, context: Context): Context = {
    val (remainingContext, rover) = context.pop
    remainingContext + instruction(remainingContext)(rover)
  }
}

object Rove {
  def the: Plateau => Rove = plateau => new Rove(plateau)
}
