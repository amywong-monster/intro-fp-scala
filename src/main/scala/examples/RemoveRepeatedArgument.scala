package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, UserDetail }

object RemoveRepeatedArgument {
  // the 2 Monad features
  // A => M[A]
  // M[A] => (A => M[B]) => M[B]
  // If Box is the Monad, then
  // A => Box[A]
  // Box[A] => (A => Box[B]) => M[B]
  case class Box[A](run: Configuration => A) {
    def flatMap[B](f: A => Box[B]): Box[B] =
      Box { configuration =>
        f(run(configuration)).run(configuration)
      }

    def map[B](f: A => B): Box[B] =
      flatMap(a => Box(_ => f(a)))
  }

  def createResult1: Box[Result1] =
    Box(cfg => Result1(s"user details from ${cfg.endpoint1}"))

  def createResult2(r1: Result1): Box[Result2] =
    Box(cfg => Result2(s"user details from ${cfg.endpoint2} and $r1"))

  def createResult3(r1: Result1): Box[Result3] =
    Box(cfg => Result3(s"user details from ${cfg.endpoint3} and $r1"))

  def createResult4: Box[Result4] =
    Box(cfg => Result4(s"user details from ${cfg.endpoint4}"))

  def createUserDetails(r1: Result1, r2: Result2, r3: Result3, r4: Result4): Box[UserDetail] =
    Box(cfg => UserDetail(s"Simon PJ found from ${cfg.dbUrl}", r1, r2, r3, r4))

  val readerForFinalResult: Box[UserDetail] =
    for {
      r1         <- createResult1
      r2         <- createResult2(r1)
      r3         <- createResult3(r1)
      r4         <- createResult4
      userDetail <- createUserDetails(r1, r2, r3, r4)
    } yield userDetail

  val result = readerForFinalResult.run(Types.cfg)

//  scala> RemoveRepeatedArgumentExample.result
}
