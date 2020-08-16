package examples

object Types {
  case class LowerCaseEvenNumber(quotient: Int, str: String)

  case class Configuration(
    endpoint1: String,
    endpoint2: String,
    endpoint3: String,
    endpoint4: String,
    dbUrl: String
  )

  case class Result1(v: String)

  case class Result2(v: String)

  case class Result3(v: String)

  case class Result4(v: String)

  case class Result5(v: String)

  case class UserDetail(
    userName: String,
    result1: Result1,
    result2: Result2,
    result3: Result3,
    result4: Result4,
    result5: Result5
  )
}
