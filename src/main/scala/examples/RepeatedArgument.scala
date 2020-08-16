package examples

import examples.Types.{
  Configuration,
  EndpointResult1,
  EndpointResult2,
  EndpointResult3,
  EndpointResult4,
  UserDetail
}

object RepeatedArgument {
  def createResult1(cfg: Configuration): EndpointResult1 = {
    val url = cfg.endpoint1
    EndpointResult1(s"user details from $url") // imagine that's the result got from endpoint1
  }

  def createResult2(cfg: Configuration, endpointResult1: EndpointResult1): EndpointResult2 = {
    val url = cfg.endpoint2
    EndpointResult2(s"user details from $url and $endpointResult1") // imagine that's the result got from endpoint2
  }

  def createResult3(cfg: Configuration, endpointResult1: EndpointResult1): EndpointResult3 = {
    val url = cfg.endpoint3
    EndpointResult3(s"user details from $url and $endpointResult1")
  }

  def createResult4(
    cfg: Configuration,
    endpointResult2: EndpointResult2,
    endpointResult3: EndpointResult3
  ): EndpointResult4 = {
    val url = cfg.endpoint3
    EndpointResult4(s"user details from $url and $endpointResult2 and $endpointResult3")
  }

  def createUserDetails(
    cfg: Configuration,
    endpointResult1: EndpointResult1,
    endpointResult2: EndpointResult2,
    endpointResult3: EndpointResult3,
    endpointResult4: EndpointResult4
  ): UserDetail = {
    val dbUrl = cfg.dbUrl
    val userName = s"Simon PJ found from $dbUrl" // imagine that's the username from from dbUrl
    UserDetail(userName, endpointResult1, endpointResult2, endpointResult3, endpointResult4)
  }

  def composeFunc(): UserDetail = {
    val cfg = Configuration(
      "http://localhost:8080/url1",
      "http://localhost:8080/url2",
      "http://localhost:8080/url3",
      "http://localhost:8080/url4",
      "jdbc:postgresql://localhost:5432/postgres"
    )
    val result1 = createResult1(cfg)
    val result2 = createResult2(cfg, result1)
    val result3 = createResult3(cfg, result1)
    val result4 = createResult4(cfg, result2, result3)
    createUserDetails(cfg, result1, result2, result3, result4)
  }

}
