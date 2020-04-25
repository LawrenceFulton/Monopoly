import java.util.Scanner;
public class Main {

    private static Player[] initPlayers(int numberOfPlayer) {
        /* Create a few different animals*/
       Player[] playerArray = new Player[numberOfPlayer];
        System.out.println(numberOfPlayer);
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.println(i);
            playerArray[i] = new Player();
        }
        System.out.println("DEBUG#2");
        return playerArray;
    }

    public static void main(String[] args) {
        Player[] players;
        System.out.println("How many people are playing?");
        Scanner scanner = new Scanner(System.in);
        String line;
        int numberOfPlayer = scanner.nextInt();
        players = initPlayers(numberOfPlayer);
        System.out.println("DEBUG#3");
        Dice dice = new Dice();
	// write your code here

        System.out.println("DEBUG#4");
        Board board = new Board();

        System.out.println("DEBUG#1");
        while (true) {
            for (Player player : players ) {
                int position = player.getPosition();
                System.out.println("It's the turn of "+ player.getName());
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

                board.arriveOnField(position,player,dice.getCombinedDice());
/*
                System.out.println("has owner: "+ board.hasOwner(position));
*/

            }
            System.out.println("You want to proceed? type 'yes'");
            line = scanner.nextLine();
            if (!line.equals("yes")) {
                System.exit(1);
            }
        }
    }
}
