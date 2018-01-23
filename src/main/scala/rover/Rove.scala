package rover

import movement.instruction.Instruction.Instruction
import world.plateau.Plateau

import scala.annotation.tailrec

class Rove(plateau: Plateau) {
  val using: Seq[(Rover,Instruction)] => Seq[Rover] = { instructedRovers =>
    val rovers = instructedRovers.map(_._1)
    val instruction = instructedRovers.map(_._2)
    runRovers(rovers, Seq(), instruction)
  }
  
  @tailrec
  private def runRovers(unmoved: Seq[Rover], moved: Seq[Rover], instructions: Seq[Instruction]): Seq[Rover] = {
    if(unmoved.isEmpty) moved
    else runRovers(
      unmoved.tail,
      moved :+ runRover(unmoved.head, instructions.head, unmoved.tail ++ moved),
      instructions.tail
    )
  }
  
  private def runRover(rover: Rover, instruction: Instruction, otherRovers: Seq[Rover]) = {
    instruction(plateau)(otherRovers)(rover)
  }
}

object Rove {
  def the: Plateau => Rove = plateau => new Rove(plateau)
}
