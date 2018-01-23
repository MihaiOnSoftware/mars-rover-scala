package movement.orientation

import movement.orientation.Orientation._
import org.scalatest.FlatSpec

class OrientationTest extends FlatSpec {
  val testOrientations = Seq(East, West, South, North)
  
  "getting the next orientation" should "return the next orientation in the sequence" in {
    assert(next(West)(testOrientations) === South)
  }
  
  it should "get the first orientation if the current orientation is the last" in {
    assert(next(North)(testOrientations) === East)
  }
  
  "getting the previous orientation" should "return the previous orientation in the sequence" in {
    assert(previous(West)(testOrientations) === East)
  }
  
  it should "get the last orientation if the current orientation is the first" in {
    assert(previous(East) === North)
  }
}
