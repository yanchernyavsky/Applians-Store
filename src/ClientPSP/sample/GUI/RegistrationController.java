package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegistrationButton;

    @FXML
    private Button BackButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;

    @FXML
    void initialize() {

        RegistrationButton.setOnAction(event -> {
            RegNewUser();
            openNewScene("/ClientPSP/sample/GUI/AuthorisationGUI.fxml");
        });

        BackButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/AuthorisationGUI.fxml");
        });
    }

    private void RegNewUser() {

        String userName = username_field.getText().trim();
        String login = login_field.getText().trim();
        String password = password_field.getText().trim();

        User user = new User(userName,login,password);
        boolean IsAlreadyInDB;
        if(!userName.equals("") && !login.equals("") && !password.equals("")){
            Service serv = new Service();
            IsAlreadyInDB=serv.Registration(user).booleanValue();
            if(IsAlreadyInDB){
                openNewScene("AuthorisationGUI.fxml");
            }
            else{
                username_field.setText("Данный пользователь");
                login_field.setText("Существует");
                password_field.setText("Введите другие значения");
            }
        }
        else {
            username_field.setText("не должно");
            login_field.setText("быть");
            password_field.setText("пустых полей");
        }
    }
    public void openNewScene(String window) {
        RegistrationButton.getScene().getWindow().hide();
        FXMLLoader loaderGetIn = new FXMLLoader();
        loaderGetIn.setLocation(getClass().getResource(window));
        try {
            loaderGetIn.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent rootGetIn = loaderGetIn.getRoot();
        Stage stageGetIn = new Stage();
        stageGetIn.setScene(new Scene(rootGetIn));
        stageGetIn.show();
    }
}

