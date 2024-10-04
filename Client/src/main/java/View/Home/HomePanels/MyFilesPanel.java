package View.Home.HomePanels;

import Controller.FileController;
import Controller.GroupController;
import Controller.UserController;
import Models.FileModel;
import Models.GroupModel;
import Models.UserModel;
import View.Home.FileUploadWindow;
import View.Home.Home;
import View.Home.UIMethods;
import View.Resources.CustomFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author benjamin
 */
public class MyFilesPanel extends javax.swing.JPanel implements UIMethods {
    
    private List<String> selectedUsernames;
    private List<FileModel> myFiles;
    private List<UserModel> users;
    private List<GroupModel> groups;
    private DefaultTableModel myFilesTableModel;
    
    public MyFilesPanel() {
        initComponents();
        selectedUsernames = new ArrayList<>();
        users = UserController.getAllUsers();
        groups = GroupController.getAllUserGroups();
        myFiles = FileController.getMyFiles(Home.user.getUserId());
        loadFonts();
        contructFileTable();
        createShareUserOption();
    }
    
    public void contructFileTable() {
        styleTable();
        loadDataToTable();
        createPopupMenu();
        addRightClickListener();
        
    }
    
    //Add a TableModelListener to detect cell edits
    private void addRightClickListener() {
        myFileTable.getModel().addTableModelListener((TableModelEvent e) -> {
            // Check if the event is an update
            if (e.getType() == TableModelEvent.UPDATE) {
                // Get the row and column of the changed cell
                int row = e.getFirstRow();
                int column = e.getColumn();
                
                // Get the new value
                String newName = myFileTable.getModel().getValueAt(row, column).toString();
                String fileId = myFileTable.getModel().getValueAt(row, 0).toString();
                
                // Update the database
                FileController.updateFileName(fileId, newName);
            }
        } 
        );
    }
    
    //Create right click context menu for the table
    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        downloadItem = new JMenuItem("Download");
        shareItem = new JMenuItem("Share");
        deleteItem = new JMenuItem("Delete");

        // Add menu items to the popup menu
        popupMenu.add(downloadItem);
        popupMenu.add(shareItem);
        popupMenu.add(deleteItem);
        
