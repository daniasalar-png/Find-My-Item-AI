import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class SearchFrame extends JFrame {

    JTextField txtSearch;
    JTable table;
    DefaultTableModel model;

    public SearchFrame(String email) {

        setTitle("Search Items");
        setSize(850,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(248,250,252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(380,10,70,70);

        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setBounds(320,90,250,35);

        JLabel lbl = new JLabel("Item Name");
        lbl.setFont(new Font("Segoe UI",Font.BOLD,15));
        lbl.setBounds(100,150,100,25);

        txtSearch = new JTextField();
        txtSearch.setBounds(190,150,300,35);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(520,150,120,35);
        btnSearch.setBackground(new Color(37,99,235));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(660,150,100,35);

        model = new DefaultTableModel();

        model.addColumn("Item");
        model.addColumn("Category");
        model.addColumn("Color");
        model.addColumn("Location");
        model.addColumn("Status");

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50,220,740,300);

        btnSearch.addActionListener(e -> {

            model.setRowCount(0);

            ItemDAO dao = new ItemDAO();

            try {

                ResultSet rs = dao.searchItems(txtSearch.getText());

                while(rs.next()){

                    model.addRow(new Object[]{

                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("color"),
                        rs.getString("location"),
                        rs.getString("status")

                    });

                }

            } catch(Exception ex){

                ex.printStackTrace();

            }

        });

        btnBack.addActionListener(e->{

            dispose();

            new DashboardFrame(email);

        });

        panel.add(lblLogo);
        panel.add(title);
        panel.add(lbl);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnBack);
        panel.add(scroll);

        add(panel);

        setVisible(true);

    }

}