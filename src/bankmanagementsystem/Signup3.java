package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton saving, current, fDeposit, rDeposit;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    public Signup3(String formno) {

        this.formno = formno;

        UIUtils.initLookAndFeel();
        setTitle("Account Details");
        getContentPane().setBackground(UIUtils.BG);
        setLayout(null);

        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 30, 850, 520);
        card.setLayout(null);
        add(card);

        JLabel l1 = new JLabel("Page 3: Account Details");
        UIUtils.styleLabel(l1, 26, true);
        l1.setBounds(250, 20, 500, 40);
        card.add(l1);

        // Account Type
        JLabel type = new JLabel("Account Type:");
        UIUtils.styleLabel(type, 18, true);
        type.setBounds(80, 90, 200, 30);
        card.add(type);

        saving = new JRadioButton("Saving Account");
        UIUtils.styleRadio(saving);
        saving.setBounds(80, 125, 200, 25);
        card.add(saving);

        current = new JRadioButton("Current Account");
        UIUtils.styleRadio(current);
        current.setBounds(300, 125, 200, 25);
        card.add(current);

        fDeposit = new JRadioButton("Fixed Deposit Account");
        UIUtils.styleRadio(fDeposit);
        fDeposit.setBounds(80, 155, 250, 25);
        card.add(fDeposit);

        rDeposit = new JRadioButton("Recurring Deposit Account");
        UIUtils.styleRadio(rDeposit);
        rDeposit.setBounds(300, 155, 250, 25);
        card.add(rDeposit);

        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(saving);
        accGroup.add(current);
        accGroup.add(fDeposit);
        accGroup.add(rDeposit);

        // Services
        JLabel service = new JLabel("Services Required:");
        UIUtils.styleLabel(service, 18, true);
        service.setBounds(80, 210, 300, 30);
        card.add(service);

        c1 = new JCheckBox("ATM Card");
        UIUtils.styleCheckBox(c1);
        c1.setBounds(80, 245, 200, 25);
        card.add(c1);

        c2 = new JCheckBox("Internet Banking");
        UIUtils.styleCheckBox(c2);
        c2.setBounds(300, 245, 200, 25);
        card.add(c2);

        c3 = new JCheckBox("Mobile Banking");
        UIUtils.styleCheckBox(c3);
        c3.setBounds(80, 275, 200, 25);
        card.add(c3);

        c4 = new JCheckBox("E-Statement");
        UIUtils.styleCheckBox(c4);
        c4.setBounds(300, 275, 200, 25);
        card.add(c4);

        c5 = new JCheckBox("SMS Alerts");
        UIUtils.styleCheckBox(c5);
        c5.setBounds(80, 305, 200, 25);
        card.add(c5);

        c6 = new JCheckBox("Cheque Book");
        UIUtils.styleCheckBox(c6);
        c6.setBounds(300, 305, 200, 25);
        card.add(c6);

        c7 = new JCheckBox("I confirm that all details provided are correct.");
        UIUtils.styleCheckBox(c7);
        c7.setBounds(80, 350, 450, 25);
        card.add(c7);

        // Buttons
        submit = new JButton("Submit");
        UIUtils.styleButton(submit);
        submit.setBounds(450, 430, 120, 38);
        submit.addActionListener(this);
        card.add(submit);

        cancel = new JButton("Cancel");
        UIUtils.styleOutlineButton(cancel);
        cancel.setBounds(600, 430, 120, 38);
        cancel.addActionListener(this);
        card.add(cancel);

        UIUtils.prepareFrame(this, 920, 600);
        setVisible(true);
    }

   @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == cancel) {
        setVisible(false);
        new Login().setVisible(true);
        return;
    }

    if (e.getSource() == submit) {

        // ---------------- DECLARATION CHECK ----------------
        if (!c7.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please accept the declaration!");
            return;
        }

        // ---------------- ACCOUNT TYPE VALIDATION ----------------
        String accountType = null;

        if (saving.isSelected()) accountType = "Saving Account";
        else if (current.isSelected()) accountType = "Current Account";
        else if (fDeposit.isSelected()) accountType = "Fixed Deposit Account";
        else if (rDeposit.isSelected()) accountType = "Recurring Deposit Account";

        if (accountType == null) {
            JOptionPane.showMessageDialog(this, "Please select an Account Type!");
            return;
        }

        // ---------------- SERVICE VALIDATION ----------------
        String services = "";

        if (c1.isSelected()) services += "ATM Card, ";
        if (c2.isSelected()) services += "Internet Banking, ";
        if (c3.isSelected()) services += "Mobile Banking, ";
        if (c4.isSelected()) services += "E-Statement, ";
        if (c5.isSelected()) services += "SMS Alerts, ";
        if (c6.isSelected()) services += "Cheque Book, ";

        // Remove last comma + space
        if (services.endsWith(", ")) {
            services = services.substring(0, services.length() - 2);
        }

        if (services.equals("")) {
            JOptionPane.showMessageDialog(this, "Please select at least ONE service!");
            return;
        }

        // ---------------- GENERATE CARD + PIN ----------------
        Random rand = new Random();
       String cardnumber = "504093" + String.format("%010d", Math.abs(rand.nextLong() % 1_000_000_0000L));

        String pinnumber = "" + Math.abs((rand.nextLong() % 9000L) + 1000L);

        // ---------------- DATABASE INSERTION ----------------
        try {
            Conn c3 = new Conn();

            String q1 = "INSERT INTO signup3(formno, accounttype, cardnumber, pinnumber, services) "
                    + "VALUES('" + formno + "','" + accountType + "','" + cardnumber + "','" + pinnumber + "','" + services + "')";

            String q2 = "INSERT INTO login(formno, cardnumber, pin) "
                    + "VALUES('" + formno + "','" + cardnumber + "','" + pinnumber + "')";

            c3.st.executeUpdate(q1);
            c3.st.executeUpdate(q2);

            JOptionPane.showMessageDialog(null,
                    "Account Created Successfully!\n\nYour Card Number: " + cardnumber +
                            "\nYour PIN: " + pinnumber);

            setVisible(false);
            new Deposit(pinnumber).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}


    public static void main(String[] args) {
        new Signup3("1001");
    }
}
