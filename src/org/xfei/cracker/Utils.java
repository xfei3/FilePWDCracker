package org.xfei.cracker;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class Utils {
    public static int min_length = 6, max_length = 8, threads = 20, count = 0;
    public static long start_time, end_time;
    public static String filePath;
    public static String fileType = "crackers";
    public static String storepass = null;
    public static String keyalias = null;
    public static String NOKEYPASS = "NO-KEYPASS";
    public static String jackpot = null;
    public static char start_char = ' ', end_char = '~';
    public static Boolean found = false;
    public static ExecutorService executor = null;
    public static HashSet<Character> exlusions = new HashSet<Character>();

    public static void testReadingPDF(String filePath, String password) {

        try {
            File file = new File(filePath);

            // Load the PDF file
            PDDocument pdd = PDDocument.load(file, password);

            // removing all security from PDF file
            pdd.setAllSecurityToBeRemoved(true);

            pdd.close();

        } catch (Exception e) {
            e.printStackTrace();
        } catch (NoClassDefFoundError er) {
            er.printStackTrace();
        }
    }

    public static void testReadingJCEKS(String storepass, String keyalias, String keypass) {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(Utils.fileType);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        try {

            FileInputStream fis = new FileInputStream(Utils.filePath);

            ks.load(fis, storepass.toCharArray());
            if (Utils.keyalias != null && Utils.storepass != null) {
                Object secretKey = ks.getKey(keyalias, keypass.toCharArray());
            }
//            String secretAsHex = new BigInteger(1, secretKey.getEncoded()).toString(16);
//            System.out.println(hexToAscii(secretAsHex));
            Utils.end_time = System.currentTimeMillis();
            Utils.found = true;
            System.out.println("Found the password: " + storepass);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ks = null;
        }


    }
    public static void generateChars(String charRange)
    {
        if(charRange.contains("-"))
        {
            String[] range= charRange.split("-");
            if(range.length==2)
            {
                for(int i=Integer.parseInt(range[0]); i <= Integer.parseInt(range[1]);i++)
                {
                    Utils.exlusions.add(new Character((char)i));
                    System.out.println((char)i);
                }
            }
        }
    }
//    public static String[] setToArray(Set st) {
//        String arr[] = new String[st.size()];
//
//        int i = 0;
//
//        // iterating over the hashset
//        for (Object ele : st) {
//            arr[i++] = ele.toString();
//        }
//        return arr;
//    }

}
