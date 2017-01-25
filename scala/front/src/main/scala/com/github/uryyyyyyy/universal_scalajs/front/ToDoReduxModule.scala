package com.github.uryyyyyyy.universal_scalajs.front

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSExport, ScalaJSDefined}
import scala.util.Random


@JSExport("ToDoReduxModule")
object ToDoReduxModule {

  @JSExport
  def helloWorld(): Seq[Int] = {
    js.Array(1, 2, 4).toSeq
  }

  @JSExport
  def reducer: js.Function2[js.Array[ToDoVO], ActionBase, js.Array[ToDoVO]] = {
    val scalaFun: (js.Array[ToDoVO], ActionBase) => js.Array[ToDoVO] = (state: js.Array[ToDoVO], action: ActionBase) => {
      action match {
        case a: AddTodoAction =>
          val seq = state.toSeq
          val newSeq = seq :+ a.todo
          newSeq.toJSArray
        case _ =>
          if(state == null){
            js.Array[ToDoVO]()
          }else{
            state
          }
      }
    }
    scalaFun
  }

  @JSExport
  def createToDoActionDispatcher(
    dispatch: js.Function1[ActionBase, js.Any],
    todoList: js.Array[ToDoVO]
  ): ToDoActionDispatcher = {
    new ToDoActionDispatcher(dispatch, todoList)
  }
}


// ---------------
// Internal Definitions
// ---------------

class ToDoActionDispatcher(
  val dispatch: js.Function1[ActionBase, js.Any],
  todoList: js.Array[ToDoVO]
){
  @JSExport
  def addToDoAction(_title: String): Unit ={
    val seed = new Random()
    val actionObject = new AddTodoAction{
      val todo = new ToDoVO{
        val id = seed.nextInt().toString
        val title = _title
        val isFinished = false
      }
    }
    this.dispatch(actionObject)
  }
}

sealed trait ActionBase
trait AddTodoAction extends ActionBase {
  val todo: ToDoVO
}

@ScalaJSDefined
trait ToDoVO extends js.Object {
  val id: String
  val title: String
  val isFinished: Boolean
}