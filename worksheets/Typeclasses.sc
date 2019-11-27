import java.util.Date

// a type class is a trait with at least one type variable.
//
// the type variable specify the concrete types the type class instances are
// defined for.

// our type class:
trait HTMLWriter[A] {
  def write(in: A): String
}

// we must package the implicit class within an object because top-level
// implicits aren't allowed
object HTMLImplicits {
  implicit class HTMLOps[T](data: T) {
    def toHTML(implicit writer: HTMLWriter[T]) = writer.write(data)
  }
}

// an "interface" that lets us quickly grab the most appropriate implicit
// HTMLWriter in scope
object HTMLWriter {
  def apply[A](implicit writer: HTMLWriter[A]): HTMLWriter[A] =
    writer
}

object Main extends App {
  final case class Person(name: String, email: String)
  val me = Person("slice", "slice@slice.website")

  implicit object PersonWriter extends HTMLWriter[Person] {
    def write(in: Person): String =
      s"<span>${in.name} &lt;${in.email}&gt;</span>"
  }

  implicit object DateWriter extends HTMLWriter[Date] {
    def write(in: Date): String = s"<span>${in.toString}</span>"
  }

  import HTMLImplicits._

  // explicitly:
  // println(DateWriter.write(new Date))
  // println(PersonWriter.write(me))

  // using the interface:
  // println(HTMLWriter[Date].write(new Date))
  // println(HTMLWriter[Person].write(me))

  // using type enrichment:
  println((new Date).toHTML)
  println(me.toHTML)

  implicit val obfuscatedWriter = new HTMLWriter[Person] {
    def write(in: Person): String = {
      val email = in.email.replaceAll("@", " at ")
      s"<span>${in.name} &lt;${email}&gt;</span>"
    }
  }

  println(me.toHTML) // this call uses the object above over the val
  println(me.toHTML(obfuscatedWriter))

  implicit object ApproximationWriter extends HTMLWriter[Int] {
    def write(in: Int): String =
      s"It's definitely less than ${((in / 10) + 1) * 10}"
  }

  val value = 23
  println(s"$value -- ${value.toHTML}!")
}
