package examples

object OptionSequencingExample {

  def isStringAllLower(v: String): Boolean =
    v.toLowerCase == v

  //  scala> (Some("abC"): Option[String]).map(isStringAllLower)
  //  val res2: Option[Boolean] = Some(false)
  //
  //  scala> (Some("abc"): Option[String]).map(isStringAllLower)
  //  val res3: Option[Boolean] = Some(true)
  //
  //  scala> (None: Option[String]).map(isStringAllLower)
  //  val res4: Option[Boolean] = None

  def sequenceComputation(intOpt: Option[Int]): Option[Boolean] =
    intOpt.flatMap(dividsor =>
      division(dividsor, 1000)
    ).map(quotient =>
      convertToString(quotient) // sidenote: `quotient` can be omitted s.t. it can be written as `map(convertToString)`
    ).map(isStringAllLower)
  // sidenote: implementaiton can be simplified as
  //    intOpt.flatMap(dividsor =>
  //      division(dividsor, 1000)
  //    ).map(
  //      convertToString andThen isStringAllLower
  //    )

  //  scala> sequenceComputation(Some(999))
  //  val res0: Option[Boolean] = Some(true)
  //
  //  scala> sequenceComputation(Some(0))
  //  val res1: Option[Boolean] = None
  //
  //  scala> sequenceComputation(Some(9))
  //  val res2: Option[Boolean] = Some(false)
  //
  //  scala> sequenceComputation(None)
  //  val res3: Option[Boolean] = None

  // same as the above implementaiton
  // scala compiler will de-sugar it back to flatMap and map before compilation
  def sequenceComputationBySugar(intOpt: Option[Int]): Option[Boolean] =
    for {
      quotient <- intOpt
      flag = isStringAllLower(convertToString(quotient))
    } yield flag

  def division(dividsor: Int, n: Int): Option[Int] =
    if (dividsor == 0) None else Some(n / dividsor)

  val convertToString: Int => String = {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case 4 => "four"
    case _ => "I give up"
  }
}
