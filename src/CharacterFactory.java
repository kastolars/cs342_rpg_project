/*
Author: Karol Stolarski
netID: kstola2

The character factory is follows the design pattern of a Factory.
The scanner is passed here and extracts all the information
to construct a character. Depending on if the character is a
NPC or a Player, it will build one versus the other.
 */

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

        // Checks to see what sort of Character is being produced
        if (charType.matches("PLAYER")) {
            return new Player(ID, name, description, placeID);
        } else {
            return new NPC(ID, name, description, placeID);
        }

    }
}
