package ClientPSP.sample.Tables;

import java.io.Serializable;

public class Client implements Serializable{
    public Integer client_id;
    public String client_name = new String();
    public String client_surname = new String();
    public String client_lastname = new String();
    public String client_phone = new String();

    public Client() {
    }


    public Client(Integer Id, String Name, String Surname, String Lastname, String Phone) {
        this.client_id = Id;
        this.client_name = Name;
        this.client_surname = Surname;
        this.client_lastname = Lastname;
        this.client_phone = Phone;

    }

    public Client(Client client){
        this.client_id=client.client_id;
        this.client_name=client.client_name;
        this.client_surname=client.client_surname;
        this.client_lastname=client.client_lastname;
        this.client_phone=client.client_phone;
    }


    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public void setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }
}
