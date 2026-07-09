public class Item {

    // Attributes
    private int itemId;
    private String userEmail;
    private String itemName;
    private String category;
    private String color;
    private String location;
    private String description;
    private String status;

    // Default Constructor
    public Item() {

    }

    // Parameterized Constructor
    public Item(String userEmail, String itemName, String category,
                String color, String location, String description,
                String status) {

        this.userEmail = userEmail;
        this.itemName = itemName;
        this.category = category;
        this.color = color;
        this.location = location;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getItemId() {
        return itemId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
