package domain.user

import domain.helpers.{LongGInt, NonEmptyStringFactory, PositiveValueFactory, ValueObjectFactory}
import play.api.libs.json.{Json, Writes}

case class User(id: UserId, email: UserEmail, fullName: UserFullName)
case class InitializingUser(email: UserEmail, fullName: UserFullName)
object User {
  implicit def userWrites: Writes[User] =
    (user: User) =>
      Json.obj("name" -> user.fullName.value, "email" -> user.email.value)
}

case class UserId(value: Long) extends AnyVal
object UserId extends PositiveValueFactory[Long, UserId] with LongGInt

case class UserEmail(value: String) extends AnyVal
object UserEmail extends ValueObjectFactory[String, UserEmail] {
  override def isValid(value: String): Boolean =
    """(\w+)@([\w\.]+)""".r.unapplySeq(value).isDefined

  override def errorMessage(value: String): String =
    s"$className requires valid email string: $value"
}

case class UserFullName(value: String) extends AnyVal
object UserFullName extends NonEmptyStringFactory[UserFullName]
