import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Character {
    private int ID;
    private String name;
    private String description;
    public static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();

    public Character(Scanner sc, int version){}

    public Character(int ID, String name, String desc){
        this.ID = ID;
        this.name = name;
        this.description = desc;
    }

    public static Character getCharacterByID(int ID){
        return characters.get(ID);
    }

    public void makeMove(){}

    public void print(){
        System.out.println(String.format("ID: ", ID));
        System.out.println(String.format("Name: ", name));
        System.out.println(String.format("Description: ", description));
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }
}
