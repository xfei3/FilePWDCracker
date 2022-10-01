package org.xfei.cracker.crackers;

import net.lingala.zip4j.ZipFile;
import org.xfei.cracker.factories.Cracker;

import java.io.File;
import java.util.List;

import net.lingala.zip4j.model.FileHeader;
import org.xfei.cracker.Utils;


public class ZIPCracker implements Runnable, Cracker {
    private String filePath = null, password = null;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void run() {
        if (Utils.found) {
            return;
        }
        try {
            synchronized (this) {


                ZipFile zipFile = new ZipFile(this.filePath);
                zipFile.setPassword(this.password.toCharArray());
                List fileHeaderList = zipFile.getFileHeaders();
                FileHeader fileHeader = (FileHeader) fileHeaderList.get(0);
                zipFile.extractFile(fileHeader, "tmp/");
                File f = new File("tmp/" + fileHeader.getFileName());
                if (f.exists()) {

                    f.delete();
                }

//            zipFile.extractFile(fileHeader, "./");
//            for (int i = 0; i < fileHeaderList.size(); i++) {
//                FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
//                //Path where you want to Extract
//                zipFile.extractFile(fileHeader, "./");
//                System.out.println("Extracted");
//            }

                Utils.end_time = System.currentTimeMillis();
                Utils.found = true;
                Utils.jackpot = password;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
