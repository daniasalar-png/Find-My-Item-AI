import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class MyItemsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public MyItemsFrame(String email) {

        setTitle("My Items");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(248,250,252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(410,10,70,70);

        // Title
        JLabel title = new JLabel("My Reported Items");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setBounds(300,90,320,35);

        // Table
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Item");
        model.addColumn("Category");
        model.addColumn("Color");
        model.addColumn("Location");
        model.addColumn("Status");

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40,150,800,330);

        // Load Data
        ItemDAO dao = new ItemDAO();

        try {

            ResultSet rs = dao.getMyItems(email);

            while(rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("color"),
                        rs.getString("location"),
                        rs.getString("status")

                });

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(375,510,150,40);
        btnBack.setBackground(new Color(37,99,235));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setFont(new Font("Segoe UI",Font.BOLD,15));

        btnBack.addActionListener(e -> {

            dispose();
            new DashboardFrame(email);

        });

        panel.add(lblLogo);
        panel.add(title);
        panel.add(scroll);
        panel.add(btnBack);

        add(panel);

        setVisible(true);

    }

}
