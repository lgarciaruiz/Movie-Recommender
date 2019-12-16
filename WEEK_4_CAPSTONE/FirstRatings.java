import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class FirstRatings {
    private ArrayList<Movie> movieList;
    private ArrayList<Rater> raterList;
    
    public FirstRatings() {
        movieList = new ArrayList<Movie>();
        raterList = new ArrayList<Rater>();
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        movieList.clear();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String anID = record.get(0);
            String aTitle = record.get(1);
            String aYear = record.get(2);
            String aCountry = record.get(3);
            String theGenres = record.get(4);
            String aDirector = record.get(5);
            int theMinutes = Integer.parseInt(record.get(6));
            String aPoster = record.get(7);
            Movie movie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            movieList.add(movie);
        }
        return movieList;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        raterList.clear();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String anID = record.get(0);
            anID = anID.trim();
            Rater rater = new EfficientRater(anID);
            if (raterList.isEmpty()) {
                raterList.add(rater);
                rater.addRating(record.get(1),Double.parseDouble(record.get(2)));
            }
            else{
                boolean added = false;
                for (int i = 0; i < raterList.size(); i++) {
                    Rater currRater = raterList.get(i);
                    if (currRater.getID().equals(anID)) {
                        currRater.addRating(record.get(1),Double.parseDouble(record.get(2)));
                        added = true;
                    }
                }
                if (added == false){
                    raterList.add(rater);
                    rater.addRating(record.get(1),Double.parseDouble(record.get(2)));
                }
            }
        }   
        return raterList;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> list = loadRaters("data/ratings.csv");
        //ArrayList<Rater> list = loadRaters("data/ratings.csv");
        HashMap<String,Integer> numRatings = new HashMap<String,Integer>();
        for (Rater rater : list) {
            String raterId = rater.getID();
            numRatings.put(raterId,rater.getItemsRated().size());
        }
        
        //find max amount of ratings
        //retun amount of raters with this rating and their id
        int amount = 0;
        for (String s : numRatings.keySet()) {
            int currAmount = numRatings.get(s);
            if (amount < currAmount) {
                amount = currAmount;
            }
        }
        System.out.println("Max amount of ratings by any rater is " + amount);
        int count = 0;
        for (String s : numRatings.keySet()) {
            if (numRatings.get(s) == amount) {
                count +=1;
                System.out.println("ID whith max amount of raters " + s);
            }
        }
        System.out.println("Raters with this amount of ratings:" + count);
        
        //returns amount of ratings by a specific rater
        String raterRated = "193";
        System.out.println("Amount of ratings by Rater id " + raterRated + " = " + numRatings.get(raterRated));
        
        //total number of raters
        System.out.println("Total number of raters " + numRatings.size());
        
        //Lists all raters, amount of ratings they've done and items rated 
        //along with the rating given by rater
        //also returns amount of raters that rated a given movie
        //returns total amount of different movies rated
        String movieRated = "1798709";
        int movieCount = 0;
        ArrayList<String> totalMoviesRated = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String listRID = list.get(i).getID();
            ArrayList<String> moviesRated = list.get(i).getItemsRated();
            if(moviesRated.contains(movieRated)) {
                movieCount += 1;
            }
            System.out.println(listRID + " Has rated " + moviesRated.size());
            for (int j = 0 ; j < moviesRated.size(); j++) {
                System.out.println(moviesRated.get(j) + " rating " + list.get(i).getRating(moviesRated.get(j)));
                if(!totalMoviesRated.contains(moviesRated.get(j))) {
                    totalMoviesRated.add(moviesRated.get(j));
                }
            }
        }
        System.out.println("Movie ID " + movieRated + " has " + movieCount + " raters");
        System.out.println("Total amount of different movies rated " + totalMoviesRated.size());
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> list = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> list = loadMovies("data/ratedmoviesfull.csv");
        int comedy = 0;
        int length = 0;
        String maxDir = "";
        HashMap<String,Integer> directorMax = new HashMap<String,Integer>();
        int dir = 0;
        for (Movie  movie : list) {
            if (movie.getGenres().contains("Comedy")){
                comedy += 1;
            }
            if (movie.getMinutes() > 150) {
                length += 1;
            }
            String director = movie.getDirector();
            if (director.contains(", ")) {
                String [] directors = director.split(", ");
                for(int i = 0 ; i < directors.length; i++){
                     if (! directorMax.containsKey(directors[i])) {
                         directorMax.put(directors[i],1);
                        }
                        else{
                            directorMax.put(directors[i],directorMax.get(directors[i])+1);
                     }
                }
            }
            if (! directorMax.containsKey(director)) {
                directorMax.put(director,1);
            }
            else{
                directorMax.put(director,directorMax.get(director)+1);
            }
        }
        for (String s : directorMax.keySet()) {
            if(directorMax.get(s) > dir) {
                dir = directorMax.get(s);
            }
        }
        System.out.println("Total number of movies in list " + list.size());
        System.out.println("Total number of comedy movies in list " + comedy);
        System.out.println("Total number of movies with length greater than 150 " + length);
        System.out.println("Most movies directed by one director is " + dir
        + "\n" + "Directors with this amount of movies directed are: ");
        for (String s : directorMax.keySet()) {
            if (directorMax.get(s) == dir) {
                System.out.println(s);
            }
        }
    }
}
