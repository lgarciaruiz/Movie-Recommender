import java.util.*;

/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
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
        if (ratings.size() >= minimalRaters){
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (int i = 0; i < movies.size(); i++) {
            String movieID = movies.get(i);
            double avg = getAverageByID(movieID, minimalRaters);
            Rating avgRating = new Rating(movieID, avg);
            if (avg > 0) {
                ratings.add(avgRating);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minialRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (int i = 0; i < movies.size(); i++) {
            String movieID = movies.get(i);
            double avg = getAverageByID(movieID, minialRaters);
            Rating avgRating = new Rating(movieID, avg);
            if (avg > 0) {
                ratings.add(avgRating);
            }
        }
        return ratings;
    }
}
