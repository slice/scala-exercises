val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val func = numbers.foldLeft(List[Int]())(_)

val squares = func((xs, x) => xs :+ x * x)
val cubes = func((xs, x) => xs :+ x * x * x)
