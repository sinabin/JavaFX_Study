package Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Check_HashDAO {
    Connection con = null; // 접속 객체 생성
    Statement stmt = null; // sql 실행객체
    ResultSet rs = null; // sql 실행 결과 저장 객체
    PreparedStatement pstmt = null;

    //생성자 작성
    public Check_HashDAO(){
        try {
            // 데이터베이스 접속(커넥션)
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/kms",
                    "user","input your password");
            if(con!= null) {
                System.out.println("DB 접속에 성공했습니다. - MemberDao");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public List<Check_HashVO> load_hashDB() {
        String sql = "select wz_name,hash_value from hashcode_wz";
        List<Check_HashVO> list = new ArrayList<>();
        System.out.println(sql);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Check_HashVO vo = new Check_HashVO();
                vo.setWz_name(rs.getString("wz_name"));
                vo.setHash_value(rs.getString("hash_value"));
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
