import pond._

object Stormtrooper {
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, _) => s"Move along, $first."
    }
}

Stormtrooper.inspect(Person("Luke Skywalker"))
Stormtrooper.inspect(Person("Ryan T"))