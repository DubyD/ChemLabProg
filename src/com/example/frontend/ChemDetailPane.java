package com.example.frontend;

import com.example.backend.Chemical;

import javax.swing.*;
import java.awt.*;

//Jonathan Murphy
public class ChemDetailPane extends JPanel {
    //Attributes
    private Chemical chemical;
    private JLabel name, casNum, sdsLink, company, unitSize, located, hazards;
    //Methods
    public ChemDetailPane(Chemical c) {
        chemical = c;
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        setPreferredSize(new Dimension(500, 100));//tweak this as needed
        setLayout(new BorderLayout());
        //chemData = new JLabel("Chemical: " + chemical.getName() + " Manufacturer: " + chemical.getName() + " Unit Size: " + chemical.getSize() + " " + chemical.getSizeUnit());
        //add(chemData);
        name = new JLabel("Chemical: " + chemical.getName());
        casNum = new JLabel("CAS #: " + chemical.getCasNum());
        sdsLink = new JLabel("More Info: " + chemical.getSdsSheet());
        company = new JLabel("Manufacturer: " + chemical.getManufacturer());
        unitSize = new JLabel("Unit Size: " + chemical.getSize() + " " + chemical.getSizeUnit());
        located = new JLabel("Location: " + chemical.getRoom() + " " + chemical.getShelf() + ", Count: " + chemical.getContainers());
        hazards = new JLabel("Warning: " + chemical.getHazards());
        add(name, BorderLayout.NORTH);

        JPanel west = new JPanel();
        west.setLayout(new GridLayout(0, 1));
        west.add(casNum);
        west.add(company);
        west.add(unitSize);
        west.add(located);

        JPanel east = new JPanel();
        east.setLayout(new GridLayout(0, 1));
        east.add(sdsLink);
        east.add(hazards);

        add(west, BorderLayout.WEST);
        add(east, BorderLayout.EAST);
    }
}
