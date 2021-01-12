var proxy = require('express-http-proxy');
var express = require('express');
var app = express();

const port = 8000;

app.use(express.static('public'))

app.listen(port, () => {
  console.log('App listening at http://localhost:' + port)
})

//Local
app.use('/api', proxy('http://localhost:8081'));

//Docker
//app.use('/api', proxy('http://10.0.2.15:8081'));


