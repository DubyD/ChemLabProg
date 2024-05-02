/*
ways to create account in the program later will add ways to save the acouunt created and
its data
@author Jin Kim
 */
package com.example.frontend;

import javax.swing.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountDialog extends JDialog {
    private JTextField nameField;
    private JTextField loginIdField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField contactNumberField;

    public CreateAccountDialog(JFrame parent) {
        super(parent, "Create Account", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        add(nameField);

        JLabel loginIdLabel = new JLabel("Login ID:");
        loginIdLabel.setBounds(50, 70, 80, 25);
        add(loginIdLabel);

        loginIdField = new JTextField();
        loginIdField.setBounds(150, 70, 200, 25);
        add(loginIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 25);
        add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 25);
        add(emailField);

        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setBounds(50, 190, 100, 25);
        add(contactNumberLabel);

        contactNumberField = new JTextField();
        contactNumberField.setBounds(150, 190, 200, 25);
        add(contactNumberField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 230, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here you can save the account information
                System.out.println("Name: " + nameField.getText());
                System.out.println("Login ID: " + loginIdField.getText());
                // Get password securely
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                System.out.println("Password: " + password);
                System.out.println("Email: " + emailField.getText());
                System.out.println("Contact Number: " + contactNumberField.getText());

                dispose();
            }
        });
        add(submitButton);

        setVisible(true);
    }
}