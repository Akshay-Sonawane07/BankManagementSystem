package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField oldPinField, newPinField, confirmPinField;
    JButton changeBtn, backBtn;
    String pinnumber;

    public PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("Change PIN");
        getContentPane().setBackground(UIUtils.BG);
        getContentPane().setLayout(null);

        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 520, 430);
        getContentPane().add(card);

        JLabel heading = new JLabel("Change Your ATM PIN");
        UIUtils.styleLabel(heading, 22, true);
        heading.setBounds(150, 30, 350, 40);
        card.add(heading);

        JLabel oldLbl = new JLabel("Old PIN:");
        UIUtils.styleLabel(oldLbl, 16, true);
        oldLbl.setBounds(80, 110, 200, 30);
        card.add(oldLbl);

        oldPinField = new JPasswordField();
        oldPinField.setBounds(220, 110, 200, 34);
        UIUtils.stylePasswordField(oldPinField);
        card.add(oldPinField);

        JLabel newLbl = new JLabel("New PIN:");
        UIUtils.styleLabel(newLbl, 16, true);
        newLbl.setBounds(80, 170, 200, 30);
        card.add(newLbl);

        newPinField = new JPasswordField();
        newPinField.setBounds(220, 170, 200, 34);
        UIUtils.stylePasswordField(newPinField);
        card.add(newPinField);

        JLabel confirmLbl = new JLabel("Confirm PIN:");
        UIUtils.styleLabel(confirmLbl, 16, true);
        confirmLbl.setBounds(80, 230, 200, 30);
        card.add(confirmLbl);

        confirmPinField = new JPasswordField();
        confirmPinField.setBounds(220, 230, 200, 34);
        UIUtils.stylePasswordField(confirmPinField);
        card.add(confirmPinField);

        changeBtn = new JButton("Change PIN");
        changeBtn.setBounds(160, 300, 150, 36);
        UIUtils.styleButton(changeBtn);
        changeBtn.addActionListener(this);
        card.add(changeBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(320, 300, 100, 36);
        UIUtils.styleOutlineButton(backBtn);
        backBtn.addActionListener(this);
        card.add(backBtn);

        UIUtils.prepareFrame(this, 600, 520);
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == backBtn) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
        return;
    }

    if (e.getSource() == changeBtn) {

        String oldPin = oldPinField.getText().trim();
        String newPin = newPinField.getText().trim();
        String confirmPin = confirmPinField.getText().trim();

        // ---------- VALIDATIONS ------------
        if (oldPin.equals("") || newPin.equals("") || confirmPin.equals("")) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!oldPin.equals(pinnumber)) {
            JOptionPane.showMessageDialog(this, "Old PIN is incorrect!");
            return;
        }

        if (!newPin.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "New PIN must be exactly 4 digits!");
            return;
        }

        if (!newPin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(this, "New PIN and Confirm PIN do not match!");
            return;
        }

        if (newPin.equals(oldPin)) {
            JOptionPane.showMessageDialog(this, "New PIN must be different from Old PIN");
            return;
        }

        try {
            Conn conn = new Conn();

            // Update in login table
            conn.st.executeUpdate(
                "UPDATE login SET pin='" + newPin + "' WHERE pin='" + oldPin + "'"
            );

            // Update in signup3 table
            conn.st.executeUpdate(
                "UPDATE signup3 SET pinnumber='" + newPin + "' WHERE pinnumber='" + oldPin + "'"
            );

            JOptionPane.showMessageDialog(this, "PIN Changed Successfully!");
            setVisible(false);
            new Transaction(newPin).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}


    public static void main(String[] args) {
        new PinChange("1234");
    }
}
