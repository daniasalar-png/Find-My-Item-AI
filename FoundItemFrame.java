import javax.swing.*;
import java.awt.*;

public class FoundItemFrame extends JFrame {

    private JTextField txtItemName;
    private JTextField txtCategory;
    private JTextField txtColor;
    private JTextField txtLocation;
    private JTextArea txtDescription;

    public FoundItemFrame(String email) {

        setTitle("Report Found Item");
        setSize(700,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248,250,252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(305,10,80,80);

        // Title
        JLabel title = new JLabel("Report Found Item");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setBounds(220,100,320,35);

        // Item Name
        JLabel lblItem = new JLabel("Item Name");
        lblItem.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblItem.setBounds(120,160,120,25);

        txtItemName = new JTextField();
        txtItemName.setBounds(120,185,450,35);

        // Category
        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblCategory.setBounds(120,235,120,25);

        txtCategory = new JTextField();
        txtCategory.setBounds(120,260,450,35);

        // Color
        JLabel lblColor = new JLabel("Color");
        lblColor.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblColor.setBounds(120,310,120,25);

        txtColor = new JTextField();
        txtColor.setBounds(120,335,450,35);

        // Location
        JLabel lblLocation = new JLabel("Found Location");
        lblLocation.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblLocation.setBounds(120,385,120,25);

        txtLocation = new JTextField();
        txtLocation.setBounds(120,410,450,35);

        // Description
        JLabel lblDesc = new JLabel("Description");
        lblDesc.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblDesc.setBounds(120,460,120,25);

        txtDescription = new JTextArea();
        txtDescription.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtDescription);
        scroll.setBounds(120,485,450,70);

        // Submit Button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(170,575,140,40);
        btnSubmit.setBackground(new Color(37,99,235));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setFont(new Font("Segoe UI",Font.BOLD,15));

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(380,575,140,40);
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(37,99,235));
        btnBack.setFocusPainted(false);
        btnBack.setFont(new Font("Segoe UI",Font.BOLD,15));

        // Submit Action
        btnSubmit.addActionListener(e -> {

            Item item = new Item(
                    email,
                    txtItemName.getText(),
                    txtCategory.getText(),
                    txtColor.getText(),
                    txtLocation.getText(),
                    txtDescription.getText(),
                    "FOUND"
            );

            ItemDAO dao = new ItemDAO();

            if (dao.addItem(item)) {

                JOptionPane.showMessageDialog(this,
                        "Found Item Reported Successfully!");

                dispose();
                new DashboardFrame(email);

            } else {

                JOptionPane.showMessageDialog(this,
                        "Failed to Report Item!");

            }

        });

        // Back Action
        btnBack.addActionListener(e -> {

            dispose();
            new DashboardFrame(email);

        });

        // Add Components
        panel.add(lblLogo);
        panel.add(title);

        panel.add(lblItem);
        panel.add(txtItemName);

        panel.add(lblCategory);
        panel.add(txtCategory);

        panel.add(lblColor);
        panel.add(txtColor);

        panel.add(lblLocation);
        panel.add(txtLocation);

        panel.add(lblDesc);
        panel.add(scroll);

        panel.add(btnSubmit);
        panel.add(btnBack);

        add(panel);

        setVisible(true);
    }
}
