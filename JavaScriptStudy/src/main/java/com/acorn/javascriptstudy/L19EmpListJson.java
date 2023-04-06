package com.acorn.javascriptstudy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/empListJson.do") //  /꼭 써야함 톰캣이 오류 일으킴E
public class L19EmpListJson extends HelloServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //동적 페이지는 서버에서 한번 실행하는 문서
        //동적 페이지는 호출할 때 마다 내용(db)이 갱신될 수 있다.
        //동적 페이지는 호출할 대때 나용을 바꿀 수 있는 파라미터(쿼리스트링)를 전달 받을 수 있어야한다.
        //url?queryString (key=value&key=value&....)
        //queryString 요청정보 request 에 존재하고 파라미터는 무조건 문자열이다.
        String deptnoStr = request.getParameter("deptno");


//        String a = request.getParameter("a");
//        String b = request.getParameter("b");
        response.setContentType("text/html:charset=UTP-8");
        PrintWriter out = response.getWriter();
        // out.println("{\"sum\":"+(a+b)+"}");
        String url = "jdbc:mysql://localhost:3306/SCOTT";
        String user = "root";
        String pw = "mysql123";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        PreparedStatement pstmt = null; //sql injection 해킹을 막음
        ResultSet rs = null;
        List<EmpDto> empList = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
            try {
                int deptno = Integer.parseInt(deptnoStr); //"?10&&" =>NumberFormatException
                String sql = "SELECT * FROM EMP WHERE DEPTNO=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, deptno);
            }catch (NumberFormatException e){
                e.printStackTrace();
                pstmt=conn.prepareStatement("SELECT * FROM EMP");
            }
            rs = pstmt.executeQuery();
            empList = new ArrayList<>();
            while (rs.next()) {
                EmpDto emp = new EmpDto();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setSal(rs.getDouble("sal"));
                emp.setDeptno(rs.getInt("deptno"));
                empList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //out.println(empList);//json 문자열로 만들어서 반환하세요!
        //"[{"empno":7788, "ename":"scott","sal":1200.00}]"
        /*String empJson="[";
        for(EmpDto emp : empList){
            empJson+="{";
            empJson+="\"empno\":"+emp.getEmpno()+",";
            empJson+="\"ename\":"+emp.getEname()+",";
            empJson+="\"job\":"+emp.getJob()+",";
            empJson+="\"sal\":"+emp.getSal()+",";
            empJson+="\"deptno\":"+emp.getDeptno()+",";
            empJson+="}";
        }
        empJson+="]";*/

        String empJson="[";
        if(empList!=null) {
            for (int i = 0; i < empList.size(); i++) {
                //12시 5분까지 쉬었다가 오세요~
                EmpDto emp = empList.get(i);
                empJson += "{";
                empJson += "\"empno\":" + emp.getEmpno() + ",";
                empJson += "\"ename\":\"" + emp.getEname() + "\",";
                empJson += "\"job\":\"" + emp.getJob() + "\",";
                empJson += "\"sal\":" + emp.getSal() + ",";
                empJson += "\"deptno\":" + emp.getDeptno() + "";
                empJson += "}";
                empJson += (i != empList.size() - 1) ? "," : "";
            }
            empJson += "]";
            out.println(empJson);
        }
    }
}
