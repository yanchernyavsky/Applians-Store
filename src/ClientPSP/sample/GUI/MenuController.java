package ClientPSP.sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProductButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button ProviderButton;

    @FXML
    private Button ClientButton;

    @FXML
    void initialize() {
        BackButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/AuthorisationGUI.fxml");
        });
        ExitButton.setOnAction(event -> {
            System.exit(0);
        });
        ProductButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/ProductGUI.fxml");
        });
        ProviderButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/ProviderGUI.fxml");
        });
        ClientButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/ClientGUI.fxml");
        });
        OrderButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/OrderGUI.fxml");
        });

    }

    public void openNewScene(String window) {
        BackButton.getScene().getWindow().hide();
        FXMLLoader loaderGetIn = new FXMLLoader();
        loaderGetIn.setLocation(getClass().getResource(window));
        try {
            Object load = loaderGetIn.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent rootGetIn = loaderGetIn.getRoot();
        Stage stageGetIn = new Stage();
        stageGetIn.setScene(new Scene(rootGetIn));
        stageGetIn.show();
    }
}

