package View.Home.HomePanels;

import Controller.ActivityLoggerController;
import Controller.GroupController;
import Controller.UserController;
import Models.ActivityLogger;
import Models.FileModel;
import Models.GroupModel;
import Models.UserModel;
import View.Home.CreateUserWindow;
import View.Home.Home;
import static View.Home.HomePanels.GroupsPanel.groupsTable;
import View.Home.UIMethods;
import View.Resources.CustomFont;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author benjamin
 */
public class UsersPanel extends javax.swing.JPanel implements UIMethods {

    private List<GroupModel> groups;
    private List<UserModel> users;
    private DefaultTableModel userTableModel;
    
    public UsersPanel() {
        initComponents();
        loadFonts();
        this.groups = GroupController.getAllUserGroups();
        this.users = UserController.getAllUsers();
        constructTable();
    }
    
        @Override
    public void changeColor(JPanel hover, Color myColor) {
        hover.setBackground(myColor);
    }

    @Override
    public void changeFontColor(JLabel text, Color myColor) {
        text.setForeground(myColor);
    }

    @Override
    public final void loadFonts() {
        
        usersPanelHeading.setFont(CustomFont.panelHeadingFont);
        createLabel.setFont(CustomFont.formLabelFont);
        removeLabel.setFont(CustomFont.formLabelFont);
    }
    
    private void repaintTable() {
        JTableHeader header = userTable.getTableHeader();
        header.setBackground(new Color(0,29,61));
        header.setForeground(new Color(255, 255, 255));
        header.setPreferredSize(
                new Dimension(header.getWidth(), 40));
        userTable.setFont(CustomFont.tableRowFont);
        userTable.getTableHeader().setFont(CustomFont.tableHeaderFont);
    }
    
    private void constructTable() {
        repaintTable();
        loadDataToUserTable();
        createPopupMenu();
    }
    
    private void loadDataToUserTable() {
        //Table model creation
        String[] columnNames = {"User Id", "Username", "First Name", "Email", "Mobile"};
        userTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<Groups> groups
        for (UserModel user : users) {
            Object[] row = { user.getUserId(), user.getUsername(), user.getFirstName(), user.getEmail(), user.getMobile() };
            userTableModel.addRow(row);
        }
        
        //Setting the table model
        userTable.setModel(userTableModel);
    }
    
