import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
        int buyableObject = 0;


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
                        System.out.println("'" + field.getName() + "' which belongs to " + ((Buyable) field).getOwner().getName());
                        buyableObject = 1;
                    }
                }
            }
            if (buyableObject == 1) {
                System.out.println("Enter the name of the prop:");

                line = scanner.nextLine();
                for (Field field : board.getFieldArray()) {
                    if (field.getName().equals(line)) {
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
                            if (line.equals("Y")) {
                                this.increaseMoney(-price);
                                ownerOfProp.increaseMoney(price);
                                ((Buyable) field).setOwner(this);
                                addProperty(field);
                                System.out.println("New balance");
                                System.out.println("  " + name + ": " + money);
                                System.out.println("  " + ownerOfProp.getName() + ": " + ownerOfProp.getMoney());
                            } else {
                                System.out.println("No trade did happen!");
                            }

                        }
                    }
                }
            }else System.out.println("Unfortunately there is no tradable object on the market right now.");
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
    public void buildApartmani (Scanner scanner) {
        int[] colours = {2,3,3,3,3,3,3,2};
        for (Field street : streets) {
            if (street instanceof Street) {
                switch (((Street) street).getColour()){
                    case "brown":
                        colours[0]--;
                        break;
                    case "lightBlue":
                        colours[1]--;
                        break;
                    case "pink":
                        colours[2]--;
                        break;
                    case "orange":
                        colours[3]--;
                        break;
                    case "red":
                        colours[4]--;
                        break;
                    case "yellow":
                        colours[5]--;
                        break;
                    case "green":
                        colours[6]--;
                        break;
                    case "darkBlue":
                        colours[7]--;
                        break;
                    default:
                        System.out.println("There is a problem in the colours");
                }
            }
        }
        int decision;

        for (int i =0; i < 8; i++){
            if (colours[i] == 0){
                switch (i){
                    case 1:
                        System.out.println("You have all brown buildings. Apartments cost 500. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 500;
                        addHouses("brown", decision);
                        break;
                    case 2:
                        System.out.println("You have all light blue buildings. Apartments cost 500. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 500;
                        addHouses("lightBlue", decision);
                        break;
                    case 3:
                        System.out.println("You have all pink buildings. Apartments cost 1000. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 1000;
                        addHouses("pink", decision);
                        break;
                    case 4:
                        System.out.println("You have all orange buildings. Apartments cost 1000. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 1000;
                        addHouses("orange", decision);
                        break;
                    case 5:
                        System.out.println("You have all brown buildings. Apartments cost 1500. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 1500;
                        addHouses("red", decision);
                        break;
                    case 6:
                        System.out.println("You have all brown buildings. Apartments cost 1500. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 1500;
                        addHouses("yellow", decision);
                        break;
                    case 7:
                        System.out.println("You have all brown buildings. Apartments cost 2000. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 2000;
                        addHouses("green", decision);
                        break;
                    case 8:
                        System.out.println("You have all blue buildings. Apartments cost 2000. How many do you "+
                                "want to build?");
                        decision = scanner.nextInt();
                        money -= decision * 2000;
                        addHouses("blue", decision);
                        break;
                }

            }
        }



    }

    /**
     * Should only be used when the player has all the houses of one colour.
     * @param colour
     * @param houses the number of houses
     */
    private void addHouses (String colour, int houses) {
        ArrayList<Street> oneColour = new ArrayList<Street>();
        for (Field street : streets) {
            if( street instanceof Street ){
                if (((Street) street).getColour().equals(colour)){
                    oneColour.add((Street)street);
                }
            }
        }


        Collections.sort(oneColour, new HouseSorter());
        int pos = 0;
        for (int i = 0; i < houses; i++){
            pos = pos % oneColour.size();
            oneColour.get(pos).addHouse();
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

