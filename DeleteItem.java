import java.util.Scanner;

public class DeleteItem {

    private Scanner sc;

    public DeleteItem(Scanner sc) {
        this.sc = sc;
    }

    public void deleteItem(String email) {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        ItemDAO dao = new ItemDAO();

        if (dao.deleteItem(id, email)) {
            System.out.println("Item Deleted Successfully!");
        } else {
            System.out.println("Item Not Found!");
        }
    }
}
