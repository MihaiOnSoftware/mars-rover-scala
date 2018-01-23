package world.location

import org.scalatest.FlatSpec
import world.location.LocationReader.LocationStringReader

import scala.util.Success

class LocationStringReaderTest extends FlatSpec {
  "reading a location's strings" should "return a success of that location if the strings are well formatted" in {
    assert(LocationStringReader("1", "2") === Success(Location(1, 2)))
  }
  
  it should "return a failure otherwise" in {
    assert(LocationStringReader("A", "2").isFailure, "A and 2 did not result in a failure")
    assert(LocationStringReader("1", "B").isFailure, "1 and B did not result in a failure")
  }
}
