package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JButton depositBtn, backBtn;
    String pinnumber;

    public Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("Deposit Money");
        getContentPane().setLayout(null);
        getContentPane().setBackground(UIUtils.BG);

        // Main panel
        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 520, 380);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel("Enter Amount to Deposit");
        UIUtils.styleLabel(heading, 20, true);
        heading.setBounds(120, 30, 400, 30);
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

        // Deposit Button
        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(180, 180, 120, 36);
        UIUtils.styleButton(depositBtn);
        depositBtn.addActionListener(this);
        card.add(depositBtn);

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

    if (e.getSource() == backBtn) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
        return;
    }

    if (e.getSource() == depositBtn) {

        String amount = amountField.getText().trim();

        // ---------------- VALIDATIONS ----------------

        // 1. Empty check
        if (amount.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an amount to deposit.");
            return;
        }

        // 2. Numeric check
        if (!amount.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Amount must contain numbers only!");
            return;
        }

        // 3. Convert to integer
        int amt = Integer.parseInt(amount);

        // 4. Minimum deposit validation
        if (amt < 100) {
            JOptionPane.showMessageDialog(this, "Minimum deposit amount is ₹100.");
            return;
        }

        // 5. Maximum deposit validation (optional)
        if (amt > 200000) {
            JOptionPane.showMessageDialog(this, "Maximum allowed deposit is ₹2,00,000.");
            return;
        }

        // ---------------- DATABASE OPERATION ----------------
        try {
            Conn conn = new Conn();
            Date date = new Date();

            String query = "INSERT INTO bank VALUES('" + pinnumber + "','" + date + "','Deposit','" + amt + "')";
            conn.st.executeUpdate(query);

            JOptionPane.showMessageDialog(this,
                    "₹" + amt + " Deposited Successfully!");

            setVisible(false);
            new Transaction(pinnumber).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
}


    public static void main(String[] args) {
        new Deposit("");
    }
}
