package traits

import com.typesafe.config.ConfigFactory
import org.scalatest.FunSpec
import org.scalatest.concurrent.ScalaFutures
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder

trait ApplicationSpec
    extends FunSpec
    with ScalaFutures
    with org.scalatest.Matchers {
  val applicationBuilder: GuiceApplicationBuilder = GuiceApplicationBuilder(
    configuration = Configuration(ConfigFactory.load("application.test.conf"))
  )
}