        //Set font of added menus
        downloadItem.setFont(CustomFont.formTextFieldFont);
        shareItem.setFont(CustomFont.formTextFieldFont);
        deleteItem.setFont(CustomFont.formTextFieldFont);
        
        
        // Add a mouse listener to detect right-clicks on the table
        myFileTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger() && myFileTable.getSelectedRow() != -1) {
                    // Select the row where the right-click occurred
                    int row = myFileTable.rowAtPoint(e.getPoint());
                    myFileTable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        // Add action listeners for the menu items
        setActionToMenu();
    }
    
    //Load data to the table
    private void loadDataToTable() {
        //Table model creation
        String[] columnNames = {"File Name", "Created By", "Shared With", "Size"};
        myFilesTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<Groups> groups
        for (FileModel file : myFiles) {
            Object[] row = { file.getFileName(), file.getOwner().getUsername(), file.sharedUsersToString(), file.getFileSize() };
            myFilesTableModel.addRow(row);
        }
        
        //Setting the table model
        myFileTable.setModel(myFilesTableModel);
        
    }
    
    private void setActionToMenu() {
        // Add action listeners for the menu items
        downloadItem.addActionListener(e -> {
            String fileId = getSelectedFileId();
            String fileName = (String) myFileTable.getValueAt(myFileTable.getSelectedRow(), 0);
            System.out.println("Downloading: " + fileId);
            
            if(FileController.downloadFile(fileId, 
                    openDownloadPathSelection(fileName))) {
                JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File download successful!"), "Download Success", JOptionPane.INFORMATION_MESSAGE); 
            } else {
                JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File download unsuccessful!"), "Download Failed", JOptionPane.ERROR_MESSAGE);
            }
            
        });

        shareItem.addActionListener(e -> {
            String fileId = getSelectedFileId();
            System.out.println("Sharing: " + fileId);
            selectedUsersArea.setText(getAlreadySharedWithUsers(fileId));
            
            int result = JOptionPane.showConfirmDialog(null, addUserPanel, "Share file " + fileId + " with users", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                    if (!selectedUsernames.isEmpty()) {
                        //Check if file share has been done from the backend
                        if(FileController.shareFileWithUser(fileId, selectedUsernames)) {
                            JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File share successful!"), "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File share unsuccessful!"), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No users selected.");
                    }
                }
        });

        deleteItem.addActionListener(e -> {
            String fileId = getSelectedFileId();
            System.out.println("Deleting: " + fileId);
            
            deleteFile(fileId);
        });

    }
    
    private Path openDownloadPathSelection(String fileName) {
        JFileChooser pathChooser = new JFileChooser();
        pathChooser.setDialogTitle("Select download location");
        pathChooser.setSelectedFile(new java.io.File(fileName));
        int userSelection = pathChooser.showSaveDialog(null);
        
        if(userSelection == JFileChooser.APPROVE_OPTION) {
            Path destinationPath = pathChooser.getSelectedFile().toPath();
            System.out.println("Saving file to: " + destinationPath);
            return destinationPath;
        }
        return null;
    }
    
    private String getAlreadySharedWithUsers(String selectedFileId) {
        for(FileModel file : myFiles) {
            if(file.getFileId().equals(selectedFileId)) {
               return file.sharedUsersToString(); 
            }else {
                return "This file is not shared";
            }
        }
        return null;
    }
    
    private void deleteFile(String fileId) {
        int response = JOptionPane.showConfirmDialog(null, setJOptionMessageLabel("Do want to remove "
                        + myFileTable.getModel().getValueAt(myFileTable.getSelectedRow(), 0).toString() + "?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
        //Checks if user confirms the file removal
        if(response == 0) { 
            //File delete indicator
            setProgressIndicator();
            if(FileController.deleteFile(fileId)) {
                JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File deleted successfully!"), "Success", JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel model = (DefaultTableModel) myFileTable.getModel();
                model.removeRow(myFileTable.getSelectedRow());
            }else {
                JOptionPane.showMessageDialog(null, setJOptionMessageLabel("File deleted unsuccessfully!"), "Error", JOptionPane.ERROR_MESSAGE);
            }
                
            
        }
    }
    
    private void setProgressIndicator() {
        SwingUtilities.invokeLater(() -> {
            // Create a JProgressBar
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);  // Show progress percentage
            progressBar.setIndeterminate(false); // Determinate progress bar

            // Create an option pane with the progress bar
            Object[] dialogContent = {setJOptionMessageLabel("File deleting, please wait..."), progressBar};
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
    
    private void createShareUserOption() {
        // Convert list to array for JComboBox
        String[] usernameArray = getUsernameArray();

        // Create components for the dialog
        userDropdown = new JComboBox<>(usernameArray);
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
                JOptionPane.showMessageDialog(null, setJOptionMessageLabel("User already selected."));
            }
        });

        // Create a panel to hold the dropdown and selected users display
        addUserPanel = new JPanel(new BorderLayout());
        addUserPanel.add(userDropdown, BorderLayout.NORTH);
        addUserPanel.add(new JScrollPane(selectedUsersArea), BorderLayout.CENTER);
        addUserPanel.setPreferredSize(new Dimension(500, 200));
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
        
        myFilesPanelHeading.setFont(CustomFont.panelHeadingFont);
        uploadBTN.setFont(CustomFont.formLabelFont);
        deleteBTN.setFont(CustomFont.formLabelFont);
        shareBTN.setFont(CustomFont.formLabelFont);
        myFileTable.setFont(CustomFont.tableRowFont);
        myFileTable.getTableHeader().setFont(CustomFont.tableHeaderFont);
        
    }
    
    //Styling the table
    private void styleTable() {
        JTableHeader header = myFileTable.getTableHeader();
        header.setBackground(new Color(0,29,61));
        header.setForeground(new Color(255, 255, 255));
        header.setPreferredSize(
                new Dimension(header.getWidth(), 40));
    }
    
    private String getSelectedFileId() {
        if(myFileTable.getSelectedRow() != -1) {
            System.out.println("Selected file: " + myFileTable.getModel().getValueAt(myFileTable.getSelectedRow(), 0).toString());
            String fileName = myFileTable.getModel().getValueAt(myFileTable.getSelectedRow(), 0).toString();
            
            for(FileModel file : myFiles) {
                if(file.getFileName().equals(fileName)) {
                    return file.getFileId();
                }
                break;
            }
            
        }else {
            JOptionPane.showMessageDialog(null, setJOptionMessageLabel("Select a file to remove"), "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return null;
    }
    
    private String[] getUsernameArray() {
        List<String> usernames = new ArrayList<>();
        
        for(UserModel user : users) {
            usernames.add(user.getUsername());
        }
        
        return usernames.toArray(new String[0]);
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
        roundPanel1 = new View.Resources.RoundPanel();
        roundPanel2 = new View.Resources.RoundPanel();
        roundPanel3 = new View.Resources.RoundPanel();
        myFilesPanelHeading = new javax.swing.JLabel();
        roundPanel4 = new View.Resources.RoundPanel();
        roundPanel5 = new View.Resources.RoundPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        deleteBTN = new View.Resources.RoundPanel();
        deleteLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        uploadBTN = new View.Resources.RoundPanel();
        uploadLabel = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        shareBTN = new View.Resources.RoundPanel();
        shareLabel = new javax.swing.JLabel();
        roundPanel6 = new View.Resources.RoundPanel();
        roundPanel7 = new View.Resources.RoundPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        roundPanel8 = new View.Resources.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myFileTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1080, 640));
        setMinimumSize(new java.awt.Dimension(1080, 640));
        setPreferredSize(new java.awt.Dimension(1080, 640));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(15, 610));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(1080, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
        jPanel2.getAccessibleContext().setAccessibleName("");

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(15, 610));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(1080, 15));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        add(jPanel4, java.awt.BorderLayout.PAGE_END);
        jPanel4.getAccessibleContext().setAccessibleName("");

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

        myFilesPanelHeading.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        myFilesPanelHeading.setText("My Files");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(myFilesPanelHeading)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(myFilesPanelHeading)
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(465, 100));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel6.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel7.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        deleteBTN.setBackground(new java.awt.Color(204, 0, 0));
        deleteBTN.setPreferredSize(new java.awt.Dimension(140, 40));
        deleteBTN.setRoundBottomLeft(28);
        deleteBTN.setRoundBottomRight(28);
        deleteBTN.setRoundTopLeft(28);
        deleteBTN.setRoundTopRight(28);
        deleteBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteBTNMouseExited(evt);
            }
        });
        deleteBTN.setLayout(new java.awt.BorderLayout());

        deleteLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        deleteLabel.setForeground(new java.awt.Color(255, 204, 204));
        deleteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteLabel.setText("Delete");
        deleteBTN.add(deleteLabel, java.awt.BorderLayout.CENTER);

        jPanel9.add(deleteBTN, java.awt.BorderLayout.LINE_END);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(20, 40));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel11, java.awt.BorderLayout.LINE_END);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        uploadBTN.setBackground(new java.awt.Color(34, 151, 153));
        uploadBTN.setPreferredSize(new java.awt.Dimension(140, 45));
        uploadBTN.setRoundBottomLeft(28);
        uploadBTN.setRoundBottomRight(28);
        uploadBTN.setRoundTopLeft(28);
        uploadBTN.setRoundTopRight(28);
        uploadBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uploadBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uploadBTNMouseExited(evt);
            }
        });
        uploadBTN.setLayout(new java.awt.BorderLayout());

        uploadLabel.setBackground(new java.awt.Color(255, 255, 255));
        uploadLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        uploadLabel.setForeground(new java.awt.Color(255, 255, 255));
        uploadLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uploadLabel.setText("Upload");
        uploadBTN.add(uploadLabel, java.awt.BorderLayout.CENTER);

        jPanel13.add(uploadBTN, java.awt.BorderLayout.LINE_END);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(20, 45));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel15, java.awt.BorderLayout.LINE_END);

        shareBTN.setBackground(new java.awt.Color(64, 165, 120));
        shareBTN.setRoundBottomLeft(28);
        shareBTN.setRoundBottomRight(28);
        shareBTN.setRoundTopLeft(28);
        shareBTN.setRoundTopRight(28);
        shareBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shareBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shareBTNMouseExited(evt);
            }
        });
        shareBTN.setLayout(new java.awt.BorderLayout());

        shareLabel.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        shareLabel.setForeground(new java.awt.Color(204, 255, 204));
        shareLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shareLabel.setText("Share");
        shareBTN.add(shareLabel, java.awt.BorderLayout.CENTER);

        jPanel14.add(shareBTN, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        roundPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

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
            .addGap(0, 500, Short.MAX_VALUE)
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
            .addGap(0, 500, Short.MAX_VALUE)
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
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        roundPanel7.add(roundPanel8, java.awt.BorderLayout.PAGE_END);

        myFileTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "File Name", "Owner", "Created", "Size"
            }
        ));
        myFileTable.setRowHeight(40);
        myFileTable.setSelectionBackground(new java.awt.Color(60, 194, 250));
        jScrollPane1.setViewportView(myFileTable);

        roundPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        roundPanel6.add(roundPanel7, java.awt.BorderLayout.CENTER);

        roundPanel1.add(roundPanel6, java.awt.BorderLayout.CENTER);

        add(roundPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBTNMouseClicked
        String selectedFileId = getSelectedFileId();
        //Checks if table row is selected
        if(!selectedFileId.isEmpty()) {
                deleteFile(selectedFileId);
        } else {
            JOptionPane.showMessageDialog(null, setJOptionMessageLabel("Select a file to remove"), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_deleteBTNMouseClicked

    private void deleteBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBTNMouseEntered
        changeFontColor(deleteLabel, new Color(102,51,0));
        changeColor(deleteBTN, new Color(255,102,102));
    }//GEN-LAST:event_deleteBTNMouseEntered

    private void deleteBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBTNMouseExited
        changeFontColor(deleteLabel, new Color(255,102,102));
        changeColor(deleteBTN, new Color(190, 49, 68));
    }//GEN-LAST:event_deleteBTNMouseExited

    private void uploadBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadBTNMouseClicked
        new FileUploadWindow().setVisible(true);
    }//GEN-LAST:event_uploadBTNMouseClicked

    private void uploadBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadBTNMouseEntered
        changeColor(uploadBTN, new Color(72, 207, 203));
    }//GEN-LAST:event_uploadBTNMouseEntered

    private void uploadBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadBTNMouseExited
        changeColor(uploadBTN, new Color(34, 151, 153));
    }//GEN-LAST:event_uploadBTNMouseExited

    private void shareBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shareBTNMouseEntered
        changeFontColor(shareLabel, new Color(64, 165, 120));
        changeColor(shareBTN, new Color(157, 222, 139));
    }//GEN-LAST:event_shareBTNMouseEntered

    private void shareBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shareBTNMouseExited
        changeFontColor(shareLabel, new Color(204,255,204));
        changeColor(shareBTN, new Color(64,165,120));
    }//GEN-LAST:event_shareBTNMouseExited

    private JLabel setJOptionMessageLabel(String message) {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(CustomFont.formTextFieldFont); 
        
        return messageLabel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.Resources.RoundPanel deleteBTN;
    private javax.swing.JLabel deleteLabel;
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
    private javax.swing.JTable myFileTable;
    private javax.swing.JLabel myFilesPanelHeading;
    private View.Resources.RoundPanel roundPanel1;
    private View.Resources.RoundPanel roundPanel2;
    private View.Resources.RoundPanel roundPanel3;
    private View.Resources.RoundPanel roundPanel4;
    private View.Resources.RoundPanel roundPanel5;
    private View.Resources.RoundPanel roundPanel6;
    private View.Resources.RoundPanel roundPanel7;
    private View.Resources.RoundPanel roundPanel8;
    private View.Resources.RoundPanel shareBTN;
    private javax.swing.JLabel shareLabel;
    private View.Resources.RoundPanel uploadBTN;
    private javax.swing.JLabel uploadLabel;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popupMenu;
    private JMenuItem downloadItem;
    private JMenuItem shareItem;
    private JMenuItem deleteItem;
    private JComboBox<String> userDropdown;
    private JTextArea selectedUsersArea;
    private JPanel addUserPanel;
    
}
