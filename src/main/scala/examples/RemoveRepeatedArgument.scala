package examples

import examples.Types.{ Configuration, Result1, Result2, Result3, Result4, Result5, UserDetail }

object RemoveRepeatedArgument {
  case class Reader[C, A](value: C => A) {
    def flatMap[B](f: A => Reader[C, B]): Reader[C, B] =
      Reader { c =>
        f(value(c)).value(c)
      }

    def map[B](f: A => B): Reader[C, B] =
      flatMap(a => Reader(_ => f(a)))
  }

  def createResult1: Reader[Configuration, Result1] =
    Reader { cfg =>
      val url = cfg.endpoint1
      Result1(s"user details from $url")
    }

  def createResult2(r2: Result1): Reader[Configuration, Result2] =
    Reader { cfg =>
      val url = cfg.endpoint2
      Result2(s"user details from $url and $r2")
    }

  def createResult3(r1: Result1): Reader[Configuration, Result3] =
    Reader { cfg =>
      val url = cfg.endpoint3
      Result3(s"user details from $url and $r1")
    }

  def createResult4(r2: Result2, r3: Result3): Reader[Configuration, Result4] =
    Reader { cfg =>
      val url = cfg.endpoint3
      Result4(s"user details from $url and $r2 and $r3")
    }

  def createResult5: Reader[Configuration, Result5] =
    Reader(_ => Result5(s"user details not using cfg"))

  def createUserDetails(
    r1: Result1,
    r2: Result2,
    r3: Result3,
    r4: Result4,
    r5: Result5
  ): Reader[Configuration, UserDetail] =
    Reader { cfg =>
      val dbUrl = cfg.dbUrl
      val userName = s"Simon PJ found from $dbUrl" // imagine that's the username from from dbUrl
      UserDetail(userName, r1, r2, r3, r4, r5)
    }

  def composeFunc(): UserDetail = {
    val reader: Reader[Configuration, UserDetail] =
      for {
        r1          <- createResult1
        r2          <- createResult2(r1)
        r3          <- createResult3(r1)
        r4          <- createResult4(r2, r3)
        r5          <- createResult5
        userDetails <- createUserDetails(r1, r2, r3, r4, r5)
      } yield userDetails

    val cfg = Configuration(
      "http://localhost:8080/url1",
      "http://localhost:8080/url2",
      "http://localhost:8080/url3",
      "http://localhost:8080/url4",
      "jdbc:postgresql://localhost:5432/postgres"
    )

    reader.value(cfg)
  }
}
