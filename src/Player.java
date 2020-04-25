import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int money;
    int position;
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
    public Player(String name){//only used for bank
        this.name = name;
        this.money = 100000000;
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
