import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AIMatchFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextArea txtExplanation;
    private final String email;

    private final ArrayList<Item> lostMatches = new ArrayList<>();
    private final ArrayList<Item> foundMatches = new ArrayList<>();
    private final ArrayList<Integer> scores = new ArrayList<>();

    public AIMatchFrame(String email) {

        this.email = email;

        setTitle("Find My Item AI - AI Match");
        setSize(950,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(248,250,252));

        ImageIcon logo = new ImageIcon("assets/logo.png");
        Image img = logo.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(430,10,80,80);

        JLabel title = new JLabel("AI Match Results");
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setBounds(320,100,320,35);

        model = new DefaultTableModel();
        model.addColumn("Lost Item");
        model.addColumn("Found Item");
        model.addColumn("Category");
        model.addColumn("Score");
        model.addColumn("Confidence");

        table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(40,160,850,260);

        JLabel lblExplain = new JLabel("AI Explanation");
        lblExplain.setFont(new Font("Segoe UI",Font.BOLD,18));
        lblExplain.setBounds(40,440,200,30);

        txtExplanation = new JTextArea();
        txtExplanation.setEditable(false);
        txtExplanation.setFont(new Font("Segoe UI",Font.PLAIN,15));
        txtExplanation.setLineWrap(true);
        txtExplanation.setWrapStyleWord(true);

        JScrollPane explainScroll = new JScrollPane(txtExplanation);
        explainScroll.setBounds(40,475,850,110);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(220,610,140,40);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(560,610,140,40);

        btnRefresh.addActionListener(e -> loadMatches());

        btnBack.addActionListener(e -> {
            dispose();
            new DashboardFrame(this.email);
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()) return;
            int row = table.getSelectedRow();
            if(row>=0){
                txtExplanation.setText(generateExplanation(
                        lostMatches.get(row),
                        foundMatches.get(row),
                        scores.get(row)
                ));
            }
        });

        panel.add(lblLogo);
        panel.add(title);
        panel.add(tableScroll);
        panel.add(lblExplain);
        panel.add(explainScroll);
        panel.add(btnRefresh);
        panel.add(btnBack);

        add(panel);

        loadMatches();

        setVisible(true);
    }

    private void loadMatches(){

        model.setRowCount(0);
        lostMatches.clear();
        foundMatches.clear();
        scores.clear();

        try{

            ItemDAO dao = new ItemDAO();
            ResultSet rs = dao.getAllItems();

            ArrayList<Item> lost = new ArrayList<>();
            ArrayList<Item> found = new ArrayList<>();

            while(rs.next()){

                Item item = new Item(
                        rs.getString("user_email"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("color"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("status")
                );

                if(item.getStatus().equalsIgnoreCase("LOST"))
                    lost.add(item);
                else if(item.getStatus().equalsIgnoreCase("FOUND"))
                    found.add(item);
            }

            AIMatcher ai = new AIMatcher();

            for(Item l : lost){
                for(Item f : found){

                    int score = ai.calculateScore(l,f);

                    if(score >= 70){

                        String confidence = score>=90 ? "Excellent" : "Possible";

                        model.addRow(new Object[]{
                                l.getItemName(),
                                f.getItemName(),
                                l.getCategory(),
                                score + "%",
                                confidence
                        });

                        lostMatches.add(l);
                        foundMatches.add(f);
                        scores.add(score);
                    }
                }
            }

            if(model.getRowCount()==0){
                txtExplanation.setText("No AI matches found.");
            }

        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Unable to load AI matches.");
        }
    }

    private String generateExplanation(Item lost, Item found, int score){

        StringBuilder sb = new StringBuilder();

        sb.append("AI Analysis\n\n");

        sb.append(lost.getItemName().equalsIgnoreCase(found.getItemName()) ?
                "✔ Same Item Name\n":"✖ Different Item Name\n");

        sb.append(lost.getCategory().equalsIgnoreCase(found.getCategory()) ?
                "✔ Same Category\n":"✖ Different Category\n");

        sb.append(lost.getColor().equalsIgnoreCase(found.getColor()) ?
                "✔ Same Color\n":"✖ Different Color\n");

        sb.append(lost.getLocation().equalsIgnoreCase(found.getLocation()) ?
                "✔ Same Location\n":"✖ Different Location\n");

        sb.append("\nMatch Score: ").append(score).append("%");

        if(score>=90)
            sb.append("\nConfidence: Excellent Match");
        else
            sb.append("\nConfidence: Possible Match");

        sb.append("\n\nRecommendation:\nContact the finder or administrator to verify the item.");

        return sb.toString();
    }
}
