import java.util.Scanner;

public class Dashboard {

    private Scanner sc;

    public Dashboard(Scanner sc) {
        this.sc = sc;
    }

    public void showDashboard(String userEmail) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        FIND MY ITEM AI");
            System.out.println("=================================");
            System.out.println("Logged in as: " + userEmail);
            System.out.println("---------------------------------");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. Search Item");
            System.out.println("4. View My Items");
            System.out.println("5. Update Item");
            System.out.println("6. Delete Item");
            System.out.println("7. Mark Item as Returned");
            System.out.println("8. Logout");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    LostItem lostItem = new LostItem(sc);
                    lostItem.reportLostItem(userEmail);
                    break;

                case 2:
                    FoundItem foundItem = new FoundItem(sc);
                    foundItem.reportFoundItem(userEmail);
                    break;

                case 3:
                    SearchItem search = new SearchItem(sc);
                    search.searchItem();
                    break;

                case 4:
                    ViewMyItems view = new ViewMyItems();
                    view.showItems(userEmail);
                    break;
                
                case 5:
                    UpdateItem update = new UpdateItem(sc);
                    update.updateItem(userEmail);
                    break;

                case 6:
                    DeleteItem delete = new DeleteItem(sc);
                    delete.deleteItem(userEmail);
                    break;

                case 7:
                    ReturnItem returned = new ReturnItem(sc);
                    returned.returnItem(userEmail);
                    break;

                case 8:
                System.out.println("Logged Out Successfully!");
                return;

                case 9:
                    System.out.println("\nLogged Out Successfully.");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}