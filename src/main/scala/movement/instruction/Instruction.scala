package movement.instruction

import rover.Rover
import world.plateau.Plateau

object Instruction {
  type Instruction = Context => Rover => Rover

  val TurnLeft: Instruction = _ => rover => rover.turnLeft
  val TurnRight: Instruction = _ => rover => rover.turnRight
  val Move: Instruction = context => rover => {
    val Context(plateau, rovers) = context
    val newRover = plateau.bound(rover.move)
    if(rovers exists newRover.intersect) {
      rover
    } else newRover
  }
}
