package domain.user

import scala.concurrent.Future

trait IUserRepository {
  def all: Future[Seq[User]]
  def create(user: InitializingUser): Future[User]
}
