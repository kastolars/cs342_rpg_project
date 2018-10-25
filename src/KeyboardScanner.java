/*
Author: Karol Stolarski
netID: kstola2

Singleton design pattern which has a private
Scanner. Reads in input from player.
 */


import java.util.Scanner;

public class KeyboardScanner {

    private static Scanner ks = new Scanner(System.in);

    private KeyboardScanner(){}

    public static Scanner getKeyBoardScanner(){
        return ks;
    }
}
