package input.basic

import java.io.{InputStream, PrintStream}

import context.Process

import scala.io.Source
import scala.util.{Failure, Success}

class StringInput(process: Process[String, String]) {
  def process(in: InputStream, out: PrintStream): Unit = {
    val source = Source.fromInputStream(in)
    val lines = source.getLines.toVector
    process(lines) match {
      case Failure(ex) => out.println(ex.getMessage)
      case Success(rovers) => rovers foreach out.println
    }
  }
}
