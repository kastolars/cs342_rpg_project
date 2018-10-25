import java.util.Random;
import java.util.Scanner;

public class Artifact {
    private int version;
    private int ID;
    private String name;
    private String description;
    private int value;
    private int mobility;
    private int keyPattern;

    public Artifact(Scanner sc, int version){
        description = "";

        // Location ID
        String line = CleanLineScanner.getCleanLine(sc);
        int placeOrCharID = Integer.valueOf(line);

        // Metadata
        line = CleanLineScanner.getCleanLine(sc);
        String[] arr = line.split("\\s+");
        ID = Integer.valueOf(arr[0]);
        value = Integer.valueOf(arr[1]);
        mobility = Integer.valueOf(arr[2]);
        keyPattern = Integer.valueOf(arr[3]);
        name = line.replaceAll("\\d", "").trim();

        // Lines for description
        line = CleanLineScanner.getCleanLine(sc);
        int count = Integer.valueOf(line);

        // Description
        for (int i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }

        // Place artifact at location
        if (placeOrCharID < 0){
            Character.getCharacterByID(Math.abs(placeOrCharID)).addArtifact(this);
        } else if (placeOrCharID > 0) {
            Place.getPlaceByID(Math.abs(placeOrCharID)).addArtifact(this);
        } else {
            Place p;
            do {
                p = (Place) Place.places.values().toArray()[new Random().nextInt(Place.places.size())];
            } while (p.isExit());
            p.addArtifact(this);
        }
    }

    public void use(Character c, Place p){}

    public boolean match(String s){
        return name.matches("(?i)" + s);
    }

    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
        System.out.println(String.format("Value: %d", value));
        System.out.println(String.format("Mobility: %d", mobility));
        System.out.println(String.format("KeyPattern: %d", keyPattern));
    }

    public String name(){
        return name;
    }

    public void display(){
        System.out.println("<" + name + ">");
        System.out.println(description);
    }

    public int mobility() {
        return mobility;
    }

    public int getKeyPattern() {
        return keyPattern;
    }
}
