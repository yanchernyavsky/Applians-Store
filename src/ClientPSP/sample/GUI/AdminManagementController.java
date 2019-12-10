package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.User;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminManagementController {
    public TableView<User> UsersTable;
    public TableColumn<User, Integer> Idusers;
    public TableColumn<User, String> Username;
    public TableColumn<User, String> Login;
    public TableColumn<User, Integer> Admin;
    public Button BackButton;

    private ObservableList<User> data;
    private Service serv;

    @FXML
    void initialize()
    {
        serv = new Service();
        Idusers.setCellValueFactory(new PropertyValueFactory<User, Integer>("UserId"));
        Username.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
        Login.setCellValueFactory(new PropertyValueFactory<User, String>("UserLogin"));
        Admin.setCellValueFactory(new PropertyValueFactory<User, Integer>("UserAdmin"));

        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/MenuGUI.fxml");
        });

        UsersTable.setRowFactory(param -> {
            final TableRow<User> row = new TableRow<>();
            final ContextMenu cm = new ContextMenu();
            final MenuItem ban = new MenuItem("БАН!");
            final MenuItem unBan = new MenuItem("Разбан");
            ban.setOnAction(event ->
            {
                User userToBan = UsersTable.getItems().get(row.getIndex());
                System.out.println("Прописал бан дурачку " + userToBan.getUserName());
                userToBan.setUserAdmin(-1);
                serv.BanUser(userToBan);
                UpdateTableInformation();
            });
            unBan.setOnAction(event ->
            {
                User userToUnban = UsersTable.getItems().get(row.getIndex());
                System.out.println("Разбанил дурачка " + userToUnban.getUserName());
                userToUnban.setUserAdmin(0);
                serv.BanUser(userToUnban);
                UpdateTableInformation();
            });
            cm.getItems().add(ban);
            cm.getItems().add(unBan);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(cm));
            return row;
        });
    }

    public void openNewScene(String window) {
        BackButton.getScene().getWindow().hide();
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

    public void UpdateTableInformation() {
        data = FXCollections.observableArrayList(serv.GetUsers());
        UsersTable.setItems(data);
    }
}
