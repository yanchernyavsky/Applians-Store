package ServerPSP.labapp;

public class Const {
    public static final String USER_TABLE = "users";

    public static final String USERS_ID = "userId";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_ADMIN = "admin";

    public static final String PROVIDER_TABLE = "provider";

    public static final String PROVIDER_ID = "idprovider";
    public static final String PROVIDER_NAME = "companyname";
    public static final String PROVIDER_ADDRESS = "address";
    public static final String PROVIDER_PHONE = "phonenumber";

    public static final String PRODUCT_TABLE = "product";

    public static final String PRODUCT_ID = "idproduct";
    public static final String PRODUCT_PROVIDER_ID = "idprovider";
    public static final String PRODUCT_NAME = "productname";
    public static final String PRODUCT_NUMBER = "number";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_CATEGORY_NAME = "categoryname";

    public static final String CATEGORY_TABLE = "category";

    public static final String CATEGORY_NAME = "categoryname";
    public static final String CATEGORY_PRODUCT_NUMBER = "productnumber";
    public static final String CATEGORY_DESCRIPTION = "description";
    public static final String CATEGORY_AVAILABILITY = "availability";

    public static final String CLIENT_TABLE = "client";

    public static final String CLIENT_ID = "idclient";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_SURNAME = "surname";
    public static final String CLIENT_LAST_NAME = "lastname";
    public static final String CLIENT_PHONE= "phonenumber";

    public static final String ORDER_TABLE = "orders";

    public static final String ORDER_ID = "idorder";
    public static final String ORDER_CLIENT_ID = "idclient";
    public static final String ORDER_PRODUCT_ID = "idproductorder";
    public static final String ORDER_PROVIDER_ID = "idprovider";
    public static final String ORDER_PRODUCT_NUMBER = "productnumber";
    public static final String ORDER_FINAL_PRICE = "finalprice";
    public static final String ORDER_IS_READY = "isready";
}
