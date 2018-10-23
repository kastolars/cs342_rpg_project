import java.util.Scanner;

public class UI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        String action;
        Scanner ks = KeyboardScanner.getKeyBoardScanner();
        System.out.println("What is thy bidding?");
        action = ks.nextLine();

        return null;
    }
}
