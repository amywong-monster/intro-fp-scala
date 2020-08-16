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

  case class EndpointResult1(v: String)

  case class EndpointResult2(v: String)

  case class EndpointResult3(v: String)

  case class EndpointResult4(v: String)

  case class UserDetail(
    userName: String,
    result1: EndpointResult1,
    result2: EndpointResult2,
    result3: EndpointResult3,
    result4: EndpointResult4
  )
}
