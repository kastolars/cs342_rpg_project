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
        Place exit = new Place(1, "Exit", "This is an exit.");

        // Get number of directions
        line = CleanLineScanner.getCleanLine(sc);
        int numDirs = CleanLineScanner.extractInt(line);

        // Make directions
        for (int i = 0; i < numDirs; i++){
            Direction d = new Direction(sc, version);
        }

        // Get number of characters
        line = CleanLineScanner.getCleanLine(sc);
        int numChars = CleanLineScanner.extractInt(line);

        String charType;

        // Create characters
//        for (int i = 0; i < numChars; i++){
//            line = sc.next();
//            charType = line.trim();
//            if (charType.matches("PLAYER")){
////                Character c = new Player(sc, version);
//            } else {
////                Character c = new NPC(sc, version);
//            }
//        }

        Character c = CharacterFactory.makeCharacter(sc, version);
        c = CharacterFactory.makeCharacter(sc, version);
        c = CharacterFactory.makeCharacter(sc, version);
        c = CharacterFactory.makeCharacter(sc, version);

//        line = CleanLineScanner.getCleanLine(sc);
//        Character c = new Character(sc, version);
//        line = CleanLineScanner.getCleanLine(sc);
//        c = new Character(sc, version);
//        line = CleanLineScanner.getCleanLine(sc);
//        c = new Character(sc, version);
//        line = CleanLineScanner.getCleanLine(sc);
//        c = new Character(sc, version);
        return;


//
//        // Get number of artifacts
//        line = CleanLineScanner.getCleanLine(sc);
//        int numArts = CleanLineScanner.extractInt(line);
//
//        // Make artifacts
//        for (i = 0; i < numArts; i++){
//            Artifact a = new Artifact(sc, version);
//        }

        // close the scanner
//        sc.close();
    }

    public void play(){
        System.out.println(String.format("Welcome to %s", name));
    }
}
