package com.example.frontend;

import com.example.backend.Chemical;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

//Improved by Jonathan Murphy
public class AddChemicalPane extends JPanel implements ActionListener {
    //Attributes
    private JTextField name, company, room, shelf, count, uSize, cas, hazards;
    private JComboBox<String> unit;
    private ArrayList<JLabel> labels;
    private JButton button;
    private Chemical c;

    //Methods
    public AddChemicalPane() {
        // Set the layout of the panel
        setPreferredSize(new Dimension(800, 600));//just for now
        setLayout(new BorderLayout());
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 2));
        JPanel end = new JPanel();
        end.setLayout(new FlowLayout());

        labels = new ArrayList<>();
        ArrayList<JTextField> boxes = new ArrayList<>();
        ArrayList<JPanel> panes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            labels.add(new JLabel());
            boxes.add(new JTextField());
            boxes.get(i).setPreferredSize(new Dimension(100, 20));
            panes.add(new JPanel());
            panes.get(i).setLayout(new FlowLayout(0));
        }


        labels.get(0).setText("Chemical Name");
        name = boxes.get(0);
        labels.get(1).setText("Manufacturer");
        company = boxes.get(1);
        labels.get(2).setText("Room Stored in");
        room = boxes.get(2);
        labels.get(3).setText("Shelf Stored on");
        shelf = boxes.get(3);
        labels.get(4).setText("Number of Units");
        count = boxes.get(4);
        labels.get(5).setText("Unit Size");
        uSize = boxes.get(5);
        //labels.get(6).setText("Units Used");
        //unit = boxes.get(6);//may be able to use a combo box
        String[] uList = {"ml", "g","oz","lb","mg","l","kg","gal","qt","pt","cup","tbsp","tsp","fl oz","mL","L","g","mg","kg","oz","lb","gal","qt","pt","cup","tbsp","tsp","fl oz"};
        unit = new JComboBox<>(uList);
        unit.setSelectedIndex(0);
        unit.addActionListener(this);
        labels.get(6).setText("CAS Number");
        cas = boxes.get(6);
        labels.get(7).setText("Hazards");
        hazards = boxes.get(7);//maybe check boxes?

        //thow a basic text box and button in as a placeholder
        //JTextField textField = new JTextField();
        button = new JButton("Add Chemical");
        button.addActionListener(this);
        /*button.addActionListener(event -> {
            //send an alert "chemical added"
            JPopupMenu popup = new JPopupMenu();
            popup.add("Chemical Added");
        });*/

        panes.get(0).add(labels.get(0));
        panes.get(0).add(name);
        panes.get(1).add(labels.get(1));
        panes.get(1).add(company);
        panes.get(2).add(labels.get(2));
        panes.get(2).add(room);
        panes.get(3).add(labels.get(3));
        panes.get(3).add(shelf);
        panes.get(4).add(labels.get(4));
        panes.get(4).add(count);
        panes.get(5).add(labels.get(5));
        panes.get(5).add(uSize);
        panes.get(5).add(unit);
        //panes.get(6).add(labels.get(6));
        //panes.get(6).add(unit);
        panes.get(6).add(labels.get(6));
        panes.get(6).add(cas);
        panes.get(7).add(labels.get(7));
        panes.get(7).add(hazards);

        for(JPanel pane : panes) {
            center.add(pane);
        }
        end.add(button);
        //JPanel space = new JPanel();
        //space.setPreferredSize(new Dimension(500, 100));

        //add(space, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(end, BorderLayout.SOUTH);
        //add(testOutput, BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked") // TODO this should be removed to minimize possible issues
            JComboBox<String> b = (JComboBox<String>) event.getSource();
            unit.setSelectedItem(b.getSelectedItem());
        }
        if(event.getSource().equals(button)) {
            //JFrame for testing purposes
            JFrame frame = new JFrame();
            JPanel p;
            try {
                this.c = buildChemical();
                p = new ChemDetailPane(c);
                frame.getContentPane().add(p);
            }
            catch(Exception e) {
                frame.setPreferredSize(new Dimension(400, 100));
                p = new JPanel();
                p.add(new JLabel(e.getMessage()));
            }
            frame.getContentPane().add(p);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }


    public Chemical buildChemical() throws Exception {
        String n = name.getText();
        String c = company.getText();
        String r = room.getText();
        String s = shelf.getText();
        int co = -1;
        double us = -1.0;
        String ca = cas.getText();
        String h = hazards.getText();

        if(n.equals("")) {
            throw new Exception("Name field was left blank.");
        }
        if(c.equals("")) {
            throw new Exception("Manufacturer field was left blank.");
        }
        if(r.equals("")) {
            throw new Exception("Room field was left blank.");
        }
        if(s.equals("")) {
            throw new Exception("Shelf field was left blank.");
        }
        try {
            co = Integer.parseInt(count.getText());
        }
        catch(Exception e) {
            throw new Exception("Number of Units field must be filled with an integer.");
        }
        if(co < 0) {
            throw new Exception("Number of Units cannot be a negative number.");
        }
        try {
            us = Double.parseDouble(uSize.getText());
        }
        catch(Exception e) {
            throw new Exception("Unit Size field must be filled with an integer or decimal.");
        }
        String u = (String) unit.getSelectedItem();
        if(ca.equals("")) {
            throw new Exception("CAS Number field was left blank.");
        }
        if(h.equals("")) {
            throw new Exception("Hazards field was left blank.");
        }
        return new Chemical(n, c, r, s, co, us, u, ca, h);
    }

    public JButton getChemButton(){
        return button;
    }

    public Chemical getC(){
        return this.c;
    }
}