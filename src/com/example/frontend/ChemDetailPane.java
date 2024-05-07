package com.example.frontend;

import com.example.backend.Chemical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

//Jonathan Murphy
public class ChemDetailPane extends JPanel {
    //Attributes
    private Chemical chemical;
    private JLabel name, casNum, sdsLink, sdsPrompt, company, unitSize, located, hazards;
    //Methods
    public ChemDetailPane(Chemical c) {
        chemical = c;
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        setPreferredSize(new Dimension(700, 200));//tweak this as needed
        setLayout(new BorderLayout());
        name = new JLabel("Chemical: " + chemical.getName());
        casNum = new JLabel("CAS #: " + chemical.getCasNum());
        sdsPrompt = new JLabel("More Info:");
        sdsLink = new JLabel(chemical.getSdsSheet());
        company = new JLabel("Manufacturer: " + chemical.getManufacturer());
        unitSize = new JLabel("Unit Size: " + chemical.getSize() + " " + chemical.getSizeUnit());
        located = new JLabel("Location: " + chemical.getRoom() + " " + chemical.getShelf() + ", Count: " + chemical.getContainers());
        hazards = new JLabel("Warning: " + chemical.getHazards());

        sdsLink.setForeground(Color.BLUE);
        sdsLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(chemical.getSdsSheet()));
                }
                catch(Exception x) {
                    //something to tell the user there is no working link
                }
            }
        });

        add(name, BorderLayout.NORTH);

        JPanel west = new JPanel();
        west.setLayout(new GridLayout(0, 1));
        west.add(casNum);
        west.add(company);
        west.add(unitSize);
        west.add(located);

        JPanel east = new JPanel();

        east.setLayout(new GridLayout(0, 1));
        east.add(sdsPrompt);
        east.add(sdsLink);
        east.add(hazards);

        add(west, BorderLayout.WEST);
        add(east, BorderLayout.EAST);
    }
}