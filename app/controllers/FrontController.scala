package controllers

import com.google.inject.Inject
import play.api.mvc._

class FrontController @Inject()(env :play.Environment) extends Controller {
  def index = Action {
    Ok(views.html.index.render(env))
  }
}