    //Create right click context menu for the table
    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        addToGroup = new JMenu("Add To Group");
        deleteUser = new JMenuItem("Delete User");
        JMenuItem groupName;
        //Add items to the submenu addToGroup
        for(GroupModel group : groups) {
            groupName = new JMenuItem(group.getGroupName());
            groupName.setFont(CustomFont.formTextFieldFont);
            
            groupName.addActionListener((ActionEvent e) -> {
                // Get the exact group name that was clicked
                JMenuItem clickedItem = (JMenuItem) e.getSource();
                String selectedGroupName = clickedItem.getText();
                
                if(!checkUserInGroups(selectedGroupName)) {
                    if(UserController.addUserToGroup(getSelectedUserId(), getSelectedGroupId(selectedGroupName))) {
                        JOptionPane.showMessageDialog(null, setJOptionMessageLabel(getSelectedUserId() + " added to the group " + selectedGroupName), 
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        ActivityLoggerController.logActivity(
                                new ActivityLogger(
                                        Home.user.getUsername(), getSelectedUserId(), "User had been added to " + selectedGroupName, new Date().toString())
                    ); 
                    } else {
                        JOptionPane.showMessageDialog(null, setJOptionMessageLabel("User add failed!"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, setJOptionMessageLabel("Selected user is already in the group " + selectedGroupName), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                
            });
            
            addToGroup.add(groupName);
        }

        // Add menu items to the popup menu
        popupMenu.add(addToGroup);
        popupMenu.add(deleteUser);
        
        //Set font of added menus
        addToGroup.setFont(CustomFont.formTextFieldFont);
        deleteUser.setFont(CustomFont.formTextFieldFont);
        
        
        // Add a mouse listener to detect right-clicks on the table
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger() && userTable.getSelectedRow() != -1) {
                    // Select the row where the right-click occurred
                    int row = userTable.rowAtPoint(e.getPoint());
                    userTable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        // Add action listeners for the menu items
        setActionToMenu();
    }
    
    private JLabel setJOptionMessageLabel(String message) {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(CustomFont.formTextFieldFont); 
        
        return messageLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        roundPanel1 = new View.Resources.RoundPanel();
        roundPanel2 = new View.Resources.RoundPanel();
        roundPanel3 = new View.Resources.RoundPanel();
        usersPanelHeading = new javax.swing.JLabel();
        roundPanel4 = new View.Resources.RoundPanel();
        roundPanel5 = new View.Resources.RoundPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        removeUserBTN = new View.Resources.RoundPanel();
        removeLabel = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        createUserBTN = new View.Resources.RoundPanel();
        createLabel = new javax.swing.JLabel();
        roundPanel6 = new View.Resources.RoundPanel();
        roundPanel7 = new View.Resources.RoundPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        roundPanel8 = new View.Resources.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(887, 15));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1042, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(887, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1042, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(15, 379));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(15, 379));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(10);
        roundPanel1.setRoundBottomRight(10);
        roundPanel1.setRoundTopLeft(10);
        roundPanel1.setRoundTopRight(10);
        roundPanel1.setLayout(new java.awt.BorderLayout());

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setRoundTopLeft(10);
        roundPanel2.setRoundTopRight(10);
        roundPanel2.setLayout(new java.awt.BorderLayout());

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setPreferredSize(new java.awt.Dimension(300, 100));
        roundPanel3.setRoundTopLeft(10);

        usersPanelHeading.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        usersPanelHeading.setText("Users");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(usersPanelHeading)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(usersPanelHeading)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        roundPanel2.add(roundPanel3, java.awt.BorderLayout.LINE_START);

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel4.setPreferredSize(new java.awt.Dimension(490, 100));
        roundPanel4.setRoundTopRight(10);
        roundPanel4.setLayout(new java.awt.BorderLayout());

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel5.setPreferredSize(new java.awt.Dimension(30, 100));
        roundPanel5.setRoundTopRight(10);

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        roundPanel4.add(roundPanel5, java.awt.BorderLayout.LINE_END);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel7.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel8.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.BorderLayout());

        removeUserBTN.setBackground(new java.awt.Color(204, 0, 0));
        removeUserBTN.setPreferredSize(new java.awt.Dimension(140, 40));
        removeUserBTN.setRoundBottomLeft(28);
        removeUserBTN.setRoundBottomRight(28);
        removeUserBTN.setRoundTopLeft(28);
        removeUserBTN.setRoundTopRight(28);
        removeUserBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeUserBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeUserBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeUserBTNMouseExited(evt);
            }
        });
        removeUserBTN.setLayout(new java.awt.BorderLayout());

        removeLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        removeLabel.setForeground(new java.awt.Color(255, 204, 204));
        removeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        removeLabel.setText("Remove");
        removeUserBTN.add(removeLabel, java.awt.BorderLayout.CENTER);

        jPanel10.add(removeUserBTN, java.awt.BorderLayout.LINE_END);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(20, 40));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel12, java.awt.BorderLayout.LINE_END);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.BorderLayout());

        createUserBTN.setBackground(new java.awt.Color(64, 165, 120));
        createUserBTN.setPreferredSize(new java.awt.Dimension(140, 19));
        createUserBTN.setRoundBottomLeft(28);
        createUserBTN.setRoundBottomRight(28);
        createUserBTN.setRoundTopLeft(28);
        createUserBTN.setRoundTopRight(28);
        createUserBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createUserBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createUserBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createUserBTNMouseExited(evt);
            }
        });
        createUserBTN.setLayout(new java.awt.BorderLayout());

        createLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        createLabel.setForeground(new java.awt.Color(204, 255, 204));
        createLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createLabel.setText("Create");
        createUserBTN.add(createLabel, java.awt.BorderLayout.CENTER);

        jPanel15.add(createUserBTN, java.awt.BorderLayout.EAST);

        jPanel14.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9, java.awt.BorderLayout.CENTER);

        roundPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        roundPanel2.add(roundPanel4, java.awt.BorderLayout.LINE_END);

        roundPanel1.add(roundPanel2, java.awt.BorderLayout.PAGE_START);

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setRoundBottomLeft(10);
        roundPanel6.setRoundBottomRight(10);
        roundPanel6.setLayout(new java.awt.BorderLayout());

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel7.setRoundBottomLeft(10);
        roundPanel7.setRoundBottomRight(10);
        roundPanel7.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(10, 225));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        roundPanel7.add(jPanel19, java.awt.BorderLayout.LINE_START);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(10, 225));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        roundPanel7.add(jPanel20, java.awt.BorderLayout.LINE_END);

        roundPanel8.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel8.setPreferredSize(new java.awt.Dimension(510, 10));
        roundPanel8.setRoundBottomLeft(10);
        roundPanel8.setRoundBottomRight(10);

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1012, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel7.add(roundPanel8, java.awt.BorderLayout.PAGE_END);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User ID", "Username", "First Name", "Email", "Mobile"
            }
        ));
        userTable.setGridColor(new java.awt.Color(255, 255, 255));
        userTable.setRowHeight(40);
        userTable.setSelectionBackground(new java.awt.Color(60, 194, 250));
        jScrollPane1.setViewportView(userTable);

        roundPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        roundPanel6.add(roundPanel7, java.awt.BorderLayout.CENTER);

        roundPanel1.add(roundPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.add(roundPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void removeUserBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeUserBTNMouseClicked
        String selectedUserId = getSelectedUserId();
        //Checks if table row is selected
        if(!selectedUserId.isEmpty()) {
            int response = JOptionPane.showConfirmDialog(null, setJOptionMessageLabel("Do want to remove " 
                    + userTable.getModel().getValueAt(userTable.getSelectedRow(), 1).toString() + "?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
            //Checks if user confirms the user removal
            if(response == 0) { 
                if(UserController.removeUser(selectedUserId)) {
                    DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                    model.removeRow(userTable.getSelectedRow());
                }
            }
         }
    }//GEN-LAST:event_removeUserBTNMouseClicked

    private void removeUserBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeUserBTNMouseEntered
        changeFontColor(removeLabel, new Color(102,51,0));
        changeColor(removeUserBTN, new Color(255,102,102));
    }//GEN-LAST:event_removeUserBTNMouseEntered

    private void removeUserBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeUserBTNMouseExited
        changeFontColor(removeLabel, new Color(255,102,102));
        changeColor(removeUserBTN, new Color(190, 49, 68));
    }//GEN-LAST:event_removeUserBTNMouseExited

    private void createUserBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserBTNMouseEntered
        changeFontColor(createLabel, new Color(64, 165, 120));
        changeColor(createUserBTN, new Color(157, 222, 139));
    }//GEN-LAST:event_createUserBTNMouseEntered

    private void createUserBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserBTNMouseExited
        changeFontColor(createLabel, new Color(204,255,204));
        changeColor(createUserBTN, new Color(64,165,120));
    }//GEN-LAST:event_createUserBTNMouseExited

    private void createUserBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserBTNMouseClicked
        new CreateUserWindow(this.groups).setVisible(true);
    }//GEN-LAST:event_createUserBTNMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createLabel;
    private View.Resources.RoundPanel createUserBTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel removeLabel;
    private View.Resources.RoundPanel removeUserBTN;
    private View.Resources.RoundPanel roundPanel1;
    private View.Resources.RoundPanel roundPanel2;
    private View.Resources.RoundPanel roundPanel3;
    private View.Resources.RoundPanel roundPanel4;
    private View.Resources.RoundPanel roundPanel5;
    private View.Resources.RoundPanel roundPanel6;
    private View.Resources.RoundPanel roundPanel7;
    private View.Resources.RoundPanel roundPanel8;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel usersPanelHeading;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popupMenu;
    private JMenu addToGroup;
    private JMenuItem deleteUser;

    
    //Setting action listeners for the menu items
    //Upon clicking on them relevant functions should get invoked
    private void setActionToMenu() {
        //Add action to delete user option
        deleteUser.addActionListener((ActionEvent e) -> {
            String userId = getSelectedUserId();
            
            if(!userId.isEmpty()) {
            int response = JOptionPane.showConfirmDialog(null, setJOptionMessageLabel("Do want to remove " 
                    + userTable.getModel().getValueAt(userTable.getSelectedRow(), 1).toString() + "?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
            //Checks if user confirms the user removal
            if(response == 0) { 
                if(UserController.removeUser(userId)) {
                    DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                    model.removeRow(userTable.getSelectedRow());
                }
            }
         }
            
        });
        
        //Add action to addToGroup option
        addToGroup.addActionListener((ActionEvent e) -> {
            String userId = getSelectedUserId();
            
        });
        
    }
    
    
    private String getSelectedUserId() {
        String userId = userTable
                .getValueAt(userTable.getSelectedRow(), userTable.getSelectedColumn())
                .toString();
        
        if(!userId.isEmpty()) {
            System.out.println("UserId selected " + userId);
            return userId;
        } else {
            System.out.println("UserId not selected " + userId);
            return null;
        }
    }
    
    //Gets the respective groupid by the group name
    private String getSelectedGroupId(String groupName) {
        
        for(GroupModel group : groups) {
            if(group.getGroupName().equals(groupName)) {
                return group.getGroupId();
            }
            break;
        }
        
        return null;
    }
    
    private boolean checkUserInGroups(String groupName) {
        for(UserModel user : users) {
            for(GroupModel group : user.getGroups()) {
                return group.getGroupName().equals(groupName);
            }
        }
        return false;
    }
}
