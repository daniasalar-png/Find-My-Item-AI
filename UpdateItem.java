import java.util.Scanner;

public class UpdateItem {

    private Scanner sc;

    public UpdateItem(Scanner sc) {
        this.sc = sc;
    }

    public void updateItem(String email) {

        System.out.println("\n===== UPDATE ITEM =====");

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Location: ");
        String location = sc.nextLine();

        ItemDAO dao = new ItemDAO();

        if (dao.updateItem(id, email, "item_name", "category", "color", location, "description")) {
            System.out.println("Item Updated Successfully!");
        } else {
            System.out.println("Item Not Found!");
        }
    }
}
