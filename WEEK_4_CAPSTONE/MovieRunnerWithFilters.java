import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class MovieRunnerWithFilters {
    
    public void printAverageRatings() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getTitle(ID));
        }
        System.out.println("Total movies with this rating " + ratings.size());
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
    
    public void printAverageRatingsByYear() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getTitle(ID) + " " + MovieDatabase.getYear(ID));
        }
        System.out.println("Total movies with this rating " + ratings.size());
    }
    
    public void printAverageRatingsByGenre() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getTitle(ID) + "\n" + MovieDatabase.getGenres(ID));
        }
        System.out.println("Total movies with this genre/rating " + ratings.size());
    }
    
    public void printAverageRatingsByMinutes() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new MinutesFilter(105,135);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " Time: " + MovieDatabase.getMinutes(ID) + " " + MovieDatabase.getTitle(ID));
        }
        System.out.println("Total movies with this minutes " + ratings.size());
    }
    
    public void printAverageRatingsByDirectors() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getTitle(ID) + "\n" + MovieDatabase.getDirector(ID));
        }
        System.out.println("Total movies with this director(s) " + ratings.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new AllFilters();
        ((AllFilters)filter).addFilter(new YearAfterFilter(1990));
        ((AllFilters)filter).addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getYear(ID) + " " + MovieDatabase.getTitle(ID) + "\n" + MovieDatabase.getGenres(ID));
        }
        System.out.println("Total movies with this year/genre " + ratings.size());
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int totalRaters = tr.getRaterSize();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new AllFilters();
        ((AllFilters)filter).addFilter(new MinutesFilter(90,180));
        ((AllFilters)filter).addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " Time: " + MovieDatabase.getMinutes(ID) + " " + MovieDatabase.getTitle(ID) + "\n" + MovieDatabase.getDirector(ID));
        }
        System.out.println("Total movies with this time/directors " + ratings.size());
    }
}
