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

  val userDetailBox: Box[UserDetail] =
    for {
      r1         <- Box(cfg => Result1(s"user details from ${cfg.endpoint1}"))
      r2         <- Box(cfg => Result2(s"user details from ${cfg.endpoint2} and $r1"))
      r3         <- Box(cfg => Result3(s"user details from ${cfg.endpoint3} and $r1"))
      r4         <- Box(cfg => Result4(s"user details from ${cfg.endpoint4}"))
      userDetail <- Box(cfg => UserDetail(s"Simon PJ found from ${cfg.dbUrl}", r1, r2, r3, r4))
    } yield userDetail

  val result = userDetailBox.run(Types.cfg)
}
