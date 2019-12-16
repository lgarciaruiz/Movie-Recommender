import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class DirectorsFilter implements Filter{
    private String[] myDirectors;
    
    public DirectorsFilter(String Directors) {
        myDirectors = Directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        String directors = MovieDatabase.getDirector(id);
        
        for (int i = 0; i < myDirectors.length; i++) {
            int index = directors.indexOf(myDirectors[i]);
            if (index > -1) {
                return true;
            }
        }
        return false;
    }
}
