const scalajs = require("../scalajsDist/front-fastopt");

console.log(scalajs);
console.log(scalajs.ExportToJS().func1());
scalajs.ExportToJS().fetch2("scalajsDDDSample/web/index.html")
  .then(v => console.log(v))