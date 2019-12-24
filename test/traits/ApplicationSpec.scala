package traits

import com.typesafe.config.ConfigFactory
import infrastructure.tables.Tables
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfter, EitherValues, FunSpec, OptionValues}
import play.api.Configuration
import play.api.db.DBApi
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.API

trait ApplicationSpec
    extends FunSpec
    with org.scalatest.Matchers
    with BeforeAndAfter
    with TableDrivenPropertyChecks
    with ScalaFutures // Futureへの簡易アクセッサ
    with OptionValues // Optionの簡易アクセッサ
    with EitherValues // Eitherの簡易アクセッサ
    with Tables
    with API {
  val applicationBuilder: GuiceApplicationBuilder = GuiceApplicationBuilder(
    configuration = Configuration(ConfigFactory.load("application.test.conf"))
  )

  val application = applicationBuilder.build()
  val dbapi = application.injector.instanceOf[DBApi].database("default")
  val db = application.injector
    .instanceOf[DatabaseConfigProvider]
    .get[PostgresProfile]
    .db
  override val profile = slick.jdbc.PostgresProfile
}
