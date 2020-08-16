package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, Result5, UserDetail }

object RemoveRepeatedArgumentExample {
  case class Reader[A](run: Configuration => A) {
    def flatMap[B](f: A => Reader[B]): Reader[B] =
      Reader { cfg =>
        f(run(cfg)).run(cfg)
      }

    def map[B](f: A => B): Reader[B] =
      flatMap(a => Reader(_ => f(a)))
  }

  def createResult1: Reader[Result1] =
    Reader { cfg =>
      val url = cfg.endpoint1
      Result1(s"user details from $url")
    }

  def createResult2(r2: Result1): Reader[Result2] =
    Reader { cfg =>
      val url = cfg.endpoint2
      Result2(s"user details from $url and $r2")
    }

  def createResult3(r1: Result1): Reader[Result3] =
    Reader { cfg =>
      val url = cfg.endpoint3
      Result3(s"user details from $url and $r1")
    }

  def createResult4(r2: Result2, r3: Result3): Reader[Result4] =
    Reader { cfg =>
      val url = cfg.endpoint3
      Result4(s"user details from $url and $r2 and $r3")
    }

  def createResult5: Reader[Result5] =
    Reader(_ => Result5(s"user details not using cfg"))

  def createUserDetails(): Reader[UserDetail] =
    for {
      r1  <- createResult1
      r2  <- createResult2(r1)
      r3  <- createResult3(r1)
      r4  <- createResult4(r2, r3)
      r5  <- createResult5
      cfg <- Reader[Configuration](identity)
      dbUrl = cfg.dbUrl
      userName = s"Simon PJ found from $dbUrl"
    } yield UserDetail(userName, r1, r2, r3, r4, r5)

//  scala> RemoveRepeatedArgumentExample.createUserDetails.run(Types.cfg)
// val res0: examples.Types.UserDetail = UserDetail(Simon PJ found from
// jdbc:postgresql://localhost:5432/postgres,Result1(user details from
// http://localhost:8080/url1),Result2(user details from http://localhost:8080/url2 and Result1(user details
// from http://localhost:8080/url1)),Result3(user details from http://localhost:8080/url3 and Result1(user
// details from http://localhost:8080/url1)),Result4(user details from http://localhost:8080/url3 and
// Result2(user details from http://localhost:8080/url2 and Result1(user details from
// http://localhost:8080/url1)) and Result3(user details from http://localhost:8080/url3 and Result1(user
// details from http://localhost:8080/url1))),Result5(user details not using cfg))
}
