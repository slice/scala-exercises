// JSON ::= String value:String
//          Number value:Double
//          Boolean value:Boolean
//          Object
//          Array
//
// Object ::= Entry key:String value:JSON tail:Object
//          | End
//
// Array ::= Pair head:JSON tail:Array
//         | End

sealed trait JSON {
  def print: String = {
    def quote(s: String): String = '"'.toString + s + '"'.toString

    def printObject(o: JSONObjectEntry): String =
      o match {
        case JSONObjectEntry(key, value, JSONObjectEnd) =>
          s"${key.print}: ${value.print}"
        case JSONObjectEntry(key, value, tail @ JSONObjectEntry(_, _, _)) =>
          s"${key.print}: ${value.print}, ${printObject(tail)}"
      }

    def printArray(a: JSONArrayPair): String =
      a match {
        case JSONArrayPair(value, JSONArrayEnd) =>
          value.print
        case JSONArrayPair(value, tail @ JSONArrayPair(_, _)) =>
          s"${value.print}, ${printArray(tail)}"
      }

    this match {
      case JSONNull => "null"
      case JSONString(s) => quote(s)
      case JSONNumber(v) => v.toString
      case JSONBoolean(b) => b.toString

      case o @ JSONObjectEntry(_, _, _) => "{" + printObject(o) + "}"
      case JSONObjectEnd => "{}"

      case a @ JSONArrayPair(_, _) => "[" + printArray(a) + "]"
      case JSONArrayEnd => "[]"
    }
  }
}

case object JSONNull extends JSON
final case class JSONString(value: String) extends JSON
final case class JSONNumber(value: Double) extends JSON
final case class JSONBoolean(value: Boolean) extends JSON

sealed trait JSONObject extends JSON
final case class JSONObjectEntry(key: JSONString, value: JSON, tail: JSONObject) extends JSONObject
final case object JSONObjectEnd extends JSONObject

sealed trait JSONArray extends JSON
final case class JSONArrayPair(head: JSON, tail: JSONArray) extends JSONArray
final case object JSONArrayEnd extends JSONArray

object Main extends App {
  val value =
    JSONObjectEntry(JSONString("name"), JSONString("slice"),
    JSONObjectEntry(JSONString("age"), JSONNumber(-1),
    JSONObjectEntry(JSONString("interests"),
      JSONArrayPair(JSONString("programming"),
      JSONArrayPair(JSONString("music"),
      JSONArrayPair(JSONString("cool stuff"),
      JSONArrayEnd))),
    JSONObjectEnd)))
  println(value.print)
}
