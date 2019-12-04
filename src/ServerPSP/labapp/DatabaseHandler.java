package ServerPSP.labapp;

import ClientPSP.sample.Tables.*;

import java.sql.*;

public class DatabaseHandler extends Configs {
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
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_USERNAME +
                "," + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + ")"
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
            String insert = "INSERT INTO " + Const.PROVIDER_TABLE + "(" + Const.PROVIDER_ID +
                    "," + Const.PROVIDER_NAME + "," + Const.PROVIDER_ADDRESS + "," + Const.PROVIDER_PHONE +")"
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
        String update = "UPDATE " + Const.PROVIDER_TABLE +" " + "SET"+ " " + Const.PROVIDER_ID+ "=?" +
                "," + Const.PROVIDER_NAME+ "=?" +  "," + Const.PROVIDER_ADDRESS  + "=?" + "," + Const.PROVIDER_PHONE
                + "=?" + " " + "WHERE" + " " + Const.PROVIDER_ID + "=" + provider.getProvider_id();
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
        String delete = "DELETE FROM " + Const.PROVIDER_TABLE +" "+ "WHERE" + " " + Const.PROVIDER_ID + "=" + Id;
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
        String insert = "INSERT INTO " + Const.CATEGORY_TABLE + "(" + Const.CATEGORY_NAME +
                "," + Const.CATEGORY_PRODUCT_NUMBER + "," + Const.CATEGORY_DESCRIPTION + "," + Const.CATEGORY_AVAILABILITY +")"
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
        String update = "UPDATE " + Const.CATEGORY_TABLE +" " + "SET"+ " " + Const.CATEGORY_NAME+ "=?" +
                "," + Const.CATEGORY_PRODUCT_NUMBER+ "=?" +  "," + Const.CATEGORY_DESCRIPTION  + "=?" + "," + Const.CATEGORY_AVAILABILITY
                + "=?" + " " + "WHERE" + " " + Const.CATEGORY_NAME + "=" + category.getCategory_name();
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
        String delete = "DELETE FROM " + Const.CATEGORY_TABLE +" "+ "WHERE" + " " + Const.CATEGORY_NAME + "='" + CathName + "'";
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
        String insert = "INSERT INTO " + Const.PRODUCT_TABLE + "(" + Const.PRODUCT_ID +
                "," + Const.PRODUCT_PROVIDER_ID + "," + Const.PRODUCT_NAME + "," + Const.PRODUCT_NUMBER +
                "," + Const.PRODUCT_PRICE + "," + Const.PRODUCT_CATEGORY_NAME +")"
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
        String update = "UPDATE " + Const.PRODUCT_TABLE +" " + "SET"+ " " + Const.PRODUCT_ID+ "=?" +
                "," + Const.PRODUCT_PROVIDER_ID+ "=?" +  "," + Const.PRODUCT_NAME  + "=?" + "," + Const.PRODUCT_NUMBER
                + "=?" +  "," + Const.PRODUCT_PRICE + "=?" + "," +
                Const.PRODUCT_CATEGORY_NAME + "=?" + " " + "WHERE" + " " + Const.PRODUCT_ID + "=" + product.getProduct_id();
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
        String delete = "DELETE FROM " + Const.PRODUCT_TABLE +" "+ "WHERE" + " " + Const.PRODUCT_ID + "=" + Id;
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
        String insert = "INSERT INTO " + Const.CLIENT_TABLE + "(" + Const.CLIENT_ID +
                "," + Const.CLIENT_NAME + "," + Const.CLIENT_SURNAME + "," + Const.CLIENT_LAST_NAME
                + "," + Const.CLIENT_PHONE +")"
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
        String update = "UPDATE " + Const.CLIENT_TABLE +" " + "SET"+ " " + Const.CLIENT_ID+ "=?" +
                "," + Const.CLIENT_NAME+ "=?" +  "," + Const.CLIENT_SURNAME  + "=?" + "," + Const.CLIENT_LAST_NAME
                + "=?" + Const.CLIENT_PHONE + "=?" + " " + "WHERE" + " " + Const.CLIENT_ID + "=" + client.getClient_id();
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
        String delete = "DELETE FROM " + Const.CLIENT_TABLE +" "+ "WHERE" + " " + Const.CLIENT_ID + "=" + Id;
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
        String insert = "INSERT INTO " + Const.ORDER_TABLE + "(" + Const.ORDER_ID +
                "," + Const.ORDER_CLIENT_ID + "," + Const.ORDER_PRODUCT_ID + "," + Const.ORDER_PROVIDER_ID
                + "," + Const.ORDER_PRODUCT_NUMBER + "," + Const.ORDER_IS_READY +")"
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
        String update = "UPDATE " + Const.ORDER_TABLE +" " + "SET"+ " " + Const.ORDER_ID+ "=?" +
                "," + Const.ORDER_CLIENT_ID+ "=?" +  "," + Const.ORDER_PRODUCT_ID  + "=?" + "," + Const.ORDER_PROVIDER_ID
                + "=?" + "," + Const.ORDER_PRODUCT_NUMBER + "=?" + "," + Const.ORDER_IS_READY
                + "=?" + " " + "WHERE" + " " + Const.ORDER_ID + "=" + order.getOrder_id();
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
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

    public void DeleteOrder(Integer Id){
        String delete = "DELETE FROM " + Const.ORDER_TABLE +" "+ "WHERE" + " " + Const.ORDER_ID + "=" + Id;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

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

    public ResultSet getClient(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " +
                Const.CLIENT_NAME + "=? AND " + Const.CLIENT_SURNAME + "=?";

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
        String select = "SELECT * FROM "+Const.PRODUCT_TABLE + " WHERE " + Const.PRODUCT_NUMBER + dir + Number;
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
        String select = "SELECT * FROM " + Const.ORDER_TABLE + " WHERE " + Const.ORDER_CLIENT_ID + " = " + ClientId;
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
        String update = "UPDATE " + Const.ORDER_TABLE + " SET " + Const.ORDER_FINAL_PRICE + " = "
                + "(" +  " SELECT " +  Const.PRODUCT_PRICE + " FROM " + Const.PRODUCT_TABLE +
                " WHERE " + Const.PRODUCT_ID + " = " + Const.ORDER_PRODUCT_ID + ") * " + Const.ORDER_PRODUCT_NUMBER;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
