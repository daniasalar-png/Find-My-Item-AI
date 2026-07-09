import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ManageMyItemsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private String email;

    public ManageMyItemsFrame(String email) {

        this.email = email;

        setTitle("Manage My Items");
        setSize(900,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(248,250,252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(405,10,80,80);

        // Title
        JLabel title = new JLabel("Manage My Items");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setBounds(305,100,300,35);

        // Table
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Item");
        model.addColumn("Category");
        model.addColumn("Color");
        model.addColumn("Location");
        model.addColumn("Description");
        model.addColumn("Status");

        table = new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40,160,810,320);

        loadItems();

        // Refresh Button
        JButton btnRefresh = new JButton("Refresh");

        btnRefresh.setBounds(200,520,140,40);
        btnRefresh.setBackground(new Color(37,99,235));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);

        btnRefresh.addActionListener(e->{

            loadItems();

        });

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(40,520,120,40);
        btnEdit.setBackground(new Color(34,197,94));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFocusPainted(false);

        btnEdit.addActionListener(e -> {

    int row = table.getSelectedRow();

    if(row==-1){

        JOptionPane.showMessageDialog(this,
                "Please select an item.");

        return;

    }

    int id = Integer.parseInt(model.getValueAt(row,0).toString());

    String itemName = JOptionPane.showInputDialog(
            this,
            "Item Name",
            model.getValueAt(row,1));

    String category = JOptionPane.showInputDialog(
            this,
            "Category",
            model.getValueAt(row,2));

    String color = JOptionPane.showInputDialog(
            this,
            "Color",
            model.getValueAt(row,3));

    String location = JOptionPane.showInputDialog(
            this,
            "Location",
            model.getValueAt(row,4));

    String description = JOptionPane.showInputDialog(
            this,
            "Description",
            model.getValueAt(row,5));

    ItemDAO dao = new ItemDAO();

    if(dao.updateItem(id,email,itemName,category,color,location,description)){

        JOptionPane.showMessageDialog(this,
                "Item Updated Successfully!");

        loadItems();

    }else{

        JOptionPane.showMessageDialog(this,
                "Update Failed!");

    }

});

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(360,520,140,40);
        btnDelete.setBackground(new Color(239,68,68));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);

        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){

                JOptionPane.showMessageDialog(this,
                        "Please select an item.");

                return;
            }

            int id = Integer.parseInt(model.getValueAt(row,0).toString());

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this item?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){

                ItemDAO dao = new ItemDAO();

                if(dao.deleteItem(id,email)){

                    JOptionPane.showMessageDialog(this,
                            "Item Deleted Successfully!");

                    loadItems();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Delete Failed!");

                }

            }

        });

        JButton btnReturn = new JButton("Mark Returned");
        btnReturn.setBounds(710,520,150,40);
        btnReturn.setBackground(new Color(245,158,11));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFocusPainted(false);

        btnReturn.addActionListener(e -> {

        int row = table.getSelectedRow();

        if(row==-1){

        JOptionPane.showMessageDialog(this,
                "Please select an item.");

        return;

        }

    int id = Integer.parseInt(model.getValueAt(row,0).toString());

    ItemDAO dao = new ItemDAO();

    if(dao.returnItem(id,email)){

        JOptionPane.showMessageDialog(this,
                "Item Marked as Returned!");

        loadItems();

    }else{

        JOptionPane.showMessageDialog(this,
                "Operation Failed!");

    }

});

        // Back Button
        JButton btnBack = new JButton("Back");

        btnBack.setBounds(550,520,140,40);
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(37,99,235));
        btnBack.setFocusPainted(false);

        btnBack.addActionListener(e->{

            dispose();

            new DashboardFrame(email);

        });

        panel.add(lblLogo);
        panel.add(title);
        panel.add(scroll);
        panel.add(btnRefresh);
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnReturn);
        panel.add(btnBack);

        add(panel);

        setVisible(true);

    }

    // Load Items
    private void loadItems(){

        model.setRowCount(0);

        ItemDAO dao = new ItemDAO();

        try{

            ResultSet rs = dao.getMyItems(email);

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("color"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("status")

                });

            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
