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
    private JTextField emailField;
    private JComboBox<String> securityQuestionComboBox;
    private JTextField securityAnswerField;
    private JTextField adminCodeField;

    public CreateAccountDialog(JFrame parent) {
        super(parent, "Create Account", true);
        setSize(400, 400);
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

        JLabel passwordLabel = new JLabel("Email:");
        passwordLabel.setBounds(50, 110, 80, 25);
        this.add(passwordLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 110, 200, 25);
        this.add(emailField);

        JLabel securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setBounds(50, 150, 80, 25);
        this.add(securityQuestionLabel);


        //Options for Security questions
        String[] options ={"What model is your first car?",
                "What is your mother's maiden name?",
                "The name of your first pet?",
                "Name of your favorite teacher?",
                "What was your highschool mascot?",
                "Who is your favorite family member?",
                "What is your all-time favorite song?"
        };
        securityQuestionComboBox = new JComboBox<>(options);
        securityQuestionComboBox.setBounds(150, 150, 200, 25);
        this.add(securityQuestionComboBox);

        JLabel securityAnswerLabel = new JLabel("Security Answer:");
        securityAnswerLabel.setBounds(50, 190, 100, 25);
        this.add(securityAnswerLabel);

        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(150, 190, 200, 25);
        this.add(securityAnswerField);

        JLabel adminCodeLabel = new JLabel("Admin Code:"); // Label for admin code
        adminCodeLabel.setBounds(50, 230, 120, 25); // Adjusted position
        this.add(adminCodeLabel);

        adminCodeField = new JTextField(); // Text field for admin code
        adminCodeField.setBounds(150, 230, 200, 25); // Adjusted position
        this.add(adminCodeField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 280, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String securityQuestion = securityQuestionComboBox.getSelectedItem().toString();
                String securityAnswer = securityAnswerField.getText();
                String adminCode = adminCodeField.getText(); // Retrieve admin code
                boolean isAdmin = false;

                // Check if the admin code is correct (assuming a specific code for demonstration)
                if ("admin123".equals(adminCode)) {
                    isAdmin = true;
                }

                User user = new User();
                if (user.createNewUser(username, password, email, securityQuestion, securityAnswer, isAdmin)) {
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