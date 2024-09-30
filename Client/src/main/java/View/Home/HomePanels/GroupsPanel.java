package View.Home.HomePanels;

import Controller.ActivityLoggerController;
import Controller.GroupController;
import Controller.UserController;
import Model.GroupModel;
import Model.UserModel;
import View.Home.CreateGroupWindow;
import View.Home.Home;
import View.Home.UIMethods;
import View.Resources.CustomFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author benjamin
 */
public final class GroupsPanel extends javax.swing.JPanel implements UIMethods {
    
    private List<GroupModel> groups;
    private List<UserModel> users;
    private List<String> selectedUsernames;
    DefaultTableModel tableModel;
    
    public GroupsPanel() {
        
        if(Home.user.getRole().equals("Admin")) {
            this.groups = GroupController.getUserGroupsAdmin();
        } else {
            this.groups = GroupController.getUserGroups(Home.user.getUserId());
        }
        this.users = UserController.getAllUsers();
        this.selectedUsernames = new ArrayList<>();
        initComponents();
        contructGroupsTable();
        createAddUserOption();
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
    public void loadFonts() {
        groupsPanelHeading.setFont(CustomFont.panelHeadingFont);
        createLabel.setFont(CustomFont.formLabelFont);
        removeLabel.setFont(CustomFont.formLabelFont);
        groupsTable.setFont(CustomFont.tableRowFont);
        groupsTable.getTableHeader().setFont(CustomFont.tableHeaderFont);
        userDropdown.setFont(CustomFont.joptionPaneFont);
        selectedUsersArea.setFont(CustomFont.formTextFieldFont);
    }
    
    //Construct the whole table
    private void contructGroupsTable() {
        styleTable();
        loadDataToTable();
        createPopupMenu();
        
    }
    
    //Style the table
    private void styleTable() {
        JTableHeader header = groupsTable.getTableHeader();
        header.setBackground(new Color(62, 62, 62));
        header.setForeground(new Color(255, 255, 255));
        header.setPreferredSize(
                new Dimension(header.getWidth(), 40));
    }
    
    //Load data to the table
    private void loadDataToTable() {
        //Table model creation
        String[] columnNames = {"Group ID", "Group Name", "Members", "Created By"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<Groups> groups
        for (GroupModel group : groups) {
            Object[] row = { group.getGroupId(), group.getGroupName(), group.groupMembersToString(), group.getGroupOwner().getFirstName() };
            tableModel.addRow(row);
        }
        
        //Setting the table model
        groupsTable.setModel(tableModel);
        
    }
    
    //Create right click context menu for the table
    private void createPopupMenu() {
        // Create the popup menu
        popupMenu = new JPopupMenu();
        addUsers = new JMenuItem("Add Users");
        removeUsers = new JMenuItem("Remove Users");
        deleteGroup = new JMenuItem("Delete");
        
        // Add menu items to the popup menu
        popupMenu.add(addUsers);
        popupMenu.add(deleteGroup);
        popupMenu.add(removeUsers);
        
        // Add a mouse listener to detect right-clicks on the table
        groupsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger() && groupsTable.getSelectedRow() != -1) {
                    // Select the row where the right-click occurred
                    int row = groupsTable.rowAtPoint(e.getPoint());
                    groupsTable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        //Setting action listeners for the above created menu items
        setActionListners();
    }
    
    private void createAddUserOption() {

        // Create components for the dialog
        userDropdown = new JComboBox<>(getUsernameArray());
        selectedUsersArea = new JTextArea(5, 20);
        selectedUsersArea.setEditable(false); // Display area for selected users
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Selected Users");
        titledBorder.setTitleFont(CustomFont.joptionPaneFont); 
        selectedUsersArea.setBorder(titledBorder);

        // Add action listener to JComboBox to handle selection
        userDropdown.addActionListener((ActionEvent e) -> {
            String selectedUser = (String) userDropdown.getSelectedItem();
            if (!selectedUsernames.contains(selectedUser)) {
                selectedUsernames.add(selectedUser);
                // Update the text area with selected usernames
                selectedUsersArea.setText(String.join(", ", selectedUsernames));
            } else {
                JOptionPane.showMessageDialog(null, "User already selected.");
            }
        });

        // Create a panel to hold the dropdown and selected users display
        userPanel = new JPanel(new BorderLayout());
        userPanel.add(userDropdown, BorderLayout.NORTH);
        userPanel.add(new JScrollPane(selectedUsersArea), BorderLayout.CENTER);
        userPanel.setPreferredSize(new Dimension(500, 200));
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
        jPanel6 = new javax.swing.JPanel();
        roundPanel1 = new View.Resources.RoundPanel();
        roundPanel2 = new View.Resources.RoundPanel();
        roundPanel3 = new View.Resources.RoundPanel();
        groupsPanelHeading = new javax.swing.JLabel();
        roundPanel4 = new View.Resources.RoundPanel();
        roundPanel5 = new View.Resources.RoundPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        removeGroupBTN = new View.Resources.RoundPanel();
        removeLabel = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        createGroupBTN = new View.Resources.RoundPanel();
        createLabel = new javax.swing.JLabel();
        roundPanel6 = new View.Resources.RoundPanel();
        roundPanel7 = new View.Resources.RoundPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        roundPanel8 = new View.Resources.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupsTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(891, 15));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(891, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(15, 512));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(15, 512));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

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

        groupsPanelHeading.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        groupsPanelHeading.setText("Groups");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(groupsPanelHeading)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(groupsPanelHeading)
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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel8.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel9.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.BorderLayout());

