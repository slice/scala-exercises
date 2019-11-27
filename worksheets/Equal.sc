// a super cool™ equality type class
trait Equal[A] {
  def equal(l: A, r: A): Boolean
}

object StringEquality extends Equal[String] {
  def equal(l: String, r: String) = l == r
}

object Equal {
  implicit object StringEquality extends Equal[String] {
    def equal(l: String, r: String): Boolean = l == r
  }

  implicit class EqualOps[A](in: A) {
    def ===(other: A)(implicit eq: Equal[A]): Boolean = eq.equal(in, other)
  }
}

object Main extends App {
  import Equal._

  println("heck" === "heck")
}
