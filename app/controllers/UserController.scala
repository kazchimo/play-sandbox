package controllers

import application.useCase.user.UserIndexUseCase
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(
  cc: ControllerComponents,
  indexUseCase: UserIndexUseCase
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {
  def index: Action[AnyContent] = Action.async { implicit request =>
    indexUseCase.exec.map { users =>
      Ok(Json.toJson(users))
    }
  }
}
