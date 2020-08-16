package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, UserDetail }

object RepeatedArgumentExample {
  def createResult1(cfg: Configuration): Result1 =
    Result1(s"user details from ${cfg.endpoint1}") // suppose the result got from endpoint1

  def createResult2(cfg: Configuration, r1: Result1): Result2 =
    Result2(s"user details from ${cfg.endpoint2} and $r1") // suppose the result got from endpoint2

  def createResult3(cfg: Configuration, r1: Result1): Result3 =
    Result3(s"user details from ${cfg.endpoint3} and $r1")

  def createResult4(cfg: Configuration): Result4 =
    Result4(s"user details from ${cfg.endpoint4}")

  def createUserDetails(
    cfg: Configuration,
    r1: Result1,
    r2: Result2,
    r3: Result3,
    r4: Result4
  ): UserDetail = {
    val userName = s"Simon PJ found from ${cfg.dbUrl}" // suppose the username obtain from from dbUrl
    UserDetail(userName, r1, r2, r3, r4)
  }

  val r1 = createResult1(Types.cfg)
  val r2 = createResult2(Types.cfg, r1)
  val r3 = createResult3(Types.cfg, r1)
  val r4 = createResult4(Types.cfg)
  val result = createUserDetails(Types.cfg, r1, r2, r3, r4)

//  scala> RepeatedArgumentExample.result
}
