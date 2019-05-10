package input.basic

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream, PrintStream}

import context.Process
import movement.instruction.InstructionReader
import org.scalatest.{FlatSpec, Matchers}
import rover.{RoverReader, RoverWriter}
import world.plateau.PlateauReader

import scala.util.{Failure, Success, Try}

class StringInputTest extends FlatSpec {

  object FakeProcess extends Process[String, String] {
    override def apply(inputs: Seq[String])
                                     (implicit plateauReader: PlateauReader[String],
                                      roverReader: RoverReader[String],
                                      instructionReader: InstructionReader[String],
                                      write: RoverWriter[String]): Try[Seq[String]] = {
      Success(inputs)
    }
  }

  object FailingProcess extends Process[String, String] {
    override def apply(inputs: Seq[String])
                      (implicit plateauReader: PlateauReader[String],
                       roverReader: RoverReader[String],
                       instructionReader: InstructionReader[String],
                       write: RoverWriter[String]): Try[Seq[String]] = {
      Failure(new RuntimeException(inputs.head))
    }
  }

  "StringInput" should "read from the input stream, pass to the process, and write new lines to the print stream" in {
    val inputString = Seq("first", "second").mkString("\n")
    val inputStream: InputStream = new ByteArrayInputStream(inputString.getBytes)
    val outputStream = new ByteArrayOutputStream()
    val printStream = new PrintStream(outputStream)

    new StringInput(FakeProcess).process(inputStream, printStream)

    assert(outputStream.toString === (inputString + "\n"))
  }

  it should "write failures out too" in {
    val inputString = "There is no try, only do, or do not!"
    val inputStream: InputStream = new ByteArrayInputStream(inputString.getBytes)
    val outputStream = new ByteArrayOutputStream()
    val printStream = new PrintStream(outputStream)

    new StringInput(FailingProcess).process(inputStream, printStream)

    assert(outputStream.toString === (inputString + "\n"))
  }
}
