import pond.fp._

object Main extends App {

  for (n <- 1 to 10) {
    println(s"fib($n) = ${Fib.fib(n)}")
  }

  /////////////////////////////////////////////////////////////////////////////

  def add(l: Int, r: Int): Int = l + r
  val curriedAdd = FunctionOps.curry(add)
  val addWithTwo = FunctionOps.partial1(2, add)

  assert(addWithTwo(2) == 4)
  assert(curriedAdd(1)(2) == 3)
  assert(FunctionOps.uncurry(curriedAdd)(1, 2) == 3)
  assert(FunctionOps.compose(addWithTwo, addWithTwo)(2) == 6)

  /////////////////////////////////////////////////////////////////////////////

  val l = List(1, 2, 3, 4, 5)
  val ds = List.map(l)(_.toDouble)

  println(s"l: $l\nds: $ds\n")

  assert(List[String]() == (Nil: List[String]))
  assert(List.length(l) == 5)
  assert(List.length2(l) == 5)
  assert(List.tail(l) == List(2, 3, 4, 5))
  assert(List.setHead(l, "new first element") == List("new first element", 2, 3, 4, 5))
  assert(List.drop(l, 20) == Nil)
  assert(List.dropWhile(l)(_ < 3) == List(3, 4, 5))
  assert(List.append(List(1, 2, 3), List(4, 5, 6)) == List(1, 2, 3, 4, 5, 6))
  assert(List.append2(List(1, 2, 3), List(4, 5, 6)) == List(1, 2, 3, 4, 5, 6))
  assert(List.init(l) == List(1, 2, 3, 4))
  assert(List.sum(l) == 15)
  assert(List.sum2(l) == 15)
  assert(List.product(ds) == 120.0)
  assert(List.product2(ds) == 120.0)
  assert(List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)) == List(1, 2, 3))
  assert(List.reverse(l) == List(5, 4, 3, 2, 1))
  assert(List.add1(l) == List(2, 3, 4, 5, 6))
  assert(List.toStringsFromDoubles(ds) == List("1.0", "2.0", "3.0", "4.0", "5.0"))
  assert(List.filter(l)(_ % 2 == 0) == List(2, 4))
  assert(List.filter2(l)(_ % 2 == 0) == List(2, 4))
  assert(List.flatMap(List(1, 2, 3))(n => List(n, n + 1)) == List(1, 2, 2, 3, 3, 4))
  assert(List.zipWith(l, l)(_ + _) == List(2, 4, 6, 8, 10))

  /////////////////////////////////////////////////////////////////////////////

  val sorter = (l: Int, r: Int) => l < r
  assert(!Sorter.isSorted(Seq(1, 2, 3, 1, 4), sorter))
  assert(!Sorter.isSorted(Seq(4, 3, 2, 1), sorter))
  assert(Sorter.isSorted(Seq(1, 2, 3, 4, 5), sorter))
}
