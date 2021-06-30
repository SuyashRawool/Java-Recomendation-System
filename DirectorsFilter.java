
public class DirectorsFilter implements Filter
{
    private String directors;
    
    public DirectorsFilter(String directors)
    {
        this.directors = directors;
        
    }

    @Override
    public boolean satisfies(String id){
        String directorsList[]=this.directors.split(",");
        try
        {
            for(String dir : directorsList){
                 if(MovieDatabase.getDirector(id).contains(dir))
                 return true;
            }
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
