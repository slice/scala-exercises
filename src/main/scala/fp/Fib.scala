package pond.fp

object Fib {
  // whew, this took a while to understand.
  // i'm not very good with algorithms, y'see?
  //
  // we give it the first two starting numbers. we add those two up and go
  // forward, keeping track of the previous number too, since we always need
  // two numbers to add...
  //
  // aaah. july 4 on 2019 is pretty damn cold. and loud...
  // i just wanna learn about monads, okay?!
  def fib(n: Int): Int = {
    @annotation.tailrec
    def algo(n: Int, prev: Int, cur: Int): Int =
      // exhausted our accumulators, now we get to return!
      if (n == 0) prev

      // one sum stage...
      // add the current with the previous, keep track of the current (it's now
      // our previous).
      else algo(n - 1, cur, prev + cur)

    // fib(0) = 0, fib(1) = 1
    // our starting points...
    algo(n, 0, 1)
  }

  // fib numbers: 0, 1, 1, 2, 3, 5, 8, 13

  // parameter log for fib(3):
  // 3 0 1
  // 2 1 1
  // 1 1 2
  // 0 2 3

  // non tail recursive algorithm (not good enough!):
  /*
  def fib(n: Int): Int =
    n match {
      case 1 => 0
      case 2 => 1
      case _ => fib(n - 2) + fib(n - 1)
    }
  */
}
