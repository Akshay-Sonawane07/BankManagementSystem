package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField amountField;
    JButton withdrawBtn, backBtn;
    String pinnumber;

    public Withdrawal(String pinnumber) {
        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("Withdraw Cash");
        getContentPane().setBackground(UIUtils.BG);
        getContentPane().setLayout(null);

        // Card Panel
        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 520, 380);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel("Enter Amount to Withdraw");
        UIUtils.styleLabel(heading, 20, true);
        heading.setBounds(120, 30, 350, 30);
        card.add(heading);

        // Amount Label
        JLabel amountLabel = new JLabel("Amount:");
        UIUtils.styleLabel(amountLabel, 16, true);
        amountLabel.setBounds(80, 110, 150, 30);
        card.add(amountLabel);

        // Amount Field
        amountField = new JTextField();
        amountField.setBounds(180, 110, 250, 34);
        UIUtils.styleTextField(amountField);
        card.add(amountField);

        // Withdraw Button
        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(180, 180, 120, 36);
        UIUtils.styleButton(withdrawBtn);
        withdrawBtn.addActionListener(this);
        card.add(withdrawBtn);

        // Back Button
        backBtn = new JButton("Back");
        backBtn.setBounds(320, 180, 120, 36);
        UIUtils.styleOutlineButton(backBtn);
        backBtn.addActionListener(this);
        card.add(backBtn);

        UIUtils.prepareFrame(this, 600, 460);
        setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {

    // Back Button
    if (e.getSource() == backBtn) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
        return;
    }

    // Withdraw Button
    if (e.getSource() == withdrawBtn) {

        String amount = amountField.getText().trim();

        // ------------------ VALIDATIONS --------------------

        if (amount.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.");
            return;
        }

        // Check numeric input
        if (!amount.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Amount must contain numbers only.");
            return;
        }

        int amt = Integer.parseInt(amount);

        // Check negative or zero
        if (amt <= 0) {
            JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
            return;
        }

        // Check minimum
        if (amt < 100) {
            JOptionPane.showMessageDialog(this, "Minimum withdrawal amount is ₹100.");
            return;
        }

        // Check multiples of 100
        if (amt % 100 != 0) {
            JOptionPane.showMessageDialog(this, "Amount must be a multiple of ₹100 (100, 200, 500, 1000...).");
            return;
        }

        // ------------------ CHECK CURRENT BALANCE --------------------
        try {
            Conn conn = new Conn();

            // Calculate Balance
            String balQuery = "SELECT type, amount FROM bank WHERE pin='" + pinnumber + "'";
            ResultSet rs = conn.st.executeQuery(balQuery);

            int balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                int amtDb = Integer.parseInt(rs.getString("amount"));

                if (type.equals("Deposit")) balance += amtDb;
                else if (type.equals("Withdraw")) balance -= amtDb;
            }

            // Check if enough balance
            if (amt > balance) {
                JOptionPane.showMessageDialog(this,
                        "Insufficient Balance!\nYour available balance is: ₹" + balance);
                return;
            }

            // ------------------ CONFIRMATION --------------------
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Withdraw ₹" + amt + " ?",
                    "Confirm Withdrawal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            // ------------------ PERFORM WITHDRAWAL --------------------
            Date date = new Date();

            String query = "INSERT INTO bank VALUES('" + pinnumber + "','" + date + "','Withdraw','" + amt + "')";
            conn.st.executeUpdate(query);

            JOptionPane.showMessageDialog(this,
                    "₹" + amt + " Withdrawn Successfully!");

            setVisible(false);
            new Transaction(pinnumber).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}


    public static void main(String[] args) {
        new Withdrawal("1234");
    }
}
