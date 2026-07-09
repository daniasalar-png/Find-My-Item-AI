import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {

    // Add Item
    public boolean addItem(Item item) {

        String query = "INSERT INTO items(user_email, item_name, category, color, location, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, item.getUserEmail());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getColor());
            ps.setString(5, item.getLocation());
            ps.setString(6, item.getDescription());
            ps.setString(7, item.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search Item by Name
    public ResultSet searchItems(String keyword) {

    String query =
    "SELECT * FROM items WHERE item_name LIKE ? OR category LIKE ? OR color LIKE ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        String value = "%" + keyword + "%";

        ps.setString(1, value);
        ps.setString(2, value);
        ps.setString(3, value);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    // View My Items
    public void viewMyItems(String email) {

        String query = "SELECT * FROM items WHERE user_email = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("\n----------------------------");
                System.out.println("Item ID : " + rs.getInt("item_id"));
                System.out.println("Item Name : " + rs.getString("item_name"));
                System.out.println("Category : " + rs.getString("category"));
                System.out.println("Color : " + rs.getString("color"));
                System.out.println("Location : " + rs.getString("location"));
                System.out.println("Status : " + rs.getString("status"));
            }

            if (!found) {
                System.out.println("\nNo Items Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean updateItem(int id, String email, String itemName,
                          String category, String color,
                          String location, String description) {

    String query = "UPDATE items SET item_name=?, category=?, color=?, location=?, description=? WHERE item_id=? AND user_email=?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, itemName);
        ps.setString(2, category);
        ps.setString(3, color);
        ps.setString(4, location);
        ps.setString(5, description);
        ps.setInt(6, id);
        ps.setString(7, email);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
    public boolean deleteItem(int id, String email) {

    String query = "DELETE FROM items WHERE item_id=? AND user_email=?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        ps.setString(2, email);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
  }
  public boolean returnItem(int id, String email) {

    String query = "UPDATE items SET status='RETURNED' WHERE item_id=? AND user_email=?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        ps.setString(2, email);

        System.out.println("ID = " + id);
        System.out.println("Email = " + email);

        int rows = ps.executeUpdate();

        System.out.println("Rows Updated = " + rows);

        return rows > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
  public ResultSet getMyItems(String email) {

    String query = "SELECT * FROM items WHERE user_email = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, email);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
public ResultSet getAllItems() {

    String query = "SELECT * FROM items";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
public int countItems(String status) {

    String query = "SELECT COUNT(*) FROM items WHERE status=?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, status);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            return rs.getInt(1);

        }

    } catch(Exception e){

        e.printStackTrace();

    }

    return 0;
}
}