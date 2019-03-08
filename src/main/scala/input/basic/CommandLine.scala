package input.basic

import context.Process

object CommandLine extends App {
  new StringInput(new Process[String, String]).process(java.lang.System.in, Console.out)
}
