object IntImplicits {
  implicit class IntOps(n: Int) {
    def yeah(): Unit =
      n times { _ =>
        println("Oh yeah!")
      }

    def times(f: Int => Unit): Unit =
      (1 to n) foreach f
  }
}

object Main extends App {
  import IntImplicits._

  2.yeah()
  println()
  3.yeah()
  println()
  -1.yeah()
}
