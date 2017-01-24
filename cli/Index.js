const {ToDoReduxModule} = require("./scalajsDist/front-fastopt");

const myModule = ToDoReduxModule();
// console.log(myModule.func1());
// const todo = ToDoVO("title", false);
// console.log(todo.toString());
//
// console.log(myModule.func1());

const aa = myModule.helloWorld()
console.log(aa.name)
console.log(aa)

// const json = `
// {
//   "foo": "bar",
//   "baz": 123,
//   "list of stuff": [ 4, 5, 6 ]
// }`
//
// const json2 = {foo: "bar", baz: 123, "list of stuff": [ 4, 5, 6 ]}
//
// myModule.circeSample(JSON.stringify(json2));

// const dispatch = (a) => console.log(a);
// const actionDispatcher = myModule.createToDoActionDispatcher(dispatch)
//
// const obj = {isFinished: false, title: "haha"}
// actionDispatcher.addToDoAction(obj);
//
// // myModule.reducer({type: "ADD_TODO"})