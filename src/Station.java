public class Station extends Field implements Buyable{
    private int price = 200;
    private Player owner;

    Station(String name) {

        this.name = name;
        this.owner = new Bank();
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.addStation(this);
    }
    public void  payRent (Player player){
        int number = owner.numberOfStatios();
        int rent = 50* number;
        owner.increaseMoney(rent);
        player.increaseMoney(-rent);
    }

    public int getPrice() {
        return price;
    }
}
