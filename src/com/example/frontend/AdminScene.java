/*
 Augustus: Make an adminScene class where if the user is an admin they can click a button to go and add new users, doesn’t have to do anything at the moment just a new scene that can switch back to the searchScene, (add a button in the SearchScene to switch to it (Use SceneSwitcher))
 @author Augustus 'Gus' Warmington
The adminScene class provides an exclusive scene for admins only, to add and remove users
*/
package com.example.frontend;

import com.example.backend.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Gus Warmington
 */
public class AdminScene extends JPanel{
    private JFrame frame;
    private JPanel userPanel;
    private DefaultListModel<User> unapprovedUsers;
    private DefaultListModel<User> approvedUsers;

    private JList<User> cUserList;
    private JList<User> pUserList;
    private JScrollPane cListScroller;
    private JScrollPane pListScroller;

    public AdminScene(JFrame frame, DefaultListModel<User> unapprovedUsers, DefaultListModel<User> approvedUsers) {
        this.frame = frame;
        if (unapprovedUsers == null) {
            this.unapprovedUsers = new DefaultListModel<>();
        } else {
            this.unapprovedUsers = unapprovedUsers;
        }
        if (approvedUsers == null) {
            this.approvedUsers = new DefaultListModel<>();
        } else {
            this.approvedUsers = approvedUsers;
        }

        runScene();
    }

    public AdminScene(JFrame frame) {
        this.frame = frame;
        this.unapprovedUsers = new DefaultListModel<>();
        this.approvedUsers = new DefaultListModel<>();

        runScene();
    }

    public AdminScene() {
        this.frame = new JFrame("User Management");
        this.unapprovedUsers = new DefaultListModel<>();

        this.approvedUsers = new DefaultListModel<>();

        runScene();
    }

