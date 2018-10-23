import java.util.Scanner;

public class Player extends Character {
    private UI decisionmaker;

    public Player(Scanner sc, int version) {
        super(sc, version);
        decisionmaker = new UI();
    }
}
