package ClientPSP.sample.Tables;

import java.io.Serializable;

public class Provider implements Serializable {
    public Integer provider_id;
    public String provider_name = new String();
    public String provider_address = new String();
    public String provider_phonenumber = new String();

    public Provider() {
    }


    public Provider(Integer Id, String Name, String Address, String Phone) {
        this.provider_id = Id;
        this.provider_name = Name;
        this.provider_address = Address;
        this.provider_phonenumber = Phone;

    }

    public Provider(Provider provider){
        this.provider_id=provider.provider_id;
        this.provider_name=provider.provider_name;
        this.provider_address=provider.provider_address;
        this.provider_phonenumber=provider.provider_phonenumber;
    }

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_address() {
        return provider_address;
    }

    public void setProvider_address(String provider_address) {
        this.provider_address = provider_address;
    }

    public String getProvider_phonenumber() {
        return provider_phonenumber;
    }

    public void setProvider_phonenumber(String provider_phonenumber) {
        this.provider_phonenumber = provider_phonenumber;
    }
}
