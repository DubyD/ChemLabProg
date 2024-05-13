/*
 Augustus: Make an adminScene class where if the user is an admin they can click a button to go and add new users, doesn’t have to do anything at the moment just a new scene that can switch back to the searchScene, (add a button in the SearchScene to switch to it (Use SceneSwitcher))
 @author Augustus 'Gus' Warmington
The adminScene class provides an exclusive scene for admins only, to add and remove users
 */
package com.example.frontend;

import com.example.backend.Admin;
import com.example.backend.User;
import com.example.backend.UserDatabase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
public class AdminScene extends JPanel {

    private JFrame frame;
    private JPanel userPanel;
    private DefaultListModel<User> unapprovedUsers;
    private DefaultListModel<User> approvedUsers;

    private JList<User> cUserList;
    private JList<User> pUserList;
    private JScrollPane cListScroller;
    private JScrollPane pListScroller;
    private boolean infoShown;
    private Admin admin;

    public AdminScene(JFrame frame) {
        admin = new Admin();
        this.frame = frame;
        this.approvedUsers = User.getDatabase().getUsers();
        this.unapprovedUsers = User.getPendingDatabase().getUsers();

        cUserList = new JList<>(approvedUsers);
        cUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cListScroller = new JScrollPane(cUserList);
        cListScroller.setWheelScrollingEnabled(true);
        cListScroller.createVerticalScrollBar();
        cListScroller.setPreferredSize(new Dimension(200, 70));
        cListScroller.setVisible(false);

        //Pending User List {
        pUserList = new JList<User>(unapprovedUsers);
        pUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pListScroller = new JScrollPane(pUserList);
        pListScroller.setWheelScrollingEnabled(true);
        pListScroller.createVerticalScrollBar();
        pListScroller.setPreferredSize(new Dimension(200, 70));
        pListScroller.setVisible(false);

        runScene();
    }

    public AdminScene() {
        admin = new Admin();
        this.frame = new JFrame("User Management");
        this.approvedUsers = User.getDatabase().getUsers();
        this.unapprovedUsers = User.getPendingDatabase().getUsers();

        cUserList = new JList<>(approvedUsers);
        cUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cListScroller = new JScrollPane(cUserList);
        cListScroller.setWheelScrollingEnabled(true);
        cListScroller.createVerticalScrollBar();
        cListScroller.setPreferredSize(new Dimension(200, 70));
        cListScroller.setVisible(false);

        //Pending User List {
        pUserList = new JList<>(unapprovedUsers);
        pUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pListScroller = new JScrollPane(pUserList);
        pListScroller.setWheelScrollingEnabled(true);
        pListScroller.createVerticalScrollBar();
        pListScroller.setPreferredSize(new Dimension(200, 70));
        pListScroller.setVisible(false);

        runScene();
    }

