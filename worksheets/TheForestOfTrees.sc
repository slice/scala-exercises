sealed trait Tree {
  // these methods are implemented using pattern matching on the base trait,
  // but we could've opted to do polymorphism (defining each method inside of
  // the actual case class) as well.

  def sum: Int =
    this match {
      case Node(left, right) => left.sum + right.sum
      case Leaf(value) => value
    }

  def double: Tree =
    this match {
      case Node(left, right) => Node(left.double, right.double)
      case Leaf(value) => Leaf(value * 2)
    }
}

final case class Node(left: Tree, right: Tree) extends Tree
final case class Leaf(value: Int) extends Tree

object Main extends App {
  val tree = Node(Node(Leaf(1), Leaf(2)), Leaf(3))
  assert(tree.sum == 6)
  assert(tree.double == Node(Node(Leaf(2), Leaf(4)), Leaf(6)))
}
