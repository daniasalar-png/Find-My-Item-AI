import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public RegisterFrame() {

        setTitle("Find My Item AI - Register");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248,250,252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(290,20,120,120);

        // Title
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBounds(235,150,250,35);

        // Name
        JLabel lblName = new JLabel("Full Name");
        lblName.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblName.setBounds(170,210,100,25);

        txtName = new JTextField();
        txtName.setBounds(170,235,350,35);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblEmail.setBounds(170,285,100,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(170,310,350,35);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI",Font.BOLD,15));
        lblPassword.setBounds(170,360,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170,385,350,35);

        // Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(180,450,140,40);
        btnRegister.setBackground(new Color(37,99,235));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(360,450,140,40);
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(37,99,235));
        btnBack.setFocusPainted(false);

        // Register Action
        btnRegister.addActionListener(e -> {

            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());

            User user = new User(name, email, password);

            UserDAO dao = new UserDAO();

            if (dao.registerUser(user)) {

                JOptionPane.showMessageDialog(this,
                        "Registration Successful!");

                dispose();
                new LoginFrame();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Registration Failed!");

            }

        });

        // Back Action
        btnBack.addActionListener(e -> {

            dispose();
            new LoginFrame();

        });

        panel.add(lblLogo);
        panel.add(title);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblEmail);
        panel.add(txtEmail);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(btnRegister);
        panel.add(btnBack);

        add(panel);

        setVisible(true);
    }
}
