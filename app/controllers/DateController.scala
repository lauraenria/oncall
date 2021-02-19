package controllers

import models.{Date, User}

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class DateController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  val laura = User("Laura Enria")
  val today = Date("19-2-2021")

  def info(date: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(formatInfo(laura.name, date))
  }

  def formatInfo(user: String, date: String): String = {
    s"${user}, ${date}"
  }
}
