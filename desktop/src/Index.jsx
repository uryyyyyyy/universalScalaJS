const scalajs = require("../scalajsDist/front-fastopt");

console.log(scalajs);
console.log(scalajs.ExportToJS().func1());

scalajs.ExportToJS().fetch("http://localhost:9000/api/todo/2")
  .then((v) => console.log(v))
  .catch((err) => console.error(err));