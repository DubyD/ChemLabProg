/*
 Augustus: Make an adminScene class where if the user is an admin they can click a button to go and add new users, doesn’t have to do anything at the moment just a new scene that can switch back to the searchScene, (add a button in the SearchScene to switch to it (Use SceneSwitcher))
 @author Augustus 'Gus' Warmington
The adminScene class provides an exclusive scene for admins only, to add and remove users
*/
package com.example.frontend;

import com.example.backend.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Gus Warmington
 */
public class AdminScene extends JPanel{
    private JFrame frame;
    private JPanel userPanel;
    private DefaultListModel<User> unapprovedUsers;
    private DefaultListModel<User> approvedUsers;
    private JButton returnButton;

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
        infoButton.addActionListener(event ->{
            
            if(userPanel == null){
                if(cUserList.isVisible()){
                    if(!cUserList.isSelectionEmpty()){
                    showUserInfo(cUserList.getSelectedValue());
                    }
                }
                if(pUserList.isVisible()){
                    
                    if(!pUserList.isSelectionEmpty()){
                        showUserInfo(pUserList.getSelectedValue());
                    }
                }
            }
            else{
                this.remove(userPanel);
                userPanel = null;
            }
                this.revalidate();
                this.repaint();
        });

        this.add(currentUsersButton);
        this.add(pendingUsersButton);
        this.add(cListScroller);
        this.add(pListScroller);
        this.add(infoButton);
        this.add(approveUserButton);
        this.add(denyUserButton);
        this.add(makeAdminButton);
        this.add(removeUserButton);
        
        
        frame.add(this);
        frame.revalidate();
    
    }
    private void showUserInfo(User user){
        if(userPanel !=null){
        this.remove(userPanel);
        }
        userPanel = new JPanel();
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userPanel.setPreferredSize(new Dimension(600, 400));
        this.add(userPanel);
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
