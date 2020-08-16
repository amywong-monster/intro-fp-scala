package examples

object Types {
  case class LowerCaseEvenNumber(quotient: Int, str: String)

  case class Configuration(endpoint1: String, endpoint2: String, endpoint3: String, dbUrl: String)

  val cfg = Configuration(
    "http://localhost:8080/url1",
    "http://localhost:8080/url2",
    "http://localhost:8080/url3",
    "jdbc:postgresql://localhost:5432/postgres"
  )

  case class Result1(v: String)

  case class Result2(v: String)

  case class Result3(v: String)

  case class UserDetail(userName: String, result1: Result1, result2: Result2, result3: Result3)
}
