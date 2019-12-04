package ClientPSP.sample.Tables;

import java.io.Serializable;

public class Order implements Serializable{
    public Integer order_id;
    public Integer order_client_id;
    public Integer order_product_id;
    public Integer order_provider_id;
    public Integer order_product_number;
    public Integer order_finalprice;
    public String order_status;

    public Order() {
    }


    public Order(Integer Id, Integer ClientId, Integer ProductId, Integer ProviderId, Integer ProductNumber, String Status) {
        this.order_id = Id;
        this.order_client_id = ClientId;
        this.order_product_id = ProductId;
        this.order_provider_id = ProviderId;
        this.order_product_number = ProductNumber;
        this.order_status = Status;

    }

    public Order(Order order){
        this.order_id=order.order_id;
        this.order_client_id=order.order_client_id;
        this.order_product_id=order.order_product_id;
        this.order_provider_id=order.order_provider_id;
        this.order_product_number=order.order_product_number;
        this.order_finalprice=order.order_finalprice;
        this.order_status=order.order_status;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getOrder_client_id() {
        return order_client_id;
    }

    public void setOrder_client_id(Integer order_client_id) {
        this.order_client_id = order_client_id;
    }

    public Integer getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(Integer order_product_id) {
        this.order_product_id = order_product_id;
    }

    public Integer getOrder_provider_id() {
        return order_provider_id;
    }

    public void setOrder_provider_id(Integer order_provider_id) {
        this.order_provider_id = order_provider_id;
    }

    public Integer getOrder_product_number() {
        return order_product_number;
    }

    public void setOrder_product_number(Integer order_product_number) {
        this.order_product_number = order_product_number;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Integer getOrder_finalprice() {
        return order_finalprice;
    }

    public void setOrder_finalprice(Integer order_finalprice) {
        this.order_finalprice = order_finalprice;
    }
}
