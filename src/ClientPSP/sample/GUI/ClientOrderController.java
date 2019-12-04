package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientOrderController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Order> OrderTable;

    @FXML
    private TableColumn<Order, Integer> OrderId;

    @FXML
    private TableColumn<Order, Integer> ClientId;

    @FXML
    private TableColumn<Order, Integer> ProductId;

    @FXML
    private TableColumn<Order, Integer> ProviderId;

    @FXML
    private TableColumn<Order, Integer> ProductNumber;

    @FXML
    private TableColumn<Order, Integer> Finalprice;

    @FXML
    private TableColumn<Order, String> Status;

    @FXML
    private Button ReportButton;


    private ObservableList<Order> data;

    @FXML
    void initialize() {
        OrderId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_id"));
        ClientId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_client_id"));
        ProductId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_product_id"));
        ProviderId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_provider_id"));
        ProductNumber.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_product_number"));
        Finalprice.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_finalprice"));
        Status.setCellValueFactory(
                new PropertyValueFactory<Order, String>("order_status"));
        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/AuthorisationGUI.fxml");
        });

        ReportButton.setOnAction(event -> {
            Report();
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
        Service serv = new Service();
        data = FXCollections.observableArrayList(serv.GetClientOrder());
        OrderTable.setItems(data);
    }

    public void Report(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Отчёт по заказам.txt", "UTF-8");


            Integer cap = data.size();
            Integer i = 0;
            writer.println("Заказы:");
            writer.println("количество заказов: "+ cap);
            writer.println("------------------------------");
            writer.println();
            writer.println();
            for(i = 0; i<cap ; i++)
            {
                Order order = data.get(i);
                writer.print("Код заказа: ");
                writer.println(order.getOrder_id());
                writer.print("Код клиента: ");
                writer.println(order.getOrder_client_id());
                writer.print("Код товара: ");
                writer.println(order.getOrder_product_id());
                writer.print("Код поставщика: ");
                writer.println(order.getOrder_provider_id());
                writer.print("Количество товара: ");
                writer.println(order.getOrder_product_number());
                writer.print("Финальная цена: ");
                writer.println(order.getOrder_finalprice());
                writer.println();
                writer.println("------------------------------");
                writer.println();

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
