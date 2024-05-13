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
    private JButton logOutButton, searchButton, backButton, deleteButton;
    private JTextField searchBar;
    private JLabel userName;
    private JComboBox<String> searchCategories;

    private SearchResultsPanel allChemicalsPanel, newSearchPanel; //test SearchResultPanel
    //private Department chemLab;

        //using to fix Sorter
    private ArrayList<Chemical> chemicalsList = Sorter.chemList();
    private ArrayList<Chemical> searchedChemical;

    /**
     * This is the main screen a user will use. It will display all the data, and allow users to Search through
     * that data and delete any Chemicals the select. You can Search by ChemicalName, Company, Room, or hazard.
     * Deleted Chemicals are saved just in case, in a recently_deleted.csv File.
     */
    public SearchScreen() {
        //Jonathan Murphy
        setPreferredSize(new Dimension(800, 600));//just for now
        this.setLayout(new BorderLayout());

        this.deleteButton = new JButton("delete");
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
        //searchPanel.add(this.flask);
        searchPanel.add(deleteButton);
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

        /**used to show allData again after searching*/
        backButton.addActionListener(event ->{
            try {
                remove(newSearchPanel);
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            add(allChemicalsPanel, BorderLayout.CENTER);
            revalidate();
        });

        /**Deletes selected chemical and stores into recently_deleted.csv file*/
        deleteButton.addActionListener(event ->{
            JPopupMenu deletePopUp = new JPopupMenu("Are you Sure?");
            try {
                if (allChemicalsPanel.isDisplayable()) {
                    int chemicalIndex = allChemicalsPanel.getTable().getSelectedRow();
                    String chemName = chemicalsList.get(chemicalIndex).getName();
                    deletePopUp.add(new JLabel("Are you sure you want to delete:" + "\n" + chemName + "?"));
                    //yes and no buttons
                    JButton yesButton = new JButton("YES");
                    yesButton.addActionListener(e1 ->{
                        Sorter.writeToRecentlyDeleted(chemicalsList.get(chemicalIndex));
                        chemicalsList.remove(chemicalIndex);
                        Sorter.writeInv(chemicalsList);
                        deletePopUp.setVisible(false);
                        remove(allChemicalsPanel);
                        allChemicalsPanel = new SearchResultsPanel(chemicalsList);
                        add(allChemicalsPanel, BorderLayout.CENTER);
                    });
                    JButton noButton = new JButton("no");
                    yesButton.addActionListener(e1 ->{
                        deletePopUp.setVisible(false);
                    });

                    deletePopUp.add(yesButton);
                    deletePopUp.add(noButton);
                    deletePopUp.setVisible(true);
                }else if (newSearchPanel.isDisplayable()) {
                    int chemicalIndex = newSearchPanel.getTable().getSelectedRow();
                    String chemName = searchedChemical.get(chemicalIndex).getName();
                    deletePopUp.add(new JLabel("Are you sure you want to delete:" + "\n" + chemName + "?"));
                    //yes and no buttons
                    JButton yesButton = new JButton("YES");
                    yesButton.addActionListener(e1 ->{
                        Sorter.writeToRecentlyDeleted(chemicalsList.get(chemicalIndex));
                        chemicalsList.remove(searchedChemical.get(chemicalIndex));
                        searchedChemical.remove(chemicalIndex);
                        Sorter.writeInv(chemicalsList);
                        deletePopUp.setVisible(false);
                        remove(newSearchPanel);
                        newSearchPanel = new SearchResultsPanel(searchedChemical);
                        add(newSearchPanel, BorderLayout.CENTER);
                    });
                    JButton noButton = new JButton("no");
                    yesButton.addActionListener(e1 ->{
                        deletePopUp.setVisible(false);
                    });

                    deletePopUp.add(yesButton);
                    deletePopUp.add(noButton);
                    deletePopUp.setVisible(true);
                }
                revalidate();
            }catch (NullPointerException e){
                System.out.println(" ");
            }
        });

        /**Goes through data and displays only what the user Searched depending on
         which Column they chose to search through.*/
        searchButton.addActionListener(event ->{
            if(newSearchPanel != null){
                remove(newSearchPanel);
            }
            searchedChemical = new ArrayList<>();
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
        allChemicalsPanel = new SearchResultsPanel(chemicalsList);
    }

        //Puts username in top Right
    public void setUserName(String user){
        this.userName.setText(user);
        revalidate();
    }

    public SearchResultsPanel getAllChemicalsPanel() {
        return allChemicalsPanel;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public ArrayList<Chemical> getChemicalsList() {
        return chemicalsList;
    }

    public ArrayList<Chemical> getSearchedChemical() {
        return searchedChemical;
    }

    @Override
    public String toString() {
        return "SearchScreen{" +
                "logOutButton=" + logOutButton +
                ", searchButton=" + searchButton +
                ", backButton=" + backButton +
                ", deleteButton=" + deleteButton +
                ", searchBar=" + searchBar +
                ", userName=" + userName +
                ", searchCategories=" + searchCategories +
                ", allChemicalsPanel=" + allChemicalsPanel +
                ", newSearchPanel=" + newSearchPanel +
                ", chemicalsList=" + chemicalsList +
                ", searchedChemical=" + searchedChemical +
                '}';
    }
}