package movement.instruction

import rover.Rover
import world.plateau.Plateau

object Instruction {
  type Instruction = Plateau => Seq[Rover] => Rover => Rover
  
  val TurnLeft: Instruction = _ => _ => rover => rover.turnLeft
  val TurnRight: Instruction = _ => _ => rover => rover.turnRight
  val Move: Instruction = plateau => rovers => rover => {
    val newRover = plateau.bound(rover.move)
    if(rovers exists newRover.intersect) {
      rover
    } else newRover
  }
}
