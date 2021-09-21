package Code;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;


public class LoginScreenController {

    public static String username;
    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    @FXML
    private Button logInButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text wrongCredentials;

    @FXML
    void exitButton() {
        System.exit(0);
    }

    @FXML
    void loginClick() throws Exception, SQLException, ClassNotFoundException {
        String usernameStr = usernameBox.getText();
        String passwordStr = passwordBox.getText();
        ObservableList<User> users = DatabaseHandler.getUserList();

        try{
            for(User u: users){
                boolean isAdmin = u.isAdmin();
                if(usernameStr.equals(u.getUsername()) && passwordStr.equals(u.getPassword())){
                    wrongCredentials.setVisible(false);
                    username = usernameStr;
                            if(isAdmin){
                                SceneCreator.launchScene("/scenes/AdminScene.fxml");
                                Main.isAdmin = true;
                            }else{
                                SceneCreator.launchScene("/scenes/UserScene.fxml");
                                Main.isAdmin = false;
                            }

                }
                else{
                    wrongCredentials.setVisible(true);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField registerFname;

    @FXML
    private TextField registerUsername;

    @FXML
    private TextField registerLname;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerEmail;


    @FXML
    void goLogin(ActionEvent event) throws Exception {
        SceneCreator.launchScene("/scenes/LoginScreenFXML.fxml");
    }

    @FXML
    void goRegister(ActionEvent event) throws Exception {
        SceneCreator.launchScene("/scenes/RegisterScreenFXML.fxml");
    }

    @FXML
    void registerClick(ActionEvent event) throws Exception {
        String FnameStr = registerFname.getText();
        String LnameStr = registerLname.getText();
        String usernameStr = registerUsername.getText();
        String passwordStr = registerPassword.getText();
        String EmailStr = registerEmail.getText();

        try{
            DatabaseHandler.insertUser(FnameStr, LnameStr, usernameStr, passwordStr, EmailStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
