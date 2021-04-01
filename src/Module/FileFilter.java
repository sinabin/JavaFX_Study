package Module;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileFilter implements FilenameFilter {
    public ArrayList<File> wzPath(String path){
        File dir = new File(path);
        File[] fileList = dir.listFiles(); //디렉토리내 모든 파일리스트

        ArrayList<File> wz_fileList  = new ArrayList<File>();

        FileFilter iswz = new FileFilter();

        String str_fileList [] = new String[fileList.length];

        for (int i=0; i<fileList.length; i++){
            str_fileList[i] = String.valueOf(fileList[i]);
            if(iswz.accept(dir, str_fileList[i])){ //필터된 파일추가
                wz_fileList.add(new File(str_fileList[i]));
            }
        }
        return wz_fileList;
    }

    @Override
    public boolean accept(File dir, String name) {
        boolean isAccept = false;

        if (name.endsWith("Skill.wz") || name.endsWith("Item.wz") || name.endsWith("Character.wz"))
        {
            isAccept = true;
        }
        else
        {
            isAccept = false;
        }

        return isAccept;
    }
}
