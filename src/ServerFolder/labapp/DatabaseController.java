package ServerFolder.labapp;

import ClientFolder.sample.DatabaseTables.*;

import java.sql.*;

public class DatabaseController extends Configuration {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +"/"+ dbName +"?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" + Constants.USERS_USERNAME +
                "," + Constants.USERS_LOGIN + "," + Constants.USERS_PASSWORD + ")"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getUserLogin());
            prSt.setString(3, user.getUserPassword());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        public void AddProvider(Provider provider){
            String insert = "INSERT INTO " + Constants.PROVIDER_TABLE + "(" + Constants.PROVIDER_ID +
                    "," + Constants.PROVIDER_NAME + "," + Constants.PROVIDER_ADDRESS + "," + Constants.PROVIDER_PHONE +")"
                    + "VALUES(?,?,?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setInt(1, provider.getProvider_id());
                prSt.setString(2, provider.getProvider_name());
                prSt.setString(3, provider.getProvider_address());
                prSt.setString(4, provider.getProvider_phonenumber());
                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    public void UpdateProvider(Provider provider){
        String update = "UPDATE " + Constants.PROVIDER_TABLE +" " + "SET"+ " " + Constants.PROVIDER_ID+ "=?" +
                "," + Constants.PROVIDER_NAME+ "=?" +  "," + Constants.PROVIDER_ADDRESS  + "=?" + "," + Constants.PROVIDER_PHONE
                + "=?" + " " + "WHERE" + " " + Constants.PROVIDER_ID + "=" + provider.getProvider_id();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setInt(1, provider.getProvider_id());
            prSt.setString(2, provider.getProvider_name());
            prSt.setString(3, provider.getProvider_address());
            prSt.setString(4, provider.getProvider_phonenumber());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteProvider(Integer Id){
        String delete = "DELETE FROM " + Constants.PROVIDER_TABLE +" "+ "WHERE" + " " + Constants.PROVIDER_ID + "=" + Id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddCategory(Category category){
        String insert = "INSERT INTO " + Constants.CATEGORY_TABLE + "(" + Constants.CATEGORY_NAME +
                "," + Constants.CATEGORY_PRODUCT_NUMBER + "," + Constants.CATEGORY_DESCRIPTION + "," + Constants.CATEGORY_AVAILABILITY +")"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, category.getCategory_name());
            prSt.setInt(2, category.getCategory_number());
            prSt.setString(3, category.getCategory_description());
            prSt.setString(4, category.getCategory_availability());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateCategory(Category category){
        String update = "UPDATE " + Constants.CATEGORY_TABLE +" " + "SET"+ " " + Constants.CATEGORY_NAME+ "=?" +
                "," + Constants.CATEGORY_PRODUCT_NUMBER+ "=?" +  "," + Constants.CATEGORY_DESCRIPTION  + "=?" + "," + Constants.CATEGORY_AVAILABILITY
                + "=?" + " " + "WHERE" + " " + Constants.CATEGORY_NAME + "=" + category.getCategory_name();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, category.getCategory_name());
            prSt.setInt(2, category.getCategory_number());
            prSt.setString(3, category.getCategory_description());
            prSt.setString(4, category.getCategory_availability());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteCategory(String CathName){
        String delete = "DELETE FROM " + Constants.CATEGORY_TABLE +" "+ "WHERE" + " " + Constants.CATEGORY_NAME + "='" + CathName + "'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddProduct(Product product){
        String insert = "INSERT INTO " + Constants.PRODUCT_TABLE + "(" + Constants.PRODUCT_ID +
                "," + Constants.PRODUCT_PROVIDER_ID + "," + Constants.PRODUCT_NAME + "," + Constants.PRODUCT_NUMBER +
                "," + Constants.PRODUCT_PRICE + "," + Constants.PRODUCT_CATEGORY_NAME +")"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, product.getProduct_id());
            prSt.setInt(2, product.getProduct_provider_id());
            prSt.setString(3, product.getProduct_name());
            prSt.setInt(4, product.getProduct_number());
            prSt.setInt(5, product.getProduct_price());
            prSt.setString(6, product.getProduct_category_name());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateProduct(Product product){
        String update = "UPDATE " + Constants.PRODUCT_TABLE +" " + "SET"+ " " + Constants.PRODUCT_ID+ "=?" +
                "," + Constants.PRODUCT_PROVIDER_ID+ "=?" +  "," + Constants.PRODUCT_NAME  + "=?" + "," + Constants.PRODUCT_NUMBER
                + "=?" +  "," + Constants.PRODUCT_PRICE + "=?" + "," +
                Constants.PRODUCT_CATEGORY_NAME + "=?" + " " + "WHERE" + " " + Constants.PRODUCT_ID + "=" + product.getProduct_id();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setInt(1, product.getProduct_id());
            prSt.setInt(2, product.getProduct_provider_id());
            prSt.setString(3, product.getProduct_name());
            prSt.setInt(4, product.getProduct_number());
            prSt.setInt(5, product.getProduct_price());
            prSt.setString(6, product.getProduct_category_name());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteProduct(Integer Id){
        String delete = "DELETE FROM " + Constants.PRODUCT_TABLE +" "+ "WHERE" + " " + Constants.PRODUCT_ID + "=" + Id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddClient(Client client){
        String insert = "INSERT INTO " + Constants.CLIENT_TABLE + "(" + Constants.CLIENT_ID +
                "," + Constants.CLIENT_NAME + "," + Constants.CLIENT_SURNAME + "," + Constants.CLIENT_LAST_NAME
                + "," + Constants.CLIENT_PHONE +")"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, client.getClient_id());
            prSt.setString(2, client.getClient_name());
            prSt.setString(3, client.getClient_surname());
            prSt.setString(4, client.getClient_lastname());
            prSt.setString(5, client.getClient_phone());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateClient(Client client){
        String update = "UPDATE " + Constants.CLIENT_TABLE +" " + "SET"+ " " + Constants.CLIENT_ID+ "=?" +
                "," + Constants.CLIENT_NAME+ "=?" +  "," + Constants.CLIENT_SURNAME  + "=?" + "," + Constants.CLIENT_LAST_NAME
                + "=?" + Constants.CLIENT_PHONE + "=?" + " " + "WHERE" + " " + Constants.CLIENT_ID + "=" + client.getClient_id();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setInt(1, client.getClient_id());
            prSt.setString(2, client.getClient_name());
            prSt.setString(3, client.getClient_surname());
            prSt.setString(4, client.getClient_lastname());
            prSt.setString(5, client.getClient_phone());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteClient(Integer Id){
        String delete = "DELETE FROM " + Constants.CLIENT_TABLE +" "+ "WHERE" + " " + Constants.CLIENT_ID + "=" + Id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddOrder(Order order){
        Integer IsReady;
        if (order.getOrder_status().equals("Готов к отправке"))
            IsReady = 1;
        else
            IsReady = 0;
        String insert = "INSERT INTO " + Constants.ORDER_TABLE + "(" + Constants.ORDER_ID +
                "," + Constants.ORDER_CLIENT_ID + "," + Constants.ORDER_PRODUCT_ID + "," + Constants.ORDER_PROVIDER_ID
                + "," + Constants.ORDER_PRODUCT_NUMBER + "," + Constants.ORDER_IS_READY +")"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, order.getOrder_id());
            prSt.setInt(2, order.getOrder_client_id());
            prSt.setInt(3, order.getOrder_product_id());
            prSt.setInt(4, order.getOrder_provider_id());
            prSt.setInt(5, order.getOrder_product_number());
            prSt.setInt(6, IsReady);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateOrder(Order order){
        Integer IsReady;
        if (order.getOrder_status().equals("Готов к отправке"))
            IsReady = 1;
        else
            IsReady = 0;
        String update = "UPDATE " + Constants.ORDER_TABLE +" " + "SET"+ " " + Constants.ORDER_ID+ "=?" +
                "," + Constants.ORDER_CLIENT_ID+ "=?" +  "," + Constants.ORDER_PRODUCT_ID  + "=?" + "," + Constants.ORDER_PROVIDER_ID
                + "=?" + "," + Constants.ORDER_PRODUCT_NUMBER + "=?" + "," + Constants.ORDER_IS_READY
                + "=?" + " " + "WHERE" + " " + Constants.ORDER_ID + "=" + order.getOrder_id();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setInt(1, order.getOrder_id());
            prSt.setInt(2, order.getOrder_client_id());
            prSt.setInt(3, order.getOrder_product_id());
            prSt.setInt(4, order.getOrder_provider_id());
            prSt.setInt(5, order.getOrder_product_number());
            prSt.setInt(6, IsReady);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOrder(Integer Id){
        String delete = "DELETE FROM " + Constants.ORDER_TABLE +" "+ "WHERE" + " " + Constants.ORDER_ID + "=" + Id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUsers()
    {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constants.USER_TABLE;

        try
        {
            Statement st = getDbConnection().createStatement();
            resSet = st.executeQuery(select);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " +
                Constants.USERS_LOGIN + "=? AND " + Constants.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserLogin());
            prSt.setString(2, user.getUserPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getClient(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constants.CLIENT_TABLE + " WHERE " +
                Constants.CLIENT_NAME + "=? AND " + Constants.CLIENT_SURNAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserLogin());
            prSt.setString(2, user.getUserPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return resSet;
    }


    public ResultSet getAll(String table_name){
        ResultSet resSet = null;
        String select = "SELECT * FROM "+table_name;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProductFilter(Integer Number, Integer Direction){
        String dir;
        if (Direction == 1)
             dir = " > " ;
        else
             dir = " < ";
        System.out.println(dir + Number);
        ResultSet resSet = null;
        String select = "SELECT * FROM "+ Constants.PRODUCT_TABLE + " WHERE " + Constants.PRODUCT_NUMBER + dir + Number;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getClientOrders(Integer ClientId) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Constants.ORDER_TABLE + " WHERE " + Constants.ORDER_CLIENT_ID + " = " + ClientId;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void CountPrice(){
        String update = "UPDATE " + Constants.ORDER_TABLE + " SET " + Constants.ORDER_FINAL_PRICE + " = "
                + "(" +  " SELECT " +  Constants.PRODUCT_PRICE + " FROM " + Constants.PRODUCT_TABLE +
                " WHERE " + Constants.PRODUCT_ID + " = " + Constants.ORDER_PRODUCT_ID + ") * " + Constants.ORDER_PRODUCT_NUMBER;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateUserRole(int id, int role)
    {
        String update = "UPDATE " + Constants.USER_TABLE + " SET " + Constants.USERS_ADMIN + "=?" +
                " WHERE " + Constants.USERS_ID + " = " + id;
        try
        {
            PreparedStatement ps = getDbConnection().prepareStatement(update);
            ps.setInt(1, role);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
