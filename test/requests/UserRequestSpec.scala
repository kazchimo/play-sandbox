package requests

import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import slick.jdbc.PostgresProfile
import traits.ApplicationSpec

trait UserRequestSpecContext { self: ApplicationSpec =>
  val seedAction = DBIO.seq(
    Users forceInsert UsersRow(1, "taro@gmail.com", "taro"),
    Users forceInsert UsersRow(2, "hanako@gmail.com", "hanako")
  )

  val createJson = Json.obj("email" -> "test@gmail.com", "name" -> "taro")

  val requestPath = "/users"
}

class UserRequestSpec extends ApplicationSpec with UserRequestSpecContext {
  val application = applicationBuilder.build()
  val dbapi = application.injector.instanceOf[DBApi].database("default")
  val db = application.injector
    .instanceOf[DatabaseConfigProvider]
    .get[PostgresProfile]
    .db
  def seed = db.run(seedAction).futureValue

  before {
    Evolutions.cleanupEvolutions(dbapi)
    Evolutions.applyEvolutions(dbapi)
  }

  describe("GET") {
    it("200OKが返る") {
      seed
      val result = route(application, FakeRequest(GET, requestPath)).get

      status(result) shouldBe OK
    }

    it("userデータが２つ返る") {
      seed
      val result = route(application, FakeRequest(GET, requestPath)).get

      status(result) shouldBe OK
    }
  }

  describe("CREATE") {
    def request =
      route(
        application,
        FakeRequest(POST, requestPath)
          .withJsonBody(createJson)
      ).get

    it("201Cratedが返る") {
      status(request) shouldBe CREATED
    }

    it("正しくレコードが作成される") {
      request.futureValue
      val dbUser = db.run(Users.take(1).result).futureValue.head

      dbUser.email shouldBe (createJson \ "email").as[String]
      dbUser.fullname shouldBe (createJson \ "name").as[String]
    }
  }
}
