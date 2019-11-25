package pond.fp

object Sorter {
  // a better impl from the solutions repository
  def isSorted[A](seq: Seq[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def algo(n: Int): Boolean =
      if (n >= seq.length - 1) true
      else if (!ordered(seq(n), seq(n + 1))) false
      else algo(n + 1)
    algo(0)
  }

  // this was my initial implementation...
  /*
  def isSorted[A](seq: Seq[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def algo(n: Int, good: Boolean): Boolean =
      if (!good) good
      else
        if (n >= seq.length - 1) good
        else algo(n + 1, ordered(seq(n), seq(n + 1)))
    algo(0, true)
  }
  */
}