    /*
    Main method in the class, sets up layout and components for the adminScene
    Has two main lists to be displayed, the current user list and the pending user list
    On both lists, a user can be selected and then display info for that user.
    Pending users can be approved, denied, or have their info displayed.
    Current (approved) users can be removed, given admin privileges, or have their info displayed.
    
    Layout
        Set up using box layout
        Contains 3 panels inside of adminScene (which is its own panel)
        topArea contains buttons
        botArea contains the JScrollPanes (user lists) and the userPanel (user info)
    

     */
    private void runScene() {

        //panel for bottom half of main panel
        JPanel botArea = new JPanel();
        //Formatting filler components
        JComponent boxFillerLeft = new Box.Filler(new Dimension(200, 500), new Dimension(200, 500), new Dimension(200, 500));
        JComponent boxFillerRight = new Box.Filler(new Dimension(300, 500), new Dimension(300, 500), new Dimension(300, 500));
        JComponent buttonFiller = new Box.Filler(new Dimension(10, 5), new Dimension(10, 5), new Dimension(10, 5));
        //Temporary test user
        //Approve and Deny buttons, appear when Pending Users is selected
        newButton approveUserButton = new newButton("check");
        approveUserButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        approveUserButton.setContentAreaFilled(false);
        approveUserButton.setToolTipText("Approve User");
        approveUserButton.setVisible(false);
        newButton denyUserButton = new newButton("x");
        denyUserButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        denyUserButton.setContentAreaFilled(false);
        denyUserButton.setToolTipText("Deny User");
        denyUserButton.setVisible(false);

        //Remove and Admin buttons, appears when current users is selected
        newButton removeUserButton = new newButton("x");
        removeUserButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        removeUserButton.setContentAreaFilled(false);
        removeUserButton.setToolTipText("Remove User");
        removeUserButton.setVisible(false);
        newButton makeAdminButton = new newButton("admin");
        makeAdminButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        makeAdminButton.setContentAreaFilled(false);
        makeAdminButton.setToolTipText("Give Admin Privileges");
        makeAdminButton.setVisible(false);

        //display user info button
        newButton infoButton = new newButton("info");
        infoButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        infoButton.setContentAreaFilled(false);
        infoButton.setToolTipText("Display Info");
        infoButton.setVisible(false);

        ListSelectionModel cListModel = cUserList.getSelectionModel();
        cListModel.addListSelectionListener(e -> {
            if (((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                infoButton.setVisible(false);
                removeUserButton.setVisible(false);
                makeAdminButton.setVisible(false);
            } else {
                infoButton.setVisible(true);
                removeUserButton.setVisible(true);
                makeAdminButton.setVisible(true);
            }
            denyUserButton.setVisible(false);
            approveUserButton.setVisible(false);
            this.revalidate();
            this.repaint();
            frame.revalidate();
        });
        cUserList.setSelectionModel(cListModel);

        ListSelectionModel pListModel = pUserList.getSelectionModel();
        pListModel.addListSelectionListener(e -> {
            if (((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                infoButton.setVisible(false);
                denyUserButton.setVisible(false);
                approveUserButton.setVisible(false);
            } else {
                infoButton.setVisible(true);
                denyUserButton.setVisible(true);
                approveUserButton.setVisible(true);
            }
            removeUserButton.setVisible(false);
            this.revalidate();
            this.repaint();
            frame.revalidate();
        });
        pUserList.setSelectionModel(pListModel);

        //}
        //Current user button, 
        JButton currentUsersButton = new JButton("Active Users (" + approvedUsers.getSize() + ")");
        currentUsersButton.addActionListener(event -> {
            cUserList.clearSelection();
            pUserList.clearSelection();
            //controlling visibility of Jscrollpanes, toggling on and off
            if (cListScroller.isVisible()) {
                cListScroller.setVisible(false);
                infoButton.setVisible(false);
                removeUserButton.setVisible(false);
                makeAdminButton.setVisible(false);

            } else {
                cListScroller.setVisible(true);
            }
            pListScroller.setVisible(false);

            userPanel.removeAll();
            userPanel.setBorder(null);
            userPanel.add(boxFillerRight);
            infoShown = false;

            this.revalidate();
            this.repaint();
            frame.revalidate();
        });

        //button shows list of current number of pending users, pUserList
        JButton pendingUsersButton = new JButton("Pending Users (" + unapprovedUsers.getSize() + ")");
        pendingUsersButton.addActionListener(event -> {
            cUserList.clearSelection();
            pUserList.clearSelection();
            if (pListScroller.isVisible()) {
                pListScroller.setVisible(false);
                infoButton.setVisible(false);
                denyUserButton.setVisible(false);
                approveUserButton.setVisible(false);

            } else {
                pListScroller.setVisible(true);
            }
            removeUserButton.setVisible(false);
            infoButton.setVisible(false);
            makeAdminButton.setVisible(false);
            cListScroller.setVisible(false);

            userPanel.removeAll();
            userPanel.setBorder(null);
            userPanel.add(boxFillerRight);
            infoShown = false;

            this.repaint();
            this.revalidate();
            frame.revalidate();
        });

        //shows approved users, cUserList
        approveUserButton.addActionListener(event -> {
            if (pUserList.isVisible()) {
                User user = pUserList.getSelectedValue();
                saveNewUser(user);
                
                //updating number on user buttons
                pendingUsersButton.setText("Pending Users (" + unapprovedUsers.getSize() + ")");
                currentUsersButton.setText("Active Users (" + approvedUsers.getSize() + ")");
                pendingUsersButton.repaint();

                userPanel.removeAll();
                userPanel.setBorder(null);
                userPanel.add(boxFillerRight);
                infoShown = false;

                pUserList.clearSelection();
                this.revalidate();
                this.repaint();
            }
        });
        //removes user from pending users
        denyUserButton.addActionListener(event -> {
            if (pUserList.isVisible()) {
                User user = pUserList.getSelectedValue();
                unapprovedUsers.removeElement(user);
                pUserList.repaint();

                pUserList.clearSelection();
                currentUsersButton.setText("Active Users (" + approvedUsers.getSize() + ")");
                pendingUsersButton.setText("Pending Users (" + unapprovedUsers.getSize() + ")");
                pendingUsersButton.repaint();
                userPanel.removeAll();
                userPanel.setBorder(null);
                userPanel.add(boxFillerRight);
                infoShown = false;
                this.revalidate();
                this.repaint();
            }
        });

        //removes user from current users
        removeUserButton.addActionListener(event -> {
            if (cListScroller.isVisible()) {
                User user = cUserList.getSelectedValue();
                user.deleteAccount();
                approvedUsers.removeElement(user);

                cUserList.clearSelection();
                currentUsersButton.setText("Active Users (" + approvedUsers.getSize() + ")");
                pendingUsersButton.setText("Pending Users (" + unapprovedUsers.getSize() + ")");
                pendingUsersButton.repaint();

                currentUsersButton.repaint();
                userPanel.removeAll();
                userPanel.setBorder(null);
                userPanel.add(boxFillerRight);
                infoShown = false;
                this.revalidate();
                this.repaint();
            }
        });
        //gives admin to a user in current users list
        makeAdminButton.addActionListener(event -> {
            User user = cUserList.getSelectedValue();
            if (cListScroller.isVisible()) {
                makeAdmin(user);

                this.revalidate();
                this.repaint();

            }
        });

        JPanel topArea = new JPanel();
        BoxLayout topBoxLayout = new BoxLayout(topArea, BoxLayout.X_AXIS);
        topArea.setLayout(topBoxLayout);

        topArea.add(currentUsersButton);
        topArea.add(pendingUsersButton);
        topArea.add(buttonFiller);
        topArea.add(infoButton);
        topArea.add(approveUserButton);
        topArea.add(denyUserButton);
        topArea.add(makeAdminButton);
        topArea.add(removeUserButton);

        BoxLayout botBoxLayout = new BoxLayout(botArea, BoxLayout.X_AXIS);
        botArea.setLayout(botBoxLayout);

        botArea.add(boxFillerLeft);
        botArea.add(cListScroller, BorderLayout.PAGE_START);
        botArea.add(pListScroller, BorderLayout.PAGE_START);
        cListScroller.setPreferredSize(new Dimension(200, 200));
        pListScroller.setPreferredSize(new Dimension(200, 200));
        userPanel = new JPanel();
        userPanel.removeAll();
        userPanel.setBorder(null);

        infoShown = false;
        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        botArea.add(userPanel);
        userPanel.repaint();
        userPanel.revalidate();

        infoButton.addActionListener((ActionEvent event) -> {
            User user;
            if (!infoShown) {
            if(cListScroller.isVisible()){
                user = cUserList.getSelectedValue();
                showUserInfo(user);
            }
            else if(pListScroller.isVisible()){
                user = pUserList.getSelectedValue();
                showUserInfo(user);
            }
                infoShown = true;
            } 
            else {
                userPanel.removeAll();
                userPanel.setBorder(null);
                userPanel.add(boxFillerRight);
                infoShown = false;
            }
            userPanel.repaint();
            userPanel.revalidate();
            botArea.revalidate();
            botArea.repaint();
            frame.repaint();
            frame.revalidate();
        });

        this.setLayout(mainBoxLayout);

        this.add(topArea, BorderLayout.NORTH);
        this.add(botArea, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
        frame.add(this);
        frame.revalidate();
        frame.repaint();
    }

    //Shows userPanel, displaying user info
    private void showUserInfo(User user) {

        userPanel.removeAll();

        //Creating boxLayout, PAGE_AXIS for vertical placement
        BoxLayout boxLayout = new BoxLayout(userPanel, BoxLayout.PAGE_AXIS);
        userPanel.setLayout(boxLayout);

        //Creating border for Info with centered "Info" text and a lowered etched border
        Border lowerEtchBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titleBorder = BorderFactory.createTitledBorder(lowerEtchBorder, "Info");
        titleBorder.setTitleJustification(TitledBorder.CENTER); //center title
        userPanel.setBorder(titleBorder);
        userPanel.setPreferredSize(new Dimension(300, 500));

        JLabel usernameLabel = new JLabel("Username: " + user.getUsername());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel adminLabel = new JLabel("Admin: " + user.isAdmin());
        JLabel lastLoginLabel = new JLabel("Last Login: " + user.getLastLogin());

        //adding components to the userPanel
        //createRigidArea adds in some space between each item
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(usernameLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(emailLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(adminLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        userPanel.add(lastLoginLabel, BorderLayout.LINE_START);
        userPanel.add(Box.createRigidArea(new Dimension(5, 5)));

        this.revalidate();
        frame.revalidate();
    }



    //approve user, adds to approvedUser list
    private User saveNewUser(User user) {
        //Not implemented yet
        approvedUsers.addElement(user);
        unapprovedUsers.removeElement(user);
        user.createNewUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getSecurityQ() , user.getSecurityA(), user.isAdmin());
        return user;
    }

    //simply gives admin privileges to a user from current user list
    private void makeAdmin(User user) {
        user.setAdmin(true);
    }

    @Override
    public String toString() {
        return "This class holds a user management screen.";
        // Perhaps add user count
    }

}

class newButton extends JButton {

    String type;
    private static final long serialVersionUID = 1L;
    ImageIcon checkIcon = new ImageIcon("src/resources/images/check.png");
    ImageIcon checkHoverIcon = new ImageIcon("src/resources/images/checkHover.png");
    ImageIcon checkPressedIcon = new ImageIcon("src/resources/images/checkPressed.png");
    ImageIcon xIcon = new ImageIcon("src/resources/images/x.png");
    ImageIcon xPressedIcon = new ImageIcon("src/resources/images/xPressed.png");
    ImageIcon xHoverIcon = new ImageIcon("src/resources/images/xHover.png");
    ImageIcon infoIcon = new ImageIcon("src/resources/images/info.png");
    ImageIcon infoHoverIcon = new ImageIcon("src/resources/images/infoHover.png");
    ImageIcon infoPressedIcon = new ImageIcon("src/resources/images/infoPressed.png");
    ImageIcon approveIcon = new ImageIcon("src/resources/images/approve.png");
    ImageIcon approveHoverIcon = new ImageIcon("src/resources/images/approveHover.png");
    ImageIcon approvePressedIcon = new ImageIcon("src/resources/images/approvePressed.png");

    public newButton(String type) {
        super();
        enableInputMethods(true);
        this.type = type;
    }

    public Dimension getPrefferedSize() {
        return new Dimension(18, 18);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(18, 18);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(18, 18);
    }

    @Override
    public void paintComponent(Graphics g) {
        if ("x".equals(type)) {

            if (getModel().isPressed()) {
                g.drawImage(xPressedIcon.getImage(), 0, 0, this);
            } else if (getModel().isRollover()) {
                g.drawImage(xHoverIcon.getImage(), 0, 0, this);
            } else {
                g.drawImage(xIcon.getImage(), 0, 0, this);
            }
        }
        if ("check".equals(type)) {
            if (getModel().isPressed()) {
                g.drawImage(checkPressedIcon.getImage(), 0, 0, this);
            } else if (getModel().isRollover()) {
                g.drawImage(checkHoverIcon.getImage(), 0, 0, this);
            } else {
                g.drawImage(checkIcon.getImage(), 0, 0, this);
            }
        }
        if ("info".equals(type)) {
            if (getModel().isPressed()) {
                g.drawImage(infoPressedIcon.getImage(), 0, 0, this);
            } else if (getModel().isRollover()) {
                g.drawImage(infoHoverIcon.getImage(), 0, 0, this);
            } else {
                g.drawImage(infoIcon.getImage(), 0, 0, this);
            }
        }
        if ("admin".equals(type)) {
            if (getModel().isPressed()) {
                g.drawImage(approvePressedIcon.getImage(), 0, 0, this);
            } else if (getModel().isRollover()) {
                g.drawImage(approveHoverIcon.getImage(), 0, 0, this);
            } else {
                g.drawImage(approveIcon.getImage(), 0, 0, this);
            }
        }

    }

    @Override
    public void paintBorder(Graphics g) {
    }

}
