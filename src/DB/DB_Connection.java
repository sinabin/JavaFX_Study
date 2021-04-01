package DB;
import java.sql.*;

public class DB_Connection {
    String driver  = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public DB_Connection(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/kms",
                    "user","input your password");
            if( con!= null){
                System.out.println("DB 접속에 성공했습니다.");
            }

        } catch (ClassNotFoundException e){
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e){
            System.out.println("DB 접속실패");
            e.printStackTrace();
        }
    }
}
