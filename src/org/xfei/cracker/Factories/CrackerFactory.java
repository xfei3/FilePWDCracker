package org.xfei.cracker.Factories;

import org.xfei.cracker.JCEKS.KeystoreCracker;
import org.xfei.cracker.JCEKS.ZIPCracker;

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
            case "JKS":
            case "JCEKS":
            default:
                return new KeystoreCracker();
        }
    }
}
