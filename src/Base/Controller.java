package Base;

import Module.*;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public TextField getTxtUserId() {
        return txtUserId;
    }

    public void setTxtUserId(TextField txtUserId) {
        this.txtUserId = txtUserId;
    }

    //로그인정보
    public TextField txtUserId;
    public PasswordField txtPassword;

    //pane and button
    public AnchorPane archRoot;
    public Circle btnClose;
    public Button btnSignup;
    public Pane pane_regist;
    public Circle btnClose1;
    public ImageView btnLogin;
    public Pane pane_game;
    public Stage stage = null;

    //회원 가입 정보
    public TextField txtSignid;
    public PasswordField txtSignpw;
    public TextField txtSignEmail;
    public Button btnLogout;
    public Button btnGamestart;
    public ProgressBar progressBar;
    public Label progressLable;
    public Pane pane_log;
    public Circle btnMini;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setStyle("-fx-accent: #FF5733");
    }

    @FXML
    public void login(ActionEvent event) {
        UserDAO dao = new UserDAO();
        try {
            int r = dao.Login(txtUserId.getText(), txtPassword.getText()); // ID, PW확인
            InetAddress ip = InetAddress.getLocalHost();
            UserVO vo = new UserVO();
            vo.setConnector_IP(ip.getHostAddress());

            int r2 = dao.connecter_ip(vo.getConnector_IP(), txtUserId.getText());//접속중인 IP 업데이트

            // ID, PW가 일치하고 IP업데이트가 되었을 경우 로그인 성공
            if (r == 1 & r2 == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("로그인 알림");
                alert.setTitle("로그인 성공");
                alert.setContentText("로그인에 성공했습니다.");
                alert.showAndWait();
                new FadeOut(btnSignup).play();
                new FadeOut(btnLogin).play();
                new FadeOut(txtUserId).play();
                new FadeOut(txtPassword).play();

                new ZoomIn(pane_game).play();
                pane_game.toFront();


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("로그인 알림");
                alert.setTitle("로그인 실패");
                alert.setContentText("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sing_up(ActionEvent event) {
        if (event.getSource().equals(btnSignup)) {
            new ZoomIn(pane_regist).play();
            pane_regist.toFront();
        }
    }


    @FXML
    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose) {
            new FadeOut(archRoot).play();
            // 클라종료
            try {
                String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                Runtime.getRuntime().exec(cmdArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 접속권한 해제
            UserDAO dao = new UserDAO();
            dao.give_allowed("0",txtUserId.getText());
            // 접속기 종료
            System.exit(0);
        }
        if (event.getSource() == btnClose1) {
            new FadeOut(pane_regist).play();
        }
        if (event.getSource() == btnMini){
            stage = (Stage) archRoot.getScene().getWindow();
            stage.setIconified(true);
        }
    }


    @FXML
    public void register(ActionEvent event) {
        //입력정보
        UserVO vo = new UserVO();
        vo.setAccount_id(txtSignid.getText());
        vo.setAccount_pwd(txtSignpw.getText());
        vo.setAccount_email(txtSignEmail.getText());
        if(txtSignid.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 알림");
            alert.setTitle("아이디 미작성");
            alert.setContentText("아이디를 입력하지 않으셨습니다.");
            alert.showAndWait();
        } else if(txtSignid.getText().length()<4){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 알림");
            alert.setTitle("아이디 작성오류");
            alert.setContentText("아이디는 최소 4자리 이상이여야 합니다.");
            alert.showAndWait();
        } else if(txtSignpw.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 알림");
            alert.setTitle("비밀번호 미작성");
            alert.setContentText("비밀번호를 입력하지 않으셨습니다.");
            alert.showAndWait();
        } else if(txtSignpw.getText().length()<6){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 알림");
            alert.setTitle("비밀번호 작성오류");
            alert.setContentText("비밀번호는 최소 6자리 이상이여야 합니다.");
            alert.showAndWait();
        } else if (txtSignEmail.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 알림");
            alert.setTitle("이메일 미작성");
            alert.setContentText("이메일을 작성하지 않으셨습니다.");
            alert.showAndWait();
        } else {
            UserDAO dao = new UserDAO();
            int r = dao.sing_up(vo);
            if (r == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("회원가입 알림");
                alert.setTitle("회원가입 성공");
                alert.setContentText("회원등록이 완료되었습니다.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("회원가입 알림");
                alert.setTitle("회원가입 실패");
                alert.setContentText("이미 존재하는 아이디 이거나 올바르지 않은 정보입니다.\r다시 입력해주세요.");
                alert.showAndWait();
                System.out.println(r);
            }
        }

    }


    @FXML
    public void game_start(ActionEvent event){
        if(event.getSource().equals(btnGamestart)){
            //접속기가 위치한 디렉토리 가져오기
            FileFilter wzFilter = new FileFilter();
            String current_path = System.getProperty("user.dir");
            System.out.println("접속기가 위치한 디렉토리 :" + current_path);
            double progress = 0.1;
            progressBar.setProgress(progress);
            progressLable.setText(Double.toString(progress*100) + "%");

            //디렉토리내 wz경로
            for (int i = 0; i < wzFilter.wzPath(current_path).size(); i++) {
                System.out.println(wzFilter.wzPath(current_path).get(i)); //wz 경로출력
            }

            //접속자 클라이언트의 Hashcode 생성
            ArrayList<HashVO> result_cli = HashGenerator.HashCode_Generator(wzFilter.wzPath(current_path));



            //생성된 hashcode값 확인
            for(int i=0; i<result_cli.size(); i++){
            System.out.println(result_cli.get(i).getMaked_wz_name() +": "+ result_cli.get(i).getMaked_hash_val());
            }

            //DB에 저장된 hashcode값 확인
            Check_HashDAO dao2 = new Check_HashDAO();
            List<Check_HashVO> result_db = dao2.load_hashDB();
            for(int i=0; i<result_db.size(); i++){
                System.out.println(result_db.get(i).getWz_name() + ": "+ result_db.get(i).getHash_value());
            }
            int bar_value = 1;
            //변조체크
            UserDAO dao = new UserDAO();
            for (int i = 0; i < result_db.size(); i++) {
                for (int j = 0; j < result_db.size(); j++) {
                    if (result_db.get(i).getWz_name().equals(result_cli.get(j).getMaked_wz_name())) {
                        if (result_db.get(i).getHash_value().equals(result_cli.get(j).getMaked_hash_val())) {
                            System.out.println("파일체크 완료 - 정상파일");
                            bar_value++;
                            progress = bar_value/4;
                            progressLable.setText(Double.toString(progress*100) + "%");
                            progressBar.setProgress(progress);
                            if(bar_value==4){
                                dao.give_allowed("1",txtUserId.getText());//접속권한 부여

                                //접속 exe파일 실행
                                Runtime rt = Runtime.getRuntime();
                                String exeFile = current_path+"\\loader.bat"; //실행파일경로
                                System.out.println("exeFile: " + exeFile);
                                Process p;

                                try {
                                    p = rt.exec(exeFile);
                                    p.waitFor();
                                } catch (Exception error) {
                                    error.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("변조파일 발견");
                        }
                    }
                }
            }

        }
    }

    @FXML
    public void Logout(ActionEvent event){
        if(event.getSource() == btnLogout){
        UserDAO dao = new UserDAO();
        dao.give_allowed("0",txtUserId.getText());//접속권한 해제
            new FadeOut(pane_game).play();
            pane_log.toFront();
            new FadeIn(btnSignup).play();
            new FadeIn(btnLogin).play();
            new FadeIn(txtUserId).play();
            new FadeIn(txtPassword).play();
            double progress = 0.0;
            progressBar.setProgress(progress);
            progressLable.setText(Double.toString(progress*100) + "%");

        }
    }
}
