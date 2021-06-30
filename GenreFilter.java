

public class GenreFilter implements Filter
{
    private String genre;
    
    public GenreFilter(String genre)
    {
        this.genre=genre;
    }

    @Override
    public boolean satisfies(String id){
        try
        {
            return MovieDatabase.getGenres(id).contains(this.genre);
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch(java.io.IOException e){
            e.printStackTrace();
        }
        return false;//here it might never come
    }
}
