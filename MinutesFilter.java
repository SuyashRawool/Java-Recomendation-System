

public class MinutesFilter implements Filter
{
    private int min;
    private int max;
    
    public MinutesFilter(int min,int max)
    {
        this.min=min;
        this.max=max;
    }

    @Override
    public boolean satisfies(String id){
        try
        {
            return (MovieDatabase.getMinutes(id)<=this.max && MovieDatabase.getMinutes(id)>=this.min);
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
