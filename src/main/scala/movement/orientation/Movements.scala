package movement.orientation

import world.location.Location

object Movements {
  type Movement = Location => Location
  val moveForward: Movement = location => location.copy(y = location.y + 1)
  val moveRight: Movement = location => location.copy(x = location.x + 1)
  val moveBackward: Movement = location => location.copy(y = location.y - 1)
  val moveLeft: Movement = location => location.copy(x = location.x - 1)
}
