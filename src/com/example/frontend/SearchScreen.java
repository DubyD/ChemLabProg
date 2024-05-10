package com.example.frontend;

import com.example.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends JPanel implements ActionListener {
    private JButton logOutButton, searchButton;
    private JTextField searchBar;
    private JLabel userName;
    private JComboBox<String> searchMenu;
    private SearchResultsPanel resultPanel; //test SearchResultPanel
    private Department chemLab;

    public SearchScreen() {
        //Jonathan Murphy
        this.chemLab = new chemLab();
        setPreferredSize(new Dimension(800, 600));//just for now
        this.setLayout(new BorderLayout());
        this.logOutButton = new JButton("Log Out");
        this.searchButton = new JButton("SEARCH");
        this.userName = new JLabel("Sample Text");//this will need to implement a way to get the real username
        this.searchBar = new JTextField("Search Chemical");
        //instantiating tester resultsPanel with example data
        this.searchBar.setPreferredSize(new Dimension(200, 20));
        this.resultPanel = new SearchResultsPanel(this.chemLab.getChems());
        String[] searchCats = {"Select a Category", "All Chemicals", "Rooms",
                "Hazards", "Flammable", "Corrosive", "Skin Hazard", "Eye Hazard",
                "Digestive Hazard","Respiratory Hazard","Aquatic Hazard",
                "Combustible","Oral Hazard","WetLab-Solutions"};
        //change categories when we figure out the approach we're taking
        this.searchMenu = new JComboBox<String>(searchCats);

        this.searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Search Chemical")) {
                    searchBar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().equals("")) {
                    searchBar.setText("Search Chemical");
                }
            }
        });

        this.searchMenu.setSelectedIndex(0);
        this.searchMenu.addActionListener(this);

        JPanel logOutPanel = new JPanel();
        logOutPanel.setLayout(new FlowLayout(2));
        logOutPanel.add(userName);
        logOutPanel.add(logOutButton);
        logOutPanel.setPreferredSize(new Dimension(350, 40));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(2));
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        searchPanel.add(searchMenu);
        searchPanel.add(logOutPanel);

        searchPanel.setBounds(this.getWidth() / 2, 200, 500, 100);

        this.add(searchPanel, BorderLayout.NORTH);
        //adding to test resultPanel to Search Scene
        this.add(resultPanelTest, BorderLayout.CENTER);
        //this.add(logOutPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    //Jonathan Murphy
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked") // TODO this should be removed to minimize possible issues
            JComboBox<String> b = (JComboBox<String>) event.getSource();
            searchMenu.setSelectedItem(b.getSelectedItem());
        }
    }
/*
    //Example data for showcase
    private static ArrayList<Chemical> getExampleData() {

        java.util.List<Room> roomsStoredAt = new ArrayList<>();
        roomsStoredAt.add(new Room("1", 2));

        List<Shelf> shelvesStoredAt = new ArrayList<>();
        shelvesStoredAt.add(new Shelf("1", 2));

        ArrayList<Chemical> chemicals = Sorter.readInv();
        //chemicals.add(new Chemical("Acetone", "500", "412", "1", 10, 2.7, "ml",
        //        "1678", "Acidic"));
        //chemicals.add(new Chemical("Ethanol", "Connecticut", "208", "1", 30, 3.8, "ml",
        //        "12896", "Acidic"));
        //chemicals.add(new Chemical("Water", "Earth", "312", "2", 100, 5.5, "ml",
        //        "3450976", "wet"));
        //chemicals.add(new Chemical("Sulfur", "florida", "110", "2", 12, 200.2, "ml",
        //        "501346789", "Acidic"));
        //chemicals.add(new Chemical("Tomato", "Mississippi", "19", "3", 7, 108.0, "ml",
        //        "23786", "saucy"));
        //chemicals.add(new Chemical("Salsa", "May 5th", "1738", "3", 14, 12.0, "ml",
        //        "266359", "spicy"));
        return chemicals;
    }
    */

}