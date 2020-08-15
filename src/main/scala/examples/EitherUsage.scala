package examples

object EitherUsage {
  sealed trait Party
  case object Independent extends Party
  case object Socialist extends Party
  case object Liberal extends Party

  def commentOnCandidate(name: String, eligibleParty: Either[String, Party]): String =
    eligibleParty match {
      case Left(err)          => s"$name is not eligible for election because $err"
//      case Right(_) => s"$name belongs to a Party!!!"
      case Right(Socialist)   => s"$name is leftist"
      case Right(Liberal)     => s"$name is conservative"
      case Right(Independent) => s"$name does not belong to any party"
    }

}
