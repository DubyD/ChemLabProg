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
