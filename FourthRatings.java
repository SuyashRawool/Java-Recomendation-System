import java.text.DecimalFormat;
import java.util.*;
/**
 * Write a description of class FourthRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FourthRatings
{   
    
    private static DecimalFormat df = new DecimalFormat("0.0000");
    
    public FourthRatings()
    {
       
    }

    
    private double getAverageById(String id,int minimalRaters){
        int count=0;
        double sum=0;
        for(Rater r : RaterDatabase.getRaters()){
            if(r.hasRating(id)){
                count++;
                sum+=r.getRating(id);    
            }               
        }
        
        if(count>=minimalRaters){
            return (double)(sum/count);
        }else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        try
        {
            movies=MovieDatabase.filterBy(new TrueFilter());//filter wala first function
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }catch(java.io.IOException e)
        {
            e.printStackTrace();
        }
        
        double avgRating;
        for(String m : movies){
            avgRating=getAverageById(m, minimalRaters);
            if(avgRating!=0.0){
                Rating rating=new Rating(m, Double.parseDouble(df.format(avgRating)));
                ratings.add(rating);
            }          
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ArrayList<String> moviesId = new ArrayList<String>();
        try
        {
            moviesId=MovieDatabase.filterBy(filterCriteria);//Second function to filter
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }catch(java.io.IOException e)
        {
            e.printStackTrace();
        }
        
        double avgRating;
        for(String m : moviesId){//from movieId(string) to returning Ratings object from it
            avgRating=getAverageById(m, minimalRaters);
            if(avgRating!=0.0){
                Rating rating=new Rating(m, Double.parseDouble(df.format(avgRating)));
                ratings.add(rating);
            }          
        }
        return ratings;        
    }
    
    public int getMovieSize(){
        return MovieDatabase.size();
    }
    
    public int getRaterSize(){
        return RaterDatabase.size();
    }
    
    
    //new step-four methods
    private double dotProduct(Rater me ,Rater r){
        ArrayList<String> myMovieId=new ArrayList<String>();
        myMovieId=me.getItemsRated();
        double sum=0;
        for(String myId : myMovieId)
        {
            if(r.hasRating(myId))
                sum+=(me.getRating(myId)-5.0)*(r.getRating(myId)-5.0);//same Id for one movie 
            
            
        }
        return sum;
    }
    
    
    public ArrayList<Rating> getSimilarities(String id)//rater id
    {
        ArrayList<Rating> list=new ArrayList<>();
        Rater me=RaterDatabase.getRater(id);
        double dot;
        for(Rater r : RaterDatabase.getRaters())
        {
            if(!(r.getID().equals(me.getID())))
            {
                dot=dotProduct(me,r);
                if(dot>=0){
                    list.add(new Rating(r.getID(),dot));
                    
                }
                    
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    
    //side function to count
    private int countRaters(String mId,ArrayList<Rating> list,int numSimilarRaters) throws java.io.IOException,java.io.FileNotFoundException
    {
        int cnt=0;
        for(int k=0;k<numSimilarRaters;k++)
        {
            if(RaterDatabase.getRater(list.get(k).getItem()).hasRating(mId))
                cnt++;
        }
        
        
        return cnt;
    }

                                               //rater id
    public ArrayList<Rating> getSimilarRatings(String id , int numSimilarRaters, int minimalRaters) throws java.io.IOException,java.io.FileNotFoundException
    {
        ArrayList<Rating> ret=new ArrayList<Rating>();
        ArrayList<Rating> list=getSimilarities(id);
        int flag=0,cnt=0;
        double weightedAvg;
        for(String mId : MovieDatabase.filterBy(new TrueFilter()))
        {
            if(countRaters(mId, list, numSimilarRaters)>minimalRaters)
                continue;
                
            weightedAvg=0;
            cnt=0;
            for(int k=0;k<=numSimilarRaters;k++)
            {
                Rating r=list.get(k);
                weightedAvg+=r.getValue()*RaterDatabase.getRater(r.getItem()).getRating(mId);//Weight*movieRating
                cnt++;
            }    
                
            weightedAvg=weightedAvg/cnt;
            if(weightedAvg>0)
                ret.add(new Rating(mId,weightedAvg)); 
         
        }
                        
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id , int numSimilarRaters, int minimalRaters,Filter filterCriteria) throws java.io.IOException,java.io.FileNotFoundException
    {
        ArrayList<Rating> ret=new ArrayList<Rating>();
        ArrayList<Rating> list=getSimilarities(id);
        int flag=0,cnt;
        double weightedAvg;
        for(String mId : MovieDatabase.filterBy(filterCriteria))
        {
            if((countRaters(mId, list, numSimilarRaters)>=minimalRaters))
            {
                weightedAvg=0.0;
                cnt=0;
                for(int k=0;k<=numSimilarRaters;k++)
                {
                    Rating r=list.get(k);
                    weightedAvg+=r.getValue()*RaterDatabase.getRater(r.getItem()).getRating(mId);//Weight*movieRating
                    cnt++;
                }    
                
                weightedAvg=weightedAvg/cnt;
                if(weightedAvg>0)
                    ret.add(new Rating(mId,weightedAvg)); 
            }
                
            
         
        }
                        
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
    
}
