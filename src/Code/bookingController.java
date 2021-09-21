package Code;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class bookingController {
    BaseMovie movie = Main.getMovieonlist();
    File scaun_A = new File("src\\res\\img\\scaunA.png");
    Image scaunA = new Image(scaun_A.toURI().toString());
    File scaun_V = new File("src\\res\\img\\scaunV.png");
    Image scaunV = new Image(scaun_V.toURI().toString());
    File scaun_R = new File("src\\res\\img\\scaunR.png");
    Image scaunR = new Image(scaun_R.toURI().toString());




    @FXML
    private ImageView seat1 = new ImageView();

    @FXML
    private ImageView seat2 = new ImageView();

    @FXML
    private ImageView seat3 = new ImageView();

    @FXML
    private ImageView seat4 = new ImageView();

    @FXML
    private ImageView seat5 = new ImageView();

    @FXML
    private ImageView seat6 = new ImageView();

    @FXML
    private ImageView seat7 = new ImageView();

    @FXML
    private ImageView seat8 = new ImageView();

    @FXML
    private ImageView seat9 = new ImageView();

    @FXML
    private ImageView seat10 = new ImageView();

    @FXML
    private ImageView seat11 = new ImageView();

    @FXML
    private ImageView seat12 = new ImageView();

    @FXML
    private ImageView seat13 = new ImageView();

    @FXML
    private ImageView seat14 = new ImageView();

    @FXML
    private ImageView seat15 = new ImageView();

    @FXML
    private ImageView seat16 = new ImageView();

    @FXML
    private ImageView seat17 = new ImageView();

    @FXML
    private ImageView seat18 = new ImageView();

    @FXML
    private ImageView seat19 = new ImageView();

    @FXML
    private ImageView seat20 = new ImageView();

    boolean[] seatsArray = new boolean[20];

    List<ImageView> imgSeats = new ArrayList<>();
//    ImageView[] imgSeats = {seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11,
//            seat12, seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20};


    @FXML
    void selectSeat(MouseEvent event) throws Exception {
        //obtinem id-ul imageview-ului pe care dam click.
        String id = event.getPickResult().getIntersectedNode().getId();
        //scot literele, pt a ramane doar cu cifrele din id, convertind in int acele cifre.
        int stringToInt = Integer.parseInt(id.substring(4)) - 1; // -1 ca sa plec de la 0/19 si nu 1/20
        //System.out.println(stringToInt);
        Seats seats = seatsObject();

        seatsArray = seats.getSeats();
        if(seatsArray[stringToInt] != true) {
            imgSeats.get(stringToInt).setImage(scaunA);
            seatsArray[stringToInt] = true;
        }

    }

    @FXML
    void bookingNow() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText("Are you sure for this booked seat/s");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Seats seats = seatsObject();
            DatabaseHandler.updateSeats(seatsArray, seats.getId());
            setImgSeats();
        } else {
            setImgSeats();
        }
    }

    @FXML
    void exitButton() {
        System.exit(0);
    }

    @FXML
    void initialize() throws Exception {
        isMovieOnDB();
        makeImgSeats();
        setImgSeats();

    }

    private Seats seatsObject() throws Exception {
        Seats seats = new Seats();
        String movieTitle = movie.title;
        List<Seats> roomsList = DatabaseHandler.getSeatsList();
        for (int i = 0; i < roomsList.size(); i++) {
            String title = roomsList.get(i).getMovieTitle();
            if (movieTitle.equals(title)) {
                seats = roomsList.get(i);
            }
        }
        return seats;
    }

    private void setImgSeats() throws Exception {
        Seats seats = seatsObject();

        boolean[] arr = seats.getSeats();
        for(int i=0 ; i< imgSeats.size(); i++){
            if(arr[i]==true){
                imgSeats.get(i).setImage(scaunR);
            }else {
                imgSeats.get(i).setImage(scaunV);
            }
        }
    }

    private void makeImgSeats(){
        imgSeats.add(seat1);
        imgSeats.add(seat2);
        imgSeats.add(seat3);
        imgSeats.add(seat4);
        imgSeats.add(seat5);
        imgSeats.add(seat6);
        imgSeats.add(seat7);
        imgSeats.add(seat8);
        imgSeats.add(seat9);
        imgSeats.add(seat10);
        imgSeats.add(seat11);
        imgSeats.add(seat12);
        imgSeats.add(seat13);
        imgSeats.add(seat14);
        imgSeats.add(seat15);
        imgSeats.add(seat16);
        imgSeats.add(seat17);
        imgSeats.add(seat18);
        imgSeats.add(seat19);
        imgSeats.add(seat20);
    }

    private void isMovieOnDB() throws Exception {

        boolean isOnList = false;
        String movieTitle = movie.title;
        List<Seats> roomsList = DatabaseHandler.getSeatsList();
        for (int i = 0 ; i < roomsList.size() ; i++){
            String title = roomsList.get(i).getMovieTitle();
            if(movieTitle.equals(title)){
                isOnList = true;
            }
        }
        if(isOnList == false){
            DatabaseHandler.insertMovieRoom(movie.title);
        }
    }
}
