const http = require("http");
const url = require("url");
const querystring = require("querystring");
const fs = require("fs");
const mysql = require("mysql2");
const pug = require("pug");

const server=http.createServer();
server.listen(8888,()=>{
    console.log("http://localhost:8888 DEPT CRUD 를 저장하는 서버")
});
const mysqlConInfo={
    host : "localhost",
    port : 3306,
    user : "root",
    password : "mysql123",
    database : "scott"
}
const createPool = mysql.createPool(mysqlConInfo);
const pool = createPool.promise();
server.on("request", async (req,res)=>{
    const urlObj = url.parse(req.url);
    const params = querystring.parse(urlObj.query);
    const urlSplits = urlObj.pathname.split("/");
    function errmsg (){
        res.statusCode=404;
        res.setHeader("content-type","text/html;charset=UTF-8")
        res.write("<h1>404 오류 : 존재하지 않는 페이지 입니다.</h1>");
        res.end();
    }

    if(urlObj.pathname==="/"){
        let html = pug.renderFile("./templatesDept/index.pug");
        res.write(html);
        res.end();
    }else if(urlObj.pathname==="/deptList.do"){
        try{
            const [rows,f] = await pool.query("SELECT * FROM DEPT");
            let html = pug.renderFile("./templatesDept/deptList.pug",{deptList:rows});
            res.write(html);
            res.end();
        }catch (e){
            console.error(e);
        }
    }else if(urlObj.pathname==="/deptDetail.do"){
        let deptno = Number(params.deptno);
        if(Number.isNaN(deptno)){
            res.statusCode=400;
            res.write("<h1>400 오류 : 파라미터 전달 X</h1>");
            res.end();
            return;
        }
        let sql = "SELECT * FROM EMP WHERE DEPTNO=? ORDER BY DEPTNO";
        const [rows, f] = await pool.query(sql,[deptno]);
        let html = pug.renderFile("./templatesDept/deptDetail.pug", {empList:rows});
        res.write(html);
        res.end();
    }else if(urlObj.pathname==="/deptUpdate.do"&&req.method==="GET") {
        let deptno = Number(params.deptno);
        if (Number.isNaN(deptno)) {
            res.statusCode = 400;
            res.write("<h1>400 오류 : 파라미터 전달 X</h1>");
            res.end();
            return;
        }
        let sql = "SELECT * FROM DEPT WHERE DEPTNO=?";
        const [rows, f] = await pool.query(sql,[deptno]);
        let html = pug.renderFile("./templatesDept/deptUpdate.pug", {d: rows[0]});
        res.write(html);
        res.end();
    }
    else if(urlObj.pathname==="/deptUpdate.do"&&req.method==="POST") {
        let postquery = "";
        let update = 0;
        req.on("data", (param) => {
            postquery += param;
        });
        req.on("end", async () => {
            const postPs = querystring.parse(postquery);
            try {
                let sql = `UPDATE DEPT
                           SET DNAME=?,
                               LOC=?
                           WHERE DEPTNO = ?`
                const [result] = await pool.execute(sql, [postPs.dname, postPs.loc, postPs.deptno]);
                console.log(result)
                update = result.affectedRows;
            } catch (e) {
                console.error(e);
            }
            if (update > 0) {
                res.writeHead(302, {location: "/deptList.do"});
                res.end();
            } else {
                res.writeHead(302, {location: "/deptUpdate.do?deptno=" + postPs.deptno});
                res.end();
            }
        });
    }else if(urlObj.pathname==="/deptInsert.do"&&req.method==="GET") {
        let html = pug.renderFile("./templatesDept/deptInsert.pug");
        res.write(html);
        res.end();
    }
    else if(urlObj.pathname==="/deptInsert.do"&&req.method==="POST"){
        let postQuery = "";
        req.on("data",(p)=>{
            postQuery+=p;
        });
        req.on("end",async ()=>{
            const postPs = querystring.parse(postQuery);
            for(let key in postPs){
                if(postPs[key].trim()==="")postPs[key]=null;
            }
            let sql = `INSERT INTO DEPT (DEPTNO, DNAME, LOC) VALUE (?,?,?)`
            let insert = 0;
            let duplicate = 0;
            try{
                const [result] = await pool.execute(sql,[postPs.deptno,postPs.dname,postPs.loc]);
                insert = result.affectedRows;
            }catch(e){
                console.error(e);
            }
            if(insert>0){
                res.writeHead(302,{location:"/deptList.do"});
                res.end();
            }else{
                res.writeHead(302,{location:"/deptInsert.do"});
                res.end();
            }
        })
    }else if(urlObj.pathname==="/deptDelete.do"){
        let deptno=Number(params.deptno);
        let sql="DELETE FROM DEPT WHERE DEPTNO=?";
        let del=0;
        try{
            const [result] = await pool.execute(sql,[deptno]);
            del = result.affectedRows;
        }catch (e){
            console.error(e);
        }
        if(del>0){
            res.writeHead(302,{location:"/deptList.do"});
            res.end();
        }else{
            res.writeHead(302,{location:"/deptUpdate.do"});
            res.end();
        }
    }
    else{
        errmsg();
    }
})
