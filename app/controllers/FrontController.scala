package controllers

import com.google.inject.Inject
import play.api.mvc._

/**
  * Created by guillaume on 05/05/17.
  */
class FrontController @Inject()(env :play.Environment) extends Controller {
  def index = Action {
    Ok(views.html.index.render(env))
  }
}
