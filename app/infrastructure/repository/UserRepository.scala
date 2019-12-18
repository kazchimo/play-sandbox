package infrastructure.repository

import domain.user.{IUserRepository, User, UserEmail, UserFullName, UserId}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import infrastructure.tables.Tables._

import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject() (
    implicit ec: ExecutionContext,
    val dbConfigProvider: DatabaseConfigProvider
) extends IUserRepository
    with HasDatabaseConfigProvider[PostgresProfile] {
  override def all: Future[Seq[User]] = db.run(Users.result).map { users =>
    users.map { user =>
      User(UserId(user.id), UserEmail(user.email), UserFullName(user.fullname))
    }
  }
}
