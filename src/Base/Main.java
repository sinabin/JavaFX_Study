package Base;

import Module.ProcessChecker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("플리토 접속기");
        primaryStage.setScene(new Scene(root, 800, 597));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                    event.consume();
            }
        });
        primaryStage.initStyle(StageStyle.TRANSPARENT); // 닫기, 최소화 등 버튼 없는 스타일로 설정
        new animatefx.animation.FadeIn(root).play(); // 처음 실행시 애니메이션 효과
        primaryStage.show();

        //프로세스 체킹
        Runtime runtime = Runtime.getRuntime();
        String cmds[] = {"cmd", "/c", "tasklist"};
        Process proc = runtime.exec(cmds);
        InputStream inputstream = proc.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        String line;
        int i = 0;
//        Pattern pattern = Pattern.compile("\\S{1,}.exe"); // 공백을 제외한 모든문자가 1자이상.exe붙은것만 거르기
        Pattern pattern2 = Pattern.compile("^[a-zA-Z]\\S{1,}");
        while ((line = bufferedreader.readLine()) != null) {
            List<String> readedLine = new ArrayList<String>();
            i++;
            if (i > 6) {
                String lines = line;
//                Matcher matcher = pattern.matcher(lines);
                Matcher matcher2 = pattern2.matcher(lines);
                while (matcher2.find()) {
//                    System.out.println(matcher.group());
                    System.out.println(matcher2.group());
                    if (matcher2.group().equals("cheatengine-x86_64.exe")) {
                        try {
                            String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                            Runtime.getRuntime().exec(cmdArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    } else if (matcher2.group().equals("AutoHotkey.exe")) {
                        try {
                            String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                            Runtime.getRuntime().exec(cmdArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    } else if (matcher2.group().equals("cheatengine-i386.exe")) {
                        try {
                            String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                            Runtime.getRuntime().exec(cmdArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    } else if (matcher2.group().equals("HaRepackerResurrected.exe")) {
                        try {
                            String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                            Runtime.getRuntime().exec(cmdArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    } else if (matcher2.group().equals("cheatengine-x86_64-SSE4-A")) {
                        try {
                            String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                            Runtime.getRuntime().exec(cmdArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                    if (matcher2.group() == null)
                        break;
                }

            }
        }


        ProcessChecker p1 = new ProcessChecker("*");
        p1.start();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
