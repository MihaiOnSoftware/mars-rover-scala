package input.basic

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream, PrintStream}

import org.scalatest.FlatSpec

class CommandLineIntegrationTest extends FlatSpec {

  val testInput = Seq("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM").mkString("\n")

  val expectedOutput = Seq("1 3 N", "5 1 E").mkString("\n") + "\n"

  def withIn[Input <: InputStream](in: Input)(test: Input => Unit): Unit = {
    val originalIn = System.in
    System.setIn(in)
    test(in)
    System.setIn(originalIn)
  }

  "Given the test command line input, the system" should "calculate the expected output" in {
    val input = new ByteArrayInputStream(testInput.getBytes)
    val output = new ByteArrayOutputStream()

    withIn(input) { in =>
      Console.withOut(new PrintStream(output)) {
        CommandLine.main(Array())
        in.close()
      }
    }

    assert(output.toString() === expectedOutput)
  }

}
