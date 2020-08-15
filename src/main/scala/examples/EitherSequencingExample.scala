package examples

import examples.CommonFuncs.convertToString
import examples.Types.LowerCaseEvenNumber

object EitherSequencingExample {
  // try input Left("not a number"), Right(0), Right(166), Right(250), Right(300), Right(400) to see what will
  // the result look like
  def computation(intEither: Either[String, Int]): Either[String, LowerCaseEvenNumber] =
    intEither.flatMap { n =>
      divideOneThousand(n).flatMap { quotient =>
        evenNumber(quotient).flatMap { n =>
          val str = convertToString(n)
          onlyForAllLowerCase(n, str)
        }
      }
    }

  def computationBySugar(intEither: Either[String, Int]): Either[String, LowerCaseEvenNumber] =
    for {
      n        <- intEither
      quotient <- divideOneThousand(n)
      even     <- evenNumber(quotient)
      str = convertToString(even)
      result   <- onlyForAllLowerCase(even, str)
    } yield result

  def divideOneThousand(dividsor: Int): Either[String, Int] =
    if (dividsor == 0)
      Left(s"Cannot be divided by $dividsor")
    else
      Right(1000 / dividsor)

  def evenNumber(n: Int): Either[String, Int] =
    if (n % 2 == 0) Right(n) else Left(s"After dividing 1000, the quotient $n is not even number")

  def onlyForAllLowerCase(quotient: Int, v: String): Either[String, LowerCaseEvenNumber] =
    if (v.toLowerCase == v)
      Right(LowerCaseEvenNumber(quotient, v))
    else
      Left(s"Quotient '$v' is not all lower case")
}
