package ClientPSP.sample;

import ClientPSP.sample.Tables.*;

import java.util.ArrayList;

public class Service {

    public String SighUp(User user){
        System.out.println("Авторизация...");
        ClientTCP client = new ClientTCP();
        return  client.SendAuth("Authorization", user);
    }

    public Boolean Registration(User user){
        System.out.println("Регистрация...");
        ClientTCP client = new ClientTCP();
        return client.Send("Registration",user).booleanValue();
    }

    public ArrayList<Provider> GetProvider(){
        System.out.println("Получение поставщиков...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayProvider("GiveProviders");
    }

    public void AddProvider(Provider provider){
        System.out.println("Добавление поставщика...");
        ClientTCP client = new ClientTCP();
        client.Send("AddProvider",provider);
    }

    public void UpdateProvider(Provider provider){
        System.out.println("Изменение поставщика...");
        ClientTCP client = new ClientTCP();
        client.Send("UpdateProvider",provider);
    }

    public boolean DeleteProvider(Integer Id){
        System.out.println("Удаление поставщика...");
        ClientTCP client = new ClientTCP();
        return client.Send("DeleteProvider", Id);
    }

    public ArrayList<Category> GetCategory(){
        System.out.println("Получение категорий...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayCategory("GiveCategories");
    }

    public void AddCategory(Category category){
        System.out.println("Добавление категории...");
        ClientTCP client = new ClientTCP();
        client.Send("AddCategory",category);
    }

    public void UpdateCategory(Category category){
        System.out.println("Изменение категории...");
        ClientTCP client = new ClientTCP();
        client.Send("UpdateCategory",category);
    }

    public boolean DeleteCategory(String CathName){
        System.out.println("Удаление категории...");
        ClientTCP client = new ClientTCP();
        return client.Send("DeleteCategory", CathName);
    }

    public ArrayList<Product> GetProduct(){
        System.out.println("Получение товаров...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayProduct("GiveProducts");
    }

    public ArrayList<Product> GetProductFilter(Integer Number, Integer Direction){
        System.out.println("Получение товаров с учётом фильтрации...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayProduct("GiveProductsFilter" , Number, Direction);
    }

    public void AddProduct(Product product){
        System.out.println("Добавление товара...");
        ClientTCP client = new ClientTCP();
        client.Send("AddProduct",product);
    }

    public void UpdateProduct(Product product){
        System.out.println("Изменение товара...");
        ClientTCP client = new ClientTCP();
        client.Send("UpdateProduct",product);
    }

    public boolean DeleteProduct(Integer Id){
        System.out.println("Удаление товара...");
        ClientTCP client = new ClientTCP();
        return client.Send("DeleteProduct", Id);
    }

    public ArrayList<Client> GetClient(){
        System.out.println("Получение клиентов...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayClient("GiveClients");
    }

    public void AddClient(Client client){
        System.out.println("Добавление клиента...");
        ClientTCP clientTCP = new ClientTCP();
        clientTCP.Send("AddClient",client);
    }

    public void UpdateClient(Client client){
        System.out.println("Изменение клиента...");
        ClientTCP clientTCP = new ClientTCP();
        clientTCP.Send("UpdateClient",client);
    }

    public boolean DeleteClient(Integer Id){
        System.out.println("Удаление клиента...");
        ClientTCP client = new ClientTCP();
        return client.Send("DeleteClient", Id);
    }

    public ArrayList<Order> GetOrder(){
        System.out.println("Получение заказов...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayOrder("GiveOrders");
    }

    public ArrayList<Order> GetClientOrder(){
        System.out.println("Получение заказов клиента...");
        ClientTCP client = new ClientTCP();
        return client.SendArrayClientOrder("GiveClientOrders");
    }

    public void AddOrder(Order order){
        System.out.println("Добавление заказа...");
        ClientTCP client = new ClientTCP();
        client.Send("AddOrder",order);
    }

    public void UpdateOrder(Order order){
        System.out.println("Изменение заказа...");
        ClientTCP client = new ClientTCP();
        client.Send("UpdateOrder",order);
    }

    public boolean DeleteOrder(Integer Id){
        System.out.println("Удаление заказа...");
        ClientTCP client = new ClientTCP();
        return client.Send("DeleteOrder", Id);
    }

    public void CountPrice(){
        System.out.println("Расчёт финально цены");
        ClientTCP client = new ClientTCP();
        client.SendCommand("CountPrice");
    }
}
