package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.File;

public class MiniStatement extends JFrame {

    String pinnumber;
    JTextArea area;
    JButton printBtn, exportBtn, closeBtn;

    public MiniStatement(String pinnumber) {
    this.pinnumber = pinnumber;

    setTitle("Mini Statement");
    setLayout(null);

    JLabel bank = new JLabel("SBI Bank");
    bank.setBounds(150, 20, 200, 30);
    bank.setFont(new Font("Arial", Font.BOLD, 24));
    add(bank);

    JLabel cardLabel = new JLabel();
    cardLabel.setBounds(20, 80, 300, 20);
    add(cardLabel);

    JTextArea area = new JTextArea();
    area.setEditable(false);
    area.setBounds(20, 120, 340, 350);
    add(area);

    try {
        Conn conn = new Conn();

        // Get Card Number
        ResultSet rsCard = conn.st.executeQuery(
                "SELECT cardnumber FROM login WHERE pin='" + pinnumber + "'"
        );

        if (rsCard.next()) {
            String cardNum = rsCard.getString("cardnumber");
            String masked = "Card: XXXX-XXXX-XXXX-" + cardNum.substring(cardNum.length() - 4);
            cardLabel.setText(masked);
        }

        // Get last transactions
        ResultSet rs = conn.st.executeQuery(
                "SELECT * FROM bank WHERE pin='" + pinnumber + "' ORDER BY date DESC LIMIT 10"
        );

        while (rs.next()) {
            area.append(
                rs.getString("date") + "   " +
                rs.getString("type") + "   ₹" +
                rs.getString("amount") + "\n"
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    setSize(400, 550);
    setLocation(500, 120);
    setVisible(true);
}

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
