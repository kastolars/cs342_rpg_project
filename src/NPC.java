import java.util.Scanner;

public class NPC extends Character {
    private AI decisionmaker;

    public NPC(Scanner sc, int version) {
        super(sc, version);
        decisionmaker = new AI();
    }
}
