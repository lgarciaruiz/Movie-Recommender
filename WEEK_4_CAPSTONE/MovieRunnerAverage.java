import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        // assume moviefile and ratingsfile have been defined
        //SecondRatings runner = new SecondRatings(moviefile,ratingsfile);
        //System.out.println("read data for " + runner.getRaterSize() + " raters");
        //System.out.println("read data for " + runner.getMovieSize() + " movies")
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        int totalMovies = sr.getMovieSize();
        int totalRaters = sr.getRaterSize();
        System.out.println("Total movies " + totalMovies);
        System.out.println("Total raters " + totalRaters);
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + sr.getTitle(ID));
        }
    }
    
    public void getAverageRatingOneMovie() {
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        String movieID = sr.getID("Vacation");
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
        String ID = ratings.get(i).getItem();
            if (movieID.equals(ID)) {
            System.out.println(ratings.get(i).getValue());
        }
        }
    }
}
