package domain.user

import scala.concurrent.Future

trait IUserRepository {
  def all: Future[Seq[User]]
}
