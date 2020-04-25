public class Dice {
    private int die1;
    private int die2;
    private int combinedDice;


    public void roll() {
        die1 = (int)(Math.random()*6) + 1;
        die2 = (int)(Math.random()*6) + 1;
        combinedDice = die1 + die2;
    }
    public boolean escapePrison() {
        for (int i =0; i < 3 ; i++) {
            die1 = (int)(Math.random()*6) + 1;
            die2 = (int)(Math.random()*6) + 1;
            System.out.println("dice: " +die1 +die2);
            if (die1 == die2) {
                return true;
            }
        }
    return false;
    }
    public void getBothDice () {
        System.out.println("die1:" +die1+ " and die2: "+ die2);
    }

    public int getCombinedDice () {
        return combinedDice;
    }

}
