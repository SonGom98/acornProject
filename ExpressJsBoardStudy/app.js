var createError = require('http-errors'); //미들웨어로 http 에러 상태 (status) 메세지 통합 작성 가능
var express = require('express');
var path = require('path'); //파일의 절대경로 검색시 사용됨 __dirname 프로젝트 폴더
var cookieParser = require('cookie-parser'); //서버가 브라우저에게 남기는 데이터 cookie 를 사용하는 모듈
var logger = require('morgan'); //서비스(웹앱)을 배포하면 유지보수시 로그검색을 해야하는데 로그를 파일로 저장하는 모듈(==log4j)

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views')); //절대 경로로 pug 위치 등록
//app.set('views', 'views'); //상대 경로로 pug 위치 등록
app.set('view engine', 'pug');

app.use(logger('dev')); //morgan 설정
app.use(express.json()); //queryString 이 json 일떄 처리   name=val&name=val2 => {name:val,name:val}
app.use(express.urlencoded({ extended: false })); //body-parser
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  //res.locals : 퍼그의 변수 등록!
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error'); // views/error.pug
});

app.listen(9999,()=>{
  console.log("http://localhost:9999 expressjs 로 게시판 만들기")
})

module.exports = app;
