import java.util.Collections;
import java.util.ArrayList;

public class MovieRunnerWithFilter
{
    
    public MovieRunnerWithFilter()
    {
        

    }
    
    
    
    private static ArrayList<Rating> bubbleSort(ArrayList<Rating> ratings){
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
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=bubbleSort(tr.getAverageRatings(35));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getTitle(r.getItem()));
        
    }
    
    
    public static void printAverageRatingsByYear() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByYear");
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        int year=2000;
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=bubbleSort(tr.getAverageRatingsByFilter(20, new YearAfterFilter(year)));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
    }
    
    
    public static void printAverageRatingsByGenre() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByGenre");
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        String genre="Comedy";
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=bubbleSort(tr.getAverageRatingsByFilter(20, new GenreFilter(genre)));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getTitle(r.getItem())+"\n   "+MovieDatabase.getGenres(r.getItem()));
    }
    
    
    public static void printAverageRatingsByMinutes() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByMinutes");
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        int min=105,max=135;
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        ratings=bubbleSort(tr.getAverageRatingsByFilter(5, new MinutesFilter(min,max)));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
    }
    
    
    public static void printAverageRatingsByDirectors() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByDirectors");
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        String directors="Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        
        ratings=bubbleSort(tr.getAverageRatingsByFilter(4, new DirectorsFilter(directors)));
        System.out.println("found "+ratings.size()+" movies");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getTitle(r.getItem())+"\n   "+MovieDatabase.getDirector(r.getItem()));
    }
    
    
    public static void printAverageRatingsByYearAfterAndGenre() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByYearAfterAndGenre");
        AllFilters af=new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        
    
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        
        ratings=bubbleSort(tr.getAverageRatingsByFilter(8, af));
        System.out.println(ratings.size()+" movie matched");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+"\n   "+MovieDatabase.getGenres(r.getItem()));
    }
    
    
    public static void printAverageRatingsByDirectorsAndMinutes() throws java.io.IOException, java.io.FileNotFoundException
    {
        printHeading("printAverageRatingsByDirectorsAndMinutes");
        AllFilters af=new AllFilters();
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        af.addFilter(new MinutesFilter(90,180));
        
        ThirdRatings tr=new ThirdRatings();
        movieDatabaseInit();
        
        System.out.println("read data for "+tr.getMovieSize()+" movies");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        
        
    
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        
        ratings=bubbleSort(tr.getAverageRatingsByFilter(3, af));
        System.out.println(ratings.size()+" movies matched");
        for(Rating r : ratings)
            System.out.println(r.getValue()+"  Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+"\n   "+MovieDatabase.getDirector(r.getItem()));
    }
}
