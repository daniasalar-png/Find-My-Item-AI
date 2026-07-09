import java.util.Scanner;

public class SearchItem {

    private Scanner sc;

    public SearchItem(Scanner sc) {
        this.sc = sc;
    }

    public void searchItem() {

        System.out.println("\n===== SEARCH ITEM =====");

        System.out.print("Enter Item Name: ");
        String itemName = sc.nextLine();

        ItemDAO dao = new ItemDAO();

        dao.searchItems(itemName);
    }
}
