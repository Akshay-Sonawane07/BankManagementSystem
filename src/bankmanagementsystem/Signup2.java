package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup2 extends JFrame implements ActionListener {

    JTextField panTextField, aadharTextField;
    JButton next;
    JRadioButton yes1, no1, yes2, no2;
    JComboBox<String> religionBox, incomeBox, educationBox, categoryBox, occupationBox;
    String formno;

    String[] varReligion = {"Hindu", "Sikh", "Muslim", "Christian", "Other"};
    String[] varCategory = {"General", "OBC", "SC", "ST", "NT", "Minority", "Other"};
    String[] varIncome = {"Null", "<1.5 Lakh", "<2.5 Lakh", "<5 Lakh", ">10 Lakh"};
    String[] varEducation = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Diploma", "Other"};
    String[] varOccupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};

    public Signup2(String formno) {
        this.formno = formno;

        UIUtils.initLookAndFeel();
        setTitle("New Account Application - Page 2");
        getContentPane().setLayout(null);
        getContentPane().setBackground(UIUtils.BG);

        // Card
        JPanel card = UIUtils.cardPanel();
        card.setBounds(30, 20, 780, 700);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel("Page 2: Additional Details");
        UIUtils.styleLabel(heading, 20, true);
        heading.setBounds(260, 10, 400, 30);
        card.add(heading);

        // Religion
        JLabel religionLabel = new JLabel("Religion:");
        UIUtils.styleLabel(religionLabel, 16, true);
        religionLabel.setBounds(80, 70, 150, 30);
        card.add(religionLabel);

        religionBox = new JComboBox<>(varReligion);
        religionBox.setBounds(300, 70, 350, 34);
        card.add(religionBox);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        UIUtils.styleLabel(categoryLabel, 16, true);
        categoryLabel.setBounds(80, 120, 150, 30);
        card.add(categoryLabel);

        categoryBox = new JComboBox<>(varCategory);
        categoryBox.setBounds(300, 120, 350, 34);
        card.add(categoryBox);

        // Income
        JLabel incomeLabel = new JLabel("Income:");
        UIUtils.styleLabel(incomeLabel, 16, true);
        incomeLabel.setBounds(80, 170, 150, 30);
        card.add(incomeLabel);

        incomeBox = new JComboBox<>(varIncome);
        incomeBox.setBounds(300, 170, 350, 34);
        card.add(incomeBox);

        // Education
        JLabel educationLabel = new JLabel("Education:");
        UIUtils.styleLabel(educationLabel, 16, true);
        educationLabel.setBounds(80, 220, 150, 30);
        card.add(educationLabel);

        educationBox = new JComboBox<>(varEducation);
        educationBox.setBounds(300, 220, 350, 34);
        card.add(educationBox);

        // Occupation
        JLabel occupationLabel = new JLabel("Occupation:");
        UIUtils.styleLabel(occupationLabel, 16, true);
        occupationLabel.setBounds(80, 270, 150, 30);
        card.add(occupationLabel);

        occupationBox = new JComboBox<>(varOccupation);
        occupationBox.setBounds(300, 270, 350, 34);
        card.add(occupationBox);

        // PAN
        JLabel panLabel = new JLabel("PAN Number:");
        UIUtils.styleLabel(panLabel, 16, true);
        panLabel.setBounds(80, 320, 150, 30);
        card.add(panLabel);

        panTextField = new JTextField();
        panTextField.setBounds(300, 320, 350, 32);
        UIUtils.styleTextField(panTextField);
        card.add(panTextField);

        // Aadhar
        JLabel aadharLabel = new JLabel("Aadhar Number:");
        UIUtils.styleLabel(aadharLabel, 16, true);
        aadharLabel.setBounds(80, 370, 150, 30);
        card.add(aadharLabel);

        aadharTextField = new JTextField();
        aadharTextField.setBounds(300, 370, 350, 32);
        UIUtils.styleTextField(aadharTextField);
        card.add(aadharTextField);

        // Senior citizen
        JLabel seniorLabel = new JLabel("Senior Citizen:");
        UIUtils.styleLabel(seniorLabel, 16, true);
        seniorLabel.setBounds(80, 430, 150, 30);
        card.add(seniorLabel);

        yes1 = new JRadioButton("Yes");
        no1 = new JRadioButton("No");
        yes1.setBackground(UIUtils.CARD);
        no1.setBackground(UIUtils.CARD);
        yes1.setBounds(300, 430, 80, 30);
        no1.setBounds(400, 430, 80, 30);
        card.add(yes1);
        card.add(no1);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(yes1);
        seniorGroup.add(no1);

        // Existing account
        JLabel existingLabel = new JLabel("Existing Account:");
        UIUtils.styleLabel(existingLabel, 16, true);
        existingLabel.setBounds(80, 480, 180, 30);
        card.add(existingLabel);

        yes2 = new JRadioButton("Yes");
        no2 = new JRadioButton("No");
        yes2.setBackground(UIUtils.CARD);
        no2.setBackground(UIUtils.CARD);
        yes2.setBounds(300, 480, 80, 30);
        no2.setBounds(400, 480, 80, 30);
        card.add(yes2);
        card.add(no2);

        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(yes2);
        existingGroup.add(no2);

        // Next button
        next = new JButton("Next");
        next.setBounds(550, 620, 100, 36);
        UIUtils.styleButton(next);
        next.addActionListener(this);
        card.add(next);

        UIUtils.prepareFrame(this, 850, 770);
        setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == next) {

        String religion = (String) religionBox.getSelectedItem();
        String category = (String) categoryBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String education = (String) educationBox.getSelectedItem();
        String occupation = (String) occupationBox.getSelectedItem();

        String pan = panTextField.getText().trim();
        String aadhar = aadharTextField.getText().trim();

        String senior = yes1.isSelected() ? "Yes" :
                        no1.isSelected() ? "No" : null;

        String existing = yes2.isSelected() ? "Yes" :
                          no2.isSelected() ? "No" : null;

        // ---------------- VALIDATION START --------------------

        // PAN Required
        if (pan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PAN Number is required");
            return;
        }

        // PAN Format Validation
        if (!pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
            JOptionPane.showMessageDialog(this,
                    "Invalid PAN Format!\nCorrect Format: ABCDE1234F");
            return;
        }

        // Aadhaar Required
        if (aadhar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aadhaar Number is required");
            return;
        }

        // Aadhaar Format Validation
        if (!aadhar.matches("\\d{12}")) {
            JOptionPane.showMessageDialog(this,
                    "Aadhaar Number must be exactly 12 digits");
            return;
        }

        // Senior Citizen Validation
        if (senior == null) {
            JOptionPane.showMessageDialog(this, "Please select Senior Citizen option");
            return;
        }

        // Existing Account Validation
        if (existing == null) {
            JOptionPane.showMessageDialog(this, "Please select Existing Account option");
            return;
        }

        // --------------- VALIDATION END -------------------

        try {
            Conn c = new Conn();

            // ORDER MUST MATCH TABLE COLUMNS
            String query = "INSERT INTO signup2 VALUES('"
                    + formno + "','" + religion + "','" + category + "','" + income + "','" 
                    + education + "','" + occupation + "','" + pan + "','" + aadhar + "','"
                    + senior + "','" + existing + "')";

            c.st.executeUpdate(query);

            setVisible(false);
            new Signup3(formno).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error saving details: " + ex.getMessage());
        }
    }
}

    public static void main(String[] args) {
        // When testing standalone, pass an example formno (replace with real when used via app)
        new Signup2("1001");
    }
}
