/*
change the login screen to make sure t ask for username and passward, create a
popup for asking create account.
@author Jin Kim
 */
/*
package com.example.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel{
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton exitButton;
    private JFrame window;
    private int windowHeight;
    private int windowWidth;

    public LoginScreen(JFrame frame){
        this.window = frame;
        this.userField = new JTextField();
        this.passField = new JPasswordField();
        this.loginButton = new JButton("Login");
        this.createAccountButton = new JButton("Create Account");
        this.exitButton = new JButton("Exit");
        this.windowHeight = frame.getHeight();
        this.windowWidth = frame.getWidth();
        this.loginButton.setBounds((windowWidth/2)-200, 3*windowHeight/4, 150, 30);
        this.createAccountButton.setBounds((windowWidth/2)-20, 3*windowHeight/4, 150, 30);
        this.exitButton.setBounds((windowWidth/2)+160, 3*windowHeight/4, 100, 30);
        this.createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open create account dialog
                new CreateAccountDialog(frame);
            }
        });
    }

    public void loginArea() {
        exitButton.addActionListener(event -> {
            System.exit(0);
        });

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 100, 80, 25);
        this.add(userLabel);

        userField.setBounds(100, 100, 200, 25);
        this.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 175, 80, 25);
        this.add(passLabel);

        passField.setBounds(100, 175, 200, 25);
        this.add(passField);

        this.setLayout(null);
        this.add(loginButton);
        this.add(createAccountButton);
        this.add(exitButton);

        this.setVisible(true);
        this.window.setVisible(true);
        this.window.repaint();
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    private void createAccount() {
        String username = userField.getText();
        String password = new String(passField.getPassword());


    }
}
*/

/*
@author Salim Jday
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

    public LoginScreen(JFrame frame) {
        initializeUI(frame);
    }

    private void initializeUI(JFrame frame) {
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
}
