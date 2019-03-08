package input.basic

import context.Process

import scala.io._
import scala.util.{Failure, Success}

object CommandLine extends App {
  new StringInput(new Process[String, String]).process(java.lang.System.in, Console.out)
}
