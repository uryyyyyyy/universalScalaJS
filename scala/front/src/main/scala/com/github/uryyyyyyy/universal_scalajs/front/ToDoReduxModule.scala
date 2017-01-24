package com.github.uryyyyyyy.universal_scalajs.front

import com.github.uryyyyyyy.universal_scalajs.domain.ToDo
import org.scalajs.dom.experimental.{Fetch, ReadableStream}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSExport, ScalaJSDefined}
import scala.scalajs.js.typedarray.Uint8Array
import io.circe._
import io.circe.parser._


// ---------------
// Entry Point
// ---------------

@JSExport("ToDoReduxModule")
object ToDoReduxModule {

  @ScalaJSDefined
  trait MyJsTrait extends js.Object {
    val name: String
    val id: Int
  }

  @JSExport
  def helloWorld(): js.Object = {
    new MyJsTrait {
      val id = 5
      val name = "haha"
    }
  }

  @JSExport
  def reducer(
    state :js.Array[ToDoVO],
    action: ActionBase
  ): js.Array[ToDoVO] = {
    if(state == null) return js.Array.apply()// initialState

    if(action.`type` == "ADD_TODO"){
      state
    }else{
      state
    }
  }

  @JSExport
  def fetch2(url :String): js.Promise[ReadableStream[Uint8Array]] = {
    Fetch.fetch(url).toFuture.map(r => {
      println(r.body)
      r.body
    }).toJSPromise
  }

  @JSExport
  def createToDoActionDispatcher(
    dispatch: js.Function1[ActionBase, js.Any]
    //todoList: List[ToDo]
  ): ToDoActionDispatcher = {
    new ToDoActionDispatcher(dispatch)
  }

  @JSExport
  def circeSample(rawJson: String): Unit = {
    val parseResult = parse(rawJson)
    println(parseResult)
  }


}


// ---------------
// Internal Definitions
// ---------------

class ToDoActionDispatcher(
  val dispatch: js.Function1[ActionBase, js.Any]
){
  @JSExport
  def addToDoAction(todo: ToDoJSON): Unit ={
    val actionObject = js.Dynamic.literal(`type` = "ADD_TODO", todo = todo).asInstanceOf[ActionBase]
    this.dispatch(actionObject)
  }
}

@js.native
trait ActionBase extends js.Object {
  val `type`: String = js.native
}

@js.native
trait ToDoJSON extends js.Object {
  val isFinished: Boolean = js.native
  val title: String = js.native
}

case class ToDoVO(id: String, title: String, isFinished: Boolean){
  @JSExport
  def getTitle():String = this.title

  @JSExport
  def isEqual(obj: scala.Any): Boolean = this == obj
}