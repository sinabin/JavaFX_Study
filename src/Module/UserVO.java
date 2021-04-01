package Module;

public class UserVO {
    private String account_id; // 아이디
    private String account_pwd;// 비번
    private String account_email;// 이메일
    private String connector_IP; // 접속 아이피

    public String getConnector_IP() {
        return connector_IP;
    }

    public void setConnector_IP(String connector_IP) {
        this.connector_IP = connector_IP;
    }

    public String getAccount_id() {
        return account_id;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_pwd() {
        return account_pwd;
    }

    public void setAccount_pwd(String account_pwd) {
        this.account_pwd = account_pwd;
    }

}
