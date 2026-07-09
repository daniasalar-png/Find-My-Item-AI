import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class DashboardFrame extends JFrame {

    private String email;

    public DashboardFrame(String email) {

        this.email = email;

        setTitle("Find My Item AI - Dashboard");
        setSize(820,820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248, 250, 252));

        // Logo
        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(355, 15, 90, 90);

        // Title
        JLabel title = new JLabel("Find My Item AI");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(30, 41, 59));
        title.setBounds(280, 105, 260, 35);

        // Subtitle
        JLabel sub = new JLabel("Dashboard");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setForeground(Color.GRAY);
        sub.setBounds(345, 140, 150, 25);

        // Welcome
        JLabel welcome = new JLabel("Welcome,");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcome.setBounds(40, 190, 120, 25);

        JLabel user = new JLabel(this.email);
        user.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        user.setForeground(new Color(37, 99, 235));
        user.setBounds(40, 220, 350, 25);

        // Cards
        JPanel lost = createCard("📦", "Report Lost Item");
        lost.setBounds(80,250,250,140);

        JPanel found = createCard("🎒", "Report Found Item");
        found.setBounds(470,250,250,140);

        JPanel search = createCard("🔍", "Search Item");
        search.setBounds(80,430,250,140);

        JPanel view = createCard("📋", "View My Items");
        view.setBounds(470, 430, 250, 140);

        JPanel ai = createCard("🤖","AI Match");
        ai.setBounds(80,610,250,140);

        // Add Click Events

        lost.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LostItemFrame(email);
            }
        });

        found.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new FoundItemFrame(email);
            }
        });

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SearchFrame(email);
            }
        });

        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ManageMyItemsFrame(email);
            }
        });

        ai.addMouseListener(new MouseAdapter() {

    @Override
    public void mouseClicked(MouseEvent e) {

        dispose();
        new AIMatchFrame(email);

    }

});

        JPanel logout = createCard("🚪","Logout");
        logout.setBounds(470,610,250,140);
        logout.addMouseListener(new MouseAdapter(){

        @Override
        public void mouseClicked(MouseEvent e){

        dispose();
        new LoginFrame();

    }

});

        // Add Components
        panel.add(lblLogo);
        panel.add(title);
        panel.add(sub);
        panel.add(welcome);
        panel.add(user);

        panel.add(lost);
        panel.add(found);
        panel.add(search);
        panel.add(view);
        panel.add(ai);
        panel.add(logout);

        add(panel);

        setVisible(true);
    }

    // Card Design
    private JPanel createCard(String emoji, String text) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));

        JLabel icon = new JLabel(emoji);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        icon.setBounds(90, 20, 70, 60);

        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setBounds(10, 95, 220, 30);

        card.add(icon);
        card.add(title);

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(230, 242, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }

        });

        return card;
        
    }
    
    {
}
}