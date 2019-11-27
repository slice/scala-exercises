import scala.annotation.tailrec

sealed trait IntList {
  @tailrec
  def sum(acc: Int = 0): Int =
    this match {
      case Pair(h, t) => t.sum(h + acc)
      case End => acc
    }

  def double: IntList =
    this match {
      case Pair(h, t) => Pair(h * 2, t.double)
      case End => End
    }

  def product: Int =
    this match {
      case Pair(h, t) => h * t.product
      case End => 1
    }

  def length: Int =
    this match {
      case Pair(h, t) => 1 + t.length
      case End => 0
    }
}

case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

object Main extends App {
  val xs = Pair(1, Pair(2, End))
  val xs2 = Pair(1, Pair(2, Pair(3, End)))

  assert(xs.sum() == 3)
  assert(xs.length == 2)
  assert(xs.tail.length == 1)
  assert(xs.product == 2)
  assert(xs2.product == 6)
  assert(xs2.double == Pair(2, Pair(4, Pair(6, End))))
}
