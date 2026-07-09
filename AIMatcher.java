import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AIMatcher {

    public void findMatch(Item item) {

        String status;

        if(item.getStatus().equals("LOST"))
            status = "FOUND";
        else
            status = "LOST";

        String query = "SELECT * FROM items WHERE item_name=? AND category=? AND color=? AND status=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, item.getItemName());
            ps.setString(2, item.getCategory());
            ps.setString(3, item.getColor());
            ps.setString(4, status);

            ResultSet rs = ps.executeQuery();

            boolean match = false;

            while(rs.next()) {

                match = true;

                System.out.println("\n==================================");
                System.out.println(" AI MATCH FOUND ");
                System.out.println("==================================");

                System.out.println("Item Name : " + rs.getString("item_name"));
                System.out.println("Category  : " + rs.getString("category"));
                System.out.println("Color     : " + rs.getString("color"));
                System.out.println("Location  : " + rs.getString("location"));
                System.out.println("Description : " + rs.getString("description"));

                System.out.println("\nPlease contact the administrator.");

            }

            if(!match) {
                System.out.println("\nNo Similar Items Found.");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public int calculateScore(Item lost, Item found) {

    int score = 0;

    if(lost.getItemName().equalsIgnoreCase(found.getItemName()))
        score += 40;

    if(lost.getCategory().equalsIgnoreCase(found.getCategory()))
        score += 25;

    if(lost.getColor().equalsIgnoreCase(found.getColor()))
        score += 20;

    if(lost.getLocation().equalsIgnoreCase(found.getLocation()))
        score += 15;

    return score;
}
}
