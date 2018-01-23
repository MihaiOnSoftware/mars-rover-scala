package movement.orientation

import movement.orientation.Movements.Movement

sealed abstract class Orientation(val code: String, val move: Movement) {
  override def toString: String = code
}

object Orientation {
  case object North extends Orientation(code = "N", move = Movements.moveForward)
  case object East extends Orientation(code = "E", move = Movements.moveRight)
  case object South extends Orientation(code = "S", move = Movements.moveBackward)
  case object West extends Orientation(code = "W", move = Movements.moveLeft)
  
  val all = Seq(North, East, South, West)
  
  private def turn(orientation: Orientation, direction: Int)(implicit orientations: Seq[Orientation]): Orientation = {
    orientations.indexOf(orientation) + direction match {
      case index if index >= orientations.length => orientations.head
      case index if index < 0 => orientations.last
      case index => orientations(index)
    }
  }
  
  def next(orientation: Orientation)(implicit orientations: Seq[Orientation] = all): Orientation =
    turn(orientation, 1)
  def previous(orientation: Orientation)(implicit orientations: Seq[Orientation] = all): Orientation =
    turn(orientation, -1)
}
