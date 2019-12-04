package ClientPSP.sample.GUI;

import ClientPSP.sample.Service;
import ClientPSP.sample.Tables.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

public class ProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Product> ProductTable;

    @FXML
    private TableColumn<Product, Integer> ProductId;

    @FXML
    private TableColumn<Product, Integer> ProviderId;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductNumber;

    @FXML
    private TableColumn<Product, Integer> ProductPrice;

    @FXML
    private TableColumn<Product, String> CategoryName;

    @FXML
    private Button CategoryButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField ProductProviderIdAdd;

    @FXML
    private TextField NameAdd;

    @FXML
    private TextField NumberAdd;

    @FXML
    private TextField PriceAdd;

    @FXML
    private TextField ProductCategoryNameAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField ProductProviderIdUpdate;

    @FXML
    private TextField NameUpdate;

    @FXML
    private TextField NumberUpdate;

    @FXML
    private TextField PriceUpdate;

    @FXML
    private TextField ProductCategoryNameUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField IdDelete;

    @FXML
    private Button ReportButton;

    @FXML
    private BarChart<?, ?> PriceChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis у;

    @FXML
    private Button FilterButton;

    @FXML
    private TextField ProductNumberFilter;

    @FXML
    private CheckBox MoreLessCheckBox;


    private ObservableList<Product> data;

    @FXML
    void initialize() {
        ProductId.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("product_id"));
        ProviderId.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_provider_id"));
        ProductName.setCellValueFactory(
                new PropertyValueFactory<Product,String>("product_name"));
        ProductNumber.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_number"));
        ProductPrice.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_price"));
        CategoryName.setCellValueFactory(
                new PropertyValueFactory<Product,String>("product_category_name"));
        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/MenuGUI.fxml");
        });

        CategoryButton.setOnAction(event -> {
            openNewScene("/ClientPSP/sample/GUI/CategoryGUI.fxml");
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

        FilterButton.setOnAction(event -> {
            Integer Direction;
            if(MoreLessCheckBox.isSelected())
                Direction = 1;
            else
                Direction = 0;
            System.out.println(Direction);
            FilterByNumber(Direction);

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
        data = FXCollections.observableArrayList(serv.GetProduct());
        ProductTable.setItems(data);
        XYChart.Series set = new XYChart.Series<>();
        PriceChart.getData().clear();
        Integer cap = data.size();
        Integer i = 0;
        for(i = 0; i<cap ; i++)
        {
            Product product = data.get(i);
            set.getData().add(new XYChart.Data(product.getProduct_name(),product.getProduct_price()));
        }
        PriceChart.getData().addAll(set);
    }

    public void Report(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Отчёт по товарам.txt", "UTF-8");


            Integer cap = data.size();
            Integer i = 0;
            writer.println("Товары:");
            writer.println("количество разных товаров: "+ cap);
            writer.println("------------------------------");
            writer.println();
            writer.println();
            for(i = 0; i<cap ; i++)
            {
                Product product = data.get(i);
                writer.print("Код товара: ");
                writer.println(product.getProduct_id());
                writer.print("Наименование товара: ");
                writer.println(product.getProduct_name());
                writer.print("Количество товара: ");
                writer.println(product.getProduct_number());
                writer.print("Цена: ");
                writer.println(product.getProduct_price());
                writer.print("Категория товара: ");
                writer.println(product.getProduct_category_name());
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
        Integer ProviderId = Integer.valueOf(ProductProviderIdAdd.getText());
        String Name = NameAdd.getText().trim();
        Integer Number = Integer.valueOf(NumberAdd.getText());
        Integer Price = Integer.valueOf(PriceAdd.getText());
        String CategoryName = ProductCategoryNameAdd.getText().trim();

        Product product = new Product(Id, ProviderId, Name, Number, Price, CategoryName);
        if(!Id.equals("") && !ProviderId.equals("") && !Name.equals("") && !Number.equals("") && !Price.equals("") && !CategoryName.equals("")){
            Service serv = new Service();
            serv.AddProduct(product);
            IdAdd.setText("Новый");
            ProductProviderIdAdd.setText("Товар");
            NameAdd.setText("Успешно");
            NumberAdd.setText("Добавлен");
            PriceAdd.setText("");
            ProductCategoryNameAdd.setText("");

        }
        else {
            IdAdd.setText("Не должно");
            ProductProviderIdAdd.setText("быть");
            NameAdd.setText("пустых");
            NumberAdd.setText("полей");
            PriceAdd.setText("Введите заново!");
            ProductCategoryNameAdd.setText("");
        }
    }

    public void Update(){
        Integer Id = Integer.valueOf(IdUpdate.getText());
        Integer ProviderId = Integer.valueOf(ProductProviderIdUpdate.getText());
        String Name = NameUpdate.getText().trim();
        Integer Number = Integer.valueOf(NumberUpdate.getText());
        Integer Price = Integer.valueOf(PriceUpdate.getText());
        String CategoryName = ProductCategoryNameUpdate.getText().trim();

        Product product = new Product(Id, ProviderId, Name, Number, Price, CategoryName);
        if(!Id.equals("") && !ProviderId.equals("") && !Name.equals("") && !Number.equals("") && !Price.equals("") && !CategoryName.equals("")){
            Service serv = new Service();
            serv.UpdateProduct(product);
            IdUpdate.setText("Выьранный");
            ProductProviderIdUpdate.setText("Товар");
            NameUpdate.setText("Успешно");
            NumberUpdate.setText("Изменён");
            PriceUpdate.setText("");
            ProductCategoryNameUpdate.setText("");

        }
        else {
            IdUpdate.setText("Не должно");
            ProductProviderIdUpdate.setText("быть");
            NameUpdate.setText("пустых");
            NumberUpdate.setText("полей");
            PriceUpdate.setText("Введите заново!");
            ProductCategoryNameUpdate.setText("");
        }
    }

    public void Delete(){
        Integer Id = Integer.valueOf(IdDelete.getText());

        if(!Id.equals("")){
            Service serv = new Service();
            boolean AdminCheck;
            AdminCheck = serv.DeleteProduct(Id);;
            if (AdminCheck)
                IdDelete.setText("Товар успешно удалён");
            else
                IdDelete.setText("Недостаточно прав для данного действия");
        }
        else {
            IdDelete.setText("Поле не должно быть пустым");
        }
    }

    public void FilterByNumber(Integer Direction){
        Integer Number = Integer.valueOf(ProductNumberFilter.getText());

        if(!Number.equals("")){

            Service serv = new Service();
            data = FXCollections.observableArrayList( serv.GetProductFilter(Number , Direction));
            ProductTable.setItems(data);
            XYChart.Series set = new XYChart.Series<>();
            PriceChart.getData().clear();
            Integer cap = data.size();
            Integer i = 0;
            for(i = 0; i<cap ; i++)
            {
                Product product = data.get(i);
                set.getData().add(new XYChart.Data(product.getProduct_name(),product.getProduct_price()));
            }
            PriceChart.getData().addAll(set);
            ProductNumberFilter.setText("Товар успешно отфильтрован");
        }
        else {
            ProductNumberFilter.setText("Поле не должно быть пустым");
        }
    }
}


