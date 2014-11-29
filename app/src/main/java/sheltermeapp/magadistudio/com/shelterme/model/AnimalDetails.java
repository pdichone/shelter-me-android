package sheltermeapp.magadistudio.com.shelterme.model;

/**
 * Created by paulodichone on 11/26/14.
 */
public class AnimalDetails extends Animal {
    private String color;
    private String size;
    private int price;
    private String intakeDate;
    private String photos;
    private String description;
    private String declawed;

    public AnimalDetails(){

    }

    public AnimalDetails(String size, String color, String intakeDate, int price, String photos,
                         String description){

        this.size = size;
        this.color = color;
        this.intakeDate = intakeDate;
        this.price = price;
        this.photos = photos;
        this.description = description;

    }

    public String getDeclawed() {
        return declawed;
    }

    public void setDeclawed(String declawed) {
        this.declawed = declawed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(String intakeDate) {
        this.intakeDate = intakeDate;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
