package Module;

import java.sql.*;

public class UserDAO {
    Connection con = null; // 접속 객체 생성
    Statement stmt = null; // sql 실행객체
    ResultSet rs = null; // sql 실행 결과 저장 객체
    PreparedStatement pstmt = null;

    //생성자 작성
    public UserDAO(){
        try {
            // 데이터베이스 접속(커넥션)
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/kms",
                    "user","input your password");
            if(con!= null) {
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public int Login(String userId, String userPwd) {
        String sql = "select password from accounts where name =?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()){
                if(rs.getString(1).equals(userPwd)){
                    return 1; //로그인 성공
                } else
                    return 0; //비밀번호 틀림
            }
            return -1;// 아이디 없음

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try { if(pstmt !=null) pstmt.close();} catch(Exception e) {e.toString();}
            try { if(rs !=null) pstmt.close();} catch(Exception e) {e.toString();}
        }
        return -2; // 데이터베이스 오류
    }

    public int sing_up(UserVO vo) {
        String sql = "insert into accounts(name, password, email) values(?,?,?);";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, vo.getAccount_id());
            pstmt.setString(2, vo.getAccount_pwd());
            pstmt.setString(3, vo.getAccount_email());
            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try { if(pstmt !=null) pstmt.close();} catch(Exception e) {e.toString();}
        }
        return -1; //DB오류
    }

    public int connecter_ip(String connecterIP, String name) {
        String sql = "update accounts set connecterIP=? where name=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, connecterIP);
            pstmt.setString(2,name);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try { if(pstmt !=null) pstmt.close();} catch(Exception e) {e.toString();}
        }
        return -1; //DB오류
    }

    public int give_allowed(String allowed, String name) {
        String sql = "update accounts set allowed=? where name=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, allowed);
            pstmt.setString(2,name);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try { if(pstmt !=null) pstmt.close();} catch(Exception e) {e.toString();}
        }
        return -1; //DB오류
    }

}
