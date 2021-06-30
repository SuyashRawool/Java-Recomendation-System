import java.util.Collections;
import java.util.ArrayList;
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 *
 * @author (Suyash Rawool)
 * @version (1.0)
 */
public class MovieRunnerSimilarRatings
{
    
    private static ArrayList<Rating> bubbleSort(ArrayList<Rating> ratings){
        int n=ratings.size();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if((ratings.get(j).compareTo(ratings.get(j+1)))==-1){
                    Collections.swap(ratings, j, j+1);
                }
            }
        }
        return ratings;
    }
    
    private static void movieDatabaseInit()
    {
        try
        {
            MovieDatabase.initialize("ratedmoviesfull");
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }catch(java.io.IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private static void printHeading(String heading)
    {
        System.out.println(heading+"\n");
    }

    public static void printAvergaeRatings() throws java.io.IOException, java.io.FileNotFoundException 
    {
        printHeading("printAvergaeRatings");
        FourthRatings tr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=bubbleSort(tr.getAverageRatings(35));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getTitle(r.getItem()));
        
        
    }
    
    
    public static void printAverageRatingsByYearAfterAndGenre() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByYearAfterAndGenre");
        AllFilters af=new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        
        FourthRatings tr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        
    
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        
        ratings=bubbleSort(tr.getAverageRatingsByFilter(8, af));
        System.out.println(ratings.size()+" movie matched");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+"\n   "+MovieDatabase.getGenres(r.getItem()));
    }

    public static void printSimilarRatings() throws java.io.IOException
    {
        FourthRatings fr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        String raterId="65";
        int simRaters=20,minRaters=5;
        ArrayList<Rating> list=fr.getSimilarRatings(raterId, simRaters,minRaters);
        for(Rating r : list)
            if(r.getValue()>0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+"  "+r.getValue());                
    }
    
    
    public static void printSimilarRatingsByGenre() throws java.io.IOException
    {
        System.out.println("By Genre");
        FourthRatings fr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        String raterId="65";
        int simRaters=20,minRaters=5;
        Filter f=new GenreFilter("Action");
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(raterId, simRaters,minRaters,f);
        for(Rating r : list)            
                System.out.println(MovieDatabase.getTitle(r.getItem())+"  "+r.getValue()+"\n   "+MovieDatabase.getGenres(r.getItem()));                
        
                
    }//Rush should be on 1st but its man of steel
    
    
    public static void printSimilarRatingsByDirector() throws java.io.IOException
    {
        System.out.println("By Director");
        FourthRatings fr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        String raterId="1034";
        int simRaters=10,minRaters=3;
        Filter f=new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(raterId, simRaters,minRaters,f);
        for(Rating r : list)
               System.out.println(MovieDatabase.getTitle(r.getItem())+"  "+r.getValue());                
    }//same thing happens here
    
    
    public static void printSimilarRatingsByGenreAndMinutes() throws java.io.IOException
    {
        System.out.println("Genre And Minutes");
        FourthRatings fr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        String raterId="65";
        int simRaters=10,minRaters=5;
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Adventure"));
        f.addFilter(new MinutesFilter(100,200));
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(raterId, simRaters,minRaters,f);
        for(Rating r : list)
                System.out.println(MovieDatabase.getTitle(r.getItem())+"  "+r.getValue()+"\n   "+MovieDatabase.getGenres(r.getItem()));                
    }
    
    
    public static void  printSimilarRatingsByYearAfterAndMinutes() throws java.io.IOException
    {
        System.out.println("Year After And Minutes");
        FourthRatings fr=new FourthRatings();
        movieDatabaseInit();
        RaterDatabase.initialize("ratings.csv");
        String raterId="314";
        int simRaters=10,minRaters=5;
        AllFilters f=new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(raterId, simRaters,minRaters,f);
        for(Rating r : list)
                System.out.println(MovieDatabase.getTitle(r.getItem())+"  "+r.getValue());                
    }
}
