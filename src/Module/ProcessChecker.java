package Module;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Base.Controller;

public class ProcessChecker extends Thread{
    String str;
    Controller control = new Controller();

    public ProcessChecker(String str){ // 생성자
        this.str = str;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep((int)(Math.random()*5000));
                //프로세스 체킹
                Runtime runtime = Runtime.getRuntime();
                String cmds[] = {"cmd", "/c", "tasklist"};
                Process proc = runtime.exec(cmds);
                InputStream inputstream = proc.getInputStream();
                InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
                String line;
                int i=0;
//        Pattern pattern = Pattern.compile("\\S{1,}.exe"); // 공백을 제외한 모든문자가 1자이상.exe붙은것만 거르기
                Pattern pattern2 = Pattern.compile("^[a-zA-Z]\\S{1,}");
                while ((line = bufferedreader.readLine()) != null) {
                    List<String> readedLine = new ArrayList<String>();
                    i++;
                    if(i>6){
                        String lines = line;
//                Matcher matcher = pattern.matcher(lines);
                        Matcher matcher2 = pattern2.matcher(lines);
                        while (matcher2.find()){
//                    System.out.println(matcher.group());
                            System.out.println(matcher2.group());
                            if(matcher2.group().equals("cheatengine-x86_64.exe")){
                                try {
                                    String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                                    Runtime.getRuntime().exec(cmdArr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // 접속권한 해제
                                UserDAO dao = new UserDAO();
                                dao.give_allowed("0",control.getTxtUserId().getText());
                                // 접속기종료
                                System.exit(0);
                            } else if(matcher2.group().equals("AutoHotkey.exe")){
                                try {
                                    String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                                    Runtime.getRuntime().exec(cmdArr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // 접속권한 해제
                                UserDAO dao = new UserDAO();
                                dao.give_allowed("0",control.getTxtUserId().getText());
                                // 접속기 종료
                                System.exit(0);
                            } else if(matcher2.group().equals("cheatengine-i386.exe")){
                                try {
                                    String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                                    Runtime.getRuntime().exec(cmdArr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // 접속권한 해제
                                UserDAO dao = new UserDAO();
                                dao.give_allowed("0",control.getTxtUserId().getText());
                                System.exit(0);
                            } else if(matcher2.group().equals("HaRepackerResurrected.exe")){
                                try {
                                    String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                                    Runtime.getRuntime().exec(cmdArr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // 접속권한 해제
                                UserDAO dao = new UserDAO();
                                dao.give_allowed("0",control.getTxtUserId().getText());
                                System.exit(0);
                            } else if(matcher2.group().equals("cheatengine-x86_64-SSE4-A")){
                                try {
                                    String [] cmdArr = new String[] {"cmd.exe","/c","taskkill /F /IM MapleStory.exe"};
                                    Runtime.getRuntime().exec(cmdArr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // 접속권한 해제
                                UserDAO dao = new UserDAO();
                                dao.give_allowed("0",control.getTxtUserId().getText());
                                System.exit(0);
                            }
                        }

                    }
                }
            } catch(InterruptedException | IOException e){
                e.printStackTrace();
            }
        }
    }

}
