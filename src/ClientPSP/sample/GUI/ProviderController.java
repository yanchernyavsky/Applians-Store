package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ProviderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Provider> ProviderTable;

    @FXML
    private TableColumn<Provider, Integer> ProviderId;

    @FXML
    private TableColumn<Provider, String> ProviderCompanyName;

    @FXML
    private TableColumn<Provider, String> ProviderAddress;

    @FXML
    private TableColumn<Provider, String> ProviderPhone;

    @FXML
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField NameAdd;

    @FXML
    private TextField AddressAdd;

    @FXML
    private TextField PhoneAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField NameUpdate;

    @FXML
    private TextField AddressUpdate;

    @FXML
    private TextField PhoneUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField IdDelete;

    @FXML
    private Button ReportButton;


    private ObservableList<Provider> data;

    @FXML
    void initialize() {
        ProviderId.setCellValueFactory(
                new PropertyValueFactory<Provider, Integer>("provider_id"));
        ProviderCompanyName.setCellValueFactory(
                new PropertyValueFactory<Provider,String>("provider_name"));
        ProviderAddress.setCellValueFactory(
                new PropertyValueFactory<Provider,String>("provider_address"));
        ProviderPhone.setCellValueFactory(
                new PropertyValueFactory<Provider,String>("provider_phonenumber"));
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
        data = FXCollections.observableArrayList(serv.GetProvider());
        ProviderTable.setItems(data);
    }

    public void Report(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Отчёт по поставщикам.txt", "UTF-8");


            Integer cap = data.size();
            Integer i = 0;
            writer.println("Поставщики:");
            writer.println("количество поставщиков: "+ cap);
            writer.println("------------------------------");
            writer.println();
            writer.println();
            for(i = 0; i<cap ; i++)
            {
                Provider provider = data.get(i);
                writer.print("Код поставщика: ");
                writer.println(provider.getProvider_id());
                writer.print("Наименование поставщика: ");
                writer.println(provider.getProvider_name());
                writer.print("Адрес: ");
                writer.println(provider.getProvider_address());
                writer.print("Телефон: ");
                writer.println(provider.getProvider_phonenumber());
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
        String Name = NameAdd.getText().trim();
        String Address = AddressAdd.getText().trim();
        String Phone = PhoneAdd.getText().trim();

        Provider provider = new Provider(Id,Name,Address,Phone);
        if(!Id.equals("") && !Name.equals("") && !Address.equals("") && !Phone.equals("")){
            Service serv = new Service();
            serv.AddProvider(provider);
            IdAdd.setText("Новый");
            NameAdd.setText("Поставщик");
            AddressAdd.setText("Успешно");
            PhoneAdd.setText("Добавлен");
        }
        else {
            IdAdd.setText("Не должно быть");
            NameAdd.setText("пустых");
            AddressAdd.setText("полей");
            PhoneAdd.setText("Введите заново!");
        }
    }

    public void Update(){
        Integer Id = Integer.valueOf(IdUpdate.getText());
        String Name = NameUpdate.getText().trim();
        String Address = AddressUpdate.getText().trim();
        String Phone = PhoneUpdate.getText().trim();

        Provider provider = new Provider(Id,Name,Address,Phone);
        if(!Id.equals("") && !Name.equals("") && !Address.equals("") && !Phone.equals("")){
            Service serv = new Service();
            serv.UpdateProvider(provider);
            IdUpdate.setText("Выбранный");
            NameUpdate.setText("Поставщик");
            AddressUpdate.setText("Успешно");
            PhoneUpdate.setText("Изменён");
        }
        else {
            IdUpdate.setText("Не должно быть");
            NameUpdate.setText("пустых");
            AddressUpdate.setText("полей");
            PhoneUpdate.setText("Введите заново!");
        }
    }

    public void Delete(){
        Integer Id = Integer.valueOf(IdDelete.getText());

        if(!Id.equals("")){
            Service serv = new Service();
            boolean AdminCheck;
            AdminCheck = serv.DeleteProvider(Id);;
            if (AdminCheck)
                IdDelete.setText("Поставщик успешно удалён");
            else
                IdDelete.setText("Недостаточно прав для данного действия");
        }
        else {
            IdDelete.setText("Поле не должно быть пустым");
        }
    }
}

