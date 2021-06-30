import java.util.*;
/**
 * Write a description of class EfficientRater here.
 *
 * @author (Suyash)
 * @version (a version number or a date)
 */
public class EfficientRater implements Rater
{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item))
            return true;          
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {//need to change
        
            if (myRatings.containsKey(item)){
                return myRatings.get(item).getValue();
            }
                
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {//might need change
        ArrayList<String> list = new ArrayList<String>();
        for(String i : myRatings.keySet()){
            list.add(myRatings.get(i).getItem());
        }
        
        return list;
    }
}
