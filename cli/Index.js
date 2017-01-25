const {ToDoReduxModule} = require("./scalajsDist/front-fastopt");

const myModule = ToDoReduxModule();
const aa = myModule.createFunc()

let action = null;

const dispatch = a => action = a

const ad = myModule.createToDoActionDispatcher(dispatch, 10)

ad.addToDoAction(3)
console.log(action)

const result = aa(10, action)
console.log(result)