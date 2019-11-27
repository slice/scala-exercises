sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

sealed trait LinkedList[A] {
  def fold[B](acc: B)(f: (A, B) => B): B =
    this match {
      case End() => acc
      case Pair(h, t) => f(h, t.fold(acc)(f))
    }

  def length: Int =
    fold(0)((value, acc) => acc + 1)

  def contains(value: A): Boolean =
    this match {
      case Pair(h, t) =>
        if (h == value) true else t.contains(value)
      case End() => false
    }

  def apply(index: Int): Result[A] =
    this match {
      case Pair(h, t) =>
        if (index == 0)
          Success(h)
        else
          t(index - 1)
      case End() =>
        Failure("Index out of bounds")
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

object Main extends App {
  val example = Pair(1, Pair(2, Pair(3, End())))

  assert(example.contains(3))
  assert(!example.contains(4))
  assert(!End().contains(0))

  assert(example(0) == Success(1))
  assert(example(2) == Success(3))
  assert(example(3) == Failure("Index out of bounds"))
}
