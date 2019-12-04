package ClientPSP.sample.Tables;

import java.io.Serializable;

public class Category implements Serializable {
    public String category_name = new String();
    public Integer category_number;
    public String category_description = new String();
    public String category_availability = new String();

    public Category() {
    }


    public Category(String Name, Integer CategoryNumber, String Description, String Availability) {
        this.category_name = Name;
        this.category_number = CategoryNumber;
        this.category_description = Description;
        this.category_availability = Availability;

    }

    public Category(Category category){
        this.category_name=category.category_name;
        this.category_number=category.category_number;
        this.category_description=category.category_description;
        this.category_availability=category.category_availability;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_number() {
        return category_number;
    }

    public void setCategory_number(Integer category_number) {
        this.category_number = category_number;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public String getCategory_availability() {
        return category_availability;
    }

    public void setCategory_availability(String category_availability) {
        this.category_availability = category_availability;
    }
}
