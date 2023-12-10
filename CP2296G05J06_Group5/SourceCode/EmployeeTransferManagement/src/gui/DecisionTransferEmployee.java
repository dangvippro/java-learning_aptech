package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DecisionTransferEmployee extends javax.swing.JFrame {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel tblModel;
    Vector row;
    server.DBHelper db = new server.DBHelper();
    ResultSet rsTPro;
    PreparedStatement psTPro;
    ResultSet rsTDep;
    PreparedStatement psTDep;
    ResultSet rsTPos;
    PreparedStatement psTPos;
    SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat ft1 = new SimpleDateFormat("dd/MM/yyyy");
    
    public DecisionTransferEmployee(String adminNumber) {
        initComponents();
        lblAdminNumber.setText(adminNumber);
        lblAdminNumber.setVisible(false);
        txtEmpID.setEnabled(false);
        txtRelievingDate.setDateFormatString("dd/MM/yyyy");
        txtJoiningDate.setDateFormatString("dd/MM/yyyy");
        
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("Employe Number");
        tblModel.addColumn("Fullname");
        tblModel.addColumn("Date Of Birth");
        tblModel.addColumn("Gender");
        tblModel.addColumn("Email");
        tblModel.addColumn("Address");
        tblModel.addColumn("Role");
        tblModel.addColumn("Work Experience");
        tblModel.addColumn("Current Project");
        tblModel.addColumn("Current DepartMent");
        tblModel.addColumn("Current Position");
        tblModel.addColumn("Allowace Level");
        tblModel.addColumn("Date Start Work");
        tblModel.addColumn("Note");
        tblDecisionTransfer.setModel(tblModel);
        
        LoadTable();
        loadData();
    }

    public void LoadTable() {
        try {
            con = db.getCon();
            ps = con.prepareStatement("SELECT empNumber, fullname, dateOfBirth, gender, email, address, role, workExperience, currentProject, currentDepartment, currentPosition, allowanceLevel, dateStartWork, note FROM Employee");
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
                tblModel.addRow(row);
            }
            if (row.isEmpty()) {
                tblModel.addRow(row);
            }
            tblDecisionTransfer.setModel(tblModel);
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

        transferForm = new javax.swing.JDialog();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblFullname1 = new javax.swing.JLabel();
        lblCPro = new javax.swing.JLabel();
        lblTPro = new javax.swing.JLabel();
        lblCDep = new javax.swing.JLabel();
        lblDateOfPosting = new javax.swing.JLabel();
        lblFullname2 = new javax.swing.JLabel();
        lblTPos = new javax.swing.JLabel();
        lblRelievingDate = new javax.swing.JLabel();
        lblFullname4 = new javax.swing.JLabel();
        lblJoiningDate = new javax.swing.JLabel();
        lblFullname5 = new javax.swing.JLabel();
        lblFullname3 = new javax.swing.JLabel();
        jcroll_emp_transfer_table = new javax.swing.JScrollPane();
        tblDecisionTransfer = new javax.swing.JTable();
        jbEmpID = new javax.swing.JLabel();
        txtEmpID = new javax.swing.JTextField();
        jbReason = new javax.swing.JLabel();
        jcroll_note = new javax.swing.JScrollPane();
        txtaReason = new javax.swing.JTextArea();
        jbCurrentDepartment = new javax.swing.JLabel();
        jbMovePosition = new javax.swing.JLabel();
        jbDateStartWork = new javax.swing.JLabel();
        btnTransfer = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtRelievingDate = new com.toedter.calendar.JDateChooser();
        jbMovePosition1 = new javax.swing.JLabel();
        cbTPos = new javax.swing.JComboBox<>();
        cbTPro = new javax.swing.JComboBox<>();
        cbTDep = new javax.swing.JComboBox<>();
        jbDateStartWork1 = new javax.swing.JLabel();
        txtJoiningDate = new com.toedter.calendar.JDateChooser();
        lblAdminNumber = new javax.swing.JLabel();

        label1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        label1.setText("Decision");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("V/v: Tansfer of Work to Mr/Ms");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("COMPANY");

        jLabel3.setText("Pursuant to the Enterprise Law;");

        jLabel4.setText("Pursuant to the Company's Charter of Operation");

        jLabel5.setText("Based on the current needs of the Company");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("DECISION");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Article 1:");

        jLabel9.setText("Transfer of Mr/Ms.");

        jLabel10.setText("- currently");

        jLabel11.setText("to");

        jLabel12.setText("Company");

        jLabel13.setText("from Date");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setText("Article 2:");

        jLabel17.setText("Mr/Ms");

        jLabel18.setText("is paid salary and benefils at position");

        jLabel19.setText("until the end of date work.");

        jLabel21.setText("From the Date");

        jLabel23.setText("the salary, bonus, and other policices of Mr/Ms");

        jLabel24.setText("Mr/Ms");

        jLabel25.setText("is obliged to perform the assigned tasks, be responsible for and report to the Head of the Direct Managerment Department, the Deputy Director of");

        jLabel26.setText("implementation. Perform the assigned duties and powers in accordance with the Company's Charter, regulations and relevant laws.");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel27.setText("Article 3:");

        jLabel28.setText("The Dicision takes effect from the Date");

        jLabel30.setText("until another Decision on this issue is issued. Mr/Ms");

        jLabel31.setText("and relevant Departments");

        jLabel32.setText("are responsible for the implementation of this decision.");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel33.setText("Recipients:");

        jLabel34.setText("- As in Article 3 (for implementation)");

        jLabel35.setText("- Save HCNS");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel36.setText("COMPANY");

        jLabel37.setText("company");

        jLabel38.setText("company");

        lblFullname1.setText("....................");

        lblCPro.setText("..............");

        lblTPro.setText("...............");

        lblCDep.setText("...............");

        lblDateOfPosting.setText("..........");

        lblFullname2.setText(".............");

        lblTPos.setText(".............");

        lblRelievingDate.setText("...........");

        lblFullname4.setText("............");

        lblJoiningDate.setText(".............");

        lblFullname5.setText(".............");

        lblFullname3.setText("................");

        javax.swing.GroupLayout transferFormLayout = new javax.swing.GroupLayout(transferForm.getContentPane());
        transferForm.getContentPane().setLayout(transferFormLayout);
        transferFormLayout.setHorizontalGroup(
            transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferFormLayout.createSequentialGroup()
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transferFormLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lblFullname1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(transferFormLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(lblCPro, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addGap(5, 5, 5)
                                .addComponent(lblTPro, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addComponent(jLabel7)))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDateOfPosting, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344)))
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCDep, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(transferFormLayout.createSequentialGroup()
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel1))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(transferFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFullname4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblJoiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(lblFullname5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transferFormLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36))
                            .addGroup(transferFormLayout.createSequentialGroup()
                                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)
                                    .addGroup(transferFormLayout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFullname3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(175, 175, 175))
                    .addGroup(transferFormLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFullname2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTPos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRelievingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        transferFormLayout.setVerticalGroup(
            transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel38))
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(lblFullname1)
                    .addComponent(lblCPro)
                    .addComponent(lblTPro)
                    .addComponent(lblCDep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblDateOfPosting))
                .addGap(18, 18, 18)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(lblFullname2)
                    .addComponent(lblTPos)
                    .addComponent(lblRelievingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblFullname3))
                .addGap(18, 18, 18)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(lblFullname4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(lblJoiningDate)
                    .addComponent(lblFullname5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addGap(35, 35, 35)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transferFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Decision Transfer Employee");

        tblDecisionTransfer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDecisionTransfer.getTableHeader().setReorderingAllowed(false);
        tblDecisionTransfer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDecisionTransferMouseClicked(evt);
            }
        });
        jcroll_emp_transfer_table.setViewportView(tblDecisionTransfer);

        jbEmpID.setText("Employee ID: ");

        jbReason.setText("Reason");

        txtaReason.setColumns(20);
        txtaReason.setRows(5);
        jcroll_note.setViewportView(txtaReason);

        jbCurrentDepartment.setText("Transfer Department:");

        jbMovePosition.setText("Transfer Project");

        jbDateStartWork.setText("Transfer Relieving Date");

        btnTransfer.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnTransfer.setText("Transfer");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jbMovePosition1.setText("Transfer Position");

        cbTPos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTDep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbDateStartWork1.setText("Transfer Joining Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcroll_emp_transfer_table)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcroll_note, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbEmpID)
                            .addComponent(jbDateStartWork)
                            .addComponent(jbMovePosition)
                            .addComponent(jbReason))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTPro, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRelievingDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(jbCurrentDepartment)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbTDep, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbMovePosition1)
                                    .addComponent(jbDateStartWork1))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTPos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtJoiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAdminNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcroll_emp_transfer_table, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbCurrentDepartment)
                            .addComponent(cbTDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbMovePosition1)
                            .addComponent(cbTPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJoiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbDateStartWork1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTransfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbEmpID)
                            .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbMovePosition)
                            .addComponent(cbTPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRelievingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbDateStartWork))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcroll_note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbReason))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAdminNumber))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        String empNumber="", fullname = "", cPro="", cDep="", cPos="";
        while (true) {
            try {
                ps = con.prepareStatement("SELECT * FROM Employee WHERE empNumber = ?");
                ps.setString(1, txtEmpID.getText().trim());
                rs = ps.executeQuery();
                if (txtRelievingDate.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Transfer Relieving Date cannot be left blank!");
                    return;
                } else if (txtJoiningDate.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Transfer Joining Date cannot be left blank!");
                    txtJoiningDate.grabFocus();
                    return;
                } else if (txtaReason.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Transfer for reason cannot be left blank!");
                    txtaReason.grabFocus();
                    return;
                } else if (!(rs.next())) {
                    JOptionPane.showMessageDialog(this, "The empNumber is not exist!");
                    txtaReason.grabFocus();
                    return;
                } else {
                    empNumber = rs.getString("empNumber");
                    fullname = rs.getString("fullname");
                    cPro = rs.getString("currentProject");
                    cDep = rs.getString("currentDepartment");
                    cPos = rs.getString("currentPosition");
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(TransferLetter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        File file = new File("DecisionTransferEmployee.txt");
        file.delete();
        //write vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("DecisionTransferEmployee.txt"), "UTF8"));
            b.write("\t\t\t\t\t\t\t\t\tDECISION");
            b.write("\n\t\t\t\t\t\t\t\tV/v: Transfer of Work");
            b.write("\n\t\t\t\t\t\t\t\t\tCOMPANY");
            b.write("\n\t\t\t- Pursuant to the Enterprise Law;");
            b.write("\n\t\t\t- Pursuant to the Company's Charter of Operation;");
            b.write("\n\t\t\t- Based on the current needs of the Company;");
            b.write("\n\n\t\t\t\t\t\t\t\t\tDECISION");
            b.write("\n\t\t\tArticle 1: Transfer of Mr/Ms: " + fullname + ", currenttly: " + cPos +" to " + (String)cbTPos.getSelectedItem()+
                    "From date "+ (String) ft1.format(txtJoiningDate.getDate()) +".");
            b.write("\n\n\t\t\tArticle 2: Mr/Ms: "+fullname+" is paid salary and benefits at position: "+ cPos +"until the end of "
                    + "\n\t\t"+(String)ft1.format(txtJoiningDate.getDate())+" From the date "
                    +(String)ft1.format(txtJoiningDate.getDate())+" the salary, bonus and other policies of Mr/Ms: "+fullname+".");
            b.write("\n\n\t\t\tMr/Ms: "+fullname+" is obliged to perform the assigned tasks, be responsinle for and report to the Head of "
                    + "\n\t\tthe Direct Managerment, the Deputy Director of Recruitment and before the law on the implementation"
                    + ".Perform the\n\t\tassigned duties and powers in accordance with the Company's Charter, regulations and relevant laws.");
            b.write("\n\n\t\t\tArticle 3: The Decision takes effect from the "+(String)ft1.format(new Date())+" until another Decision"
                    + "on this issue is issued.\n\t\t Mr/Ms: "+fullname+" and relavant Departments/Departments are responsible for the"
                            + "implementation of this decision");
            b.write("\n\n\t\t\tRECIPIENTS: "+fullname+".");
            b.write("\n\t\t\t- As in Article 3 (for implementation)\t\t\t\t\t\t\t\t\t COMPANY");
            b.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //open file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad DecisionTransferEmployee.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnResetActionPerformed(evt);
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtaReason.setText("");
        txtRelievingDate.setDate(null);
        txtJoiningDate.setDate(null);
        txtEmpID.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblDecisionTransferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDecisionTransferMouseClicked
        int row = tblDecisionTransfer.getSelectedRow();
        if (row == -1) {
            return;
        } else {
            txtEmpID.setText((String) tblDecisionTransfer.getValueAt(row, 0));
        }
    }//GEN-LAST:event_tblDecisionTransferMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JComboBox<String> cbTDep;
    private javax.swing.JComboBox<String> cbTPos;
    private javax.swing.JComboBox<String> cbTPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jbCurrentDepartment;
    private javax.swing.JLabel jbDateStartWork;
    private javax.swing.JLabel jbDateStartWork1;
    private javax.swing.JLabel jbEmpID;
    private javax.swing.JLabel jbMovePosition;
    private javax.swing.JLabel jbMovePosition1;
    private javax.swing.JLabel jbReason;
    private javax.swing.JScrollPane jcroll_emp_transfer_table;
    private javax.swing.JScrollPane jcroll_note;
    private java.awt.Label label1;
    private javax.swing.JLabel lblAdminNumber;
    private javax.swing.JLabel lblCDep;
    private javax.swing.JLabel lblCPro;
    private javax.swing.JLabel lblDateOfPosting;
    private javax.swing.JLabel lblFullname1;
    private javax.swing.JLabel lblFullname2;
    private javax.swing.JLabel lblFullname3;
    private javax.swing.JLabel lblFullname4;
    private javax.swing.JLabel lblFullname5;
    private javax.swing.JLabel lblJoiningDate;
    private javax.swing.JLabel lblRelievingDate;
    private javax.swing.JLabel lblTPos;
    private javax.swing.JLabel lblTPro;
    private javax.swing.JTable tblDecisionTransfer;
    private javax.swing.JDialog transferForm;
    private javax.swing.JTextField txtEmpID;
    private com.toedter.calendar.JDateChooser txtJoiningDate;
    private com.toedter.calendar.JDateChooser txtRelievingDate;
    private javax.swing.JTextArea txtaReason;
    // End of variables declaration//GEN-END:variables
}