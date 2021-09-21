package Code;


import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.services.MoviesService;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;



public class MovieHandler {



    static String API_KEY = "25b7bf1c3ede6c0bfa69e661aab1acab";

    public static String imagePathConstruction(String wMarime, String posterPath){
        String base = "http://image.tmdb.org/t/p/";
        String baseWMarime = base.concat(wMarime);
        String finalURL = baseWMarime.concat(posterPath);
        return finalURL;
    }

    public static List<BaseMovie> getUpcomingList() throws IOException {
        Tmdb tmdb = new Tmdb(API_KEY);
        MoviesService moviesService = tmdb.moviesService();
        Response<MovieResultsPage> movies = moviesService.upcoming(1,"en-US","").execute();
        List<BaseMovie> listOne = null;
        if(movies.isSuccessful()) {
            MovieResultsPage movie = movies.body();
            if (movie != null) {
                listOne = movie.results;
            }
        }
        return listOne;
    }

    public static List<BaseMovie> getPopularList() throws IOException {
        Tmdb tmdb = new Tmdb(API_KEY);
        MoviesService moviesService = tmdb.moviesService();
        Response<MovieResultsPage> movies = moviesService.popular(1,"en-US","").execute();
        List<BaseMovie> listOne = null;
        if(movies.isSuccessful()) {
            MovieResultsPage movie = movies.body();
            if (movie != null) {
                listOne = movie.results;
            }
        }
        return listOne;
    }

    public static void removeDuplicatesMovie(List<BaseMovie> listaPopular, List<BaseMovie> listaUpcoming, List<BaseMovie> resultList){
        int count = 0;
        //resultList = new ArrayList<BaseMovie>(listaUpcoming);
        for (BaseMovie Popular : listaPopular) {
            for (BaseMovie Upcoming : listaUpcoming){
                String popular = Popular.title;
                String upcoming = Upcoming.title;
                if (popular != null) {
                    if(popular.equals(upcoming)){
                        resultList.remove(Upcoming);
                        count++;
                    }
//                    else {
//                        System.out.println("Nu s-au gasit filme identice");
//                    }
                }
            }
        }
    }



}