    private void runScene() {
        
         unapprovedUsers.add(unapprovedUsers.getSize(), new User("Gus", "password"));
        //Temporary test user
        
        //Approve and Deny buttons, appear when Pending Users is selected
        JButton approveUserButton = new JButton("Approve");
        approveUserButton.setVisible(false);
        JButton denyUserButton = new JButton("Deny");
        denyUserButton.setVisible(false);
        
        JButton removeUserButton = new JButton("Remove");
        removeUserButton.setVisible(false);
        JButton makeAdminButton = new JButton("Set Admin");
        makeAdminButton.setVisible(false);
        
        JButton currentUsersButton = new JButton("Active Users (" + approvedUsers.getSize() +")" );
        currentUsersButton.addActionListener(event ->{
            cUserList.clearSelection();
            pUserList.clearSelection();
        if(cListScroller.isVisible()){
            cListScroller.setVisible(false);
            removeUserButton.setVisible(false);
            makeAdminButton.setVisible(false);
        }
        else{
            cListScroller.setVisible(true);
            removeUserButton.setVisible(true);
            makeAdminButton.setVisible(true);
        }
            pListScroller.setVisible(false);
            approveUserButton.setVisible(false);
            denyUserButton.setVisible(false);
            
             if(userPanel !=null){
                this.remove(userPanel);
                userPanel = null;
            }

            this.revalidate();
            this.repaint();
            frame.revalidate();
        });
        
        
        JButton pendingUsersButton = new JButton("Pending Users (" + unapprovedUsers.getSize() +")" );
        pendingUsersButton.addActionListener(event ->{
            cUserList.clearSelection();
            pUserList.clearSelection();
        if(pListScroller.isVisible()){
            pListScroller.setVisible(false);
            approveUserButton.setVisible(false);
            denyUserButton.setVisible(false);

        }
        else{
            pListScroller.setVisible(true);
            approveUserButton.setVisible(true);
            denyUserButton.setVisible(true);
        }   
            removeUserButton.setVisible(false);
            makeAdminButton.setVisible(false);
            cListScroller.setVisible(false);
            if(userPanel !=null){
                 this.remove(userPanel);
                 userPanel = null;                 
            }
            
            this.repaint();
            this.revalidate();
            frame.revalidate();
        });

        //Current User List {
        cUserList = new JList(approvedUsers);
        cUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
       cListScroller = new JScrollPane(cUserList);
        cListScroller.setWheelScrollingEnabled(true);
        cListScroller.createVerticalScrollBar();
        cListScroller.setPreferredSize(new Dimension(200, 70));
        cListScroller.setVisible(false);
        //}
        
        //Pending User List {
        pUserList = new JList(unapprovedUsers);
        pUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        pListScroller = new JScrollPane(pUserList);
        pListScroller.setWheelScrollingEnabled(true);
        pListScroller.createVerticalScrollBar();
        pListScroller.setPreferredSize(new Dimension(200, 70));
        pListScroller.setVisible(false);
        //}
                 
         pListScroller.createVerticalScrollBar();

        
        approveUserButton.addActionListener(event ->{
            if(pUserList.isVisible()){
                for(int i: pUserList.getSelectedIndices()){
                    if(i >= 0 ){
                    saveNewUser((User) unapprovedUsers.get(i));
                    }
                }
                pendingUsersButton.setText("Pending Users (" + unapprovedUsers.getSize() +")" );
                currentUsersButton.setText("Active Users (" + approvedUsers.getSize() +")" );
                pendingUsersButton.repaint();
                 if(userPanel !=null){
                    this.remove(userPanel);
                    userPanel = null;
                }
                pUserList.clearSelection();
                this.revalidate();
                this.repaint();
            }
        });
        denyUserButton.addActionListener(event ->{
            if(pUserList.isVisible()){
                for(int i: pUserList.getSelectedIndices()){
                    if(i >= 0 && unapprovedUsers.getSize() > 0){
                    unapprovedUsers.remove(i);
                    pUserList.remove(i);

                    }
                }

                pUserList.clearSelection();
                pendingUsersButton.setText("Pending Users (" + unapprovedUsers.getSize() +")" );
                
                pendingUsersButton.repaint();
                if(userPanel !=null){
                    this.remove(userPanel);
                    userPanel = null;
                }
                this.revalidate();
                this.repaint();
            }
        });

        
        removeUserButton.addActionListener(event ->{
           for(int i: cUserList.getSelectedIndices()){
                    if(i >= 0){
                    approvedUsers.remove(i);
                    cUserList.remove(i);
                    }
                }

                cUserList.clearSelection();
                currentUsersButton.setText("Active Users (" + approvedUsers.getSize() +")" );
                currentUsersButton.repaint();
                if(userPanel !=null){
                    this.remove(userPanel);
                    userPanel = null;
                }              
                this.revalidate();
                this.repaint();
        });

        makeAdminButton.addActionListener(event ->{
            if(cUserList.isVisible()){
                for(int i: cUserList.getSelectedIndices()){
                    if(i >= 0){
                    makeAdmin(approvedUsers.get(i));
                    }
                }
                if(userPanel !=null){
                    userPanel.repaint();
                    userPanel.revalidate();
                }  
                
                this.revalidate();
                this.repaint();
                
            }
        });
        JButton infoButton = new JButton("Display Info");
        
        JPanel topArea = new JPanel();
        BoxLayout topBoxLayout = new BoxLayout(topArea, BoxLayout.X_AXIS);
        topArea.setLayout(topBoxLayout);
        
        topArea.add(currentUsersButton);
        topArea.add(pendingUsersButton);
        topArea.add(infoButton);
        topArea.add(approveUserButton);
        topArea.add(denyUserButton);
        topArea.add(makeAdminButton);
        topArea.add(removeUserButton);
        
        JPanel botArea = new JPanel();
        BoxLayout botBoxLayout = new BoxLayout(botArea, BoxLayout.X_AXIS);
        botArea.setLayout(botBoxLayout);
        JComponent boxFillerLeft= new Box.Filler(new Dimension(200, 500),new Dimension(200, 500),new Dimension(200, 500));
        JComponent boxFillerRight = new Box.Filler(new Dimension(200, 500),new Dimension(200, 500),new Dimension(200, 500));
        botArea.add(boxFillerLeft);
        botArea.add(cListScroller, BorderLayout.PAGE_START);
        botArea.add(pListScroller, BorderLayout.PAGE_START);
        cListScroller.setPreferredSize(new Dimension(200, 200));
        pListScroller.setPreferredSize(new Dimension(200, 200));
        
        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        botArea.add(boxFillerRight);
        infoButton.addActionListener(event ->{
            
            if(userPanel == null){
                if(cUserList.isVisible()){
                    if(!cUserList.isSelectionEmpty()){
                        showUserInfo(cUserList.getSelectedValue());
                        botArea.remove(boxFillerRight);
                        botArea.add(userPanel);
                    }
                }
                if(pUserList.isVisible()){
                    
                    if(!pUserList.isSelectionEmpty()){
                        showUserInfo(pUserList.getSelectedValue());
                        botArea.remove(boxFillerRight);
                        botArea.add(userPanel);
                    }
                }
            }
            else{
                botArea.remove(userPanel);
                botArea.add(boxFillerRight);
                
                userPanel = null;
            }
                this.revalidate();
                this.repaint();
        });
        
        this.setLayout(mainBoxLayout);
        
        this.add(topArea, BorderLayout.NORTH);
        this.add(botArea, BorderLayout.SOUTH);
        frame.add(this);
        frame.revalidate();
    
    }
    private void showUserInfo(User user){
        

        userPanel = new JPanel();
        
        //Creating boxLayout, PAGE_AXIS for vertical placement
        BoxLayout boxLayout = new BoxLayout(userPanel, BoxLayout.PAGE_AXIS);
        userPanel.setLayout(boxLayout);
        
        //Creating border for Info with centered "Info" text and a lowered etched border
        Border lowerEtchBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titleBorder = BorderFactory.createTitledBorder(lowerEtchBorder, "Info");
        titleBorder.setTitleJustification(TitledBorder.CENTER); //center title
        userPanel.setBorder(titleBorder);
        userPanel.setPreferredSize(new Dimension(200, 500)); 
        
        JLabel usernameLabel = new JLabel("Username: " +user.getUsername());
        JLabel passwordLabel = new JLabel("Password: " + user.getPassword());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel securityQLabel = new JLabel("Question: " + user.getSecurityQ());
        JLabel securityALabel = new JLabel("Answer: " + user.getSecurityA());
        JLabel adminLabel = new JLabel("Admin: " + user.isAdmin());
        JLabel lastLoginLabel = new JLabel("Last Login: " + user.getLastLogin());
        
        //adding components to the userPanel
        //createRigidArea adds in some space between each item
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(usernameLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(passwordLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(emailLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(securityQLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(securityALabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(adminLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(lastLoginLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        
        this.revalidate();
        frame.revalidate();
        
    }

    public void createNewUser(String username, String password){
        User user = new User(username, password);
        unapprovedUsers.addElement(user);
        
    }
    
    private void saveNewUser(User user){
        //Not implemented yet
        approvedUsers.addElement(user);
        unapprovedUsers.removeElement(user);

    }
    
    private void makeAdmin(User user){
        user.setAdmin(true);
    }

    /*    public JButton getReturnButton() {
    return returnButton;
    }*/

    @Override
    public String toString() {
        return "This class holds a user management screen.";
        // Perhaps add user count
    }

}
