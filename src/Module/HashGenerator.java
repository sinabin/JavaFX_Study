package Module;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HashGenerator {

    public static ArrayList<HashVO> HashCode_Generator(ArrayList<File> fileList){
        ArrayList<HashVO> list = new ArrayList<>();
        for(int i=0; i<fileList.size(); i++){
            HashVO vo = new HashVO(); // vo객체를 for문 안에서 선언해야 갱신된 vo객체를 add할 수 있음.
            File file = fileList.get(i);
            String hashData = getHashData(file);
            if(hashData ==null){
                continue;
            }
            vo.setMaked_wz_name(file.getName());
            vo.setMaked_hash_val(hashData);
            list.add(vo);

        }
        return list;
    }

    public static String getHashData(File file){
        BufferedInputStream fis = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            return DigestUtils.sha256Hex(fis);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }
        return null;
    }

}
