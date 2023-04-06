const express = require("express");
const bodyParser = require("body-parser");
const app = express();
app.set("views","./templates");
app.set("view engine","pug")
app.use(express.static("public"));
app.use(bodyParser.urlencoded());
//app.[get|post|put|patch|delete].("경로",(req,res)=>{}); //라우팅
//app.[get|post|put|patch|delete|use].("경로",(req,res,next)=>{}); //미들웨어 라우팅

app.get("/",(req,res)=>{
    res.render("index")
});
const empRouter = require("./L05EmpRouter");
app.use("/emp",empRouter);





// const scott = require("./mysqlScottPool");
// app.get("/emp/list.do",async (req, res) => {
//     const [rows, f] = await scott.query("SELECT * FROM EMP");
//     res.render("empList",{empList:rows});
// })
//
// app.get("/emp/insert.do",(req,res)=>{
//     res.render("empInsert")
// })


app.listen(7777, ()=>{
    console.log("http://localhost:7777 라우터로 라우팅을 분리해보자~")
})