public class Chance extends Field {

    public Chance() {
        super("Chance");
    }
    public int pullCard() {
        int x = (int)Math.random()*200;
        if (x>0) {
            System.out.println("You recieved "+x+ " money!");
        } else
            System.out.println("You payed "+x+ " money!");


        return (int)Math.random()*200;
    }

}
