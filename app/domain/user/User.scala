package domain.user

import play.api.libs.json.{Json, Writes}

case class User(id: UserId, email: UserEmail, fullName: UserFullName)
case class InitializingUser(email: UserEmail, fullName: UserFullName)
object User {
  implicit def userWrites: Writes[User] =
    (user: User) =>
      Json.obj("name" -> user.fullName.value, "email" -> user.email.value)
}

case class UserId(value: Long) extends AnyVal
case class UserEmail(value: String) extends AnyVal
case class UserFullName(value: String) extends AnyVal
