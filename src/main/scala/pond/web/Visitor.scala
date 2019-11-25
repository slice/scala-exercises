package pond.web

import java.util.Date

trait Visitor {
  def id: String

  def createdAt: Date

  def age: Long = new Date().getTime - createdAt.getTime

  def olderThan(that: Visitor): Boolean = this.createdAt.before(that.createdAt)

  def youngerThan(that: Visitor): Boolean = this.createdAt.after(that.createdAt)
}
