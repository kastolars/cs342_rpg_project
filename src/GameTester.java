/*
Author: Karol Stolarski
netID: kstola2

Main file. The program can either take in a file as an
environment variable or can prompt the Player for a data file.
Game Tester will then attempt to construct the game given the
file and its contents.
If the user enters quit, it will quit the game.
If successful, it will run the Game class play loop.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameTester {

    public static void main(String [] args){

        System.out.println("Author: Karol Stolarski");
        System.out.println("NetID: kstola2");

        String filename;

        Scanner ks = KeyboardScanner.getKeyBoardScanner();

        if (args.length < 1){
            System.out.println("No file name detected. Please provide one.");
            filename = ks.nextLine();
        } else {
            filename = args[0];
        }

        File infile = new File(filename);

        System.out.println(String.format("Loading %s", filename));

        while (true){
            try{
                Scanner fs = new Scanner(infile);
                Game g = new Game(fs);
                g.play();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not found; please enter a valid file name");
                filename = ks.nextLine();
                if (filename.matches("(?i)quit")){
                    System.out.println("Exiting...");
                    return;
                } else {
                    infile = new File(filename);
                    System.out.println(String.format("Loading %s", filename));
                }
            }
        }
    }
}
