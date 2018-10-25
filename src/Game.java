/*
Author: Karol Stolarski
netID: kstola2
 */


import java.util.Scanner;

public class Game {

    String name; // Name of Game
    int version; // Version

    public Game(Scanner sc){

        // Get version # and name
        String line = CleanLineScanner.getCleanLine(sc);
        version = CleanLineScanner.extractInt(line);
        name = line.replaceAll("GDF|\\d|\\.", "").trim();

        // Get number of places
        line = CleanLineScanner.getCleanLine(sc);
        int numPlaces = CleanLineScanner.extractInt(line);

        // Make places
        for (int i = 0; i < numPlaces; i++){
            Place p = new Place(sc, version);
        }

        // Make exit and nowhere
        Place nowhere = new Place(0, "Nowhere", "There's an abundance of nothing.");
        Place exit = new Place(1, "Exit", "This is the exit!");

        // Get number of directions
        line = CleanLineScanner.getCleanLine(sc);
        int numDirs = CleanLineScanner.extractInt(line);

        // Make directions
        for (int i = 0; i < numDirs; i++){
            Direction d = new Direction(sc, version);
        }

        // Get number of characters
        int numChars;
        if (version >= 40) {

            line = CleanLineScanner.getCleanLine(sc);
            numChars = CleanLineScanner.extractInt(line);

            // Create characters
            for (int i = 0; i < numChars; i++){
                Character c = CharacterFactory.makeCharacter(sc, version);
            }
        } else {
            Scanner ks = KeyboardScanner.getKeyBoardScanner();
            System.out.println("How many players would you like?");
            numChars = CleanLineScanner.extractInt(ks.nextLine());

            for (int i = 0; i < numChars; i++){
                System.out.println(String.format("What is Player %d's name?", (i+1)));
                String name = ks.nextLine();
                Player p = new Player(i, name, "A hero named " + name + ".", Place.firstPlace);
            }
        }



        // Get number of artifacts
        line = CleanLineScanner.getCleanLine(sc);
        int numArts = CleanLineScanner.extractInt(line);

        // Make Artifacts
        for (int i = 0; i < numArts; i++){
            Artifact a = new Artifact(sc, version);
        }

        // close the scanner
        sc.close();
    }

    public void play(){
        System.out.println(String.format("Welcome to %s.\n", name));
        do {
            for (Character c : Character.characters.values()){
                if (c instanceof Player){ // only prints if Character is a player
                    System.out.println(String.format("%s's turn.", c.name()));
                }
                if (c.makeMove()){ // if the Player has reached the exit we end the game.
                    return;
                }
            }
        } while (Character.characters.size() > 0);
        System.out.println("Exiting game...");
    }


}
