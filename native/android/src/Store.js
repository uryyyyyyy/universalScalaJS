// @flow
import todoList from './todo/Reducer'
import { createStore, combineReducers } from 'redux'

export default createStore(
  combineReducers({
    todoList: todoList
  })
);