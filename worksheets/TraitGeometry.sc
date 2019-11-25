sealed trait Shape {
  def sides: Int

  def perimeter: Double

  def area: Double
}

sealed trait Rectangular extends Shape {
  def length: Double
  def width: Double
  override val sides = 4
  override def perimeter = 2 * length + 2 * width
  override def area = length * width
}

final case class Circle(radius: Int) extends Shape {
  val sides = 1 // :thinking:
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

final case class Rectangle(length: Double, width: Double) extends Rectangular

final case class Square(size: Double) extends Rectangular {
  val length = size
  val width = size
}

