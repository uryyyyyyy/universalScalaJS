package com.github.uryyyyyyy.universal_scalajs.front

import com.github.uryyyyyyy.universal_scalajs.front.ToDoReduxModule.MyJsTrait
import org.scalajs.dom.experimental.{Fetch, ReadableStream}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSExport, ScalaJSDefined}
import scala.scalajs.js.typedarray.Uint8Array


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
  def fetch2(url: String): js.Promise[ReadableStream[Uint8Array]] = {
    Fetch.fetch(url).toFuture.map(r => {
      println(r.body)
      r.body
    }).toJSPromise
  }

  @JSExport
  def createFunc(): js.Function2[js.UndefOr[Int], ActionBase, Int] = {
    (state: js.UndefOr[Int], action: ActionBase) => {
      state.toOption match {
        case None => 2
        case Some(x) =>
          action.`type` match {
            case "SCALA_ADD" => {
              val aaction = action.asInstanceOf[AddToDoAction]
              x + aaction.todo
            }
            case _ => x
          }
      }
    }
  }

  @JSExport
  def createToDoActionDispatcher(
                                  dispatch: js.Function1[ActionBase, js.Any],
                                  todoList: Int
                                ): ToDoActionDispatcher = {
    new ToDoActionDispatcher(dispatch, todoList)
  }

}


// ---------------
// Internal Definitions
// ---------------

class ToDoActionDispatcher(
                            val dispatch: js.Function1[ActionBase, js.Any],
                            val todoList: Int
                          ) {
  @JSExport
  def addToDoAction(_todo: Int): Unit = {
    val actionObject = new AddToDoAction {
      val todo = _todo
    }
    this.dispatch(actionObject)
  }
}

@ScalaJSDefined
trait ActionBase extends js.Object {
  val `type`: String
}

@ScalaJSDefined
trait AddToDoAction extends ActionBase {
  val `type` = "SCALA_ADD"
  val todo: Int
}

@js.native
trait ToDoJSON extends js.Object {
  val isFinished: Boolean = js.native
  val title: String = js.native
}

case class ToDoVO(id: String, title: String, isFinished: Boolean) {
  @JSExport
  def getTitle(): String = this.title

  @JSExport
  def isEqual(obj: scala.Any): Boolean = this == obj
}