package infrastructure.postgres

import domain.user.{
  IUserRepository,
  InitializingUser,
  User,
  UserEmail,
  UserFullName,
  UserId
}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import infrastructure.tables.Tables._

import scala.concurrent.{ExecutionContext, Future}

class UserPostgresRepo @Inject() (
    implicit ec: ExecutionContext,
    val dbConfigProvider: DatabaseConfigProvider
) extends IUserRepository
    with HasDatabaseConfigProvider[PostgresProfile] {
  override def all: Future[Seq[User]] = db.run(Users.result).map { users =>
    users.map { user =>
      User(UserId(user.id), UserEmail(user.email), UserFullName(user.fullname))
    }
  }

  override def create(user: InitializingUser): Future[User] = db.run {
    Users
      .returning(Users.map(_.id))
      .into((_, id) => User(UserId(id), user.email, user.fullName)) +=
      UsersRow(
        0,
        user.email.value,
        user.fullName.value
      )
  }
}
