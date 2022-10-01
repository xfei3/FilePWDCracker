package org.xfei.cracker.JCEKS;

import org.xfei.cracker.Factories.Cracker;
import org.xfei.cracker.Utils;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.security.KeyStore;

public class KeystoreCracker implements Runnable,Cracker {

    private String storepass = null, keypass = null, keyAlias = null, filePath = null, fileType;

    public String getStorepass() {
        return storepass;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public String getKeypass() {
        return keypass;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }

    public KeystoreCracker() {

    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public KeystoreCracker(String storepass) {
        this.storepass = storepass;
    }

    public KeystoreCracker(String storepass, String keypass) {
        this.storepass = storepass;
        this.keypass = keypass;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public void run() {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(this.fileType);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        try {
            Object secretKey = null;
            synchronized (this) {
                if (Utils.found) {
                    return;
                }
                FileInputStream fis = new FileInputStream(this.filePath);
                ks.load(fis, storepass.toCharArray());

                if (Utils.keyalias != null && Utils.storepass != null) {
                    secretKey = ks.getKey(this.keyAlias, this.keypass.toCharArray());
                }
//            String secretAsHex = new BigInteger(1, secretKey.getEncoded()).toString(16);
//            System.out.println(hexToAscii(secretAsHex));
                Utils.end_time = System.currentTimeMillis();
                Utils.found = true;
                Utils.jackpot = keypass==null?storepass:keypass;
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
        } finally {
            ks = null;
        }
    }
}
