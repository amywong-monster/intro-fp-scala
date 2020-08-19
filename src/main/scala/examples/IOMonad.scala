package examples

import zio.{ UIO => IO }

object SideEffectExample {
  lazy val print_ = println("hey!")
  def print3Times(): Unit = {
    print_
    print_
    print_
  }

  def print3TimesAgain(): Unit = {
    println("hey!")
    println("hey!")
    println("hey!")
  }

}

object EffectExample {
  lazy val printIO = IO(println("hey!"))
  // try `Runtime.default.unsafeRun(EffectExample.print3Times)` to see the output
  def print3Times(): IO[Unit] =
    printIO.flatMap(_ => printIO.flatMap(_ => printIO))

  // try `Runtime.default.unsafeRun(EffectExample.print3TimesAgain)` to see the output
  def print3TimesAgain(): IO[Unit] =
    IO(println("hey!")).flatMap(_ => IO(println("hey!")).flatMap(_ => IO(println("hey!"))))

}
