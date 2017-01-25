const path = require('path');
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({
  extended : true
}));
app.use(bodyParser.json());

app.use('/dist', express.static('dist'));

app.get('/api/todos', (req, res) => {
  res.contentType('application/json');
  const obj = [{id: "1", title: "hello", isCompleted: false}];
  setTimeout(() => res.json(obj), 100);
  //res.status(400).json(obj); //for error testing
});

app.put('/api/todos', (req, res) => {
  res.contentType('application/json');
  console.log(req.body)
  const obj = {"result": "OK"};
  setTimeout(() => res.json(obj), 500);
});

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

app.listen(3000, (err) => {
  if (err) {
    console.log(err);
  }
  console.log("server start at port 3000")
});