class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name = s"$firstName $lastName"
}

class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double,
           val director: Director) {
  def directorsAge: Int = director.yearOfBirth - yearOfRelease

  def isDirectedBy(supposedDirector: Director): Boolean = supposedDirector == director

  def copy(name: String = name, yearOfRelease: Int = yearOfRelease,
           imdbRating: Double = imdbRating, director: Director = director): Film =
    new Film(name, yearOfRelease, imdbRating, director)
}
