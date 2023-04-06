// const mysql = require("mysql2");
// const scottConnInfo={
//     user:"root",
//     password:"mysql123",
//     host:"localhost",
//     port:"3306",
//     database:"SCOTT"
// }
// const pool = mysql.createPool(scottConnInfo);
// const poolPromise=pool.promise();
//
// (async ()=>{
// const [rows,f]=await poolPromise.query("SELECT * FROM EMP");
// console.log(rows);
// })();
const scott = require("./mysqlScottPool");
(async ()=>{
    const [rows,f]= await scott.query("SELECT * FROM DEPT");
    console.log(rows)

})(); // 함수를 생성하자마자 실행