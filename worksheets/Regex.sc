val numberRegex = "[0-9]".r

numberRegex.findFirstMatchIn("awesomepassword") match {
  case Some(_) => println("Password OK!")
  case None => println("Bad password...")
}
