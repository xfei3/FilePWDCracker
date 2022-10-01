package org.xfei.cracker.factories;

import org.xfei.cracker.crackers.KeystoreCracker;
import org.xfei.cracker.crackers.PDFCracker;
import org.xfei.cracker.crackers.ZIPCracker;

public class CrackerFactory {
    private CrackerFactory()
    {

    }

    public static Cracker getCrackerInstance(String fileType)
    {
        switch (fileType.toUpperCase())
        {
            case "ZIP":
                return new ZIPCracker();
            case "PDF":
                return new PDFCracker();
            case "JKS":
            case "crackers":
            default:
                return new KeystoreCracker();
        }
    }
}
