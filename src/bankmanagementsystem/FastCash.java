package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b100, b200, b500, b1000, b2000, b5000, b10000, back;
    String pinnumber;

    public FastCash(String pinnumber) {

        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("Fast Cash");
        getContentPane().setBackground(UIUtils.BG);
        getContentPane().setLayout(null);

        // Main panel
        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 520, 520);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel("Select Amount to Withdraw");
        UIUtils.styleLabel(heading, 20, true);
        heading.setBounds(120, 30, 350, 30);
        card.add(heading);

        // Buttons
        b100 = new JButton("₹ 100");
        b200 = new JButton("₹ 200");
        b500 = new JButton("₹ 500");
        b1000 = new JButton("₹ 1000");
        b2000 = new JButton("₹ 2000");
        b5000 = new JButton("₹ 5000");
        b10000 = new JButton("₹ 10000");
        back = new JButton("Back");

        JButton[] btns = {b100, b200, b500, b1000, b2000, b5000, b10000};

        int y = 90;
        for (JButton b : btns) {
            UIUtils.styleButton(b);
            b.setBounds(150, y, 220, 40);
            card.add(b);
            b.addActionListener(this);
            y += 55;
        }

        // Back button
        UIUtils.styleOutlineButton(back);
        back.setBounds(150, 470, 220, 40);
        card.add(back);
        back.addActionListener(this);

        UIUtils.prepareFrame(this, 600, 620);
        setVisible(true);
    }

    @Override
 
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == back) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
        return;
    }

    String amount = ((JButton) e.getSource()).getText().substring(1); // remove ₹ sign
    int withdrawAmount = Integer.parseInt(amount);

    try {
        Conn conn = new Conn();

        // ------ CHECK BALANCE ------
        String query = "SELECT type, amount FROM bank WHERE pin='" + pinnumber + "'";
        ResultSet rs = conn.st.executeQuery(query);

        int balance = 0;
        while (rs.next()) {
            String type = rs.getString("type");
            int amt = Integer.parseInt(rs.getString("amount"));

            if (type.equals("Deposit")) balance += amt;
            else balance -= amt;
        }

        if (withdrawAmount > balance) {
            JOptionPane.showMessageDialog(this,
                    "Insufficient Balance!\nYour balance: ₹" + balance);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Withdraw ₹" + withdrawAmount + "?",
                "Confirm Fast Cash",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        // ------ WITHDRAW ------
        Date date = new Date();
        String q = "INSERT INTO bank VALUES('" + pinnumber + "','" + date +
                   "','Withdraw','" + withdrawAmount + "')";

        conn.st.executeUpdate(q);

        JOptionPane.showMessageDialog(this,
                "₹" + withdrawAmount + " withdrawn successfully!");

        setVisible(false);
        new Transaction(pinnumber).setVisible(true);

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}

    public static void main(String[] args) {
        new FastCash("");
    }
}
