object Main extends App {
  val people = Set(
    "Alice",
    "Bob",
    "Charlie",
    "Derek",
    "Edith",
    "Fred"
  )

  val ages = Map(
    "Alice" -> 20,
    "Bob" -> 30,
    "Charlie" -> 50,
    "Derek" -> 40,
    "Edith" -> 10,
    "Fred" -> 60
  )

  val favoriteColors = Map(
    "Bob" -> "green",
    "Derek" -> "magenta",
    "Fred" -> "yellow"
  )

  val favoriteLolcats = Map(
    "Alice" -> "Long Cat",
    "Charlie" -> "Ceiling Cat",
    "Edith" -> "Cloud Cat"
  )

  def favoriteColor(person: String): String =
    favoriteColors.get(person).getOrElse("beige")

  def printColors(): Unit = {
    // for ((name, color) <- favoriteColors) println(s"$name's favorite color is $color.")
    for (person <- people) println(s"$person's favorite color is ${favoriteColor(person)}!")
  }

  def lookup[VT](map: Map[String, VT], person: String): Option[VT] =
    map.get(person)

  printColors()

  val (oldestPerson, oldestAge) = ages.maxBy(_._2) // oh my god
  val colorOfOldestPerson = favoriteColor(oldestPerson)

  println(s"$oldestPerson (the oldest person at $oldestAge years old)'s favorite color is $colorOfOldestPerson!")
}
