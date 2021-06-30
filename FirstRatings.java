 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of class FirstRatings here.
 *
 * @author (Suyash Rawool)
 * @version (11-05-2021)
 */
public class FirstRatings
{
    private ArrayList<Movie> movie;
    public FirstRatings()
    {
       movie =new ArrayList<Movie>();
    }
    
    
    //the File loading functions
    public ArrayList<Movie> loadMovies(String filename) throws FileNotFoundException, IOException{
        Reader in = new FileReader("./data/"+filename+".csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            
            //getting each column
            String id = record.get("id");
            String title= record.get("title");
            String year=record.get("year");
            String genres= record.get("genre");
            String director= record.get("director");
            String country= record.get("country");
            String poster= record.get("poster");
            int minutes=Integer.parseInt(record.get("minutes"));
            
            //loading in ArrayList
            movie.add(new Movie(id,title,year,genres,director,country,poster,minutes));
              
        }//for end 
        return movie;
    }//function end
    
    public ArrayList<EfficientRater> loadRaters(String filename) throws FileNotFoundException, IOException{
        ArrayList<EfficientRater> rater=new ArrayList<EfficientRater>();
        ArrayList<Rating> rating=new ArrayList<Rating>();
        Reader in = new FileReader("./data/"+filename+".csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        int prev=0,i=-1;
        for(CSVRecord record : records){
            int rater_id=Integer.parseInt(record.get("rater_id"));
            String movie_id=record.get("movie_id");
            double value=Double.parseDouble(record.get("rating"));
            if(rater_id!=prev){
                i++;
                rater.add(new EfficientRater(rater_id+""));//new rater is added only when its a new one
                rater.get(i).addRating(movie_id, value);
            }else{//when there are more ratings done by one rater
                rater.get(i).addRating(movie_id, value);
            }
            prev=rater_id;
            
        }
        
        return rater;
    }
    
    
    //testLoadMovies sub functions
    public static int checkGenre(ArrayList<Movie> movie,String genre){
        int count =0;
        for(Movie m:movie){
            if(m.getGenres().contains(genre))
                count++;
        }
        return count;
    }
    
     public static int great150Min(ArrayList<Movie> movie){
         int count =0;
         for(Movie m:movie){
            if(m.getMinutes()>150)
                count++;
        }
         return count;
     }
     
     public static void maxMovieDirector(ArrayList<Movie> movie){
         ArrayList<String> directors=new ArrayList<String>();
         String tDirectors[];
         String tDirector;
         int count[];
         
         for(Movie m : movie){
             tDirector=m.getDirector();
             if(tDirector.contains(",")){
                 tDirectors=m.getDirector().split(",");
                 for(String t : tDirectors)
                     directors.add(t);
             }else{
                 directors.add(tDirector);
             }
        }
         
        int c;
        count=new int[directors.size()];
        for(int i=0;i<directors.size();i++){
            c=0;
            String outDir=directors.get(i);
            for(int j=0;j<directors.size();j++){
                if(outDir.equals(directors.get(j)))
                    c++;
            }
            count[i]=c;
        }
        
        Integer max;
        max = Arrays.stream(count).max().getAsInt();
        
         System.out.println("The Maximum no. of movies :"+max);
         System.out.println("And the Directors :");
         int prev=1;
         for(int i=0;i<count.length;i++){
             if(directors.get(prev).equals(directors.get(i)))//need changes wrong logic!
                 continue;
             if(count[i]==max){
                 System.out.println(directors.get(i));
                 prev=i;
             }
                 
         }
     }
//End of testLoadMovies sub functions  
     
//testLoadRaters sub functions
     public static void maxRater(ArrayList<EfficientRater> rater){
        int max=0,maxRater=0;
        for(EfficientRater r : rater){
            if(r.numRatings()>max)
                max=r.numRatings();
        }
        
        for(EfficientRater r : rater){
            if(r.numRatings()==max){
                System.out.println("Rater ID: "+r.getID()+" with "+max+" ratings");
                maxRater++;
            }
        }
        System.out.println("Total number of Max Rater: "+maxRater);
    }
    
    public static void totalRatings(ArrayList<EfficientRater> rater,String movieId){
        ArrayList<String> moviesId=new ArrayList<String>();
        for(EfficientRater r : rater){
            for(String s : r.getItemsRated()){
                moviesId.add(s);
            }
        }
        
        int count=0;
        for (String s : moviesId){
            if(movieId.equals(s))
                count++;
        }
        System.out.println("\n"+movieId+" is rated by "+count+" raters");
        
    }
    
    public static void totalMovies(ArrayList<EfficientRater> rater) {
        ArrayList<String> moviesId=new ArrayList<String>();
        ArrayList<String> moviesId2=new ArrayList<String>();
        for(EfficientRater r : rater){
            for(String s : r.getItemsRated()){
                moviesId.add(s);
            }
        }
        
        
        for(String movie : moviesId){
            if(!moviesId2.contains(movie))
                moviesId2.add(movie);
        }
        System.out.println("Total Exclusive movies rated :"+moviesId2.size());
    }
    
    public static void raterData(ArrayList<EfficientRater> rater,String id){
        for(EfficientRater r : rater){
            if(id.equals(r.getID())){
                System.out.println("Rater ID: "+r.getID()+" Total movies rated: "+r.numRatings());
            
            for(String s:r.getItemsRated())
                System.out.println("  "+s+"   "+r.getRating(s));
            System.out.println();
            }            
        }
    }
    
    public static void allRaters(ArrayList<EfficientRater> rater){
        for(EfficientRater r : rater){
            
            System.out.println("Rater ID: "+r.getID()+" Total movies rated: "+r.numRatings());
            
            for(String s:r.getItemsRated())
                System.out.println("  "+s+"   "+r.getRating(s));
            System.out.println();
                        
        }
    }
//End of testLoadRaters sub functions
    
    
//test functions are below
    public static void testLoadMovies() throws IOException{
         FirstRatings fr=new FirstRatings();
        //ArrayList<Movie> movie= fr.loadMovies("ratedmovies_short");
        ArrayList<Movie> movie=fr.loadMovies("ratedmoviesfull");
        System.out.println("Total movies :"+movie.size());
        
        System.out.println("No. of movies of specified genre: "+checkGenre(movie, "Comedy"));
        
        System.out.println("No. of movies greter than 150 min: "+great150Min(movie));
        
        maxMovieDirector(movie);
     }
     
     public static void testLoadRaters() throws IOException{
         FirstRatings fr=new FirstRatings();
        //ArrayList<PlainRater> rater=fr.loadRaters("ratings_short");
        ArrayList<EfficientRater> rater=fr.loadRaters("ratings");
        
        //1st solution
        //allRaters(rater);
        
        //2nd solution
        raterData(rater, "2");
        
        //3rd solution
        maxRater(rater);
        
        //4th solution
        totalRatings(rater, "1798709");
        
        //5th solution
        totalMovies(rater);
     }
     
     

}
