package examples

import examples.CommonFuncs.convertToString
import examples.Types.LowerCaseEvenNumber

object OptionSequencingExample {

  // try input None, Some(0), Some(166), Some(250), Some(300), Some(400) to see what will the result look like
  def computation(intOpt: Option[Int]): Option[LowerCaseEvenNumber] =
    intOpt.flatMap { n =>
      divideOneThousand(n).flatMap { quotient =>
        evenNumber(quotient).flatMap { n =>
          val str = convertToString(n)
          onlyForAllLowerCase(n, str)
        }
      }
    }

  def computationBySugar(intOpt: Option[Int]): Option[LowerCaseEvenNumber] =
    for {
      n        <- intOpt // intOpt is a value wrapped by Option, therefore it can use the arrow to "retrieve"
      quotient <- divideOneThousand(n)
      even     <- evenNumber(quotient)
      str = convertToString(even) // return value not wrapped by Option, therefore use equal sign
      result   <- onlyForAllLowerCase(even, str)
    } yield result

  def divideOneThousand(dividsor: Int): Option[Int] =
    if (dividsor == 0) None else Some(1000 / dividsor)

  def evenNumber(n: Int): Option[Int] =
    if (n % 2 == 0) Some(n) else None

  def onlyForAllLowerCase(quotient: Int, v: String): Option[LowerCaseEvenNumber] =
    if (v.toLowerCase == v) Some(LowerCaseEvenNumber(quotient, v)) else None
}
