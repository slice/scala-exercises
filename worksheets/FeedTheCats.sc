import pond.cats._

object ChipShop {
  def willServe(cat: Cat): Boolean =
    cat match {
      case Cat(_, _, "Chips") => true
      case _ => false
    }
}

ChipShop.willServe(Cat("Tabby", "Orange", "Oranges"))
ChipShop.willServe(Cat("Snobby", "Red", "Chips"))
