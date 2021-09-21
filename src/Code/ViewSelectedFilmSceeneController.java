package Code;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewSelectedFilmSceeneController {

    BaseMovie movie = Main.getMovieonlist();


    @FXML
    private ImageView image;

    @FXML
    Image imageU;

    @FXML
    private Text title;

    @FXML
    private Text description;

    @FXML
    private Text popularity;

    @FXML
    private Text relaseDate;

    @FXML
    void bookMovie() throws IOException {
        SceneCreator.launchScene("/scenes/bookingScene.fxml");
    }

    @FXML
    void goAdminPanel() throws IOException {
        if(Main.isAdmin == true){
        SceneCreator.launchScene("/scenes/AdminScene.fxml");
        }else{
            SceneCreator.launchScene("/scenes/UserScene.fxml");
        }
        Main.currentList = "";
        Main.currentMovie = -1;

    }

    @FXML
    void initialize() {
        //System.out.println(movie.title);
        String imgURL = Main.getCurrentImg();
        //System.out.println(imgURL);
        imageU = new Image(imgURL);
        image.setImage(imageU);
        title.setText(movie.title);
        description.setText(movie.overview);
        double popular = movie.popularity / 3400;
        NumberFormat format = NumberFormat.getPercentInstance(Locale.US);
        String popularit = format.format(popular);
        //String popularit = String.valueOf(procent);
        popularity.setText(popularit);
        Date date = movie.release_date;
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        String strDate = dateFormat.format(date);
        relaseDate.setText(strDate);
    }
}