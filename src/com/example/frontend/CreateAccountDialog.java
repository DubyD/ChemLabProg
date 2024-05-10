/*
ways to create account in the program later will add ways to save the acouunt created and
its data
@author Jin Kim
 */
package com.example.frontend;

import javax.swing.*;

import com.example.backend.User;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountDialog extends JDialog {
    private JTextField nameField;
    private JTextField loginIdField;
    private JPasswordField passwordField;
    private JComboBox<String> securityQuestion;
    private JTextField contactNumberField;

    public CreateAccountDialog(JFrame parent) {
        super(parent, "Create Account", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setBounds(50, 30, 80, 25);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        this.add(nameField);

        JLabel loginIdLabel = new JLabel("Password:");
        loginIdLabel.setBounds(50, 70, 80, 25);
        this.add(loginIdLabel);

        loginIdField = new JTextField();
        loginIdField.setBounds(150, 70, 200, 25);
        this.add(loginIdField);

        JLabel passwordLabel = new JLabel("Email:");
        passwordLabel.setBounds(50, 110, 80, 25);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 25);
        this.add(passwordField);

        JLabel securityQuestion = new JLabel("Security Question:");
        securityQuestion.setBounds(50, 150, 80, 25);
        this.add(securityQuestion);


        //Options for Security questions
        String[] options ={"What model is your first car?",
                "What is your mother's maiden name?",
                "The name of your first pet?",
                "Name of your favorite teacher?",
                "What was your highschool mascot?",
                "Who is your favorite family member?",
                "What is your all-time favorite song?"
        };
        securityQuestion = new JComboBox<>(options);
        securityQuestion.setBounds(150, 150, 200, 25);
        this.add(securityQuestion);

        JLabel contactNumberLabel = new JLabel("Security Answer:");
        contactNumberLabel.setBounds(50, 190, 100, 25);
        this.add(contactNumberLabel);

        contactNumberField = new JTextField();
        contactNumberField.setBounds(150, 190, 200, 25);
        this.add(contactNumberField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 230, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here you can save the account information
                // System.out.println("Name: " + nameField.getText());
                // System.out.println("Login ID: " + loginIdField.getText());
                // Get password securely
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                // System.out.println("Password: " + password);
                // System.out.println("Email: " + emailField.getText());
                // System.out.println("Contact Number: " + contactNumberField.getText());

                // User tester (please remove later)
                // Variable names are incorrect
                // They should be username, password, email, securityQ, securityA
                User user = new User();
                if (user.createNewUser(nameField.getText(), loginIdField.getText(), password, emailField.getText(), contactNumberField.getText(), false)) {
                    JOptionPane.showMessageDialog(CreateAccountDialog.this, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(CreateAccountDialog.this, "Invalid input for field(s).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(submitButton);

        setVisible(true);
    }
}