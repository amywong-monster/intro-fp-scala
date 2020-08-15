package examples

object BadOptionExample {
  sealed trait Partition
  case object NoPartition extends Partition
  case object PartitionByMonth extends Partition
  case object PartitionByYear extends Partition

  def partitionPeriod(p: Partition, month: Option[String], year: Option[String]): String =
    p match {
      case NoPartition      => "There is no partition"
      case PartitionByMonth => s"Partition by ${month.get} month"
      case PartitionByYear  => s"Partition by ${year.get} year"
    }

  def exampleCall(): String = {
    partitionPeriod(NoPartition, None, None)
    partitionPeriod(PartitionByMonth, None, None)
  }

//  scala> partitionPeriod(NoPartition, None, None)
//  val res2: String = There is no partition

//  scala> partitionPeriod(PartitionByMonth, Some("Jan"), None)
//  val res10: String = Partition by Jan month

//  scala> partitionPeriod(PartitionByYear, None, Some("2020"))
//  val res11: String = Partition by 2020 year
}
