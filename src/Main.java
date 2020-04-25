import java.util.Scanner;
public class Main {

    private static Player[] initPlayers(int numberOfPlayer) {
        /* Create a few different animals*/
       Player[] playerArray = new Player[numberOfPlayer];
        System.out.println(numberOfPlayer);
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.println("Player "+(i+1));
            playerArray[i] = new Player();
        }
        return playerArray;
    }

    public static void main(String[] args) {
        Player[] players;
        System.out.println("How many people are playing?");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int numberOfPlayer =  Integer.parseInt(line);
        players = initPlayers(numberOfPlayer);
        Dice dice = new Dice();
	// write your code here

        Board board = new Board();

        while (true) {
            for (Player player : players ) {
                System.out.println("It's the turn of "+ player.getName());
                player.getStats();
                int position = player.getPosition();
                dice.roll();
                System.out.println("You rolled a "+ dice.getCombinedDice());
                position +=  dice.getCombinedDice();
                if (position >= 40) {
                    position -= 40;
                    player.increaseMoney(100);
                }
                player.setPosition(position);
                System.out.println("You proceded to position "+ position );
                System.out.println("You are on field "+ board.getNameOfField(position));

                board.arriveOnField(position, player, dice.getCombinedDice(), scanner);
                player.trade(scanner,board);
            }
            System.out.println("You want to proceed? type 'yes' if the case else 'no'");
            line = scanner.nextLine();
            if (line.equals("no")) {
                System.exit(1);
            }
        }
    }
}
