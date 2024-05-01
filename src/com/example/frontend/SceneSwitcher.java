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


/*

//Some other version of code, mostly for testing. By SJ

package com.example.frontend;

import javax.swing.*;
import java.awt.*;

public class SceneSwitcher {
    private JFrame frame;
    private JPanel searchPanel;

    private LoginScreen loginScreen;
    private SearchScreen searchScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        initializeScreens();
        setupLoginScreenActions();
    }

    private void initializeScreens() {
        this.loginScreen = new LoginScreen();
        frame.setContentPane(loginScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void setupLoginScreenActions() {

        loginScreen.getLoginButton().addActionListener(e -> {
            if (authenticate(loginScreen.getUserField().getText(), new String(loginScreen.getPassField().getPassword()))) {
                showSearchScreen();
            } else {
                loginScreen.getMessageLabel().setText("Invalid credentials, please try again.");
            }
        });
    }

    //hardcoding some basic authentication for now at least, enter admin and password to take you to the search chemical page
    private boolean authenticate(String username, String password) {
        // Simple authentication logic
        return "admin".equals(username) && "password".equals(password);
    }

    // chemical search screen, doesn't do anything much for now
    private void showSearchScreen() {
        if (this.searchScreen == null) {
            this.searchScreen = new SearchScreen();
        }
        // Set up action for the logout button in the search screen
        searchScreen.getLogOutButton().addActionListener(e -> System.exit(0));

        // Prepare the search screen panel
        this.searchPanel = new JPanel();
        this.searchPanel.setLayout(new BorderLayout());
        this.searchPanel.add(this.searchScreen, BorderLayout.CENTER);

        // Update the main frame to show the search screen
        this.frame.setContentPane(searchPanel);
        this.frame.revalidate();
        this.frame.repaint();
    }
}
*/
