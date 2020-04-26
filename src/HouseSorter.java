import java.util.Comparator;

public class HouseSorter implements Comparator<Street>
{
    @Override
    public int compare( Street street, Street street2){
        // your logic here
        if (street.getHouses() < street2.getHouses()){
            return  1;
        } else  if(street.getHouses() > street2.getHouses()) {
            return -1;
        } else return 0;
    }

}