package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, UserDetail }

object RepeatedArgumentExample {
  def createResult1(cfg: Configuration): Result1 =
    Result1(s"user details from ${cfg.endpoint1}") // suppose the result got from endpoint1

  def createResult2(cfg: Configuration, r1: Result1): Result2 =
    Result2(s"user details from ${cfg.endpoint2} and $r1") // suppose the result got from endpoint2

  def createResult3(cfg: Configuration, r1: Result1): Result3 =
    Result3(s"user details from ${cfg.endpoint3} and $r1")

  def createUserDetails(cfg: Configuration, r1: Result1, r2: Result2, r3: Result3): UserDetail = {
    val userName = s"Simon PJ found from ${cfg.dbUrl}" // suppose the username obtain from from dbUrl
    UserDetail(userName, r1, r2, r3)
  }

  val r1 = createResult1(Types.cfg)
  val r2 = createResult2(Types.cfg, r1)
  val r3 = createResult3(Types.cfg, r1)
  val result = createUserDetails(Types.cfg, r1, r2, r3)

//  scala> RepeatedArgumentExample.result
// val res0: examples.Types.UserDetail = UserDetail(Simon PJ found from
  // jdbc:postgresql://localhost:5432/postgres,Result1(user details from
  // http://localhost:8080/url1),Result2(user details from http://localhost:8080/url2 and Result1(user details
  // from http://localhost:8080/url1)),Result3(user details from http://localhost:8080/url3 and Result1(user
  // details from http://localhost:8080/url1)))
}
