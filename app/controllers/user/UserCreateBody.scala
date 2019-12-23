package controllers.user

import play.api.libs.json.{Json, Reads}

case class UserCreateBody(email: String, name: String)
object UserCreateBody {
  implicit val userCreateBodyRead: Reads[UserCreateBody] = Json.reads[UserCreateBody]
}
