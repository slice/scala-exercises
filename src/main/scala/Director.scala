package pond

case class Director(firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String = s"$firstName $lastName"
}

object Director {
  def older(a: Director, b: Director): Director =
    if (a.yearOfBirth > b.yearOfBirth) a else b
}
