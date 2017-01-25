// @flow
import {DECREMENT, INCREMENT, FETCH_REQUEST_START, FETCH_REQUEST_FINISH, TODO_UPDATE} from "./Actions";
import {CounterState, MyAction, ToDoState, ToDoAction} from "./Entities";

const initialState: ToDoState = [];

export default function reducer(state: ToDoState = initialState, action: ToDoAction): ToDoState {
  switch (action.type) {
    case TODO_UPDATE: {
      return action.todoList;
    }
    default:
      return state
  }
}