import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
        public FourthRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public FourthRatings(String ratingsFile) {
        RaterDatabase.addRatings(ratingsFile);
    }
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Double> ratings = new ArrayList<Double>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater rater : raters) {
            ArrayList<String> moviesRated = rater.getItemsRated();
            for (int j = 0; j < moviesRated.size(); j++) {
                if (moviesRated.get(j).equals(id)) {
                    ratings.add(rater.getRating(moviesRated.get(j)));
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
    
    
    //get dotProduct of rater to myself to see how similar we are
    //helps in getting the weighted average by seeing how close raters are to me
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        ArrayList<String> myRatings = me.getItemsRated();
        ArrayList<String> otherRatings = r.getItemsRated();
        for (String id : myRatings) {
            //get rating minus 5 to center result
            double rating = me.getRating(id) - 5;
            if (otherRatings.contains(id)) {
                //get other rating to same movie minus 5 to center result
                double otherRating = r.getRating(id) - 5;
                //add all ratings together
                dotProduct = (otherRating * rating) + dotProduct;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similars = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for(Rater rater : raters) {
            if (!rater.equals(RaterDatabase.getRater(id))){
                double dotProdcut = dotProduct(RaterDatabase.getRater(id), rater);
                if (dotProdcut > -1){
                    Rating rating = new Rating(rater.getID(),dotProdcut);
                    similars.add(rating);
                }
            }
        }
        Collections.sort(similars, Collections.reverseOrder());
        return similars;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies){
            double weightedAvg = 0.0;
            int count = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = list.get(i);
                Rater rater = RaterDatabase.getRater(r.getItem());
                ArrayList<String> moviesRatedByRater = rater.getItemsRated();
                if(moviesRatedByRater.contains(movieID)){
                   count += 1;
                   weightedAvg = (rater.getRating(movieID) * r.getValue()) + weightedAvg;
                }
            }
            if(count >= minimalRaters){
                weightedAvg = weightedAvg / count;
                similarRatings.add(new Rating(movieID,weightedAvg));
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    } 
}
