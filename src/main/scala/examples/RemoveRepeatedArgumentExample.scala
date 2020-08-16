package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, UserDetail }

object RemoveRepeatedArgumentExample {
  case class Reader[A](run: Configuration => A) {
    def flatMap[B](f: A => Reader[B]): Reader[B] =
      Reader(cfg => f(run(cfg)).run(cfg))

    def map[B](f: A => B): Reader[B] =
      flatMap(a => Reader(_ => f(a)))
  }

  def createResult1: Reader[Result1] =
    Reader(cfg => Result1(s"user details from ${cfg.endpoint1}"))

  def createResult2(r1: Result1): Reader[Result2] =
    Reader(cfg => Result2(s"user details from ${cfg.endpoint2} and $r1"))

  def createResult3(r1: Result1): Reader[Result3] =
    Reader(cfg => Result3(s"user details from ${cfg.endpoint3} and $r1"))

  def createUserDetails(r1: Result1, r2: Result2, r3: Result3): Reader[UserDetail] =
    Reader(cfg => UserDetail(s"Simon PJ found from ${cfg.dbUrl}", r1, r2, r3))

  val finalReader: Reader[UserDetail] =
    for {
      r1         <- createResult1
      r2         <- createResult2(r1)
      r3         <- createResult3(r1)
      userDetail <- createUserDetails(r1, r2, r3)
    } yield userDetail

  val result = finalReader.run(Types.cfg)

//  scala> RemoveRepeatedArgumentExample.result
// val res3: examples.Types.UserDetail = UserDetail(Simon PJ found from
  // jdbc:postgresql://localhost:5432/postgres,Result1(user details from
  // http://localhost:8080/url1),Result2(user details from http://localhost:8080/url2 and Result1(user details
  // from http://localhost:8080/url1)),Result3(user details from http://localhost:8080/url3 and Result1(user
  // details from http://localhost:8080/url1)))
}
