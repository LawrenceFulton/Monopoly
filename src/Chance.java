public class Chance extends Field {

    public Chance() {
        super("Chance");
    }
    public int pullCard() {
        return (int)Math.random()*200;
    }

}
