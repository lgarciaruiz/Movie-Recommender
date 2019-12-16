import java.util.*;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> moviesToRate = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random r = new Random();
        for (int i = 0; i < 10; i++){
           moviesToRate.add(movies.get((r.nextInt(movies.size())))); 
        } 
        
        return moviesToRate;
    }

    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        ArrayList<Rating> recommendedMovies = fr.getSimilarRatings(webRaterID, 50, 3);
        if(recommendedMovies.size() < 1){
            System.out.println("Sorry no movies were recommended for your taste! Try refreshing.");
        }
        System.out.println("<style>th {colspan:2;}table {bgcolor: #FAFAFA; border: 1px solid black;} td{border: 1px solid black;} font-size{30rem;}</style>");
        System.out.println("<table><th >Movies Recommended for you</th>");
        int amountToPrint = 15;
        if (recommendedMovies.size() < 15){amountToPrint = recommendedMovies.size();}
        for (int i=0; i < amountToPrint; i++) {
            Rating r = recommendedMovies.get(i);
            String movie = r.getItem();
            System.out.println("<tr><td>" + MovieDatabase.getTitle(movie) + "</td>"+ "<td><img src=\"" + MovieDatabase.getPoster(movie) + "\"></td></tr>");
        }
        System.out.println("</table>");
    }
}
