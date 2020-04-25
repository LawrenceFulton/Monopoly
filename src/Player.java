import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int money;
    private int position;
    ArrayList<Field> stations = new ArrayList<Field>();
    ArrayList<Field> municipalities = new ArrayList<Field>();
    ArrayList<Field> streets = new ArrayList<Field>();


    public Player() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please state your name:");
        this.name = scanner.nextLine();
        this.money = 2000;
        this. position = 0;
    }

    /**
     * Only used for the bank
     *
     * @param name
     */
    public Player(String name){
        this.name = name;
        this.money = 100000000;
    }

    /**
     * Enables trading
     *
     * @param scanner so we dont loose track of which line we shall be
     * @param board To look around on the board
     */
    public void trade (Scanner scanner, Board board) {
        System.out.println(name+ ", do you want to trade? (Y/N)");
        System.out.println("You have " + money + "available");
        String line = scanner.nextLine();
        while (!line.equals("Y") && !line.equals( "N")) {
            System.out.println("Please enter (Y/N)");
            line = scanner.nextLine();
        }
        if (line.equals("Y")) {
            System.out.println("Which prop you want to buy?");
            for (Field field : board.getFieldArray()){
                if (field instanceof Buyable) {
                    if ((!((Buyable) field).getOwner().getName().equals("Bank")  &&  !((Buyable) field).getOwner().getName().equals(this.name) ) ) {
                        System.out.println(field.getName() + " which belongs to " + ((Buyable) field).getOwner().getName());
                    }
                }
            }
            System.out.println("Enter the name of the prop:");

            line = scanner.nextLine();
            for (Field field: board.getFieldArray()){
                if (field.getName().equals(line)){
                    if (field instanceof Buyable) {
                        Player ownerOfProp = (((Buyable) field).getOwner());
                        System.out.println("This prop belongs to: " + ownerOfProp.getName());
                        System.out.println("How much do you offer?");
                        line = scanner.nextLine();
                        int price = Integer.parseInt(line);
                        System.out.println(ownerOfProp.getName() + " do you accept the offer of" + line + "? (Y/N)");
                        line = scanner.nextLine();
                        while (!line.equals("Y") && !line.equals("N")) {
                            System.out.println("Please enter (Y/N)");
                            line = scanner.nextLine();
                        }
                        if (line.equals("Y")){
                            this.increaseMoney(-price);
                            ownerOfProp.increaseMoney(price);
                            ((Buyable) field).setOwner(this);
                            addProperty(field);
                            System.out.println("New balance");
                            System.out.println("  "+name + ": "+money);
                            System.out.println("  "+ownerOfProp.getName()+ ": "+ ownerOfProp.getMoney());
                        }else{
                            System.out.println("No trade did happen!");
                        }

                    }
                }
            }
        }

    }

    public void getStats() {
        System.out.println("Player: "+name);
        System.out.println("Money: "+ money);
        System.out.println("Position: "+position);
        System.out.println("Propeties: ");
        for (Field station : stations){
            System.out.println(station.getName());
        }
        for (Field municipality : municipalities){
            System.out.println(municipality.getName());
        }
        for (Field street : streets){
            System.out.println(street.getName());
        }

    }

    public void addProperty (Field  field){
        if (field instanceof Station){
            addStation(field);
        } else if (field instanceof  Municipality){
            addMunicipality(field);
        } else  if(field instanceof  Street) {
            addStreet(field);
        }
    }

    public void addStation (Field station){
        stations.add(station);
    }
    public void addMunicipality (Field municipality){
        municipalities.add(municipality);
    }
    public void addStreet (Field street) {
        streets.add(street);
    }

    public int numberOfMunicipalities (){
        return municipalities.size();
    }

    public int numberOfStatios (){
        return stations.size();
    }

    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public int getPosition() {
        return position;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(int position) {
        this.position = position;
    }


    public void goForward (int position) {
        this.position += position;
    }

    public void increaseMoney (int money) {
        this.money += money;
    }
    public void decreaseMoney (int money) {
        this.money -= money;
    }
}

