public class Municipality extends Field implements Buyable{
    int price = 150;
    Player owner;
    public Municipality (String name ) {
        this.name = name;
        this.owner = new Bank();
    }


    public void payRent(Player player, int dice) {
        int rent ;

        if (owner.numberOfMunicipalities() == 2){
            rent = 8* dice;
        }else {
            rent = 4 * dice;
        }
        owner.increaseMoney(rent);
        player.increaseMoney(-rent);
    }

    public int getPrice() {
        return price;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.addMunicipality(this);
    }
    public Player getOwner() {
        return owner;
    }
}
