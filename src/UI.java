import java.util.Arrays;
import java.util.Scanner;

public class UI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        c.currentPlace.display();
        Scanner ks = KeyboardScanner.getKeyBoardScanner();
        System.out.println("What is thy bidding?");
        String line = ks.nextLine();
        String args[] = line.split(" ");
        return new Move(args[0], Arrays.copyOfRange(args, 1, args.length - 1));
    }
}
