import java.util.Arrays;
import java.util.Scanner;

public class UI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        Scanner ks = KeyboardScanner.getKeyBoardScanner();
        System.out.println(String.format("What is thy bidding, %s?", c.name));
        String line = ks.nextLine();
        String action = line.split(" ")[0];
        String args = line.replaceAll(action, "").trim();
        return new Move(action, args);
    }
}
