class Person(val firstName: String, val lastName: String) {
  def name: String = s"$firstName $lastName"
}

object Person {
  def apply(wholeName: String): Person = {
    val parts = wholeName.split(" ")
    new Person(parts(0), parts(1))
  }
}

val joe = Person("Billy Joe")
println(joe.name)