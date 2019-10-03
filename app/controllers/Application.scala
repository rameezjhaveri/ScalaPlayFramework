package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("MY APP NOW"))
  }

  def displayName(name:String):Action[AnyContent] = Action { implicit request:
  Request[AnyContent] =>
    Ok(views.html.displayName(name)).withSession("connected" -> name)
  }

  def serverDown = Action {
    InternalServerError("GODDAMM SERVER BROKE!!")
  }

  def removeSession = Action { request =>
    Ok("Logged Out").withSession("notConnected" -> "")
  }

  def accessWithSession = Action { request =>
    request.session
      .get("connected")
      .map {user =>
        Ok("Hello " + user)
      }
      .getOrElse{
        Unauthorized("SOZ. NOT ALLOWED MATE")
      }
  }
}