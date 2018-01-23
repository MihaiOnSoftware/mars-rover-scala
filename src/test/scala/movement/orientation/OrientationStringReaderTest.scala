package movement.orientation

import movement.orientation.Orientation.North
import movement.orientation.OrientationReader.OrientationStringReader
import org.scalatest.FlatSpec

class OrientationStringReaderTest extends FlatSpec {
  "reading a string" should "return the orientation in an option if it exists" in {
    assert(OrientationStringReader("N") === Some(North))
  }
  
  it should "return an empty option otherwise" in {
    assert(OrientationStringReader("Nothing").isEmpty, "the orientation option was not empty")
  }
}
