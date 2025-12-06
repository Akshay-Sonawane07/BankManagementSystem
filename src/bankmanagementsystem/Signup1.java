package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class Signup1 extends JFrame implements ActionListener {

    JTextField nameTextField, fnameTextField, emailTextField, addressTextField,
            cityTextField, stateTextField, pinTextField, mobileTextField;
    JDateChooser dateChooser;
    JRadioButton male, female, married, unmarried, other;
    JButton next;
    long random;

    public Signup1() {

        setLayout(null);
        setTitle("New Account Application Form - Page 1");

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("Application Form No: " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 32));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        // ----------------- Name --------------------
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 200, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        // ---------------- Father Name ----------------
        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        // ------------------ DOB ---------------------
        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Raleway", Font.BOLD, 14));
        dateChooser.setBounds(300, 240, 400, 30);
        add(dateChooser);

        // ------------------ Gender -------------------
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBackground(Color.WHITE);
        male.setFont(new Font("Raleway", Font.BOLD, 14));
        male.setBounds(300, 290, 120, 30);
        add(male);

        female = new JRadioButton("Female");
        female.setBackground(Color.WHITE);
        female.setFont(new Font("Raleway", Font.BOLD, 14));
        female.setBounds(450, 290, 120, 30);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // ------------------ Email -------------------
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        // ------------------ Mobile -------------------
        JLabel mobile = new JLabel("Mobile Number:");
        mobile.setFont(new Font("Raleway", Font.BOLD, 20));
        mobile.setBounds(100, 390, 200, 30);
        add(mobile);

        mobileTextField = new JTextField();
        mobileTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        mobileTextField.setBounds(300, 390, 400, 30);
        add(mobileTextField);

        // --------------- Marital Status ---------------
        JLabel mStatus = new JLabel("Marital Status:");
        mStatus.setFont(new Font("Raleway", Font.BOLD, 20));
        mStatus.setBounds(100, 440, 200, 30);
        add(mStatus);

        married = new JRadioButton("Married");
        married.setBackground(Color.WHITE);
        married.setFont(new Font("Raleway", Font.BOLD, 14));
        married.setBounds(300, 440, 120, 30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBackground(Color.WHITE);
        unmarried.setFont(new Font("Raleway", Font.BOLD, 14));
        unmarried.setBounds(450, 440, 120, 30);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBackground(Color.WHITE);
        other.setFont(new Font("Raleway", Font.BOLD, 14));
        other.setBounds(600, 440, 120, 30);
        add(other);

        ButtonGroup mStatusGroup = new ButtonGroup();
        mStatusGroup.add(married);
        mStatusGroup.add(unmarried);
        mStatusGroup.add(other);

        // ------------------ Address ------------------
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 490, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 490, 400, 30);
        add(addressTextField);

        // ------------------ City ---------------------
        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 540, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 540, 400, 30);
        add(cityTextField);

        // ------------------ State ---------------------
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 590, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextField.setBounds(300, 590, 400, 30);
        add(stateTextField);

        // ------------------ Pin Code ------------------
        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 640, 200, 30);
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        pinTextField.setBounds(300, 640, 400, 30);
        add(pinTextField);

        // ------------------ NEXT Button ----------------
        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620, 700, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String formno = "" + random;
        String name = nameTextField.getText().trim();
        String fname = fnameTextField.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        String email = emailTextField.getText().trim();
        String mobile = mobileTextField.getText().trim();
        String address = addressTextField.getText().trim();
        String city = cityTextField.getText().trim();
        String state = stateTextField.getText().trim();
        String pin = pinTextField.getText().trim();

        String gender = male.isSelected() ? "Male" :
                        female.isSelected() ? "Female" : null;

        String mStatusValue = married.isSelected() ? "Married" :
                              unmarried.isSelected() ? "Unmarried" :
                              other.isSelected() ? "Other" : null;

        // ------------------- VALIDATIONS START ------------------------

        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Name is required");
            return;
        }

        if (fname.equals("")) {
            JOptionPane.showMessageDialog(null, "Father's Name is required");
            return;
        }

        if (dob.equals("")) {
            JOptionPane.showMessageDialog(null, "Date of Birth is required");
            return;
        }

        if (gender == null) {
            JOptionPane.showMessageDialog(null, "Please select your gender");
            return;
        }

        if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Email is required");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Invalid email format");
            return;
        }

        if (mobile.equals("")) {
            JOptionPane.showMessageDialog(null, "Mobile Number is required");
            return;
        }

        if (!mobile.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Mobile Number must be exactly 10 digits");
            return;
        }

        if (mStatusValue == null) {
            JOptionPane.showMessageDialog(null, "Please select your Marital Status");
            return;
        }

        if (address.equals("")) {
            JOptionPane.showMessageDialog(null, "Address is required");
            return;
        }

        if (city.equals("")) {
            JOptionPane.showMessageDialog(null, "City is required");
            return;
        }

        if (state.equals("")) {
            JOptionPane.showMessageDialog(null, "State is required");
            return;
        }

        if (pin.equals("")) {
            JOptionPane.showMessageDialog(null, "Pin Code is required");
            return;
        }

        if (!pin.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "Pin Code must be 6 digits");
            return;
        }

        // ------------------- VALIDATIONS END ------------------------

        try {
            Conn c = new Conn();

            String query = "INSERT INTO signup(formno, name, fname, dob, gender, email, marital, address, city, state, pin, mobile) "
                    + "VALUES('" + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','"
                    + mStatusValue + "','" + address + "','" + city + "','" + state + "','" + pin + "','" + mobile + "')";

            c.st.executeUpdate(query);

            setVisible(false);
            new Signup2(formno).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup1();
    }
}
