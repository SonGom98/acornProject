const express = require("express");
//const pug=require("pug");
const fs = require("fs/promises");
const app = express();

//app.set("views","./views"); //default 로 pug 의 경로를 views 로 설정
app.set("view engine", "pug"); //default 로 pug 의 경로를 views 로 설정!

app.use(express.static("public")); //urlEncoding 처리가 되어 있음!
// app.get("/public/*",async (req,res)=>{
//     let data = await fs.readFile("."+req.path);
//     res.send(data);
// })

//url 문자는 아스키코드만 참조?? 1byte 로 표현할 수 있는 문자표!
//1byte => 데이터 전송과 저장하는 주소의 기본단위! => 최초의 문자표를 아스키코드로 생성

app.get("/", (req,resp)=>{
    // let html=pug.renderFile("./views/index.pug")
    // resp.write(html);
    // resp.end();
    resp.render("index",{a:10,b:20});
})

const querystring=require("querystring");
app.post("/signup.do",(req,res)=>{
    console.log(req.query.id); //url 에 포함된 id 파라미터
    //res.send(req.query.id+"님 회원가입 완료!")
    //req.on("data",()=>{}) : 요청헤더의 쿼리스트링을 읽어오는 이벤트
    let bodyQueryString="";
    req.on("data",(data)=>{
        bodyQueryString+=data;
    });
    req.on("end",()=>{
        const params=querystring.parse(bodyQueryString);
        res.send(JSON.stringify(params));
    })
});

const bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({extended:false}));
//req.body 필드가 추가되고 쿼리스트링을 오브젝트로 파싱하고 있다.
//extended:false    : nodejs 제공하는 query-string 을 사용해서 파싱하겠다.

app.post("/signup2.do",(req,res)=>{
    res.send(JSON.stringify(req.body))
})


app.listen(7777,()=>{
    console.log("http://localhost:7777 미들웨어로 bodyParser 적용")
})
