/**
  *@author Salim Jday
  */

package com.example.frontend;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton exitButton;
    private JLabel messageLabel;
    private ImageIcon flask;
    private ImageIcon smallFlask;

    public LoginScreen(JFrame frame) {
        initializeUI(frame);
    }

    private void initializeUI(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30);
        

        //Attempting to add image to login
        this.flask = new ImageIcon("src/resources/images/flask.png");
        this.smallFlask = new ImageIcon("src/resources/images/flaskIcon.png");
        JLabel iconLabel = new JLabel(this.flask);

        //Changing icon of program
        frame.setIconImage(this.smallFlask.getImage());

        add(iconLabel, gbc);

        JLabel headerLabel = new JLabel("Login to Your Account", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(headerLabel, gbc);

        JLabel userLabel = new JLabel("User Name:");
        add(userLabel, gbc);

        userField = new JTextField(15);
        add(userField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel, gbc);

        passField = new JPasswordField(15);
        add(passField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> new CreateAccountDialog(frame));
        buttonPanel.add(createAccountButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        add(buttonPanel, gbc);

        messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(messageLabel, gbc);
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }
    public ImageIcon getIcon(){
        return this.
    }
}
