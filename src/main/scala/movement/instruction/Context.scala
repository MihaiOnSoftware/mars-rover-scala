package movement.instruction

import rover.Rover
import world.plateau.Plateau

case class Context(plateau: Plateau, rovers: Seq[Rover]) {
  def pop: (Context, Rover) = {
    (copy(rovers = rovers.tail), rovers.head)
  }

  def +(rover: Rover): Context = {
    copy(rovers = rovers :+ rover)
  }
}
