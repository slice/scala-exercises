class Cat(val color: String, val food: String)

val oswald = new Cat("Black", "Milk")
val henderson = new Cat("Ginger", "Chips")
val quentin = new Cat("Tabby and White", "Curry")

object ChipShop {
  def willServe(cat: Cat): Boolean =
    cat.food == "Chips"
}

ChipShop.willServe(henderson)
ChipShop.willServe(quentin)