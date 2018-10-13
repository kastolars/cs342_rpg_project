import java.util.Scanner;

public class Artifact {
    private int ID;
    private String name;
    private String description;
    private int value;
    private int mobility;
    private int keyPattern;

    public Artifact(Scanner sc, int version){}

    public void use(Character c, Place p){}

    public boolean match(String s){
        return name.matches(s);
    }

    public void print(){
        System.out.println(String.format("ID: ", ID));
        System.out.println(String.format("Name: ", name));
        System.out.println(String.format("Description: ", description));
        System.out.println(String.format("Value: ", value));
        System.out.println(String.format("Mobility: ", mobility));
        System.out.println(String.format("KeyPattern: ", keyPattern));
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }

}
