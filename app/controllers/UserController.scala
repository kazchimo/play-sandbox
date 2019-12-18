package controllers

import application.useCase.user.UserIndexUseCase
import domain.user.User
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "userAPI")
class UserController @Inject()(
  cc: ControllerComponents,
  indexUseCase: UserIndexUseCase
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  @ApiOperation(
    httpMethod = "GET",
    value = "fetch users",
    response = classOf[User]
  )
  @ApiResponses(Array(new ApiResponse(code = 400, message = "bad request")))
  def index = Action.async { implicit request =>
    indexUseCase.exec.map { users =>
      Ok(Json.toJson(users))
    }
  }
}
