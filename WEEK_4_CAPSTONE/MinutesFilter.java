
/**
 * Write a description of MinutesFilter here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class MinutesFilter implements Filter{
    private int myMinMin;
    private int myMaxMin;

    public MinutesFilter(int minMin, int maxMin) {
    	myMinMin = minMin;
    	myMaxMin = maxMin;
    }
    
    @Override
    public boolean satisfies(String id) {
    	return MovieDatabase.getMinutes(id) >= myMinMin && MovieDatabase.getMinutes(id) <= myMaxMin;
    }
}
