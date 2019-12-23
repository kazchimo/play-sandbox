import com.google.inject.AbstractModule
import domain.user.IUserRepository
import infrastructure.postgres.UserPostgresRepo
import net.codingwell.scalaguice.ScalaModule

class Module extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[IUserRepository].to[UserPostgresRepo]
  }
}
