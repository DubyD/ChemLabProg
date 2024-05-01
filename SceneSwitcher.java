package com.example.frontend;
//Author VS
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {

    private JFrame frame;
    private JPanel searchPanel;

    private LoginScreen loginScreen;
    private SearchScreen searchScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.loginScreen = new LoginScreen(frame);
        this.searchScreen = new SearchScreen();

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
        this.searchPanel = null;
        this.loginScreen = null;
        this.searchScreen = null;
    }

    private void showSearchScreen() {
        searchScreen = new SearchScreen();
        //Exits Application
        searchScreen.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });

        //Sets up the Search Screen
        this.searchPanel = new JPanel();
        this.searchPanel.setLayout(new BorderLayout());
        this.searchPanel.add(this.searchScreen, BorderLayout.CENTER);
        
        //Added by AW 5/1/2024
        //Adding manage users function, is not restricted to just admin accounts yet
        JButton manageButton = new JButton("Manage Users");
        
        manageButton.addActionListener(event ->{
            frame.getContentPane().removeAll();
            adminScene adminScreen = new adminScene(frame);
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