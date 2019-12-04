package ClientPSP.sample.Tables;

import java.io.Serializable;

public class Product implements Serializable{
    public Integer product_id;
    public Integer product_provider_id;
    public String product_name = new String();
    public Integer product_number;
    public Integer product_price;
    public String product_category_name = new String();

    public Product() {
    }


    public Product(Integer Id, Integer ProviderId, String Name, Integer Number, Integer Price, String CategoryName) {
        this.product_id = Id;
        this.product_provider_id = ProviderId;
        this.product_name = Name;
        this.product_number = Number;
        this.product_price = Price;
        this.product_category_name = CategoryName;

    }

    public Product(Product product){
        this.product_id=product.product_id;
        this.product_provider_id=product.product_provider_id;
        this.product_name=product.product_name;
        this.product_number=product.product_number;
        this.product_price=product.product_price;
        this.product_category_name=product.product_category_name;
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_provider_id() {
        return product_provider_id;
    }

    public void setProduct_provider_id(Integer product_provider_id) {
        this.product_provider_id = product_provider_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_number() {
        return product_number;
    }

    public void setProduct_number(Integer product_number) {
        this.product_number = product_number;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }
}
