package View.Home.HomePanels;

import Controller.FileController;
import Controller.UserController;
import Model.FileModel;
import Model.UserModel;
import View.Home.Home;
import View.Home.UIMethods;
import View.Resources.CustomFont;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author benjamin
 */
public class SharedFiles extends javax.swing.JPanel implements UIMethods {

    private DefaultTableModel sharedWithMeModel;
    private DefaultTableModel sharedByMeModel;
    private List<FileModel> filesSharedWithMe;
    private List<FileModel> filesSharedByMe;
    private List<UserModel> users;
    private List<String> selectedUsernames;
    
    public SharedFiles() {
        initComponents();
        loadFonts();
        filesSharedWithMe = FileController.getSharedWithYouFiles(Home.user.getUserId());
        filesSharedByMe = FileController.getSharedByMeFiles(Home.user.getUserId());
        users = UserController.getAllUsers();
        selectedUsernames = new ArrayList<>();
        constructTable();
    }
    
    private void constructTable() {
        styleTable(sharedWithMeTable);
        styleTable(sharedByMeTable);
        loadDataToSharedWithMeTable();
        loadDataToSharedByMeTable();
        createPopupMenu(sharedWithMeTable);
        createPopupMenu(sharedByMeTable);
    }
    
    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(62, 62, 62));
        header.setForeground(new Color(255, 255, 255));
        header.setPreferredSize(
                new Dimension(header.getWidth(), 40));
        table.setFont(CustomFont.tableRowFont);
        table.getTableHeader().setFont(CustomFont.tableHeaderFont);
    }
    
    //Create right click context menu for the table
    private void createPopupMenu(JTable table) {
        popupMenu = new JPopupMenu();
        downloadItem = new JMenuItem("Download");
        shareItem = new JMenuItem("Share");
        deleteItem = new JMenuItem("Delete");

        // Add menu items to the popup menu
        popupMenu.add(downloadItem);
        
        //Delete and share option is available only on sharedByMeTable
        //**Only file owner can delete and share files
        if(table.equals(sharedByMeTable)) {
            popupMenu.add(deleteItem);
            popupMenu.add(shareItem);
        }
        
        // Add a mouse listener to detect right-clicks on the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger() && table.getSelectedRow() != -1) {
                    // Select the row where the right-click occurred
                    int row = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        // Add action listeners for the menu items
        setActionToMenu(table);
    }
    
    private void setActionToMenu(JTable table) {
        // Add action listeners for the menu items
        downloadItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            String fileId = (String) table.getValueAt(selectedRow, 0);
            System.out.println("Downloading: " + fileId);
            // Add download logic here
        });

        

        //Delete and share option is available only on sharedByMeTable
        //**Only file owner can delete and share files
        if(table.equals(sharedByMeTable)) {
            
            shareItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            String fileId = (String) table.getValueAt(selectedRow, 0);
            System.out.println("Sharing: " + fileId);
            selectedUsersArea.setText(getAlreadySharedWithUsers(fileId));
            
            int result = JOptionPane.showConfirmDialog(null, addUserPanel, "Share file " + fileId + " with users", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                    if (!selectedUsernames.isEmpty()) {
                        //Check if file share has been done from the backend
                        if(FileController.shareFileWithUser(fileId, getSelectedUserIds(selectedUsernames))) {
                            JOptionPane.showMessageDialog(null, "File share successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "File share unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No users selected.");
                    }
                }
            });
            
            deleteItem.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                String fileId = (String) table.getValueAt(selectedRow, 0);
                System.out.println("Deleting: " + fileId);

                deleteFile(fileId);
            });
        }
        

    }
    
    private void loadDataToSharedByMeTable() {
        //Table model creation
        String[] columnNames = {"File Name", "Shared With", "Size"};
        sharedByMeModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<FileModel> filesSharedByMe
        for (FileModel file : filesSharedByMe) {
            Object[] row = { file.getFileName(), file.getOwner().getUsername(), file.sharedUsersToString(), file.getFileSize() };
            sharedByMeModel.addRow(row);
        }
        
        //Setting the table model
        sharedByMeTable.setModel(sharedByMeModel);
    }
    
    private void loadDataToSharedWithMeTable() {
        //Table model creation
        String[] columnNames = {"File Name", "Created By", "Shared With", "Size"};
        sharedWithMeModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<FileModel> filesSharedWithMe
        for (FileModel file : filesSharedWithMe) {
            Object[] row = { file.getFileName(), file.getOwner().getUsername(), file.sharedUsersToString(), file.getFileSize() };
            sharedWithMeModel.addRow(row);
        }
        
        //Setting the table model
        sharedWithMeTable.setModel(sharedWithMeModel);
    }
    
    private void setProgressIndicator() {
        SwingUtilities.invokeLater(() -> {
            // Create a JProgressBar
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);  // Show progress percentage
            progressBar.setIndeterminate(false); // Determinate progress bar

            // Create an option pane with the progress bar
            Object[] dialogContent = {"File deleting, please wait...", progressBar};
            JOptionPane optionPane = new JOptionPane(dialogContent, JOptionPane.INFORMATION_MESSAGE, 
                                                      JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

            // Create and show the dialog containing the option pane
            JDialog dialog = optionPane.createDialog("Progress");
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent closing during operation
            dialog.setModal(true);

            // Start a background thread to simulate progress
            new Thread(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(50); // Simulate work being done
                    } catch (InterruptedException e) {
                        System.err.println(Arrays.toString(e.getStackTrace()));;
                    }
                    progressBar.setValue(i); // Update the progress bar
                }
                dialog.dispose(); // Close the dialog when done
            }).start();

            dialog.setVisible(true); // Show the progress dialog
        });
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
        
        sharedFilesPanelHeading.setFont(CustomFont.panelHeadingFont);
        section1Heading.setFont(CustomFont.sectionHeadingFont);
        section2Heading.setFont(CustomFont.sectionHeadingFont);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        roundPanel2 = new View.Resources.RoundPanel();
        roundPanel3 = new View.Resources.RoundPanel();
        sharedFilesPanelHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        roundPanel1 = new View.Resources.RoundPanel();
        roundPanel5 = new View.Resources.RoundPanel();
        section1Heading = new javax.swing.JLabel();
        roundPanel9 = new View.Resources.RoundPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        roundPanel10 = new View.Resources.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sharedWithMeTable = new javax.swing.JTable();
        roundPanel4 = new View.Resources.RoundPanel();
        roundPanel6 = new View.Resources.RoundPanel();
        section2Heading = new javax.swing.JLabel();
        roundPanel11 = new View.Resources.RoundPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        roundPanel12 = new View.Resources.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sharedByMeTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(240, 240, 240));
        jPanel10.setPreferredSize(new java.awt.Dimension(1108, 15));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel10, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(240, 240, 240));
        jPanel11.setPreferredSize(new java.awt.Dimension(1108, 15));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel11, java.awt.BorderLayout.PAGE_END);

        jPanel12.setBackground(new java.awt.Color(240, 240, 240));
        jPanel12.setPreferredSize(new java.awt.Dimension(15, 621));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );

        add(jPanel12, java.awt.BorderLayout.LINE_START);

        jPanel13.setBackground(new java.awt.Color(240, 240, 240));
        jPanel13.setPreferredSize(new java.awt.Dimension(15, 621));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );

        add(jPanel13, java.awt.BorderLayout.LINE_END);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new java.awt.BorderLayout());

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setRoundBottomLeft(10);
        roundPanel2.setRoundBottomRight(10);
        roundPanel2.setRoundTopLeft(10);
        roundPanel2.setRoundTopRight(10);
        roundPanel2.setLayout(new java.awt.BorderLayout());

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setPreferredSize(new java.awt.Dimension(300, 100));
        roundPanel3.setRoundBottomLeft(10);
        roundPanel3.setRoundBottomRight(10);
        roundPanel3.setRoundTopLeft(10);
        roundPanel3.setRoundTopRight(10);

        sharedFilesPanelHeading.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        sharedFilesPanelHeading.setText("Shared Files");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(sharedFilesPanelHeading)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(sharedFilesPanelHeading)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        roundPanel2.add(roundPanel3, java.awt.BorderLayout.LINE_START);

        jPanel1.add(roundPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(1078, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1570, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));
        jPanel4.setLayout(new java.awt.BorderLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setPreferredSize(new java.awt.Dimension(810, 501));
        roundPanel1.setRoundBottomLeft(10);
        roundPanel1.setRoundBottomRight(10);
        roundPanel1.setRoundTopLeft(10);
        roundPanel1.setRoundTopRight(10);
        roundPanel1.setLayout(new java.awt.BorderLayout());

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel5.setPreferredSize(new java.awt.Dimension(530, 50));
        roundPanel5.setRoundTopLeft(10);
        roundPanel5.setRoundTopRight(10);

        section1Heading.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        section1Heading.setText("With you");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(section1Heading)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(section1Heading)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        roundPanel1.add(roundPanel5, java.awt.BorderLayout.PAGE_START);

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel9.setRoundBottomLeft(10);
        roundPanel9.setRoundBottomRight(10);
        roundPanel9.setLayout(new java.awt.BorderLayout());

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
            .addGap(0, 690, Short.MAX_VALUE)
        );

        roundPanel9.add(jPanel19, java.awt.BorderLayout.LINE_START);

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
            .addGap(0, 690, Short.MAX_VALUE)
        );

        roundPanel9.add(jPanel20, java.awt.BorderLayout.LINE_END);

        roundPanel10.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel10.setPreferredSize(new java.awt.Dimension(510, 10));
        roundPanel10.setRoundBottomLeft(10);
        roundPanel10.setRoundBottomRight(10);

        javax.swing.GroupLayout roundPanel10Layout = new javax.swing.GroupLayout(roundPanel10);
        roundPanel10.setLayout(roundPanel10Layout);
        roundPanel10Layout.setHorizontalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel9.add(roundPanel10, java.awt.BorderLayout.PAGE_END);

        sharedWithMeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "File Id", "File Name", "Created", "Owner", "Size"
            }
        ));
        sharedWithMeTable.setRowHeight(40);
        sharedWithMeTable.setSelectionBackground(new java.awt.Color(72, 207, 203));
        jScrollPane1.setViewportView(sharedWithMeTable);

        roundPanel9.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        roundPanel1.add(roundPanel9, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundPanel1, java.awt.BorderLayout.LINE_START);

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel4.setPreferredSize(new java.awt.Dimension(810, 501));
        roundPanel4.setRoundBottomLeft(10);
        roundPanel4.setRoundBottomRight(10);
        roundPanel4.setRoundTopLeft(10);
        roundPanel4.setRoundTopRight(10);
        roundPanel4.setLayout(new java.awt.BorderLayout());

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setPreferredSize(new java.awt.Dimension(530, 50));
        roundPanel6.setRoundTopLeft(10);
        roundPanel6.setRoundTopRight(10);

        section2Heading.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        section2Heading.setText("By you");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(section2Heading)
                .addContainerGap(728, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(section2Heading)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        roundPanel4.add(roundPanel6, java.awt.BorderLayout.PAGE_START);

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel11.setRoundBottomLeft(10);
        roundPanel11.setRoundBottomRight(10);
        roundPanel11.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(10, 225));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        roundPanel11.add(jPanel21, java.awt.BorderLayout.LINE_START);

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
            .addGap(0, 690, Short.MAX_VALUE)
        );

        roundPanel11.add(jPanel22, java.awt.BorderLayout.LINE_END);

        roundPanel12.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel12.setPreferredSize(new java.awt.Dimension(510, 10));
        roundPanel12.setRoundBottomLeft(10);
        roundPanel12.setRoundBottomRight(10);

        javax.swing.GroupLayout roundPanel12Layout = new javax.swing.GroupLayout(roundPanel12);
        roundPanel12.setLayout(roundPanel12Layout);
        roundPanel12Layout.setHorizontalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        roundPanel12Layout.setVerticalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel11.add(roundPanel12, java.awt.BorderLayout.PAGE_END);

        sharedByMeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "File Id", "File Name", "Created", "Shared with", "Size"
            }
        ));
        sharedByMeTable.setRowHeight(40);
        sharedByMeTable.setSelectionBackground(new java.awt.Color(72, 207, 203));
        jScrollPane2.setViewportView(sharedByMeTable);

        roundPanel11.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        roundPanel4.add(roundPanel11, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundPanel4, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private View.Resources.RoundPanel roundPanel1;
    private View.Resources.RoundPanel roundPanel10;
    private View.Resources.RoundPanel roundPanel11;
    private View.Resources.RoundPanel roundPanel12;
    private View.Resources.RoundPanel roundPanel2;
    private View.Resources.RoundPanel roundPanel3;
    private View.Resources.RoundPanel roundPanel4;
    private View.Resources.RoundPanel roundPanel5;
    private View.Resources.RoundPanel roundPanel6;
    private View.Resources.RoundPanel roundPanel9;
    private javax.swing.JLabel section1Heading;
    private javax.swing.JLabel section2Heading;
    private javax.swing.JTable sharedByMeTable;
    private javax.swing.JLabel sharedFilesPanelHeading;
    private javax.swing.JTable sharedWithMeTable;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popupMenu;
    private JMenuItem downloadItem;
    private JMenuItem shareItem;
    private JMenuItem deleteItem;
    private JComboBox<String> userDropdown;
    private JTextArea selectedUsersArea;
    private JPanel addUserPanel;
    
    
    
    private String getAlreadySharedWithUsers(String selectedFileId) {
        for(FileModel file : filesSharedByMe) {
            if(file.getFileId().equals(selectedFileId)) {
               return file.sharedUsersToString(); 
            }else {
                return "This file is not shared";
            }
        }
        return null;
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
    
    private void deleteFile(String fileId) {
        int response = JOptionPane.showConfirmDialog(null, "Do want to remove "
                        + sharedByMeTable.getModel().getValueAt(sharedByMeTable.getSelectedRow(), 0).toString() + "?", "Warning!", JOptionPane.OK_CANCEL_OPTION);
        //Checks if user confirms the file removal
        if(response == 0) {
            //File delete indicator
            setProgressIndicator();
            if(FileController.deleteFile(fileId)) {
                JOptionPane.showMessageDialog(null, "File deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel model = (DefaultTableModel) sharedByMeTable.getModel();
                model.removeRow(sharedByMeTable.getSelectedRow());
            }else {
                JOptionPane.showMessageDialog(null, "File delete unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
            }
                
            
        }
    }
}
