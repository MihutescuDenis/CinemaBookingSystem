package Code;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Code.LoginScreenController.username;

public class AdminSceneController {

    @FXML
    private BorderPane borderpane;


    @FXML
    private Label logAsLB;

    @FXML
    void exitButton() {
        System.exit(0);
    }

    @FXML
    void home(MouseEvent event) {

        loadUI("HomeAdminSceene");
    }

    @FXML
    void user(MouseEvent event) {
        loadUI("UserAdminSceene");
    }

    @FXML
    void movie(MouseEvent event) {
        loadUI("MovieAdminSceene");
    }


    @FXML
    void initialize() {
        logAsLB.setText(username.toUpperCase(Locale.ROOT));
    }

    public void loadUI (String ui) {
        Parent root = null;
        try {
            String path = "/scenes/" + ui + ".fxml";
            root = FXMLLoader.load(getClass().getResource(path)); 
        }catch(IOException e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        borderpane.setCenter(root);
    }
}
