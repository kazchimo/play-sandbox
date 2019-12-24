package domains

import domain.user.{UserEmail, UserFullName, UserId}
import traits.ApplicationSpec

class UserDomainSpec extends ApplicationSpec {
  describe("UserId") {
    it("正じゃない値では作成できない") {
      val ids = Table("id", 0, -4, -5, -100, -333333333)

      forAll(ids)(UserId.create(_) shouldBe 'left)
    }
  }

  describe("UserEmail") {
    it("不正な形式の文字列では作成できない") {
      val emails = Table("email", "t.gmail.com", "@gmail.com", "test@")

      forAll(emails)(UserEmail.create(_) shouldBe 'left)
    }
  }

  describe("UserFullName") {
    it("空文字列では作成できない") {
      UserFullName.create("") shouldBe 'left
    }
  }
}
