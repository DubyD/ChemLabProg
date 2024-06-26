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
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField reEnterPasswordField;
    private JTextField emailField;
    private JComboBox<String> securityQuestionComboBox;
    private JTextField securityAnswerField;
    private JTextField adminCodeField;

    public CreateAccountDialog(JFrame parent) {
        super(parent, "Create Account", true);
        setSize(400, 450);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setBounds(50, 30, 80, 25);
        this.add(nameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 200, 25);
        this.add(usernameField);

        JLabel loginIdLabel = new JLabel("Password:");
        loginIdLabel.setBounds(50, 70, 80, 25);
        this.add(loginIdLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 200, 25);
        this.add(passwordField);

        JLabel reEnterPasswordLabel = new JLabel("Re-enter Password:");
        reEnterPasswordLabel.setBounds(30, 110, 120, 25);
        this.add(reEnterPasswordLabel);

        reEnterPasswordField = new JPasswordField();
        reEnterPasswordField.setBounds(150, 110, 200, 25);
        this.add(reEnterPasswordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 80, 25);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 25);
        this.add(emailField);

        JLabel securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setBounds(40, 190, 120, 25);
        this.add(securityQuestionLabel);

        String[] options = {"What model is your first car?",
                "What is your mother's maiden name?",
                "The name of your first pet?",
                "Name of your favorite teacher?",
                "What was your high school mascot?",
                "Who is your favorite family member?",
                "What is your all-time favorite song?"
        };
        securityQuestionComboBox = new JComboBox<>(options);
        securityQuestionComboBox.setBounds(150, 190, 200, 25);
        add(securityQuestionComboBox);

        JLabel securityAnswerLabel = new JLabel("Security Answer:");
        securityAnswerLabel.setBounds(50, 230, 100, 25);
        this.add(securityAnswerLabel);

        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(150, 230, 200, 25);
        this.add(securityAnswerField);

        JLabel adminCodeLabel = new JLabel("Admin Code:");
        adminCodeLabel.setBounds(50, 270, 120, 25);
        this.add(adminCodeLabel);

        adminCodeField = new JTextField();
        adminCodeField.setBounds(150, 270, 200, 25);
        this.add(adminCodeField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 310, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String reEnterPassword = new String(reEnterPasswordField.getPassword());
                String email = emailField.getText();
                String securityQuestion = securityQuestionComboBox.getSelectedItem().toString();
                String securityAnswer = securityAnswerField.getText();
                String adminCode = adminCodeField.getText(); // Retrieve admin code
                boolean isAdmin = false;

                if (!password.equals(reEnterPassword)) {
                    JOptionPane.showMessageDialog(CreateAccountDialog.this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = new User();

                // Check if the admin code is correct (assuming a specific code for demonstration)
                // Only one admin user can exist at a time
                if ("admin123".equals(adminCode) && !User.getDatabase().hasAdminUser()) {
                    isAdmin = true;
                }

                if (user.createNewUser(username, password, email, securityQuestion, securityAnswer, isAdmin)) {
                    JOptionPane.showMessageDialog(CreateAccountDialog.this, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CreateAccountDialog.this, "Invalid input for field(s).", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        });
        add(submitButton);

        setVisible(true);
    }
}