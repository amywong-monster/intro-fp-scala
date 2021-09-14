package examples

object ExplainSameContext {
  // it compiles because all operations return the value with the same context as the origOpts.
  def sameContext1(origOpt: Option[String]): Option[String] =
    origOpt.flatMap { v1 =>
      Option(s"$v1, execute using the same context Option") // same as using Some(???)
    }.flatMap { v2 =>
      Option(s"$v2, execute using the same context Option")
    }.flatMap { _ =>
      None
    }

  // compiles, this time the context is Either[Throwable, ?]
  def sameContext2(origEither: Either[Throwable, String]): Either[Throwable, String] =
    origEither.flatMap { v1 =>
      Right(s"$v1, execute using the same context Either[Throwable, ?]")
    }.flatMap { _ =>
      Left(new Exception("execute using the same context Either[Throwable, ?]"))
    }

  // not compiles, because Either[Throwable, ?] and Either[String, ?] are two different contexts
//  def differentContext(origEither: Either[Throwable, String]): Either[Throwable, String] =
//    origEither.flatMap { v1 =>
//      Right(s"$v1, execute using the same context Either[Throwable, ?]")
//    }.flatMap { _ =>
// Left("This time the context is Either[String, ?], different from origEither's, therefore not compiles")
//    }

  // compiles
  def sameContext3(origEither: Either[Throwable, String]): Either[Throwable, String] =
    origEither.flatMap { v1 =>
      Right(s"$v1, execute using the same context Either[Throwable, ?]")
    }.flatMap { _ =>
      Left(new Exception("execute using the same context Either[Throwable, ?]"))
    }.flatMap { _ =>
      Left(
        new Exception(
          "Although different error message from last one, still same context Either[Throwable, ?]"
        )
      )
    }
}
