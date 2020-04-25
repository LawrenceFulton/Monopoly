import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private Field[] fieldArray = new Field[40];

    public Board() {
        System.out.println("DEBUG#6");
        this.fieldArray[0] = new Go();
        System.out.println("DEBUG#7");
        this.fieldArray[1] = new Street("Old Kent Road", 60, "brown",2,10,30,90,160,250);
        System.out.println("DEBUG#8");
        this.fieldArray[2] = new Chance();
        System.out.println("DEBUG#5");
        this.fieldArray[3] = new Street("Whitechapel Road", 60, "brown",4,20,60,180,320,450);
        this.fieldArray[4] = new Tax(200);
        this.fieldArray[5] = new Station("King Cross Station");
        this.fieldArray[6] = new Street("The Angle Islington", 100, "lightBlue",6, 30,90,270,400,550);
        this.fieldArray[7] = new Chance();
        this.fieldArray[8] = new Street("Euston Road", 100, "lightBlue",6, 30,90,270,400,550);
        this.fieldArray[9] = new Street("Pentoville Road", 120, "lightBlue",8,40,100,300,450,600);
        this.fieldArray[10] = new Prison();
        this.fieldArray[11] = new Street("Pall Mall", 140, "pink" ,10,50,150,450,625, 750);
        this.fieldArray[12] = new Municipality("Water Municipality");
        this.fieldArray[13] = new Street("Whitehall", 140, "pink" ,10,50,150,450,625, 750);
        this.fieldArray[14] = new Street("Northhumrl'd Avenue", 160, "pink",12, 60,180,500,700,900);
        this.fieldArray[15] = new Station("Marylebone Station");
        this.fieldArray[16] = new Street("Bow Street", 180, "orange", 14,70,200,550,700,900);
        this.fieldArray[17] = new Chance();
        this.fieldArray[18] = new Street("Marlborough Street", 180, "orange", 14,70,200,550,700,900);
        this.fieldArray[19] = new Street("Vine Street", 200, "orange", 16,80, 200, 600, 800, 1000);
        this.fieldArray[20] = new Field();

        this.fieldArray[21] = new Street("Strand", 220, "red",18, 90,250,700,875,1050);
        this.fieldArray[22] = new Chance();
        this.fieldArray[23] = new Street("Fleet Street", 220, "red",18, 90,250,700,875,1050);
        this.fieldArray[24] = new Street("Trafalgar Square", 240, "red",20,100,300,750,925,1100);
        this.fieldArray[25] = new Station("Fenchurch St. Station");
        this.fieldArray[26] = new Street("Leicester Square", 260, "Yellow", 22,110,330,800,975, 1150);
        this.fieldArray[27] = new Street("Coventry Street", 260, "Yellow", 22,110,330,800,975, 1150);
        this.fieldArray[28] = new Municipality("Electrical Municipality");
        this.fieldArray[29] = new Street("Piccadilly", 280, "Yellow", 24, 120,200,850,1025,1200);
        this.fieldArray[30] = new Field();
        this.fieldArray[31] = new Street("Regrent Street", 300, "Green", 26,130, 390,900,1100, 1275);
        this.fieldArray[32] = new Street("Oxford Street", 300, "Green", 26,130, 390,900,1100, 1275);
        this.fieldArray[33] = new Chance();
        this.fieldArray[34] = new Street("Bond Street", 300, "Green", 28,150,450,1000,1200, 1400);
        this.fieldArray[35] = new Station("Liverpool St. Station");
        this.fieldArray[36] = new Chance();
        this.fieldArray[37] = new Street("Park Lane", 350, "Blue", 35, 175, 500, 1100, 1300, 1500);
        this.fieldArray[38] = new Tax(100);
        this.fieldArray[39] = new Street("May Fair", 350, "Blue", 50, 200,600,1400, 1700, 2000);
    }


    public String getNameOfField (int position) {
        System.out.println("debug");
        Field field = fieldArray[position];
        System.out.println("debug #3");

        return fieldArray[position].getName();
    }

    public Player getOwner (int position) {
        System.out.println("debug#5");
        Field field = fieldArray[position];
        if(field instanceof Street) {
            return ((Street) field).getOwner();
        } else if(field instanceof Station) {
            return ((Station) field).getOwner();
        } else {
            return ((Municipality) field).getOwner();
        }
    }

    public Player getOwner (Field field) {
        if(field instanceof Street) {
            return ((Street) field).getOwner();
        } else if (field instanceof Station) {
            return ((Station) field).getOwner();
        } else {
            return ((Municipality) field).getOwner();
        }
    }

    public boolean hasOwner (int position) {
        // if the propetry has an owner
        System.out.println("Debug#4");
        Player owner = getOwner(position);

        if (owner.getName().equals("Bank")) {
            return false;
        }else  return true;
    }

    public boolean hasOwner (Field field){
        //returns true if the field has a owner
        Player owner = getOwner(field);
        if (owner.getName().equals("Bank")) {
            return false;
        }else  return true;
    }

    public boolean isBuyableObject (int position ) {
        Field field = fieldArray[position];
        if (field instanceof Street  ||field instanceof Station || field instanceof Municipality) {
            return true;
        }
        return false;
    }

    public void arriveOnField(int position, Player player, int dice) {
        Scanner scanner = new Scanner(System.in);
        Field field = fieldArray[position];
        int change=0;

        if (field instanceof Go){
            player.increaseMoney(200);
        }else if (field instanceof Chance){
            player.increaseMoney(((Chance) field).pullCard());
        } else if (field instanceof Tax){
            player.increaseMoney(((Tax) field).getPrice());
        } else if (field instanceof Street) {
            if (this.hasOwner(field)){
                ((Street) field).renting(player);
                System.out.println("Player"+ player + "had to pay rent");
            }else{
                System.out.println(player+" You want to buy following property:"+field.getName()+ "with price "
                        + ((Street) field).getBuyingPrice()+ "(Y/N)");
                String line = scanner.nextLine();
                while (!line.equals("Y") && !(line.equals("N"))) {
                    System.out.println("please enter 'y' or 'n'");
                    line = scanner.nextLine();
                }
                if (line.equals("Y")){
                    // If you buy the property
                    change = -((Street) field).getBuyingPrice();
                    player.increaseMoney(change);
                    ((Street) field).setOwner(player);
                }
            }
        } else if (field instanceof Municipality) {
            if(this.hasOwner(field)){
                ((Municipality) field).payRent(player,dice);
            } else {
                System.out.println(player+" You want to buy following property:"+field.getName()+ "with price "
                        + ((Municipality) field).getPrice()+ "(Y/N)");
                String line = scanner.nextLine();
                while (!line.equals("Y") && !(line.equals("N"))) {
                    System.out.println("please enter 'y' or 'n'");
                    line = scanner.nextLine();
                }
                if (line.equals("Y")){
                    // If you buy the property
                    change = -((Municipality) field).getPrice();
                    player.increaseMoney(change);
                    ((Municipality) field).setOwner(player);
                }
            }
        }else if (field instanceof Station){
            if (hasOwner(field)) {
                ((Station) field).payRent(player);
            } else {
                System.out.println(player+" You want to buy following property:"+field.getName()+ "with price "
                        + ((Station) field).getPrice()+ "(Y/N)");
                String line = scanner.nextLine();
                while (!line.equals("Y") && !(line.equals("N"))) {
                    System.out.println("please enter 'y' or 'n'");
                    line = scanner.nextLine();
                }
                if (line.equals("Y")) {
                    // If you buy the property
                    change = -((Station) field).getPrice();
                    player.increaseMoney(change);
                    ((Station) field).setOwner(player);
                }
            }

        }


    }



}
