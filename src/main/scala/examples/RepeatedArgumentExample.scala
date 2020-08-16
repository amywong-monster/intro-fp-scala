package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, Result5, UserDetail }

object RepeatedArgumentExample {
  def createResult1(cfg: Configuration): Result1 = {
    val url = cfg.endpoint1
    Result1(s"user details from $url") // suppose the result got from endpoint1
  }

  def createResult2(cfg: Configuration, r1: Result1): Result2 = {
    val url = cfg.endpoint2
    Result2(s"user details from $url and $r1") // suppose the result got from endpoint2
  }

  def createResult3(cfg: Configuration, r1: Result1): Result3 = {
    val url = cfg.endpoint3
    Result3(s"user details from $url and $r1")
  }

  def createResult4(cfg: Configuration, r2: Result2, r3: Result3): Result4 = {
    val url = cfg.endpoint3
    Result4(s"user details from $url and $r2 and $r3")
  }

  def createResult5: Result5 =
    Result5(s"user details not using cfg")

  def createUserDetails(cfg: Configuration): UserDetail = {
    val r1 = createResult1(cfg)
    val r2 = createResult2(cfg, r1)
    val r3 = createResult3(cfg, r1)
    val r4 = createResult4(cfg, r2, r3)
    val r5 = createResult5

    val dbUrl = cfg.dbUrl
    val userName = s"Simon PJ found from $dbUrl" // suppose the username obtain from from dbUrl
    UserDetail(userName, r1, r2, r3, r4, r5)
  }

//  scala> RepeatedArgumentExample.createUserDetails(Types.cfg)
// val res4: examples.Types.UserDetail = UserDetail(Simon PJ found from
// jdbc:postgresql://localhost:5432/postgres,Result1(user details from
// http://localhost:8080/url1),Result2(user details from http://localhost:8080/url2 and Result1(user details
// from http://localhost:8080/url1)),Result3(user details from http://localhost:8080/url3 and Result1(user
// details from http://localhost:8080/url1)),Result4(user details from http://localhost:8080/url3 and
// Result2(user details from http://localhost:8080/url2 and Result1(user details from
// http://localhost:8080/url1)) and Result3(user details from http://localhost:8080/url3 and Result1(user
// details from http://localhost:8080/url1))),Result5(user details not using cfg))
}
