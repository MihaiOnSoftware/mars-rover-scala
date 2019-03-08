package context

import org.scalatest.FlatSpec
import rover.RoverReader._
import rover.RoverWriter._

import scala.util.Try

class ProcessTest extends FlatSpec {
  val testInput = Seq("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM")
  
  val expectedOutput = Seq("1 3 N", "5 1 E")
  
  "sending the test input" should "result in the test output" in {
    val actual: Try[Seq[String]] = new Process[String, String].apply(testInput)
    assert(actual.isSuccess, "test data did not successfully pass")
    assert(actual.get === expectedOutput)
  }
}