        removeGroupBTN.setBackground(new java.awt.Color(204, 0, 0));
        removeGroupBTN.setPreferredSize(new java.awt.Dimension(140, 40));
        removeGroupBTN.setRoundBottomLeft(28);
        removeGroupBTN.setRoundBottomRight(28);
        removeGroupBTN.setRoundTopLeft(28);
        removeGroupBTN.setRoundTopRight(28);
        removeGroupBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeGroupBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeGroupBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeGroupBTNMouseExited(evt);
            }
        });
        removeGroupBTN.setLayout(new java.awt.BorderLayout());

        removeLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        removeLabel.setForeground(new java.awt.Color(255, 204, 204));
        removeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        removeLabel.setText("Remove");
        removeGroupBTN.add(removeLabel, java.awt.BorderLayout.CENTER);

        jPanel11.add(removeGroupBTN, java.awt.BorderLayout.LINE_END);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(20, 40));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel13, java.awt.BorderLayout.LINE_END);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.BorderLayout());

        createGroupBTN.setBackground(new java.awt.Color(64, 165, 120));
        createGroupBTN.setPreferredSize(new java.awt.Dimension(140, 19));
        createGroupBTN.setRoundBottomLeft(28);
        createGroupBTN.setRoundBottomRight(28);
        createGroupBTN.setRoundTopLeft(28);
        createGroupBTN.setRoundTopRight(28);
        createGroupBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createGroupBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createGroupBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createGroupBTNMouseExited(evt);
            }
        });
        createGroupBTN.setLayout(new java.awt.BorderLayout());

        createLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        createLabel.setForeground(new java.awt.Color(204, 255, 204));
        createLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createLabel.setText("Create");
        createGroupBTN.add(createLabel, java.awt.BorderLayout.CENTER);

        jPanel16.add(createGroupBTN, java.awt.BorderLayout.EAST);

        jPanel15.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        roundPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

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
            .addGap(0, 263, Short.MAX_VALUE)
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
            .addGap(0, 263, Short.MAX_VALUE)
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
            .addGap(0, 944, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel7.add(roundPanel8, java.awt.BorderLayout.PAGE_END);

        groupsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Group ID", "Group Name", "Members", "Created by"
            }
        ));
        groupsTable.setRowHeight(40);
        groupsTable.setSelectionBackground(new java.awt.Color(72, 207, 203));
        jScrollPane1.setViewportView(groupsTable);

        roundPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        roundPanel6.add(roundPanel7, java.awt.BorderLayout.CENTER);

        roundPanel1.add(roundPanel6, java.awt.BorderLayout.CENTER);

        jPanel6.add(roundPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void removeGroupBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeGroupBTNMouseClicked
        String selectedGroupId = groupsTable
                .getValueAt(groupsTable.getSelectedRow(), groupsTable.getSelectedRow())
                .toString();
        
        //Checks if table row is selected
        if(!selectedGroupId.isEmpty()) {
            deleteGroup(selectedGroupId);
            tableModel.removeRow(groupsTable.getSelectedRow());
         }else {
            JOptionPane.showMessageDialog(null, "Please select a group to delete", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_removeGroupBTNMouseClicked

    private void removeGroupBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeGroupBTNMouseEntered
        changeFontColor(removeLabel, new Color(102,51,0));
        changeColor(removeGroupBTN, new Color(255,102,102));
    }//GEN-LAST:event_removeGroupBTNMouseEntered

    private void removeGroupBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeGroupBTNMouseExited
        changeFontColor(removeLabel, new Color(255,102,102));
        changeColor(removeGroupBTN, new Color(190, 49, 68));
    }//GEN-LAST:event_removeGroupBTNMouseExited

    private void createGroupBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGroupBTNMouseEntered
        changeFontColor(createLabel, new Color(64, 165, 120));
        changeColor(createGroupBTN, new Color(157, 222, 139));
    }//GEN-LAST:event_createGroupBTNMouseEntered

    private void createGroupBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGroupBTNMouseExited
        changeFontColor(createLabel, new Color(204,255,204));
        changeColor(createGroupBTN, new Color(64,165,120));
    }//GEN-LAST:event_createGroupBTNMouseExited

    private void createGroupBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGroupBTNMouseClicked
        new CreateGroupWindow().setVisible(true);
    }//GEN-LAST:event_createGroupBTNMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.Resources.RoundPanel createGroupBTN;
    private javax.swing.JLabel createLabel;
    private javax.swing.JLabel groupsPanelHeading;
    public static javax.swing.JTable groupsTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private View.Resources.RoundPanel removeGroupBTN;
    private javax.swing.JLabel removeLabel;
    private View.Resources.RoundPanel roundPanel1;
    private View.Resources.RoundPanel roundPanel2;
    private View.Resources.RoundPanel roundPanel3;
    private View.Resources.RoundPanel roundPanel4;
    private View.Resources.RoundPanel roundPanel5;
    private View.Resources.RoundPanel roundPanel6;
    private View.Resources.RoundPanel roundPanel7;
    private View.Resources.RoundPanel roundPanel8;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popupMenu;
    private JMenuItem addUsers;
    private JMenuItem removeUsers;
    private JMenuItem deleteGroup;
    private JComboBox<String> userDropdown;
    private JTextArea selectedUsersArea;
    private JPanel userPanel;
    
    //Setting action listeners for the menu items
    //Upon clicking on them relevant functions should get invoked
    private void setActionListners() {
        //Add users option
        addUsers.addActionListener((ActionEvent e) -> {
            int selectedRow = groupsTable.getSelectedRow();
            String groupId = (String) groupsTable.getValueAt(selectedRow, 0);
            
            int response = JOptionPane.showConfirmDialog(null, userPanel, "Add Users to Group " + groupId, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            //Current usernames in the group
            String currentUsers = groupsTable.getValueAt(selectedRow, 2).toString(); 
            //Checks the user response
            if(response == JOptionPane.OK_OPTION) {
                if (!selectedUsernames.isEmpty()) {
                    //Checks if the current value in the members column is empty
                    if(!currentUsers.isEmpty()) {
                        groupsTable.setValueAt(currentUsers + ", " + selectedUsersArea.getText(), selectedRow, 2);
                    }else {
                        groupsTable.setValueAt(selectedUsersArea.getText(), selectedRow, 2);
                    }
                    if(GroupController.addUsertoGroup(groupId, getSelectedUserIds(selectedUsernames))) {
                        JOptionPane.showMessageDialog(null, "User add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "User add unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No users selected.");
                }
            }
            //Clearing selected usernames list
            selectedUsernames.clear();
        });
        
        //Remove users option
        removeUsers.addActionListener(e -> {
            int selectedRow = groupsTable.getSelectedRow();
            String groupId = (String) groupsTable.getValueAt(selectedRow, 0);
            userDropdown = new JComboBox(getGroupMemberNamesArray());
            
            int response = JOptionPane.showConfirmDialog(null, userPanel, "Remove users from the " + groupId, JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE);
            String currentUsers = groupsTable.getValueAt(selectedRow, 2).toString(); 
            //Checks the user response
            if(response == JOptionPane.OK_OPTION) {
                if (!selectedUsernames.isEmpty()) {
                    //Checks if the current value in the members column is empty
                    if(!currentUsers.isEmpty()) {
                        groupsTable.setValueAt(currentUsers + ", " + selectedUsersArea.getText(), selectedRow, 2);
                    }else {
                        JOptionPane.showMessageDialog(null, "No users to remove!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(GroupController.removeUserFromGroup(groupId, getSelectedUserIds(selectedUsernames))) {
                        JOptionPane.showMessageDialog(null, "User removal successful! \nClick on the Groups menu to see the changes", "Success", 
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "User removal unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No users selected.");
                }
            }
            //Clearing selected usernames list
            selectedUsernames.clear();
        });
        
        //Delete group action
        deleteGroup.addActionListener(e -> {
            int selectedRow = groupsTable.getSelectedRow();
            String groupId = (String) groupsTable.getValueAt(selectedRow, 0);
            //Checks if table row is selected
            if(!groupId.isEmpty()) {
                deleteGroup(groupId);
                tableModel.removeRow(groupsTable.getSelectedRow());
             }
        });
    }
    
    //Gets a username arrays from the users list
    private String[] getUsernameArray() {
        // Convert list to array for JComboBox
        List<String> userNames = new ArrayList<>();
        for(UserModel user : users) {
            userNames.add(user.getUsername());
        }
        
        return userNames.toArray(new String[0]);
    }
    
    private String[] getGroupMemberNamesArray() {
        //Convert list to array for JComboBox
        List<String> groupMembers = new ArrayList<>();
        for(GroupModel group : groups) {
            for(UserModel user : group.getGroupMembers()) {
                groupMembers.add(user.getUsername());
            }
        }
        
        return groupMembers.toArray(new String[0]);
    }
    
    //Gets the respective userId by the username
    private List<String> getSelectedUserIds(List<String> usernames) {
        List<String> userIds = new ArrayList<>();
        
        for(UserModel user : users) {
            for(String username : usernames) {
                if(user.getUsername().equals(username)) {
                    userIds.add(user.getUserId());
                }
            }
        }
        
        return userIds;
    }
    
    private boolean deleteGroup(String groupId) {
        return GroupController.removeGroup(groupId);
    }
}
