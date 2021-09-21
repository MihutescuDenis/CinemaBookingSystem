package Code;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieAdminSceeneController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPaneRuleaza;

    @FXML
    private GridPane gridRuleaza;

    @FXML
    private ScrollPane scrollPaneUpcoming;

    @FXML
    private GridPane gridUpcoming;

    @FXML
    ImageView pic;
    @FXML
    Image image;

    HBox hbRuleaza = new HBox();
    HBox hbUpcoming = new HBox();

    @FXML
    void initialize() throws IOException {

        try {

            scrollPaneRuleaza.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPaneUpcoming.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            // gridpane settings
            // setting exterior grid padding
            gridRuleaza.setPadding(new Insets(7, 7, 7, 7));
            // setting interior grid padding
            gridRuleaza.setHgap(10);
            gridRuleaza.setVgap(10);
            //gridRuleaza.setGridLinesVisible(true);

            // gridpane settings
            // setting exterior grid padding
            gridUpcoming.setPadding(new Insets(7, 7, 7, 7));
            // setting interior grid padding
            gridUpcoming.setHgap(10);
            gridUpcoming.setVgap(10);
            //gridUpcoming.setGridLinesVisible(true);


            int rows = (Main.popularMovies.size() / 4) + 1;
            int columns = 4;
            int imageIndex = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (imageIndex < Main.popularMovies.size()) {
                        addImagePopular(imageIndex, j, i);
                        imageIndex++;
                    }
                }
            }

            int rowsU = (Main.nonDuplicateUpcomingMovie.size() / 4) + 1;
            int columnsU = 4;
            int imageIndexU = 0;


            for (int i = 0; i < rowsU; i++) {
                for (int j = 0; j < columnsU; j++) {
                    if (imageIndexU < Main.nonDuplicateUpcomingMovie.size()) {
                        addImageUpcoming(imageIndexU, j, i);
                        imageIndexU++;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void addImagePopular(int index, int colIndex, int rowIndex) {
        String imageURL = Main.popularImages.get(index);
        //System.out.println(imageURL);
        image = new Image(imageURL);
        pic = new ImageView();
        pic.setFitWidth(229);
        pic.setFitHeight(309);
        pic.setImage(image);
//        pic.setId(id);
        hbRuleaza.getChildren().add(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
//        // grid.add(pic, imageCol, imageRow);
        gridRuleaza.getChildren().addAll(pic);

        pic.setOnMouseClicked(e -> {
//             System.out.printf("Mouse clicked cell [%d, %d]%n", rowIndex, colIndex);
//             System.out.println(Main.popularMovies.get(index).title);
            try {
                // storing the selected film to customise the newly created scene
                Main.setCurrentMovie(index);
                Main.setMovieList("popularMovies");

                SceneCreator.launchScene("/scenes/ViewSelectedFilmScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addImageUpcoming(int index, int colIndex, int rowIndex) {
        String imageURL = Main.nonDuplicateUpcomingImages.get(index);
        //System.out.println(imageURL);
        image = new Image(imageURL);
        pic = new ImageView();
        pic.setFitWidth(229);
        pic.setFitHeight(309);
        pic.setImage(image);
//        pic.setId(id);
        hbUpcoming.getChildren().add(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
//        // grid.add(pic, imageCol, imageRow);
        gridUpcoming.getChildren().addAll(pic);

        pic.setOnMouseClicked(e -> {
//            System.out.printf("Mouse clicked cell [%d, %d]%n", rowIndex, colIndex);
//            System.out.println(Main.popularMovies.get(index).title);
            try {
                // storing the selected film to customise the newly created scene
                Main.setCurrentMovie(index);
                Main.setMovieList("nonDuplicateUpcomingMovie");
                SceneCreator.launchScene("/scenes/ViewSelectedFilmScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}