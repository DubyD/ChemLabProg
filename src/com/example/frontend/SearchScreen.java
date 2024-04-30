package com.example.frontend;

import javax.swing.*;
import java.awt.*;

public class SearchScreen extends JPanel {
    private JButton logOutButton;
    private JTextArea searchBar;

    public SearchScreen(){
        this.setLayout(new BorderLayout());
        logOutButton = new JButton("EXIT");
        searchBar = new JTextArea("Search chemical");

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchBar);
        searchPanel.setBounds(this.getWidth()/2, 200, 500,100);

        JPanel logOutPanel = new JPanel();
        logOutPanel.add(logOutButton);

        this.add(searchPanel);
        this.add(logOutPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }
}
