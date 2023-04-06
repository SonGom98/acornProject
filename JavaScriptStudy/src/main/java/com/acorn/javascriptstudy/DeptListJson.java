package com.acorn.javascriptstudy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deptListJson.do")
public class DeptListJson extends HelloServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html:charset=UTP-8");
        PrintWriter out = response.getWriter();
        String url = "jdbc:mysql://localhost:3306/SCOTT";
        String user = "root";
        String pw = "mysql123";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DeptDto> deptList = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
            String sql = "SELECT * FROM DEPT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            deptList = new ArrayList<>();
            while(rs.next()){
                DeptDto dept = new DeptDto();
                dept.setDeptno(rs.getInt("deptno"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                deptList.add(dept);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        String deptJson="[";
//        for (DeptDto d : deptList){
//            deptJson+="{";
//            deptJson+="\"deptno\":" + d.getDeptno() + ",";
//            deptJson+="\"dname\":" + d.getDname()+ ",";
//            deptJson+="\"loc\":"+d.getLoc()+"";
//            deptJson+="}";
//        }
//        deptJson+="]";
//        out.println(deptJson);
        String deptJson="[";
        if(deptList!=null) {
            for (int i = 0; i < deptList.size(); i++) {
                //12시 5분까지 쉬었다가 오세요~
                DeptDto d = deptList.get(i);
                deptJson += "{";
                deptJson += "\"deptno\":" + d.getDeptno() + ",";
                deptJson += "\"dname\":\"" + d.getDname() + "\",";
                deptJson += "\"loc\":\"" + d.getLoc() + "\"";
                deptJson += "}";
                deptJson += (i != deptList.size() - 1) ? "," : "";
            }
            deptJson += "]";
            out.println(deptJson);
        }
    }
}
