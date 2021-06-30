import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author Suyash
 */
public class MovieRunnerAverage {
    private ArrayList<Rating> bubbleSort(ArrayList<Rating> ratings){
        int n=ratings.size();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if((ratings.get(j).compareTo(ratings.get(j+1)))==1){
                    Collections.swap(ratings, j, j+1);
                }
            }
        }
        return ratings;
    }
    void printAvergaeRatings(){
        SecondRatings sr=new SecondRatings();
        System.out.println("Number of movies :"+sr.getMovieSize());
        System.out.println("Number of Raters :"+sr.getRaterSize());
        
        //print all movies with average ratings with specified number of ratings
        for(Rating r : bubbleSort(sr.getAverageRatings(12))){
            System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
        }
    }
    
    private double getAverageRating(ArrayList<Rating> AvgRatings,String movieId){
        for(Rating r : AvgRatings){
            if(r.getItem().equals(movieId)){
                return r.getValue();
            }
        }
        return 0.0;//not gonna come here (Already checked for not found case)
    }
    
    void getAverageRatingOneMovie(){
        SecondRatings sr=new SecondRatings();
        String title="3 idiots";
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=sr.getAverageRatings(0);
        
        //getting movieId from list of movies
        String movieId=sr.getId(title);
        if(movieId.equals("NO SUCH TITLE.")){
            System.out.println("Movie not found!");
            return;
        }
        
        //printing AvgRating of given Id
        System.out.println("Average rating of "+title+" "+getAverageRating(ratings, movieId));
        
    }
}
