import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Leo Garcia
 * @version v1
 */
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        ArrayList<Rating> ratings = fr.getAverageRatings(35);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
            String ID = ratings.get(i).getItem();
            double avg = ratings.get(i).getValue();
            System.out.println(avg + " " + MovieDatabase.getTitle(ID));
        }
        System.out.println("Total movies with this rating " + ratings.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new AllFilters();
        ((AllFilters)filter).addFilter(new YearAfterFilter(1990));
        ((AllFilters)filter).addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(8,filter);
        //sort from smallest to highest rated movie
        Collections.sort(ratings);
        for (int i = 0; i < ratings.size(); i++){
           String ID = ratings.get(i).getItem();
           double avg = ratings.get(i).getValue();
           System.out.println(avg + " " + MovieDatabase.getYear(ID) + " " + MovieDatabase.getTitle(ID) + "\n" + MovieDatabase.getGenres(ID));
        }
        System.out.println("Total movies with this year/genre " + ratings.size());
    }
    
    public void printSimilarRatings(){
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        ArrayList<Rating> topMovies = fr.getSimilarRatings("71",20,5);
        for (Rating r : topMovies) {
            String movie = r.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " weighted average is " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre(){
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        ArrayList<Rating> topMovies = fr.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
        for (Rating r : topMovies) {
            String movie = r.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " weighted average is " + r.getValue() + "\nGenre" + MovieDatabase.getGenres(movie));
        }
    }
   
    public void printSimilarRatingsByDirector(){
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        ArrayList<Rating> topMovies = fr.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating r : topMovies) {
            String movie = r.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " weighted average is " + r.getValue() + "\nDirectors" + MovieDatabase.getDirector(movie));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new AllFilters();
        ((AllFilters)filter).addFilter(new MinutesFilter(80,160));
        ((AllFilters)filter).addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> topMovies = fr.getSimilarRatingsByFilter("168",10,3,filter);
        for (Rating r : topMovies) {
            String movie = r.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " weighted average is " + r.getValue() + "\nMinutes" + MovieDatabase.getMinutes(movie));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int totalRaters = RaterDatabase.size();
        System.out.println("Total movies read " + MovieDatabase.size());
        System.out.println("Total raters read " + totalRaters);
        Filter filter = new AllFilters();
        ((AllFilters)filter).addFilter(new MinutesFilter(70,200));
        ((AllFilters)filter).addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> topMovies = fr.getSimilarRatingsByFilter("314",10,5,filter);
        for (Rating r : topMovies) {
            String movie = r.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " weighted average is " + r.getValue() + "\nMinutes " + MovieDatabase.getMinutes(movie) + " Year " + MovieDatabase.getYear(movie));
        }
    }
}
