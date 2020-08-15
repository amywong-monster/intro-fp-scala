package examples

object OptionUsage {
  def func(name: String, party: Option[String]): String =
    party match {
      case None        => s"$name is independent candidate"
      case Some(party) => s"$name represent the $party party"
    }
}
