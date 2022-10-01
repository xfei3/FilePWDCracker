package org.xfei.cracker.crackers;

import org.xfei.cracker.Utils;
import org.xfei.cracker.factories.Cracker;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;

public class PDFCracker implements Runnable, Cracker {
    private String password, filePath;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        if (Utils.found) {
            return;
        }
        System.out.println("Trying "+password);
        try {
            synchronized (this) {
                /*
                if don`t add Utils.done here, seems thread pool won`t end, not sure why.
                this may introduce a bug that password might be found in last a few threads but Utils.done is set to true,
                then the password will not be found
                 */

                File file = new File(this.filePath);

                // Load the PDF file
                PDDocument pdd = PDDocument.load(file, this.password);

                // removing all security from PDF file
                pdd.setAllSecurityToBeRemoved(true);

                pdd.close();
                Utils.end_time = System.currentTimeMillis();
                Utils.found = true;
                Utils.jackpot = password;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }catch (NoClassDefFoundError er)
        {
            //er.printStackTrace();
        }

    }
}
