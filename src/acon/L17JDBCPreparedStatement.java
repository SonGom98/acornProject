package acon;

import java.sql.*;

public class L17JDBCPreparedStatement {
    public static void main(String[] args) {
        //PreparedStatement : sql injection 해킹을 방어하기 위해 등장
        String mysqlDriver = "com.mysql.cj.jdbc.Driver"; //DriverManager 가 동적 로딩시 사용
        String url = "jdbc:mysql://localhost:3306/scott";
        String user = "root";
        String pw = "mysql123";

        Connection conn = null; //접속하면 반환하는 객체
        PreparedStatement pstmt = null; //쿼리를 실행하는 객체
        ResultSet rs = null; //질의어를 실행하면 반환되는 table

        //검색창에서 20수를 입력 받아서 서비스를 제공하는 것이 목표!!
        //해커가 검색창에 "20 OR 1=1; DROP TABLE ENP;"을 입력받아서 실행 => emp 테이블이 삭제!!
//        String sql = "Select * FROM EMP where deptno=20 OR 1=1; DROP TABLE ENP";
        //해당 해킹은 파라미터의 타입 검사만 하면 막을 수 있다.
//        String sql = "Select * FROM EMP where deptno=20";

        String sql = "Select * FROM EMP where deptno=? and job = ?";

        try {
            Class.forName(mysqlDriver); //동적로딩 할 수 있도록 준비
            conn = DriverManager.getConnection(url,user,pw);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,20);
            pstmt.setString(2,"clerk");
            rs = pstmt.executeQuery();//질의어를 실행
            while(rs.next()){
                int empno = rs.getInt(1); //table 의 칼럼을 정의할 때 순서대로 변환
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                System.out.println(empno+"ㅣ"+ename+"ㅣ"+job+"ㅣ"+mgr);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
