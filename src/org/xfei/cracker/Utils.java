package org.xfei.cracker;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class Utils {
    public static final String NUMERIC = "numeric";
    public static final String UPPER = "upper";
    public static final String LOWER = "lower";
    public static final String SYMBOLS_ALL = "allsymbols";
    public static final String SYMBOLS_09 = "09symbols";
    public static final String[] ARRAY_NUMERIC = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String[] ARRAY_UPPER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static final String[] ARRAY_LOWER = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static final String[] ARRAY_SYMBOLS_09 = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "(", ")"};
    public static final String[] ARRAY_SYMBOLS_ALL = new String[]{" ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
    public static Set<String> combos = new HashSet<String>();
    public static int min_length = 6, max_length = 8, threads = 20, count = 0;
    public static long start_time, end_time;
    public static String fileName;
    public static String fileType = "JCEKS";
    public static String storepass = null;
    public static String keyalias = null;
    public static String NOKEYPASS = "NO-KEYPASS";
    public static String jackpot = null;
    public static char start_char = ' ', end_char ='~';
    public static Boolean found = false;
    public static ExecutorService executor = null;

    public static void testReadingJCEKS(String storepass, String keyalias, String keypass) {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(Utils.fileType);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        try {

            FileInputStream fis = new FileInputStream(Utils.fileName);

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
