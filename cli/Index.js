const {ToDoReduxModule} = require("./scalajsDist/front-fastopt");

const myModule = ToDoReduxModule();

let action = null;

console.log(myModule.reducer)

const toDoActionDispatcher = myModule.createToDoActionDispatcher((a) => action = a, [])
toDoActionDispatcher.addToDoAction("title2")
const result = myModule.reducer([{id: "id1", title: "title1", isFinished: true}], action)
console.log(result)
