import jdk.internal.joptsimple.internal.Strings;

import java.util.Scanner;

public class CleanLineScanner {

    public static String getCleanLine(Scanner sc){
        String line = null;
        while (sc.hasNext()){
            line = sc.nextLine();
            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }
            line = line.trim();
            if (line.length() > 0){
                break;
            }
        }
        return line;
    }
}