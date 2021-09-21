package Code;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    public static List<BaseMovie> popularMovies = null;
    public static List<BaseMovie> upcomingMovies = null;
    public static List<String> popularImages = new ArrayList<>();
    public static List<String> upcomingImages = new ArrayList<>();
    public static List<BaseMovie> nonDuplicateUpcomingMovie = null;
    public static List<String> nonDuplicateUpcomingImages = new ArrayList<>();
    public static int currentMovie;
    public static String currentList;
    static Parent root;
    static Stage primaryStage;
    static Main m = null;
    static boolean isAdmin;
    //---------------------------------------------------------------


    static Parent getRoot() {

        return root;
    }

    static void setRoot(Parent root) {

        Main.root = root;
    }

    static Stage getStage() {

        return primaryStage;
    }

    static void setStage(Stage stage) {

        Main.primaryStage = stage;
    }

    static String getPath() {
        return ClassLoader.getSystemClassLoader().getResource(".").getPath();
    }

    static void setCurrentMovie(int MovieIndex){
        Main.currentMovie = MovieIndex;
    }

    static int getCurrentMovie() {
        return currentMovie;
    }

    static void setMovieList(String list){
        currentList = list;
    }

    static String getCurrentImg(){
        String string = "";
        if(currentList == "popularMovies") {
            string = popularImages.get(currentMovie);
            return string;
        }
        if(currentList == "nonDuplicateUpcomingMovie"){
            string = nonDuplicateUpcomingImages.get(currentMovie);
            return string;
        }
        return string;
    }

    static BaseMovie getMovieonlist (){
        BaseMovie movie = null;
        if(currentList == "popularMovies"){
            movie = popularMovies.get(currentMovie);
            return movie;
        }
        if(currentList == "nonDuplicateUpcomingMovie"){
            movie = nonDuplicateUpcomingMovie.get(currentMovie);
            return movie;
        }
        return movie;
    }

    static void createMovieListAdmin(){
        try {
            if (popularMovies == null) {
                popularMovies = MovieHandler.getPopularList();
                if (popularMovies != null) {
                    for (BaseMovie baseMovie : popularMovies) {
                        //System.out.println(baseMovie.title);
                        String poster = baseMovie.poster_path;
                        String marime = "w200";
                        String finalPath = MovieHandler.imagePathConstruction(marime, poster);
                        popularImages.add(finalPath);
//                    System.out.println(finalPath);
//                    System.out.println(" ");
//                    System.out.println(" ");
                    }
                }
            }

            if (upcomingMovies == null) {
                upcomingMovies = MovieHandler.getUpcomingList();
                if (upcomingMovies != null) {
                    for (BaseMovie baseMovie : upcomingMovies) {
                        //System.out.println(baseMovie.title);
                        String poster = baseMovie.poster_path;
                        String marime = "w200";
                        String finalPath = MovieHandler.imagePathConstruction(marime, poster);
                        upcomingImages.add(finalPath);
//                    System.out.println(finalPath);
//                    System.out.println(" ");
//                    System.out.println(" ");
                    }
                }
            }

            if (nonDuplicateUpcomingMovie == null) {
                nonDuplicateUpcomingMovie = new ArrayList<BaseMovie>(upcomingMovies);
                MovieHandler.removeDuplicatesMovie(popularMovies, upcomingMovies, nonDuplicateUpcomingMovie);
                if (nonDuplicateUpcomingMovie != null) {
                    for (BaseMovie baseMovie : nonDuplicateUpcomingMovie) {
                        String poster = baseMovie.poster_path;
                        String marime = "w200";
                        String finalPath = MovieHandler.imagePathConstruction(marime, poster);
                        nonDuplicateUpcomingImages.add(finalPath);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        m = new Main();
        createMovieListAdmin();

        try {
            DatabaseHandler.createRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }

    //private double x, y;

    @Override
    public void start(Stage primaryStage){
        try {
            root = FXMLLoader.load(getClass().getResource("/scenes/LoadingFXML.fxml"));
            Main.primaryStage = primaryStage;
            Scene scene = new Scene(root);

//            scene.setFill(Color.TRANSPARENT);
//            root.setOnMousePressed(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    x = mouseEvent.getSceneX();
//                    y = mouseEvent.getSceneY();
//                }
//            });
//
//            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    primaryStage.setX(mouseEvent.getScreenX() - x );
//                    primaryStage.setY(mouseEvent.getScreenY() - y );
//                }
//            });

            scene.getStylesheets().add(getClass().getResource("/res/style.css").toExternalForm());
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
