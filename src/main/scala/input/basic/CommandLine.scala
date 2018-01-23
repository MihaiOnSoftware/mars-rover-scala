package input.basic

import scala.io._
import scala.util.{Failure, Success}

object CommandLine extends App {
  val lines = Source.stdin.getLines.toVector
  Process[String, String](lines) match {
    case Failure(ex) => println(ex.getMessage)
    case Success(rovers) => rovers foreach println
  }
}
