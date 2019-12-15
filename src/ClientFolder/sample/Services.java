package ClientFolder.sample;

import ClientFolder.sample.DatabaseTables.*;

import java.util.ArrayList;

public class Services {

    public String SighUp(User user){
        System.out.println("Авторизация...");
        ClientApp client = new ClientApp();
        return  client.SendAuth("Authorization", user);
    }

    public Boolean Registration(User user){
        System.out.println("Регистрация...");
        ClientApp client = new ClientApp();
        return client.Send("Registration", user);
    }

    public ArrayList<Provider> GetProvider(){
        System.out.println("Получение поставщиков...");
        ClientApp client = new ClientApp();
        return client.SendArrayProvider("GiveProviders");
    }

    public void AddProvider(Provider provider){
        System.out.println("Добавление поставщика...");
        ClientApp client = new ClientApp();
        client.Send("AddProvider",provider);
    }

    public void UpdateProvider(Provider provider){
        System.out.println("Изменение поставщика...");
        ClientApp client = new ClientApp();
        client.Send("UpdateProvider",provider);
    }

    public boolean DeleteProvider(Integer Id){
        System.out.println("Удаление поставщика...");
        ClientApp client = new ClientApp();
        return client.Send("DeleteProvider", Id);
    }

    public ArrayList<Category> GetCategory(){
        System.out.println("Получение категорий...");
        ClientApp client = new ClientApp();
        return client.SendArrayCategory("GiveCategories");
    }

    public void AddCategory(Category category){
        System.out.println("Добавление категории...");
        ClientApp client = new ClientApp();
        client.Send("AddCategory",category);
    }

    public void UpdateCategory(Category category){
        System.out.println("Изменение категории...");
        ClientApp client = new ClientApp();
        client.Send("UpdateCategory",category);
    }

    public boolean DeleteCategory(String CathName){
        System.out.println("Удаление категории...");
        ClientApp client = new ClientApp();
        return client.Send("DeleteCategory", CathName);
    }

    public ArrayList<Product> GetProduct(){
        System.out.println("Получение товаров...");
        ClientApp client = new ClientApp();
        return client.SendArrayProduct("GiveProducts");
    }

    public ArrayList<Product> GetProductFilter(Integer Number, Integer Direction){
        System.out.println("Получение товаров с учётом фильтрации...");
        ClientApp client = new ClientApp();
        return client.SendArrayProduct("GiveProductsFilter" , Number, Direction);
    }

    public void AddProduct(Product product){
        System.out.println("Добавление товара...");
        ClientApp client = new ClientApp();
        client.Send("AddProduct",product);
    }

    public void UpdateProduct(Product product){
        System.out.println("Изменение товара...");
        ClientApp client = new ClientApp();
        client.Send("UpdateProduct",product);
    }

    public boolean DeleteProduct(Integer Id){
        System.out.println("Удаление товара...");
        ClientApp client = new ClientApp();
        return client.Send("DeleteProduct", Id);
    }

    public ArrayList<Client> GetClient(){
        System.out.println("Получение клиентов...");
        ClientApp client = new ClientApp();
        return client.SendArrayClient("GiveClients");
    }

    public void AddClient(Client client){
        System.out.println("Добавление клиента...");
        ClientApp clientApp = new ClientApp();
        clientApp.Send("AddClient",client);
    }

    public void UpdateClient(Client client){
        System.out.println("Изменение клиента...");
        ClientApp clientApp = new ClientApp();
        clientApp.Send("UpdateClient",client);
    }

    public boolean DeleteClient(Integer Id){
        System.out.println("Удаление клиента...");
        ClientApp client = new ClientApp();
        return client.Send("DeleteClient", Id);
    }

    public ArrayList<Order> GetOrder(){
        System.out.println("Получение заказов...");
        ClientApp client = new ClientApp();
        return client.SendArrayOrder("GiveOrders");
    }

    public ArrayList<Order> GetClientOrder(){
        System.out.println("Получение заказов клиента...");
        ClientApp client = new ClientApp();
        return client.SendArrayClientOrder("GiveClientOrders");
    }

    public void AddOrder(Order order){
        System.out.println("Добавление заказа...");
        ClientApp client = new ClientApp();
        client.Send("AddOrder",order);
    }

    public void UpdateOrder(Order order){
        System.out.println("Изменение заказа...");
        ClientApp client = new ClientApp();
        client.Send("UpdateOrder",order);
    }

    public boolean DeleteOrder(Integer Id){
        System.out.println("Удаление заказа...");
        ClientApp client = new ClientApp();
        return client.Send("DeleteOrder", Id);
    }

    public void CountPrice(){
        System.out.println("Расчёт финально цены");
        ClientApp client = new ClientApp();
        client.SendCommand("CountPrice");
    }

    public ArrayList<User> GetUsers()
    {
        System.out.println("Получение пользователей");
        ClientApp client = new ClientApp();
        return client.SendArrayUsers("GetUsers");
    }

    public void BanUser(User user)
    {
        System.out.println("Блокировка пользователя (id : " + user.getUserId() + ")");
        ClientApp client =  new ClientApp();
        client.ChangeUserRole(user);
    }
}
