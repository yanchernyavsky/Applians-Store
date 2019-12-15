package ClientFolder.sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientFolder.sample.ClientApp;
import ClientFolder.sample.Services;
import ClientFolder.sample.DatabaseTables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSighUpButton;

    @FXML
    private Button authSighInButton;

    @FXML
    private Button ExitButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSighInButton.setOnAction(event -> {
            loginUser();
        });


        loginSighUpButton.setOnAction(event -> {
           openNewScene("/ClientFolder/sample/GUI/RegistrationGUI.fxml");
        });

        ExitButton.setOnAction(event -> {
            System.exit(0);
        });
    }

            private void loginUser() {
                String login = login_field.getText().trim();
                String password = password_field.getText().trim();
                User user = new User(login, password);
                boolean userExistence;
                if(!login.equals("") && !password.equals("")){
                    Services serv = new Services();
                    String WhoIsHere=serv.SighUp(user);
                    if(ClientApp.AdminOrClientId == -1)
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR,
                                "Не удалось войти. Причина: Блокировка",
                                ButtonType.APPLY);
                        alert.show();
                        return;
                    }
                    if(WhoIsHere.equals("User")){
                        openNewScene("MenuGUI.fxml");
                    }
                    if(WhoIsHere.equals("Client"))
                        openNewScene("ClientOrderGUI.fxml");
                    else
                    {
                        login_field.setText("Пользователь не найден");
                        password_field.setText("");
                    }
                }
                else {
                    login_field.setText("Необходимо заполнить все поля");
                    password_field.setText("");
                }
            }
            public void openNewScene(String window) {
                loginSighUpButton.getScene().getWindow().hide();
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

