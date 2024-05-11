package com.example.frontend;

import com.example.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Objects;

public class SearchScreen extends JPanel implements ActionListener {
    private JButton logOutButton, searchButton, backButton;
    private JTextField searchBar;
    private JLabel userName;
    private JComboBox<String> searchCategories;
    private SearchResultsPanel allChemicalsPanel, newSearchPanel; //test SearchResultPanel
    //private Department chemLab;
    //private ArrayList<Chemical> chemicalsList;// = new SorterTest().chemicalArrayList();

        //using to fix Sorter
    private ArrayList<Chemical> chemicalsList = Sorter.chemList();

    public SearchScreen() {
        //Jonathan Murphy
        //this.chemicalsList = Sorter.chemList();
        setPreferredSize(new Dimension(800, 600));//just for now
        this.setLayout(new BorderLayout());

        this.backButton = new JButton("<");
        this.logOutButton = new JButton("Log Out");
        this.searchButton = new JButton("SEARCH");
        this.userName = new JLabel("Sample Text");//@TODO this will need to implement a way to get the real username
        this.allChemicalsPanel = new SearchResultsPanel(chemicalsList);

        //Sets up search Categories
        String[] searchCats = {"Select a Category", "All Chemicals", "Rooms",
                "Hazards", "Company" ,"Flammable", "Corrosive", "Skin Hazard", "Eye Hazard",
                "Digestive Hazard","Respiratory Hazard","Aquatic Hazard",
                "Combustible","Oral Hazard","WetLab-Solutions"};
        this.searchCategories = new JComboBox<String>(searchCats);
        this.searchCategories.setSelectedIndex(0);


        //change categories when we figure out the approach we're taking
        this.searchBar = new JTextField("Search Chemical");
        this.searchBar.setPreferredSize(new Dimension(200, 20));
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

        JPanel logOutPanel = new JPanel();
        logOutPanel.setLayout(new FlowLayout(2));
        logOutPanel.add(userName);
        logOutPanel.add(logOutButton);
        logOutPanel.setPreferredSize(new Dimension(350, 40));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(2));
        searchPanel.add(backButton);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        searchPanel.add(searchCategories);
        searchPanel.add(logOutPanel);

        searchPanel.setBounds(this.getWidth() / 2, 200, 500, 100);

        this.add(searchPanel, BorderLayout.NORTH);
        //adding to test resultPanel to Search Scene
        this.add(allChemicalsPanel, BorderLayout.CENTER);
        //this.add(logOutPanel, BorderLayout.PAGE_END);
        this.setVisible(true);

        backButton.addActionListener(event ->{
            remove(newSearchPanel);
            add(allChemicalsPanel, BorderLayout.CENTER);
            revalidate();
        });

        searchButton.addActionListener(event ->{
            if(newSearchPanel != null){
                remove(newSearchPanel);
            }
            ArrayList<Chemical> searchedChemical = new ArrayList<>();
            String searchResult = searchBar.getText();
            //searches by companies
                if(Objects.equals(searchCategories.getSelectedItem(), "Company")) {
                    for(Chemical currChem: chemicalsList) {
                        if (currChem.getManufacturer().contains(searchResult)) {
                            searchedChemical.add(currChem);
                        }
                    }
                    //searches by Rooms
                } else if (Objects.equals(searchCategories.getSelectedItem(), "Rooms")) {
                    for(Chemical currChem: chemicalsList) {
                        if (currChem.getRoom().contains(searchResult)) {
                            searchedChemical.add(currChem);

                        }
                    }
                    //searches by Hazards
                } else if (Objects.equals(searchCategories.getSelectedItem(), "Hazards")) {
                    for(Chemical currChem: chemicalsList){
                        if (currChem.getHazards().contains(searchResult)) {
                        searchedChemical.add(currChem);
                        }
                    }
                } else {
                    for(Chemical currChem: chemicalsList){
                        if (currChem.getName().contains(searchResult)) {
                            searchedChemical.add(currChem);
                        }
                    }
                }
            newSearchPanel = new SearchResultsPanel(searchedChemical);
            remove(allChemicalsPanel);
            add(newSearchPanel, BorderLayout.CENTER);
            revalidate();
        });
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
            searchCategories.setSelectedItem(b.getSelectedItem());
        }
    }

    public void addChem(Chemical next){
        this.chemicalsList.add(next);
    }

}