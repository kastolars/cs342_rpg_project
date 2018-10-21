import java.util.Scanner;

public class KeyboardScanner {

    private static Scanner ks = new Scanner(System.in);

    private KeyboardScanner(){}

    public static Scanner getKeyBoardScanner(){
        return ks;
    }
}
