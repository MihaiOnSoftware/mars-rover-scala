package input.basic

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream, PrintStream}

import context.Process
import movement.instruction.InstructionReader
import org.scalatest.{FlatSpec, Matchers}
import rover.{RoverReader, RoverWriter}
import world.plateau.PlateauReader

import scala.util.{Success, Try}

class StringInputTest extends FlatSpec with Matchers {

  object FakeProcess extends Process[String, String] {
    override def apply(inputs: Seq[String])
                                     (implicit plateauReader: PlateauReader[String],
                                      roverReader: RoverReader[String],
                                      instructionReader: InstructionReader[String],
                                      write: RoverWriter[String]): Try[Seq[String]] = {
      Success(inputs)
    }
  }

  "StringInput" should "read from the input stream, pass to the process, and write to the print stream as new lines" in {
    val inputString = Seq("first", "second").mkString("\n")
    val inputStream: InputStream = new ByteArrayInputStream(inputString.getBytes)
    val outputStream = new ByteArrayOutputStream()
    val printStream = new PrintStream(outputStream)

    new StringInput(FakeProcess).process(inputStream, printStream)

    outputStream.toString shouldBe (inputString + "\n")
  }
}
