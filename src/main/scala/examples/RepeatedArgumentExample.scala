package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, UserDetail }

object RepeatedArgumentExample {
  val r1 = Result1(s"user details from ${Types.cfg.endpoint1}")
  val r2 = Result2(s"user details from ${Types.cfg.endpoint2} and $r1")
  val r3 = Result3(s"user details from ${Types.cfg.endpoint3} and $r1")
  val r4 = Result4(s"user details from ${Types.cfg.endpoint4}")
  val result = UserDetail(s"Simon PJ found from ${Types.cfg.dbUrl}", r1, r2, r3, r4)
}
