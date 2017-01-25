package controllers

import javax.inject._

import com.github.uryyyyyyy.universal_scalajs.domain.ToDo
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import utils.JsonMapping._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class HomeController @Inject() extends Controller {

  def getToDos() = Action.async {
    Future {
      val todo = ToDo("randomID", "title", false)
      val json: JsValue = Json.toJson(Seq(todo))
      Ok(json)
    }
  }

  def putToDos() = Action.async { request =>
    val myObjOpt = for{
      json <- request.body.asJson
      myObj <- Json.fromJson[Seq[ToDo]](json).asOpt
    }yield myObj

    myObjOpt match {
      case None => println("None")
      case Some(list) => list.foreach(println)
    }

    Future {
      val json: JsValue = Json.obj(
        "result" -> "OK"
      )
      Ok(json)
    }
  }

}
