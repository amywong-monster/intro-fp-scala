package examples

object ForComprehenUsage {
  def sameContext1(origOpt: Option[String]): Option[String] =
    for {
      v1 <- origOpt
      v2 <- Option(s"$v1, execute using the same context Option")
    } yield v2

// Scala compiler de-sugar it to
//  origOpt.flatMap { v1 =>
//    Option(s"$v1, execute using the same context Option") // same as using Some(???)
//  }.flatMap { v2 =>
//    Option(s"$v2, execute using the same context Option")
//  }
}
