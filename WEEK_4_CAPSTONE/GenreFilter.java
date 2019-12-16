import java.util.*;
/**
 * Write a description of GenreFilter here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre.trim();
    }
    
    @Override
    public boolean satisfies(String id){
        int index = MovieDatabase.getGenres(id).indexOf(",");
        if (index > -1){
            String [] genres = MovieDatabase.getGenres(id).split(", ");
            for (String s : genres) {
                if (s.equals(myGenre)){
                    return true;
                }
            }
        }
        if(MovieDatabase.getGenres(id).equals(myGenre)){
           return true;
        }
        return false;
    }
}
