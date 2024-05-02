package com.example.frontend;

import java.util.Arrays;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainTabbedPane extends JPanel{
    private SearchScreen searchScreen;

    public MainTabbedPane(){
        // Set the layout of the panel
        setLayout(new BorderLayout());
        
        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Create the panels
        this.searchScreen = new SearchScreen();
        AddChemicalPane addChemicalPane = new AddChemicalPane();
        AdminScene adminScene = new AdminScene();

        
        // Add the panels to the tabbed pane
        tabbedPane.addTab("Search", searchScreen);
        tabbedPane.addTab("Add Chemical", addChemicalPane);
        tabbedPane.addTab("Admin Settings", adminScene);
        
        // Add the tabbed pane to the panel
        add(tabbedPane);
    }

     // basic testing stub
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Science Department Inventory");

        // Set the size of the frame
        frame.setSize(400, 300);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new MainTabbedPane
        MainTabbedPane mainTabbedPane = new MainTabbedPane();

        // Add the MainTabbedPane to the frame
        frame.add(mainTabbedPane);

        // Set the visibility of the frame
        frame.setVisible(true);
    }
    //needed for SceneSwitcher.java
    public SearchScreen getSearchScreen() {
        return searchScreen;
    }
}
