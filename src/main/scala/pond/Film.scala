package pond

case class Film(name: String, yearOfRelease: Int, imdbRating: String, director: Director) {
  def directedAtAge: Int = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director): Boolean = this.director == director
}

object Film {
  def newer(a: Film, b: Film): Film =
    if (a.yearOfRelease > b.yearOfRelease) a else b

  def highestRating(a: Film, b: Film): Film =
    if (a.imdbRating > b.imdbRating) a else b

  def oldestDirectorAtTheTime(a: Film, b: Film): Director =
    if (a.directedAtAge > b.directedAtAge) a.director else b.director
}