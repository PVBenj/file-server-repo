package View.Home.HomePanels;

import Controller.ActivityLoggerController;
import Controller.FileController;
import Controller.GroupController;
import Controller.UserController;
import Models.ActivityLogger;
import Models.FileModel;
import Models.GroupModel;
import Models.UserModel;
import View.Home.Home;
import View.Home.UIMethods;
import View.Resources.CustomFont;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author benjamin
 */
public final class AdminDashboardPanel extends javax.swing.JPanel implements UIMethods {
    
    private List<UserModel> users;
    private List<GroupModel> groups;
    private List<ActivityLogger> activities;
    private List<FileModel> recentFiles;
    private DefaultTableModel userGroupsModel;
    private DefaultTableModel recentActivityModel;
    
    public AdminDashboardPanel() {
        initComponents();
        loadFonts();
        users = UserController.getAllUsers();
        groups = GroupController.getAllUserGroups();
        activities = ActivityLoggerController.getAdminActivities();
        recentFiles = FileController.getRecentFiles(Home.user.getUserId());
        constructTables();
        setSectionCountLabels();
    }
    
    private void constructTables() {
        styleTable(userGroupsTable);
        styleTable(recentActivityTable);
        loadDataToUserGroupsTable();
        loadDataToActvityTable();
    }
    
    
    //Style the tables
    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0,29,61));
        header.setForeground(new Color(255, 255, 255));
        header.setPreferredSize(
                new Dimension(header.getWidth(), 40));
        table.setFont(CustomFont.tableRowFont);
        table.getTableHeader().setFont(CustomFont.tableHeaderFont);
    }
    
    //Load data to the userGroupsTable
    private void loadDataToUserGroupsTable() {
        //Table model creation
        String[] columnNames = {"Group ID", "Group Name", "Members", "Created By"};
        userGroupsModel = new DefaultTableModel(columnNames, 0);
        
        if(groups != null) {
            // Add rows from List<Groups> groups
            for (GroupModel group : groups) {
                Object[] row = { group.getGroupId(), group.getGroupName(), group.groupMembersToString(), group.getGroupOwner().getFirstName() };
                userGroupsModel.addRow(row);
            }
            
            //Setting the table model
            userGroupsTable.setModel(userGroupsModel);  
        }else {
            System.err.println("No data in groups list!");
        }
        
        
        
        
        
    }
    
    //Load data to recentActivityTable
    private void loadDataToActvityTable() {
        String[] columnNames = {"Username", "Detail", "On"};
        recentActivityModel = new DefaultTableModel(columnNames, 0);
        
        if(activities != null) {
            // Add rows from List<ActivityLogger> activities
            for (ActivityLogger activity : activities) {
                Object[] row = { activity.getUserName(), activity.getDetails(), activity.getDateAndTime() };
                recentActivityModel.addRow(row);
            }
            //Setting the table model
            recentActivityTable.setModel(recentActivityModel);
        }else {
            System.err.println("No data in activities list!");
        }
        
        
        
        
    }
    
    private void setSectionCountLabels() {
        if(users != null && groups != null && recentFiles != null) {
            userNoLabel.setText(String.valueOf(users.size()));
            groupNoLabel.setText(String.valueOf(groups.size()));
            recentNoLabel.setText(String.valueOf(recentFiles.size()));
        } else {
            userNoLabel.setText("00");
            groupNoLabel.setText("00");
            recentNoLabel.setText("00");
        }
        
    }
    
    
    @Override
    public void changeColor(JPanel hover, Color myColor) {
        hover.setBackground(myColor);
    }

    @Override
    public void loadFonts() {
        dashboardHeading.setFont(CustomFont.panelHeadingFont);
        section1Heading.setFont(CustomFont.sectionHeadingFont);
        section2Heading.setFont(CustomFont.sectionHeadingFont);
        section3Heading.setFont(CustomFont.sectionHeadingFont);
        recentActivityHeading.setFont(CustomFont.sectionHeadingFont);
        recentUploadsHeading.setFont(CustomFont.sectionHeadingFont);
        userNoLabel.setFont(CustomFont.panelNumberLabel);
        groupNoLabel.setFont(CustomFont.panelNumberLabel);
        recentNoLabel.setFont(CustomFont.panelNumberLabel);
    }

    @Override
    public void changeFontColor(JLabel text, Color myColor) {
        text.setForeground(myColor);
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
        dashboardHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        section1Panel = new View.Resources.RoundPanel();
        section1Heading = new javax.swing.JLabel();
        userNoLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        section2Panel = new View.Resources.RoundPanel();
        section2Heading = new javax.swing.JLabel();
        groupNoLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        section3Panel = new View.Resources.RoundPanel();
        section3Heading = new javax.swing.JLabel();
        recentNoLabel = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        recentActivitySection = new View.Resources.RoundPanel();
        roundPanel1 = new View.Resources.RoundPanel();
        recentActivityHeading = new javax.swing.JLabel();
        roundPanel3 = new View.Resources.RoundPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        roundPanel4 = new View.Resources.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recentActivityTable = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        recentUploadsSection = new View.Resources.RoundPanel();
        roundPanel2 = new View.Resources.RoundPanel();
        recentUploadsHeading = new javax.swing.JLabel();
        roundPanel5 = new View.Resources.RoundPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        roundPanel6 = new View.Resources.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userGroupsTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(245, 245, 245));
        setMaximumSize(new java.awt.Dimension(1080, 640));
        setMinimumSize(new java.awt.Dimension(1080, 640));
        setPreferredSize(new java.awt.Dimension(1080, 640));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(879, 80));

        dashboardHeading.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        dashboardHeading.setForeground(new java.awt.Color(62, 62, 62));
        dashboardHeading.setText("Admin Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(dashboardHeading)
                .addContainerGap(1327, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(dashboardHeading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(879, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(15, 397));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(15, 397));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );

        add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setBackground(new java.awt.Color(240, 240, 240));
        jPanel5.setToolTipText("");
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(240, 240, 240));
        jPanel6.setPreferredSize(new java.awt.Dimension(849, 150));
        jPanel6.setLayout(new java.awt.BorderLayout());

        section1Panel.setBackground(new java.awt.Color(255, 255, 255));
        section1Panel.setPreferredSize(new java.awt.Dimension(300, 150));
        section1Panel.setRoundBottomLeft(10);
        section1Panel.setRoundBottomRight(10);
        section1Panel.setRoundTopLeft(10);
        section1Panel.setRoundTopRight(10);

        section1Heading.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        section1Heading.setText("Users");

        userNoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        userNoLabel.setText("00");

        javax.swing.GroupLayout section1PanelLayout = new javax.swing.GroupLayout(section1Panel);
        section1Panel.setLayout(section1PanelLayout);
        section1PanelLayout.setHorizontalGroup(
            section1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section1PanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(section1Heading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, section1PanelLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(userNoLabel)
                .addGap(124, 124, 124))
        );
        section1PanelLayout.setVerticalGroup(
            section1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section1PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(section1Heading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userNoLabel)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel6.add(section1Panel, java.awt.BorderLayout.LINE_START);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(240, 240, 240));
        jPanel8.setPreferredSize(new java.awt.Dimension(350, 150));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel9.setLayout(new java.awt.BorderLayout());

        section2Panel.setBackground(new java.awt.Color(255, 255, 255));
        section2Panel.setPreferredSize(new java.awt.Dimension(300, 150));
        section2Panel.setRoundBottomLeft(10);
        section2Panel.setRoundBottomRight(10);
        section2Panel.setRoundTopLeft(10);
        section2Panel.setRoundTopRight(10);

        section2Heading.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        section2Heading.setText("Groups");

        groupNoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        groupNoLabel.setText("00");

        javax.swing.GroupLayout section2PanelLayout = new javax.swing.GroupLayout(section2Panel);
        section2Panel.setLayout(section2PanelLayout);
        section2PanelLayout.setHorizontalGroup(
            section2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section2PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(section2Heading)
                .addContainerGap(222, Short.MAX_VALUE))
            .addGroup(section2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(section2PanelLayout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(groupNoLabel)
                    .addContainerGap(123, Short.MAX_VALUE)))
        );
        section2PanelLayout.setVerticalGroup(
            section2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section2PanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(section2Heading)
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(section2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(section2PanelLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(groupNoLabel)
                    .addContainerGap(47, Short.MAX_VALUE)))
        );

        jPanel9.add(section2Panel, java.awt.BorderLayout.LINE_START);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(240, 240, 240));
        jPanel11.setPreferredSize(new java.awt.Dimension(350, 150));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel11, java.awt.BorderLayout.LINE_START);

        jPanel12.setBackground(new java.awt.Color(240, 240, 240));
        jPanel12.setLayout(new java.awt.BorderLayout());

        section3Panel.setBackground(new java.awt.Color(255, 255, 255));
        section3Panel.setPreferredSize(new java.awt.Dimension(300, 150));
        section3Panel.setRoundBottomLeft(10);
        section3Panel.setRoundBottomRight(10);
        section3Panel.setRoundTopLeft(10);
        section3Panel.setRoundTopRight(10);

        section3Heading.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        section3Heading.setText("Recently Uploaded");

        recentNoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        recentNoLabel.setText("00");

        javax.swing.GroupLayout section3PanelLayout = new javax.swing.GroupLayout(section3Panel);
        section3Panel.setLayout(section3PanelLayout);
        section3PanelLayout.setHorizontalGroup(
            section3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section3PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(section3Heading)
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(section3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(section3PanelLayout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(recentNoLabel)
                    .addContainerGap(123, Short.MAX_VALUE)))
        );
        section3PanelLayout.setVerticalGroup(
            section3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(section3PanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(section3Heading)
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(section3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(section3PanelLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(recentNoLabel)
                    .addContainerGap(47, Short.MAX_VALUE)))
        );

        jPanel12.add(section3Panel, java.awt.BorderLayout.LINE_START);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel14.setBackground(new java.awt.Color(240, 240, 240));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(240, 240, 240));
        jPanel13.setPreferredSize(new java.awt.Dimension(1000, 30));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1570, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel15.setBackground(new java.awt.Color(240, 240, 240));
        jPanel15.setToolTipText("");
        jPanel15.setLayout(new java.awt.BorderLayout());

        recentActivitySection.setBackground(new java.awt.Color(255, 255, 255));
        recentActivitySection.setPreferredSize(new java.awt.Dimension(770, 385));
        recentActivitySection.setRoundBottomLeft(10);
        recentActivitySection.setRoundBottomRight(10);
        recentActivitySection.setRoundTopLeft(10);
        recentActivitySection.setRoundTopRight(10);
        recentActivitySection.setLayout(new java.awt.BorderLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setToolTipText("");
        roundPanel1.setPreferredSize(new java.awt.Dimension(0, 60));
        roundPanel1.setRoundTopLeft(10);
        roundPanel1.setRoundTopRight(10);

        recentActivityHeading.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        recentActivityHeading.setText("Recent Activity");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(recentActivityHeading)
                .addContainerGap(636, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(recentActivityHeading)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        recentActivitySection.add(roundPanel1, java.awt.BorderLayout.PAGE_START);

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setRoundBottomLeft(10);
        roundPanel3.setRoundBottomRight(10);
        roundPanel3.setLayout(new java.awt.BorderLayout());

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
            .addGap(0, 555, Short.MAX_VALUE)
        );

        roundPanel3.add(jPanel19, java.awt.BorderLayout.LINE_START);

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
            .addGap(0, 555, Short.MAX_VALUE)
        );

        roundPanel3.add(jPanel20, java.awt.BorderLayout.LINE_END);

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel4.setPreferredSize(new java.awt.Dimension(510, 10));
        roundPanel4.setRoundBottomLeft(10);
        roundPanel4.setRoundBottomRight(10);

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel3.add(roundPanel4, java.awt.BorderLayout.PAGE_END);

        recentActivityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Details", "On"
            }
        ));
        recentActivityTable.setEnabled(false);
        recentActivityTable.setRowHeight(40);
        recentActivityTable.setSelectionBackground(new java.awt.Color(60, 194, 250));
        jScrollPane1.setViewportView(recentActivityTable);

        roundPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        recentActivitySection.add(roundPanel3, java.awt.BorderLayout.CENTER);

        jPanel15.add(recentActivitySection, java.awt.BorderLayout.LINE_START);

        jPanel18.setBackground(new java.awt.Color(240, 240, 240));
        jPanel18.setToolTipText("");
        jPanel18.setPreferredSize(new java.awt.Dimension(810, 402));
        jPanel18.setLayout(new java.awt.BorderLayout());

        recentUploadsSection.setBackground(new java.awt.Color(255, 255, 255));
        recentUploadsSection.setPreferredSize(new java.awt.Dimension(770, 402));
        recentUploadsSection.setRoundBottomLeft(10);
        recentUploadsSection.setRoundBottomRight(10);
        recentUploadsSection.setRoundTopLeft(10);
        recentUploadsSection.setRoundTopRight(10);
        recentUploadsSection.setLayout(new java.awt.BorderLayout());

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setToolTipText("");
        roundPanel2.setPreferredSize(new java.awt.Dimension(0, 60));
        roundPanel2.setRoundTopLeft(10);
        roundPanel2.setRoundTopRight(10);

        recentUploadsHeading.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        recentUploadsHeading.setText("User Groups");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(recentUploadsHeading)
                .addContainerGap(653, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(recentUploadsHeading)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        recentUploadsSection.add(roundPanel2, java.awt.BorderLayout.PAGE_START);

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel5.setRoundBottomLeft(10);
        roundPanel5.setRoundBottomRight(10);
        roundPanel5.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(10, 225));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        roundPanel5.add(jPanel22, java.awt.BorderLayout.LINE_START);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(10, 225));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        roundPanel5.add(jPanel23, java.awt.BorderLayout.LINE_END);

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setPreferredSize(new java.awt.Dimension(510, 10));
        roundPanel6.setRoundBottomLeft(10);
        roundPanel6.setRoundBottomRight(10);

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel5.add(roundPanel6, java.awt.BorderLayout.PAGE_END);

        userGroupsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Group Id", "Group Name", "Members", "Created by"
            }
        ));
        userGroupsTable.setEnabled(false);
        userGroupsTable.setRowHeight(40);
        userGroupsTable.setSelectionBackground(new java.awt.Color(60, 194, 250));
        jScrollPane2.setViewportView(userGroupsTable);

        roundPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        recentUploadsSection.add(roundPanel5, java.awt.BorderLayout.CENTER);

        jPanel18.add(recentUploadsSection, java.awt.BorderLayout.LINE_END);

        jPanel15.add(jPanel18, java.awt.BorderLayout.EAST);

        jPanel14.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel14, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboardHeading;
    private javax.swing.JLabel groupNoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel recentActivityHeading;
    private View.Resources.RoundPanel recentActivitySection;
    private javax.swing.JTable recentActivityTable;
    private javax.swing.JLabel recentNoLabel;
    private javax.swing.JLabel recentUploadsHeading;
    private View.Resources.RoundPanel recentUploadsSection;
    private View.Resources.RoundPanel roundPanel1;
    private View.Resources.RoundPanel roundPanel2;
    private View.Resources.RoundPanel roundPanel3;
    private View.Resources.RoundPanel roundPanel4;
    private View.Resources.RoundPanel roundPanel5;
    private View.Resources.RoundPanel roundPanel6;
    private javax.swing.JLabel section1Heading;
    private View.Resources.RoundPanel section1Panel;
    private javax.swing.JLabel section2Heading;
    private View.Resources.RoundPanel section2Panel;
    private javax.swing.JLabel section3Heading;
    private View.Resources.RoundPanel section3Panel;
    private javax.swing.JTable userGroupsTable;
    private javax.swing.JLabel userNoLabel;
    // End of variables declaration//GEN-END:variables

}
