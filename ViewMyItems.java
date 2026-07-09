public class ViewMyItems {

    public void showItems(String email) {

        System.out.println("\n===== MY ITEMS =====");

        ItemDAO dao = new ItemDAO();

        dao.viewMyItems(email);
    }
}