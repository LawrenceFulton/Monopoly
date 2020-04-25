public class Street extends Field implements Buyable {
    private final int buyingPrice;
    private int houses;
    private final String colour;
    private Player owner;
    private final int p0;
    private final int p1;
    private final int p2;
    private final int p3;
    private final int p4;
    private final int p5;


    Street(String name, int buyingPrice, String colour,int p0, int p1, int p2, int p3, int p4,int p5) {
        owner = new Bank();
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.houses = 0;
        this.colour =colour;
        System.out.println("DEBUG#8");
        this.owner = new Bank();
        System.out.println("DEBUG#9");
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.houses = 0;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
        owner.addStreet(this);
    }

    public Player getOwner() {
        return owner;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void renting(Player player) {
        int rent;
        switch (houses) {
            case 1:
                rent = p1;
                break;
            case 2:
                rent = p2;
                break;
            case 3:
                rent = p3;
                break;
            case 4:
                rent = p4;
                break;
            case 5:
                rent = p5;
                break;
            default:
                rent = p0;
        }
        owner.increaseMoney(rent);
        player.increaseMoney(-rent);
    }

}
