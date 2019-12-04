package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class OrderController {

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
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField ClientIdAdd;

    @FXML
    private TextField ProductIdAdd;

    @FXML
    private TextField ProviderIdAdd;

    @FXML
    private TextField ProductNumberAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField ClientIdUpdate;

    @FXML
    private TextField ProductIdUpdate;

    @FXML
    private TextField ProviderIdUpdate;

    @FXML
    private TextField ProductNumberUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button ReportButton;

    @FXML
    private Button CountButton;

    @FXML
    private TextField IdDelete;

    @FXML
    private CheckBox StatusUpdate;

    @FXML
    private CheckBox StatusAdd;

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
            openNewScene("/ClientPSP/sample/GUI/MenuGUI.fxml");
        });

        AddButton.setOnAction(event -> {
            Add();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });

        UpdateButton.setOnAction(event -> {
            Update();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });

        DeleteButton.setOnAction(event -> {
            Delete();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });

        ReportButton.setOnAction(event -> {
            Report();
        });

        CountButton.setOnAction(event -> {
           FinalPriceCounter();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           UpdateTableInformation();
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
        data = FXCollections.observableArrayList(serv.GetOrder());
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

    public void Add(){
        Integer Id = Integer.valueOf(IdAdd.getText());
        Integer ClientId = Integer.valueOf(ClientIdAdd.getText());
        Integer ProductId = Integer.valueOf(ProductIdAdd.getText());
        Integer ProviderId = Integer.valueOf(ProviderIdAdd.getText());
        Integer ProductNumber = Integer.valueOf(ProductNumberAdd.getText());
        String Status = " ";
        if(StatusAdd.isSelected())
            Status = "Готов к отправке";
        else
            Status = "Сбор заказа";
        System.out.println(Status);

        Order order = new Order(Id, ClientId, ProductId, ProviderId, ProductNumber, Status);
        if(!Id.equals("") && !ClientId.equals("") && !ProductId.equals("") && !ProviderId.equals("")
                && !ProductNumber.equals("") && !Status.equals("") ){
            Service serv = new Service();
            serv.AddOrder(order);
            IdAdd.setText("Новый");
            ClientIdAdd.setText("Заказ");
            ProductIdAdd.setText("Успешно");
            ProviderIdAdd.setText("Добавлен");
            ProductNumberAdd.setText("");
        }
        else {
            IdAdd.setText("Не должно быть");
            ClientIdAdd.setText("пустых");
            ProductIdAdd.setText("полей");
            ProviderIdAdd.setText("Введите заново!");
            ProductNumberAdd.setText("");
        }
    }

    public void Update(){
        Integer Id = Integer.valueOf(IdUpdate.getText());
        Integer ClientId = Integer.valueOf(ClientIdUpdate.getText());
        Integer ProductId = Integer.valueOf(ProductIdUpdate.getText());
        Integer ProviderId = Integer.valueOf(ProviderIdUpdate.getText());
        Integer ProductNumber = Integer.valueOf(ProductNumberUpdate.getText());
        String Status = " ";
        if(StatusUpdate.isSelected())
            Status = "Готов к отправке";
        else
            Status = "Сбор заказа";
        System.out.println(Status);

        Order order = new Order(Id, ClientId, ProductId, ProviderId, ProductNumber, Status);
        if(!Id.equals("") && !ClientId.equals("") && !ProductId.equals("") && !ProviderId.equals("")
                && !ProductNumber.equals("") && !Status.equals("") ){
            Service serv = new Service();
            serv.UpdateOrder(order);
            IdUpdate.setText("Выбранный");
            ClientIdUpdate.setText("Заказ");
            ProductIdUpdate.setText("Успешно");
            ProviderIdUpdate.setText("Изменён");
            ProductNumberUpdate.setText("");
        }
        else {
            IdUpdate.setText("Не должно быть");
            ClientIdUpdate.setText("пустых");
            ProductIdUpdate.setText("полей");
            ProviderIdUpdate.setText("Введите заново!");
            ProductNumberUpdate.setText("");
        }
    }

    public void Delete(){
        Integer Id = Integer.valueOf(IdDelete.getText());

        if(!Id.equals("")){
            Service serv = new Service();
            boolean AdminCheck;
            AdminCheck = serv.DeleteOrder(Id);;
            if (AdminCheck)
                IdDelete.setText("Заказ успешно удалён");
            else
                IdDelete.setText("Недостаточно прав для данного действия");
        }
        else {
            IdDelete.setText("Поле не должно быть пустым");
        }
    }

    public void FinalPriceCounter()
    {
        Service serv = new Service();
        serv.CountPrice();
    }
}

