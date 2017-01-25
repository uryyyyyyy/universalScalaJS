// @flow
export interface ToDoState {
  todoList: IToDo[]
}

export interface IToDo {
  id: string;
  title: string;
  isCompleted: boolean;
}

export interface ToDoAction {
  type: string;
  todoList: IToDo[];
}

export interface JsonObject {
  amount: number
}
