import java.util.Scanner;

public class LostItem {

    private Scanner sc;

    public LostItem(Scanner sc) {
        this.sc = sc;
    }

    public void reportLostItem(String userEmail) {

        System.out.println("\n===== REPORT LOST ITEM =====");

        System.out.print("Item Name: ");
        String itemName = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Color: ");
        String color = sc.nextLine();

        System.out.print("Lost Location: ");
        String location = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        Item item = new Item(userEmail, itemName, category,
                color, location, description, "LOST");

        ItemDAO dao = new ItemDAO();

        if (dao.addItem(item)) {
            System.out.println("\nLost Item Reported Successfully!");
            AIMatcher ai = new AIMatcher();
            ai.findMatch(item);
        } else {
            System.out.println("\nFailed to Report Item!");
        }
    }
}
