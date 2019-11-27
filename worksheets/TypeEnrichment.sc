object Main extends App {
  implicit class ExtraStringMethods(str: String) {
    val vowels = Seq('a', 'e', 'i', 'o', 'u')

    def numberOfVowels: Int =
      str.filter(vowels contains _).length
  }

  // manually wrapping
  assert(new ExtraStringMethods("the quick brown fox").numberOfVowels == 5)

  // when the class is implicit, the constructor call can be automatically
  // inserted
  assert("the quick brown fox".numberOfVowels == 5)
}
