package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;


public class Transaction extends JFrame implements ActionListener {

    JButton deposit, withdraw, fastCash, miniStatement, pinChange, balanceEnquiry, exit;
    String pinnumber;

    public Transaction(String pinnumber) {
        this.pinnumber = pinnumber;

        UIUtils.initLookAndFeel();
        setTitle("ATM - Dashboard");
        getContentPane().setLayout(null);
        getContentPane().setBackground(UIUtils.BG);

        JPanel left = UIUtils.cardPanel();
        left.setBounds(30, 30, 420, 520);
        getContentPane().add(left);

        JLabel abc = new JLabel("Welcome");
        UIUtils.styleLabel(abc, 24, true);
        abc.setBounds(140, 20, 200, 30);
        left.add(abc);

        // dashboard buttons as tiles
        deposit = tileButton("Deposit");
        withdraw = tileButton("Withdraw");
        fastCash = tileButton("Fast Cash");
        miniStatement = tileButton("Mini Statement");
        pinChange = tileButton("Pin Change");
        balanceEnquiry = tileButton("Balance Enquiry");
        exit = tileButton("Logout");

        JButton[] btns = {deposit, withdraw, fastCash, miniStatement, pinChange, balanceEnquiry, exit};
        int x = 40, y = 80;
        for (JButton b : btns) {
            b.setBounds(x, y, 340, 48);
            left.add(b);
            b.addActionListener(this);
            y += 60;
        }

        UIUtils.prepareFrame(this, 500, 620);
        setVisible(true);
    }

    private JButton tileButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font(UIUtils.UI_FONT_FAMILY, Font.BOLD, 16));
        b.setForeground(Color.WHITE);
        b.setBackground(UIUtils.PRIMARY);
        b.setBorder(new EmptyBorder(8, 12, 8, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(UIUtils.PRIMARY_DARK); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(UIUtils.PRIMARY); }
        });
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        String t = src.getText();

        switch (t) {
            case "Deposit": setVisible(false); new Deposit(pinnumber).setVisible(true); break;
            case "Withdraw": setVisible(false); new Withdrawal(pinnumber).setVisible(true); break;
            case "Fast Cash": setVisible(false); new FastCash(pinnumber).setVisible(true); break;
            case "Mini Statement": new MiniStatement(pinnumber).setVisible(true); break;
            case "Pin Change": setVisible(false); new PinChange(pinnumber).setVisible(true); break;
            case "Balance Enquiry": setVisible(false); new BalanceEnquiry(pinnumber).setVisible(true); break;
            case "Logout": setVisible(false); new Login().setVisible(true); break;
        }
    }

    public static void main(String[] args) { new Transaction("1234"); }
}
