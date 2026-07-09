import java.util.Scanner;

public class FoundItem {

    private Scanner sc;

    public FoundItem(Scanner sc) {
        this.sc = sc;
    }

    public void reportFoundItem(String userEmail) {

        System.out.println("\n===== REPORT FOUND ITEM =====");

        System.out.print("Item Name: ");
        String itemName = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Color: ");
        String color = sc.nextLine();

        System.out.print("Found Location: ");
        String location = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        Item item = new Item(userEmail, itemName, category,
                color, location, description, "FOUND");

        ItemDAO dao = new ItemDAO();

        if (dao.addItem(item)) {
            System.out.println("\nFound Item Reported Successfully!");
            AIMatcher ai = new AIMatcher();
            ai.findMatch(item);
        } else {
            System.out.println("\nFailed to Report Item!");
        }
    }
}