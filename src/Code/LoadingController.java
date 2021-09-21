package Code;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    @FXML
    private ImageView image;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(4000), image);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        fadeOutTransition.setOnFinished(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Parent root;

                try {

                    root = FXMLLoader.load(getClass().getResource("/scenes/LoginScreenFXML.fxml"));
                    Scene newScene = new Scene(root);
                    Stage curStage = (Stage) image.getScene().getWindow();

                    curStage.setScene(newScene);

                } catch (Exception e) {
                    System.err.println("There was an error... " + e);
                }
            }
        });
        fadeOutTransition.play();
    }
}
