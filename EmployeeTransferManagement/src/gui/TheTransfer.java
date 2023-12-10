/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MY PC
 */
public final class TheTransfer extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel tblModelTransferDepartment;
    DefaultTableModel tblModelTransferProject;
    DefaultTableModel tblModelTransferPosition;
    DefaultTableModel tblModelEmployeeTransfer;
    DefaultTableModel tblModelAllowanceOfEmployee;
    Vector rowTransferDepartment;
    Vector rowTransferProject;
    Vector rowTransferPosition;
    Vector rowEmployeeTransfer;
    Vector rowAllowanceOfEmployee;
    server.DBHelper db = new server.DBHelper();
    Connection cn= db.getCon();
    
    /**
     * Creates new form TheTransfer 
     */
    public TheTransfer() {
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("image//a.jpg");
        this.setIconImage(img.getImage());
        btnDeleteDep.setEnabled(false);
        btnDeletePro.setEnabled(false);
        btnDeletePos.setEnabled(false);
        btnDeleteEmployeesRequestingTransfer.setEnabled(false);
        image();
//        setModel Transfer department table
        tblModelTransferDepartment = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelTransferDepartment.addColumn("Transfer department number");
        tblModelTransferDepartment.addColumn("Transfer department name");
        jTableTransferDepartment.setModel(tblModelTransferDepartment);
        LoadTableTransferDepartment();

//        setModel Transfer project table
        tblModelTransferProject = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelTransferProject.addColumn("Transfer project number");
        tblModelTransferProject.addColumn("Transfer project name");
        jTableTransferProject.setModel(tblModelTransferProject);
        LoadTableTransferProject();

//        setModel Transfer position table
        tblModelTransferPosition = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelTransferPosition.addColumn("Transfer prosition number");
        tblModelTransferPosition.addColumn("Transfer prosition name");
        tblModelTransferPosition.addColumn("Allowance level");
        tblModelTransferPosition.addColumn("Note");
        jTableTransferPosition.setModel(tblModelTransferPosition);
        LoadTableTransferPosition();

//        setModel Employee transfer table
        tblModelEmployeeTransfer = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelEmployeeTransfer.addColumn("EmpNumber");
        tblModelEmployeeTransfer.addColumn("EmpName");
        tblModelEmployeeTransfer.addColumn("Transfer department name");
        tblModelEmployeeTransfer.addColumn("Transfer project name");
        tblModelEmployeeTransfer.addColumn("Transfer position name");
        jTableEmployeesRequestingTransfer.setModel(tblModelEmployeeTransfer);
        LoadTableEmployeesRequestingTransfer();
        
//        setModel Allowance of employee table
        tblModelAllowanceOfEmployee = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelAllowanceOfEmployee.addColumn("EmpNumber");
        tblModelAllowanceOfEmployee.addColumn("EmpName");
        tblModelAllowanceOfEmployee.addColumn("Transfer position number");
        tblModelAllowanceOfEmployee.addColumn("Transfer position name");
        tblModelAllowanceOfEmployee.addColumn("Allowance level");
        tblModelAllowanceOfEmployee.addColumn("Transfer request status");
        tblModelAllowanceOfEmployee.addColumn("Note");
        jTableAllowanceOfEmployee.setModel(tblModelAllowanceOfEmployee);
        LoadTableAllowanceOfEmployee();
    }
    
    public void image(){
        String refesh = "image//refresh.png";
        String add = "image//add.png";
        String edit = "image//edit2.png";
        String delete = "image//deleted.png";
        btnResetDep.setSize(20,20);
        new SetImage().setImageButton(btnResetDep, refesh);
        btnResetPro.setSize(20,20);
        new SetImage().setImageButton(btnResetPro, refesh);
        btnResetPos.setSize(20,20);
        new SetImage().setImageButton(btnResetPos, refesh);
        btnResetEmp.setSize(20,20);
        new SetImage().setImageButton(btnResetEmp, refesh);
        btnResetEmpAllowance.setSize(20,20);
        new SetImage().setImageButton(btnResetEmpAllowance, refesh);
        btnAddDep.setSize(20,20);
        new SetImage().setImageButton(btnAddDep, add);
        btnAddPos.setSize(20,20);
        new SetImage().setImageButton(btnAddPos, add);
        btnAddPro.setSize(20,20);
        new SetImage().setImageButton(btnAddPro, add);
        btnEditPro.setSize(20,20);
        new SetImage().setImageButton(btnEditPro, edit);
        btnEditDep.setSize(20,20);
        new SetImage().setImageButton(btnEditDep, edit);
        btnEditPos.setSize(20,20);
        new SetImage().setImageButton(btnEditPos, edit);
        btnDeleteDep.setSize(20,20);
        new SetImage().setImageButton(btnDeleteDep, delete);
        btnDeletePro.setSize(20,20);
        new SetImage().setImageButton(btnDeletePro, delete);
        btnDeletePos.setSize(20,20);
        new SetImage().setImageButton(btnDeletePos, delete);
        btnDeleteEmployeesRequestingTransfer.setSize(20,20);
        new SetImage().setImageButton(btnDeleteEmployeesRequestingTransfer, delete);
    }
    
    public void LoadTableTransferDepartment() {
        try {
            ps = cn.prepareStatement("SELECT transferDepartmentNumber, transferDepartmentName FROM TransferDepartment");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowTransferDepartment = new Vector();
                rowTransferDepartment.add(rs.getString(1));
                rowTransferDepartment.add(rs.getString(2));
                tblModelTransferDepartment.addRow(rowTransferDepartment);
            }
            if (rowTransferDepartment.isEmpty()) {
                tblModelTransferDepartment.addRow(rowTransferDepartment);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableTransferDepartment.setModel(tblModelTransferDepartment);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableTransferProject() {
        try {
            ps = cn.prepareStatement("SELECT transferProjectNumber, transferProjectName FROM TransferProject");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowTransferProject = new Vector();
                rowTransferProject.add(rs.getString(1));
                rowTransferProject.add(rs.getString(2));
                tblModelTransferProject.addRow(rowTransferProject);
            }
            if (rowTransferProject.isEmpty()) {
                tblModelTransferProject.addRow(rowTransferProject);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableTransferProject.setModel(tblModelTransferProject);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableTransferPosition() {
        try {
            ps = cn.prepareStatement("SELECT transferPositionNumber, transferPositionName, allowanceLevel, note FROM TransferPosition");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowTransferPosition = new Vector();
                rowTransferPosition.add(rs.getString(1));
                rowTransferPosition.add(rs.getString(2));
                rowTransferPosition.add(rs.getString(3));
                rowTransferPosition.add(rs.getString(4));
                tblModelTransferPosition.addRow(rowTransferPosition);
            }
            if (rowTransferPosition.isEmpty()) {
                tblModelTransferPosition.addRow(rowTransferPosition);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableTransferPosition.setModel(tblModelTransferPosition);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableEmployeesRequestingTransfer() {
        try {
            ps = cn.prepareStatement("SELECT e.empNumber, e.fullname, tdpm.transferDepartmentName, tpj.transferProjectName, tpst.transferPositionName FROM Employee e INNER JOIN TransferRequests trq ON e.empNumber = trq.empNumber JOIN TransferPosition tpst ON trq.transferPositionNumber = tpst.transferPositionNumber JOIN TransferDepartment tdpm ON trq.transferDepartmentNumber = tdpm.transferDepartmentNumber JOIN TransferProject tpj ON trq.transferProjectNumber = tpj.transferProjectNumber WHERE trq.status = 'Waiting Approval'");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowEmployeeTransfer = new Vector();
                rowEmployeeTransfer.add(rs.getString(1));
                rowEmployeeTransfer.add(rs.getString(2));
                rowEmployeeTransfer.add(rs.getString(3));
                rowEmployeeTransfer.add(rs.getString(4));
                rowEmployeeTransfer.add(rs.getString(5));
                tblModelEmployeeTransfer.addRow(rowEmployeeTransfer);
            }
            if (rowEmployeeTransfer.isEmpty()) {
                tblModelEmployeeTransfer.addRow(rowEmployeeTransfer);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableEmployeesRequestingTransfer.setModel(tblModelEmployeeTransfer);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadTableAllowanceOfEmployee() {
        try {
            ps = cn.prepareStatement("SELECT e.empNumber, e.fullname, tpst.transferPositionNumber, tpst.transferPositionName, tpst.allowanceLevel, trq.status, tpst.note FROM Employee e INNER JOIN TransferRequests trq ON e.empNumber = trq.empNumber JOIN TransferPosition tpst ON trq.transferPositionNumber = tpst.transferPositionNumber");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowAllowanceOfEmployee = new Vector();
                rowAllowanceOfEmployee.add(rs.getString(1));
                rowAllowanceOfEmployee.add(rs.getString(2));
                rowAllowanceOfEmployee.add(rs.getString(3));
                rowAllowanceOfEmployee.add(rs.getString(4));
                rowAllowanceOfEmployee.add(rs.getString(5));
                rowAllowanceOfEmployee.add(rs.getString(6));
                rowAllowanceOfEmployee.add(rs.getString(7));
                tblModelAllowanceOfEmployee.addRow(rowAllowanceOfEmployee);
            }
            if (rowAllowanceOfEmployee.isEmpty()) {
                tblModelAllowanceOfEmployee.addRow(rowAllowanceOfEmployee);
//                
//
            }
            jTableAllowanceOfEmployee.setModel(tblModelAllowanceOfEmployee);

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

        jpnTransfer = new javax.swing.JPanel();
        jPnTransfer = new javax.swing.JTabbedPane();
        jPnTransferDepartment = new javax.swing.JPanel();
        jScrollPane0 = new javax.swing.JScrollPane();
        jTableTransferDepartment = new javax.swing.JTable();
        panelControlTDep = new javax.swing.JPanel();
        lbNumberDep = new javax.swing.JLabel();
        txtTransferDepartmentNumber = new javax.swing.JTextField();
        lbNameDep = new javax.swing.JLabel();
        txtTransferDepartmentName = new javax.swing.JTextField();
        btnResetDep = new javax.swing.JButton();
        btnAddDep = new javax.swing.JButton();
        btnEditDep = new javax.swing.JButton();
        btnDeleteDep = new javax.swing.JButton();
        jPnTransferProject = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTransferProject = new javax.swing.JTable();
        panelControlPro = new javax.swing.JPanel();
        lbNumberPro = new javax.swing.JLabel();
        txtTransferProjectNumber = new javax.swing.JTextField();
        lbNamePro = new javax.swing.JLabel();
        txtTransferProjectName = new javax.swing.JTextField();
        btnResetPro = new javax.swing.JButton();
        btnAddPro = new javax.swing.JButton();
        btnEditPro = new javax.swing.JButton();
        btnDeletePro = new javax.swing.JButton();
        jPnTransferPosition = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTransferPosition = new javax.swing.JTable();
        panelControlPos = new javax.swing.JPanel();
        lbNumberPos = new javax.swing.JLabel();
        txtTransferPositionNumber = new javax.swing.JTextField();
        lbNamePos = new javax.swing.JLabel();
        txtTransferPositionName = new javax.swing.JTextField();
        lbAllowanceLevel = new javax.swing.JLabel();
        txtAllowanceLevel = new javax.swing.JTextField();
        lbNoteTransferDepartment = new javax.swing.JLabel();
        btnAddPos = new javax.swing.JButton();
        btnEditPos = new javax.swing.JButton();
        btnDeletePos = new javax.swing.JButton();
        btnResetPos = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNoteTransferPosition = new javax.swing.JTextArea();
        jPnEmployeesRequestingTransfer = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEmployeesRequestingTransfer = new javax.swing.JTable();
        panleControlEmp = new javax.swing.JPanel();
        lbKeywordEmp = new javax.swing.JLabel();
        txtKeywordEmployeeTransfer = new javax.swing.JTextField();
        btnDeleteEmployeesRequestingTransfer = new javax.swing.JButton();
        btnResetEmp = new javax.swing.JButton();
        jPnTheAllowances = new javax.swing.JPanel();
        lbtitleEmployeeTheAllowances = new javax.swing.JLabel();
        jScrollPaneEmployeeTheAllowances = new javax.swing.JScrollPane();
        jTableAllowanceOfEmployee = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbKeywordTheAllowances = new javax.swing.JLabel();
        txtKeywordTheAllowances = new javax.swing.JTextField();
        btnResetEmpAllowance = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THE TRANSFER");
        setMinimumSize(new java.awt.Dimension(940, 575));

        jPnTransferDepartment.setMinimumSize(new java.awt.Dimension(1000, 575));
        jPnTransferDepartment.setPreferredSize(new java.awt.Dimension(900, 575));

        jTableTransferDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTableTransferDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransferDepartmentMouseClicked(evt);
            }
        });
        jScrollPane0.setViewportView(jTableTransferDepartment);

        lbNumberDep.setText("Transfer department number:");

        lbNameDep.setText("Transfer department name:");

        btnResetDep.setText("Reset");
        btnResetDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDepActionPerformed(evt);
            }
        });

        btnAddDep.setText("Add");
        btnAddDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDepActionPerformed(evt);
            }
        });

        btnEditDep.setText("Edit");
        btnEditDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDepActionPerformed(evt);
            }
        });

        btnDeleteDep.setText("Delete");
        btnDeleteDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlTDepLayout = new javax.swing.GroupLayout(panelControlTDep);
        panelControlTDep.setLayout(panelControlTDepLayout);
        panelControlTDepLayout.setHorizontalGroup(
            panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlTDepLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNumberDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNameDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTransferDepartmentNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtTransferDepartmentName))
                .addGap(18, 18, 18)
                .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlTDepLayout.createSequentialGroup()
                        .addComponent(btnAddDep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditDep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteDep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetDep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        panelControlTDepLayout.setVerticalGroup(
            panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlTDepLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNumberDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTransferDepartmentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetDep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNameDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelControlTDepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTransferDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddDep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditDep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteDep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnTransferDepartmentLayout = new javax.swing.GroupLayout(jPnTransferDepartment);
        jPnTransferDepartment.setLayout(jPnTransferDepartmentLayout);
        jPnTransferDepartmentLayout.setHorizontalGroup(
            jPnTransferDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferDepartmentLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPnTransferDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelControlTDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane0))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        jPnTransferDepartmentLayout.setVerticalGroup(
            jPnTransferDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferDepartmentLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane0, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelControlTDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPnTransfer.addTab("Transfer Department", jPnTransferDepartment);

        jTableTransferProject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableTransferProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransferProjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTransferProject);

        lbNumberPro.setText("Transfer project number:");

        lbNamePro.setText("Transfer project name:");

        btnResetPro.setText("Reset");
        btnResetPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProActionPerformed(evt);
            }
        });

        btnAddPro.setText("Add");
        btnAddPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProActionPerformed(evt);
            }
        });

        btnEditPro.setText("Edit");
        btnEditPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProActionPerformed(evt);
            }
        });

        btnDeletePro.setText("Delete");
        btnDeletePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlProLayout = new javax.swing.GroupLayout(panelControlPro);
        panelControlPro.setLayout(panelControlProLayout);
        panelControlProLayout.setHorizontalGroup(
            panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlProLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNamePro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNumberPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTransferProjectNumber)
                    .addComponent(txtTransferProjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlProLayout.createSequentialGroup()
                        .addComponent(btnAddPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletePro))
                    .addComponent(btnResetPro))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelControlProLayout.setVerticalGroup(
            panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlProLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNumberPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTransferProjectNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetPro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNamePro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelControlProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTransferProjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnAddPro)
                        .addComponent(btnEditPro)
                        .addComponent(btnDeletePro)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnTransferProjectLayout = new javax.swing.GroupLayout(jPnTransferProject);
        jPnTransferProject.setLayout(jPnTransferProjectLayout);
        jPnTransferProjectLayout.setHorizontalGroup(
            jPnTransferProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferProjectLayout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addGroup(jPnTransferProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelControlPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPnTransferProjectLayout.setVerticalGroup(
            jPnTransferProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferProjectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelControlPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jPnTransfer.addTab("Transfer Project", jPnTransferProject);

        jTableTransferPosition.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableTransferPosition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransferPositionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTransferPosition);

        lbNumberPos.setText("Transfer position number:");

        lbNamePos.setText("Transfer position name:");

        lbAllowanceLevel.setText("Allowance level:");

        lbNoteTransferDepartment.setText("Note:");

        btnAddPos.setText("Add");
        btnAddPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPosActionPerformed(evt);
            }
        });

        btnEditPos.setText("Edit");
        btnEditPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPosActionPerformed(evt);
            }
        });

        btnDeletePos.setText("Delete");
        btnDeletePos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePosActionPerformed(evt);
            }
        });

        btnResetPos.setText("Reset");
        btnResetPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPosActionPerformed(evt);
            }
        });

        txtNoteTransferPosition.setColumns(20);
        txtNoteTransferPosition.setRows(5);
        jScrollPane4.setViewportView(txtNoteTransferPosition);

        javax.swing.GroupLayout panelControlPosLayout = new javax.swing.GroupLayout(panelControlPos);
        panelControlPos.setLayout(panelControlPosLayout);
        panelControlPosLayout.setHorizontalGroup(
            panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlPosLayout.createSequentialGroup()
                .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlPosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNamePos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNumberPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTransferPositionNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtTransferPositionName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNoteTransferDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAllowanceLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelControlPosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnAddPos)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditPos)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletePos)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetPos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(txtAllowanceLevel))
                .addContainerGap())
        );
        panelControlPosLayout.setVerticalGroup(
            panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlPosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTransferPositionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbAllowanceLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAllowanceLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNumberPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlPosLayout.createSequentialGroup()
                        .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNamePos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTransferPositionName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNoteTransferDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelControlPosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditPos)
                            .addComponent(btnResetPos)
                            .addComponent(btnAddPos)
                            .addComponent(btnDeletePos)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnTransferPositionLayout = new javax.swing.GroupLayout(jPnTransferPosition);
        jPnTransferPosition.setLayout(jPnTransferPositionLayout);
        jPnTransferPositionLayout.setHorizontalGroup(
            jPnTransferPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferPositionLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPnTransferPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addComponent(panelControlPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPnTransferPositionLayout.setVerticalGroup(
            jPnTransferPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTransferPositionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelControlPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPnTransfer.addTab("Transfer Position", jPnTransferPosition);

        jTableEmployeesRequestingTransfer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableEmployeesRequestingTransfer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmployeesRequestingTransferMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableEmployeesRequestingTransfer);

        lbKeywordEmp.setText("Keyword:");

        txtKeywordEmployeeTransfer.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKeywordEmployeeTransferCaretUpdate(evt);
            }
        });

        btnDeleteEmployeesRequestingTransfer.setText("Delete");
        btnDeleteEmployeesRequestingTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEmployeesRequestingTransferActionPerformed(evt);
            }
        });

        btnResetEmp.setText("Reset");
        btnResetEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panleControlEmpLayout = new javax.swing.GroupLayout(panleControlEmp);
        panleControlEmp.setLayout(panleControlEmpLayout);
        panleControlEmpLayout.setHorizontalGroup(
            panleControlEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panleControlEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbKeywordEmp)
                .addGap(18, 18, 18)
                .addComponent(txtKeywordEmployeeTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteEmployeesRequestingTransfer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetEmp)
                .addContainerGap())
        );
        panleControlEmpLayout.setVerticalGroup(
            panleControlEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panleControlEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panleControlEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panleControlEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteEmployeesRequestingTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKeywordEmployeeTransfer)
                    .addComponent(lbKeywordEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnEmployeesRequestingTransferLayout = new javax.swing.GroupLayout(jPnEmployeesRequestingTransfer);
        jPnEmployeesRequestingTransfer.setLayout(jPnEmployeesRequestingTransferLayout);
        jPnEmployeesRequestingTransferLayout.setHorizontalGroup(
            jPnEmployeesRequestingTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEmployeesRequestingTransferLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPnEmployeesRequestingTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panleControlEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPnEmployeesRequestingTransferLayout.setVerticalGroup(
            jPnEmployeesRequestingTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEmployeesRequestingTransferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panleControlEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPnTransfer.addTab("Employee Requesting Transfer ", jPnEmployeesRequestingTransfer);

        lbtitleEmployeeTheAllowances.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtitleEmployeeTheAllowances.setText("Employee's allowances");

        jTableAllowanceOfEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableAllowanceOfEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAllowanceOfEmployeeMouseClicked(evt);
            }
        });
        jScrollPaneEmployeeTheAllowances.setViewportView(jTableAllowanceOfEmployee);

        lbKeywordTheAllowances.setText("Keyword:");

        txtKeywordTheAllowances.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKeywordTheAllowancesCaretUpdate(evt);
            }
        });

        btnResetEmpAllowance.setText("Reset");
        btnResetEmpAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetEmpAllowanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbKeywordTheAllowances)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKeywordTheAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnResetEmpAllowance)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKeywordTheAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKeywordTheAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetEmpAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnTheAllowancesLayout = new javax.swing.GroupLayout(jPnTheAllowances);
        jPnTheAllowances.setLayout(jPnTheAllowancesLayout);
        jPnTheAllowancesLayout.setHorizontalGroup(
            jPnTheAllowancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTheAllowancesLayout.createSequentialGroup()
                .addGroup(jPnTheAllowancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTheAllowancesLayout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(lbtitleEmployeeTheAllowances))
                    .addGroup(jPnTheAllowancesLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPnTheAllowancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPaneEmployeeTheAllowances))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPnTheAllowancesLayout.setVerticalGroup(
            jPnTheAllowancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTheAllowancesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtitleEmployeeTheAllowances)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneEmployeeTheAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jPnTransfer.addTab("The allowances", jPnTheAllowances);

        javax.swing.GroupLayout jpnTransferLayout = new javax.swing.GroupLayout(jpnTransfer);
        jpnTransfer.setLayout(jpnTransferLayout);
        jpnTransferLayout.setHorizontalGroup(
            jpnTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTransferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jpnTransferLayout.setVerticalGroup(
            jpnTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTransferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnTransfer)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnTransfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKeywordEmployeeTransferCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKeywordEmployeeTransferCaretUpdate
        try {
            boolean flag = false;
            String query = "SELECT e.empNumber, e.fullname, tdpm.transferDepartmentName, tpj.transferProjectName, tpst.transferPositionName FROM Employee e INNER JOIN TransferRequests trq ON e.empNumber = trq.empNumber JOIN TransferPosition tpst ON trq.transferPositionNumber = tpst.transferPositionNumber JOIN TransferDepartment tdpm ON trq.transferDepartmentNumber = tdpm.transferDepartmentNumber JOIN TransferProject tpj ON trq.transferProjectNumber = tpj.transferProjectNumber WHERE e.empNumber LIKE '" + txtKeywordEmployeeTransfer.getText() + "%' or e.fullname LIKE '" + txtKeywordEmployeeTransfer.getText() + "%' or tdpm.transferDepartmentName LIKE '" + txtKeywordEmployeeTransfer.getText() + "%' or tpj.transferProjectName LIKE '" + txtKeywordEmployeeTransfer.getText() + "%' or tpst.transferPositionName LIKE '" + txtKeywordEmployeeTransfer.getText() + "%'";
            Statement st = cn.createStatement();
            rs = st.executeQuery(query);

            while (true) {
                tblModelEmployeeTransfer.getDataVector().removeAllElements();
                Statement stm = cn.createStatement();
                rs = stm.executeQuery(query);

                rowEmployeeTransfer = new Vector();
                while (rs.next()) {
                    rowEmployeeTransfer = new Vector();
                    rowEmployeeTransfer.add(rs.getString(1));
                    rowEmployeeTransfer.add(rs.getString(2));
                    rowEmployeeTransfer.add(rs.getString(3));
                    rowEmployeeTransfer.add(rs.getString(4));
                    rowEmployeeTransfer.add(rs.getString(5));
                    tblModelEmployeeTransfer.addRow(rowEmployeeTransfer);
                }
                if (rowEmployeeTransfer.isEmpty()) {
                    tblModelEmployeeTransfer.addRow(rowEmployeeTransfer);
                }
                flag = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtKeywordEmployeeTransferCaretUpdate

    private void btnDeleteEmployeesRequestingTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEmployeesRequestingTransferActionPerformed
        if(txtKeywordEmployeeTransfer.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter employee number is required.");
            txtKeywordEmployeeTransfer.requestFocus();
        } else {
            try {
                ps = cn.prepareStatement("SELECT empNumber FROM TransferRequests WHERE empNumber = ?");
                ps.setString(1, txtKeywordEmployeeTransfer.getText().trim());
                rs = ps.executeQuery();
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Employee number does not exist.");
                    txtKeywordEmployeeTransfer.requestFocus();
                } else {
                    int click1 = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?");
                    if(click1 == 0){
                        String query = "DELETE TransferRequests WHERE empNumber = ?";
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setString(1, txtKeywordEmployeeTransfer.getText().trim());
                        ps.executeUpdate();
                        tblModelEmployeeTransfer.getDataVector().removeAllElements();
                        LoadTableEmployeesRequestingTransfer();
                        txtKeywordEmployeeTransfer.setText("");
                        btnDeleteEmployeesRequestingTransfer.setEnabled(false);
                    }
                }   
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteEmployeesRequestingTransferActionPerformed

    private void btnResetDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDepActionPerformed
        txtTransferDepartmentNumber.setText("");
        txtTransferDepartmentName.setText("");
        tblModelTransferDepartment.getDataVector().removeAllElements();
        LoadTableTransferDepartment();
    }//GEN-LAST:event_btnResetDepActionPerformed

    private void btnAddDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDepActionPerformed
        String reg = "^TD[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferDepartmentNumber.getText());
        if(txtTransferDepartmentNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer department number is required.");
            txtTransferDepartmentNumber.requestFocus();
        } else if (!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer department number must start with \"TD000\".");
            txtTransferDepartmentNumber.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferDepartmentNumber FROM TransferDepartment WHERE transferDepartmentNumber= '"+txtTransferDepartmentNumber.getText().trim()+"'");
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer department number already exists.");
                    txtTransferDepartmentNumber.requestFocus();
                } else if(txtTransferDepartmentName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter transfer department name is required.");
                    txtTransferDepartmentName.requestFocus();
                } else {
                    Statement st1 = cn.createStatement();
                    rs = st1.executeQuery("SELECT transferDepartmentName FROM TransferDepartment WHERE transferDepartmentName= '"+txtTransferDepartmentName.getText().trim()+"'");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this, "Transfer department name already exists.");
                        txtTransferDepartmentName.requestFocus();
                    } else {
                        ps = cn.prepareStatement("INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES (?, ?)");
                        ps.setString(1, txtTransferDepartmentNumber.getText().trim());
                        ps.setString(2, txtTransferDepartmentName.getText().trim());
                        ps.executeUpdate();
                        tblModelTransferDepartment.getDataVector().removeAllElements();
                        LoadTableTransferDepartment();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddDepActionPerformed

    private void btnEditDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDepActionPerformed
        String reg = "^TD[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferDepartmentNumber.getText());
        if(txtTransferDepartmentNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer department number is required.");
            txtTransferDepartmentNumber.requestFocus();
        } else if (!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer department number must start with \"TD000\".");
            txtTransferDepartmentNumber.requestFocus();
        } else{
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferDepartmentNumber FROM TransferDepartment WHERE transferDepartmentNumber= '"+txtTransferDepartmentNumber.getText().trim()+"'");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer department number does not exist.");
                    txtTransferDepartmentNumber.requestFocus();
                }  else if(txtTransferDepartmentName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter transfer department name is required.");
                    txtTransferDepartmentName.requestFocus();
                }else {
                    ps = cn.prepareStatement("UPDATE [dbo].[TransferDepartment] SET [transferDepartmentName]= ? WHERE [transferDepartmentNumber]=?");
                    ps.setString(1, txtTransferDepartmentName.getText().trim());
                    ps.setString(2, txtTransferDepartmentNumber.getText().trim());
                    ps.executeUpdate();
                    tblModelTransferDepartment.getDataVector().removeAllElements();
                    LoadTableTransferDepartment();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEditDepActionPerformed

    private void btnDeleteDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDepActionPerformed
        if (txtTransferDepartmentNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer department number is required to delete.");
            txtTransferDepartmentNumber.requestFocus();
        } else if(txtTransferDepartmentName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer department name is required to delete.");
            txtTransferDepartmentName.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferDepartmentNumber, transferDepartmentName FROM TransferDepartment WHERE transferDepartmentNumber = '"+txtTransferDepartmentNumber.getText().trim()+"' and transferDepartmentName = '"+txtTransferDepartmentName.getText().trim()+"'");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer department number and transfer department name does not exist.");
                    txtTransferDepartmentNumber.requestFocus();
                } else {
                    PreparedStatement pre = cn.prepareStatement("SELECT transferDepartmentNumber FROM TransferRequests WHERE transferDepartmentNumber = ?");
                    pre.setString(1, txtTransferDepartmentNumber.getText().trim());
                    ResultSet r = pre.executeQuery();
                    if(r.next()){
                        int click = JOptionPane.showConfirmDialog(this, "The employee is requesting transfer of this department.\n If you delete this department, the employee who requested the transfer will also delete from Employees Requesting Transfer.\nAre you sure you want to delete this transfer department?");
                        if (click == 0){
                            PreparedStatement ps1 = cn.prepareStatement("DELETE TransferRequests WHERE transferDepartmentNumber= ?");
                            ps1.setString(1, txtTransferDepartmentNumber.getText().trim());
                            ps1.executeUpdate();
                            PreparedStatement ps2 = cn.prepareStatement("DELETE TransferDepartment WHERE transferDepartmentNumber= ? and transferDepartmentName= ?");
                            ps2.setString(1, txtTransferDepartmentNumber.getText().trim());
                            ps2.setString(2, txtTransferDepartmentName.getText().trim());
                            ps2.executeUpdate();
                            tblModelTransferDepartment.getDataVector().removeAllElements();
                            LoadTableTransferDepartment();
                            txtTransferDepartmentNumber.setText("");
                            txtTransferDepartmentName.setText("");
                            tblModelEmployeeTransfer.getDataVector().removeAllElements();
                            LoadTableEmployeesRequestingTransfer();
                            btnDeleteDep.setEnabled(false);
                        }
                    } else {
                        int click1 = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this transfer department?");
                        if(click1 == 0){
                            ps = cn.prepareStatement("DELETE TransferDepartment WHERE transferDepartmentNumber= ? and transferDepartmentName= ?");
                            ps.setString(1, txtTransferDepartmentNumber.getText().trim());
                            ps.setString(2, txtTransferDepartmentName.getText().trim());
                            ps.executeUpdate();
                            tblModelTransferDepartment.getDataVector().removeAllElements();
                            LoadTableTransferDepartment();
                            txtTransferDepartmentNumber.setText("");
                            txtTransferDepartmentName.setText("");
                            btnDeleteDep.setEnabled(false);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteDepActionPerformed

    private void jTableTransferDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransferDepartmentMouseClicked
        btnDeleteDep.setEnabled(true);
        int row = jTableTransferDepartment.getSelectedRow();
        if(row == -1) return;
        txtTransferDepartmentNumber.setText((String) jTableTransferDepartment.getValueAt(row, 0));
        txtTransferDepartmentName.setText((String) jTableTransferDepartment.getValueAt(row, 1));
    }//GEN-LAST:event_jTableTransferDepartmentMouseClicked

    private void jTableTransferProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransferProjectMouseClicked
        btnDeletePro.setEnabled(true);
        int row = jTableTransferProject.getSelectedRow();
        if(row == -1) return;
        txtTransferProjectNumber.setText((String) jTableTransferProject.getValueAt(row, 0));
        txtTransferProjectName.setText((String) jTableTransferProject.getValueAt(row, 1));
    }//GEN-LAST:event_jTableTransferProjectMouseClicked

    private void jTableTransferPositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransferPositionMouseClicked
        btnDeletePos.setEnabled(true);
        int row = jTableTransferPosition.getSelectedRow();
        if(row == -1) return;
        txtTransferPositionNumber.setText((String) jTableTransferPosition.getValueAt(row, 0));
        txtTransferPositionName.setText((String) jTableTransferPosition.getValueAt(row, 1));
        txtAllowanceLevel.setText((String) jTableTransferPosition.getValueAt(row, 2));
        txtNoteTransferPosition.setText((String) jTableTransferPosition.getValueAt(row, 3));
    }//GEN-LAST:event_jTableTransferPositionMouseClicked

    private void btnAddPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPosActionPerformed
        String reg = "^TPS[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferPositionNumber.getText());
        String reg1 = "^[0-9]+$";
        Pattern p1 = Pattern.compile(reg1);
        Matcher m1 = p1.matcher(txtAllowanceLevel.getText());
        if(txtTransferPositionNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer position number is required.");
            txtTransferPositionNumber.requestFocus();
        } else if(!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer position number must start with \"TPS000\".");
            txtTransferPositionNumber.requestFocus();
        } else if(txtTransferPositionName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer position name is required.");
            txtTransferPositionName.requestFocus();
        } else if(txtAllowanceLevel.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter allowance level is required.");
            txtAllowanceLevel.requestFocus();
        } else if(!m1.find()){
            JOptionPane.showMessageDialog(this, "Allowance level must be number.");
            txtAllowanceLevel.requestFocus();
        } else {
            try {
                PreparedStatement pre = cn.prepareStatement("SELECT transferPositionNumber FROM TransferPosition WHERE transferPositionNumber= ?");
                pre.setString(1, txtTransferPositionNumber.getText().trim());
                rs = pre.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer position number already exists.");
                    txtTransferPositionNumber.requestFocus();
                } else {
                    PreparedStatement pre1 = cn.prepareStatement("SELECT transferPositionName FROM TransferPosition WHERE transferPositionName= ?");
                    pre1.setString(1, txtTransferPositionName.getText().trim());
                    rs = pre1.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this, "Transfer position name already exists.");
                        txtTransferPositionName.requestFocus();
                    }
                    else {
                        ps = cn.prepareStatement("INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES (?, ?, ?, ?)");
                        ps.setString(1, txtTransferPositionNumber.getText().trim());
                        ps.setString(2, txtTransferPositionName.getText().trim());
                        ps.setString(3, txtAllowanceLevel.getText().trim());
                        ps.setString(4, txtNoteTransferPosition.getText().trim());
                        ps.executeUpdate();
                        tblModelTransferPosition.getDataVector().removeAllElements();
                        LoadTableTransferPosition();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddPosActionPerformed

    private void btnEditPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPosActionPerformed
        String reg = "^TPS[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferPositionNumber.getText());
        String reg1 = "^[0-9]+[\\.]0000$";
        Pattern p1 = Pattern.compile(reg1);
        Matcher m1 = p1.matcher(txtAllowanceLevel.getText());
        if(txtTransferPositionNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer position number is required.");
            txtTransferPositionNumber.requestFocus();
        } else if(!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer position number must start with \"TPS000\".");
            txtTransferPositionNumber.requestFocus();
        } else {
            try {
                ps = cn.prepareStatement("SELECT transferPositionNumber FROM TransferPosition WHERE transferPositionNumber= ?");
                ps.setString(1, txtTransferPositionNumber.getText().trim());
                rs = ps.executeQuery();
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer position number does not exists.");
                    txtTransferPositionNumber.requestFocus();
                } else if(txtTransferPositionName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter transfer position name is required.");
                    txtTransferPositionName.requestFocus();
                } else if(txtAllowanceLevel.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter allowance level is required.");
                    txtAllowanceLevel.requestFocus();
                } else if(!m1.find()){
                    JOptionPane.showMessageDialog(this, "Allowance level must be in \"___.0000\" format");
                    txtAllowanceLevel.requestFocus();
                } else {
                    PreparedStatement pre2 = cn.prepareStatement("UPDATE [dbo].[TransferPosition] SET [transferPositionName]=?, [allowanceLevel]= ?, [note]= ? WHERE [transferPositionNumber] =?");
                    pre2.setString(1, txtTransferPositionName.getText().trim());
                    pre2.setString(2, txtAllowanceLevel.getText().trim());
                    pre2.setString(3, txtNoteTransferPosition.getText().trim());
                    pre2.setString(4, txtTransferPositionNumber.getText().trim());
                    pre2.executeUpdate();
                    tblModelTransferPosition.getDataVector().removeAllElements();
                    LoadTableTransferPosition();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnEditPosActionPerformed

    private void btnDeletePosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePosActionPerformed
        if (txtTransferPositionNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer position number is required to delete.");
            txtTransferPositionNumber.requestFocus();
        } else if(txtTransferPositionName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer position name is required to delete.");
            txtTransferPositionName.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferPositionNumber, transferPositionName FROM TransferPosition WHERE transferPositionNumber = '"+txtTransferPositionNumber.getText().trim()+"' and transferPositionName = '"+txtTransferPositionName.getText().trim()+"'");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer position number and transfer position name does not exist.");
                    txtTransferPositionNumber.requestFocus();
                } else {
                    PreparedStatement pre = cn.prepareStatement("SELECT transferPositionNumber FROM TransferRequests WHERE transferPositionNumber = ?");
                    pre.setString(1, txtTransferPositionNumber.getText().trim());
                    ResultSet r = pre.executeQuery();
                    if(r.next()){
                        int click = JOptionPane.showConfirmDialog(this, "The employee is requesting transfer of this position.\n If you delete this position, the employee who requested the transfer will also delete from Employees Requesting Transfer.\nAre you sure you want to delete?");
                        if (click == 0){
                            PreparedStatement ps1 = cn.prepareStatement("DELETE TransferRequests WHERE transferPositionNumber= ?");
                            ps1.setString(1, txtTransferPositionNumber.getText().trim());
                            ps1.executeUpdate();
                            PreparedStatement ps2 = cn.prepareStatement("DELETE TransferPosition WHERE transferPositionNumber= ?");
                            ps2.setString(1, txtTransferPositionNumber.getText().trim());
                            ps2.executeUpdate();
                            tblModelTransferPosition.getDataVector().removeAllElements();
                            LoadTableTransferPosition();
                            txtTransferPositionNumber.setText("");
                            txtTransferPositionName.setText("");
                            tblModelEmployeeTransfer.getDataVector().removeAllElements();
                            LoadTableEmployeesRequestingTransfer();
                            btnDeletePos.setEnabled(false);
                        }
                    } else {
                        int click = JOptionPane.showConfirmDialog(null, "Are you sure this transfer position?");
                        if(click == 0){
                            ps = cn.prepareStatement("DELETE TransferPosition WHERE transferPositionNumber= ? and transferPositionName= ?");
                            ps.setString(1, txtTransferPositionNumber.getText().trim());
                            ps.setString(2, txtTransferPositionName.getText().trim());
                            ps.executeUpdate();
                            tblModelTransferPosition.getDataVector().removeAllElements();
                            LoadTableTransferPosition();
                            txtTransferPositionNumber.setText("");
                            txtTransferPositionName.setText("");
                            btnDeletePos.setEnabled(false);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeletePosActionPerformed

    private void btnResetPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPosActionPerformed
        txtTransferPositionNumber.setText("");
        txtTransferPositionName.setText("");
        txtAllowanceLevel.setText("");
        txtNoteTransferPosition.setText("");
        tblModelTransferPosition.getDataVector().removeAllElements();
        LoadTableTransferPosition();
    }//GEN-LAST:event_btnResetPosActionPerformed

    private void jTableEmployeesRequestingTransferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmployeesRequestingTransferMouseClicked
        btnDeleteEmployeesRequestingTransfer.setEnabled(true);
        int row = jTableEmployeesRequestingTransfer.getSelectedRow();
        if(row == -1) return;
        txtKeywordEmployeeTransfer.setText((String) jTableEmployeesRequestingTransfer.getValueAt(row, 0));
    }//GEN-LAST:event_jTableEmployeesRequestingTransferMouseClicked

    private void btnResetEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetEmpActionPerformed
        txtKeywordEmployeeTransfer.setText("");
        tblModelEmployeeTransfer.getDataVector().removeAllElements();
        LoadTableEmployeesRequestingTransfer();
    }//GEN-LAST:event_btnResetEmpActionPerformed

    private void btnResetProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProActionPerformed
        txtTransferProjectNumber.setText("");
        txtTransferProjectName.setText("");
        tblModelTransferProject.getDataVector().removeAllElements();
        LoadTableTransferProject();
    }//GEN-LAST:event_btnResetProActionPerformed

    private void btnAddProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProActionPerformed
        String reg = "^TPJ[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferProjectNumber.getText());
        if(txtTransferProjectNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer project number is required.");
            txtTransferProjectNumber.requestFocus();
        } else if (!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer project number must start with \"TPJ000\".");
            txtTransferProjectNumber.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferProjectNumber FROM TransferProject WHERE transferProjectNumber= '"+txtTransferProjectNumber.getText().trim()+"'");
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer project number already exists.");
                    txtTransferProjectNumber.requestFocus();
                } else if(txtTransferProjectName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter transfer project name is required.");
                    txtTransferProjectName.requestFocus();
                } else {
                    Statement st1 = cn.createStatement();
                    rs = st1.executeQuery("SELECT transferProjectName FROM TransferProject WHERE transferProjectName= '"+txtTransferProjectName.getText().trim()+"'");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this, "Transfer project name already exists.");
                        txtTransferProjectName.requestFocus();
                    } else {
                        ps = cn.prepareStatement("INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES (?, ?)");
                        ps.setString(1, txtTransferProjectNumber.getText().trim());
                        ps.setString(2, txtTransferProjectName.getText().trim());
                        ps.executeUpdate();
                        tblModelTransferProject.getDataVector().removeAllElements();
                        LoadTableTransferProject();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddProActionPerformed

    private void btnEditProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProActionPerformed
        String reg = "^TPJ[0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(txtTransferProjectNumber.getText());
        if(txtTransferProjectNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer project number is required.");
            txtTransferProjectNumber.requestFocus();
        } else if (!m.find()){
            JOptionPane.showMessageDialog(this, "Transfer project number must start with \"TPJ000\".");
            txtTransferProjectNumber.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferProjectNumber FROM TransferProject WHERE transferProjectNumber= '"+txtTransferProjectNumber.getText().trim()+"'");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer project number does not exist.");
                    txtTransferProjectNumber.requestFocus();
                } else if(txtTransferProjectName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter transfer project name is required.");
                    txtTransferProjectName.requestFocus();
                } else {
                    ps = cn.prepareStatement("UPDATE [dbo].[TransferProject] SET [transferProjectName]= ? WHERE [transferProjectNumber]= ?");
                    ps.setString(1, txtTransferProjectName.getText().trim());
                    ps.setString(2, txtTransferProjectNumber.getText().trim());
                    ps.executeUpdate();
                    tblModelTransferProject.getDataVector().removeAllElements();
                    LoadTableTransferProject();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEditProActionPerformed

    private void btnDeleteProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProActionPerformed
        if (txtTransferProjectNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer project number is required to delete.");
            txtTransferProjectNumber.requestFocus();
        } else if(txtTransferProjectName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter transfer project name is required to delete.");
            txtTransferProjectName.requestFocus();
        } else {
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery("SELECT transferProjectNumber, transferProjectName FROM TransferProject WHERE transferProjectNumber = '"+txtTransferProjectNumber.getText().trim()+"' and transferProjectName = '"+txtTransferProjectName.getText().trim()+"'");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Transfer project number and transfer project name does not exist.");
                    txtTransferProjectNumber.requestFocus();
                } else {
                    PreparedStatement pre = cn.prepareStatement("SELECT transferProjectNumber FROM TransferRequests WHERE transferProjectNumber = ?");
                    pre.setString(1, txtTransferProjectNumber.getText().trim());
                    ResultSet r = pre.executeQuery();
                    if(r.next()){
                        int click = JOptionPane.showConfirmDialog(this, "The employee is requesting transfer of this project.\n If you delete this project, the employee who requested the transfer will also delete from Employees Requesting Transfer.\nAre you sure you want to delete?");
                        if (click == 0){
                            PreparedStatement ps1 = cn.prepareStatement("DELETE TransferRequests WHERE transferProjectNumber= ?");
                            ps1.setString(1, txtTransferProjectNumber.getText().trim());
                            ps1.executeUpdate();
                            PreparedStatement ps2 = cn.prepareStatement("DELETE TransferProject WHERE transferProjectNumber= ? and transferProjectName= ?");
                            ps2.setString(1, txtTransferProjectNumber.getText().trim());
                            ps2.setString(2, txtTransferProjectName.getText().trim());
                            ps2.executeUpdate();
                            tblModelTransferProject.getDataVector().removeAllElements();
                            LoadTableTransferProject();
                            txtTransferProjectNumber.setText("");
                            txtTransferProjectName.setText("");
                            tblModelEmployeeTransfer.getDataVector().removeAllElements();
                            LoadTableEmployeesRequestingTransfer();
                            btnDeletePro.setEnabled(false);
                        }
                    } else {
                        int click = JOptionPane.showConfirmDialog(null, "Are you sure this transfer project?");
                        if(click == 0){
                            ps = cn.prepareStatement("DELETE TransferProject WHERE transferProjectNumber= ? and transferProjectName= ?");
                            ps.setString(1, txtTransferProjectNumber.getText().trim());
                            ps.setString(2, txtTransferProjectName.getText().trim());
                            ps.executeUpdate();
                            tblModelTransferProject.getDataVector().removeAllElements();
                            LoadTableTransferProject();
                            txtTransferProjectNumber.setText("");
                            txtTransferProjectName.setText("");
                            btnDeletePro.setEnabled(false);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteProActionPerformed

    private void txtKeywordTheAllowancesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKeywordTheAllowancesCaretUpdate
        try {
            boolean flag = false;
            String query = "SELECT e.empNumber, e.fullname, tpst.transferPositionNumber, tpst.transferPositionName, tpst.allowanceLevel, trq.status, tpst.note FROM Employee e INNER JOIN TransferRequests trq ON e.empNumber = trq.empNumber JOIN TransferPosition tpst ON trq.transferPositionNumber = tpst.transferPositionNumber WHERE e.empNumber LIKE ? or e.fullname LIKE ? or tpst.transferPositionNumber LIKE ? or tpst.transferPositionName LIKE ? or tpst.allowanceLevel LIKE ? or trq.status LIKE ? or tpst.note LIKE ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, txtKeywordTheAllowances.getText() + "%");
            ps.setString(2, txtKeywordTheAllowances.getText() + "%");
            ps.setString(3, txtKeywordTheAllowances.getText() + "%");
            ps.setString(4, txtKeywordTheAllowances.getText() + "%");
            ps.setString(5, txtKeywordTheAllowances.getText() + "%");
            ps.setString(6, txtKeywordTheAllowances.getText() + "%");
            ps.setString(7, txtKeywordTheAllowances.getText() + "%");
            rs = ps.executeQuery();

            while (true) {
                tblModelAllowanceOfEmployee.getDataVector().removeAllElements();
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, txtKeywordTheAllowances.getText() + "%");
                ps.setString(2, txtKeywordTheAllowances.getText() + "%");
                ps.setString(3, txtKeywordTheAllowances.getText() + "%");
                ps.setString(4, txtKeywordTheAllowances.getText() + "%");
                ps.setString(5, txtKeywordTheAllowances.getText() + "%");
                ps.setString(6, txtKeywordTheAllowances.getText() + "%");
                ps.setString(7, txtKeywordTheAllowances.getText() + "%");
                rs = ps.executeQuery();

                
                rowAllowanceOfEmployee = new Vector();
                while (rs.next()) {
                    rowAllowanceOfEmployee = new Vector();
                    rowAllowanceOfEmployee.add(rs.getString(1));
                    rowAllowanceOfEmployee.add(rs.getString(2));
                    rowAllowanceOfEmployee.add(rs.getString(3));
                    rowAllowanceOfEmployee.add(rs.getString(4));
                    rowAllowanceOfEmployee.add(rs.getString(5));
                    rowAllowanceOfEmployee.add(rs.getString(6));
                    rowAllowanceOfEmployee.add(rs.getString(7));
                    tblModelAllowanceOfEmployee.addRow(rowAllowanceOfEmployee);
                }
                if (rowAllowanceOfEmployee.isEmpty()) {
                    tblModelAllowanceOfEmployee.addRow(rowAllowanceOfEmployee);
                }
                flag = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtKeywordTheAllowancesCaretUpdate

    private void btnResetEmpAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetEmpAllowanceActionPerformed
        txtKeywordTheAllowances.setText("");
        tblModelAllowanceOfEmployee.getDataVector().removeAllElements();
        LoadTableAllowanceOfEmployee();
        txtKeywordTheAllowances.requestFocus();
    }//GEN-LAST:event_btnResetEmpAllowanceActionPerformed

    private void jTableAllowanceOfEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAllowanceOfEmployeeMouseClicked
        int row = jTableAllowanceOfEmployee.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtKeywordTheAllowances.setText((String) jTableAllowanceOfEmployee.getValueAt(row, 0));
    }//GEN-LAST:event_jTableAllowanceOfEmployeeMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDep;
    private javax.swing.JButton btnAddPos;
    private javax.swing.JButton btnAddPro;
    private javax.swing.JButton btnDeleteDep;
    private javax.swing.JButton btnDeleteEmployeesRequestingTransfer;
    private javax.swing.JButton btnDeletePos;
    private javax.swing.JButton btnDeletePro;
    private javax.swing.JButton btnEditDep;
    private javax.swing.JButton btnEditPos;
    private javax.swing.JButton btnEditPro;
    private javax.swing.JButton btnResetDep;
    private javax.swing.JButton btnResetEmp;
    private javax.swing.JButton btnResetEmpAllowance;
    private javax.swing.JButton btnResetPos;
    private javax.swing.JButton btnResetPro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnEmployeesRequestingTransfer;
    private javax.swing.JPanel jPnTheAllowances;
    private javax.swing.JTabbedPane jPnTransfer;
    private javax.swing.JPanel jPnTransferDepartment;
    private javax.swing.JPanel jPnTransferPosition;
    private javax.swing.JPanel jPnTransferProject;
    private javax.swing.JScrollPane jScrollPane0;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneEmployeeTheAllowances;
    private javax.swing.JTable jTableAllowanceOfEmployee;
    private javax.swing.JTable jTableEmployeesRequestingTransfer;
    private javax.swing.JTable jTableTransferDepartment;
    private javax.swing.JTable jTableTransferPosition;
    private javax.swing.JTable jTableTransferProject;
    private javax.swing.JPanel jpnTransfer;
    private javax.swing.JLabel lbAllowanceLevel;
    private javax.swing.JLabel lbKeywordEmp;
    private javax.swing.JLabel lbKeywordTheAllowances;
    private javax.swing.JLabel lbNameDep;
    private javax.swing.JLabel lbNamePos;
    private javax.swing.JLabel lbNamePro;
    private javax.swing.JLabel lbNoteTransferDepartment;
    private javax.swing.JLabel lbNumberDep;
    private javax.swing.JLabel lbNumberPos;
    private javax.swing.JLabel lbNumberPro;
    private javax.swing.JLabel lbtitleEmployeeTheAllowances;
    private javax.swing.JPanel panelControlPos;
    private javax.swing.JPanel panelControlPro;
    private javax.swing.JPanel panelControlTDep;
    private javax.swing.JPanel panleControlEmp;
    private javax.swing.JTextField txtAllowanceLevel;
    private javax.swing.JTextField txtKeywordEmployeeTransfer;
    private javax.swing.JTextField txtKeywordTheAllowances;
    private javax.swing.JTextArea txtNoteTransferPosition;
    private javax.swing.JTextField txtTransferDepartmentName;
    private javax.swing.JTextField txtTransferDepartmentNumber;
    private javax.swing.JTextField txtTransferPositionName;
    private javax.swing.JTextField txtTransferPositionNumber;
    private javax.swing.JTextField txtTransferProjectName;
    private javax.swing.JTextField txtTransferProjectNumber;
    // End of variables declaration//GEN-END:variables
}
