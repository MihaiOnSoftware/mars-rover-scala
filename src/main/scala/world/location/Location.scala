package world.location

case class Location(x: Int, y: Int) {
  def bound(start: Location, end: Location): Location = {
    Location(
      x = start.x max x min end.x,
      y = start.y max y min end.y
    )
  }
}
