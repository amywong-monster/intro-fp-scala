package examples

import examples.Types.{
  Configuration,
  EndpointResult1,
  EndpointResult2,
  EndpointResult3,
  EndpointResult4,
  UserDetail
}

object RemoveRepeatedArgument {
  case class Reader[R, A](value: R => A) {
    def flatMap[B](f: A => Reader[R, B]): Reader[R, B] =
      Reader { r => // r's type is R
        f(value(r)).value(r)
      }

    def map[B](f: A => B): Reader[R, B] =
      flatMap(a => Reader(r => f(a)))
  }

  def createResult1: Reader[Configuration, EndpointResult1] =
    Reader { cfg =>
      val url = cfg.endpoint1
      EndpointResult1(s"user details from $url")
    }

  def createResult2(endpointResult1: EndpointResult1): Reader[Configuration, EndpointResult2] =
    Reader { cfg =>
      val url = cfg.endpoint2
      EndpointResult2(s"user details from $url and $endpointResult1")
    }

  def createResult3(endpointResult1: EndpointResult1): Reader[Configuration, EndpointResult3] =
    Reader { cfg =>
      val url = cfg.endpoint3
      EndpointResult3(s"user details from $url and $endpointResult1")
    }

  def createResult4(
    endpointResult2: EndpointResult2,
    endpointResult3: EndpointResult3
  ): Reader[Configuration, EndpointResult4] =
    Reader { cfg =>
      val url = cfg.endpoint3
      EndpointResult4(s"user details from $url and $endpointResult2 and $endpointResult3")
    }

  def createUserDetails(
    endpointResult1: EndpointResult1,
    endpointResult2: EndpointResult2,
    endpointResult3: EndpointResult3,
    endpointResult4: EndpointResult4
  ): Reader[Configuration, UserDetail] =
    Reader { cfg =>
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

    val reader =
      for {
        result1 <- createResult1
        result2 <- createResult2(result1)
        result3 <- createResult3(result1)
        result4 <- createResult4(result2, result3)
        userDetails <- createUserDetails(result1, result2, result3, result4)
      } yield userDetails

    reader.value(cfg)
  }
}
