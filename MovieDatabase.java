import java.util.*;
import org.apache.commons.csv.*;
import java.io.IOException;
import java.io.FileNotFoundException;

//import edu.duke.FileResource;//I have made normal classes of this resources since there was a problem in importing

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    public static void initialize(String moviefile) throws FileNotFoundException, IOException{
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies(moviefile);
        }
    }

    private static void initialize() throws FileNotFoundException, IOException{
        if (ourMovies == null) {//it execute only when explicit file is not set first
            ourMovies = new HashMap<String,Movie>();
            loadMovies("ratedmoviesfull");
            //loadMovies("ratedmovies_short");
        }
    }    

    
    private static void loadMovies(String filename) throws FileNotFoundException, IOException{
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);//Using FirstRating to get data
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    public static boolean containsID(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) throws FileNotFoundException, IOException{
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f) throws FileNotFoundException, IOException{
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }

}
