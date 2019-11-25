//class Counter(var count: Int) {
//  def inc: Counter = {
//    count += 1
//    this
//  }
//
//  def dec: Counter = {
//    count -= 1
//    this
//  }
//}

//class Counter(val count: Int) {
//  def inc: Counter = inc()
//  def dec: Counter = dec()
//  def inc(n: Int = 1) = new Counter(count + n)
//  def dec(n: Int = 1) = new Counter(count - n)
//
//  def adjust(adder: Adder) =
//    new Counter(adder.add(count))
//}

case class Counter(count: Int) {
  def inc: Counter = inc()
  def dec: Counter = dec()
  def inc(n: Int = 1): Counter = copy(count + n)
  def dec(n: Int = 1): Counter = copy(count - n)
  def adjust(adder: Adder): Counter =
    copy(adder.add(count))
}

class Adder(amount: Int) {
  def add(in: Int) = in + amount
}

new Counter(10).inc.dec.inc.inc.count
new Counter(50).adjust(new Adder(5)).count