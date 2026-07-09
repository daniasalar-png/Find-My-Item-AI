import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    JTextField txtEmail;
    JPasswordField txtPassword;
    JButton btnLogin;
    JButton btnRegister;

    public LoginFrame() {

        setTitle("Find My Item AI");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248, 250, 252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(280, 20, 140, 140);

        // Title
        JLabel title = new JLabel("Find My Item AI");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(30, 41, 59));
        title.setBounds(220, 160, 300, 40);

        // Subtitle
        JLabel sub = new JLabel("Smart Lost & Found Management System");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sub.setForeground(Color.GRAY);
        sub.setBounds(175, 200, 350, 25);

        // Email Label
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblEmail.setBounds(170, 250, 100, 25);

        // Email TextField
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtEmail.setBounds(170, 275, 350, 35);

        // Password Label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblPassword.setBounds(170, 325, 100, 25);

        // Password Field
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPassword.setBounds(170, 350, 350, 35);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 420, 140, 40);
        btnLogin.setBackground(new Color(37, 99, 235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(360, 420, 140, 40);
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setForeground(new Color(37, 99, 235));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegister.setFocusPainted(false);

        // Add Components
        panel.add(lblLogo);
        panel.add(title);
        panel.add(sub);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);

        // Login Button Action
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPassword.getPassword());

                UserDAO dao = new UserDAO();

                if (dao.loginUser(email, password)) {

                    JOptionPane.showMessageDialog(null,
                            "Login Successful!");

                    dispose();

                    new DashboardFrame(email);

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Invalid Email or Password!");

                }

            }

        });
        btnRegister.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

        dispose();

        new RegisterFrame();

    }

});

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}