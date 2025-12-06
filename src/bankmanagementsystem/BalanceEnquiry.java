package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton backBtn;
    String pinnumber;

    public BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("Balance Enquiry");
        getContentPane().setBackground(UIUtils.BG);
        getContentPane().setLayout(null);

        // Main panel
        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 520, 350);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel("Your Account Balance");
        UIUtils.styleLabel(heading, 22, true);
        heading.setBounds(140, 30, 350, 30);
        card.add(heading);

        int balance = calculateBalance();
        
        JLabel balanceLabel = new JLabel("₹ " + balance);
        UIUtils.styleLabel(balanceLabel, 30, true);
        balanceLabel.setForeground(new Color(0, 120, 0)); // Green
        balanceLabel.setBounds(180, 120, 350, 40);
        card.add(balanceLabel);

        backBtn = new JButton("Back");
        backBtn.setBounds(200, 220, 120, 36);
        UIUtils.styleButton(backBtn);
        backBtn.addActionListener(this);
        card.add(backBtn);

        UIUtils.prepareFrame(this, 600, 450);
        setVisible(true);
    }

    private int calculateBalance() {
        int balance = 0;

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bank WHERE pin='" + pinnumber + "'";
            ResultSet rs = conn.st.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return balance;
    }

   @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backBtn) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
        return;
    }

    try {
        Conn conn = new Conn();

        String query = "SELECT type, amount FROM bank WHERE pin='" + pinnumber + "'";
        ResultSet rs = conn.st.executeQuery(query);

        int balance = 0;
        while (rs.next()) {
            int amt = Integer.parseInt(rs.getString("amount"));
            if (rs.getString("type").equals("Deposit")) balance += amt;
            else balance -= amt;
        }

        JOptionPane.showMessageDialog(this,
                "Your Current Balance is:\n\n₹" + balance);

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}


    public static void main(String[] args) {
        new BalanceEnquiry("1234");
    }
}
