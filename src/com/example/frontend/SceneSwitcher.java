/*
@author Salim Jday
*/
package com.example.frontend;
import com.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {
    private JFrame frame;
    private LoginScreen loginScreen;
    private MainTabbedPane mainTabbedPane;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        initializeScreens();
        setupLoginScreenActions();
    }

    private void initializeScreens() {
        this.loginScreen = new LoginScreen(frame);
        this.frame.setContentPane(loginScreen);
        this.frame.revalidate();
        this.frame.repaint();
        this.frame.setVisible(true);
        this.mainTabbedPane = new MainTabbedPane();

    }

    private void setupLoginScreenActions() {
        this.loginScreen.getLoginButton().addActionListener(e -> {
            if (authenticate(this.loginScreen.getUserField().getText(), new String(this.loginScreen.getPassField().getPassword()))) {
                showMainTabbedPane();
            } else {
                this.loginScreen.getMessageLabel().setText("Invalid credentials, please try again.");
            }
        });
    }

    private boolean authenticate(String username, String password) {


        if(("admin".equals(username) && "password".equals(password)) || (.equals(username) && .equals(password))){
            return true;
        }else{
            return false;
        }

    }

    private void showMainTabbedPane() {
        this.frame.setContentPane(mainTabbedPane);
        this.frame.revalidate();
        this.frame.repaint();
    }
}


/*package com.example.frontend;
//Author VS

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SceneSwitcher {

    private JFrame frame;

    private LoginScreen loginScreen;
    private MainTabbedPane mainTabbedPane;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.loginScreen = new LoginScreen(frame);
        this.mainTabbedPane = new MainTabbedPane();


        this.loginScreen.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    showSearchScreen();
            }
        });

        this.frame.setContentPane(loginScreen);
        this.frame.setVisible(true);
        this.loginScreen.loginArea();
    }

    public SceneSwitcher(){
        this.frame = null;
        this.loginScreen = null;
        this.mainTabbedPane = null;
    }

    private void showSearchScreen() {
        //Exits Application
        mainTabbedPane.getSearchScreen().getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginScreen.loginArea();
                frame.setContentPane(loginScreen);
            }
        });

        this.frame.setContentPane(mainTabbedPane);
        this.frame.revalidate();
    }
}
        /* moved instantiation to MainTabbedPane.java
        //Added by AW 5/1/2024
        //Adding manage users function, is not restricted to just admin accounts yet
        JButton manageButton = new JButton("Manage Users");

        manageButton.addActionListener(event ->{
            frame.getContentPane().removeAll();
            AdminScene adminScreen = new AdminScene(frame);
            adminScreen.getReturnButton().addActionListener(internalEvent ->{
            frame.getContentPane().removeAll();
            showSearchScreen();

        });
        });

        this.searchPanel.add(manageButton, BorderLayout.NORTH);
        this.frame.setContentPane(searchPanel);
        this.frame.revalidate();
    }
}
*/
