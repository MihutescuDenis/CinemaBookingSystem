package Code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class UserAdminSceenController {

    @FXML
    private TableView<User> userTabelViev;

    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private TableColumn<User, String> colFirstName;

    @FXML
    private TableColumn<User, String> colLastName;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private TableColumn<User, String> colEmail;


    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField idText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField emailText;

    ObservableList <User> userList = FXCollections.observableArrayList();

    @FXML
    void selectedFieldOnTabel(MouseEvent event) {
        User selectUser = new User();
        selectUser = userTabelViev.getSelectionModel().getSelectedItem();
        if(selectUser != null){
            int id = selectUser.getId();
            String s=String.valueOf(id);
            idText.setText(s);
            firstNameText.setText(selectUser.getFirstName());
            lastNameText.setText(selectUser.getLastName());
            usernameText.setText(selectUser.getUsername());
            passwordText.setText(selectUser.getPassword());
            emailText.setText(selectUser.getEmail());
        }else{
            System.out.println("eroare in selectedFieldOnTabel");
        }
    }

    @FXML
    void delete(ActionEvent event) throws Exception {
        if(idText != null) {
            DatabaseHandler.deleteUser(idText.getText());
        }
        showUser();
    }

    @FXML
    void update(ActionEvent event) throws Exception {
        if(idText != null) {
            DatabaseHandler.updateUser(idText.getText(), firstNameText.getText(), lastNameText.getText(), usernameText.getText(), passwordText.getText(), emailText.getText());
        }
        showUser();
    }

    @FXML
    void initialize() throws Exception {
        clearFields();
        userTabelViev.getItems().clear();
        showUser();
    }

    private void showUser() throws Exception {
        userTabelViev.getItems().clear();
        userList = DatabaseHandler.getUserList();

        colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        userTabelViev.setItems(userList);
    }

    private void clearFields (){
        idText.clear();
        firstNameText.clear();
        lastNameText.clear();
        usernameText.clear();
        passwordText.clear();
        emailText.clear();
    }

}
