package application.useCase.user

import domain.user.{
  IUserRepository,
  InitializingUser,
  User,
  UserEmail,
  UserFullName
}
import javax.inject.Inject

import scala.concurrent.Future

class UserUseCase @Inject() (repository: IUserRepository) {
  def index: Future[Seq[User]] = repository.all

  def create(email: UserEmail, name: UserFullName): Future[User] =
    repository.create(InitializingUser(email, name))
}
