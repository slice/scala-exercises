val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

// We can actually use sum here, but I'd rather not...
val res = numbers.foldLeft(0)(_ + _)
