package org.xfei.cracker;

import org.xfei.cracker.Permutations.ASCIIPermutations;

import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

        String[] combo_options = null;
        //ugly way to get arguments, need to improve
        if (args.length > 0 && args[0] != null) // target file path
        {
            Utils.fileName = args[0];
        }
        if (args.length > 1 && args[1] != null) // file type, JCEKS, JKS etc.
        {
            Utils.fileType = args[1];
        }
        if (args.length > 2 && args[2] != null) // min length, default is 6
        {
            Utils.min_length = Integer.parseInt(args[2]);
        }
        if (args.length > 3 && args[3] != null) // max length default is 8
        {
            Utils.max_length = Integer.parseInt(args[3]);
        }
        if (args.length > 4 && args[4] != null) // max length default is 20
        {
            Utils.threads = Integer.parseInt(args[4]);
        }

        if (args.length > 5 && args[5] != null && !args[5].equals(Utils.NOKEYPASS)) // if you provide a valid storepass, meaning you want to crack keypass
        {
            Utils.storepass = args[5];
        }

        if (args.length > 6 && args[6] != null && !args[6].equals(Utils.NOKEYPASS)) // if you provide a valid storepass, you need to provide key alias
        {
            Utils.keyalias = args[6];
        }

        if (args.length > 7 && args[7] != null)// ascii based solution, start with SPACE
        {
            Utils.start_char = (char)Integer.parseInt(args[7]);
        }

        if (args.length > 8 && args[8] != null)// ascii based solution, start with SPACE
        {
            Utils.end_char = (char)Integer.parseInt(args[8]);
        }
/** currently not in use, however, if you want to use your own algorithem, you can refer to it.
 if (args.length > 7 && args[7] != null) { // example: numeric_upper_lower_allsymbols,
 combo_options = args[7].split("_");
 }
 for (int i = 0; combo_options != null && i < combo_options.length; i++) {
 switch (combo_options[i]) {
 case Utils.NUMERIC:
 Utils.combos.addAll(Arrays.asList(Utils.ARRAY_NUMERIC));
 break;
 case Utils.UPPER:
 Utils.combos.addAll(Arrays.asList(Utils.ARRAY_UPPER));
 break;
 case Utils.LOWER:
 Utils.combos.addAll(Arrays.asList(Utils.ARRAY_LOWER));
 break;
 case Utils.SYMBOLS_09:
 Utils.combos.addAll(Arrays.asList(Utils.ARRAY_SYMBOLS_09));
 break;
 case Utils.SYMBOLS_ALL:
 Utils.combos.addAll(Arrays.asList(Utils.ARRAY_SYMBOLS_ALL));
 break;
 default:
 break;
 }
 }
 Combination combo = new Combination();
 String arr[] = Utils.setToArray(Utils.combos);

 int n = arr.length;

 for(int i=Utils.min_length;i<=Utils.max_length;i++) {
 combo.printCombination(arr, n, i);
 }
 **/
        System.out.println("File name: " + Utils.fileName);
        System.out.println("File type: " + Utils.fileType);
        System.out.println("Password min length: " + Utils.min_length);
        System.out.println("Password max length: " + Utils.max_length);
        System.out.println("Number of threads: " + Utils.threads);
        Utils.executor = Executors.newFixedThreadPool(Utils.threads); // thread pool

//        Utils.testReadingJCEKS("888888", "CERT1", "999888");  //simple unit test

        Utils.start_time = System.currentTimeMillis();//not in use
        ASCIIPermutations.passWordGen("", Utils.start_char);// core function, replace this with your algorithm and just pass generated password to new thread
        if (Utils.jackpot != null) {
            System.out.println("Found the password: " + Utils.jackpot);
        }
        System.out.println("Number of password generated: " + Utils.count);
        Utils.executor.shutdown();
        while (!Utils.executor.isTerminated()) {
        }
    }


}
