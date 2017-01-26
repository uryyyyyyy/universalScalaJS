// @flow

import * as React from "react";
import {Counter} from "./ToDoList";
import {connect} from "react-redux";
import {ToDoActionDispatcher, TODO_UPDATE} from "./Actions";
// import {ToDoReduxModule} from "../../scalajsDist/front-opt"

// const myModule = ToDoReduxModule()

const mergeProps = (store:any, dispatch:any, ownProps:any) => {
  return {
    todoList: store.todoList,
    actions: new ToDoActionDispatcher(dispatch.dispatch, store.todoList, TODO_UPDATE)
  }
}

export default connect(
  (store: any) => ({todoList: store.todoList}),
  (dispatch: any) => ({dispatch: dispatch}),
  mergeProps
)(Counter);