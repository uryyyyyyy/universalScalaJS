package com.github.uryyyyyyy.universal_scalajs.front

import org.scalajs.dom.experimental._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, ScalaJSDefined}
import scala.util.Random


// ---------------
// Entry Point
// ---------------

@JSExport("ToDoReduxModule")
object ToDoReduxModule {

  @JSExport
  def createToDoActionDispatcher(
    dispatch: js.Function1[ToDoAction, js.Any],
    todoList: js.Array[ToDoVO],
    actionType: String
  ): ToDoActionDispatcher = {
    new ToDoActionDispatcher(dispatch, todoList, actionType)
  }

}


// ---------------
// Internal Definitions
// ---------------

class ToDoActionDispatcher(
  val dispatch: js.Function1[ToDoAction, js.Any],
  val _todoList: js.Array[ToDoVO],
  val actionType: String
) {
  @JSExport
  def addToDo(_title: String): Unit = {
    val newId = new Random().nextInt(10000).toString
    val newToDo = new ToDoVO {
      val isCompleted: Boolean = false
      val title: String = _title
      val id: String = newId
    }
    val newToDList = _todoList.toSeq.:+(newToDo).toJSArray
    val actionObject = new ToDoAction {
      val `type` = actionType
      val todoList = newToDList
    }
    this.dispatch(actionObject)
  }

  @JSExport
  def complete(_id: String): Unit = {
    val newList = _todoList.toSeq.map(todo => {
      if(todo.id != _id) todo else new ToDoVO {
        val isCompleted: Boolean = true
        val title: String = todo.title
        val id: String = todo.id
      }
    }).toJSArray
    val actionObject = new ToDoAction {
      val `type` = actionType
      val todoList = newList
    }
    this.dispatch(actionObject)
  }

  @JSExport
  def deleteToDo(_id: String): Unit = {
    val newList = _todoList.toSeq.filter(todo => todo.id != _id).toJSArray
    val actionObject = new ToDoAction {
      val `type` = actionType
      val todoList = newList
    }
    this.dispatch(actionObject)
  }


  @JSExport
  def fetchFromServer(): js.Promise[Any] = {
    val reqInfo: RequestInfo = new Request(
      "http://localhost:3000/api/todos",
      RequestInit(
        method = HttpMethod.GET,
        headers = js.Dictionary[ByteString](("Content-Type", "application/json"), ("Accept", "application/json"))
      )
    )

    Fetch.fetch(reqInfo).toFuture.flatMap(r => r.json().toFuture)
      .map(r => {
        val result = r.asInstanceOf[js.Array[ToDoVO]]
        val actionObject = new ToDoAction {
          val `type` = actionType
          val todoList = result
        }
        this.dispatch(actionObject)
      }).toJSPromise
  }

  @JSExport
  def saveToServer(): js.Promise[Any] = {
    val reqInfo: RequestInfo = new Request(
      "http://localhost:3000/api/todos",
      RequestInit(
        method = HttpMethod.PUT,
        body = JSON.stringify(_todoList),
        headers = js.Dictionary[ByteString](("Content-Type", "application/json"), ("Accept", "application/json"))
      )
    )

    Fetch.fetch(reqInfo).toFuture.flatMap(r => r.json().toFuture)
      .toJSPromise
  }
}

@ScalaJSDefined
trait ToDoAction extends js.Object {
  val `type`: String
  val todoList: js.Array[ToDoVO]
}

@ScalaJSDefined
trait ToDoVO extends js.Object {
  val id: String
  val title: String
  val isCompleted: Boolean
}
