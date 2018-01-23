package world.plateau

import org.scalatest.FlatSpec
import world.location.Location
import world.plateau.PlateauReader.PlateauStringReader

import scala.util.Success

class PlateauStringReaderTest extends FlatSpec {
  "reading a plateau" should "return a success of that plateau if the string is properly formatted" in {
    assert(PlateauStringReader("5 5") === Success(Plateau(Location(5, 5))))
  }
  
  it should "result in a failure otherwise" in {
    assert(PlateauStringReader("5 NaN").isFailure, "5 NaN was successfully parsed as a plateau")
    assert(PlateauStringReader("5 6 7").isFailure, "5 6 7 was successfully parsed as a plateau")
  }
}
