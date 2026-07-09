import java.util.Scanner;

public class ReturnItem {

    private Scanner sc;

    public ReturnItem(Scanner sc) {
        this.sc = sc;
    }

    public void returnItem(String email) {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        ItemDAO dao = new ItemDAO();

        if (dao.returnItem(id, email)) {
            System.out.println("Item Marked as Returned!");
        } else {
            System.out.println("Item Not Found!");
        }
    }
}
