package gui;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TheTransferRecord extends javax.swing.JFrame {

    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel tblModel;
    Vector row;
    server.DBHelper db = new server.DBHelper();
    Connection cn = db.getCon();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    public TheTransferRecord() {
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("image//transferRecord.jpg");
        this.setIconImage(img.getImage());
        image();
        loadData();
        cbbtdpm.setSelectedIndex(-1);
        cbbtpj.setSelectedIndex(-1);
        cbbtpst.setSelectedIndex(-1);
        btnDeleteRecord.setEnabled(false);
        btnUpdateRecord.setEnabled(false);
        txtDateOfBirth.setDateFormatString("dd/MM/yyyy");
        txtDateStartWork.setDateFormatString("dd/MM/yyyy");
        txtJoiningDate.setDateFormatString("dd/MM/yyyy");
        txtRelievingDate.setDateFormatString("dd/MM/yyyy");

        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("RequestID");
        tblModel.addColumn("EmpNumber");
        tblModel.addColumn("EmpName");
        tblModel.addColumn("Gender");
        tblModel.addColumn("Date of birth");
        tblModel.addColumn("Address");
        tblModel.addColumn("Email");
        tblModel.addColumn("Date start work");
        tblModel.addColumn("Role");
        tblModel.addColumn("CurrentDepartment");
        tblModel.addColumn("CurrentProject");
        tblModel.addColumn("CurrentPosition");
        tblModel.addColumn("TransferDepartment");
        tblModel.addColumn("TransferProject");
        tblModel.addColumn("TransferPosition");
        tblModel.addColumn("TransferJoiningDate");
        tblModel.addColumn("TransferRelievingDate");
        tblModel.addColumn("Request status");
        jTableTheTransferRecord.setModel(tblModel);
        loadTable();
    }

    public void image() {
        String refesh = "image//refresh.png";
        String add = "image//add.png";
        String edit = "image//edit2.png";
        String delete = "image//deleted.png";
        String search = "image//search.png";
        btnResetRecord.setSize(20, 20);
        new SetImage().setImageButton(btnResetRecord, refesh);
        btnUpdateRecord.setSize(20, 20);
        new SetImage().setImageButton(btnUpdateRecord, edit);
        btnDeleteRecord.setSize(20, 20);
        new SetImage().setImageButton(btnDeleteRecord, delete);
        btnSearchRecord.setSize(20, 20);
        new SetImage().setImageButton(btnSearchRecord, search);
    }

    private void loadData() {
        try {
            cn = db.getCon();
            cbbtdpm.removeAllItems();
            cbbtpj.removeAllItems();
            cbbtpst.removeAllItems();

            PreparedStatement psTPJ = cn.prepareStatement("SELECT transferProjectName FROM TransferProject");
            ResultSet rsTPJ = psTPJ.executeQuery();
            PreparedStatement psTDPM = cn.prepareStatement("SELECT transferDepartmentName FROM TransferDepartment");
            ResultSet rsTDPM = psTDPM.executeQuery();
            PreparedStatement psTPST = cn.prepareStatement("SELECT transferPositionName FROM TransferPosition");
            ResultSet rsTPST = psTPST.executeQuery();

            while (rsTPJ.next()) {
                cbbtpj.addItem(rsTPJ.getString(1));
            }
            while (rsTDPM.next()) {
                cbbtdpm.addItem(rsTDPM.getString(1));
            }
            while (rsTPST.next()) {
                cbbtpst.addItem(rsTPST.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error:: SQLException !");
            Logger.getLogger(RequestATransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTable() {
        try {
            pst = cn.prepareStatement("SELECT req.transferRequestNumber, req.empNumber, e.fullname, e.gender, e.dateOfBirth, e.address, e.email, e.dateStartWork, e.role, e.currentDepartment, e.currentProject, e.currentPosition, tdpm.transferDepartmentName, tpj.transferProjectName, tpst.transferPositionName, req.transferJoiningDate, req.transferRelievingDate, req.status FROM TransferRequests req INNER JOIN Employee e ON req.empNumber = e.empNumber INNER JOIN TransferProject tpj ON req.transferProjectNumber = tpj.transferProjectNumber INNER JOIN TransferDepartment tdpm ON req.transferDepartmentNumber = tdpm.transferDepartmentNumber INNER JOIN TransferPosition tpst ON req.transferPositionNumber = tpst.transferPositionNumber");
            rs = pst.executeQuery();

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
                row.add(rs.getString(17));
                row.add(rs.getString(18));
                tblModel.addRow(row);
            }
            if (row.isEmpty()) {
                tblModel.addRow(row);
            }
            jTableTheTransferRecord.setModel(tblModel);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error:: SQLException");
            Logger.getLogger(Approval.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanelTheTransferRecord = new javax.swing.JPanel();
        jScrollPaneTheTransferRecord = new javax.swing.JScrollPane();
        jTableTheTransferRecord = new javax.swing.JTable();
        jPanelControl = new javax.swing.JPanel();
        lbEmpNumberRecord = new javax.swing.JLabel();
        lbEmpNameRecord = new javax.swing.JLabel();
        lbGenderRecord = new javax.swing.JLabel();
        lbDateOfBirthRecord = new javax.swing.JLabel();
        txtEmpNumber = new javax.swing.JTextField();
        txtEmpName = new javax.swing.JTextField();
        rdbtnMale = new javax.swing.JRadioButton();
        rdbtnFemale = new javax.swing.JRadioButton();
        lbAddressRecord = new javax.swing.JLabel();
        lbEmailRecord = new javax.swing.JLabel();
        lbDateStartWorkRecord = new javax.swing.JLabel();
        lbRoleRecord = new javax.swing.JLabel();
        lbCurrentDepartmentRecord = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCurrentDepartment = new javax.swing.JTextField();
        lbCurrentProjectRecord = new javax.swing.JLabel();
        lbCurrentPositionRecord = new javax.swing.JLabel();
        lbTransferDepartmentRecord = new javax.swing.JLabel();
        lbTransferProjectRecord = new javax.swing.JLabel();
        lbTransferPositionRecord = new javax.swing.JLabel();
        txtCurrentProject = new javax.swing.JTextField();
        txtCurrentPosition = new javax.swing.JTextField();
        lbTransferJoiningDateRecord = new javax.swing.JLabel();
        lbTransferRelievingDateRecord = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        jScrollPaneNoteRecord = new javax.swing.JScrollPane();
        txtAStatus = new javax.swing.JTextArea();
        txtRole = new javax.swing.JTextField();
        btnResetRecord = new javax.swing.JButton();
        btnDeleteRecord = new javax.swing.JButton();
        btnUpdateRecord = new javax.swing.JButton();
        btnSearchRecord = new javax.swing.JButton();
        cbbtdpm = new javax.swing.JComboBox<>();
        cbbtpj = new javax.swing.JComboBox<>();
        cbbtpst = new javax.swing.JComboBox<>();
        txtRelievingDate = new com.toedter.calendar.JDateChooser();
        txtDateOfBirth = new com.toedter.calendar.JDateChooser();
        txtDateStartWork = new com.toedter.calendar.JDateChooser();
        txtJoiningDate = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("The transfer record");
        setMinimumSize(new java.awt.Dimension(1289, 678));

        jPanelTheTransferRecord.setPreferredSize(new java.awt.Dimension(1283, 618));

        jTableTheTransferRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTheTransferRecord.getTableHeader().setReorderingAllowed(false);
        jTableTheTransferRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTheTransferRecordMouseClicked(evt);
            }
        });
        jScrollPaneTheTransferRecord.setViewportView(jTableTheTransferRecord);

        lbEmpNumberRecord.setText("Emp number:");

        lbEmpNameRecord.setText("Emp name:");

        lbGenderRecord.setText("Gender:");

        lbDateOfBirthRecord.setText("Date of birth:");

        rdbtnMale.setText("Male");

        rdbtnFemale.setText("Female");

        lbAddressRecord.setText("Address:");

        lbEmailRecord.setText("Email:");

        lbDateStartWorkRecord.setText("Date start work:");

        lbRoleRecord.setText("Role:");

        lbCurrentDepartmentRecord.setText("Current department:");

        lbCurrentProjectRecord.setText("Current project:");

        lbCurrentPositionRecord.setText("Current position:");

        lbTransferDepartmentRecord.setText("Transfer department:");

        lbTransferProjectRecord.setText("Transfer project:");

        lbTransferPositionRecord.setText("Transfer position:");

        lbTransferJoiningDateRecord.setText("Transfer joining date:");

        lbTransferRelievingDateRecord.setText("Transfer relieving date:");

        lbStatus.setText("Request status:");

        txtAStatus.setColumns(20);
        txtAStatus.setRows(5);
        jScrollPaneNoteRecord.setViewportView(txtAStatus);

        btnResetRecord.setText("Reset");
        btnResetRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetRecordActionPerformed(evt);
            }
        });

        btnDeleteRecord.setText("Delete");
        btnDeleteRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRecordActionPerformed(evt);
            }
        });

        btnUpdateRecord.setText("Update");
        btnUpdateRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordActionPerformed(evt);
            }
        });

        btnSearchRecord.setText("Search");
        btnSearchRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRecordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelControlLayout = new javax.swing.GroupLayout(jPanelControl);
        jPanelControl.setLayout(jPanelControlLayout);
        jPanelControlLayout.setHorizontalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDateOfBirthRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(lbGenderRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmpNumberRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmpNameRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAddressRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addComponent(rdbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmpName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddress)
                            .addComponent(txtDateOfBirth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCurrentProjectRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDateStartWorkRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRoleRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCurrentDepartmentRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmailRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCurrentProject, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurrentDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateStartWork, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCurrentPositionRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTransferDepartmentRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTransferProjectRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTransferPositionRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTransferJoiningDateRecord))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbtdpm, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbtpj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbtpst, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJoiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneNoteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addComponent(lbTransferRelievingDateRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRelievingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelControlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchRecord)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateRecord)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteRecord)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetRecord)
                        .addGap(67, 67, 67))))
        );
        jPanelControlLayout.setVerticalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmpNumberRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmpNumber)
                    .addComponent(lbEmailRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCurrentPositionRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addComponent(txtRelievingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbTransferRelievingDateRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmpNameRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbbtdpm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTransferDepartmentRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEmpName)
                                    .addComponent(lbDateStartWorkRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDateStartWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbGenderRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbtpj)
                                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbRoleRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbTransferProjectRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDateOfBirthRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCurrentDepartmentRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCurrentDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbbtpst)
                                    .addComponent(lbTransferPositionRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbAddressRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelControlLayout.createSequentialGroup()
                                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbCurrentProjectRecord)
                                            .addComponent(txtCurrentProject, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                            .addComponent(lbTransferJoiningDateRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtJoiningDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addComponent(jScrollPaneNoteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdbtnMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdbtnFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetRecord)
                    .addComponent(btnDeleteRecord)
                    .addComponent(btnUpdateRecord)
                    .addComponent(btnSearchRecord))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelTheTransferRecordLayout = new javax.swing.GroupLayout(jPanelTheTransferRecord);
        jPanelTheTransferRecord.setLayout(jPanelTheTransferRecordLayout);
        jPanelTheTransferRecordLayout.setHorizontalGroup(
            jPanelTheTransferRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTheTransferRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTheTransferRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTheTransferRecordLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelTheTransferRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTheTransferRecordLayout.createSequentialGroup()
                                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTheTransferRecordLayout.createSequentialGroup()
                                .addComponent(jScrollPaneTheTransferRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 1265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanelTheTransferRecordLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        jPanelTheTransferRecordLayout.setVerticalGroup(
            jPanelTheTransferRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTheTransferRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTheTransferRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTheTransferRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTheTransferRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRecordActionPerformed
        boolean b = false;
        String query = "SELECT req.transferRequestNumber, req.empNumber, e.fullname, e.gender, e.dateOfBirth, e.address, e.email, e.dateStartWork, e.role, e.currentDepartment, e.currentProject, e.currentPosition, tdpm.transferDepartmentName, tpj.transferProjectName, tpst.transferPositionName, req.transferJoiningDate, req.transferRelievingDate, req.status FROM TransferRequests req INNER JOIN Employee e ON req.empNumber = e.empNumber INNER JOIN TransferProject tpj ON req.transferProjectNumber = tpj.transferProjectNumber INNER JOIN TransferDepartment tdpm ON req.transferDepartmentNumber = tdpm.transferDepartmentNumber INNER JOIN TransferPosition tpst ON req.transferPositionNumber = tpst.transferPositionNumber WHERE ";
        
        try {
            String s = query + " req.empNumber LIKE ?";
            pst = cn.prepareStatement(s);
            pst.setString(1, txtEmpNumber.getText().trim() + "%");
            rs = pst.executeQuery();
            while (true) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Employee number does not exist.");
                    txtEmpNumber.requestFocus();
                    b = false;
                    return;
                } else {
                    tblModel.getDataVector().removeAllElements();
                    PreparedStatement pst1 = cn.prepareStatement(s);
                    pst1.setString(1, txtEmpNumber.getText().trim() + "%");
                    rs = pst1.executeQuery();
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
                        row.add(rs.getString(17));
                        row.add(rs.getString(18));
                        tblModel.addRow(row);
                    }
                    b = true;
                    break;
                }
            }

            if (!txtEmpName.getText().isEmpty()) {
                String s1 = "SELECT req.transferRequestNumber, req.empNumber, e.fullname, e.gender, e.dateOfBirth, e.address, e.email, e.dateStartWork, e.role, e.currentDepartment, e.currentProject, e.currentPosition, tdpm.transferDepartmentName, tpj.transferProjectName, tpst.transferPositionName, req.transferJoiningDate, req.transferRelievingDate, req.status FROM TransferRequests req INNER JOIN Employee e ON req.empNumber = e.empNumber INNER JOIN TransferProject tpj ON req.transferProjectNumber = tpj.transferProjectNumber INNER JOIN TransferDepartment tdpm ON req.transferDepartmentNumber = tdpm.transferDepartmentNumber INNER JOIN TransferPosition tpst ON req.transferPositionNumber = tpst.transferPositionNumber WHERE e.fullname LIKE ?";
                pst = cn.prepareStatement(s1);
                pst.setString(1, txtEmpName.getText().trim() + "%");
                rs = pst.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Employee name does not exist.");
                    txtEmpName.requestFocus();
                    b = false;
                } else {
                    tblModel.getDataVector().removeAllElements();
                    PreparedStatement pst1 = cn.prepareStatement(s1);
                    pst1.setString(1, txtEmpName.getText().trim() + "%");
                    rs = pst1.executeQuery();
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
                        row.add(rs.getString(17));
                        row.add(rs.getString(18));
                        tblModel.addRow(row);
                    }
                    b = true;
                }
            }

            if (!txtCurrentDepartment.getText().isEmpty()) {
                String s2 = query + "e.currentDepartment LIKE ?";
                pst = cn.prepareStatement(s2);
                pst.setString(1, txtCurrentDepartment.getText().trim() + "%");
                rs = pst.executeQuery();
                while (true) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Current department does not exist.");
                        txtCurrentDepartment.requestFocus();
                        return;
                    } else {
                        tblModel.getDataVector().removeAllElements();
                        PreparedStatement pst1 = cn.prepareStatement(s2);
                        pst1.setString(1, txtCurrentDepartment.getText().trim() + "%");
                        rs = pst1.executeQuery();
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
                            row.add(rs.getString(17));
                            row.add(rs.getString(18));
                            tblModel.addRow(row);
                        }
                        break;
                    }
                }
            }

            if (!txtCurrentProject.getText().isEmpty()) {
                String s3 = query + "e.currentProject LIKE ?";
                pst = cn.prepareStatement(s3);
                pst.setString(1, txtCurrentProject.getText().trim() + "%");
                rs = pst.executeQuery();
                while (true) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Current project does not exist.");
                        txtCurrentProject.requestFocus();
                        return;
                    } else {
                        tblModel.getDataVector().removeAllElements();
                        PreparedStatement pst1 = cn.prepareStatement(s3);
                        pst1.setString(1, txtCurrentProject.getText().trim() + "%");
                        rs = pst1.executeQuery();
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
                            row.add(rs.getString(17));
                            row.add(rs.getString(18));
                            tblModel.addRow(row);
                        }
                        break;
                    }
                }
            }
            
            if(!txtCurrentPosition.getText().isEmpty()){
                String s4 = query + "e.currentPosition LIKE ?";
                pst = cn.prepareStatement(s4);
                pst.setString(1, txtCurrentPosition.getText().trim() + "%");
                rs = pst.executeQuery();
                while (true) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Current position does not exist.");
                        txtCurrentPosition.requestFocus();
                        return;
                    } else {
                        tblModel.getDataVector().removeAllElements();
                        PreparedStatement pst1 = cn.prepareStatement(s4);
                        pst1.setString(1, txtCurrentPosition.getText().trim() + "%");
                        rs = pst1.executeQuery();
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
                            row.add(rs.getString(17));
                            row.add(rs.getString(18));
                            tblModel.addRow(row);
                        }
                        break;
                    }
                }
            }
            
            if(cbbtdpm.getSelectedItem()!=null){
                String s5 = query + "tdpm.transferDepartmentName LIKE ?";
                pst = cn.prepareStatement(s5);
                pst.setString(1, (String) cbbtdpm.getSelectedItem() + "");
                rs = pst.executeQuery();
                while (true) {
                    tblModel.getDataVector().removeAllElements();
                    PreparedStatement pst1 = cn.prepareStatement(s5);
                    pst1.setString(1, (String) cbbtdpm.getSelectedItem());
                    rs = pst1.executeQuery();
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
                        row.add(rs.getString(17));
                        row.add(rs.getString(18));
                        tblModel.addRow(row);
                    }
                    break;
                }
            }
            if(cbbtpj.getSelectedItem()!=null){
                String s6 = query + "tpj.transferProjectName LIKE ?";
                pst = cn.prepareStatement(s6);
                pst.setString(1, (String) cbbtpj.getSelectedItem() + "");
                rs = pst.executeQuery();
                while (true) {

                    tblModel.getDataVector().removeAllElements();
                    PreparedStatement pst1 = cn.prepareStatement(s6);
                    pst1.setString(1, (String) cbbtpj.getSelectedItem());
                    rs = pst1.executeQuery();
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
                        row.add(rs.getString(17));
                        row.add(rs.getString(18));
                        tblModel.addRow(row);
                    }
                    break;
                }
            }
            
            if(cbbtpst.getSelectedItem()!=null){
                String s7 = query + "tpst.transferPositionName LIKE ?";
                pst = cn.prepareStatement(s7);
                pst.setString(1, (String) cbbtpst.getSelectedItem() + "");
                rs = pst.executeQuery();
                while (true) {
                    tblModel.getDataVector().removeAllElements();
                    PreparedStatement pst1 = cn.prepareStatement(s7);
                    pst1.setString(1, (String) cbbtpst.getSelectedItem());
                    rs = pst1.executeQuery();
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
                        row.add(rs.getString(17));
                        row.add(rs.getString(18));
                        tblModel.addRow(row);
                    }
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheTransferRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSearchRecordActionPerformed

    private void btnUpdateRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordActionPerformed
        String tPro = null, tDep = null, tPos = null;
        try {
            String regRmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
            Pattern p1 = Pattern.compile(regRmail);
            Matcher m2 = p1.matcher(txtEmail.getText().trim());
            String gender;
            if (rdbtnMale.isSelected()) {
                gender = "male";
            } else {
                gender = "female";
            }
            if (txtEmpNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter employee number is required.");
                txtEmpNumber.requestFocus();
            } else {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT empNumber FROM TransferRequests WHERE empNumber= '" + txtEmpNumber.getText().trim() + "'");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Employee number does not exist.");
                    txtEmpNumber.requestFocus();
                } else if (txtEmpName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter employee name is required.");
                    txtEmpName.requestFocus();
                } else if (!rdbtnMale.isSelected() && !rdbtnFemale.isSelected()) {
                    JOptionPane.showMessageDialog(this, "Choose gender is required.");
                    rdbtnMale.requestFocus();
                } else if (rdbtnMale.isSelected() && rdbtnFemale.isSelected()) {
                    JOptionPane.showMessageDialog(this, "Invaild gender.");
                    rdbtnMale.requestFocus();
                } else if (txtDateOfBirth.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Enter date of birth is required.");
                    txtDateOfBirth.requestFocus();
                } else if (txtAddress.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter address is required.");
                    txtAddress.requestFocus();
                } else if (txtEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter email is required.");
                    txtEmail.requestFocus();
                } else if (!m2.find()) {
                    JOptionPane.showMessageDialog(this, "Invalid email.");
                    txtEmail.requestFocus();
                } else if (txtDateStartWork.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Enter date start work is required.");
                    txtDateStartWork.requestFocus();
                } else if (txtRole.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter role is required.");
                    txtRole.requestFocus();
                } else if (txtCurrentDepartment.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter current department is required.");
                    txtCurrentDepartment.requestFocus();
                } else if (txtCurrentProject.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter current project is required.");
                    txtCurrentProject.requestFocus();
                } else if (txtCurrentPosition.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter current position is required.");
                    txtCurrentPosition.requestFocus();
                } else if (txtJoiningDate.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Enter transfer joining date is required.");
                    txtJoiningDate.requestFocus();
                } else if (txtRelievingDate.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Enter transfer relieving date is required.");
                    txtRelievingDate.requestFocus();
                } else {
                    String sql1 = "SELECT [transferProjectNumber] FROM [dbo].[TransferProject] WHERE [transferProjectName] = ?";
                    String sql2 = "SELECT [transferDepartmentNumber] FROM [dbo].[TransferDepartment] WHERE [transferDepartmentName] = ?";
                    String sql3 = "SELECT [transferPositionNumber] FROM [dbo].[TransferPosition] WHERE [transferPositionName] = ?";

                    pst = cn.prepareStatement(sql1);
                    pst.setString(1, (String) cbbtpj.getSelectedItem());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        tPro = rs.getString(1);
                    }

                    pst = cn.prepareStatement(sql2);
                    pst.setString(1, (String) cbbtdpm.getSelectedItem());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        tDep = rs.getString(1);
                    }

                    pst = cn.prepareStatement(sql3);
                    pst.setString(1, (String) cbbtpst.getSelectedItem());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        tPos = rs.getString(1);
                    }
                    String query = "UPDATE Employee SET fullname=?, gender=?, dateOfBirth=?, address=?, email=?, dateStartWork=?, role=?, currentDepartment=?, currentProject=?, currentPosition=? WHERE empNumber= ?";
                    PreparedStatement pst1 = cn.prepareStatement(query);
                    pst1.setString(1, txtEmpName.getText().trim());
                    pst1.setString(2, gender);
                    pst1.setString(3, ft.format(txtDateOfBirth.getDate()));
                    pst1.setString(4, txtAddress.getText().trim());
                    pst1.setString(5, txtEmail.getText().trim());
                    pst1.setString(6, ft.format(txtDateStartWork.getDate()));
                    pst1.setString(7, txtRole.getText().trim());
                    pst1.setString(8, txtCurrentDepartment.getText().trim());
                    pst1.setString(9, txtCurrentProject.getText().trim());
                    pst1.setString(10, txtCurrentPosition.getText().trim());
                    pst1.setString(11, txtEmpNumber.getText().trim());
                    pst1.executeUpdate();
                    String s = "UPDATE TransferRequests SET transferProjectNumber= ?, transferDepartmentNumber = ?, transferPositionNumber = ?, transferJoiningDate = ?, transferRelievingDate = ? WHERE transferRequestNumber = ?";
                    PreparedStatement statement = cn.prepareStatement(s);
                    statement.setString(1, tPro);
                    statement.setString(2, tDep);
                    statement.setString(3, tPos);
                    statement.setString(4, ft.format(txtJoiningDate.getDate()));
                    statement.setString(5, ft.format(txtRelievingDate.getDate()));
                    statement.setString(6, (String) jTableTheTransferRecord.getValueAt(jTableTheTransferRecord.getSelectedRow(), 0));
                    statement.executeUpdate();
                    tblModel.getDataVector().removeAllElements();
                    loadTable();
                    btnUpdateRecord.setEnabled(false);
                    btnDeleteRecord.setEnabled(false);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateRecordActionPerformed

    private void btnDeleteRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRecordActionPerformed
        if (txtEmpNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the information you want to delete by clicking on the table.");
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT empNumber FROM TransferRequests WHERE empNumber = '" + txtEmpNumber.getText().trim() + "'");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Please select the information you want to delete by clicking on the table.");
                } else {
                    int click = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + (String) jTableTheTransferRecord.getValueAt(jTableTheTransferRecord.getSelectedRow(), 2) + "\'s transfer record?");
                    if (click == 0) {
                        String s = "DELETE TransferRequests WHERE transferRequestNumber = ?";
                        pst = cn.prepareStatement(s);
                        pst.setString(1, (String) jTableTheTransferRecord.getValueAt(jTableTheTransferRecord.getSelectedRow(), 0));
                        pst.executeUpdate();
                        tblModel.getDataVector().removeAllElements();
                        loadTable();
                        txtEmpNumber.setText("");
                        txtEmpName.setText("");
                        rdbtnMale.setSelected(false);
                        rdbtnFemale.setSelected(false);
                        txtDateOfBirth.setDate(null);
                        txtAddress.setText("");
                        txtEmail.setText("");
                        txtDateStartWork.setDate(null);
                        txtRole.setText("");
                        txtCurrentDepartment.setText("");
                        cbbtdpm.setSelectedIndex(-1);
                        txtCurrentProject.setText("");
                        cbbtpj.setSelectedIndex(-1);
                        txtCurrentPosition.setText("");
                        cbbtpst.setSelectedIndex(-1);
                        txtJoiningDate.setDate(null);
                        txtRelievingDate.setDate(null);
                        txtAStatus.setText("");
                        btnDeleteRecord.setEnabled(false);
                        btnUpdateRecord.setEnabled(false);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheTransferRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteRecordActionPerformed

    private void btnResetRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetRecordActionPerformed
        btnDeleteRecord.setEnabled(false);
        btnUpdateRecord.setEnabled(false);
        txtEmpNumber.setText("");
        txtEmpName.setText("");
        rdbtnMale.setSelected(false);
        rdbtnFemale.setSelected(false);
        txtDateOfBirth.setDate(null);
        txtAddress.setText("");
        txtEmail.setText("");
        txtDateStartWork.setDate(null);
        txtRole.setText("");
        txtCurrentDepartment.setText("");
        cbbtdpm.setSelectedIndex(-1);
        txtCurrentProject.setText("");
        cbbtpj.setSelectedIndex(-1);
        txtCurrentPosition.setText("");
        cbbtpst.setSelectedIndex(-1);
        txtJoiningDate.setDate(null);
        txtRelievingDate.setDate(null);
        txtAStatus.setText("");
        tblModel.getDataVector().removeAllElements();
        loadTable();
    }//GEN-LAST:event_btnResetRecordActionPerformed

    private void jTableTheTransferRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTheTransferRecordMouseClicked
        btnDeleteRecord.setEnabled(true);
        btnUpdateRecord.setEnabled(true);
        int row = jTableTheTransferRecord.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtEmpNumber.setText((String) jTableTheTransferRecord.getValueAt(row, 1));
        txtEmpName.setText((String) jTableTheTransferRecord.getValueAt(row, 2));
        String gender = jTableTheTransferRecord.getValueAt(row, 3) + "";
        if (gender.equals("male")) {
            rdbtnMale.setSelected(true);
            rdbtnFemale.setSelected(false);
        } else {
            rdbtnFemale.setSelected(true);
            rdbtnMale.setSelected(false);
        }
        txtAddress.setText((String) jTableTheTransferRecord.getValueAt(row, 5));
        txtEmail.setText((String) jTableTheTransferRecord.getValueAt(row, 6));
        txtRole.setText((String) jTableTheTransferRecord.getValueAt(row, 8));
        txtCurrentDepartment.setText((String) jTableTheTransferRecord.getValueAt(row, 9));
        txtCurrentProject.setText((String) jTableTheTransferRecord.getValueAt(row, 10));
        txtCurrentPosition.setText((String) jTableTheTransferRecord.getValueAt(row, 11));
        cbbtdpm.setSelectedItem((String) jTableTheTransferRecord.getValueAt(row, 12));
        cbbtpj.setSelectedItem((String) jTableTheTransferRecord.getValueAt(row, 13));
        cbbtpst.setSelectedItem((String) jTableTheTransferRecord.getValueAt(row, 14));
        txtAStatus.setText((String) jTableTheTransferRecord.getValueAt(row, 17));
        try {
            txtDateOfBirth.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jTableTheTransferRecord.getValueAt(row, 4)));
            txtDateStartWork.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jTableTheTransferRecord.getValueAt(row, 7)));
            txtJoiningDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jTableTheTransferRecord.getValueAt(row, 15)));
            txtRelievingDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jTableTheTransferRecord.getValueAt(row, 16)));
        } catch (ParseException ex) {
            Logger.getLogger(TheTransferRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableTheTransferRecordMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteRecord;
    private javax.swing.JButton btnResetRecord;
    private javax.swing.JButton btnSearchRecord;
    private javax.swing.JButton btnUpdateRecord;
    private javax.swing.JComboBox<String> cbbtdpm;
    private javax.swing.JComboBox<String> cbbtpj;
    private javax.swing.JComboBox<String> cbbtpst;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelTheTransferRecord;
    private javax.swing.JScrollPane jScrollPaneNoteRecord;
    private javax.swing.JScrollPane jScrollPaneTheTransferRecord;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTheTransferRecord;
    private javax.swing.JLabel lbAddressRecord;
    private javax.swing.JLabel lbCurrentDepartmentRecord;
    private javax.swing.JLabel lbCurrentPositionRecord;
    private javax.swing.JLabel lbCurrentProjectRecord;
    private javax.swing.JLabel lbDateOfBirthRecord;
    private javax.swing.JLabel lbDateStartWorkRecord;
    private javax.swing.JLabel lbEmailRecord;
    private javax.swing.JLabel lbEmpNameRecord;
    private javax.swing.JLabel lbEmpNumberRecord;
    private javax.swing.JLabel lbGenderRecord;
    private javax.swing.JLabel lbRoleRecord;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTransferDepartmentRecord;
    private javax.swing.JLabel lbTransferJoiningDateRecord;
    private javax.swing.JLabel lbTransferPositionRecord;
    private javax.swing.JLabel lbTransferProjectRecord;
    private javax.swing.JLabel lbTransferRelievingDateRecord;
    private javax.swing.JRadioButton rdbtnFemale;
    private javax.swing.JRadioButton rdbtnMale;
    private javax.swing.JTextArea txtAStatus;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCurrentDepartment;
    private javax.swing.JTextField txtCurrentPosition;
    private javax.swing.JTextField txtCurrentProject;
    private com.toedter.calendar.JDateChooser txtDateOfBirth;
    private com.toedter.calendar.JDateChooser txtDateStartWork;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpNumber;
    private com.toedter.calendar.JDateChooser txtJoiningDate;
    private com.toedter.calendar.JDateChooser txtRelievingDate;
    private javax.swing.JTextField txtRole;
    // End of variables declaration//GEN-END:variables
}
