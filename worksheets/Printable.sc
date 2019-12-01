// type class
trait Printable[A] {
  def format(in: A): String
}

// interface methods
object Printable {
  def format[A](in: A)(implicit printer: Printable[A]): String =
    printer.format(in)

  def print[A](in: A)(implicit printer: Printable[A]): Unit =
    println(format(in))
}

// type class implementations for some types
object PrintableInstances {
  implicit val printableString = new Printable[String] {
    def format(in: String): String = in
  }

  implicit val printableInt = new Printable[Int] {
    def format(in: Int): String = in.toString
  }
}

// better syntax for printing printable stuff
object PrintableSyntax {
  implicit class PrintableOps[A](in: A) {
    def format(implicit printer: Printable[A]): String =
      printer.format(in)

    def print(implicit printer: Printable[A]): Unit =
      println(printer.format(in))
  }
}

object Main extends App {
  import PrintableInstances._
  import PrintableSyntax._

  final case class Cat(name: String, age: Int, color: String)

  implicit val printableCat = new Printable[Cat] {
    def format(in: Cat): String = {
      val name = Printable.format(cat.name)
      val age = cat.age.format
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  val cat = Cat("Garfield", 38, "ginger and black")
  cat.print
}
