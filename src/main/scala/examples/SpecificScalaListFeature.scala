package examples

object SpecificScalaListFeature {
  def listFlatMapExample[A](xs: List[A]): List[A] =
    xs.flatMap(x => List(x, x))

  def seemsDifferentContextButCompile[A](xs: List[Option[A]]): List[A] =
    xs.flatMap(opt => opt)

  def meaningOfLastExample[A](xs: List[Option[A]]): List[A] =
    xs.flatMap(opt => opt.fold(List.empty[A])(x => List(x)))
}
