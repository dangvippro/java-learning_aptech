package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TheEmployeeMasterRecords extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel tblModel;
    DefaultTableModel tblModelProfessionalSkills;
    DefaultTableModel tblModelProfessionalSkillsOfEmployee;
    DefaultTableModel tblModelEducationQualifications;
    Vector row;
    Vector rowProfessionalSkills;
    Vector rowProfessionalSkillsOfEmployee;
    Vector rowEducationQualifications;
    server.DBHelper db = new server.DBHelper();
    Connection con = db.getCon();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    public TheEmployeeMasterRecords() {
        initComponents();
        setLocationRelativeTo(null);
        image();
        ImageIcon img = new ImageIcon("image//TransferEmployee.png");
        this.setIconImage(img.getImage());
        btnEdit.setEnabled(false);
        btnDeleteProfessionalSkills.setEnabled(false);
        btnDeleteProfessionalSkillsOfEmployees.setEnabled(false);
        btnDeleteEducationQualifications.setEnabled(false);
        txtDateOfBirth.setDateFormatString("dd/MM/yyyy");
        txtDateStartWork.setDateFormatString("dd/MM/yyyy");

//        setModel Employee table
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("EmpNumber");
        tblModel.addColumn("EmpName");
        tblModel.addColumn("Gender");
        tblModel.addColumn("Date Of Birth");
        tblModel.addColumn("Address");
        tblModel.addColumn("Email");
        tblModel.addColumn("Date start work");
        tblModel.addColumn("Role");
        tblModel.addColumn("Current DepartMent");
        tblModel.addColumn("Current Project");
        tblModel.addColumn("Current Position");
        tblModel.addColumn("Allowances level");
        tblModel.addColumn("Work experience");
        tblModel.addColumn("Note");
        jTableEmployee.setModel(tblModel);
        LoadTableEmployee();

//        setModel Professional Skills table
        tblModelProfessionalSkills = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelProfessionalSkills.addColumn("Professional skill number");
        tblModelProfessionalSkills.addColumn("Technical skills");
        jTableProfessionalSkills.setModel(tblModelProfessionalSkills);
        LoadTableProfessionalSkills();

//        setModel Professional Skills Of Employee table
        tblModelProfessionalSkillsOfEmployee = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelProfessionalSkillsOfEmployee.addColumn("Professional skill of employee number");
        tblModelProfessionalSkillsOfEmployee.addColumn("EmpNumber");
        tblModelProfessionalSkillsOfEmployee.addColumn("Professional skill number");
        jTableProfessionalSkillsOfEmployees.setModel(tblModelProfessionalSkillsOfEmployee);
        LoadTableProfessionalSkillsOfEmployee();

//        setModel Education qualifications table
        tblModelEducationQualifications = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelEducationQualifications.addColumn("Education qualifications number");
        tblModelEducationQualifications.addColumn("EmpNumber");
        tblModelEducationQualifications.addColumn("School name");
        tblModelEducationQualifications.addColumn("Majors");
        tblModelEducationQualifications.addColumn("Graduation GAP scores");
        tblModelEducationQualifications.addColumn("Duration of study");
        jTableEducationQualifications.setModel(tblModelEducationQualifications);
        LoadTableEducationQualifications();

    }

    public void LoadTableEmployee() {
        try {
            ps = con.prepareStatement("SELECT empNumber, fullname, gender, dateOfBirth, address, email, dateStartWork, role, currentDepartment, currentProject, currentPosition, allowanceLevel, workExperience, note FROM Employee");
            rs = ps.executeQuery();

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
//                btnAdd.setEnabled(false);
//                btnEdit.setEnabled(false);
            }
            jTableEmployee.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(Approval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableProfessionalSkills() {
        try {
            ps = con.prepareStatement("SELECT professionalSkillNumber, technicalSkills FROM ProfessionalSkills");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowProfessionalSkills = new Vector();
                rowProfessionalSkills.add(rs.getString(1));
                rowProfessionalSkills.add(rs.getString(2));
                tblModelProfessionalSkills.addRow(rowProfessionalSkills);
            }
            if (rowProfessionalSkills.isEmpty()) {
                tblModelProfessionalSkills.addRow(rowProfessionalSkills);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableProfessionalSkills.setModel(tblModelProfessionalSkills);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableProfessionalSkillsOfEmployee() {
        try {
            ps = con.prepareStatement("SELECT professionalSkillsOfEmployeeNumber, empNumber, professionalSkillNumber FROM ProfessionalSkillsOfEmployee");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowProfessionalSkillsOfEmployee = new Vector();
                rowProfessionalSkillsOfEmployee.add(rs.getString(1));
                rowProfessionalSkillsOfEmployee.add(rs.getString(2));
                rowProfessionalSkillsOfEmployee.add(rs.getString(3));
                tblModelProfessionalSkillsOfEmployee.addRow(rowProfessionalSkillsOfEmployee);
            }
            if (rowProfessionalSkillsOfEmployee.isEmpty()) {
                tblModelProfessionalSkillsOfEmployee.addRow(rowProfessionalSkillsOfEmployee);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableProfessionalSkillsOfEmployees.setModel(tblModelProfessionalSkillsOfEmployee);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableEducationQualifications() {
        try {
            ps = con.prepareStatement("SELECT educationalQualificationsNumber, empNumber, schoolName, majors, graduationGAPScores, durationOfStudy FROM EducationalQualifications");
            rs = ps.executeQuery();

            while (rs.next()) {
                rowEducationQualifications = new Vector();
                rowEducationQualifications.add(rs.getString(1));
                rowEducationQualifications.add(rs.getString(2));
                rowEducationQualifications.add(rs.getString(3));
                rowEducationQualifications.add(rs.getString(4));
                rowEducationQualifications.add(rs.getString(5));
                rowEducationQualifications.add(rs.getString(6));
                tblModelEducationQualifications.addRow(rowEducationQualifications);
            }
            if (rowEducationQualifications.isEmpty()) {
                tblModelEducationQualifications.addRow(rowEducationQualifications);
//                btnAddTransferDepartment.setEnabled(false);
//                btnEditTransferDepartment.setEnabled(false);
            }
            jTableEducationQualifications.setModel(tblModelEducationQualifications);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLServer errors");
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void image(){
        String refesh = "image//refresh.png";
        String add = "image//add.png";
        String edit = "image//edit2.png";
        String delete = "image//deleted.png";
        String search = "image//search.png";
        btnReset.setSize(20,20);
        new SetImage().setImageButton(btnReset, refesh);
        btnResetProfessionalSkills.setSize(20,20);
        new SetImage().setImageButton(btnResetProfessionalSkills, refesh);
        btnResetProfessionalSkillsOfEmployees.setSize(20,20);
        new SetImage().setImageButton(btnResetProfessionalSkillsOfEmployees, refesh);
        btnResetEducationQualifications.setSize(20,20);
        new SetImage().setImageButton(btnResetEducationQualifications, refesh);
        btnAdd.setSize(20,20);
        new SetImage().setImageButton(btnAdd, add);
        btnAddEducationQualifications.setSize(20,20);
        new SetImage().setImageButton(btnAddEducationQualifications, add);
        btnAddProfessionalSkills.setSize(20,20);
        new SetImage().setImageButton(btnAddProfessionalSkills, add);
        btnAddProfessionalSkillsOfEmployees.setSize(20,20);
        new SetImage().setImageButton(btnAddProfessionalSkillsOfEmployees, add);
        btnEdit.setSize(20,20);
        new SetImage().setImageButton(btnEdit, edit);
        btnEditEducationQualifications.setSize(20,20);
        new SetImage().setImageButton(btnEditEducationQualifications, edit);
        btnEditProfessionalSkills.setSize(20,20);
        new SetImage().setImageButton(btnEditProfessionalSkills, edit);
        btnEditProfessionalSkillsOfEmployees.setSize(20,20);
        new SetImage().setImageButton(btnEditProfessionalSkillsOfEmployees, edit);
        btnDelete.setSize(20,20);
        new SetImage().setImageButton(btnDelete, delete);
        btnDeleteEducationQualifications.setSize(20,20);
        new SetImage().setImageButton(btnDeleteEducationQualifications, delete);
        btnDeleteProfessionalSkills.setSize(20,20);
        new SetImage().setImageButton(btnDeleteProfessionalSkills, delete);
        btnDeleteProfessionalSkillsOfEmployees.setSize(20,20);
        new SetImage().setImageButton(btnDeleteProfessionalSkillsOfEmployees, delete);
        btnSearch.setSize(20, 20);
        new SetImage().setImageButton(btnSearch, search);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneEmployee = new javax.swing.JTabbedPane();
        jPanelEmployee = new javax.swing.JPanel();
        jScrollPaneTableEmployee = new javax.swing.JScrollPane();
        jTableEmployee = new javax.swing.JTable();
        jPanelControl = new javax.swing.JPanel();
        lbEmpNumber = new javax.swing.JLabel();
        lbEmpName = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lbDateOfBirth = new javax.swing.JLabel();
        txtEmpNumber = new javax.swing.JTextField();
        txtEmpName = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbDateStartWork = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lbCurretDepartment = new javax.swing.JLabel();
        lbCurrentProject = new javax.swing.JLabel();
        txtCurrentProject = new javax.swing.JTextField();
        txtCurrentPosition = new javax.swing.JTextField();
        lbNote = new javax.swing.JLabel();
        jScrollPaneNote = new javax.swing.JScrollPane();
        txtAreaNote = new javax.swing.JTextArea();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rdbtnMale = new javax.swing.JRadioButton();
        rdbtnFemale = new javax.swing.JRadioButton();
        txtCurrentDepartment = new javax.swing.JTextField();
        lbWorkExperience = new javax.swing.JLabel();
        txtAllowanceLevelEmployee = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        lbAllowanceLevelEmployee = new javax.swing.JLabel();
        txtWorkExperience = new javax.swing.JTextField();
        lbCurrentPosition = new javax.swing.JLabel();
        txtDateOfBirth = new com.toedter.calendar.JDateChooser();
        txtDateStartWork = new com.toedter.calendar.JDateChooser();
        jPanelCV = new javax.swing.JPanel();
        jScrollPaneProfessionalSkills = new javax.swing.JScrollPane();
        jTableProfessionalSkills = new javax.swing.JTable();
        lbtitleProfessionalSkills = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableEducationQualifications = new javax.swing.JTable();
        lbtitleEducationLevel = new javax.swing.JLabel();
        lbtitleEmployeeCV = new javax.swing.JLabel();
        jPanelEducations = new javax.swing.JPanel();
        txtEducationQualificationNumber = new javax.swing.JTextField();
        lbSchoolName = new javax.swing.JLabel();
        txtSchoolName = new javax.swing.JTextField();
        lbMajors = new javax.swing.JLabel();
        txtMajors = new javax.swing.JTextField();
        txtDurationOfStudy = new javax.swing.JTextField();
        lbEmpNumberEducationQualifications = new javax.swing.JLabel();
        lbGraduationGAPscores = new javax.swing.JLabel();
        lbEducationQualificationNumber = new javax.swing.JLabel();
        txtEmpNumberEducationQualifications = new javax.swing.JTextField();
        lbDurationOfStudy = new javax.swing.JLabel();
        txtGraduationGAPscores = new javax.swing.JTextField();
        btnAddEducationQualifications = new javax.swing.JButton();
        btnEditEducationQualifications = new javax.swing.JButton();
        btnDeleteEducationQualifications = new javax.swing.JButton();
        btnResetEducationQualifications = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableProfessionalSkillsOfEmployees = new javax.swing.JTable();
        jPanelProfessionalSkills = new javax.swing.JPanel();
        lbProfessionalSkillNumber = new javax.swing.JLabel();
        txtProfessionalSkillNumber = new javax.swing.JTextField();
        lbTechnicalSkills = new javax.swing.JLabel();
        txtEngineeringRelatedSkills = new javax.swing.JTextField();
        btnAddProfessionalSkills = new javax.swing.JButton();
        btnEditProfessionalSkills = new javax.swing.JButton();
        btnResetProfessionalSkills = new javax.swing.JButton();
        btnDeleteProfessionalSkills = new javax.swing.JButton();
        jPanelProfessionalSkillsOfEmployees = new javax.swing.JPanel();
        lbProfessionalSkillsOfEmployeeNumber = new javax.swing.JLabel();
        txtProfessionalSkillsOfEmployeeNumber = new javax.swing.JTextField();
        lbEmpNumberProfessionalSkillsOfEmployees = new javax.swing.JLabel();
        txtEmpNumberProfessionalSkillsOfEmployees = new javax.swing.JTextField();
        lbProfessionalSkillNumberProfessionalSkillsOfEmployees = new javax.swing.JLabel();
        txtProfessionalSkillNumberProfessionalSkillsOfEmployees = new javax.swing.JTextField();
        btnAddProfessionalSkillsOfEmployees = new javax.swing.JButton();
        btnEditProfessionalSkillsOfEmployees = new javax.swing.JButton();
        btnDeleteProfessionalSkillsOfEmployees = new javax.swing.JButton();
        btnResetProfessionalSkillsOfEmployees = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THE EMPLOYEE MASTER RECORD");
        setResizable(false);

        jTabbedPaneEmployee.setPreferredSize(new java.awt.Dimension(1377, 684));

        jPanelEmployee.setMinimumSize(new java.awt.Dimension(900, 700));
        jPanelEmployee.setPreferredSize(new java.awt.Dimension(1000, 600));

        jScrollPaneTableEmployee.setPreferredSize(new java.awt.Dimension(1000, 500));

        jTableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEmployee.setPreferredSize(new java.awt.Dimension(1080, 580));
        jTableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmployeeMouseClicked(evt);
            }
        });
        jScrollPaneTableEmployee.setViewportView(jTableEmployee);

        jPanelControl.setPreferredSize(new java.awt.Dimension(1460, 217));
        jPanelControl.setRequestFocusEnabled(false);

        lbEmpNumber.setText("Employee number:");

        lbEmpName.setText("Employee name:");

        lbGender.setText("Gender:");

        lbDateOfBirth.setText("Date of birth:");

        lbEmail.setText("Email:");

        lbAddress.setText("Address:");

        lbDateStartWork.setText("Date start work: ");

        lbRole.setText("Role: ");

        lbCurretDepartment.setText("Current department: ");

        lbCurrentProject.setText("Current project: ");

        lbNote.setText("Note: ");

        txtAreaNote.setColumns(20);
        txtAreaNote.setRows(5);
        jScrollPaneNote.setViewportView(txtAreaNote);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        rdbtnMale.setText("Male");

        rdbtnFemale.setText("Female");

        lbWorkExperience.setText("Work experience:");

        lbAllowanceLevelEmployee.setText("Allowance level:");

        lbCurrentPosition.setText("Current position: ");

        javax.swing.GroupLayout jPanelControlLayout = new javax.swing.GroupLayout(jPanelControl);
        jPanelControl.setLayout(jPanelControlLayout);
        jPanelControlLayout.setHorizontalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmpNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmpName)
                    .addComponent(txtEmpNumber)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addComponent(rdbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(rdbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAddress))
                .addGap(18, 18, 18)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbCurretDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCurrentProject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDateStartWork, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurrentProject, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCurrentDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNote, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbWorkExperience, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWorkExperience, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneNote, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addComponent(txtDateStartWork, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCurrentPosition)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addComponent(lbAllowanceLevelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAllowanceLevelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105))
        );
        jPanelControlLayout.setVerticalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmpNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCurrentPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbEmpNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbEmpName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmpName, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDateStartWork, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDateStartWork, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAllowanceLevelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAllowanceLevelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtWorkExperience, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtRole, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbWorkExperience, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelControlLayout.createSequentialGroup()
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbGender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdbtnMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCurretDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCurrentDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelControlLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(lbNote, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCurrentProject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCurrentProject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelControlLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneNote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jPanelEmployeeLayout = new javax.swing.GroupLayout(jPanelEmployee);
        jPanelEmployee.setLayout(jPanelEmployeeLayout);
        jPanelEmployeeLayout.setHorizontalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addComponent(jScrollPaneTableEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 1375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 1206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEmployeeLayout.setVerticalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTableEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPaneEmployee.addTab("Employee", jPanelEmployee);

        jPanelCV.setPreferredSize(new java.awt.Dimension(1000, 600));

        jTableProfessionalSkills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableProfessionalSkills.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProfessionalSkillsMouseClicked(evt);
            }
        });
        jScrollPaneProfessionalSkills.setViewportView(jTableProfessionalSkills);

        lbtitleProfessionalSkills.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtitleProfessionalSkills.setText("Professional Skills");

        jTableEducationQualifications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEducationQualifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEducationQualificationsMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableEducationQualifications);

        lbtitleEducationLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtitleEducationLevel.setText("Education Qualifications");

        lbtitleEmployeeCV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtitleEmployeeCV.setText("Professional Skills Of Employees");

        lbSchoolName.setText("School name:");

        lbMajors.setText("Majors:");

        lbEmpNumberEducationQualifications.setText("EmpNumber:");

        lbGraduationGAPscores.setText("Graduation GAP scores:");

        lbEducationQualificationNumber.setText("Education qualification number:");

        lbDurationOfStudy.setText("Duration of study:");

        btnAddEducationQualifications.setText("Add");
        btnAddEducationQualifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEducationQualificationsActionPerformed(evt);
            }
        });

        btnEditEducationQualifications.setText("Edit");
        btnEditEducationQualifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditEducationQualificationsActionPerformed(evt);
            }
        });

        btnDeleteEducationQualifications.setText("Delete");
        btnDeleteEducationQualifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEducationQualificationsActionPerformed(evt);
            }
        });

        btnResetEducationQualifications.setText("Reset");
        btnResetEducationQualifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetEducationQualificationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEducationsLayout = new javax.swing.GroupLayout(jPanelEducations);
        jPanelEducations.setLayout(jPanelEducationsLayout);
        jPanelEducationsLayout.setHorizontalGroup(
            jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEducationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEducationsLayout.createSequentialGroup()
                        .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEducationsLayout.createSequentialGroup()
                                .addComponent(lbEducationQualificationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEducationQualificationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelEducationsLayout.createSequentialGroup()
                                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbEmpNumberEducationQualifications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSchoolName, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmpNumberEducationQualifications, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(txtSchoolName))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMajors, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGraduationGAPscores)
                            .addComponent(lbDurationOfStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDurationOfStudy, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtGraduationGAPscores)
                            .addComponent(txtMajors)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEducationsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddEducationQualifications)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditEducationQualifications)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteEducationQualifications)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetEducationQualifications)))
                .addContainerGap())
        );
        jPanelEducationsLayout.setVerticalGroup(
            jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEducationsLayout.createSequentialGroup()
                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMajors, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMajors, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEducationQualificationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEducationQualificationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGraduationGAPscores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGraduationGAPscores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmpNumberEducationQualifications, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmpNumberEducationQualifications, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDurationOfStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDurationOfStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEducationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetEducationQualifications)
                    .addComponent(btnDeleteEducationQualifications)
                    .addComponent(btnEditEducationQualifications)
                    .addComponent(btnAddEducationQualifications))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTableProfessionalSkillsOfEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableProfessionalSkillsOfEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProfessionalSkillsOfEmployeesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableProfessionalSkillsOfEmployees);

        lbProfessionalSkillNumber.setText("Professional skill number:");

        lbTechnicalSkills.setText("Technical skills:");

        btnAddProfessionalSkills.setText("Add");
        btnAddProfessionalSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProfessionalSkillsActionPerformed(evt);
            }
        });

        btnEditProfessionalSkills.setText("Edit");
        btnEditProfessionalSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfessionalSkillsActionPerformed(evt);
            }
        });

        btnResetProfessionalSkills.setText("Reset");
        btnResetProfessionalSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProfessionalSkillsActionPerformed(evt);
            }
        });

        btnDeleteProfessionalSkills.setText("Delete");
        btnDeleteProfessionalSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProfessionalSkillsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProfessionalSkillsLayout = new javax.swing.GroupLayout(jPanelProfessionalSkills);
        jPanelProfessionalSkills.setLayout(jPanelProfessionalSkillsLayout);
        jPanelProfessionalSkillsLayout.setHorizontalGroup(
            jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProfessionalSkillsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbProfessionalSkillNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTechnicalSkills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProfessionalSkillsLayout.createSequentialGroup()
                        .addComponent(txtProfessionalSkillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetProfessionalSkills))
                    .addGroup(jPanelProfessionalSkillsLayout.createSequentialGroup()
                        .addComponent(txtEngineeringRelatedSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddProfessionalSkills)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditProfessionalSkills)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteProfessionalSkills)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanelProfessionalSkillsLayout.setVerticalGroup(
            jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfessionalSkillsLayout.createSequentialGroup()
                .addGroup(jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProfessionalSkillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfessionalSkillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetProfessionalSkills))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTechnicalSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProfessionalSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEngineeringRelatedSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddProfessionalSkills)
                        .addComponent(btnEditProfessionalSkills)
                        .addComponent(btnDeleteProfessionalSkills)))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        lbProfessionalSkillsOfEmployeeNumber.setText("Professional skills of employee number:");

        lbEmpNumberProfessionalSkillsOfEmployees.setText("EmpNumber:");

        lbProfessionalSkillNumberProfessionalSkillsOfEmployees.setText("Professional skill number:");

        btnAddProfessionalSkillsOfEmployees.setText("Add");
        btnAddProfessionalSkillsOfEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProfessionalSkillsOfEmployeesActionPerformed(evt);
            }
        });

        btnEditProfessionalSkillsOfEmployees.setText("Edit");
        btnEditProfessionalSkillsOfEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfessionalSkillsOfEmployeesActionPerformed(evt);
            }
        });

        btnDeleteProfessionalSkillsOfEmployees.setText("Delete");
        btnDeleteProfessionalSkillsOfEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProfessionalSkillsOfEmployeesActionPerformed(evt);
            }
        });

        btnResetProfessionalSkillsOfEmployees.setText("Reset");
        btnResetProfessionalSkillsOfEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProfessionalSkillsOfEmployeesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProfessionalSkillsOfEmployeesLayout = new javax.swing.GroupLayout(jPanelProfessionalSkillsOfEmployees);
        jPanelProfessionalSkillsOfEmployees.setLayout(jPanelProfessionalSkillsOfEmployeesLayout);
        jPanelProfessionalSkillsOfEmployeesLayout.setHorizontalGroup(
            jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmpNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProfessionalSkillsOfEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmpNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtProfessionalSkillsOfEmployeeNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createSequentialGroup()
                        .addComponent(lbProfessionalSkillNumberProfessionalSkillsOfEmployees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProfessionalSkillNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createSequentialGroup()
                        .addComponent(btnAddProfessionalSkillsOfEmployees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditProfessionalSkillsOfEmployees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteProfessionalSkillsOfEmployees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetProfessionalSkillsOfEmployees)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProfessionalSkillsOfEmployeesLayout.setVerticalGroup(
            jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createSequentialGroup()
                .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtProfessionalSkillNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lbProfessionalSkillNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProfessionalSkillsOfEmployeeNumber)
                    .addComponent(lbProfessionalSkillsOfEmployeeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmpNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelProfessionalSkillsOfEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmpNumberProfessionalSkillsOfEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnAddProfessionalSkillsOfEmployees)
                        .addComponent(btnEditProfessionalSkillsOfEmployees)
                        .addComponent(btnDeleteProfessionalSkillsOfEmployees)
                        .addComponent(btnResetProfessionalSkillsOfEmployees)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelCVLayout = new javax.swing.GroupLayout(jPanelCV);
        jPanelCV.setLayout(jPanelCVLayout);
        jPanelCVLayout.setHorizontalGroup(
            jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCVLayout.createSequentialGroup()
                .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(lbtitleProfessionalSkills))
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneProfessionalSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelProfessionalSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCVLayout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(lbtitleEmployeeCV))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelProfessionalSkillsOfEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(lbtitleEducationLevel))
                    .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane9)
                        .addComponent(jPanelEducations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanelCVLayout.setVerticalGroup(
            jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addComponent(lbtitleProfessionalSkills)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneProfessionalSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addComponent(lbtitleEducationLevel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelEducations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(450, 450, 450))
                    .addGroup(jPanelCVLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanelProfessionalSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbtitleEmployeeCV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelProfessionalSkillsOfEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPaneEmployee.addTab("CV", jPanelCV);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 1401, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            boolean flag = false;
            if (txtEmpNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter a employee number to find.");
                txtEmpNumber.requestFocus();
            } else {
                String query = "SELECT empNumber, fullname, gender, dateOfBirth, address, email, dateStartWork, role, currentDepartment, currentProject, currentPosition, allowanceLevel, workExperience, note FROM Employee WHERE empNumber LIKE ? and fullname LIKE ?";
                ps = con.prepareStatement(query);
                ps.setString(1, txtEmpNumber.getText() + "%");
                ps.setString(2, txtEmpName.getText() + "%");
                rs = ps.executeQuery();

                while (true) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Employee number or employee name does not exist.");
                        txtEmpNumber.requestFocus();
                        flag = false;
                        return;
                    } else {
                        tblModel.getDataVector().removeAllElements();
                        PreparedStatement pre = con.prepareStatement(query);
                        pre.setString(1, txtEmpNumber.getText() + "%");
                        pre.setString(2, txtEmpName.getText() + "%");
                        rs = pre.executeQuery();
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
                        flag = true;
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String regEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        Pattern p1 = Pattern.compile(regEmail);
        Matcher m2 = p1.matcher(txtEmail.getText().trim());
        String regAllowanceLevel = "^[0-9]+$";
        Pattern p2 = Pattern.compile(regAllowanceLevel);
        Matcher m3 = p2.matcher(txtAllowanceLevelEmployee.getText().trim());
        String regWorkExperience = "^[0-9]+$";
        Pattern p3 = Pattern.compile(regWorkExperience);
        Matcher m4 = p3.matcher(txtWorkExperience.getText().trim());
        String gender;
        String workExp = null;
        String yearExp = "SELECT workExperience FROM Employee WHERE workExperience = '" + txtWorkExperience.getText().trim() + "'";
        if (rdbtnMale.isSelected()) {
            gender = "male";
        } else {
            gender = "female";
        }
        if (txtEmpNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter employee number is required.");
            txtEmpNumber.requestFocus();
        } else {
            try {
                Statement st = con.createStatement();
                rs = st.executeQuery("SELECT empNumber FROM Employee WHERE empNumber= '" + txtEmpNumber.getText().trim() + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Employee number already exists.");
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
                } else if (txtAllowanceLevelEmployee.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter allowance level is required.");
                    txtAllowanceLevelEmployee.requestFocus();
                } else if (!m3.find()) {
                    JOptionPane.showMessageDialog(this, "Allowance level must be number.");
                    txtAllowanceLevelEmployee.requestFocus();
                } else if (txtWorkExperience.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter work experience is required.");
                    txtWorkExperience.requestFocus();
                } else if (!m4.find()) {
                    JOptionPane.showMessageDialog(this, "Work experience year must be number.");
                    txtWorkExperience.requestFocus();
                } else if (yearExp.equals(1)) {
                    workExp = txtWorkExperience.getText().trim() + " year";
                } else {
                    workExp = txtWorkExperience.getText().trim() + " years";
                }
                String query = "INSERT INTO [dbo].[Employee]([empNumber], [fullname], [gender], [dateOfBirth], [address], [email], [dateStartWork], [role], [currentDepartment], [currentProject], [currentPosition], [allowanceLevel], [workExperience], [note]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, txtEmpNumber.getText().trim());
                pst.setString(2, txtEmpName.getText().trim());
                pst.setString(3, gender);
                pst.setString(4, ft.format(txtDateOfBirth.getDate()));
                pst.setString(5, txtAddress.getText().trim());
                pst.setString(6, txtEmail.getText().trim());
                pst.setString(7, ft.format(txtDateStartWork.getDate()));
                pst.setString(8, txtRole.getText().trim());
                pst.setString(9, txtCurrentDepartment.getText().trim());
                pst.setString(10, txtCurrentProject.getText().trim());
                pst.setString(11, txtCurrentPosition.getText().trim());
                pst.setString(12, txtAllowanceLevelEmployee.getText().trim());
                pst.setString(13, workExp);
                pst.setString(14, txtAreaNote.getText().trim());
                pst.executeUpdate();
                tblModel.getDataVector().removeAllElements();
                LoadTableEmployee();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        String regEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        Pattern p1 = Pattern.compile(regEmail);
        Matcher m2 = p1.matcher(txtEmail.getText().trim());
        String regAllowanceLevel = "^[0-9]+[\\.?][0]+$";
        Pattern p2 = Pattern.compile(regAllowanceLevel);
        Matcher m3 = p2.matcher(txtAllowanceLevelEmployee.getText().trim());
        String regWorkExperience = "^[0-9]+[\\ ][year | years]+$";
        Pattern p3 = Pattern.compile(regWorkExperience);
        Matcher m4 = p3.matcher(txtWorkExperience.getText().trim());
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
            try {
                Statement st = con.createStatement();
                rs = st.executeQuery("SELECT empNumber FROM Employee WHERE empNumber= '" + txtEmpNumber.getText().trim() + "'");
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
                } else if (txtAllowanceLevelEmployee.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter allowance level is required.");
                    txtAllowanceLevelEmployee.requestFocus();
                } else if (!m3.find()) {
                    JOptionPane.showMessageDialog(this, "Allowance level must be number (0000.0000).");
                    txtAllowanceLevelEmployee.requestFocus();
                } else if (txtWorkExperience.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter work experience is required.");
                    txtWorkExperience.requestFocus();
                } else if (!m4.find()) {
                    JOptionPane.showMessageDialog(this, "Work experience must be number, in \"number year or number years\" format.");
                    txtWorkExperience.requestFocus();
                } else {
                        String s = "UPDATE Employee SET fullname=?, gender=?, dateOfBirth=?, address=?, email=?, dateStartWork=?, role=?, currentDepartment=?, currentProject=?, currentPosition=?, allowanceLevel=?, workExperience=?, note=? WHERE empNumber= ?";
                        PreparedStatement pstm = con.prepareStatement(s);
                        pstm.setString(1, txtEmpName.getText().trim());
                        pstm.setString(2, gender);
                        pstm.setString(3, ft.format(txtDateOfBirth.getDate()));
                        pstm.setString(4, txtAddress.getText().trim());
                        pstm.setString(5, txtEmail.getText().trim());
                        pstm.setString(6, ft.format(txtDateStartWork.getDate()));
                        pstm.setString(7, txtRole.getText().trim());
                        pstm.setString(8, txtCurrentDepartment.getText().trim());
                        pstm.setString(9, txtCurrentProject.getText().trim());
                        pstm.setString(10, txtCurrentPosition.getText().trim());
                        pstm.setString(11, txtAllowanceLevelEmployee.getText().trim());
                        pstm.setString(12, txtWorkExperience.getText().trim());
                        pstm.setString(13, txtAreaNote.getText().trim());
                        pstm.setString(14, txtEmpNumber.getText().trim());
                        pstm.executeUpdate();
                        tblModel.getDataVector().removeAllElements();
                        LoadTableEmployee();
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
                        txtCurrentProject.setText("");
                        txtCurrentPosition.setText("");
                        txtAllowanceLevelEmployee.setText("");
                        txtWorkExperience.setText("");
                        txtAreaNote.setText("");
                        btnEdit.setEnabled(false);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtEmpNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter employee number is required.");
            txtEmpNumber.requestFocus();
        } else {
            try {
                Statement st = con.createStatement();
                rs = st.executeQuery("SELECT empNumber FROM Employee WHERE empNumber= '" + txtEmpNumber.getText().trim() + "'");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Employee number does not exist.");
                    txtEmpNumber.requestFocus();
                } else {
                    String query = "SELECT empNumber FROM TransferRequests WHERE empNumber = ?";
                    PreparedStatement pre = con.prepareStatement(query);
                    pre.setString(1, txtEmpNumber.getText().trim());
                    ResultSet r = pre.executeQuery();
                    if (r.next()) {
                        JOptionPane.showMessageDialog(this, "Cannot delete because this employee requesting transfer.");
                        txtEmpNumber.requestFocus();
                    } else {
                        int click = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?");
                        if (click == 0) {
                            PreparedStatement p = con.prepareStatement("DELETE Employee WHERE empNumber = ?");
                            p.setString(1, txtEmpNumber.getText().trim());
                            p.executeUpdate();
                            tblModel.getDataVector().removeAllElements();
                            LoadTableEmployee();
                            tblModelProfessionalSkillsOfEmployee.getDataVector().removeAllElements();
                            LoadTableProfessionalSkillsOfEmployee();
                            tblModelEducationQualifications.getDataVector().removeAllElements();
                            LoadTableEducationQualifications();
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
                            txtCurrentProject.setText("");
                            txtCurrentPosition.setText("");
                            txtAllowanceLevelEmployee.setText("");
                            txtWorkExperience.setText("");
                            txtAreaNote.setText("");
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
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
        txtCurrentProject.setText("");
        txtCurrentPosition.setText("");
        txtAllowanceLevelEmployee.setText("");
        txtWorkExperience.setText("");
        txtAreaNote.setText("");
        tblModel.getDataVector().removeAllElements();
        LoadTableEmployee();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnResetProfessionalSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProfessionalSkillsActionPerformed
        txtProfessionalSkillNumber.setText("");
        txtEngineeringRelatedSkills.setText("");
        tblModelProfessionalSkills.getDataVector().removeAllElements();
        LoadTableProfessionalSkills();
    }//GEN-LAST:event_btnResetProfessionalSkillsActionPerformed

    private void btnAddProfessionalSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProfessionalSkillsActionPerformed
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT professionalSkillNumber FROM ProfessionalSkills WHERE professionalSkillNumber= '" + txtProfessionalSkillNumber.getText().trim() + "'");
            if (txtProfessionalSkillNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter professional skill number is required.");
                txtProfessionalSkillNumber.requestFocus();
            } else if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Professional skill number already exists.");
                txtProfessionalSkillNumber.requestFocus();
            } else {
                Statement s1 = con.createStatement();
                ResultSet rs1 = s1.executeQuery("SELECT technicalSkills FROM ProfessionalSkills WHERE technicalSkills= '" + txtEngineeringRelatedSkills.getText().trim() + "'");
                if (txtEngineeringRelatedSkills.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter technical skills is required.");
                    txtEngineeringRelatedSkills.requestFocus();
                } else if (rs1.next()) {
                    JOptionPane.showMessageDialog(null, "Technical skills already exists.");
                    txtEngineeringRelatedSkills.requestFocus();
                } else {
                    String query = "INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES (?, ?)";
                    ps = con.prepareStatement(query);
                    ps.setString(1, txtProfessionalSkillNumber.getText().trim());
                    ps.setString(2, txtEngineeringRelatedSkills.getText().trim());
                    ps.executeUpdate();
                    tblModelProfessionalSkills.getDataVector().removeAllElements();
                    LoadTableProfessionalSkills();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddProfessionalSkillsActionPerformed

    private void btnEditProfessionalSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfessionalSkillsActionPerformed
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT professionalSkillNumber FROM ProfessionalSkills WHERE professionalSkillNumber= '" + txtProfessionalSkillNumber.getText().trim() + "'");
            if (txtProfessionalSkillNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter professional skill number is required.");
                txtProfessionalSkillNumber.requestFocus();
            } else if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Professional skill number does not exist.");
                txtProfessionalSkillNumber.requestFocus();
            } else if (txtEngineeringRelatedSkills.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter technical skills is required.");
                txtEngineeringRelatedSkills.requestFocus();
            } else {
                String query = "UPDATE [dbo].[ProfessionalSkills] SET [technicalSkills]= ? WHERE [professionalSkillNumber]= ?";
                ps = con.prepareStatement(query);
                ps.setString(1, txtEngineeringRelatedSkills.getText().trim());
                ps.setString(2, txtProfessionalSkillNumber.getText().trim());
                ps.executeUpdate();
                tblModelProfessionalSkills.getDataVector().removeAllElements();
                LoadTableProfessionalSkills();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditProfessionalSkillsActionPerformed

    private void btnDeleteProfessionalSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProfessionalSkillsActionPerformed
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT professionalSkillNumber FROM ProfessionalSkills WHERE professionalSkillNumber= '" + txtProfessionalSkillNumber.getText().trim() + "'");
            if (rs.next()) {
                int click = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this professional skill?");
                if (click == 0) {
                    PreparedStatement ps1 = con.prepareStatement("DELETE ProfessionalSkills WHERE professionalSkillNumber= ?");
                    ps1.setString(1, txtProfessionalSkillNumber.getText().trim());
                    ps1.executeUpdate();
                    tblModelProfessionalSkills.getDataVector().removeAllElements();
                    LoadTableProfessionalSkills();
                    txtProfessionalSkillNumber.setText("");
                    txtEngineeringRelatedSkills.setText("");
                    btnDeleteProfessionalSkills.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Professional skill number does not exist.");
                txtProfessionalSkillNumber.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteProfessionalSkillsActionPerformed

    private void btnAddProfessionalSkillsOfEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProfessionalSkillsOfEmployeesActionPerformed
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT professionalSkillsOfEmployeeNumber FROM ProfessionalSkillsOfEmployee WHERE professionalSkillsOfEmployeeNumber= '" + txtProfessionalSkillsOfEmployeeNumber.getText().trim() + "'");
            if (txtProfessionalSkillsOfEmployeeNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter professional skills of employee number is required.");
                txtProfessionalSkillsOfEmployeeNumber.requestFocus();
            } else if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Enter professional skills of employee number already exists.");
                txtProfessionalSkillsOfEmployeeNumber.requestFocus();
            } else {
                Statement s2 = con.createStatement();
                rs = s2.executeQuery("SELECT empNumber FROM Employee WHERE empNumber= '" + txtEmpNumberProfessionalSkillsOfEmployees.getText().trim() + "'");
                if (txtEmpNumberProfessionalSkillsOfEmployees.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter employee number is required.");
                    txtEmpNumberProfessionalSkillsOfEmployees.requestFocus();
                } else if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Enter employee number does not exist.");
                    txtEmpNumberProfessionalSkillsOfEmployees.requestFocus();
                } else {
                    Statement s1 = con.createStatement();
                    rs = s1.executeQuery("SELECT professionalSkillNumber FROM ProfessionalSkills WHERE professionalSkillNumber= '" + txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().trim() + "'");
                    if (txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter professional skills number is required.");
                        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.requestFocus();
                    } else if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Enter professional skills number does not exist.");
                        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.requestFocus();
                    } else {
                        String query = "INSERT INTO ProfessionalSkillsOfEmployee (professionalSkillsOfEmployeeNumber, empNumber, professionalSkillNumber) VALUES (?, ?, ?)";
                        ps = con.prepareStatement(query);
                        ps.setString(1, txtProfessionalSkillsOfEmployeeNumber.getText().trim());
                        ps.setString(2, txtEmpNumberProfessionalSkillsOfEmployees.getText().trim());
                        ps.setString(3, txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().trim());
                        ps.executeUpdate();
                        tblModelProfessionalSkillsOfEmployee.getDataVector().removeAllElements();
                        LoadTableProfessionalSkillsOfEmployee();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddProfessionalSkillsOfEmployeesActionPerformed

    private void btnEditProfessionalSkillsOfEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfessionalSkillsOfEmployeesActionPerformed
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT professionalSkillsOfEmployeeNumber FROM ProfessionalSkillsOfEmployee WHERE professionalSkillsOfEmployeeNumber= '" + txtProfessionalSkillsOfEmployeeNumber.getText().trim() + "'");
            if (txtProfessionalSkillsOfEmployeeNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter professional skills of employee number is required.");
                txtProfessionalSkillsOfEmployeeNumber.requestFocus();
            } else if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Enter professional skills of employee number does not exist.");
                txtProfessionalSkillsOfEmployeeNumber.requestFocus();
            } else {
                Statement s2 = con.createStatement();
                rs = s2.executeQuery("SELECT empNumber FROM Employee WHERE empNumber= '" + txtEmpNumberProfessionalSkillsOfEmployees.getText().trim() + "'");
                if (txtEmpNumberProfessionalSkillsOfEmployees.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter employee number is required.");
                    txtEmpNumberProfessionalSkillsOfEmployees.requestFocus();
                } else if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Enter employee number does not exist.");
                    txtEmpNumberProfessionalSkillsOfEmployees.requestFocus();
                } else {
                    Statement s1 = con.createStatement();
                    rs = s1.executeQuery("SELECT professionalSkillNumber FROM ProfessionalSkills WHERE professionalSkillNumber= '" + txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().trim() + "'");
                    if (txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter professional skills number is required.");
                        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.requestFocus();
                    } else if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Enter professional skills number does not exist.");
                        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.requestFocus();
                    } else {
                        String query = "UPDATE ProfessionalSkillsOfEmployee SET empNumber= ?, professionalSkillNumber= ? WHERE professionalSkillsOfEmployeeNumber= ?";
                        ps = con.prepareStatement(query);
                        ps.setString(1, txtEmpNumberProfessionalSkillsOfEmployees.getText().trim());
                        ps.setString(2, txtProfessionalSkillNumberProfessionalSkillsOfEmployees.getText().trim());
                        ps.setString(3, txtProfessionalSkillsOfEmployeeNumber.getText().trim());
                        ps.executeUpdate();
                        tblModelProfessionalSkillsOfEmployee.getDataVector().removeAllElements();
                        LoadTableProfessionalSkillsOfEmployee();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditProfessionalSkillsOfEmployeesActionPerformed

    private void btnDeleteProfessionalSkillsOfEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProfessionalSkillsOfEmployeesActionPerformed
        try {
            PreparedStatement p = con.prepareStatement("SELECT professionalSkillsOfEmployeeNumber FROM ProfessionalSkillsOfEmployee WHERE professionalSkillsOfEmployeeNumber= ?");
            p.setString(1, txtProfessionalSkillsOfEmployeeNumber.getText().trim());
            rs = p.executeQuery();
            if (rs.next()) {
                int click = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?");
                if (click == 0) {
                    ps = con.prepareStatement("DELETE ProfessionalSkillsOfEmployee WHERE professionalSkillsOfEmployeeNumber= ?");
                    ps.setString(1, txtProfessionalSkillsOfEmployeeNumber.getText().trim());
                    ps.executeUpdate();
                    tblModelProfessionalSkillsOfEmployee.getDataVector().removeAllElements();
                    LoadTableProfessionalSkillsOfEmployee();
                    txtProfessionalSkillsOfEmployeeNumber.setText("");
                    txtEmpNumberProfessionalSkillsOfEmployees.setText("");
                    txtProfessionalSkillNumberProfessionalSkillsOfEmployees.setText("");
                    btnDeleteProfessionalSkillsOfEmployees.setEnabled(false);
                }
            } else {
                    JOptionPane.showMessageDialog(this, "Professional skill of employee number does not exist.");
                    txtProfessionalSkillsOfEmployeeNumber.requestFocus();
                }
        } catch (SQLException ex) {
            Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteProfessionalSkillsOfEmployeesActionPerformed

    private void btnResetProfessionalSkillsOfEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProfessionalSkillsOfEmployeesActionPerformed
        txtProfessionalSkillsOfEmployeeNumber.setText("");
        txtEmpNumberProfessionalSkillsOfEmployees.setText("");
        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.setText("");
        tblModelProfessionalSkillsOfEmployee.getDataVector().removeAllElements();
        LoadTableProfessionalSkillsOfEmployee();
    }//GEN-LAST:event_btnResetProfessionalSkillsOfEmployeesActionPerformed

    private void btnAddEducationQualificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEducationQualificationsActionPerformed
        String regexGAP = "^[0123]{1}[.][0-9]{1,2}|[4.0]$";
        Pattern p = Pattern.compile(regexGAP);
        Matcher m = p.matcher(txtGraduationGAPscores.getText());
        String regexDuration = "^[0-9]{4}[-][0-9]{4}$";
        Pattern pattern = Pattern.compile(regexDuration);
        Matcher mc = pattern.matcher(txtDurationOfStudy.getText());
        if (txtEducationQualificationNumber.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter education qualification number is required.");
            txtEducationQualificationNumber.requestFocus();
        } else {
            try {
                Statement stm = con.createStatement();
                rs = stm.executeQuery("SELECT educationalQualificationsNumber FROM EducationalQualifications WHERE educationalQualificationsNumber = '" + txtEducationQualificationNumber.getText() + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "educationalQualificationsNumber already exists.");
                    txtEducationQualificationNumber.requestFocus();
                } else if (txtEmpNumberEducationQualifications.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter employee number is required.");
                    txtEmpNumberEducationQualifications.requestFocus();
                } else {
                    Statement s = con.createStatement();
                    ResultSet r = s.executeQuery("SELECT empNumber FROM Employee WHERE empNumber = '" + txtEmpNumberEducationQualifications.getText() + "'");
                    if (!r.next()) {
                        JOptionPane.showMessageDialog(null, "empNumber does not exists");
                        txtEmpNumberEducationQualifications.requestFocus();
                    } else if (txtSchoolName.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter school name is required.");
                        txtSchoolName.requestFocus();
                    } else if (txtMajors.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter majors is required.");
                        txtMajors.requestFocus();
                    } else if (txtGraduationGAPscores.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter graduation GAP scores is required.");
                        txtGraduationGAPscores.requestFocus();
                    } else if (!m.find()) {
                        JOptionPane.showMessageDialog(this, "Graduation GAP score just from 1 to 4 and must be in \"0.0\" format");
                        txtGraduationGAPscores.requestFocus();
                    } else if (txtDurationOfStudy.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter duration of study is required.");
                        txtDurationOfStudy.requestFocus();
                    } else if (!mc.find()) {
                        JOptionPane.showMessageDialog(null, "Duration of study must be in \"yyyy-yyyy\" format.");
                        txtDurationOfStudy.requestFocus();
                    } else {
                        String query = "INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [empNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy]) VALUES ( ?, ?, ?, ?, ?, ?)";
                        ps = con.prepareStatement(query);
                        ps.setString(1, txtEducationQualificationNumber.getText().trim());
                        ps.setString(2, txtEmpNumberEducationQualifications.getText().trim());
                        ps.setString(3, txtSchoolName.getText().trim());
                        ps.setString(4, txtMajors.getText().trim());
                        ps.setString(5, txtGraduationGAPscores.getText().trim());
                        ps.setString(6, txtDurationOfStudy.getText().trim());
                        ps.executeUpdate();
                        tblModelEducationQualifications.getDataVector().removeAllElements();
                        LoadTableEducationQualifications();
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddEducationQualificationsActionPerformed

    private void btnEditEducationQualificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditEducationQualificationsActionPerformed
        String regexGAP = "^[0123]{1}[.][0-9]{1,2}|[4.0]$";
        Pattern p = Pattern.compile(regexGAP);
        Matcher m = p.matcher(txtGraduationGAPscores.getText());
        String regexDuration = "^[0-9]{4}[-][0-9]{4}$";
        Pattern pattern = Pattern.compile(regexDuration);
        Matcher mc = pattern.matcher(txtDurationOfStudy.getText());
        if (txtEducationQualificationNumber.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter education qualification number is required.");
            txtEducationQualificationNumber.requestFocus();
        } else {
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT educationalQualificationsNumber FROM EducationalQualifications WHERE educationalQualificationsNumber = '" + txtEducationQualificationNumber.getText() + "'");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "educationalQualificationsNumber does not exists.");
                    txtEducationQualificationNumber.requestFocus();
                } else if (txtEmpNumberEducationQualifications.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter employee number is required.");
                    txtEmpNumberEducationQualifications.requestFocus();
                } else {
                    Statement stm = con.createStatement();
                    ResultSet rss = stm.executeQuery("SELECT empNumber FROM Employee WHERE empNumber = '" + txtEmpNumberEducationQualifications.getText() + "'");
                    if (!rss.next()) {
                        JOptionPane.showMessageDialog(null, "empNumber does not exists");
                        txtEmpNumberEducationQualifications.requestFocus();
                    } else if (txtSchoolName.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter school name is required.");
                        txtSchoolName.requestFocus();
                    } else if (txtMajors.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter majors is required.");
                        txtMajors.requestFocus();
                    } else if (txtGraduationGAPscores.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter graduation GAP scores is required.");
                        txtGraduationGAPscores.requestFocus();
                    } else if (!m.find()) {
                        JOptionPane.showMessageDialog(this, "Graduation GAP score just from 1 to 4 and must be in \"0.0\" format");
                        txtGraduationGAPscores.requestFocus();
                    } else if (txtDurationOfStudy.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter duration of study is required.");
                        txtDurationOfStudy.requestFocus();
                    } else if (!mc.find()) {
                        JOptionPane.showMessageDialog(null, "Duration of study must be in \"yyyy-yyyy\" format.");
                        txtDurationOfStudy.requestFocus();
                    } else {
                        String query2 = "UPDATE [dbo].[EducationalQualifications] SET [empNumber] = ?, [schoolName] = ?, [majors] = ?, [graduationGAPScores] = ?, [durationOfStudy] = ? WHERE educationalQualificationsNumber = ?";
                        PreparedStatement psmt = con.prepareStatement(query2);
                        psmt.setString(1, txtEmpNumberEducationQualifications.getText().trim());
                        psmt.setString(2, txtSchoolName.getText().trim());
                        psmt.setString(3, txtMajors.getText().trim());
                        psmt.setString(4, txtGraduationGAPscores.getText().trim());
                        psmt.setString(5, txtDurationOfStudy.getText().trim());
                        psmt.setString(6, txtEducationQualificationNumber.getText().trim());
                        psmt.executeUpdate();
                        tblModelEducationQualifications.getDataVector().removeAllElements();
                        LoadTableEducationQualifications();
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditEducationQualificationsActionPerformed

    private void btnDeleteEducationQualificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEducationQualificationsActionPerformed
        if (txtEducationQualificationNumber.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter education qualification number is required.");
            txtEducationQualificationNumber.requestFocus();
        } else if (txtEmpNumberEducationQualifications.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter employee number is required.");
            txtEmpNumberEducationQualifications.requestFocus();
        } else {
            try {
                Statement stm = con.createStatement();
                ResultSet r = stm.executeQuery("SELECT educationalQualificationsNumber, empNumber FROM EducationalQualifications where educationalQualificationsNumber= '" + txtEducationQualificationNumber.getText() + "' and empNumber= '" + txtEmpNumberEducationQualifications.getText() + "'");
                if (!r.next()) {
                    JOptionPane.showMessageDialog(null, "Invalid education qualification number or employee number.");
                    txtEducationQualificationNumber.requestFocus();
                } else {
                    int click = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this education qualifications?");
                    if (click == 0) {
                        Statement s = con.createStatement();
                        s.executeUpdate("DELETE EducationalQualifications WHERE educationalQualificationsNumber= '" + txtEducationQualificationNumber.getText() + "' and empNumber= '" + txtEmpNumberEducationQualifications.getText() + "'");
                        tblModelEducationQualifications.getDataVector().removeAllElements();
                        LoadTableEducationQualifications();
                        txtEmpNumberEducationQualifications.setText("");
                        txtEducationQualificationNumber.setText("");
                        txtSchoolName.setText("");
                        txtMajors.setText("");
                        txtDurationOfStudy.setText("");
                        txtGraduationGAPscores.setText("");
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(TheEmployeeMasterRecords.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteEducationQualificationsActionPerformed

    private void btnResetEducationQualificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetEducationQualificationsActionPerformed
        txtEmpNumberEducationQualifications.setText("");
        txtEducationQualificationNumber.setText("");
        txtSchoolName.setText("");
        txtMajors.setText("");
        txtDurationOfStudy.setText("");
        txtGraduationGAPscores.setText("");
        tblModelEducationQualifications.getDataVector().removeAllElements();
        LoadTableEducationQualifications();
    }//GEN-LAST:event_btnResetEducationQualificationsActionPerformed

    private void jTableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmployeeMouseClicked
        int row = jTableEmployee.getSelectedRow();
        if (row == -1) {
            return;
        }
        btnEdit.setEnabled(true);
        txtEmpNumber.setText((String) jTableEmployee.getValueAt(row, 0));
        txtEmpName.setText((String) jTableEmployee.getValueAt(row, 1));
        String gender = jTableEmployee.getValueAt(row, 2) + "";
        if (gender.equals("male")) {
            rdbtnMale.setSelected(true);
            rdbtnFemale.setSelected(false);
        } else {
            rdbtnFemale.setSelected(true);
            rdbtnMale.setSelected(false);
        }
        txtAddress.setText((String) jTableEmployee.getValueAt(row, 4));
        txtEmail.setText((String) jTableEmployee.getValueAt(row, 5));
        txtRole.setText((String) jTableEmployee.getValueAt(row, 7));
        txtCurrentDepartment.setText((String) jTableEmployee.getValueAt(row, 8));
        txtCurrentProject.setText((String) jTableEmployee.getValueAt(row, 9));
        txtCurrentPosition.setText((String) jTableEmployee.getValueAt(row, 10));
        txtAllowanceLevelEmployee.setText((String) jTableEmployee.getValueAt(row, 11));
        txtWorkExperience.setText((String) jTableEmployee.getValueAt(row, 12));
        txtAreaNote.setText((String) jTableEmployee.getValueAt(row, 13));
        try {
            Date d;
            txtDateOfBirth.setDate(d = ft.parse((String) jTableEmployee.getValueAt(row, 3)));
            txtDateStartWork.setDate(d = ft.parse((String) jTableEmployee.getValueAt(row, 6)));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jTableEmployeeMouseClicked

    private void jTableProfessionalSkillsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProfessionalSkillsMouseClicked
        int row = jTableProfessionalSkills.getSelectedRow();
        if (row == -1) {
            return;
        }
        btnDeleteProfessionalSkills.setEnabled(true);
        txtProfessionalSkillNumber.setText((String) jTableProfessionalSkills.getValueAt(row, 0));
        txtEngineeringRelatedSkills.setText((String) jTableProfessionalSkills.getValueAt(row, 1));
    }//GEN-LAST:event_jTableProfessionalSkillsMouseClicked

    private void jTableProfessionalSkillsOfEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProfessionalSkillsOfEmployeesMouseClicked
        int row = jTableProfessionalSkillsOfEmployees.getSelectedRow();
        if (row == -1) {
            return;
        }
        btnDeleteProfessionalSkillsOfEmployees.setEnabled(true);
        txtProfessionalSkillsOfEmployeeNumber.setText((String) jTableProfessionalSkillsOfEmployees.getValueAt(row, 0));
        txtEmpNumberProfessionalSkillsOfEmployees.setText((String) jTableProfessionalSkillsOfEmployees.getValueAt(row, 1));
        txtProfessionalSkillNumberProfessionalSkillsOfEmployees.setText((String) jTableProfessionalSkillsOfEmployees.getValueAt(row, 2));
    }//GEN-LAST:event_jTableProfessionalSkillsOfEmployeesMouseClicked

    private void jTableEducationQualificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEducationQualificationsMouseClicked
        int row = jTableEducationQualifications.getSelectedRow();
        if (row == -1) {
            return;
        }
        btnDeleteEducationQualifications.setEnabled(true);
        txtEducationQualificationNumber.setText((String) jTableEducationQualifications.getValueAt(row, 0));
        txtEmpNumberEducationQualifications.setText((String) jTableEducationQualifications.getValueAt(row, 1));
        txtSchoolName.setText((String) jTableEducationQualifications.getValueAt(row, 2));
        txtMajors.setText((String) jTableEducationQualifications.getValueAt(row, 3));
        txtGraduationGAPscores.setText((String) jTableEducationQualifications.getValueAt(row, 4));
        txtDurationOfStudy.setText((String) jTableEducationQualifications.getValueAt(row, 5));
    }//GEN-LAST:event_jTableEducationQualificationsMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddEducationQualifications;
    private javax.swing.JButton btnAddProfessionalSkills;
    private javax.swing.JButton btnAddProfessionalSkillsOfEmployees;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteEducationQualifications;
    private javax.swing.JButton btnDeleteProfessionalSkills;
    private javax.swing.JButton btnDeleteProfessionalSkillsOfEmployees;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditEducationQualifications;
    private javax.swing.JButton btnEditProfessionalSkills;
    private javax.swing.JButton btnEditProfessionalSkillsOfEmployees;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetEducationQualifications;
    private javax.swing.JButton btnResetProfessionalSkills;
    private javax.swing.JButton btnResetProfessionalSkillsOfEmployees;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanelCV;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelEducations;
    private javax.swing.JPanel jPanelEmployee;
    private javax.swing.JPanel jPanelProfessionalSkills;
    private javax.swing.JPanel jPanelProfessionalSkillsOfEmployees;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneNote;
    private javax.swing.JScrollPane jScrollPaneProfessionalSkills;
    private javax.swing.JScrollPane jScrollPaneTableEmployee;
    private javax.swing.JTabbedPane jTabbedPaneEmployee;
    private javax.swing.JTable jTableEducationQualifications;
    private javax.swing.JTable jTableEmployee;
    private javax.swing.JTable jTableProfessionalSkills;
    private javax.swing.JTable jTableProfessionalSkillsOfEmployees;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbAllowanceLevelEmployee;
    private javax.swing.JLabel lbCurrentPosition;
    private javax.swing.JLabel lbCurrentProject;
    private javax.swing.JLabel lbCurretDepartment;
    private javax.swing.JLabel lbDateOfBirth;
    private javax.swing.JLabel lbDateStartWork;
    private javax.swing.JLabel lbDurationOfStudy;
    private javax.swing.JLabel lbEducationQualificationNumber;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmpName;
    private javax.swing.JLabel lbEmpNumber;
    private javax.swing.JLabel lbEmpNumberEducationQualifications;
    private javax.swing.JLabel lbEmpNumberProfessionalSkillsOfEmployees;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbGraduationGAPscores;
    private javax.swing.JLabel lbMajors;
    private javax.swing.JLabel lbNote;
    private javax.swing.JLabel lbProfessionalSkillNumber;
    private javax.swing.JLabel lbProfessionalSkillNumberProfessionalSkillsOfEmployees;
    private javax.swing.JLabel lbProfessionalSkillsOfEmployeeNumber;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbSchoolName;
    private javax.swing.JLabel lbTechnicalSkills;
    private javax.swing.JLabel lbWorkExperience;
    private javax.swing.JLabel lbtitleEducationLevel;
    private javax.swing.JLabel lbtitleEmployeeCV;
    private javax.swing.JLabel lbtitleProfessionalSkills;
    private javax.swing.JRadioButton rdbtnFemale;
    private javax.swing.JRadioButton rdbtnMale;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAllowanceLevelEmployee;
    private javax.swing.JTextArea txtAreaNote;
    private javax.swing.JTextField txtCurrentDepartment;
    private javax.swing.JTextField txtCurrentPosition;
    private javax.swing.JTextField txtCurrentProject;
    private com.toedter.calendar.JDateChooser txtDateOfBirth;
    private com.toedter.calendar.JDateChooser txtDateStartWork;
    private javax.swing.JTextField txtDurationOfStudy;
    private javax.swing.JTextField txtEducationQualificationNumber;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpNumber;
    private javax.swing.JTextField txtEmpNumberEducationQualifications;
    private javax.swing.JTextField txtEmpNumberProfessionalSkillsOfEmployees;
    private javax.swing.JTextField txtEngineeringRelatedSkills;
    private javax.swing.JTextField txtGraduationGAPscores;
    private javax.swing.JTextField txtMajors;
    private javax.swing.JTextField txtProfessionalSkillNumber;
    private javax.swing.JTextField txtProfessionalSkillNumberProfessionalSkillsOfEmployees;
    private javax.swing.JTextField txtProfessionalSkillsOfEmployeeNumber;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtSchoolName;
    private javax.swing.JTextField txtWorkExperience;
    // End of variables declaration//GEN-END:variables

}
