package examples

object FixToOptionProblem {
  sealed trait Partition
  case object NoPartition extends Partition
  case class PartitionByMonth(month: String) extends Partition
  case class PartitionByYear(year: Int, leapCheck: Boolean) extends Partition

  def partitionPeriod(p: Partition): String =
    p match {
      case NoPartition                  => "There is no partition"
      case PartitionByMonth(month)      => s"Partition by ${month} month"
      case PartitionByYear(year, false) => s"Partition by ${year} year"
      case PartitionByYear(year, _)     =>
        val leap = if (year % 4 == 0) "leap " else ""
        s"Partition by $leap $year year"
    }

  partitionPeriod(NoPartition)
  partitionPeriod(PartitionByMonth("jan"))
}
