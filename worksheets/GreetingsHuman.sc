object person {
  val firstName = "Ryan"
  val lastName = "Tongol"
}

object alien {
  def greet(p: person.type) = "Hello, " + p.firstName
}

println(alien.greet(person))

// objects are singletons, so we must access the only instance by using ".type"
// on the object! makes sense.