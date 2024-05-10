/*
@author Salim Jday
*/
package com.example.frontend;
import com.example.Main;
import com.example.backend.User;
import com.example.backend.UserDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {
    private JFrame frame;
    private LoginScreen loginScreen;
    private MainTabbedPane mainTabbedPane;
    private UserDatabase userSheet;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        initializeScreens();
        setupLoginScreenActions();
    }

    private void initializeScreens() {
        this.loginScreen = new LoginScreen(this.frame);
        this.frame.setContentPane(this.loginScreen);
        this.frame.revalidate();
        this.frame.repaint();
        this.frame.setVisible(true);
        this.mainTabbedPane = new MainTabbedPane();

        // this.userSheet = new userSheet();

    }

    private void setupLoginScreenActions() {
        User user = new User();
        this.loginScreen.getLoginButton().addActionListener(e -> {
            if (user.login(this.loginScreen.getUserField().getText(), this.loginScreen.getPassField().getText())) {
                showMainTabbedPane();
            } else {
                this.loginScreen.getMessageLabel().setText("Invalid credentials, please try again.");
            }
        });
    }


        //UserDataBase has a verify log in method
    /*private boolean authenticate(String username, String password) {

        return this.userSheet.verifyLogin(username, password);

    }*/

    private void showMainTabbedPane() {
        this.frame.setContentPane(mainTabbedPane);
        this.frame.revalidate();
        this.frame.repaint();
    }

        //returns the user sheet for the admin
    public UserDatabase getUserSheet(){
        return this.userSheet;
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
