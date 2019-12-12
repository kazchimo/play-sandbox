package application.useCase.user

import domain.user.{IUserRepository, User}
import javax.inject.Inject

import scala.concurrent.Future

class UserIndexUseCase @Inject()(repository: IUserRepository) {
  def exec: Future[Seq[User]] = repository.all
}
