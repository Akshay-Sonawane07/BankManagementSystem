package Bankmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;

    // Constructor
    public Login() {

        // Modern Look & Feel + Background
        UIUtils.initLookAndFeel();
        setTitle("ATM Login");
        getContentPane().setLayout(null);
        getContentPane().setBackground(UIUtils.BG);

        // MAIN CARD PANEL
        JPanel card = UIUtils.cardPanel();
        card.setBounds(80, 40, 620, 360);
        getContentPane().add(card);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(260, 10, 90, 90);
        card.add(label);

        // Heading
        JLabel text = new JLabel("Welcome To ATM");
        UIUtils.styleLabel(text, 26, true);
        text.setBounds(210, 105, 300, 30);
        card.add(text);

        // Card Number Label
        JLabel cardno = new JLabel("Card Number:");
        UIUtils.styleLabel(cardno, 18, true);
        cardno.setBounds(80, 150, 200, 30);
        card.add(cardno);

        // Card Number Field
        cardTextField = new JTextField();
        cardTextField.setBounds(260, 150, 250, 34);
        UIUtils.styleTextField(cardTextField);
        card.add(cardTextField);

        // PIN Label
        JLabel pin = new JLabel("PIN:");
        UIUtils.styleLabel(pin, 18, true);
        pin.setBounds(80, 210, 200, 30);
        card.add(pin);

        // PIN Field
        pinTextField = new JPasswordField();
        pinTextField.setBounds(260, 210, 250, 34);
        UIUtils.stylePasswordField(pinTextField);
        card.add(pinTextField);

        // Buttons
        login = new JButton("Login");
        login.setBounds(260, 265, 110, 36);
        UIUtils.styleButton(login);
        login.addActionListener(this);
        card.add(login);

        clear = new JButton("Clear");
        clear.setBounds(400, 265, 110, 36);
        UIUtils.styleOutlineButton(clear);
        clear.addActionListener(this);
        card.add(clear);

        signup = new JButton("Sign Up");
        signup.setBounds(260, 310, 250, 36);
        UIUtils.styleOutlineButton(signup);
        signup.addActionListener(this);
        card.add(signup);

        // Frame Setup
        UIUtils.prepareFrame(this, 800, 480);
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent e) {

    // ---------------- CLEAR BUTTON ----------------
    if (e.getSource() == clear) {
        cardTextField.setText("");
        pinTextField.setText("");
        return;
    }

    // ---------------- SIGN UP BUTTON ----------------
    if (e.getSource() == signup) {
        setVisible(false);
        new Signup1().setVisible(true);
        return;
    }

    // ---------------- LOGIN BUTTON ----------------
    if (e.getSource() == login) {

        String cardnumber = cardTextField.getText().trim();
        String pinnumber = pinTextField.getText().trim();

        // ----------- VALIDATIONS BEGIN ------------------

        if (cardnumber.equals("")) {
            JOptionPane.showMessageDialog(this, "Card Number is required!");
            return;
        }

        if (!cardnumber.matches("\\d{16}")) {
            JOptionPane.showMessageDialog(this, "Card Number must be exactly 16 digits!");
            return;
        }

        if (pinnumber.equals("")) {
            JOptionPane.showMessageDialog(this, "PIN is required!");
            return;
        }

        if (!pinnumber.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits!");
            return;
        }

        // ----------- VALIDATIONS END --------------------

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM login WHERE cardnumber='" + cardnumber + "' AND pin='" + pinnumber + "'";
            ResultSet rs = conn.st.executeQuery(query);

            if (rs.next()) {
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Card Number or PIN!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

    public static void main(String args[]) {
        SplashScreen.showThenStart(1500); // 1.5 sec

    }
}
