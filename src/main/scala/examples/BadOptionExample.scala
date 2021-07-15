package examples

object BadOptionExample {
  sealed trait Partition
  case object NoPartition extends Partition
  case object PartitionByMonth extends Partition
  case object PartitionByYear extends Partition

  def partitionPeriod(
    p: Partition,
    month: Option[String],
    year: Option[Int],
    leapYearCheck: Option[Boolean]
  ): String =
    p match {
      case NoPartition                          => "There is no partition"
      case PartitionByMonth                     => s"Partition by ${month.get} month"
      case PartitionByYear if leapYearCheck.get =>
        val v = year.get
        val leap = if (v % 4 == 0) "leap " else ""
        s"Partition by $leap year $v"
      case PartitionByYear                      => s"Partition by year ${year.get}"
    }

  def exampleCall(): String = {
    partitionPeriod(NoPartition, None, None, None)
    partitionPeriod(PartitionByMonth, None, None, None)
  }

//  scala> partitionPeriod(NoPartition, None, None, None)
//  val res2: String = There is no partition

//  scala> partitionPeriod(PartitionByMonth, Some("Jan"), None, None)
//  val res10: String = Partition by Jan month

//  scala> partitionPeriod(PartitionByYear, None, Some(2020), Some(false))
//  val res11: String = Partition by year 2020
}
