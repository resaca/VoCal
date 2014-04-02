/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Manual.java
 *
 * Created on Apr 15, 2011, 9:03:50 PM
 */

/**
 *
 * @author Rahana,Anu,Sebin,Pratheesh
 */

 
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Manual extends javax.swing.JFrame {


    /** Creates new form Manual */
    public Manual(String username,int dateGet,int monthGet,int yearGet,int date,int month,int year,int val1,int val2) {
        initComponents();
        int v1=val1,v2=val2;
        int d1,d2,mo1,mo2,y1,y2;
        d1=dateGet;d2=date;mo1=monthGet;mo2=month;y1=yearGet;y2=year;
        init(username,d1,mo1,y1,d2,mo2,y2,v1,v2);
    }
    public void init(String username,int dateGet,int monthGet,int yearGet,int date,int month,int year,int val1,int val2){
        try {
            int a = val1;
            int b = val2;
            tab1.setSelectedIndex(a);
            tab2.setSelectedIndex(b);
            user = username;
            dateIntF = dateGet;
            monthIntF = monthGet;
            yearIntF = yearGet;
            dateIntT = date;
            monthIntT = month;
            yearIntT = year;
            cmbDateAnn.setSelectedIndex(dateIntF);
            cmbMonthAnn.setSelectedIndex(monthIntF);
            txtYearAnn.setText(Integer.toString(yearIntF));
            cmbDateBdy.setSelectedIndex(dateIntF);
            cmbMonthBdy.setSelectedIndex(monthIntF);
            txtYearBdy.setText(Integer.toString(yearIntF));
            cmbDateMet.setSelectedIndex(dateIntF);
            cmbMonthMet.setSelectedIndex(monthIntF);
            txtYearMet.setText(Integer.toString(yearIntF));
            cmbDateRem.setSelectedIndex(dateIntF);
            cmbMonthRem.setSelectedIndex(monthIntF);
            txtYearRem.setText(Integer.toString(yearIntF));
            cmbDateVAF.setSelectedIndex(dateIntF);
            cmbDateVAT.setSelectedIndex(dateIntT);
            cmbMonthVAF.setSelectedIndex(monthIntF);
            cmbMonthVAT.setSelectedIndex(monthIntT);
            txtYearVAF.setText(Integer.toString(yearIntF));
            txtYearVAT.setText(Integer.toString(yearIntT));
            cmbDateVBF.setSelectedIndex(dateIntF);
            cmbDateVBT.setSelectedIndex(dateIntT);
            cmbMonthVBF.setSelectedIndex(monthIntF);
            cmbMonthVBT.setSelectedIndex(monthIntT);
            txtYearVBF.setText(Integer.toString(yearIntF));
            txtYearVBT.setText(Integer.toString(yearIntT));
            cmbDateVMF.setSelectedIndex(dateIntF);
            cmbDateVMT.setSelectedIndex(dateIntT);
            cmbMonthVMF.setSelectedIndex(monthIntF);
            cmbMonthVMT.setSelectedIndex(monthIntT);
            txtYearVMF.setText(Integer.toString(yearIntF));
            txtYearVMT.setText(Integer.toString(yearIntT));
            cmbDateVRF.setSelectedIndex(dateIntF);
            cmbDateVRT.setSelectedIndex(dateIntT);
            cmbMonthVRF.setSelectedIndex(monthIntF);
            cmbMonthVRT.setSelectedIndex(monthIntT);
            txtYearVRF.setText(Integer.toString(yearIntF));
            txtYearVRT.setText(Integer.toString(yearIntT));
            lblUser.setText(user);
            createConn();
            stmtRem.executeUpdate("delete from APP.REMINDER where date < '" + CurrentDate + "'");
            stmtMet.executeUpdate("delete from APP.MEETING where date < '" + CurrentDate + "'");
            disconnect();
            createConn();
            selDateF = cmbMonthVAF.getSelectedItem().toString().concat("/".concat(cmbDateVAF.getSelectedItem().toString()).concat("/".concat(txtYearVAF.getText())));
            selDateT = cmbMonthVAT.getSelectedItem().toString().concat("/".concat(cmbDateVAT.getSelectedItem().toString()).concat("/".concat(txtYearVAT.getText())));
            setView("select * from APP.ANNIVERSARY where date between'" + selDateF + "' and '" + selDateT + "'and username='" + user + "'", rsAnn, stmtAnn);
            setView("select * from APP.BIRTHDAY where date between'" + selDateF + "' and '" + selDateT + "'and username='" + user + "'", rsBdy, stmtBdy);
            setView("select * from APP.REMINDER where date between'" + selDateF + "' and '" + selDateT + "'and username='" + user + "'", rsRem, stmtRem);
            setView("select * from APP.MEETING where date between'" + selDateF + "' and '" + selDateT + "'and username='" + user + "'", rsMet, stmtMet);
            setView("select * from APP.NOTES where username='" + user + "'", rsNot, stmtNot);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    public void createConn() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            stmtAnn = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtBdy = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtRem = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtMet = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtNot = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void disconnect(){
        try {
            stmtAnn.close();
            stmtBdy.close();
            stmtMet.close();
            stmtNot.close();
            stmtRem.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab1 = new javax.swing.JTabbedPane();
        anniversary = new javax.swing.JPanel();
        btnCancelAnn = new javax.swing.JButton();
        btnClrAnn = new javax.swing.JButton();
        btnSaveAnn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoteAnn = new javax.swing.JTextArea();
        cmbAPAnn = new javax.swing.JComboBox();
        cmbMinAnn = new javax.swing.JComboBox();
        cmbHourAnn = new javax.swing.JComboBox();
        cmbAlarmAnn = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbDateAnn = new javax.swing.JComboBox();
        cmbMonthAnn = new javax.swing.JComboBox();
        txtYearAnn = new javax.swing.JTextField();
        lblDate1 = new javax.swing.JLabel();
        txtNameAnn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbMonthAnnA = new javax.swing.JComboBox();
        cmbDateAnnA = new javax.swing.JComboBox();
        lblDate5 = new javax.swing.JLabel();
        txtYearAnnA = new javax.swing.JTextField();
        notes = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTitleNote = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoteNote = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        btnCancelNote = new javax.swing.JButton();
        btnClearNote = new javax.swing.JButton();
        btnSaveNote = new javax.swing.JButton();
        reminder = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtTitleRem = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNoteRem = new javax.swing.JTextArea();
        btnSaveRem = new javax.swing.JButton();
        btnClearRem = new javax.swing.JButton();
        btnCancelRem = new javax.swing.JButton();
        lblDate4 = new javax.swing.JLabel();
        cmbDateRem = new javax.swing.JComboBox();
        cmbMonthRem = new javax.swing.JComboBox();
        txtYearRem = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cmbAlarmRem = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        cmbHourRem = new javax.swing.JComboBox();
        cmbMinRem = new javax.swing.JComboBox();
        cmbAPRem = new javax.swing.JComboBox();
        cmbMonthRemA = new javax.swing.JComboBox();
        cmbDateRemA = new javax.swing.JComboBox();
        txtYearRemA = new javax.swing.JTextField();
        lblDate6 = new javax.swing.JLabel();
        birthday = new javax.swing.JPanel();
        btnCancelBdy = new javax.swing.JButton();
        btnClearBdy = new javax.swing.JButton();
        btnSaveBdy = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNoteBdy = new javax.swing.JTextArea();
        cmbAPBdy = new javax.swing.JComboBox();
        cmbMinBdy = new javax.swing.JComboBox();
        cmbHourBdy = new javax.swing.JComboBox();
        cmbAlarmBdy = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDate3 = new javax.swing.JLabel();
        cmbDateBdy = new javax.swing.JComboBox();
        cmbMonthBdy = new javax.swing.JComboBox();
        txtYearBdy = new javax.swing.JTextField();
        txtNameBdy = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbMonthBdyA = new javax.swing.JComboBox();
        cmbDateBdyA = new javax.swing.JComboBox();
        txtYearBdyA = new javax.swing.JTextField();
        lblDate7 = new javax.swing.JLabel();
        meeting = new javax.swing.JPanel();
        btnCancelMet = new javax.swing.JButton();
        btnClearMet = new javax.swing.JButton();
        btnSaveMet = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSubject = new javax.swing.JTextArea();
        cmbAPMet = new javax.swing.JComboBox();
        cmbMinMet = new javax.swing.JComboBox();
        cmbHourMet = new javax.swing.JComboBox();
        cmbAlarmMet = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cmbDateMet = new javax.swing.JComboBox();
        cmbMonthMet = new javax.swing.JComboBox();
        txtYearMet = new javax.swing.JTextField();
        lblDate2 = new javax.swing.JLabel();
        txtNameMet = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbAPFrom = new javax.swing.JComboBox();
        cmbMinFrom = new javax.swing.JComboBox();
        cmbHourFrom = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbAPTo = new javax.swing.JComboBox();
        cmbMinTo = new javax.swing.JComboBox();
        cmbHourTo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbMonthMetA = new javax.swing.JComboBox();
        cmbDateMetA = new javax.swing.JComboBox();
        txtYearMetA = new javax.swing.JTextField();
        lblDate8 = new javax.swing.JLabel();
        view = new javax.swing.JPanel();
        tab2 = new javax.swing.JTabbedPane();
        vBirthday = new javax.swing.JPanel();
        btnPPBdy = new javax.swing.JButton();
        btnNPBdy = new javax.swing.JButton();
        btnGoVBF = new javax.swing.JButton();
        txtYearVBF = new javax.swing.JTextField();
        cmbMonthVBF = new javax.swing.JComboBox();
        cmbDateVBF = new javax.swing.JComboBox();
        txtYearVBT = new javax.swing.JTextField();
        cmbMonthVBT = new javax.swing.JComboBox();
        cmbDateVBT = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        b1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        b2 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        b3 = new javax.swing.JTextArea();
        btnTextBdy = new javax.swing.JButton();
        btnEditBdy1 = new javax.swing.JButton();
        btnDelBdy1 = new javax.swing.JButton();
        btnEditBdy2 = new javax.swing.JButton();
        btnDelBdy2 = new javax.swing.JButton();
        btnEditBdy3 = new javax.swing.JButton();
        btnDelBdy3 = new javax.swing.JButton();
        vMeeting = new javax.swing.JPanel();
        btnPPMet = new javax.swing.JButton();
        btnNPMet = new javax.swing.JButton();
        btnGoVM = new javax.swing.JButton();
        txtYearVMF = new javax.swing.JTextField();
        cmbMonthVMF = new javax.swing.JComboBox();
        cmbDateVMF = new javax.swing.JComboBox();
        txtYearVMT = new javax.swing.JTextField();
        cmbMonthVMT = new javax.swing.JComboBox();
        cmbDateVMT = new javax.swing.JComboBox();
        jScrollPane15 = new javax.swing.JScrollPane();
        m3 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        m2 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        m1 = new javax.swing.JTextArea();
        btnTextMet = new javax.swing.JButton();
        btnEditMet1 = new javax.swing.JButton();
        btnDelMet1 = new javax.swing.JButton();
        btnEditMet2 = new javax.swing.JButton();
        btnDelMet2 = new javax.swing.JButton();
        btnDelMet3 = new javax.swing.JButton();
        btnEditMet3 = new javax.swing.JButton();
        vReminder = new javax.swing.JPanel();
        btnPPRem = new javax.swing.JButton();
        btnNPRem = new javax.swing.JButton();
        btnGoVR = new javax.swing.JButton();
        txtYearVRF = new javax.swing.JTextField();
        cmbMonthVRF = new javax.swing.JComboBox();
        cmbDateVRF = new javax.swing.JComboBox();
        txtYearVRT = new javax.swing.JTextField();
        cmbMonthVRT = new javax.swing.JComboBox();
        cmbDateVRT = new javax.swing.JComboBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        r3 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        r2 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        r1 = new javax.swing.JTextArea();
        btnTextRem = new javax.swing.JButton();
        btnEditRem1 = new javax.swing.JButton();
        btnDelRem1 = new javax.swing.JButton();
        btnEditRem2 = new javax.swing.JButton();
        btnDelRem2 = new javax.swing.JButton();
        btnEditRem3 = new javax.swing.JButton();
        btnDelRem3 = new javax.swing.JButton();
        vNotes = new javax.swing.JPanel();
        ns1 = new javax.swing.JScrollPane();
        n1 = new javax.swing.JTextArea();
        ns2 = new javax.swing.JScrollPane();
        n2 = new javax.swing.JTextArea();
        ns3 = new javax.swing.JScrollPane();
        n3 = new javax.swing.JTextArea();
        btnPPNot = new javax.swing.JButton();
        btnNPNot = new javax.swing.JButton();
        btnTextNot = new javax.swing.JButton();
        btnEditNot1 = new javax.swing.JButton();
        btnDelNot1 = new javax.swing.JButton();
        btnEditNot2 = new javax.swing.JButton();
        btnDelNot2 = new javax.swing.JButton();
        btnEditNot3 = new javax.swing.JButton();
        btnDelNot3 = new javax.swing.JButton();
        vAnniversary = new javax.swing.JPanel();
        btnPPAnn = new javax.swing.JButton();
        btnNPAnn = new javax.swing.JButton();
        btnGoVAF = new javax.swing.JButton();
        txtYearVAF = new javax.swing.JTextField();
        cmbMonthVAF = new javax.swing.JComboBox();
        cmbDateVAF = new javax.swing.JComboBox();
        txtYearVAT = new javax.swing.JTextField();
        cmbMonthVAT = new javax.swing.JComboBox();
        cmbDateVAT = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        a3 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        a2 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        a1 = new javax.swing.JTextArea();
        btnTextAnn = new javax.swing.JButton();
        btnDelAnn1 = new javax.swing.JButton();
        btnEditAnn1 = new javax.swing.JButton();
        btnEditAnn2 = new javax.swing.JButton();
        btnDelAnn2 = new javax.swing.JButton();
        btnDelAnn3 = new javax.swing.JButton();
        btnEditAnn3 = new javax.swing.JButton();
        lblUser = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manual");
        setBounds(new java.awt.Rectangle(450, 80, 0, 0));
        setResizable(false);

        btnCancelAnn.setFont(new java.awt.Font("Smooth", 1, 15));
        btnCancelAnn.setText("Cancel");
        btnCancelAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAnnActionPerformed(evt);
            }
        });

        btnClrAnn.setFont(new java.awt.Font("Smooth", 1, 15)); // NOI18N
        btnClrAnn.setText("Clear");
        btnClrAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClrAnnActionPerformed(evt);
            }
        });

        btnSaveAnn.setFont(new java.awt.Font("Smooth", 1, 15)); // NOI18N
        btnSaveAnn.setText("Save");
        btnSaveAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAnnActionPerformed(evt);
            }
        });

        txtNoteAnn.setColumns(20);
        txtNoteAnn.setRows(5);
        jScrollPane2.setViewportView(txtNoteAnn);

        cmbAPAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        cmbAPAnn.setEnabled(false);

        cmbMinAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMinAnn.setEnabled(false);

        cmbHourAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbHourAnn.setEnabled(false);

        cmbAlarmAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cmbAlarmAnn.setSelectedIndex(1);
        cmbAlarmAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlarmAnnActionPerformed(evt);
            }
        });

        jLabel4.setText("Alarm");

        cmbDateAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDateAnnActionPerformed(evt);
            }
        });

        cmbMonthAnn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthAnn.setToolTipText("Select month..");

        txtYearAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearAnnActionPerformed(evt);
            }
        });

        lblDate1.setText("Date");

        jLabel5.setText("Name");

        jLabel8.setText("Alarm Time");

        jLabel2.setText("Notes");

        cmbMonthAnnA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthAnnA.setToolTipText("Select month..");
        cmbMonthAnnA.setEnabled(false);

        cmbDateAnnA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateAnnA.setEnabled(false);

        lblDate5.setText("Alarm Date");

        txtYearAnnA.setEnabled(false);
        txtYearAnnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearAnnAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout anniversaryLayout = new javax.swing.GroupLayout(anniversary);
        anniversary.setLayout(anniversaryLayout);
        anniversaryLayout.setHorizontalGroup(
            anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anniversaryLayout.createSequentialGroup()
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(anniversaryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSaveAnn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClrAnn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelAnn))
                    .addGroup(anniversaryLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addGroup(anniversaryLayout.createSequentialGroup()
                                .addComponent(lblDate5)
                                .addGap(228, 228, 228))
                            .addGroup(anniversaryLayout.createSequentialGroup()
                                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anniversaryLayout.createSequentialGroup()
                                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDate1)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(anniversaryLayout.createSequentialGroup()
                                        .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, anniversaryLayout.createSequentialGroup()
                                                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cmbAlarmAnn, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbHourAnn, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbDateAnnA, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cmbMonthAnnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmbMinAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(anniversaryLayout.createSequentialGroup()
                                                .addComponent(cmbDateAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cmbMonthAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtYearAnn, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                            .addComponent(txtYearAnnA, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                            .addComponent(cmbAPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNameAnn))))))
                .addGap(214, 214, 214))
        );
        anniversaryLayout.setVerticalGroup(
            anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anniversaryLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNameAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate1)
                    .addComponent(cmbDateAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonthAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbAlarmAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate5)
                    .addComponent(cmbDateAnnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonthAnnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearAnnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbHourAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMinAnn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(anniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelAnn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClrAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveAnn))
                .addGap(62, 62, 62))
        );

        tab1.addTab("Anniversary", anniversary);

        jLabel15.setText("Title");

        txtNoteNote.setColumns(20);
        txtNoteNote.setRows(5);
        jScrollPane1.setViewportView(txtNoteNote);

        jLabel16.setText("Note");

        btnCancelNote.setFont(new java.awt.Font("Smooth", 1, 15));
        btnCancelNote.setText("Cancel");
        btnCancelNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelNoteActionPerformed(evt);
            }
        });

        btnClearNote.setFont(new java.awt.Font("Smooth", 1, 15));
        btnClearNote.setText("Clear");
        btnClearNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNoteActionPerformed(evt);
            }
        });

        btnSaveNote.setFont(new java.awt.Font("Smooth", 1, 15));
        btnSaveNote.setText("Save");
        btnSaveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout notesLayout = new javax.swing.GroupLayout(notes);
        notes.setLayout(notesLayout);
        notesLayout.setHorizontalGroup(
            notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesLayout.createSequentialGroup()
                .addGroup(notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(notesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSaveNote)
                        .addGap(22, 22, 22)
                        .addComponent(btnClearNote)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelNote))
                    .addGroup(notesLayout.createSequentialGroup()
                        .addContainerGap(95, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, notesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(notesLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(82, 82, 82)
                                .addComponent(txtTitleNote, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        notesLayout.setVerticalGroup(
            notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitleNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveNote)
                    .addComponent(btnCancelNote)
                    .addComponent(btnClearNote))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        tab1.addTab("Notes", notes);

        jLabel17.setText("Title");

        jLabel18.setText("Note");

        txtNoteRem.setColumns(20);
        txtNoteRem.setRows(5);
        jScrollPane5.setViewportView(txtNoteRem);

        btnSaveRem.setFont(new java.awt.Font("Smooth", 1, 15));
        btnSaveRem.setText("Save");
        btnSaveRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRemActionPerformed(evt);
            }
        });

        btnClearRem.setFont(new java.awt.Font("Smooth", 1, 15));
        btnClearRem.setText("Clear");
        btnClearRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRemActionPerformed(evt);
            }
        });

        btnCancelRem.setFont(new java.awt.Font("Smooth", 1, 15));
        btnCancelRem.setText("Cancel");
        btnCancelRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelRemActionPerformed(evt);
            }
        });

        lblDate4.setText("Date");

        cmbDateRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDateRemActionPerformed(evt);
            }
        });

        cmbMonthRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthRem.setToolTipText("Select month..");

        jLabel19.setText("Alarm");

        cmbAlarmRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cmbAlarmRem.setSelectedIndex(1);
        cmbAlarmRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlarmRemActionPerformed(evt);
            }
        });

        jLabel20.setText("Alarm Time");

        cmbHourRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbHourRem.setEnabled(false);

        cmbMinRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMinRem.setEnabled(false);

        cmbAPRem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        cmbAPRem.setEnabled(false);

        cmbMonthRemA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthRemA.setToolTipText("Select month..");
        cmbMonthRemA.setEnabled(false);

        cmbDateRemA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateRemA.setEnabled(false);

        txtYearRemA.setEnabled(false);
        txtYearRemA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearRemAActionPerformed(evt);
            }
        });

        lblDate6.setText("Alarm Date");

        javax.swing.GroupLayout reminderLayout = new javax.swing.GroupLayout(reminder);
        reminder.setLayout(reminderLayout);
        reminderLayout.setHorizontalGroup(
            reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reminderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(reminderLayout.createSequentialGroup()
                        .addComponent(btnSaveRem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearRem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelRem))
                    .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reminderLayout.createSequentialGroup()
                            .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(lblDate4)
                                .addComponent(jLabel19)
                                .addComponent(lblDate6))
                            .addGap(59, 59, 59)
                            .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbAlarmRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTitleRem, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reminderLayout.createSequentialGroup()
                                    .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reminderLayout.createSequentialGroup()
                                            .addComponent(cmbDateRem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cmbMonthRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reminderLayout.createSequentialGroup()
                                            .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmbHourRem, 0, 56, Short.MAX_VALUE)
                                                .addComponent(cmbDateRemA, 0, 56, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmbMonthRemA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbMinRem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtYearRemA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                        .addComponent(cmbAPRem, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtYearRem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        reminderLayout.setVerticalGroup(
            reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reminderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitleRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate4)
                    .addComponent(txtYearRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonthRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDateRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cmbAlarmRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate6)
                    .addComponent(txtYearRemA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDateRemA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonthRemA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbAPRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbHourRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbMinRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(reminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelRem)
                    .addComponent(btnSaveRem)
                    .addComponent(btnClearRem))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        tab1.addTab("Reminders", reminder);

        btnCancelBdy.setFont(new java.awt.Font("Smooth", 1, 15));
        btnCancelBdy.setText("Cancel");
        btnCancelBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelBdyActionPerformed(evt);
            }
        });

        btnClearBdy.setFont(new java.awt.Font("Smooth", 1, 15));
        btnClearBdy.setText("Clear");
        btnClearBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearBdyActionPerformed(evt);
            }
        });

        btnSaveBdy.setFont(new java.awt.Font("Smooth", 1, 15));
        btnSaveBdy.setText("Save");
        btnSaveBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBdyActionPerformed(evt);
            }
        });

        txtNoteBdy.setColumns(20);
        txtNoteBdy.setRows(5);
        jScrollPane4.setViewportView(txtNoteBdy);

        cmbAPBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        cmbAPBdy.setEnabled(false);

        cmbMinBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMinBdy.setEnabled(false);

        cmbHourBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbHourBdy.setEnabled(false);

        cmbAlarmBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cmbAlarmBdy.setSelectedIndex(1);
        cmbAlarmBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlarmBdyActionPerformed(evt);
            }
        });

        jLabel9.setText("Alarm Time");

        jLabel10.setText("Alarm");

        lblDate3.setText("Date");

        cmbDateBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbMonthBdy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthBdy.setToolTipText("Select month..");

        jLabel11.setText("Name");

        jLabel14.setText("Notes");

        cmbMonthBdyA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthBdyA.setToolTipText("Select month..");
        cmbMonthBdyA.setEnabled(false);

        cmbDateBdyA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateBdyA.setEnabled(false);

        txtYearBdyA.setEnabled(false);
        txtYearBdyA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearBdyAActionPerformed(evt);
            }
        });

        lblDate7.setText("Alarm Date");

        javax.swing.GroupLayout birthdayLayout = new javax.swing.GroupLayout(birthday);
        birthday.setLayout(birthdayLayout);
        birthdayLayout.setHorizontalGroup(
            birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(birthdayLayout.createSequentialGroup()
                        .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addGroup(birthdayLayout.createSequentialGroup()
                                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDate3)
                                    .addComponent(jLabel11)
                                    .addComponent(lblDate7))
                                .addGap(52, 52, 52)
                                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNameBdy, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, birthdayLayout.createSequentialGroup()
                                            .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmbDateBdyA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmbAlarmBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbDateBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(birthdayLayout.createSequentialGroup()
                                                    .addComponent(cmbMonthBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(txtYearBdy, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                                                .addGroup(birthdayLayout.createSequentialGroup()
                                                    .addComponent(cmbMonthBdyA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtYearBdyA)))))
                                    .addGroup(birthdayLayout.createSequentialGroup()
                                        .addComponent(cmbHourBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbMinBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10))
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, birthdayLayout.createSequentialGroup()
                        .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addGroup(birthdayLayout.createSequentialGroup()
                                .addComponent(btnSaveBdy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClearBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelBdy)))
                        .addGap(63, 63, 63))))
        );
        birthdayLayout.setVerticalGroup(
            birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNameBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate3)
                    .addComponent(cmbMonthBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDateBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbAlarmBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(birthdayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDateBdyA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthBdyA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearBdyA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(birthdayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate7)
                        .addGap(18, 18, 18)))
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbHourBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMinBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(birthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelBdy)
                    .addComponent(btnClearBdy)
                    .addComponent(btnSaveBdy))
                .addGap(137, 137, 137))
        );

        tab1.addTab("B'day", birthday);

        btnCancelMet.setFont(new java.awt.Font("Smooth", 1, 15));
        btnCancelMet.setText("Cancel");
        btnCancelMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelMetActionPerformed(evt);
            }
        });

        btnClearMet.setFont(new java.awt.Font("Smooth", 1, 15));
        btnClearMet.setText("Clear");
        btnClearMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMetActionPerformed(evt);
            }
        });

        btnSaveMet.setFont(new java.awt.Font("Smooth", 1, 15));
        btnSaveMet.setText("Save");
        btnSaveMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMetActionPerformed(evt);
            }
        });

        txtSubject.setColumns(20);
        txtSubject.setRows(5);
        jScrollPane3.setViewportView(txtSubject);

        cmbAPMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        cmbAPMet.setEnabled(false);

        cmbMinMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMinMet.setEnabled(false);

        cmbHourMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbHourMet.setEnabled(false);

        cmbAlarmMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cmbAlarmMet.setSelectedIndex(1);
        cmbAlarmMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlarmMetActionPerformed(evt);
            }
        });

        jLabel6.setText("Alarm");

        cmbDateMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbMonthMet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthMet.setToolTipText("Select month..");

        lblDate2.setText("Date");

        jLabel7.setText("With");

        cmbAPFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        cmbMinFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        cmbHourFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel1.setText("From");

        cmbAPTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        cmbMinTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        cmbHourTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hh", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel3.setText("To");

        jLabel12.setText("Alarm Time");

        jLabel13.setText("Subject");

        cmbMonthMetA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthMetA.setToolTipText("Select month..");
        cmbMonthMetA.setEnabled(false);

        cmbDateMetA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateMetA.setEnabled(false);

        txtYearMetA.setEnabled(false);
        txtYearMetA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearMetAActionPerformed(evt);
            }
        });

        lblDate8.setText("Alarm Date");

        javax.swing.GroupLayout meetingLayout = new javax.swing.GroupLayout(meeting);
        meeting.setLayout(meetingLayout);
        meetingLayout.setHorizontalGroup(
            meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meetingLayout.createSequentialGroup()
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDate2)
                                    .addComponent(lblDate8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbAlarmMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                            .addComponent(cmbHourTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbMinTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                            .addComponent(cmbAPTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                            .addComponent(cmbHourFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbMinFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                            .addComponent(cmbAPFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                            .addComponent(cmbDateMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbMonthMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(19, 19, 19)
                                            .addComponent(txtYearMet, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, meetingLayout.createSequentialGroup()
                                            .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmbDateMetA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbHourMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(17, 17, 17)
                                            .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmbMonthMetA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbMinMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtYearMetA)
                                                .addComponent(cmbAPMet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(txtNameMet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                            .addGroup(meetingLayout.createSequentialGroup()
                                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(meetingLayout.createSequentialGroup()
                                        .addComponent(btnSaveMet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClearMet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelMet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)))
                        .addGap(225, 225, 225))
                    .addGroup(meetingLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(338, Short.MAX_VALUE))))
        );
        meetingLayout.setVerticalGroup(
            meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meetingLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15)
                        .addComponent(lblDate2))
                    .addGroup(meetingLayout.createSequentialGroup()
                        .addComponent(txtNameMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMonthMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbHourFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMinFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAPFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbHourTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMinTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAPTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13)
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbAlarmMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtYearMetA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateMetA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate8)
                            .addComponent(cmbMonthMetA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbHourMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAPMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMinMet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(meetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelMet)
                    .addComponent(btnClearMet)
                    .addComponent(btnSaveMet))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        tab1.addTab("Meeting", meeting);

        btnPPBdy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/previous.png"))); // NOI18N
        btnPPBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPBdyActionPerformed(evt);
            }
        });

        btnNPBdy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/next.png"))); // NOI18N
        btnNPBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNPBdyActionPerformed(evt);
            }
        });

        btnGoVBF.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnGoVBF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/go_button.png"))); // NOI18N
        btnGoVBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVBFActionPerformed(evt);
            }
        });

        txtYearVBF.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtYearVBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearVBFActionPerformed(evt);
            }
        });

        cmbMonthVBF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVBF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVBF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVBF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        txtYearVBT.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbMonthVBT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVBT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthVBTActionPerformed(evt);
            }
        });

        cmbDateVBT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVBT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDateVBTActionPerformed(evt);
            }
        });

        b1.setColumns(20);
        b1.setRows(5);
        b1.setEnabled(false);
        jScrollPane6.setViewportView(b1);

        b2.setColumns(20);
        b2.setRows(5);
        b2.setEnabled(false);
        jScrollPane7.setViewportView(b2);

        b3.setColumns(20);
        b3.setRows(5);
        b3.setEnabled(false);
        jScrollPane8.setViewportView(b3);

        btnTextBdy.setText("Text");
        btnTextBdy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextBdyActionPerformed(evt);
            }
        });

        btnEditBdy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditBdy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBdy1ActionPerformed(evt);
            }
        });

        btnDelBdy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelBdy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelBdy1ActionPerformed(evt);
            }
        });

        btnEditBdy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditBdy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBdy2ActionPerformed(evt);
            }
        });

        btnDelBdy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelBdy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelBdy2ActionPerformed(evt);
            }
        });

        btnEditBdy3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditBdy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBdy3ActionPerformed(evt);
            }
        });

        btnDelBdy3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelBdy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelBdy3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vBirthdayLayout = new javax.swing.GroupLayout(vBirthday);
        vBirthday.setLayout(vBirthdayLayout);
        vBirthdayLayout.setHorizontalGroup(
            vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vBirthdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDateVBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateVBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vBirthdayLayout.createSequentialGroup()
                                .addComponent(cmbMonthVBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(txtYearVBT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vBirthdayLayout.createSequentialGroup()
                                .addComponent(cmbMonthVBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(txtYearVBF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnGoVBF, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addComponent(btnTextBdy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(btnPPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelBdy3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditBdy3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btnDelBdy2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditBdy2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDelBdy1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditBdy1, 0, 0, Short.MAX_VALUE)))
                .addGap(157, 157, 157))
        );
        vBirthdayLayout.setVerticalGroup(
            vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vBirthdayLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDateVBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthVBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearVBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDateVBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthVBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearVBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGoVBF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addComponent(btnEditBdy1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelBdy1)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditBdy2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelBdy2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addComponent(btnEditBdy3)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelBdy3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(vBirthdayLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vBirthdayLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(vBirthdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnNPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPPBdy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(261, 261, 261))
                            .addGroup(vBirthdayLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTextBdy)
                                .addContainerGap())))))
        );

        tab2.addTab("Birthday", vBirthday);

        btnPPMet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/previous.png"))); // NOI18N
        btnPPMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPMetActionPerformed(evt);
            }
        });

        btnNPMet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/next.png"))); // NOI18N
        btnNPMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNPMetActionPerformed(evt);
            }
        });

        btnGoVM.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnGoVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/go_button.png"))); // NOI18N
        btnGoVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVMActionPerformed(evt);
            }
        });

        txtYearVMF.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbMonthVMF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVMF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVMF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVMF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        txtYearVMT.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbMonthVMT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVMT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVMT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVMT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        m3.setColumns(20);
        m3.setRows(5);
        m3.setEnabled(false);
        jScrollPane15.setViewportView(m3);

        m2.setColumns(20);
        m2.setRows(5);
        m2.setEnabled(false);
        jScrollPane16.setViewportView(m2);

        m1.setColumns(20);
        m1.setRows(5);
        m1.setEnabled(false);
        jScrollPane17.setViewportView(m1);

        btnTextMet.setText("Text");
        btnTextMet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextMetActionPerformed(evt);
            }
        });

        btnEditMet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditMet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMet1ActionPerformed(evt);
            }
        });

        btnDelMet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelMet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMet1ActionPerformed(evt);
            }
        });

        btnEditMet2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditMet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMet2ActionPerformed(evt);
            }
        });

        btnDelMet2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelMet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMet2ActionPerformed(evt);
            }
        });

        btnDelMet3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelMet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMet3ActionPerformed(evt);
            }
        });

        btnEditMet3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditMet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMet3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vMeetingLayout = new javax.swing.GroupLayout(vMeeting);
        vMeeting.setLayout(vMeetingLayout);
        vMeetingLayout.setHorizontalGroup(
            vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vMeetingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vMeetingLayout.createSequentialGroup()
                        .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDateVMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateVMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMonthVMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthVMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtYearVMT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearVMF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnGoVM, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vMeetingLayout.createSequentialGroup()
                        .addComponent(btnTextMet)
                        .addGap(98, 98, 98)
                        .addComponent(btnPPMet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNPMet, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vMeetingLayout.createSequentialGroup()
                        .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vMeetingLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnEditMet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(vMeetingLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelMet2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(btnDelMet1, 0, 0, Short.MAX_VALUE)
                                    .addComponent(btnEditMet2, 0, 0, Short.MAX_VALUE)
                                    .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnEditMet3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDelMet3)))))))
                .addGap(64, 64, 64))
        );
        vMeetingLayout.setVerticalGroup(
            vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vMeetingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(vMeetingLayout.createSequentialGroup()
                            .addComponent(cmbDateVMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDateVMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(vMeetingLayout.createSequentialGroup()
                            .addComponent(cmbMonthVMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbMonthVMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vMeetingLayout.createSequentialGroup()
                        .addComponent(txtYearVMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtYearVMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGoVM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vMeetingLayout.createSequentialGroup()
                        .addComponent(btnEditMet1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelMet1))
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vMeetingLayout.createSequentialGroup()
                        .addComponent(btnEditMet2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelMet2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vMeetingLayout.createSequentialGroup()
                        .addComponent(btnEditMet3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelMet3))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTextMet)
                    .addGroup(vMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNPMet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPPMet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tab2.addTab("Meeting", vMeeting);

        btnPPRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/previous.png"))); // NOI18N
        btnPPRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPRemActionPerformed(evt);
            }
        });

        btnNPRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/next.png"))); // NOI18N
        btnNPRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNPRemActionPerformed(evt);
            }
        });

        btnGoVR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGoVR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/go_button.png"))); // NOI18N
        btnGoVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVRActionPerformed(evt);
            }
        });

        txtYearVRF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cmbMonthVRF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVRF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVRF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVRF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        txtYearVRT.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbMonthVRT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVRT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        r3.setColumns(20);
        r3.setRows(5);
        r3.setEnabled(false);
        jScrollPane12.setViewportView(r3);

        r2.setColumns(20);
        r2.setRows(5);
        r2.setEnabled(false);
        jScrollPane13.setViewportView(r2);

        r1.setColumns(20);
        r1.setRows(5);
        r1.setEnabled(false);
        jScrollPane14.setViewportView(r1);

        btnTextRem.setText("Text");
        btnTextRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextRemActionPerformed(evt);
            }
        });

        btnEditRem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditRem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRem1ActionPerformed(evt);
            }
        });

        btnDelRem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelRem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRem1ActionPerformed(evt);
            }
        });

        btnEditRem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditRem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRem2ActionPerformed(evt);
            }
        });

        btnDelRem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelRem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRem2ActionPerformed(evt);
            }
        });

        btnEditRem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditRem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRem3ActionPerformed(evt);
            }
        });

        btnDelRem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelRem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRem3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vReminderLayout = new javax.swing.GroupLayout(vReminder);
        vReminder.setLayout(vReminderLayout);
        vReminderLayout.setHorizontalGroup(
            vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vReminderLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(vReminderLayout.createSequentialGroup()
                            .addComponent(btnTextRem)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPPRem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNPRem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vReminderLayout.createSequentialGroup()
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDateVRF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateVRT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbMonthVRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthVRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtYearVRF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearVRT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGap(4, 4, 4)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelRem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelRem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditRem2, 0, 0, Short.MAX_VALUE)
                            .addComponent(btnEditRem1, 0, 0, Short.MAX_VALUE))
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEditRem3, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(btnDelRem3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vReminderLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGoVR, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        vReminderLayout.setVerticalGroup(
            vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vReminderLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGoVR, 0, 0, Short.MAX_VALUE)
                    .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(vReminderLayout.createSequentialGroup()
                                .addComponent(cmbDateVRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDateVRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vReminderLayout.createSequentialGroup()
                                .addComponent(cmbMonthVRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMonthVRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(vReminderLayout.createSequentialGroup()
                            .addComponent(txtYearVRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtYearVRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vReminderLayout.createSequentialGroup()
                        .addComponent(btnEditRem1)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelRem1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vReminderLayout.createSequentialGroup()
                        .addComponent(btnEditRem2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(btnDelRem2))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vReminderLayout.createSequentialGroup()
                        .addComponent(btnEditRem3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelRem3))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vReminderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNPRem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPPRem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTextRem))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tab2.addTab("Reminder", vReminder);

        n1.setColumns(20);
        n1.setEditable(false);
        n1.setRows(5);
        n1.setEnabled(false);
        ns1.setViewportView(n1);

        n2.setColumns(20);
        n2.setEditable(false);
        n2.setRows(5);
        n2.setEnabled(false);
        ns2.setViewportView(n2);

        n3.setColumns(20);
        n3.setEditable(false);
        n3.setRows(5);
        n3.setEnabled(false);
        ns3.setViewportView(n3);

        btnPPNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/previous.png"))); // NOI18N
        btnPPNot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPNotActionPerformed(evt);
            }
        });

        btnNPNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/next.png"))); // NOI18N
        btnNPNot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNPNotActionPerformed(evt);
            }
        });

        btnTextNot.setText("Text");
        btnTextNot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextNotActionPerformed(evt);
            }
        });

        btnEditNot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditNot1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNot1ActionPerformed(evt);
            }
        });

        btnDelNot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelNot1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelNot1ActionPerformed(evt);
            }
        });

        btnEditNot2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditNot2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNot2ActionPerformed(evt);
            }
        });

        btnDelNot2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N

        btnEditNot3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditNot3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNot3ActionPerformed(evt);
            }
        });

        btnDelNot3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N

        javax.swing.GroupLayout vNotesLayout = new javax.swing.GroupLayout(vNotes);
        vNotes.setLayout(vNotesLayout);
        vNotesLayout.setHorizontalGroup(
            vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vNotesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(vNotesLayout.createSequentialGroup()
                        .addComponent(btnTextNot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPPNot, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNPNot, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ns3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ns1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ns2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelNot3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditNot3, 0, 0, Short.MAX_VALUE)
                    .addComponent(btnDelNot2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditNot2, 0, 0, Short.MAX_VALUE)
                    .addComponent(btnDelNot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditNot1, 0, 0, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        vNotesLayout.setVerticalGroup(
            vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vNotesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vNotesLayout.createSequentialGroup()
                        .addComponent(ns1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ns2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(vNotesLayout.createSequentialGroup()
                        .addComponent(btnEditNot1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelNot1)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditNot2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelNot2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vNotesLayout.createSequentialGroup()
                        .addComponent(btnEditNot3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelNot3))
                    .addComponent(ns3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPPNot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNPNot, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTextNot))
                .addGap(97, 97, 97))
        );

        tab2.addTab("Notes", vNotes);

        btnPPAnn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/previous.png"))); // NOI18N
        btnPPAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPAnnActionPerformed(evt);
            }
        });

        btnNPAnn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/next.png"))); // NOI18N
        btnNPAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNPAnnActionPerformed(evt);
            }
        });

        btnGoVAF.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnGoVAF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/go_button.png"))); // NOI18N
        btnGoVAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVAFActionPerformed(evt);
            }
        });

        txtYearVAF.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtYearVAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearVAFActionPerformed(evt);
            }
        });

        cmbMonthVAF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVAF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbDateVAF.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVAF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateVAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDateVAFActionPerformed(evt);
            }
        });

        txtYearVAT.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbMonthVAT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonthVAT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbMonthVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthVATActionPerformed(evt);
            }
        });

        cmbDateVAT.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDateVAT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDateVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDateVATActionPerformed(evt);
            }
        });

        a3.setColumns(20);
        a3.setRows(5);
        a3.setEnabled(false);
        jScrollPane9.setViewportView(a3);

        a2.setColumns(20);
        a2.setRows(5);
        a2.setEnabled(false);
        jScrollPane10.setViewportView(a2);

        a1.setColumns(20);
        a1.setRows(5);
        a1.setEnabled(false);
        jScrollPane11.setViewportView(a1);

        btnTextAnn.setText("Text");
        btnTextAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextAnnActionPerformed(evt);
            }
        });

        btnDelAnn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelAnn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelAnn1ActionPerformed(evt);
            }
        });

        btnEditAnn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditAnn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAnn1ActionPerformed(evt);
            }
        });

        btnEditAnn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditAnn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAnn2ActionPerformed(evt);
            }
        });

        btnDelAnn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelAnn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelAnn2ActionPerformed(evt);
            }
        });

        btnDelAnn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Trash Bin Full.png"))); // NOI18N
        btnDelAnn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelAnn3ActionPerformed(evt);
            }
        });

        btnEditAnn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/pencil-icon.png"))); // NOI18N
        btnEditAnn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAnn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vAnniversaryLayout = new javax.swing.GroupLayout(vAnniversary);
        vAnniversary.setLayout(vAnniversaryLayout);
        vAnniversaryLayout.setHorizontalGroup(
            vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vAnniversaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vAnniversaryLayout.createSequentialGroup()
                        .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDateVAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDateVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMonthVAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonthVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtYearVAF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGoVAF, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vAnniversaryLayout.createSequentialGroup()
                        .addComponent(btnTextAnn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(btnPPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEditAnn2, 0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelAnn1, 0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditAnn1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelAnn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnDelAnn3, 0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditAnn3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        vAnniversaryLayout.setVerticalGroup(
            vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vAnniversaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(vAnniversaryLayout.createSequentialGroup()
                            .addComponent(cmbMonthVAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbMonthVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(vAnniversaryLayout.createSequentialGroup()
                            .addComponent(cmbDateVAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDateVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtYearVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(vAnniversaryLayout.createSequentialGroup()
                            .addComponent(txtYearVAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33))
                        .addComponent(btnGoVAF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vAnniversaryLayout.createSequentialGroup()
                        .addComponent(btnEditAnn1)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelAnn1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditAnn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelAnn2)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditAnn3)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelAnn3))
                    .addGroup(vAnniversaryLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vAnniversaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNPAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTextAnn))))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        tab2.addTab("Anniversary", vAnniversary);

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view);
        view.setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tab1.addTab("View", view);

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/boot.png"))); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/310px-Android_robot.svg.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(251, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAnnActionPerformed
        try {
            flag=0;
            disconnect();
            String nameAnn=txtNameAnn.getText(),id="";
            date=cmbDateAnn.getSelectedItem().toString();
            month=cmbMonthAnn.getSelectedItem().toString();
            flag=monthCheck(cmbDateAnn,cmbMonthAnn,txtYearAnn);
            //System.out.println("1"+Integer.toString(flag));
            year=txtYearAnn.getText();
            String note=txtNoteAnn.getText();
            String dateS = year.concat("-").concat(month).concat("-").concat(date);
            dateIn=cmbDateAnn.getSelectedIndex();
            monthIn=cmbMonthAnn.getSelectedIndex();
            yearIn=Integer.parseInt(year);
            String alarmAnn=cmbAlarmAnn.getSelectedItem().toString();
            if(cmbAlarmAnn.getSelectedIndex()==0){
            flag=setAlarm(cmbAlarmAnn,cmbDateAnnA,cmbMonthAnnA,txtYearAnnA,cmbHourAnn,cmbMinAnn,flag);
            AlmDate=almYear.concat("-").concat(almMonth).concat("-").concat(almDate);
            AlmTime=setTime(cmbHourAnn, cmbMinAnn, cmbAPAnn);
            }
            if(cmbAlarmAnn.getSelectedIndex()==0){
            flag=monthCheck(cmbDateAnnA,cmbMonthAnnA,txtYearAnnA);
            //System.out.println("2"+Integer.toString(flag));
            setSaveAlarm(alarmYearInt,alarmMonthInt,alarmDateInt,alarmHourInt,alarmMinInt);
            if(cmbDateAnnA.getSelectedItem().toString().equals("dd")||cmbMonthAnnA.getSelectedItem().toString().equals("mm")||txtYearAnnA.getText().equals("")||cmbHourAnn.getSelectedItem().toString().equals("hh")||cmbMinAnn.getSelectedItem().toString().equals("mm"))
            {
             flag=1;
            }
            }
            if(flag==0){
            if(edit==0){
                id=getId("select * from APP.ANNIVERSARY","A");
            }
            else{
                id=idEdit;
            }
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement();
            int i=yearIn,j=alarmYearInt;
            do{
            dateS = Integer.toString(i).concat("-").concat(month).concat("-").concat(date);
            if(cmbAlarmAnn.getSelectedIndex()==0){
            saveAlarmDate=Integer.toString(j).concat("-").concat(almMonth).concat("-").concat(almDate);
            stmt.executeUpdate("insert into APP.ANNIVERSARY values ('"+id+"','"+user+"','" + nameAnn + "','" + dateS + "','" +alarmAnn+"','"+saveAlarmDate+"','"+saveAlarmTime+"','"+note+"')");
            }
            else{
                stmt.executeUpdate("INSERT INTO APP.ANNIVERSARY (ID, USERNAME, NAME, DATE, ALARM, NOTES) VALUES ('"+id+"', '"+user+"', '"+nameAnn+"', '"+dateS+"', '"+alarmAnn+"', '"+note+"')");
                }
                i++;
            j++;
            }while(i<yearIn+10);
            edit=0;
            JOptionPane.showMessageDialog(this, "Details have been saved!!");
            //}
            stmt.close();}
            else if(flag==1)JOptionPane.showMessageDialog(this, "Invalid data or empty fields!!!");
            createConn();
            setView("select * from APP.ANNIVERSARY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsAnn,stmtAnn);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
}//GEN-LAST:event_btnSaveAnnActionPerformed
    int monthCheck(JComboBox a,JComboBox b,JTextField c){
        int f=0;
        switch(b.getSelectedIndex()){
            case 2:
                int yer=Integer.parseInt(c.getText());
                if((yer%4==0&&yer%100!=0)||yer%400==0){
                    if(a.getSelectedIndex()>29){JOptionPane.showMessageDialog(this, "Invalid date!!");a.setFocusable(true);f=1;}
                }
                else if(a.getSelectedIndex()>28){JOptionPane.showMessageDialog(this, "Invalid date!!");a.setFocusable(true);f=1;}
                break;
            case 4:case 6:case 9:case 11:
                if(a.getSelectedIndex()>30){JOptionPane.showMessageDialog(this, "Invalid date!!");a.setFocusable(true);f=1;}
        }
        return f;
    }

    int setAlarm(JComboBox yn,JComboBox cmbDate,JComboBox cmbMonth,JTextField txtYear,JComboBox cmbHour,JComboBox cmbMin,int f){
        if(yn.getSelectedIndex()==0)
            {   f=0;
                almDate = cmbDate.getSelectedItem().toString();
                almMonth = cmbMonth.getSelectedItem().toString();
                almYear = txtYear.getText();
                almHr = cmbHour.getSelectedItem().toString();
                almMin = cmbMin.getSelectedItem().toString();
                if(cmbDate.getSelectedItem().toString().equals("dd"))
                f=1;
                else alarmDateInt=Integer.parseInt(almDate);
                if(cmbMonth.getSelectedItem().toString().equals("mm"))
                f=1;
                else alarmMonthInt=Integer.parseInt(almMonth);
                if(txtYear.getText().equals(""))
                f=1;
                else alarmYearInt=Integer.parseInt(almYear);
                if(cmbHour.getSelectedItem().toString().equals("hh"))
                f=1;
                else alarmHourInt=Integer.parseInt(almHr);
                if(cmbMin.getSelectedItem().toString().equals("mm"))
                f=1;
                else alarmMinInt=Integer.parseInt(almMin);
           }
                
            
             else
                almDate=almHr=almMin=almMonth=almYear="00";
        return f;
    }
    private void btnSaveBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBdyActionPerformed
         try {
            // TODO add your handling code here:
            flag=0;
            disconnect();
            String nameBdy=txtNameBdy.getText(),id="";
            date=cmbDateBdy.getSelectedItem().toString();
            month=cmbMonthBdy.getSelectedItem().toString();
            flag=monthCheck(cmbDateBdy,cmbMonthBdy,txtYearBdy);
            year=txtYearBdy.getText();
            String note=txtNoteBdy.getText();
            String dateS = year.concat("-").concat(month).concat("-").concat(date);
            dateIn=cmbDateBdy.getSelectedIndex();
            monthIn=cmbMonthBdy.getSelectedIndex();
            yearIn=Integer.parseInt(year);
            String alarmBdy=cmbAlarmBdy.getSelectedItem().toString();
            if(cmbAlarmBdy.getSelectedIndex()==0)
            {
                flag=setAlarm(cmbAlarmBdy,cmbDateBdyA,cmbMonthBdyA,txtYearBdyA,cmbHourBdy,cmbMinBdy,flag);
            AlmDate=almYear.concat("-").concat(almMonth).concat("-").concat(almDate);
            AlmTime=setTime(cmbHourBdy, cmbMinBdy, cmbAPBdy);
            }
            if(cmbAlarmBdy.getSelectedIndex()==0){
            flag=monthCheck(cmbDateBdyA,cmbMonthBdyA,txtYearBdyA);
            setSaveAlarm(alarmYearInt,alarmMonthInt,alarmDateInt,alarmHourInt,alarmMinInt);
            if(cmbDateBdyA.getSelectedItem().toString().equals("dd")||cmbMonthBdyA.getSelectedItem().toString().equals("mm")||txtYearBdyA.getText().equals("")||cmbHourBdy.getSelectedItem().toString().equals("hh")||cmbMinBdy.getSelectedItem().toString().equals("mm"))
            {
             flag=1;
            }
            }

            if(flag==0){
            if(edit==0)
            id=getId("select * from APP.BIRTHDAY","B");
            else id=idEdit;
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement();
            int i=yearIn,j=alarmYearInt;
            do{
            dateS = Integer.toString(i).concat("-").concat(month).concat("-").concat(date);
            if(cmbAlarmAnn.getSelectedIndex()==0){
            saveAlarmDate=Integer.toString(j).concat("-").concat(almMonth).concat("-").concat(almDate);
            stmt.executeUpdate("insert into APP.BIRTHDAY values ('"+id+"','"+user+"','" + nameBdy + "','" + dateS + "','" +alarmBdy+"','"+saveAlarmDate+"','"+saveAlarmTime+"','"+note+"')");
            }
            else
                stmt.executeUpdate("INSERT INTO APP.BIRTHDAY (ID, USERNAME, NAME, DATE, ALARM, NOTES) VALUES ('"+id+"', '"+user+"', '"+nameBdy+"', '"+dateS+"', '"+alarmBdy+"', '"+note+"')");
           i++;
            j++;
            }while(i<yearIn+10);
            edit=0;
            JOptionPane.showMessageDialog(this, "Details have been saved");
            //}
            stmt.close();}
            else if(flag==1)JOptionPane.showMessageDialog(this, "Invalid data or empty fields!!!");
            createConn();
            setView("select * from APP.BIRTHDAY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsBdy,stmtBdy);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }//GEN-LAST:event_btnSaveBdyActionPerformed

    private void btnCancelAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAnnActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            StoreRetrieve st = new StoreRetrieve(user);
            st.setVisible(true);
            stmtAnn.close();
            stmtRem.close();
            stmtBdy.close();
            stmtNot.close();
            stmtMet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelAnnActionPerformed

    private void btnCancelNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelNoteActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            StoreRetrieve st = new StoreRetrieve(user);
            st.setVisible(true);
            stmtAnn.close();
            stmtRem.close();
            stmtBdy.close();
            stmtNot.close();
            stmtMet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelNoteActionPerformed

    private void btnCancelRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelRemActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            StoreRetrieve st = new StoreRetrieve(user);
            st.setVisible(true);
            stmtAnn.close();
            stmtRem.close();
            stmtBdy.close();
            stmtNot.close();
            stmtMet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelRemActionPerformed

    private void btnCancelBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelBdyActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            StoreRetrieve st = new StoreRetrieve(user);
            st.setVisible(true);
            stmtAnn.close();
            stmtRem.close();
            stmtBdy.close();
            stmtNot.close();
            stmtMet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelBdyActionPerformed

    private void btnCancelMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelMetActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            StoreRetrieve st = new StoreRetrieve(user);
            st.setVisible(true);
            stmtAnn.close();
            stmtRem.close();
            stmtBdy.close();
            stmtNot.close();
            stmtMet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelMetActionPerformed

    private void btnSaveRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRemActionPerformed
         try {
            // TODO add your handling code here:
            flag=0;
            disconnect();
            String nameRem=txtTitleRem.getText(),id="";
            date=cmbDateRem.getSelectedItem().toString();
            month=cmbMonthRem.getSelectedItem().toString();
            flag=monthCheck(cmbDateRem,cmbMonthRem,txtYearRem);
            year=txtYearRem.getText();
            String note=txtNoteRem.getText();
            String dateS = year.concat("-").concat(month).concat("-").concat(date);
            dateIn=cmbDateRem.getSelectedIndex();
            monthIn=cmbMonthRem.getSelectedIndex();
            yearIn=Integer.parseInt(year);
            String alarmRem=cmbAlarmRem.getSelectedItem().toString();
            if(cmbAlarmRem.getSelectedIndex()==0){
            flag=setAlarm(cmbAlarmRem,cmbDateRemA,cmbMonthRemA,txtYearRemA,cmbHourRem,cmbMinRem,flag);
            AlmDate=almYear.concat("-").concat(almMonth).concat("-").concat(almDate);
            AlmTime=setTime(cmbHourRem, cmbMinRem, cmbAPRem);
            }
            if(cmbAlarmRem.getSelectedIndex()==0){
            flag=monthCheck(cmbDateRemA,cmbMonthRemA,txtYearRemA);
            setSaveAlarm(alarmYearInt,alarmMonthInt,alarmDateInt,alarmHourInt,alarmMinInt);
            if(cmbDateRemA.getSelectedItem().toString().equals("dd")||cmbMonthRemA.getSelectedItem().toString().equals("mm")||txtYearRemA.getText().equals("")||cmbHourRem.getSelectedItem().toString().equals("hh")||cmbMinRem.getSelectedItem().toString().equals("mm"))
            {
             flag=1;
            }
            }

            if(flag==0){
            if(edit==0)
            id=getId("select * from APP.REMINDER","R");
            else id=idEdit;
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement();
            if(cmbAlarmAnn.getSelectedIndex()==0)
            stmt.executeUpdate("insert into APP.REMINDER values ('"+id+"','"+user+"','" + nameRem + "','" + dateS + "','" +alarmRem+"','"+saveAlarmDate+"','"+saveAlarmTime+"','"+note+"')");
            else
                stmt.executeUpdate("INSERT INTO APP.REMINDER (ID, USERNAME, NAME, DATE, ALARM, NOTES) VALUES ('"+id+"', '"+user+"', '"+nameRem+"', '"+dateS+"', '"+alarmRem+"', '"+note+"')");
            edit=0;
            JOptionPane.showMessageDialog(this, "Details have been saved");
            stmt.close();}
            else if(flag==1)JOptionPane.showMessageDialog(this, "Invalid data or empty fields!!!");
            createConn();
            setView("select * from APP.REMINDER where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsRem,stmtRem);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }//GEN-LAST:event_btnSaveRemActionPerformed

    private void txtYearRemAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearRemAActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtYearRemAActionPerformed

    private void txtYearBdyAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearBdyAActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtYearBdyAActionPerformed

    private void txtYearMetAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearMetAActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtYearMetAActionPerformed

    private void cmbAlarmAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlarmAnnActionPerformed
      // TODO add your handling code here:
        actAlarm(cmbAlarmAnn,cmbDateAnnA,cmbMonthAnnA,txtYearAnnA,cmbHourAnn,cmbMinAnn,cmbAPAnn);        
    }//GEN-LAST:event_cmbAlarmAnnActionPerformed

    private void cmbDateRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDateRemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDateRemActionPerformed

    private void txtYearAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearAnnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearAnnActionPerformed

    private void btnSaveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNoteActionPerformed
        try {
            // TODO add your handling code here:
            //stmtNot.close();
            disconnect();
            String titleNote = txtTitleNote.getText(),id="";
            String note = txtNoteNote.getText();
            if(!note.isEmpty()){
            if(edit==0)
            id = getId("select * from APP.NOTES", "N");
            else
            id=idEdit;
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate("insert into APP.NOTES values ('" + id + "','" + user + "','" + titleNote + "','" + note + "')");
            stmt.close();
            edit=0;
            JOptionPane.showMessageDialog(this, "Details have been saved.");
            }
            else
            JOptionPane.showMessageDialog(this, "Invalid data or empty fields!!!");
            createConn();
            setView("select * from APP.NOTES where username='"+user+"'",rsNot,stmtNot);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnSaveNoteActionPerformed

    private void cmbAlarmMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlarmMetActionPerformed
        // TODO add your handling code here:
        actAlarm(cmbAlarmMet,cmbDateMetA,cmbMonthMetA,txtYearMetA,cmbHourMet,cmbMinMet,cmbAPMet);
    }//GEN-LAST:event_cmbAlarmMetActionPerformed

    private void cmbAlarmBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlarmBdyActionPerformed
        // TODO add your handling code here:
        actAlarm(cmbAlarmBdy,cmbDateBdyA,cmbMonthBdyA,txtYearBdyA,cmbHourBdy,cmbMinBdy,cmbAPBdy);
    }//GEN-LAST:event_cmbAlarmBdyActionPerformed

    private void cmbAlarmRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlarmRemActionPerformed
        // TODO add your handling code here:
        actAlarm(cmbAlarmRem,cmbDateRemA,cmbMonthRemA,txtYearRemA,cmbHourRem,cmbMinRem,cmbAPRem);
    }//GEN-LAST:event_cmbAlarmRemActionPerformed

    private void btnNPAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNPAnnActionPerformed
      
            // TODO add your handling code here:
            printData(rsAnn,a1,a2,a3,btnPPAnn,btnNPAnn,"anniversary",btnEditAnn1,btnEditAnn2,btnEditAnn3,btnDelAnn1,btnDelAnn2,btnDelAnn3);
            btnPPAnn.setEnabled(true);
}//GEN-LAST:event_btnNPAnnActionPerformed

    private void btnPPAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPAnnActionPerformed
        try {
            // TODO add your handling code here:
        for(int i=0;i<6;i++)
                rsAnn.previous();
            //rsAnn.next();
            printData(rsAnn,a1,a2,a3,btnPPAnn,btnNPAnn,"anniversary",btnEditAnn1,btnEditAnn2,btnEditAnn3,btnDelAnn1,btnDelAnn2,btnDelAnn3);
            btnNPAnn.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPPAnnActionPerformed

    private void btnPPBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPBdyActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
        for(int i=0;i<6;i++)
                rsBdy.previous();
            //rsAnn.next();
            printData(rsBdy,b1,b2,b3,btnPPBdy,btnNPBdy,"birthday",btnEditBdy1,btnEditBdy2,btnEditBdy3,btnDelBdy1,btnDelBdy2,btnDelBdy3);
            btnNPBdy.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnPPBdyActionPerformed

    private void btnNPBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNPBdyActionPerformed
        // TODO add your handling code here:
        printData(rsBdy,b1,b2,b3,btnPPBdy,btnNPBdy,"birthday",btnEditBdy1,btnEditBdy2,btnEditBdy3,btnDelBdy1,btnDelBdy2,btnDelBdy3);
        btnPPBdy.setEnabled(true);
}//GEN-LAST:event_btnNPBdyActionPerformed

    private void btnPPRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPRemActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
        for(int i=0;i<6;i++)
                rsRem.previous();
            //rsAnn.next();
            printData(rsRem,r1,r2,r3,btnPPRem,btnNPRem,"reminder",btnEditRem1,btnEditRem2,btnEditRem3,btnDelRem1,btnDelRem2,btnDelRem3);
            btnNPRem.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnPPRemActionPerformed

    private void btnNPRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNPRemActionPerformed
        // TODO add your handling code here:
        printData(rsRem,r1,r2,r3,btnPPRem,btnNPRem,"reminder",btnEditRem1,btnEditRem2,btnEditRem3,btnDelRem1,btnDelRem2,btnDelRem3);
        btnPPRem.setEnabled(true);
}//GEN-LAST:event_btnNPRemActionPerformed

    private void btnPPMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPMetActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
        for(int i=0;i<6;i++)
                rsMet.previous();
            //rsAnn.next();
            printData(rsMet,m1,m2,m3,btnPPMet,btnNPMet,"meeting",btnEditMet1,btnEditMet2,btnEditMet3,btnDelMet1,btnDelMet2,btnDelMet3);
            btnNPMet.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnPPMetActionPerformed

    private void btnNPMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNPMetActionPerformed
        // TODO add your handling code here:
        printData(rsMet,m1,m2,m3,btnPPMet,btnNPMet,"meeting",btnEditMet1,btnEditMet2,btnEditMet3,btnDelMet1,btnDelMet2,btnDelMet3);
        btnPPMet.setEnabled(true);
}//GEN-LAST:event_btnNPMetActionPerformed

    private void btnPPNotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPNotActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
        for(int i=0;i<6;i++)
                rsNot.previous();
            //rsAnn.next();
            printData(rsNot,n1,n2,n3,btnPPNot,btnNPNot,"note",btnEditNot1,btnEditNot2,btnEditNot3,btnDelNot1,btnDelNot2,btnDelNot3);
            btnNPNot.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnPPNotActionPerformed

    private void btnNPNotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNPNotActionPerformed
        // TODO add your handling code here:
        printData(rsNot,n1,n2,n3,btnPPNot,btnNPNot,"note",btnEditNot1,btnEditNot2,btnEditNot3,btnDelNot1,btnDelNot2,btnDelNot3);
        btnPPNot.setEnabled(true);
}//GEN-LAST:event_btnNPNotActionPerformed

    private void btnGoVBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVBFActionPerformed
        // TODO add your handling code here:
        if(txtYearVBF.getText().equals(txtYearVBT.getText())){
        selDateF=cmbMonthVBF.getSelectedItem().toString().concat("/".concat(cmbDateVBF.getSelectedItem().toString()).concat("/".concat(txtYearVBF.getText())));
        selDateT=cmbMonthVBT.getSelectedItem().toString().concat("/".concat(cmbDateVBT.getSelectedItem().toString()).concat("/".concat(txtYearVBT.getText())));
        setView("select * from APP.BIRTHDAY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsBdy,stmtBdy);
        }
        else
            JOptionPane.showMessageDialog(this, "Years must be equal!!");
}//GEN-LAST:event_btnGoVBFActionPerformed

    private void btnGoVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVMActionPerformed
        // TODO add your handling code here:
        selDateF=cmbMonthVMF.getSelectedItem().toString().concat("/".concat(cmbDateVMF.getSelectedItem().toString()).concat("/".concat(txtYearVMF.getText())));
        selDateT=cmbMonthVMT.getSelectedItem().toString().concat("/".concat(cmbDateVMT.getSelectedItem().toString()).concat("/".concat(txtYearVMT.getText())));
        setView("select * from APP.MEETING where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsMet,stmtMet);
}//GEN-LAST:event_btnGoVMActionPerformed

    private void btnGoVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVRActionPerformed
        // TODO add your handling code here:
        selDateF=cmbMonthVRF.getSelectedItem().toString().concat("/".concat(cmbDateVRF.getSelectedItem().toString()).concat("/".concat(txtYearVRF.getText())));
        selDateT=cmbMonthVRT.getSelectedItem().toString().concat("/".concat(cmbDateVRT.getSelectedItem().toString()).concat("/".concat(txtYearVRT.getText())));
        setView("select * from APP.REMINDER where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsRem,stmtRem);
}//GEN-LAST:event_btnGoVRActionPerformed

    private void btnClrAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClrAnnActionPerformed
        // TODO add your handling code here:
        txtNameAnn.setText("");
        cmbDateAnn.setSelectedIndex(0);
        cmbMonthAnn.setSelectedIndex(0);
        txtYearAnn.setText("");
        cmbAlarmAnn.setSelectedIndex(1);
        cmbDateAnnA.setSelectedIndex(0);
        cmbMonthAnnA.setSelectedIndex(0);
        txtYearAnnA.setText("");
        cmbHourAnn.setSelectedIndex(0);
        cmbMinAnn.setSelectedIndex(0);
        cmbAPAnn.setSelectedIndex(0);
        txtNoteAnn.setText("");
    }//GEN-LAST:event_btnClrAnnActionPerformed

    private void btnClearRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRemActionPerformed
        // TODO add your handling code here:
        txtTitleRem.setText("");
        cmbDateRem.setSelectedIndex(0);
        cmbMonthRem.setSelectedIndex(0);
        txtYearRem.setText("");
        cmbAlarmRem.setSelectedIndex(1);
        cmbDateRemA.setSelectedIndex(0);
        cmbMonthRemA.setSelectedIndex(0);
        txtYearRemA.setText("");
        cmbHourRem.setSelectedIndex(0);
        cmbMinRem.setSelectedIndex(0);
        cmbAPRem.setSelectedIndex(0);
        txtNoteRem.setText("");
    }//GEN-LAST:event_btnClearRemActionPerformed

    private void btnClearBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearBdyActionPerformed
        // TODO add your handling code here:
        txtNameBdy.setText("");
        cmbDateBdy.setSelectedIndex(0);
        cmbMonthBdy.setSelectedIndex(0);
        txtYearBdy.setText("");
        cmbAlarmBdy.setSelectedIndex(1);
        cmbDateBdyA.setSelectedIndex(0);
        cmbMonthBdyA.setSelectedIndex(0);
        txtYearBdyA.setText("");
        cmbHourBdy.setSelectedIndex(0);
        cmbMinBdy.setSelectedIndex(0);
        cmbAPBdy.setSelectedIndex(0);
        txtNoteBdy.setText("");
    }//GEN-LAST:event_btnClearBdyActionPerformed

    private void btnClearMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMetActionPerformed
        // TODO add your handling code here:
        txtNameMet.setText("");
        cmbDateMet.setSelectedIndex(0);
        cmbMonthMet.setSelectedIndex(0);
        txtYearMet.setText("");
        cmbHourFrom.setSelectedIndex(0);
        cmbMinFrom.setSelectedIndex(0);
        cmbAPFrom.setSelectedIndex(0);
        cmbHourTo.setSelectedIndex(0);
        cmbMinTo.setSelectedIndex(0);
        cmbAPFrom.setSelectedIndex(0);
        cmbAPTo.setSelectedIndex(0);
        cmbAlarmMet.setSelectedIndex(1);
        cmbDateMetA.setSelectedIndex(0);
        cmbMonthMetA.setSelectedIndex(0);
        txtYearMetA.setText("");
        cmbHourMet.setSelectedIndex(0);
        cmbMinMet.setSelectedIndex(0);
        cmbAPMet.setSelectedIndex(0);
        txtSubject.setText("");
    }//GEN-LAST:event_btnClearMetActionPerformed

    private void btnClearNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNoteActionPerformed
        // TODO add your handling code here:
        txtTitleNote.setText("");
        txtNoteNote.setText("");
    }//GEN-LAST:event_btnClearNoteActionPerformed

    private void btnGoVAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVAFActionPerformed
        // TODO add your handling code here:
        if(txtYearVAF.getText().equals(txtYearVAT.getText())){
        selDateF=cmbMonthVAF.getSelectedItem().toString().concat("/".concat(cmbDateVAF.getSelectedItem().toString()).concat("/".concat(txtYearVAF.getText())));
        selDateT=cmbMonthVAT.getSelectedItem().toString().concat("/".concat(cmbDateVAT.getSelectedItem().toString()).concat("/".concat(txtYearVAT.getText())));
        //System.out.println(selDate);
        setView("select * from APP.ANNIVERSARY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsAnn,stmtAnn);
        }
        else
            JOptionPane.showMessageDialog(this, "Year must be equal!!");
}//GEN-LAST:event_btnGoVAFActionPerformed

    private void cmbDateVAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDateVAFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDateVAFActionPerformed

    private void cmbMonthVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthVATActionPerformed
        // TODO add your handling code here:
        txtYearVAT.setText(txtYearVAF.getText());
    }//GEN-LAST:event_cmbMonthVATActionPerformed

    private void cmbMonthVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthVBTActionPerformed
        // TODO add your handling code here:
        txtYearVBT.setText(txtYearVBF.getText());
    }//GEN-LAST:event_cmbMonthVBTActionPerformed

    private void txtYearVBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearVBFActionPerformed
        // TODO add your handling code here:
        txtYearVBT.setText(txtYearVBF.getText());
    }//GEN-LAST:event_txtYearVBFActionPerformed

    private void txtYearVAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearVAFActionPerformed
        // TODO add your handling code here:
        txtYearVAT.setText(txtYearVAF.getText());
    }//GEN-LAST:event_txtYearVAFActionPerformed

    private void cmbDateVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDateVATActionPerformed
        // TODO add your handling code here:
        txtYearVAT.setText(txtYearVAF.getText());
    }//GEN-LAST:event_cmbDateVATActionPerformed

    private void cmbDateVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDateVBTActionPerformed
        // TODO add your handling code here:
        txtYearVBT.setText(txtYearVBF.getText());
    }//GEN-LAST:event_cmbDateVBTActionPerformed

    private void btnTextBdyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextBdyActionPerformed
        // TODO add your handling code here:
            disconnect();
            this.setVisible(false);
            Text T = new Text(user);
            T.setVisible(true);
}//GEN-LAST:event_btnTextBdyActionPerformed

    private void btnTextAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextAnnActionPerformed
        // TODO add your handli
            disconnect();
            this.setVisible(false);
            Text T = new Text(user);
            T.setVisible(true);
}//GEN-LAST:event_btnTextAnnActionPerformed

    private void btnTextNotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextNotActionPerformed
        // TODO add your handling code here:
            disconnect();
            this.setVisible(false);
            Text T = new Text(user);
            T.setVisible(true);
}//GEN-LAST:event_btnTextNotActionPerformed

    private void btnTextRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextRemActionPerformed
        // TODO add your handling code here:
            disconnect();
            this.setVisible(false);
            Text T = new Text(user);
            T.setVisible(true);
}//GEN-LAST:event_btnTextRemActionPerformed

    private void btnTextMetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextMetActionPerformed
            disconnect();
            this.setVisible(false);
            Text T = new Text(user);
            T.setVisible(true);
}//GEN-LAST:event_btnTextMetActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            disconnect();
            this.setVisible(false);
            Home h = new Home();
            h.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
    private void editBdy(JTextArea z){
            try{
            disconnect();
            edit = 1;
            int index = z.getText().indexOf("\n"),val=0;
            idEdit = z.getText().substring(0, index);va="";
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from APP.BIRTHDAY where username='" + user + "' and id= '" + idEdit + "'");
            rs.beforeFirst();
            if (rs.next()) {
                txtNameBdy.setText(rs.getString(3));
                cmbDateBdy.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(8, 10)));
                cmbMonthBdy.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(5, 7)));
                txtYearBdy.setText(rs.getString(4).substring(0, 4));
                cmbAlarmBdy.setSelectedItem(rs.getString(5));
                if(rs.getString(5).equals("Yes")){
                cmbDateBdyA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(8, 10)));
                cmbMonthBdyA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(5, 7)));
                txtYearBdyA.setText(rs.getString(6).substring(0, 4));
                val=Integer.parseInt(rs.getString(7).substring(0, 2));
                System.out.println(val);
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourBdy.setSelectedIndex(val);
                cmbMinBdy.setSelectedItem(rs.getString(7).substring(3, 5));
                cmbAPBdy.setSelectedItem(va);
                }
            }
            stmt.executeUpdate("delete from APP.BIRTHDAY where id='"+idEdit+"'");
            stmt.close();
            tab1.setSelectedIndex(3);
            createConn();
            setView("select * from APP.BIRTHDAY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsBdy,stmtBdy);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void editRem(JTextArea z){
        try {
            disconnect();
            edit = 1;
            int index = z.getText().indexOf("\n"),val=0;
            idEdit = z.getText().substring(0, index);va="";
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from APP.REMINDER where username='" + user + "' and id= '" + idEdit + "'");
            rs.beforeFirst();
            if (rs.next()) {
                txtTitleRem.setText(rs.getString(3));
                cmbDateRem.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(8, 10)));
                cmbMonthRem.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(5, 7)));
                txtYearRem.setText(rs.getString(4).substring(0, 4));
                cmbAlarmRem.setSelectedItem(rs.getString(5));
                if(rs.getString(5).equals("Yes")){
                cmbDateRemA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(8, 10)));
                cmbMonthRemA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(5, 7)));
                txtYearRemA.setText(rs.getString(6).substring(0, 4));
                val=Integer.parseInt(rs.getString(7).substring(0, 2));
                System.out.println(val);
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourRem.setSelectedIndex(val);
                cmbMinRem.setSelectedItem(rs.getString(7).substring(3, 5));
                cmbAPRem.setSelectedItem(va);
                }
            }
            stmt.executeUpdate("delete from APP.REMINDER where id='"+idEdit+"'");
            stmt.close();
            tab1.setSelectedIndex(2);
            createConn();
            setView("select * from APP.REMINDER where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsRem,stmtRem);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void editAnn(JTextArea z){
        try {
            disconnect();
            edit = 1;
            int index = z.getText().indexOf("\n"),val=0;
            idEdit = z.getText().substring(0, index);va="";
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from APP.ANNIVERSARY where username='" + user + "' and id= '" + idEdit + "'");
            rs.beforeFirst();
            if (rs.next()) {
                txtNameAnn.setText(rs.getString(3));
                cmbDateAnn.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(8, 10)));
                cmbMonthAnn.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(5, 7)));
                txtYearAnn.setText(rs.getString(4).substring(0, 4));
                cmbAlarmAnn.setSelectedItem(rs.getString(5));
                if(rs.getString(5).equals("Yes")){
                cmbDateAnnA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(8, 10)));
                cmbMonthAnnA.setSelectedIndex(Integer.parseInt(rs.getString(6).substring(5, 7)));
                txtYearAnnA.setText(rs.getString(6).substring(0, 4));
                val=Integer.parseInt(rs.getString(7).substring(0, 2));
                System.out.println(val);
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourAnn.setSelectedIndex(val);
                cmbMinAnn.setSelectedItem(rs.getString(7).substring(3, 5));
                cmbAPAnn.setSelectedItem(va);
                }
            }
            stmt.executeUpdate("delete from APP.ANNIVERSARY where id='"+idEdit+"'");
            stmt.close();
            tab1.setSelectedIndex(0);
            createConn();
            setView("select * from APP.ANNIVERSARY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsAnn,stmtAnn);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void editMet(JTextArea z){
        try {
            disconnect();
            edit = 1;
            int index = z.getText().indexOf("\n"),val=0;
            idEdit = z.getText().substring(0, index);va="";
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from APP.MEETING where username='" + user + "' and id= '" + idEdit + "'");
            rs.beforeFirst();
            if (rs.next()) {
                txtNameMet.setText(rs.getString(3));
                cmbDateMet.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(8, 10)));
                cmbMonthMet.setSelectedIndex(Integer.parseInt(rs.getString(4).substring(5, 7)));
                txtYearMet.setText(rs.getString(4).substring(0, 4));
                cmbAlarmMet.setSelectedItem(rs.getString(7));
                val=Integer.parseInt(rs.getString(5).substring(0, 2));
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourFrom.setSelectedIndex(val);
                cmbMinFrom.setSelectedItem(rs.getString(5).substring(3, 5));
                cmbAPFrom.setSelectedItem(va);
                val=Integer.parseInt(rs.getString(6).substring(0, 2));
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourTo.setSelectedIndex(val);
                cmbMinTo.setSelectedItem(rs.getString(6).substring(3, 5));
                cmbAPTo.setSelectedItem(va);
                if(rs.getString(7).equals("Yes")){
                cmbDateMetA.setSelectedIndex(Integer.parseInt(rs.getString(8).substring(8, 10)));
                cmbMonthMetA.setSelectedIndex(Integer.parseInt(rs.getString(8).substring(5, 7)));
                txtYearMetA.setText(rs.getString(8).substring(0, 4));
                val=Integer.parseInt(rs.getString(9).substring(0, 2));
                if(val>12){val=val-12;va="PM";}
                else if(val==0){val=12;va="AM";}
                cmbHourMet.setSelectedIndex(val);
                cmbMinMet.setSelectedItem(rs.getString(9).substring(3, 5));
                cmbAPMet.setSelectedItem(va);
                }
            }
            stmt.executeUpdate("delete from APP.MEETING where id='"+idEdit+"'");
            stmt.close();
            tab1.setSelectedIndex(4);
            createConn();
            setView("select * from APP.MEETING where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsMet,stmtMet);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void editNote(JTextArea z){
        try {
            disconnect();
            edit = 1;
            int index = z.getText().indexOf("\n"),val=0;
            idEdit = z.getText().substring(0, index);va="";
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from APP.NOTES where username='" + user + "' and id= '" + idEdit + "'");
            rs.beforeFirst();
            if (rs.next()) {
                txtTitleNote.setText(rs.getString(3));
                txtNoteNote.setText(rs.getString(4));
            }
            stmt.executeUpdate("delete from APP.NOTES where id='"+idEdit+"'");
            stmt.close();
            tab1.setSelectedIndex(1);
            createConn();
            setView("select * from APP.NOTES where username='"+user+"'",rsNot,stmtNot);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btnEditAnn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAnn1ActionPerformed
        editAnn(a1);
}//GEN-LAST:event_btnEditAnn1ActionPerformed

    private void btnEditBdy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBdy2ActionPerformed
        editBdy(b2);
    }//GEN-LAST:event_btnEditBdy2ActionPerformed

    private void btnEditBdy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBdy3ActionPerformed
        editBdy(b3);
    }//GEN-LAST:event_btnEditBdy3ActionPerformed

    private void btnEditAnn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAnn2ActionPerformed
        // TODO add your handling code here:
        editAnn(a2);
    }//GEN-LAST:event_btnEditAnn2ActionPerformed

    private void btnEditAnn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAnn3ActionPerformed
        // TODO add your handling code here:
        editAnn(a3);
    }//GEN-LAST:event_btnEditAnn3ActionPerformed

    private void btnEditBdy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBdy1ActionPerformed
        // TODO add your handling code here:
        editBdy(b1);
    }//GEN-LAST:event_btnEditBdy1ActionPerformed

    private void btnEditRem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRem1ActionPerformed
        // TODO add your handling code here:
        editRem(r1);
    }//GEN-LAST:event_btnEditRem1ActionPerformed

    private void btnEditRem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRem2ActionPerformed
        // TODO add your handling code here:
        editRem(r2);
    }//GEN-LAST:event_btnEditRem2ActionPerformed

    private void btnEditRem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRem3ActionPerformed
        // TODO add your handling code here:
        editRem(r3);
    }//GEN-LAST:event_btnEditRem3ActionPerformed

    private void btnDelAnn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelAnn3ActionPerformed
        // TODO add your handling code here:
        delete(a3, "anniversary");
}//GEN-LAST:event_btnDelAnn3ActionPerformed

    private void btnEditMet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMet1ActionPerformed
        // TODO add your handling code here:
            editMet(m1);
    }//GEN-LAST:event_btnEditMet1ActionPerformed

    private void btnEditMet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMet2ActionPerformed
        // TODO add your handling code here:
        editMet(m2);
    }//GEN-LAST:event_btnEditMet2ActionPerformed

    private void btnEditMet3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMet3ActionPerformed
        // TODO add your handling code here:
        editMet(m3);
    }//GEN-LAST:event_btnEditMet3ActionPerformed

    private void btnDelMet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMet1ActionPerformed
        // TODO add your handling code here:
        delete(m1,"meeting");
    }//GEN-LAST:event_btnDelMet1ActionPerformed

    private void btnDelMet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMet2ActionPerformed
        // TODO add your handling code here:
        delete(m2,"meeting");
    }//GEN-LAST:event_btnDelMet2ActionPerformed

    private void btnDelMet3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMet3ActionPerformed
        // TODO add your handling code here:
        delete(m3,"meeting");
    }//GEN-LAST:event_btnDelMet3ActionPerformed

    private void btnDelNot1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelNot1ActionPerformed
        // TODO add your handling code here:
        delete(n1, "note");
    }//GEN-LAST:event_btnDelNot1ActionPerformed

    private void btnDelNot2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelNot2ActionPerformed
        // TODO add your handling code here:
        delete(n2, "note");
    }//GEN-LAST:event_btnDelNot2ActionPerformed

    private void btnDelNot3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelNot3ActionPerformed
        // TODO add your handling code here:
        delete(n3, "note");
    }//GEN-LAST:event_btnDelNot3ActionPerformed

    private void btnDelRem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRem1ActionPerformed
        // TODO add your handling code here
        delete(r1, "reminder");
    }//GEN-LAST:event_btnDelRem1ActionPerformed

    private void btnDelRem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRem2ActionPerformed
        // TODO add your handling code here:
        delete(r2, "reminder");
    }//GEN-LAST:event_btnDelRem2ActionPerformed

    private void btnDelRem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRem3ActionPerformed
        // TODO add your handling code here:
        delete(r3, "reminder");
    }//GEN-LAST:event_btnDelRem3ActionPerformed

    private void btnDelAnn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelAnn1ActionPerformed
        // TODO add your handling code here:
        delete(a1, "anniversary");
    }//GEN-LAST:event_btnDelAnn1ActionPerformed

    private void btnDelAnn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelAnn2ActionPerformed
        // TODO add your handling code here:
        delete(a2, "anniversary");
    }//GEN-LAST:event_btnDelAnn2ActionPerformed

    private void btnDelBdy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelBdy1ActionPerformed
        // TODO add your handling code here:
        delete(b1, "birthday");
    }//GEN-LAST:event_btnDelBdy1ActionPerformed

    private void btnDelBdy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelBdy2ActionPerformed
        // TODO add your handling code here
        delete(b2, "birthday");
    }//GEN-LAST:event_btnDelBdy2ActionPerformed

    private void btnDelBdy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelBdy3ActionPerformed
        // TODO add your handling code here:
        delete(b3, "birthday");
    }//GEN-LAST:event_btnDelBdy3ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Home hm=new Home();
        hm.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnEditNot1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNot1ActionPerformed
        // TODO add your handling code here:
        editNote(n1);
    }//GEN-LAST:event_btnEditNot1ActionPerformed

    private void btnEditNot2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNot2ActionPerformed
        // TODO add your handling code here:
        editNote(n2);
    }//GEN-LAST:event_btnEditNot2ActionPerformed

    private void btnEditNot3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNot3ActionPerformed
        // TODO add your handling code here:
        editNote(n3);
    }//GEN-LAST:event_btnEditNot3ActionPerformed

    private void cmbDateAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDateAnnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDateAnnActionPerformed
    private void delete(JTextArea z,String del){
        try {
            disconnect();
            int index = z.getText().indexOf("\n");
            idEdit = z.getText().substring(0, index);
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if(del.equals("note"))
            stmt.executeUpdate("delete from APP.NOTES where id= '" + idEdit + "'");
            else if(del.equals("meeting"))
            stmt.executeUpdate("delete from APP.MEETING where id= '" + idEdit + "'");
            else if(del.equals("anniversary"))
            stmt.executeUpdate("delete from APP.ANNIVERSARY where id= '" + idEdit + "'");
            else if(del.equals("birthday"))
            stmt.executeUpdate("delete from APP.BIRTHDAY where id= '" + idEdit + "'");
            else if(del.equals("reminder"))
            stmt.executeUpdate("delete from APP.REMINDER where id= '" + idEdit + "'");
            stmt.close();
            createConn();
            if(del.equals("note"))
            setView("select * from APP.NOTES where username='"+user+"'",rsNot,stmtNot);
            else if(del.equals("meeting"))
            setView("select * from APP.MEETING where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsMet,stmtMet);
            else if(del.equals("anniversary"))
            setView("select * from APP.ANNIVERSARY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsAnn,stmtAnn);
            else if(del.equals("birthday"))
            setView("select * from APP.BIRTHDAY where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsBdy,stmtBdy);
            else if(del.equals("reminder"))
            setView("select * from APP.REMINDER where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsRem,stmtRem);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void txtYearAnnAActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    void actAlarm(JComboBox yn,JComboBox cmbDate,JComboBox cmbMonth,JTextField txtYear,JComboBox cmbHour,JComboBox cmbMin,JComboBox cmbAP)
    {

        if(yn.getSelectedIndex()==0){
                cmbDate.setEnabled(true);
                cmbMonth.setEnabled(true);
                txtYear.setEnabled(true);
                cmbHour.setEnabled(true);
                cmbMin.setEnabled(true);
                cmbAP.setEnabled(true);

            }
            else{
                 cmbDate.setEnabled(false);
                cmbMonth.setEnabled(false);
                txtYear.setEnabled(false);
                cmbHour.setEnabled(false);
                cmbMin.setEnabled(false);
                cmbAP.setEnabled(false);
            }

    }
    private void btnSaveMetActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            // TODO add your handling code here:
            flag=0;
            disconnect();
            String TimeFrom="",TimeTo="",id="";
            int HourFrom,HourTo,MinFrom,MinTo;
            String nameMet=txtNameMet.getText();
            date=cmbDateMet.getSelectedItem().toString();
            month=cmbMonthMet.getSelectedItem().toString();
            flag=monthCheck(cmbDateMet,cmbMonthMet,txtYearMet);
            year=txtYearMet.getText();
            String note=txtSubject.getText();
            String dateS = year.concat("-").concat(month).concat("-").concat(date);
            dateIn=cmbDateMet.getSelectedIndex();
            monthIn=cmbMonthMet.getSelectedIndex();
            yearIn=Integer.parseInt(year);
            String alarmMet=cmbAlarmMet.getSelectedItem().toString();
            String HourFromS = cmbHourFrom.getSelectedItem().toString();
            String MinFromS = cmbMinFrom.getSelectedItem().toString();
            String HourToS = cmbHourTo.getSelectedItem().toString();
            String MinToS = cmbMinTo.getSelectedItem().toString();
            if(cmbHourFrom.getSelectedItem().toString().equals("hh"))
                flag=1;
                else HourFrom=Integer.parseInt(HourFromS);
                if(cmbMinFrom.getSelectedItem().toString().equals("mm"))
                flag=1;
                else MinFrom=Integer.parseInt(MinFromS);
            if(cmbHourTo.getSelectedItem().toString().equals("hh"))
                flag=1;
                else HourTo=Integer.parseInt(HourToS);
                if(cmbMinTo.getSelectedItem().toString().equals("mm"))
                flag=1;
                else MinTo=Integer.parseInt(MinToS);
                TimeFrom=setTime(cmbHourFrom,cmbMinFrom,cmbAPFrom);
                TimeTo=setTime(cmbHourTo,cmbMinTo,cmbAPTo);
            if(cmbAPFrom.getSelectedIndex()==1&&cmbAPTo.getSelectedIndex()==0)flag=1;
            else if(cmbAPFrom.getSelectedIndex()==cmbAPTo.getSelectedIndex()&&cmbHourTo.getSelectedIndex()<cmbHourFrom.getSelectedIndex())flag=1;
            else if(cmbAPFrom.getSelectedIndex()==cmbAPTo.getSelectedIndex()&&cmbHourTo.getSelectedIndex()==cmbHourFrom.getSelectedIndex()&&cmbMinTo.getSelectedIndex()<=cmbMinFrom.getSelectedIndex())flag=1;
            if(cmbAlarmMet.getSelectedIndex()==0){
            flag=setAlarm(cmbAlarmMet,cmbDateMetA,cmbMonthMetA,txtYearMetA,cmbHourMet,cmbMinMet,flag);
            AlmDate=almYear.concat("-").concat(almMonth).concat("-").concat(almDate);
            AlmTime=setTime(cmbHourMet, cmbMinMet, cmbAPMet);
            }
            if(cmbAlarmMet.getSelectedIndex()==0){
            setSaveAlarm(alarmYearInt,alarmMonthInt,alarmDateInt,alarmHourInt,alarmMinInt);
            flag=monthCheck(cmbDateMetA,cmbMonthMetA,txtYearMetA);
            if(cmbDateMetA.getSelectedItem().toString().equals("dd")||cmbMonthMetA.getSelectedItem().toString().equals("mm")||txtYearMetA.getText().equals("")||cmbHourMet.getSelectedItem().toString().equals("hh")||cmbMinMet.getSelectedItem().toString().equals("mm"))
            {
             flag=1;
            }
            }

            if(flag==0){
            if(edit==0)
            id=getId("select * from APP.MEETING","M");
            else
                id=idEdit;
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement();
            if(cmbAlarmMet.getSelectedIndex()==0)
            stmt.executeUpdate("insert into APP.MEETING values ('"+id+"','"+user+"','" + nameMet + "','" + dateS + "','"+TimeFrom+"','"+TimeTo+"','" +alarmMet+"','"+saveAlarmDate+"','"+saveAlarmTime+"','"+note+"')");
            else
                stmt.executeUpdate("INSERT INTO APP.MEETING (ID, USERNAME, NAME, DATE, TIMEFROM, TIMETO, ALARM, NOTES) VALUES ('"+id+"', '"+user+"', '"+nameMet+"', '"+dateS+"', '"+TimeFrom+"','"+TimeTo+"','"+alarmMet+"', '"+note+"')");
            edit=0;
            JOptionPane.showMessageDialog(this, "Details have been saved");
            //}
            stmt.close();}
            else if(flag==1)JOptionPane.showMessageDialog(this, "Invalid data or empty fields!!!");
            createConn();
            setView("select * from APP.MEETING where date between'"+selDateF+"' and '"+selDateT+"'and username='"+user+"'",rsMet,stmtMet);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    String setTime(JComboBox a,JComboBox b,JComboBox c){
        String s1="00",s2,d;
        s2=b.getSelectedItem().toString();
        //System.out.println(c.getSelectedIndex()+"hhhhh");
        if(c.getSelectedIndex()==0){
            switch(a.getSelectedIndex()){
                case 1:s1="01";break;
                case 2:s1="02";break;
                case 3:s1="03";break;
                case 4:s1="04";break;
                case 5:s1="05";break;
                case 6:s1="06";break;
                case 7:s1="07";break;
                case 8:s1="08";break;
                case 9:s1="09";break;
                case 10:s1="10";break;
                case 11:s1="11";break;
                case 12:s1="00";break;
                default:flag=1;break;
            }
        }
        else if(c.getSelectedIndex()==1){
            switch(a.getSelectedIndex()){
                case 1:s1="13";break;
                case 2:s1="14";break;
                case 3:s1="15";break;
                case 4:s1="16";break;
                case 5:s1="17";break;
                case 6:s1="18";break;
                case 7:s1="19";break;
                case 8:s1="20";break;
                case 9:s1="21";break;
                case 10:s1="22";break;
                case 11:s1="23";break;
                case 12:s1="12";break;
                default:flag=1;break;
            }
        }
        //System.out.println(s1+"qqqqqqq");
        if(s1.equals("hh")||s2.equals("mm")){
            flag=1;
            d="00:00:00";
        }
        else
        d=s1.concat(":".concat(s2.concat(":00")));
        //lblLogout.setText(d);
        return d;
    }
    String findText(String qr,ResultSet sa){
        String dor="",ss="",fs,nam="",dat="",mon="",yer="",nt="";
        try {
            nam = sa.getString(3);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!qr.contains("note")){
        try {
            dor = sa.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
        dat=dor.substring(8, 10);
        if(dor.substring(5, 7).equals("01"))mon="january";
        else if(dor.substring(5, 7).equals("02"))mon="february";
        else if(dor.substring(5, 7).equals("03"))mon="march";
        else if(dor.substring(5, 7).equals("04"))mon="april";
        else if(dor.substring(5, 7).equals("05"))mon="may";
        else if(dor.substring(5, 7).equals("06"))mon="june";
        else if(dor.substring(5, 7).equals("07"))mon="july";
        else if(dor.substring(5, 7).equals("08"))mon="august";
        else if(dor.substring(5, 7).equals("09"))mon="september";
        else if(dor.substring(5, 7).equals("10"))mon="october";
        else if(dor.substring(5, 7).equals("11"))mon="november";
        else if(dor.substring(5, 7).equals("12"))mon="december";
        yer=dor.substring(0, 4);
        }
        if(qr.contains("annivers")){
            ss="anniversary of ";
        }
        else if(qr.contains("meet")){
            ss="meeting with ";
        }
        else if(qr.contains("remind")){
            ss="reminder \n";
        }
        else if(qr.contains("note")){
            ss="note \n";
        }
        else if(qr.contains("birth")){
            ss="birthday of ";
        }
        try {
            if(qr.contains("meeting"))
            nt = sa.getString(10);
            else if(qr.contains("note"))
                nt=sa.getString(4);
            else nt=sa.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(qr.contains("note"))
            fs=ss.concat(nam.concat(" ".concat(nt)));
        else
        fs=ss.concat(nam.concat(" on ".concat(dat).concat("th ".concat(mon).concat(" ").concat(yer).concat("\n\n\n").concat(nt))));
        return fs;
    }
    void printData(ResultSet rs,JTextArea l1,JTextArea l2,JTextArea l3,JButton a,JButton b,String qr,JButton jb1,JButton jb2, JButton jb3,JButton jb4,JButton jb5,JButton jb6){
        try {
            String q=qr,speakText="",sf="";//int index;
            Speech s=new Speech();
            if(!rs.previous()){
                a.setEnabled(false);
            }
            if (rs.next()) {
                l1.setVisible(true);
                l1.setEnabled(true);
                jb1.setEnabled(true);
                jb4.setEnabled(true);
                if(q.contains("meeting"))l1.setText(rs.getString(1) + "\nWith " + rs.getString(3)+ " on "+rs.getString(4)+" from "+rs.getString(5)+" to"+rs.getString(6) +"\n"+ rs.getString(10));
                else if(q.contains("note"))l1.setText(rs.getString(1) + "\n" + rs.getString(3) + "\n" + rs.getString(4));
                else l1.setText(rs.getString(1) + "\n" + rs.getString(3)+ "\n" +rs.getString(4)+"\n"+ rs.getString(8));
                speakText = findText(q,rs);
                s.speak(speakText);
            } else {
                l1.setText("");
                l1.setVisible(false);
                jb1.setEnabled(false);
                jb4.setEnabled(false);
                b.setEnabled(false);
            }
            if (rs.next()) {
                l2.setVisible(true);
                l2.setEnabled(true);
                jb2.setEnabled(true);
                jb5.setEnabled(true);
                if(q.contains("meeting"))l2.setText(rs.getString(1) + "\nWith" + rs.getString(3)+ " on "+rs.getString(4)+" from "+rs.getString(5)+" to"+rs.getString(6) +"\n"+ rs.getString(10));
                else if(q.contains("note"))l2.setText(rs.getString(1) + "\n" + rs.getString(3) + "\n" + rs.getString(4));
                else l2.setText(rs.getString(1) + "\n" + rs.getString(3)+"\n" +rs.getString(4)+ "\n" + rs.getString(8));
                speakText = findText(q,rs);
                s.speak(speakText);
            } else {
                l2.setText("");
                l2.setVisible(false);
                jb2.setEnabled(false);
                jb5.setEnabled(false);
                b.setEnabled(false);
            }
            if (rs.next()) {
                l3.setVisible(true);
                l3.setEnabled(true);
                jb3.setEnabled(true);
                jb6.setEnabled(true);
                if(q.contains("meeting"))l3.setText(rs.getString(1) + "\nWith" + rs.getString(3)+ " on "+rs.getString(4)+" from "+rs.getString(5)+" to"+rs.getString(6) +"\n"+ rs.getString(10));
                else if(q.contains("note"))l3.setText(rs.getString(1) + "\n" + rs.getString(3) + "\n" + rs.getString(4));
                else l3.setText(rs.getString(1) + "\n" + rs.getString(3)+"\n" +rs.getString(4)+ "\n" + rs.getString(8));
                speakText = findText(q,rs);
                s.speak(speakText);
            } else {
                l3.setText("");
                l3.setVisible(false);
                jb3.setEnabled(false);
                jb6.setEnabled(false);
                b.setEnabled(false);
            }
            if(!rs.next()){
                b.setEnabled(false);
                rs.previous();
            }
            else b.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setView(String query,ResultSet rs,Statement stmt){
        try {
            rs = stmt.executeQuery(query);
            rs.beforeFirst();
            if(query.contains("APP.ANNIVERSARY")){
            rsAnn=rs;
            printData(rs,a1,a2,a3,btnPPAnn,btnNPAnn,"anniversary",btnEditAnn1,btnEditAnn2,btnEditAnn3,btnDelAnn1,btnDelAnn2,btnDelAnn3);
            }
            else if(query.contains("APP.BIRTHDAY")){
            rsBdy=rs;
            printData(rs, b1, b2, b3,btnPPBdy,btnNPBdy,"birthday",btnEditBdy1,btnEditBdy2,btnEditBdy3,btnDelBdy1,btnDelBdy2,btnDelBdy3);
            }
            else if(query.contains("APP.REMINDER")){
            rsRem=rs;
            printData(rs, r1, r2, r3,btnPPRem,btnNPRem,"reminder",btnEditRem1,btnEditRem2,btnEditRem3,btnDelRem1,btnDelRem2,btnDelRem3);
            }
            else if(query.contains("APP.MEETING")){
            rsMet=rs;
            printData(rs, m1, m2, m3,btnPPMet,btnNPMet,"meeting",btnEditMet1,btnEditMet2,btnEditMet3,btnDelMet1,btnDelMet2,btnDelMet3);
            }
            else if(query.contains("APP.NOTES")){
            rsNot=rs;
            printData(rs,n1,n2,n3,btnPPNot,btnNPNot,"note",btnEditNot1,btnEditNot2,btnEditNot3,btnDelNot1,btnDelNot2,btnDelNot3);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Check date and month!!!");
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            private String username;
            private int a,b,dateIntT,monthIntT,yearIntT,dateIntF,monthIntF,yearIntF;
            public void run() {
                new Manual(username,dateIntF,monthIntF,yearIntF,dateIntT,monthIntT,yearIntT,a,b).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea a1;
    private javax.swing.JTextArea a2;
    private javax.swing.JTextArea a3;
    private javax.swing.JPanel anniversary;
    private javax.swing.JTextArea b1;
    private javax.swing.JTextArea b2;
    private javax.swing.JTextArea b3;
    private javax.swing.JPanel birthday;
    private javax.swing.JButton btnCancelAnn;
    private javax.swing.JButton btnCancelBdy;
    private javax.swing.JButton btnCancelMet;
    private javax.swing.JButton btnCancelNote;
    private javax.swing.JButton btnCancelRem;
    private javax.swing.JButton btnClearBdy;
    private javax.swing.JButton btnClearMet;
    private javax.swing.JButton btnClearNote;
    private javax.swing.JButton btnClearRem;
    private javax.swing.JButton btnClrAnn;
    private javax.swing.JButton btnDelAnn1;
    private javax.swing.JButton btnDelAnn2;
    private javax.swing.JButton btnDelAnn3;
    private javax.swing.JButton btnDelBdy1;
    private javax.swing.JButton btnDelBdy2;
    private javax.swing.JButton btnDelBdy3;
    private javax.swing.JButton btnDelMet1;
    private javax.swing.JButton btnDelMet2;
    private javax.swing.JButton btnDelMet3;
    private javax.swing.JButton btnDelNot1;
    private javax.swing.JButton btnDelNot2;
    private javax.swing.JButton btnDelNot3;
    private javax.swing.JButton btnDelRem1;
    private javax.swing.JButton btnDelRem2;
    private javax.swing.JButton btnDelRem3;
    private javax.swing.JButton btnEditAnn1;
    private javax.swing.JButton btnEditAnn2;
    private javax.swing.JButton btnEditAnn3;
    private javax.swing.JButton btnEditBdy1;
    private javax.swing.JButton btnEditBdy2;
    private javax.swing.JButton btnEditBdy3;
    private javax.swing.JButton btnEditMet1;
    private javax.swing.JButton btnEditMet2;
    private javax.swing.JButton btnEditMet3;
    private javax.swing.JButton btnEditNot1;
    private javax.swing.JButton btnEditNot2;
    private javax.swing.JButton btnEditNot3;
    private javax.swing.JButton btnEditRem1;
    private javax.swing.JButton btnEditRem2;
    private javax.swing.JButton btnEditRem3;
    private javax.swing.JButton btnGoVAF;
    private javax.swing.JButton btnGoVBF;
    private javax.swing.JButton btnGoVM;
    private javax.swing.JButton btnGoVR;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNPAnn;
    private javax.swing.JButton btnNPBdy;
    private javax.swing.JButton btnNPMet;
    private javax.swing.JButton btnNPNot;
    private javax.swing.JButton btnNPRem;
    private javax.swing.JButton btnPPAnn;
    private javax.swing.JButton btnPPBdy;
    private javax.swing.JButton btnPPMet;
    private javax.swing.JButton btnPPNot;
    private javax.swing.JButton btnPPRem;
    private javax.swing.JButton btnSaveAnn;
    private javax.swing.JButton btnSaveBdy;
    private javax.swing.JButton btnSaveMet;
    private javax.swing.JButton btnSaveNote;
    private javax.swing.JButton btnSaveRem;
    private javax.swing.JButton btnTextAnn;
    private javax.swing.JButton btnTextBdy;
    private javax.swing.JButton btnTextMet;
    private javax.swing.JButton btnTextNot;
    private javax.swing.JButton btnTextRem;
    private javax.swing.JComboBox cmbAPAnn;
    private javax.swing.JComboBox cmbAPBdy;
    private javax.swing.JComboBox cmbAPFrom;
    private javax.swing.JComboBox cmbAPMet;
    private javax.swing.JComboBox cmbAPRem;
    private javax.swing.JComboBox cmbAPTo;
    private javax.swing.JComboBox cmbAlarmAnn;
    private javax.swing.JComboBox cmbAlarmBdy;
    private javax.swing.JComboBox cmbAlarmMet;
    private javax.swing.JComboBox cmbAlarmRem;
    private javax.swing.JComboBox cmbDateAnn;
    private javax.swing.JComboBox cmbDateAnnA;
    private javax.swing.JComboBox cmbDateBdy;
    private javax.swing.JComboBox cmbDateBdyA;
    private javax.swing.JComboBox cmbDateMet;
    private javax.swing.JComboBox cmbDateMetA;
    private javax.swing.JComboBox cmbDateRem;
    private javax.swing.JComboBox cmbDateRemA;
    private javax.swing.JComboBox cmbDateVAF;
    private javax.swing.JComboBox cmbDateVAT;
    private javax.swing.JComboBox cmbDateVBF;
    private javax.swing.JComboBox cmbDateVBT;
    private javax.swing.JComboBox cmbDateVMF;
    private javax.swing.JComboBox cmbDateVMT;
    private javax.swing.JComboBox cmbDateVRF;
    private javax.swing.JComboBox cmbDateVRT;
    private javax.swing.JComboBox cmbHourAnn;
    private javax.swing.JComboBox cmbHourBdy;
    private javax.swing.JComboBox cmbHourFrom;
    private javax.swing.JComboBox cmbHourMet;
    private javax.swing.JComboBox cmbHourRem;
    private javax.swing.JComboBox cmbHourTo;
    private javax.swing.JComboBox cmbMinAnn;
    private javax.swing.JComboBox cmbMinBdy;
    private javax.swing.JComboBox cmbMinFrom;
    private javax.swing.JComboBox cmbMinMet;
    private javax.swing.JComboBox cmbMinRem;
    private javax.swing.JComboBox cmbMinTo;
    private javax.swing.JComboBox cmbMonthAnn;
    private javax.swing.JComboBox cmbMonthAnnA;
    private javax.swing.JComboBox cmbMonthBdy;
    private javax.swing.JComboBox cmbMonthBdyA;
    private javax.swing.JComboBox cmbMonthMet;
    private javax.swing.JComboBox cmbMonthMetA;
    private javax.swing.JComboBox cmbMonthRem;
    private javax.swing.JComboBox cmbMonthRemA;
    private javax.swing.JComboBox cmbMonthVAF;
    private javax.swing.JComboBox cmbMonthVAT;
    private javax.swing.JComboBox cmbMonthVBF;
    private javax.swing.JComboBox cmbMonthVBT;
    private javax.swing.JComboBox cmbMonthVMF;
    private javax.swing.JComboBox cmbMonthVMT;
    private javax.swing.JComboBox cmbMonthVRF;
    private javax.swing.JComboBox cmbMonthVRT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblDate3;
    private javax.swing.JLabel lblDate4;
    private javax.swing.JLabel lblDate5;
    private javax.swing.JLabel lblDate6;
    private javax.swing.JLabel lblDate7;
    private javax.swing.JLabel lblDate8;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextArea m1;
    private javax.swing.JTextArea m2;
    private javax.swing.JTextArea m3;
    private javax.swing.JPanel meeting;
    private javax.swing.JTextArea n1;
    private javax.swing.JTextArea n2;
    private javax.swing.JTextArea n3;
    private javax.swing.JPanel notes;
    private javax.swing.JScrollPane ns1;
    private javax.swing.JScrollPane ns2;
    private javax.swing.JScrollPane ns3;
    private javax.swing.JTextArea r1;
    private javax.swing.JTextArea r2;
    private javax.swing.JTextArea r3;
    private javax.swing.JPanel reminder;
    private javax.swing.JTabbedPane tab1;
    private javax.swing.JTabbedPane tab2;
    private javax.swing.JTextField txtNameAnn;
    private javax.swing.JTextField txtNameBdy;
    private javax.swing.JTextField txtNameMet;
    private javax.swing.JTextArea txtNoteAnn;
    private javax.swing.JTextArea txtNoteBdy;
    private javax.swing.JTextArea txtNoteNote;
    private javax.swing.JTextArea txtNoteRem;
    private javax.swing.JTextArea txtSubject;
    private javax.swing.JTextField txtTitleNote;
    private javax.swing.JTextField txtTitleRem;
    private javax.swing.JTextField txtYearAnn;
    private javax.swing.JTextField txtYearAnnA;
    private javax.swing.JTextField txtYearBdy;
    private javax.swing.JTextField txtYearBdyA;
    private javax.swing.JTextField txtYearMet;
    private javax.swing.JTextField txtYearMetA;
    private javax.swing.JTextField txtYearRem;
    private javax.swing.JTextField txtYearRemA;
    private javax.swing.JTextField txtYearVAF;
    private javax.swing.JTextField txtYearVAT;
    private javax.swing.JTextField txtYearVBF;
    private javax.swing.JTextField txtYearVBT;
    private javax.swing.JTextField txtYearVMF;
    private javax.swing.JTextField txtYearVMT;
    private javax.swing.JTextField txtYearVRF;
    private javax.swing.JTextField txtYearVRT;
    private javax.swing.JPanel vAnniversary;
    private javax.swing.JPanel vBirthday;
    private javax.swing.JPanel vMeeting;
    private javax.swing.JPanel vNotes;
    private javax.swing.JPanel vReminder;
    private javax.swing.JPanel view;
    // End of variables declaration//GEN-END:variables
    String user,date,month,year,CurrentTime,selDateF,selDateT,idEdit,va;
    Statement stmtAnn,stmtBdy,stmtRem,stmtMet,stmtNot;
    ResultSet rsAnn,rsBdy,rsRem,rsNot,rsMet;
    int dateIntF,monthIntF,yearIntF,dateIntT,monthIntT,yearIntT,flag=0,dateIn,monthIn,yearIn,edit=0;
    DateFormat yearf=new SimpleDateFormat("yyyy");
        DateFormat monthf=new SimpleDateFormat("MM");
        DateFormat datef=new SimpleDateFormat("dd");
        DateFormat hourf=new SimpleDateFormat("hh");
        DateFormat minutef=new SimpleDateFormat("mm");
            String almDate,almMonth,almYear,almHr,almMin;
            int alarmDateInt=0,alarmMonthInt=0,alarmYearInt=0,alarmHourInt=0,alarmMinInt=0;
        Date datess=new Date();
        String Year=yearf.format(datess);
        String Month=monthf.format(datess);
        String Date=datef.format(datess);
        //String Hour=hourf.format(datess);
        String Minute=minutef.format(datess);
        int currentDate=Integer.parseInt(Date);
        int currentMonth=Integer.parseInt(Month);
        int currentYear=Integer.parseInt(Year);
        //int currentHour=Integer.parseInt(Hour);
        int currentMin=Integer.parseInt(Minute);
        String currentTime;
        String saveAlarmDate="";
        String saveAlarmTime="";
        String AlmDate="";
        String AlmTime="";
        String CurrentDate=Year.concat("-").concat(Month).concat("-").concat(Date);
        Calendar now = Calendar.getInstance();
        int currentHour=now.get(Calendar.HOUR_OF_DAY);

void setSaveAlarm(int y,int m,int d,int h,int mi){
        if(y>currentYear)saveAlarm();
            else if(y==currentYear&&m>currentMonth)saveAlarm();
            else if(y==currentYear&&m==currentMonth&&d>currentDate)saveAlarm();
            else if(y==currentYear&&m==currentMonth&&d==currentDate&&h>currentHour)saveAlarm();
            else if(y==currentYear&&m==currentMonth&&d==currentDate&&h==currentHour&&mi>currentMin)saveAlarm();
            else flag=1;

            if(yearIn>y)saveAlarm();
            else if(yearIn==alarmYearInt&&monthIn>m)saveAlarm();
            else if(yearIn==alarmYearInt&&monthIn==m&&dateIn>=d)saveAlarm();
            else flag=1;
}

        /* void ap()
         {
             //System.out.println(h);
             if(currentHour>=12){
                currentHour-=12;
             CurrentTime=Integer.toString(currentHour).concat(":".concat(Minute.concat(":00")));}
             else if(currentHour==0){
                 currentHour=12;
                 
                CurrentTime=Integer.toString(currentHour).concat(":".concat(Minute.concat(":00")));}
             System.out.println();
         }*/

         void saveAlarm()
         {
              //JOptionPane.showMessageDialog(this, "Alarm has been set");
              saveAlarmDate=AlmDate;
              saveAlarmTime=AlmTime;
         }
    String getId(String query,String cod){
        String id,code=cod;
        try {

            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);
            int[] arrayId=new int[1000];
            rs.beforeFirst();
            if(rs.next()){
                id="";
            rs.beforeFirst();
            int i=0;
            while(rs.next())
            {
                arrayId[i]=Integer.parseInt(rs.getString(1).substring(1));
                i++;
            }
            int largest=arrayId[0];
            for(int j=1;j<arrayId.length;j++)
            {
                if(arrayId[j]>largest)largest=arrayId[j];
            }
            largest++;
            code=code.concat(String.valueOf(largest));
            }
            else
            {
                code=cod.concat("1");
            }
            stmt.close();
            return code;
        } catch (SQLException ex) {
            Logger.getLogger(Manual.class.getName()).log(Level.SEVERE, null, ex);
        }return code;
    }
}
