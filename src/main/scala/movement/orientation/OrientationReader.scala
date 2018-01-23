package movement.orientation

trait OrientationReader[T] {
  def apply(orientation: T): Option[Orientation]
}

object OrientationReader {

  object OrientationStringReader extends OrientationReader[String] {
    override def apply(orientation: String): Option[Orientation] = Orientation.all.find(_.code == orientation)
  }
  
}
