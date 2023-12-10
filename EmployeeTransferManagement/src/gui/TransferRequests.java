package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransferRequests extends javax.swing.JFrame {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rsTPro;
    PreparedStatement psTPro;
    ResultSet rsTDep;
    PreparedStatement psTDep;
    ResultSet rsTPos;
    PreparedStatement psTPos;
    ResultSet rsStatus;
    PreparedStatement psStatus;
    DefaultTableModel tblModel;
    Vector row;
    server.DBHelper db = new server.DBHelper();
    
   public TransferRequests(String empNumber) {
        initComponents();
        ImageIcon img = new ImageIcon("image//request.png");
        this.setIconImage(img.getImage());
        lblEmpNumber.setText(empNumber);
        lblEmpNumber.setVisible(false);
        
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("ID");
        tblModel.addColumn("Name");
        tblModel.addColumn("Date Of Birth");
        tblModel.addColumn("Current Project");
        tblModel.addColumn("Transfer Project");
        tblModel.addColumn("Current DepartMent");
        tblModel.addColumn("Transfer DepartMent");
        tblModel.addColumn("Current Position");
        tblModel.addColumn("Transfer Position");
        tblModel.addColumn("Allowance");
        tblModel.addColumn("Reason");
        tblModel.addColumn("Relieving Date");
        tblModel.addColumn("Joining Date");
        tblModel.addColumn("Posting Date");
        tblModel.addColumn("Approval Date");
        tblModel.addColumn("Status");
        tblTransferRequests.setModel(tblModel);
        
        LoadTable();
        loadData();
        cbTPro.setSelectedIndex(-1);
        cbTDep.setSelectedIndex(-1);
        cbTPos.setSelectedIndex(-1);
        cbStatus.setSelectedIndex(-1);
    }

    public void LoadTable() {
        try {
            con = db.getCon();
            ps = con.prepareStatement("SELECT req.transferRequestNumber, e.fullname, e.dateOfBirth, req.cProject, tPro.transferProjectName, req.cDepartment, Dep.transferDepartmentName, req.cPosition, Pos.transferPositionName, Pos.allowanceLevel, req.reason, req.transferRelievingDate, req.transferJoiningDate, req.dateOfPosting, req.approvalDate, req.status FROM TransferRequests AS req INNER JOIN Employee AS e ON req.empNumber = e.empNumber INNER JOIN TransferProject AS tPro ON req.transferProjectNumber = tPro.transferProjectNumber INNER JOIN TransferDepartment AS Dep ON Req.transferDepartmentNumber = Dep.transferDepartmentNumber INNER JOIN TransferPosition AS Pos ON req.transferPositionNumber = Pos.transferPositionNumber WHERE req.empNumber=?");
            ps.setString(1, lblEmpNumber.getText());
            rs = ps.executeQuery();
            row = new Vector();
            
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                tblModel.addRow(row);
            }
            if (row.isEmpty()) {
                tblModel.addRow(row);
            }
            tblTransferRequests.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:: SQLException");
            Logger.getLogger(Approval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadData() {
        try {
            con = db.getCon();
            cbTPro.removeAllItems();
            cbTDep.removeAllItems();
            cbTPos.removeAllItems();
            cbStatus.removeAllItems();
            
            psTPro = con.prepareStatement("SELECT transferProjectName FROM TransferProject");
            rsTPro = psTPro.executeQuery();
            psTDep = con.prepareStatement("SELECT transferDepartmentName FROM TransferDepartment");
            rsTDep = psTDep.executeQuery();
            psTPos = con.prepareStatement("SELECT transferPositionName FROM TransferPosition");
            rsTPos = psTPos.executeQuery();
            
            while (rsTPro.next()) {
                cbTPro.addItem(rsTPro.getString(1));
            }
            while (rsTDep.next()) {
                cbTDep.addItem(rsTDep.getString(1));
            }
            while (rsTPos.next()) {
                cbTPos.addItem(rsTPos.getString(1));
            }
            cbStatus.addItem("Waiting Approval");
            cbStatus.addItem("Approved");
            cbStatus.addItem("Denied");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error:: SQLException !");
            Logger.getLogger(RequestATransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransferRequests = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        lbTimeError = new javax.swing.JLabel();
        lbDateError = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbTPro = new javax.swing.JComboBox<>();
        cbTPos = new javax.swing.JComboBox<>();
        cbTDep = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        lblEmpNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transfer Requests");

        tblTransferRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTransferRequests.setAutoscrolls(false);
        tblTransferRequests.setFocusable(false);
        tblTransferRequests.setOpaque(false);
        tblTransferRequests.setRequestFocusEnabled(false);
        tblTransferRequests.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblTransferRequests);

        btnReset.setText("Refresh");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Transfer Project");

        jLabel2.setText("Transfer Department");

        jLabel5.setText("Transfer Position");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Search for Transfer Requests Information");

        cbTPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTPos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTDep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblStatus.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEmpNumber))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTPro, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTDep, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTimeError)
                            .addComponent(lbDateError))
                        .addGap(357, 357, 357))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbTPos, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStatus)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(82, 82, 82))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addComponent(lblEmpNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(cbTPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbTimeError)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStatus)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbTDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDateError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbTPro.setSelectedIndex(-1);
        cbTDep.setSelectedIndex(-1);
        cbTPos.setSelectedIndex(-1);
        cbStatus.setSelectedIndex(-1);
        tblModel.getDataVector().removeAllElements();
        LoadTable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (cbTPro.getSelectedItem() == null && cbTDep.getSelectedItem() == null && cbTPos.getSelectedItem() == null && cbStatus.getSelectedItem() == null) {
            tblModel.getDataVector().removeAllElements();
            LoadTable();
        } else {
            tblModel.getDataVector().removeAllElements();
            try {
            con = db.getCon();
            ps = con.prepareStatement("SELECT req.transferRequestNumber, e.fullname, e.dateOfBirth, req.cProject, tPro.transferProjectName, req.cDepartment, Dep.transferDepartmentName, req.cPosition, Pos.transferPositionName, Pos.allowanceLevel, req.reason, req.transferRelievingDate, req.transferJoiningDate, req.dateOfPosting, req.approvalDate, req.status FROM TransferRequests AS req INNER JOIN Employee AS e ON req.empNumber = e.empNumber INNER JOIN TransferProject AS tPro ON req.transferProjectNumber = tPro.transferProjectNumber INNER JOIN TransferDepartment AS Dep ON Req.transferDepartmentNumber = Dep.transferDepartmentNumber INNER JOIN TransferPosition AS Pos ON req.transferPositionNumber = Pos.transferPositionNumber WHERE (tPro.transferProjectName=? OR Dep.transferDepartmentName=? OR Pos.transferPositionName=? OR req.status=?) AND req.empNumber=?");
            ps.setString(1, (String) cbTPro.getSelectedItem());
            ps.setString(2, (String) cbTDep.getSelectedItem());
            ps.setString(3, (String) cbTPos.getSelectedItem());
            ps.setString(4, (String) cbStatus.getSelectedItem());
            ps.setString(5, lblEmpNumber.getText());
            rs = ps.executeQuery();
            row = new Vector();
            
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                tblModel.addRow(row);
            }
            if (row.isEmpty()) {
                tblModel.addRow(row);
            }
            tblTransferRequests.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:: SQLException");
            Logger.getLogger(Approval.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JComboBox<String> cbTDep;
    private javax.swing.JComboBox<String> cbTPos;
    private javax.swing.JComboBox<String> cbTPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDateError;
    private javax.swing.JLabel lbTimeError;
    private javax.swing.JLabel lblEmpNumber;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblTransferRequests;
    // End of variables declaration//GEN-END:variables
}
