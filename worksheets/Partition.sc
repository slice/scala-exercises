case class Person(name: String, age: Int)

val people = List(
  Person("Ryan", 16),
  Person("Soren", 16),
  Person("Luna", 19),
  Person("Ave", 18),
)

val (minors, adults) = people.partition(_.age > 18)
println(s"minors: $minors, adults: $adults")
