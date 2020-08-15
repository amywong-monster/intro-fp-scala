package examples

object FixToOptionProblem {
  sealed trait Partition
  case object NoPartition extends Partition
  case class PartitionByMonth(month: String) extends Partition
  case class PartitionByYear(year: String) extends Partition

  def partitionPeriod(p: Partition): String =
    p match {
      case NoPartition             => "There is no partition"
      case PartitionByMonth(month) => s"Partition by ${month} month"
      case PartitionByYear(year)   => s"Partition by ${year} year"
    }

  partitionPeriod(NoPartition)
  partitionPeriod(PartitionByMonth("jan"))
}
