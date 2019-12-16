
/**
 * Write a description of SecondRatings here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            String currTitle = m.getTitle();
            if (currTitle.equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Double> ratings = new ArrayList<Double>();
        for (int i = 0; i < getRaterSize(); i++) {
            ArrayList<String> moviesRated = myRaters.get(i).getItemsRated();
            for (int j = 0; j < moviesRated.size(); j++) {
                if (moviesRated.get(j).equals(id)) {
                    ratings.add(myRaters.get(i).getRating(moviesRated.get(j)));
                }
            }
        }
        if (ratings.size() > minimalRaters){
            double average = 0.0;
            for (Double d : ratings) {
                average = average + d;
            }
            return average / ratings.size();
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (int i = 0; i < getMovieSize(); i++) {
            String movieID = myMovies.get(i).getID();
            double avg = getAverageByID(movieID, minimalRaters);
            Rating avgRating = new Rating(movieID, avg);
            if (avg > 0) {
                ratings.add(avgRating);
            }
        }
        return ratings;
    }
    
    public String getTitle(String id) {
        for (int i = 0; i < getMovieSize(); i++) {
            if (myMovies.get(i).getID().equals(id)){
                return myMovies.get(i).getTitle();
            }
        }
        return "ID was not found";
    }
}