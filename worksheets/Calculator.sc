sealed trait Calc
final case class Bad(why: String) extends Calc
final case class Good(n: Double) extends Calc

sealed trait Expr {
  def eval: Calc =
    this match {
      // okay this is disgusting
      case Add(l, r) =>
        l.eval match {
          case Bad(why) => Bad(why)
          case Good(ln) =>
            r.eval match {
              case Bad(why) => Bad(why)
              case Good(rn) => Good(ln + rn)
            }
        }

      // okay this is disgusting (pt. 2)
      case Sub(l, r) =>
        l.eval match {
          case Bad(why) => Bad(why)
          case Good(ln) =>
            r.eval match {
              case Bad(why) => Bad(why)
              case Good(rn) => Good(ln - rn)
            }
        }

      // okay this is disgusting (pt. 3)
      case Div(l, r) =>
        l.eval match {
          case Bad(why) => Bad(why)
          case Good(ln) =>
            r.eval match {
              case Bad(why) => Bad(why)
              case Good(0) => Bad("can't divide by zero, dingus")
              case Good(n) => Good(ln / n)
            }
        }

      // okay this is disgusting (pt. 4)
      case SqRoot(n) =>
        n.eval match {
          case Bad(why) => Bad(why)
          case Good(n) if n < 0 => Bad("can't square root a negative, dingus")
          case Good(n) => Good(Math.sqrt(n))
        }

      case Const(n) => Good(n)
    }
}

final case class Add(l: Expr, r: Expr) extends Expr
final case class Sub(l: Expr, r: Expr) extends Expr
final case class Div(l: Expr, r: Expr) extends Expr
final case class Const(n: Double) extends Expr
final case class SqRoot(n: Expr) extends Expr

object Main extends App {
  val three = Add(Const(1.0), Const(2.0))
  assert(three.eval == Good(3.0))

  assert(Add(SqRoot(Const(-1.0)), Const(2.0)).eval == Bad("can't square root a negative, dingus"))
  assert(Add(SqRoot(Const(4.0)), Const(2.0)).eval == Good(4.0))
  assert(Div(Const(4), Const(0)).eval == Bad("can't divide by zero, dingus"))
}
