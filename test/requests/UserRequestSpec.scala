package requests

import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.db.slick.DatabaseConfigProvider
import play.api.test.FakeRequest
import play.api.test.Helpers._
import slick.jdbc.PostgresProfile
import traits.ApplicationSpec

trait UserRequestSpecContext { self: ApplicationSpec =>
  val seedAction = DBIO.seq(
    Users forceInsert UsersRow(1, "taro@gmail.com", "taro"),
    Users forceInsert UsersRow(2, "hanako@gmail.com", "hanako")
  )

  val requestPath = "/users"
}

class UserRequestSpec extends ApplicationSpec with UserRequestSpecContext {
  val application = applicationBuilder.build()
  val dbapi = application.injector.instanceOf[DBApi].database("default")
  val db = application.injector
    .instanceOf[DatabaseConfigProvider]
    .get[PostgresProfile]
    .db

  before {
    Evolutions.cleanupEvolutions(dbapi)
    Evolutions.applyEvolutions(dbapi)
    db.run(seedAction).futureValue
  }

  describe("GET") {
    it("200OKが返る") {
      val result = route(application, FakeRequest(GET, requestPath)).get

      status(result) shouldBe OK
    }

    it("userデータが２つ返る") {
      val result = route(application, FakeRequest(GET, requestPath)).get

      status(result) shouldBe OK
    }
  }
}
