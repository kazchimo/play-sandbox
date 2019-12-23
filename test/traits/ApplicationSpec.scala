package traits

import com.typesafe.config.ConfigFactory
import infrastructure.tables.Tables
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfter, FunSpec}
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder
import slick.jdbc.PostgresProfile.API

trait ApplicationSpec
    extends FunSpec
    with ScalaFutures
    with org.scalatest.Matchers
    with BeforeAndAfter
    with Tables
    with API {
  val applicationBuilder: GuiceApplicationBuilder = GuiceApplicationBuilder(
    configuration = Configuration(ConfigFactory.load("application.test.conf"))
  )

  override val profile = slick.jdbc.PostgresProfile
}
