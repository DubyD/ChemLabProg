package com.example.frontend;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainTabbedPane extends JPanel{
    private SearchScreen searchScreen;
    private JTabbedPane tabbedPane;
    private AddChemicalPane addChemicalPane;
    private ImageIcon topLeft;

    public MainTabbedPane(){
        // Set the layout of the panel
        setLayout(new BorderLayout());
        
        // Create the tabbed pane
        this.tabbedPane = new JTabbedPane();
        
        // Create the panels
        this.searchScreen = new SearchScreen();
        this.addChemicalPane = new AddChemicalPane();
        this.setChemPaneButton();
        
        // Add the panels to the tabbed pane
        this.tabbedPane.addTab("Search", searchScreen);
        this.tabbedPane.addTab("Add Chemical", addChemicalPane);
        
        // Add the tabbed pane to the panel
        this.add(tabbedPane);
    }
/*
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
*/
    public void addAdminPane(){
        AdminScene adminScene = new AdminScene();
        this.tabbedPane.addTab("Admin Settings", adminScene);
    }
    //needed for SceneSwitcher.java
    public SearchScreen getSearchScreen() {
        return searchScreen;
    }

    public void setChemPaneButton(){
        this.addChemicalPane.getChemButton().addActionListener(event ->{

           this.searchScreen.addChem(this.addChemicalPane.getC());
        });
    }

        //Puts username in top Right
    public void setUserName(String user){
        this.searchScreen.setUserName(user);
    }

    public JButton getLogoutButton() {
        return searchScreen.getLogOutButton();
    }
}
