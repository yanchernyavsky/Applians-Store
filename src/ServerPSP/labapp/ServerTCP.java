package ServerPSP.labapp;

import ClientPSP.sample.Tables.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerTCP extends Thread{

    Socket socket;
    int num;

    public static void main(String[] args) {
        try
        {
            int i = 0; // счётчик подключений
            System.out.println("Порт сервера: "+ 1224);

            ServerSocket server = new ServerSocket(1224, 0,
                    InetAddress.getByName("localhost"));
            System.out.println("Сервер запущен");

            // слушаем порт
            while(true)
            {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку
                new ServerTCP(i, server.accept());
                i++;
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public ServerTCP(int num, Socket socket) {
        // копируем данные
        this.num = num;
        this.socket = socket;
        // и запускаем новый вычислительный поток (см. ф-ю run())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {
            System.out.println("Начало передачи...");
            ObjectInputStream  Inputstream   = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream Outputstream   = new ObjectOutputStream(socket.getOutputStream());
            String command = new String();
            command = (String)Inputstream.readObject();
            Outputstream.writeObject(command);
            DatabaseHandler dbh = new DatabaseHandler();
            switch (command) {
                case "Authorization":
                {
                    System.out.println("Авторизация пользователя...");
                    User user=new User((User)Inputstream.readObject());
                    Integer AdminOrClientId = 0;
                    ResultSet result = dbh.getClient(user);
                    int count=0;
                    try{
                        while(result.next()){
                            count++;
                            AdminOrClientId= result.getInt(Const.CLIENT_ID);
                        }
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    if(count>=1) {
                        Outputstream.writeObject("Client");
                        Outputstream.writeObject(AdminOrClientId);

                    }
                    else {
                        //
                        result = dbh.getUser(user);
                        count = 0;
                        try {
                            while (result.next()) {
                                count++;
                                AdminOrClientId = result.getInt(Const.USERS_ADMIN);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        if (count >= 1) {
                            Outputstream.writeObject("User");
                            Outputstream.writeObject(AdminOrClientId);

                        } else
                            Outputstream.writeObject("NoSuchPerson");
                            Outputstream.writeObject(AdminOrClientId);
                    }
                }
                break;

                case "Registration":
                {
                    System.out.println("Регистрация пользователя...");
                    User user=new User((User)Inputstream.readObject());
                    ResultSet result = dbh.getUser(user);
                    int count=0;
                    try{
                        while(result.next()){
                            count++;
                        }
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    if(count>=1)
                        Outputstream.writeObject(new Boolean(false));
                    else{
                        dbh.signUpUser(user);
                        Outputstream.writeObject(new Boolean(true));
                    }
                }
                break;

                case "GiveProviders":
                {
                        System.out.println("Отправка данных о поставщиках...");
                        Outputstream.writeObject(GiveProviders());
                }
                break;

                case "AddProvider":
                {
                    System.out.println("Добавление поставщика...");
                    Provider provider=new Provider((Provider)Inputstream.readObject());
                    dbh.AddProvider(provider);
                }
                break;

                case "UpdateProvider":
                {
                    System.out.println("Изменение поставщика...");
                    Provider provider=new Provider((Provider)Inputstream.readObject());
                    dbh.UpdateProvider(provider);
                }
                break;

                case "DeleteProvider":
                {
                    System.out.println("Удаление поставщика...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteProvider(Id);
                }
                break;

                case "GiveCategories":
                {
                    System.out.println("Отправка данных о категориях...");
                    Outputstream.writeObject(GiveCategories());
                }
                break;

                case "AddCategory":
                {
                    System.out.println("Добавление категории...");
                    Category category=new Category((Category)Inputstream.readObject());
                    dbh.AddCategory(category);
                }
                break;

                case "UpdateCategory":
                {
                    System.out.println("Изменение категории...");
                    Category category=new Category((Category)Inputstream.readObject());
                    dbh.UpdateCategory(category);
                }
                break;

                case "DeleteCategory":
                {
                    System.out.println("Удаление категории...");
                    String CathName=((String)Inputstream.readObject());
                    dbh.DeleteCategory(CathName);
                }
                break;

                case "GiveProducts":
                {
                    System.out.println("Отправка данных о товарах...");
                    Outputstream.writeObject(GiveProducts());
                }
                break;

                case "GiveProductsFilter":
                {
                    Integer Number=((Integer)Inputstream.readObject());
                    Integer Direction=((Integer)Inputstream.readObject());
                    System.out.println("Отправка отфильтрованных данных о товарах...");
                    Outputstream.writeObject(GiveProductsFilter(Number,Direction));
                }
                break;

                case "AddProduct":
                {
                    System.out.println("Добавление товара...");
                    Product product=new Product((Product)Inputstream.readObject());
                    dbh.AddProduct(product);
                }
                break;

                case "UpdateProduct":
                {
                    System.out.println("Изменение товара...");
                    Product product=new Product((Product)Inputstream.readObject());
                    dbh.UpdateProduct(product);
                }
                break;

                case "DeleteProduct":
                {
                    System.out.println("Удаление товара...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteProduct(Id);
                }
                break;

                case "GiveClients":
                {
                    System.out.println("Отправка данных о клиетнах...");
                    Outputstream.writeObject(GiveClients());
                }
                break;

                case "AddClient":
                {
                    System.out.println("Добавление клиента...");
                    Client client=new Client((Client)Inputstream.readObject());
                    dbh.AddClient(client);
                }
                break;

                case "UpdateClient":
                {
                    System.out.println("Изменение клиента...");
                    Client client=new Client((Client)Inputstream.readObject());
                    dbh.UpdateClient(client);
                }
                break;

                case "DeleteClient":
                {
                    System.out.println("Удаление клиента...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteClient(Id);
                }
                break;

                case "GiveOrders":
                {
                    System.out.println("Отправка данных о заказах...");
                    Outputstream.writeObject(GiveOrders());
                }
                break;

                case "GiveClientOrders":
                {
                    Integer ClientId = ((Integer)Inputstream.readObject());
                    System.out.println("Отправка данных о заказах клиента: "+ClientId);
                    Outputstream.writeObject(GiveClientOrders(ClientId));
                }
                break;

                case "AddOrder":
                {
                    System.out.println("Добавление заказа...");
                    Order order=new Order((Order)Inputstream.readObject());
                    dbh.AddOrder(order);
                }
                break;

                case "UpdateOrder":
                {
                    System.out.println("Изменение заказа...");
                    Order order=new Order((Order)Inputstream.readObject());
                    dbh.UpdateOrder(order);
                }
                break;

                case "DeleteOrder":
                {
                    System.out.println("Удаление заказа...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteOrder(Id);
                }
                break;

                case "CountPrice":
                {
                    System.out.println("Вычисление цены...");
                    dbh.CountPrice();
                }
                break;

                case "GetUsers":
                {
                    System.out.println("Получение списка пользователей...");
                    Outputstream.writeObject(GiveUsers());
                }
                break;
                case "UpdateUser":
                {
                    System.out.println("Блокировка пользователя...");
                    User user = (User)Inputstream.readObject();
                    dbh.UpdateUserRole(user.getUserId(), user.getUserAdmin());
                }
                default:
                    System.out.println("Неправильная комманда");
                    break;
            }
            socket.close();
            System.out.println("Конец передачи...");
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public ArrayList<Provider> GiveProviders(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Provider> data = new ArrayList<Provider>();
        ResultSet result = dbh.getAll(Const.PROVIDER_TABLE);
        try {
            while (result.next()) {
                Provider object = new Provider();
                object.setProvider_id(result.getInt(Const.PROVIDER_ID));
                object.setProvider_name(result.getString(Const.PROVIDER_NAME));
                object.setProvider_address(result.getString(Const.PROVIDER_ADDRESS));
                object.setProvider_phonenumber(result.getString(Const.PROVIDER_PHONE));
                //System.out.println((object.provider_id).toString() + object.provider_name.toString()
                //      + object.provider_address.toString() + object.provider_phonenumber.toString());
                data.add(object);
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                }
                return data;
    }

    public ArrayList<Category> GiveCategories(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Category> data = new ArrayList<Category>();
        ResultSet result = dbh.getAll(Const.CATEGORY_TABLE);
        try {
            while (result.next()) {
                Category object = new Category();
                object.setCategory_name(result.getString(Const.CATEGORY_NAME));
                object.setCategory_number(result.getInt(Const.CATEGORY_PRODUCT_NUMBER));
                object.setCategory_description(result.getString(Const.CATEGORY_DESCRIPTION));
                object.setCategory_availability(result.getString(Const.CATEGORY_AVAILABILITY));
                data.add(object);
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                }
                return data;
    }

    public ArrayList<Product> GiveProducts(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Product> data = new ArrayList<Product>();
        ResultSet result = dbh.getAll(Const.PRODUCT_TABLE);
        try {
            while (result.next()) {
                Product object = new Product();
                object.setProduct_id(result.getInt(Const.PRODUCT_ID));
                object.setProduct_provider_id(result.getInt(Const.PRODUCT_PROVIDER_ID));
                object.setProduct_name(result.getString(Const.PRODUCT_NAME));
                object.setProduct_number(result.getInt(Const.PRODUCT_NUMBER));
                object.setProduct_price(result.getInt(Const.PRODUCT_PRICE));
                object.setProduct_category_name(result.getString(Const.PRODUCT_CATEGORY_NAME));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Product> GiveProductsFilter(Integer Number, Integer Direction){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Product> data = new ArrayList<Product>();
        ResultSet result = dbh.getProductFilter(Number, Direction);
        try {
            while (result.next()) {
                Product object = new Product();
                object.setProduct_id(result.getInt(Const.PRODUCT_ID));
                object.setProduct_provider_id(result.getInt(Const.PRODUCT_PROVIDER_ID));
                object.setProduct_name(result.getString(Const.PRODUCT_NAME));
                object.setProduct_number(result.getInt(Const.PRODUCT_NUMBER));
                object.setProduct_price(result.getInt(Const.PRODUCT_PRICE));
                object.setProduct_category_name(result.getString(Const.PRODUCT_CATEGORY_NAME));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Client> GiveClients(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Client> data = new ArrayList<Client>();
        ResultSet result = dbh.getAll(Const.CLIENT_TABLE);
        try {
            while (result.next()) {
                Client object = new Client();
                object.setClient_id(result.getInt(Const.CLIENT_ID));
                object.setClient_name(result.getString(Const.CLIENT_NAME));
                object.setClient_surname(result.getString(Const.CLIENT_SURNAME));
                object.setClient_lastname(result.getString(Const.CLIENT_LAST_NAME));
                object.setClient_phone(result.getString(Const.CLIENT_PHONE));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Order> GiveOrders(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Order> data = new ArrayList<Order>();
        ResultSet result = dbh.getAll(Const.ORDER_TABLE);
        try {
            while (result.next()) {
                Order object = new Order();
                object.setOrder_id(result.getInt(Const.ORDER_ID));
                object.setOrder_client_id(result.getInt(Const.ORDER_CLIENT_ID));
                object.setOrder_product_id(result.getInt(Const.ORDER_PRODUCT_ID));
                object.setOrder_provider_id(result.getInt(Const.ORDER_PROVIDER_ID));
                object.setOrder_product_number(result.getInt(Const.ORDER_PRODUCT_NUMBER));
                object.setOrder_finalprice(result.getInt(Const.ORDER_FINAL_PRICE));
                if (result.getInt(Const.ORDER_IS_READY) == 1)
                    object.setOrder_status("Готов к отправке");
                else
                    object.setOrder_status("Сбор заказа");
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Order> GiveClientOrders(Integer ClientId){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Order> data = new ArrayList<Order>();
        ResultSet result = dbh.getClientOrders(ClientId);
        try {
            while (result.next()) {
                Order object = new Order();
                object.setOrder_id(result.getInt(Const.ORDER_ID));
                object.setOrder_client_id(result.getInt(Const.ORDER_CLIENT_ID));
                object.setOrder_product_id(result.getInt(Const.ORDER_PRODUCT_ID));
                object.setOrder_provider_id(result.getInt(Const.ORDER_PROVIDER_ID));
                object.setOrder_product_number(result.getInt(Const.ORDER_PRODUCT_NUMBER));
                object.setOrder_finalprice(result.getInt(Const.ORDER_FINAL_PRICE));
                if (result.getInt(Const.ORDER_IS_READY) == 1)
                    object.setOrder_status("Готов к отправке");
                else
                    object.setOrder_status("Сбор заказа");
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<User> GiveUsers()
    {
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<User> data = new ArrayList<>();
        ResultSet resultSet = dbh.getAll(Const.USER_TABLE);
        try
        {
            while(resultSet.next())
            {
                User user = new User();
                user.setUserId(resultSet.getInt(Const.USERS_ID));
                user.setUserLogin(resultSet.getString(Const.USERS_LOGIN));
                user.setUserName(resultSet.getString(Const.USERS_USERNAME));
                user.setUserAdmin(resultSet.getInt(Const.USERS_ADMIN));
                data.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}