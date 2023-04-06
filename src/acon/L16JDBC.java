package acon;
import com.mysql.cj.jdbc.Driver; //mysql에서 제공하는 접속 패키지
//java.sql.* JDBC lib : jdk 에 포함되어 있다.
import java.sql.*;

public class L16JDBC {
    public static void main(String[] args) {
        //JDBC :JavaDataBaseConnect : 자바로 db 서버에 접속 하는 것 java.sql.* 객체를 제공
        //JDBC 로 db 서버에 접속하려면 해당 db(mysql)에서 제공하는 커넥터 객체를 사용해야 한다.
        String url = "jdbc:mysql://localhost:3306/SCOTT";
        String user = "root";
        String pw = "mysql123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =DriverManager.getConnection(url,user,pw);
//            Connection conn =DriverManager.getConnection(url,user,pw, new Driver()); //일반적인 객체지향 문법
            //DriverManager : jdbc db 접속 객체로 접속 성공시 connection(접속을 유지) 객체를 반환
            //동적로딩 : DriverManager 가 mysql 에 접속할 떄 mysql 에서 제공하는 Driver 를 객체로 생성해서 사용하는데 동적로딩이라는 방식을 사용
            //터미널 mysql - uroot -pmysql123
            //use SCOTT
            Statement stmt =conn.createStatement(); //쿼리를 실행하는 객체

            ResultSet rs = stmt.executeQuery("SELECT * FROM EMP WHERE DEPTNO = 30"); //executeQuery : 질의어(select, DQL)를 실행하는 함수
            //ResultSet : table 의 자료구조 (iterator 로 출력확인 가능 next)

            while (rs.next()){
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int sal = rs.getInt("sal");
                System.out.println(empno+"\tㅣ\t"+ename+"\tㅣ\t"+job+"\tㅣ\t"+sal);
            }

            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
