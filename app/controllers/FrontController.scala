package controllers

import java.io.File

import com.google.inject.Inject
import play.api.mvc._
import com.typesafe.config.{Config, ConfigFactory}

class FrontController @Inject()(env :play.Environment) extends Controller {
  val config: Config = ConfigFactory.parseFile(new File("conf/frontend.conf")).resolve()

  def index = Action {
    Ok(views.html.index.render(env, config.getInt("webpack.port")))
  }
}
