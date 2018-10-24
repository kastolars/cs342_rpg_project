import java.util.Random;
import java.util.Scanner;

public class CharacterFactory {

    public static Character makeCharacter(Scanner sc, int version){

        String description = "";

        String line = CleanLineScanner.getCleanLine(sc);

        String charType = line.replaceAll("\\d", "").trim();
        int placeID = CleanLineScanner.extractInt(line);


        line = CleanLineScanner.getCleanLine(sc);
        int ID = CleanLineScanner.extractInt(line);
        String name = line.substring(line.indexOf(String.valueOf(ID)) + String.valueOf(ID).length()).trim();
        line = CleanLineScanner.getCleanLine(sc);
        int count = CleanLineScanner.extractInt(line);

        // Complete description
        for (int i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }

        if (charType.matches("PLAYER")) {
            return new Player(ID, name, description, placeID);
        } else {
            return new NPC(ID, name, description, placeID);
        }

    }
}
