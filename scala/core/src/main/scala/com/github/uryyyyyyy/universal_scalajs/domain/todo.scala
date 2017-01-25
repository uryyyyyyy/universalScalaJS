package com.github.uryyyyyyy.universal_scalajs.domain

case class ToDo(id: String, title: String, isCompleted: Boolean)

//object ToDoDomainService{
//
//  def addToDo(todoList: List[ToDo], newToDo: ToDo): List[ToDo] = {
//    todoList :+ newToDo
//  }
//
//  def removeToDoByIndex(todoList: List[ToDo], index: Int): List[ToDo] = {
//    todoList.drop(index)
//  }
//
//  def finishToDo(todo: ToDo): ToDo = {
//    todo.copy(isFinished = true)
//  }
//
//  def unfinishToDo(todo: ToDo): ToDo = {
//    todo.copy(isFinished = false)
//  }
//
//  def serializeAsJson(todo: ToDo): String = {
//    "" //TODO
//  }
//}