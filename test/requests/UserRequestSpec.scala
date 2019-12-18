package requests

import play.api.test.FakeRequest
import play.api.test.Helpers._
import traits.ApplicationSpec

class UserRequestSpec extends ApplicationSpec {
  val application = applicationBuilder.build()

  describe("GET") {
    it("200OKが返る") {
      val result = route(application, FakeRequest(GET, "/users")).get

      status(result) shouldBe OK
    }
  }
}
