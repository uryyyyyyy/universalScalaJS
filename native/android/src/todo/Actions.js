// @flow

import {IToDo} from "./Entities";
export const TODO_UPDATE: string = 'todo/update';

const myHeaders = {
  "Content-Type": "application/json",
  'Accept': 'application/json'
};

export class ToDoActionDispatcher {
  dispatch: (action: any) => any;
  todoList: IToDo[];
  actionType: String;

  constructor(
    dispatch: (action: any) => any,
    todoList: IToDo[],
    actionType: String
  ) {
    this.dispatch = dispatch
    this.todoList = todoList
    this.actionType = actionType
  }

  addToDo(title: string) {
    const newId = Math.random().toString(36).slice(-8)
    const newToDo: IToDo = {id: newId, title: title, isCompleted: false}
    const newToDoList = this.todoList.concat(newToDo)
    this.dispatch({type: this.actionType, todoList: newToDoList})
  }

  complete(id: string) {
    const newToDoList = this.todoList
      .map((todo) => (todo.id !== id) ? todo : Object.assign({}, todo, {isCompleted:true}))
    this.dispatch({type: this.actionType, todoList: newToDoList})
  }

  deleteToDo(id: string) {
    const newToDoList = this.todoList
      .filter((todo) => todo.id !== id)
    this.dispatch({type: this.actionType, todoList: newToDoList})
  }

  async fetchFromServer(): Promise<void> {
    try {
      const response: Response = await fetch('http://localhost:3000/api/todos', {
        method: 'GET',
        headers: myHeaders
      });

      if (response.status === 200) { //2xx
        const json: IToDo[] = await response.json();
        this.dispatch({type: this.actionType, todoList: json})
      } else {
        throw new Error(`illegal status code: ${response.status}`);
      }
    } catch (err) {
      console.error(err);
    }
  }

  async saveToServer(): Promise<void> {
    try {
      const response: Response = await fetch('http://localhost:3000/api/todos', {
        method: 'PUT',
        headers: myHeaders,
        body: JSON.stringify(this.todoList)
      });

      if (response.status === 200) { //2xx
        /*do something*/
      } else {
        throw new Error(`illegal status code: ${response.status}`);
      }
    } catch (err) {
      console.error(err.message);
    }
  }
}