package pond.fp

sealed trait List[+A]
final case object Nil extends List[Nothing]
final case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  /** Exercise 3.13: Combines two lists together. */
  def append[A](l1: List[A], l2: List[A]): List[A] =
    l1 match {
      case Nil => l2
      case Cons(h, t) => Cons(h, append(t, l2))
    }

  /** Exercise 3.14: Combines two lists together using `foldLeft`. */
  def append2[A](l1: List[A], l2: List[A]): List[A] =
    foldRight(l1, l2)(Cons(_, _))

  /** Exercise 3.15: Concatenates a list of lists into a single list. */
  def concat[A](l: List[List[A]]): List[A] =
    foldRight(l, List[A]())(append)

  /** Returns all elements of the list except for the first. */
  def tail[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("List.tail of Nil")
      case Cons(h, t) => t
    }

  /** Returns all elements of the list except for the last. */
  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("List.init of Nil")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }

  /** Drops `n` elements from the list. */
  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_, t) => drop(t, n - 1)
    }

  // def map[A, B](l: List[A])(f: A => B): List[B] =
  //   foldRight(l, List[B]())((l, r) => Cons(f(l), r))

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] =
    l match {
      case Cons(h, t) if f(h) => dropWhile(t)(f)
      case _ => l
    }

  /*
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Nil => Nil
      case Cons(h, t) =>
        if (f(h)) dropWhile(t, f)
        else l
    }
  */

  def setHead[A](l: List[A], x: A): List[A] =
    Cons(x, tail(l))

  def foldRight[A, B](l: List[A], acc: B)(f: (A, B) => B): B =
    l match {
      case Nil => acc
      case Cons(h, t) => f(h, foldRight(t, acc)(f))
    }

  @annotation.tailrec
  def foldLeft[A, B](l: List[A], acc: B)(f: (B, A) => B): B =
    l match {
      case Nil => acc
      case Cons(h, t) => foldLeft(t, f(acc, h))(f)
    }

  /** Exercise 3.16: Adds 1 to each [[Int]] element. */
  def add1(ns: List[Int]): List[Int] =
    foldRight(ns, List[Int]())((x, acc) => Cons(x + 1, acc))

  /** Exercise 3.17: Converts each [[Double]] element into a [[String]]. */
  def toStringsFromDoubles(ds: List[Double]): List[String] =
    foldRight(ds, List[String]())((x, acc) => Cons(x.toString, acc))

  /** Exercise 3.18 */
  def map[A, B](l: List[A])(f: A => B): List[B] =
    foldRight(l, List[B]())((x, acc) => Cons(f(x), acc))

  /** Exercise 3.19 */
  // def filter[A](l: List[A])(f: A => Boolean): List[A] =
  //   l match {
  //     case Nil => l
  //     // element matches
  //     case Cons(h, t) if f(h) => Cons(h, filter(t)(f))
  //     // element doesn't match and we have more, continue
  //     case Cons(_, Cons(h, t)) => Cons(h, filter(t)(f))
  //     // element doesn't match and we don't have any more
  //     case Cons(_, Nil) => init(l)
  //   }

  /** Exercise 3.19 */
  def filter[A](l: List[A])(f: A => Boolean): List[A] =
    foldRight(l, List[A]())((x, acc) => if (f(x)) Cons(x, acc) else acc)

  /** Exercise 3.20 */
  // def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
  //   foldRight(l, List[B]())((x, acc) => append(f(x), acc))

  /** Exercise 3.20 */
  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
    concat(map(l)(f))

  /** Exercise 3.21 */
  def filter2[A](l: List[A])(f: A => Boolean): List[A] =
    flatMap(l)(x => if (f(x)) List(x) else Nil)

  /** Exercise 3.22 */
  def addVertically(l: List[Int], r: List[Int]): List[Int] =
    (l, r) match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addVertically(t1, t2))
    }

  /** Exercise 3.23 */
  def zipWith[A](l: List[A], r: List[A])(f: (A, A) => A): List[A] =
    (l, r) match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    }

  /** Reverses the ordering of a list. */
  def reverse[A](l: List[A]): List[A] =
    foldLeft(l, List[A]())((acc, x) => Cons(x, acc))

  /** Computes the length of a list using `foldRight`. */
  def length[A](l: List[A]): Int =
    foldRight(l, 0)((_, acc) => acc + 1)

  /** Computes the length of a list using `foldLeft`. */
  def length2[A](l: List[A]): Int =
    foldLeft(l, 0)((acc, _) => acc + 1)

  /** Computes the sum of a list of [[Int]] using `foldRight`. */
  def sum(ns: List[Int]): Int =
    foldRight(ns, 0)(_ + _)

  /** Computes the sum of a list of [[Int]] using `foldLeft`. */
  def sum2(ns: List[Int]): Int =
    foldLeft(ns, 0)(_ + _)

  /** Computes the product of a list of [[Double]] using `foldRight`. */
  def product(ds: List[Double]): Double =
    foldRight(ds, 1.0)(_ * _)

  /** Computes the product of a list of [[Double]] using `foldLeft`. */
  def product2(ds: List[Double]): Double =
    foldLeft(ds, 1.0)(_ * _)

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
