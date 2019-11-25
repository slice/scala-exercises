object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}

println(argh.c + argh.b + argh.a)

// Printing:
// b
// a
// c
// a
// a
// 3c31

// Explanation:
// "b", is a *val*, not a def, meaning that it's evaluated once the object is
// loaded (not upon definition because of lazy loading), and the value is stored
// in the value. that means that "b" only ever gets printed once!
