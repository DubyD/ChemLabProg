package com.example.frontend;

import com.example.backend.Chemical;
import com.example.backend.Sorter;
import com.example.backend.TakeOutSlip;
import com.example.backend.User;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class MainTabbedPane extends JPanel{
    private SearchScreen searchScreen;
    private JTabbedPane tabbedPane;
    private AddChemicalPane addChemicalPane;
    private User user;

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
        this.getTable();
        
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
            try {
                Chemical newChemical = this.addChemicalPane.buildChemical();
                Sorter.addChemToInv(newChemical);
                this.searchScreen.addChem(newChemical);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            searchScreen.refreshScreen();
        });
    }

        //Puts username in top Right
    public void setUserName(String user){
        this.searchScreen.setUserName(user);
    }

    public JButton getLogoutButton() {
        return searchScreen.getLogOutButton();
    }

    public void getTable(){


        JTable table = this.searchScreen.getTable();
        User signingOut = this.user;


                //Trying to create a method that when a unit is
                // clicked on the table, the user is prompet
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {


                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();


                    /*
                        // Get the value of the clicked cell
                    Chemical value = (Chemical) target.getValueAt(row, 1);
                        // Show a pop-up with the name
                    int option = JOptionPane.showConfirmDialog(null, "Would you like to Check out this Chemical?\n"+
                                value.getName() + "\n" +JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                            // Handle Yes option
                        TakeOutSlip checkOut = new TakeOutSlip(signingOut, value);
                                //removes the chemical until returned
                        searchScreen.removeChem(value);

                    }
                   
                     */

                }

            });
        }
    }

    public void setUser(User viewer){
        this.user = viewer;
    }
}
