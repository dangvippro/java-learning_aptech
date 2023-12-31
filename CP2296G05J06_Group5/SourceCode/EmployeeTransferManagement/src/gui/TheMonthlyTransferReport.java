package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class TheMonthlyTransferReport extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel tblModel;
    Vector vtr;
    server.DBHelper db = new server.DBHelper();
    Connection cn = db.getCon();

    /**
     * Creates new form Monthly
     */
    public TheMonthlyTransferReport() {
        initComponents();
        setLocationRelativeTo(null);
        image();
        btnDelete.setEnabled(false);
        LocalDate date = LocalDate.now();
        lblDate.setText(date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear());
        
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("ID");
        tblModel.addColumn("Month");
        tblModel.addColumn("Transfer Department");
        tblModel.addColumn("Transfer Project");
        tblModel.addColumn("Transfer Position");
        tblModel.addColumn("Employee");
        tbMonthlyReport.setModel(tblModel);
        loadTable();
        lblLogo.setSize(100, 100);
        new SetImage().setImageLable(lblLogo, "image//logo1.png");
    }

    public void image(){
        String refesh = "image//refresh.png";
        String search = "image//search.png";
        String delete = "image//deleted.png"; 
        btnSearch.setSize(20, 20);
        new SetImage().setImageButton(btnSearch, search);
        btnReset.setSize(20, 20);
        new SetImage().setImageButton(btnReset, refesh);
        btnDelete.setSize(20, 20);
        new SetImage().setImageButton(btnDelete, delete);
        ImageIcon img = new ImageIcon("image//MonthlyTransferReport.png");
        this.setIconImage(img.getImage());
    }
    
    public void loadTable() {
        try {
            ps = cn.prepareStatement("SELECT req.transferRequestNumber, req.transferJoiningDate, tdep.transferDepartmentName, tpro.transferProjectName, tpos.transferPositionName, e.fullname FROM Employee e INNER JOIN TransferRequests req ON e.empNumber = req.empNumber INNER JOIN TransferDepartment tdep ON req.transferDepartmentNumber = tdep.transferDepartmentNumber INNER JOIN TransferProject tpro ON req.transferProjectNumber = tpro.transferProjectNumber INNER JOIN TransferPosition tpos ON req.transferPositionNumber = tpos.transferPositionNumber WHERE req.status = 'Approved'");
            rs = ps.executeQuery();

            vtr = new Vector();
            while (rs.next()) {
                vtr = new Vector();
                vtr.add(rs.getString(1));
                vtr.add(rs.getString(2).substring(0, 7));
                vtr.add(rs.getString(3));
                vtr.add(rs.getString(4));
                vtr.add(rs.getString(5));
                vtr.add(rs.getString(6));
                tblModel.addRow(vtr);
            }
            if (vtr.isEmpty()) {
                tblModel.addRow(vtr);
            }
            tbMonthlyReport.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlMonthlyTransferReport = new javax.swing.JPanel();
        lblTitleMontlyTransferReport = new javax.swing.JLabel();
        scpMonthlyReport = new javax.swing.JScrollPane();
        tbMonthlyReport = new javax.swing.JTable();
        pnlLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lblMonth = new javax.swing.JLabel();
        txtMonth = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THE MONTHLY TRANSFER REPORTS");
        setMinimumSize(new java.awt.Dimension(854, 613));

        lblTitleMontlyTransferReport.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitleMontlyTransferReport.setForeground(new java.awt.Color(0, 0, 255));
        lblTitleMontlyTransferReport.setText("MONTHLY TRANSFER REPORT");

        tbMonthlyReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbMonthlyReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonthlyReportMouseClicked(evt);
            }
        });
        scpMonthlyReport.setViewportView(tbMonthlyReport);

        lblLogo.setPreferredSize(new java.awt.Dimension(100, 100));

        lblCompanyName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCompanyName.setForeground(new java.awt.Color(0, 102, 255));
        lblCompanyName.setText("ABC TECHNOLOGIES Ltd.");

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addGroup(pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLogoLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLogoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCompanyName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDate.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(442, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblMonth.setText("Transfer month:");
        lblMonth.setMaximumSize(null);

        txtMonth.setMaximumSize(null);

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 102, 255));
        btnReset.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 102, 255));
        btnDelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMonthlyTransferReportLayout = new javax.swing.GroupLayout(pnlMonthlyTransferReport);
        pnlMonthlyTransferReport.setLayout(pnlMonthlyTransferReportLayout);
        pnlMonthlyTransferReportLayout.setHorizontalGroup(
            pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                .addGroup(pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                        .addGroup(pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                                .addGap(275, 275, 275)
                                .addComponent(lblTitleMontlyTransferReport)))
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMonthlyTransferReportLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scpMonthlyReport, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMonthlyTransferReportLayout.createSequentialGroup()
                                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMonthlyTransferReportLayout.setVerticalGroup(
            pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonthlyTransferReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMonthlyTransferReportLayout.createSequentialGroup()
                        .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleMontlyTransferReport, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMonthlyTransferReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMonthlyTransferReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String regMonth = "^[0-9]{4}[-](0[1-9]|1[012])$";
        Pattern p = Pattern.compile(regMonth);
        Matcher m = p.matcher(txtMonth.getText().trim());
        if(txtMonth.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer month is required.");
            txtMonth.requestFocus();
        } else if(!m.find()){
            JOptionPane.showMessageDialog(this, "The transfer month must be in the format \"yyyy-MM\".");
            txtMonth.requestFocus();
        } else {
            try {
                boolean flag = false;
                String string = "SELECT req.transferRequestNumber, req.transferJoiningDate, tdep.transferDepartmentName, tpro.transferProjectName, tpos.transferPositionName, e.fullname FROM Employee e INNER JOIN TransferRequests req ON e.empNumber = req.empNumber INNER JOIN TransferDepartment tdep ON req.transferDepartmentNumber = tdep.transferDepartmentNumber INNER JOIN TransferProject tpro ON req.transferProjectNumber = tpro.transferProjectNumber INNER JOIN TransferPosition tpos ON req.transferPositionNumber = tpos.transferPositionNumber WHERE req.status = 'Approved' and ";
                String query = string + "req.transferJoiningDate LIKE ?";
                ps = cn.prepareStatement(query);
                ps.setString(1, txtMonth.getText() + "%");
                rs = ps.executeQuery();
                while (true) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this, "This transfer month may not have been approved for transfer.");
                        txtMonth.requestFocus();
                        flag = false;
                        return;
                    } else {
                        tblModel.getDataVector().removeAllElements();
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setString(1, txtMonth.getText() + "%");
                        rs = ps.executeQuery();
                        
                        vtr = new Vector();
                        while (rs.next()) {
                            vtr = new Vector();
                            vtr.add(rs.getString(1));
                            vtr.add(rs.getString(2).substring(0, 7));
                            vtr.add(rs.getString(3));
                            vtr.add(rs.getString(4));
                            vtr.add(rs.getString(5));
                            vtr.add(rs.getString(6));
                            tblModel.addRow(vtr);
                        }
                        flag = true;
                        break;
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        btnDelete.setEnabled(false);
        txtMonth.setText("");
        tblModel.getDataVector().removeAllElements();
        loadTable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String regMonth = "^[0-9]{4}[-](0[1-9]|1[012])$";
        Pattern p = Pattern.compile(regMonth);
        Matcher m = p.matcher(txtMonth.getText().trim());
        if(txtMonth.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer month is required.");
            txtMonth.requestFocus();
        } else if(!m.find()){
            JOptionPane.showMessageDialog(this, "The transfer month must be in the format \"yyyy-MM\".");
            txtMonth.requestFocus();
        } else {
            try {
                String s = "SELECT transferJoiningDate FROM TransferRequests WHERE status = 'Approved' and transferJoiningDate LIKE ?";
                ps = cn.prepareStatement(s);
                ps.setString(1, txtMonth.getText()+"%");
                rs = ps.executeQuery();
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "This transfer month may not have been approved for transfer.");
                    txtMonth.requestFocus();
                } else {
                    int click = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this "+(String) tbMonthlyReport.getValueAt(tbMonthlyReport.getSelectedRow(), 1)+" month transfer report?");
                    if (click == 0) {
                        String query = "DELETE TransferRequests WHERE status = 'Approved' and transferRequestNumber = ?";
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setString(1, (String) tbMonthlyReport.getValueAt(tbMonthlyReport.getSelectedRow(), 0));
                        ps.executeUpdate();
                        tblModel.getDataVector().removeAllElements();
                        loadTable();
                        txtMonth.setText("");
                        btnDelete.setEnabled(false);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbMonthlyReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonthlyReportMouseClicked
        btnDelete.setEnabled(true);
        int row = tbMonthlyReport.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtMonth.setText((String) tbMonthlyReport.getValueAt(row, 1));
    }//GEN-LAST:event_tbMonthlyReportMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblTitleMontlyTransferReport;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlMonthlyTransferReport;
    private javax.swing.JScrollPane scpMonthlyReport;
    private javax.swing.JTable tbMonthlyReport;
    private javax.swing.JTextField txtMonth;
    // End of variables declaration//GEN-END:variables
}
