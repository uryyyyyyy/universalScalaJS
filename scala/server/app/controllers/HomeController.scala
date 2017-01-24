package controllers

import javax.inject._

import com.github.uryyyyyyy.scalajsddd.sample.domain.ToDo
import controllers.Assets.Asset
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import utils.JsonMapping._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    Ok("Your new application is ready.")
  }

  def todo(id: Int) = Action.async {
    Future {
      val todo = ToDo(id, "title", false)
      val json: JsValue = Json.toJson(todo)
      Ok(json).withHeaders("Access-Control-Allow-Origin" -> " *")
    }
  }

  def preFright(path: Asset) = Action.async {
    Future {
      Ok("")
        .withHeaders("Access-Control-Allow-Origin" -> "*")
        .withHeaders("Access-Control-Allow-Headers" -> "content-type")
    }
  }
}
