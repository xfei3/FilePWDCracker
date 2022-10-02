package org.xfei.cracker.permutations;

import org.xfei.cracker.crackers.PDFCracker;
import org.xfei.cracker.factories.CrackerFactory;
import org.xfei.cracker.crackers.KeystoreCracker;
import org.xfei.cracker.crackers.ZIPCracker;
import org.xfei.cracker.Utils;

public class ASCIIPermutations {
    // refer to https://stackoverflow.com/questions/16848918/how-to-generate-the-password-with-permutation-of-string
    private static void crackZip(String currentPass, char c) {
        ZIPCracker zipcracker = (ZIPCracker) CrackerFactory.getCrackerInstance(Utils.fileType);
        zipcracker.setFilePath(Utils.filePath);
        zipcracker.setPassword(currentPass + c);
        Utils.executor.execute(zipcracker);
    }

    private static void crackPDF(String currentPass, char c) {
        PDFCracker pdfcracker = (PDFCracker) CrackerFactory.getCrackerInstance(Utils.fileType);
        pdfcracker.setFilePath(Utils.filePath);
        pdfcracker.setPassword(currentPass + c);
        Utils.executor.submit(pdfcracker);
    }

    private static void crackKeystore(String currentPass, char c) {
    //pass password to new thread
        KeystoreCracker kscracker = (KeystoreCracker) CrackerFactory.getCrackerInstance(Utils.fileType);
        kscracker.setFilePath(Utils.filePath);
        kscracker.setFileType(Utils.fileType);
        if (Utils.keyalias != null && Utils.storepass != null) {
            //brute forcing key pass

            kscracker.setStorepass(Utils.storepass);
            kscracker.setKeypass(currentPass + c);
            kscracker.setKeyAlias(Utils.keyalias);
            //Utils.executor.execute(kscracker);
            //Utils.executor.execute(new KeystoreCracker(Utils.storepass, currentPass + c));
        } else {
            //brute forcing store pass
            kscracker.setStorepass(currentPass + c);
            //Utils.executor.execute(new KeystoreCracker(currentPass + c));
        }
        Utils.executor.execute(kscracker);
    }

    public static void passWordGen(String currentPass, char c) {
        if (Utils.found) {
            return;
        }
        while(Utils.exlusions.contains(new Character(c)))
        {
            c++;
        }
        if (c <= Utils.end_char) {
            if ((currentPass + c).length() >= Utils.min_length) {
                Utils.count++;
                //System.out.println("trying password: " + currentPass + c);

                switch (Utils.fileType.toUpperCase())
                {
                    case "PDF":
                        crackPDF(currentPass,c);
                        break;
                    case "ZIP":
                        crackZip(currentPass,c);
                        break;
                    case "JKS":
                    case "crackers":
                    default:
                        crackKeystore(currentPass,c);
                }

            }
            passWordGen(currentPass, (char) (c + 1)); //go through every character at this postion
            if (currentPass.length() < Utils.max_length - 1) {
                passWordGen(currentPass + c, Utils.start_char); //start over by adding the current charterer to the current postion, default should be SPACE
            }
        }
    }
}
