package org.xfei.cracker;

import org.xfei.cracker.permutations.ASCIIPermutations;

import java.time.Duration;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

        String[] combo_options = null;
        //ugly way to get arguments, need to improve
        if (args.length > 0 && args[0] != null) // target file path
        {
            Utils.filePath = args[0];
        }
        if (args.length > 1 && args[1] != null) // file type, crackers, JKS etc.
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
            Utils.start_char = (char) Integer.parseInt(args[7]);
        }

        if (args.length > 8 && args[8] != null)// ascii based solution, start with SPACE
        {
            Utils.end_char = (char) Integer.parseInt(args[8]);
        }
        if (args.length > 9 && args[9] != null)// ascii exclusions, examples 57-57,55-58
        {
            String excStr = args[9];
            if (excStr.contains(",")) {
                for (String range : excStr.split(",")) {
                    Utils.generateChars(range);
                }
            } else {
                Utils.generateChars(excStr);
            }
        }

        System.out.println("File name: " + Utils.filePath);
        System.out.println("File type: " + Utils.fileType);
        System.out.println("Password min length: " + Utils.min_length);
        System.out.println("Password max length: " + Utils.max_length);
        System.out.println("Number of threads: " + Utils.threads);
        Utils.executor = Executors.newFixedThreadPool(Utils.threads); // thread pool
//        simple unit test
//        Utils.testReadingJCEKS("888888", "CERT1", "999888");
//        Utils.testReadingPDF(Utils.filePath,"123456");

        Utils.start_time = System.currentTimeMillis();
        ASCIIPermutations.passWordGen("", Utils.start_char);// core function, replace this with your algorithm and just pass generated password to new thread

        //Utils.done=true;
        Utils.executor.shutdown();
        while (!Utils.executor.isTerminated()) {
        }

        if (Utils.jackpot != null) {
            System.out.println("Found the password: " + Utils.jackpot);
        } else {
            System.out.println("No password found.");
        }
        System.out.println("Number of password tried: " + Utils.count);
        System.out.println("Minutes used: " + Duration.ofMillis(Utils.end_time - Utils.start_time).toMinutes());
    }

}
