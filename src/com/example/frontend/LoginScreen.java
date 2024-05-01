package com.example.frontend;

import javax.swing.*;

public class LoginScreen extends JPanel{
    private JTextField userField;
    private JTextField passField;
    private JButton loginButton;
    private JFrame window;
    private int windowHeight;
    private int windowWidth;

    public LoginScreen(JFrame frame){
        this.window = frame;
        this.userField = new JTextField("UserName");
        this.passField = new JTextField("Password");
        this.loginButton = new JButton("Login");
        this.windowHeight = frame.getHeight();
        this.windowWidth = frame.getWidth();
        this.loginButton.setBounds((windowWidth/2)-200, 3*windowHeight/4, 150, 30);

    }

    public void loginArea(){
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(event -> {
            System.exit(0);
        });
        exitButton.setBounds(50+(windowWidth/2), 3*windowHeight/4, 100, 30);

        userField.setBounds(100,100,200,50);
        this.add(userField);

        passField.setBounds(100,175,200,50);
        this.add(passField);

        this.setLayout(null);

        this.add(loginButton);
        this.add(exitButton);

        this.setVisible(true);
        this.window.setVisible(true);

        this.window.repaint();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}


/*

//Some other version of code, mostly for testing. By SJ

package com.example.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JPanel {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel messageLabel; // For displaying login status messages

    public LoginScreen() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30);

        JLabel headerLabel = new JLabel("Login to Your Account", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(headerLabel, gbc);

        JLabel userLabel = new JLabel("User Name:");
        add(userLabel, gbc);

        userField = new JTextField(15);
        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });
        userField.setBorder(BorderFactory.createCompoundBorder(
                userField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(userField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel, gbc);

        passField = new JPasswordField(15);
        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkFields();
            }
        });
        passField.setBorder(BorderFactory.createCompoundBorder(
                passField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(passField, gbc);

        loginButton = new JButton("Login");
        loginButton.setEnabled(false); // Initially disabled
        add(loginButton, gbc);

        messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(messageLabel, gbc);
    }

    private void checkFields() {
        // Enable the login button only if both fields have some text
        loginButton.setEnabled(userField.getText().length() > 0 && passField.getPassword().length > 0);
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

    public JLabel getMessageLabel() {
        return messageLabel;
    }
}
*/
