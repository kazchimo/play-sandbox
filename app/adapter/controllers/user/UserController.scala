package adapter.controllers.user

import application.useCase.user.UserUseCase
import domain.user.{User, UserEmail, UserFullName}
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
@Api(value = "userAPI")
class UserController @Inject() (
    cc: ControllerComponents,
    useCase: UserUseCase
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  @ApiOperation(
    httpMethod = "GET",
    value = "fetch users",
    response = classOf[User]
  )
  @ApiResponses(Array(new ApiResponse(code = 400, message = "bad request")))
  def index = Action.async { implicit request =>
    useCase.index.map { users =>
      Ok(Json.toJson(users))
    }
  }

  def create = Action(parse.json[UserCreateBody]).async { implicit request =>
    val result = for {
      userEmail <- UserEmail.create(request.body.email)
      userName <- UserFullName.create(request.body.name)
    } yield useCase
      .create(userEmail, userName)
      .map { user =>
        Created(Json.toJson(user))
      }

    result.fold(e => Future.successful(BadRequest(e.message)), r => r)
  }
}
