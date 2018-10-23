import java.util.Scanner;

public class Game {
    String name;
    int version;

    public Game(Scanner sc){
        String line;
        int numPlaces, i, numDirs, numChars, numArts;

        // Get game name
        line = CleanLineScanner.getCleanLine(sc);
        version = CleanLineScanner.extractInt(line);
        name = line.replaceAll("GDF|\\d|\\.", "").trim();

        // Get number of places
        line = CleanLineScanner.getCleanLine(sc);
        numPlaces = CleanLineScanner.extractInt(line);

        // Make places
        for (i = 0; i < numPlaces; i++){
            Place p = new Place(sc, version);
        }

        // Make exit and nowhere
        Place nowhere = new Place(0, "Nowhere", "There's an abundance of nothing.");
        Place exit = new Place(1, "Exit", "This is an exit.");

        // Get number of directions
        line = CleanLineScanner.getCleanLine(sc);
        numDirs = CleanLineScanner.extractInt(line);

        // Make directions
        for (i = 0; i < numDirs; i++){
            Direction d = new Direction(sc, version);
        }

        // Get number of characters
        line = CleanLineScanner.getCleanLine(sc);
        numChars = CleanLineScanner.extractInt(line);

        String charType;

        // Make characters
        for (i = 0; i < numChars; i++){
//            line = CleanLineScanner.getCleanLine(sc);
//            charType = line.replaceAll("\\d", "").trim();
//            if (charType.matches("PLAYER")){
//                Player player = new Player(sc, version);
//            } else {
//                NPC npc = new NPC(sc, version);
//            }
            Character c = new Character(sc, version);
        }

        // Get number of artifacts
        line = CleanLineScanner.getCleanLine(sc);
        numArts = CleanLineScanner.extractInt(line);

        // Make artifacts
        for (i = 0; i < numArts; i++){
            Artifact a = new Artifact(sc, version);
        }

        // close the scanner
        sc.close();
    }

    public void play(){
        System.out.println(String.format("Welcome to %s", name));
    }
}
