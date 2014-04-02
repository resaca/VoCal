/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StoreRetrieve.java
 *
 * Created on Apr 4, 2011, 11:54:46 AM
 */

/**
 *
 * @author Rahana,Anu,Sebin,Pratheesh
 */
 

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;

public class StoreRetrieve extends javax.swing.JFrame {

    Home Hom = new Home();
    public StoreRetrieve(String username) {
        try {
            initComponents();
            user = username;
            Hom.year = new SimpleDateFormat("yyyy");
            Hom.month = new SimpleDateFormat("MM");
            Hom.date = new SimpleDateFormat("dd");
            Hom.hour = new SimpleDateFormat("hh");
            Hom.minute = new SimpleDateFormat("mm");
            Hom.sec = new SimpleDateFormat("ss");
            Hom.dates = new Date();
            Hom.Year = Hom.year.format(Hom.dates);
            Hom.Month = Hom.month.format(Hom.dates);
            Hom.Date = Hom.date.format(Hom.dates);
            Hom.Hour = Hom.hour.format(Hom.dates);
            Hom.Minute = Hom.minute.format(Hom.dates);
            Hom.Sec = Hom.sec.format(Hom.dates);
            Hom.dateInt = Integer.parseInt(Hom.Date);
            Hom.monthInt = Integer.parseInt(Hom.Month);
            Hom.currentMonth = Hom.monthInt;
            Hom.yearInt = Integer.parseInt(Hom.Year);
            Hom.currentYear = Hom.yearInt;
            Hom.offset = 0;
            Hom.leap = 0;
            Hom.i = 0;
            Hom.j = 0;
            Hom.k = 0;
            cmbDate.setSelectedIndex(Hom.dateInt);
            cmbMonth.setSelectedIndex(Hom.monthInt);
            txtYear.setText(Hom.Year);
            String sd=cmbMonth.getSelectedItem().toString().concat("/".concat(cmbDate.getSelectedItem().toString()).concat("/".concat(txtYear.getText())));
            Hom.datesss = new String[42];
            setMnY();
            Hom.setCal(Hom.monthInt, Hom.yearInt, Hom.findC(Hom.monthInt, Hom.yearInt));
            setM();
            dateSelect(Hom.dateInt, Color.BLUE);
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            Statement stmtAnn = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement stmtMet = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement stmtRem = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement stmtBdy = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Speech sp=new Speech();
            ResultSet rsAnn=stmtAnn.executeQuery("select * from APP.ANNIVERSARY where alarmdate='"+sd+"' and username='"+user+"'");
            ResultSet rsMet=stmtMet.executeQuery("select * from APP.MEETING where alarmdate='"+sd+"' and username='"+user+"'");
            ResultSet rsBdy=stmtBdy.executeQuery("select * from APP.BIRTHDAY where alarmdate='"+sd+"' and username='"+user+"'");
            ResultSet rsRem=stmtRem.executeQuery("select * from APP.REMINDER where alarmdate='"+sd+"' and username='"+user+"'");
            rsAnn.beforeFirst();
            while(rsAnn.next())
            sp.speak(findText("anniversary", rsAnn));
            rsBdy.beforeFirst();
            while(rsBdy.next())
            sp.speak(findText("anniversary", rsBdy));
            rsMet.beforeFirst();
            while(rsMet.next())
            sp.speak(findText("anniversary", rsMet));
            rsRem.beforeFirst();
            while(rsRem.next())
            sp.speak(findText("anniversary", rsRem));
            stmtAnn.close();
            stmtRem.close();
            stmtMet.close();
            stmtBdy.close();
            //System.out.println(user);
            //PopupMenu popup=new PopupMenu();
            //m1.add(popup);
        } catch (SQLException ex) {
            Logger.getLogger(StoreRetrieve.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(user);
        //PopupMenu popup=new PopupMenu();
        //m1.add(popup);

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
    public void setMnY() {
        switch (Hom.monthInt) {
            case 1:
                lblMonth.setText("January");
                break;
            case 2:
                lblMonth.setText("February");
                break;
            case 3:
                lblMonth.setText("March");
                break;
            case 4:
                lblMonth.setText("April");
                break;
            case 5:
                lblMonth.setText("May");
                break;
            case 6:
                lblMonth.setText("June");
                break;
            case 7:
                lblMonth.setText("July");
                break;
            case 8:
                lblMonth.setText("August");
                break;
            case 9:
                lblMonth.setText("September");
                break;
            case 10:
                lblMonth.setText("October");
                break;
            case 11:
                lblMonth.setText("November");
                break;
            case 12:
                lblMonth.setText("December");
                break;

        }
        Hom.Year = Integer.toString(Hom.yearInt);
        lblYear.setText(Hom.Year);
    }

    void setM() {
        //Insets s2=m1.getInsets();
        //lblMonth.setText(Integer.toString(s2.bottom));
        m1.setText(Hom.datesss[0]);
        m2.setText(Hom.datesss[1]);
        m3.setText(Hom.datesss[2]);
        m4.setText(Hom.datesss[3]);
        m5.setText(Hom.datesss[4]);
        m6.setText(Hom.datesss[5]);
        m7.setText(Hom.datesss[6]);
        m8.setText(Hom.datesss[7]);
        m9.setText(Hom.datesss[8]);
        m10.setText(Hom.datesss[9]);
        m11.setText(Hom.datesss[10]);
        m12.setText(Hom.datesss[11]);
        m13.setText(Hom.datesss[12]);
        m14.setText(Hom.datesss[13]);
        m15.setText(Hom.datesss[14]);
        m16.setText(Hom.datesss[15]);
        m17.setText(Hom.datesss[16]);
        m18.setText(Hom.datesss[17]);
        m19.setText(Hom.datesss[18]);
        m20.setText(Hom.datesss[19]);
        m21.setText(Hom.datesss[20]);
        m22.setText(Hom.datesss[21]);
        m23.setText(Hom.datesss[22]);
        m24.setText(Hom.datesss[23]);
        m25.setText(Hom.datesss[24]);
        m26.setText(Hom.datesss[25]);
        m27.setText(Hom.datesss[26]);
        m28.setText(Hom.datesss[27]);
        m29.setText(Hom.datesss[28]);
        m30.setText(Hom.datesss[29]);
        m31.setText(Hom.datesss[30]);
        m32.setText(Hom.datesss[31]);
        m33.setText(Hom.datesss[32]);
        m34.setText(Hom.datesss[33]);
        m35.setText(Hom.datesss[34]);
        m36.setText(Hom.datesss[35]);
        m37.setText(Hom.datesss[36]);
        m38.setText(Hom.datesss[37]);
        m39.setText(Hom.datesss[38]);
        m40.setText(Hom.datesss[39]);
        m41.setText(Hom.datesss[40]);
        m42.setText(Hom.datesss[41]);

    }
    void dateSelect(int H,Color clr)
    {
        java.awt.Font f=new java.awt.Font("Ubuntu", 0, 9);
        if(!m1.getText().equals("")&&Integer.parseInt(m1.getText())==H)m1.setForeground(clr);
        else if(!m2.getText().equals("")&&Integer.parseInt(m2.getText())==H)m2.setForeground(clr);
        else if(!m3.getText().equals("")&&Integer.parseInt(m3.getText())==H)m3.setForeground(clr);
        else if(!m4.getText().equals("")&&Integer.parseInt(m4.getText())==H)m4.setForeground(clr);
        else if(!m5.getText().equals("")&&Integer.parseInt(m5.getText())==H)m5.setForeground(clr);
        else if(!m6.getText().equals("")&&Integer.parseInt(m6.getText())==H)m6.setForeground(clr);
        else if(!m7.getText().equals("")&&Integer.parseInt(m7.getText())==H)m7.setForeground(clr);
        else if(!m8.getText().equals("")&&Integer.parseInt(m8.getText())==H)m8.setForeground(clr);
        else if(!m9.getText().equals("")&&Integer.parseInt(m9.getText())==H)m9.setForeground(clr);
        else if(!m10.getText().equals("")&&Integer.parseInt(m10.getText())==H)m10.setForeground(clr);
        else if(!m11.getText().equals("")&&Integer.parseInt(m11.getText())==H)m11.setForeground(clr);
        else if(!m12.getText().equals("")&&Integer.parseInt(m12.getText())==H)m12.setForeground(clr);
        else if(!m13.getText().equals("")&&Integer.parseInt(m13.getText())==H)m13.setForeground(clr);
        else if(!m14.getText().equals("")&&Integer.parseInt(m14.getText())==H)m14.setForeground(clr);
        else if(!m15.getText().equals("")&&Integer.parseInt(m15.getText())==H)m15.setForeground(clr);
        else if(!m16.getText().equals("")&&Integer.parseInt(m16.getText())==H)m16.setForeground(clr);
        else if(!m17.getText().equals("")&&Integer.parseInt(m17.getText())==H)m17.setForeground(clr);
        else if(!m18.getText().equals("")&&Integer.parseInt(m18.getText())==H)m18.setForeground(clr);
        else if(!m19.getText().equals("")&&Integer.parseInt(m19.getText())==H)m19.setForeground(clr);
        else if(!m20.getText().equals("")&&Integer.parseInt(m20.getText())==H)m20.setForeground(clr);
        else if(!m21.getText().equals("")&&Integer.parseInt(m21.getText())==H)m21.setForeground(clr);
        else if(!m22.getText().equals("")&&Integer.parseInt(m22.getText())==H)m22.setForeground(clr);
        else if(!m23.getText().equals("")&&Integer.parseInt(m23.getText())==H)m23.setForeground(clr);
        else if(!m24.getText().equals("")&&Integer.parseInt(m24.getText())==H)m24.setForeground(clr);
        else if(!m25.getText().equals("")&&Integer.parseInt(m25.getText())==H)m25.setForeground(clr);
        else if(!m26.getText().equals("")&&Integer.parseInt(m26.getText())==H)m26.setForeground(clr);
        else if(!m27.getText().equals("")&&Integer.parseInt(m27.getText())==H)m27.setForeground(clr);
        else if(!m28.getText().equals("")&&Integer.parseInt(m28.getText())==H)m28.setForeground(clr);
        else if(!m29.getText().equals("")&&Integer.parseInt(m29.getText())==H)m29.setForeground(clr);
        else if(!m30.getText().equals("")&&Integer.parseInt(m30.getText())==H)m30.setForeground(clr);
        else if(!m31.getText().equals("")&&Integer.parseInt(m31.getText())==H)m31.setForeground(clr);
        else if(!m32.getText().equals("")&&Integer.parseInt(m32.getText())==H)m32.setForeground(clr);
        else if(!m33.getText().equals("")&&Integer.parseInt(m33.getText())==H)m33.setForeground(clr);
        else if(!m34.getText().equals("")&&Integer.parseInt(m34.getText())==H)m34.setForeground(clr);
        else if(!m35.getText().equals("")&&Integer.parseInt(m35.getText())==H)m35.setForeground(clr);
        else if(!m36.getText().equals("")&&Integer.parseInt(m36.getText())==H)m36.setForeground(clr);
        else if(!m37.getText().equals("")&&Integer.parseInt(m37.getText())==H)m37.setForeground(clr);
        else if(!m38.getText().equals("")&&Integer.parseInt(m38.getText())==H)m38.setForeground(clr);
        else if(!m39.getText().equals("")&&Integer.parseInt(m39.getText())==H)m39.setForeground(clr);
        else if(!m40.getText().equals("")&&Integer.parseInt(m40.getText())==H)m40.setForeground(clr);
        else if(!m41.getText().equals("")&&Integer.parseInt(m41.getText())==H)m41.setForeground(clr);
        else if(!m42.getText().equals("")&&Integer.parseInt(m42.getText())==H)m42.setForeground(clr);
    }
    void dateSetting()
    {
        Hom.Year = txtYear.getText();
        Hom.yearInt = Integer.parseInt(Hom.Year);
        setMnY();
        int q=Hom.findC(Hom.monthInt, Hom.yearInt);
        if(((Hom.yearInt%4==0&&Hom.yearInt%100!=0)||Hom.yearInt%400==0)&&Hom.monthInt<=2){
            q=q-1;
            q=q+7;
            q=q%7;
        }
        Hom.setCal(Hom.monthInt, Hom.yearInt,q);
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogout = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        cmbDate = new javax.swing.JComboBox();
        cmbMonth = new javax.swing.JComboBox();
        txtYear = new javax.swing.JTextField();
        btnGo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnPreviousMonth = new javax.swing.JButton();
        lblMonth = new javax.swing.JLabel();
        btnNextMonth = new javax.swing.JButton();
        btnPreviousYear = new javax.swing.JButton();
        lblYear = new javax.swing.JLabel();
        btnNextYear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        m15 = new javax.swing.JButton();
        m16 = new javax.swing.JButton();
        m17 = new javax.swing.JButton();
        m18 = new javax.swing.JButton();
        m19 = new javax.swing.JButton();
        m20 = new javax.swing.JButton();
        m21 = new javax.swing.JButton();
        m8 = new javax.swing.JButton();
        m9 = new javax.swing.JButton();
        m10 = new javax.swing.JButton();
        m11 = new javax.swing.JButton();
        m12 = new javax.swing.JButton();
        m13 = new javax.swing.JButton();
        m14 = new javax.swing.JButton();
        m22 = new javax.swing.JButton();
        m23 = new javax.swing.JButton();
        m24 = new javax.swing.JButton();
        m29 = new javax.swing.JButton();
        m30 = new javax.swing.JButton();
        m31 = new javax.swing.JButton();
        m32 = new javax.swing.JButton();
        m33 = new javax.swing.JButton();
        m34 = new javax.swing.JButton();
        m35 = new javax.swing.JButton();
        m25 = new javax.swing.JButton();
        m26 = new javax.swing.JButton();
        m27 = new javax.swing.JButton();
        m28 = new javax.swing.JButton();
        m1 = new javax.swing.JButton();
        m2 = new javax.swing.JButton();
        m3 = new javax.swing.JButton();
        m4 = new javax.swing.JButton();
        m5 = new javax.swing.JButton();
        m6 = new javax.swing.JButton();
        m7 = new javax.swing.JButton();
        m36 = new javax.swing.JButton();
        m37 = new javax.swing.JButton();
        m38 = new javax.swing.JButton();
        m39 = new javax.swing.JButton();
        m40 = new javax.swing.JButton();
        m41 = new javax.swing.JButton();
        m42 = new javax.swing.JButton();
        btnToday = new javax.swing.JButton();
        lblNote = new javax.swing.JLabel();
        btnText = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Store/ Retrieve");
        setBounds(new java.awt.Rectangle(450, 80, 0, 0));

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnLogout.setText("Logout");

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/index.jpg"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        cmbDate.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbMonth.setFont(new java.awt.Font("Tahoma", 0, 14));
        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mm", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        txtYear.setFont(new java.awt.Font("Tahoma", 0, 14));

        btnGo.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnGo.setText("Go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("Go to date");

        btnPreviousMonth.setText("<");
        btnPreviousMonth.setToolTipText("Previous Month");
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });

        lblMonth.setFont(new java.awt.Font("Tahoma", 1, 18));

        btnNextMonth.setText(">");
        btnNextMonth.setToolTipText("Next Month");
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });

        btnPreviousYear.setText("<");
        btnPreviousYear.setToolTipText("Previous Year");
        btnPreviousYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousYearActionPerformed(evt);
            }
        });

        lblYear.setFont(new java.awt.Font("Tahoma", 1, 18));

        btnNextYear.setText(">");
        btnNextYear.setToolTipText("Next Year");
        btnNextYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextYearActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel2.setText("Sun");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel3.setText("Mon");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel4.setText("Tue");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel5.setText("Wed");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel6.setText("Thu");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel7.setText("Fri");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 13));
        jLabel8.setText("Sat");

        m15.setBackground(new java.awt.Color(0, 0, 0));
        m15.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m15.setForeground(new java.awt.Color(1, 1, 1));
        m15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m15ActionPerformed(evt);
            }
        });

        m16.setBackground(new java.awt.Color(0, 0, 0));
        m16.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m16.setForeground(new java.awt.Color(1, 1, 1));
        m16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m16ActionPerformed(evt);
            }
        });

        m17.setBackground(new java.awt.Color(0, 0, 0));
        m17.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m17.setForeground(new java.awt.Color(1, 1, 1));
        m17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m17ActionPerformed(evt);
            }
        });

        m18.setBackground(new java.awt.Color(0, 0, 0));
        m18.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m18.setForeground(new java.awt.Color(1, 1, 1));
        m18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m18ActionPerformed(evt);
            }
        });

        m19.setBackground(new java.awt.Color(0, 0, 0));
        m19.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m19.setForeground(new java.awt.Color(1, 1, 1));
        m19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m19ActionPerformed(evt);
            }
        });

        m20.setBackground(new java.awt.Color(0, 0, 0));
        m20.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m20.setForeground(new java.awt.Color(1, 1, 1));
        m20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m20ActionPerformed(evt);
            }
        });

        m21.setBackground(new java.awt.Color(0, 0, 0));
        m21.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m21.setForeground(new java.awt.Color(1, 1, 1));
        m21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m21ActionPerformed(evt);
            }
        });

        m8.setBackground(new java.awt.Color(0, 0, 0));
        m8.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m8.setForeground(new java.awt.Color(1, 1, 1));
        m8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m8ActionPerformed(evt);
            }
        });

        m9.setBackground(new java.awt.Color(0, 0, 0));
        m9.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m9.setForeground(new java.awt.Color(1, 1, 1));
        m9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m9ActionPerformed(evt);
            }
        });

        m10.setBackground(new java.awt.Color(0, 0, 0));
        m10.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m10.setForeground(new java.awt.Color(1, 1, 1));
        m10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m10ActionPerformed(evt);
            }
        });

        m11.setBackground(new java.awt.Color(0, 0, 0));
        m11.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m11.setForeground(new java.awt.Color(1, 1, 1));
        m11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m11ActionPerformed(evt);
            }
        });

        m12.setBackground(new java.awt.Color(0, 0, 0));
        m12.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m12.setForeground(new java.awt.Color(1, 1, 1));
        m12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m12ActionPerformed(evt);
            }
        });

        m13.setBackground(new java.awt.Color(0, 0, 0));
        m13.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m13.setForeground(new java.awt.Color(1, 1, 1));
        m13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m13ActionPerformed(evt);
            }
        });

        m14.setBackground(new java.awt.Color(0, 0, 0));
        m14.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m14.setForeground(new java.awt.Color(1, 1, 1));
        m14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m14ActionPerformed(evt);
            }
        });

        m22.setBackground(new java.awt.Color(0, 0, 0));
        m22.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m22.setForeground(new java.awt.Color(1, 1, 1));
        m22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m22ActionPerformed(evt);
            }
        });

        m23.setBackground(new java.awt.Color(0, 0, 0));
        m23.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m23.setForeground(new java.awt.Color(1, 1, 1));
        m23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m23ActionPerformed(evt);
            }
        });

        m24.setBackground(new java.awt.Color(0, 0, 0));
        m24.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m24.setForeground(new java.awt.Color(1, 1, 1));
        m24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m24ActionPerformed(evt);
            }
        });

        m29.setBackground(new java.awt.Color(0, 0, 0));
        m29.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m29.setForeground(new java.awt.Color(1, 1, 1));
        m29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m29ActionPerformed(evt);
            }
        });

        m30.setBackground(new java.awt.Color(0, 0, 0));
        m30.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m30.setForeground(new java.awt.Color(1, 1, 1));
        m30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m30ActionPerformed(evt);
            }
        });

        m31.setBackground(new java.awt.Color(0, 0, 0));
        m31.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m31.setForeground(new java.awt.Color(1, 1, 1));
        m31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m31ActionPerformed(evt);
            }
        });

        m32.setBackground(new java.awt.Color(0, 0, 0));
        m32.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m32.setForeground(new java.awt.Color(1, 1, 1));
        m32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m32ActionPerformed(evt);
            }
        });

        m33.setBackground(new java.awt.Color(0, 0, 0));
        m33.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m33.setForeground(new java.awt.Color(1, 1, 1));
        m33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m33ActionPerformed(evt);
            }
        });

        m34.setBackground(new java.awt.Color(0, 0, 0));
        m34.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m34.setForeground(new java.awt.Color(1, 1, 1));
        m34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m34ActionPerformed(evt);
            }
        });

        m35.setBackground(new java.awt.Color(0, 0, 0));
        m35.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m35.setForeground(new java.awt.Color(1, 1, 1));
        m35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m35ActionPerformed(evt);
            }
        });

        m25.setBackground(new java.awt.Color(0, 0, 0));
        m25.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m25.setForeground(new java.awt.Color(1, 1, 1));
        m25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m25ActionPerformed(evt);
            }
        });

        m26.setBackground(new java.awt.Color(0, 0, 0));
        m26.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m26.setForeground(new java.awt.Color(1, 1, 1));
        m26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m26ActionPerformed(evt);
            }
        });

        m27.setBackground(new java.awt.Color(0, 0, 0));
        m27.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m27.setForeground(new java.awt.Color(1, 1, 1));
        m27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m27ActionPerformed(evt);
            }
        });

        m28.setBackground(new java.awt.Color(0, 0, 0));
        m28.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m28.setForeground(new java.awt.Color(1, 1, 1));
        m28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m28ActionPerformed(evt);
            }
        });

        m1.setBackground(new java.awt.Color(0, 0, 0));
        m1.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m1.setForeground(new java.awt.Color(1, 1, 1));
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        m2.setBackground(new java.awt.Color(0, 0, 0));
        m2.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m2.setForeground(new java.awt.Color(1, 1, 1));
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });

        m3.setBackground(new java.awt.Color(0, 0, 0));
        m3.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m3.setForeground(new java.awt.Color(1, 1, 1));
        m3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m3ActionPerformed(evt);
            }
        });

        m4.setBackground(new java.awt.Color(0, 0, 0));
        m4.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m4.setForeground(new java.awt.Color(1, 1, 1));
        m4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m4ActionPerformed(evt);
            }
        });

        m5.setBackground(new java.awt.Color(0, 0, 0));
        m5.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m5.setForeground(new java.awt.Color(1, 1, 1));
        m5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m5ActionPerformed(evt);
            }
        });

        m6.setBackground(new java.awt.Color(0, 0, 0));
        m6.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m6.setForeground(new java.awt.Color(1, 1, 1));
        m6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m6ActionPerformed(evt);
            }
        });

        m7.setBackground(new java.awt.Color(0, 0, 0));
        m7.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m7.setForeground(new java.awt.Color(1, 1, 1));
        m7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m7ActionPerformed(evt);
            }
        });

        m36.setBackground(new java.awt.Color(0, 0, 0));
        m36.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m36.setForeground(new java.awt.Color(1, 1, 1));
        m36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m36ActionPerformed(evt);
            }
        });

        m37.setBackground(new java.awt.Color(0, 0, 0));
        m37.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m37.setForeground(new java.awt.Color(1, 1, 1));
        m37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m37ActionPerformed(evt);
            }
        });

        m38.setBackground(new java.awt.Color(0, 0, 0));
        m38.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m38.setForeground(new java.awt.Color(1, 1, 1));
        m38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m38ActionPerformed(evt);
            }
        });

        m39.setBackground(new java.awt.Color(0, 0, 0));
        m39.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m39.setForeground(new java.awt.Color(1, 1, 1));
        m39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m39ActionPerformed(evt);
            }
        });

        m40.setBackground(new java.awt.Color(0, 0, 0));
        m40.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m40.setForeground(new java.awt.Color(1, 1, 1));
        m40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m40ActionPerformed(evt);
            }
        });

        m41.setBackground(new java.awt.Color(0, 0, 0));
        m41.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m41.setForeground(new java.awt.Color(1, 1, 1));
        m41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m41ActionPerformed(evt);
            }
        });

        m42.setBackground(new java.awt.Color(0, 0, 0));
        m42.setFont(new java.awt.Font("Ubuntu", 0, 9));
        m42.setForeground(new java.awt.Color(1, 1, 1));
        m42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m42ActionPerformed(evt);
            }
        });

        btnToday.setText("Today");
        btnToday.setToolTipText("Click to jump to current day..");
        btnToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodayActionPerformed(evt);
            }
        });

        lblNote.setForeground(new java.awt.Color(255, 0, 0));

        btnText.setText("Text");
        btnText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPreviousMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(m22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(m29, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m30, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(m32, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m33, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m34, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m35, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(m25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m28, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNextMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(btnPreviousYear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNextYear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m36, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m37, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m38, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m39, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m40, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnToday, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblNote, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnText)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLogout)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(cmbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPreviousYear)
                            .addComponent(btnNextMonth))
                        .addComponent(btnPreviousMonth)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNextYear)
                            .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m39, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnToday)
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNote, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        // TODO add your handling code here:
        dateSelect(Hom.dateInt,Color.black);
        Hom.monthInt--;
        if (Hom.monthInt < 1) {
            Hom.monthInt = 12;
            Hom.yearInt--;
        }
        if (Hom.yearInt < 1) {
            Hom.yearInt = Hom.currentYear;
        }
        setMnY();
        Hom.setCal(Hom.monthInt, Hom.yearInt,Hom.findC(Hom.monthInt,Hom.yearInt));
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
}//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        // TODO add your handling code here:
       dateSelect(Hom.dateInt,Color.black);
        Hom.monthInt++;
        if (Hom.monthInt > 12) {
            Hom.monthInt = 1;
            Hom.yearInt++;
        }
        setMnY();
        Hom.setCal(Hom.monthInt, Hom.yearInt,Hom.findC(Hom.monthInt,Hom.yearInt));
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
}//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnPreviousYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousYearActionPerformed
        // TODO add your handling code here:
        dateSelect(Hom.dateInt,Color.black);
        Hom.yearInt--;
        if (Hom.yearInt < 1) {
            Hom.yearInt = Hom.currentYear;
        }
        setMnY();
        Hom.setCal(Hom.monthInt, Hom.yearInt,Hom.findC(Hom.monthInt,Hom.yearInt));
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
}//GEN-LAST:event_btnPreviousYearActionPerformed

    private void btnNextYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextYearActionPerformed
        // TODO add your handling code here:
        dateSelect(Hom.dateInt,Color.black);
        Hom.yearInt++;
        setMnY();
        Hom.setCal(Hom.monthInt, Hom.yearInt,Hom.findC(Hom.monthInt,Hom.yearInt));
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
}//GEN-LAST:event_btnNextYearActionPerformed

    private void m3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m3ActionPerformed
        // TODO add your handling code here:
        if(!m3.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m3.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m3.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m3ActionPerformed

    private void m6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m6ActionPerformed
        // TODO add your handling code here:
          if(!m6.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m6.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m6.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
          else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");
    }//GEN-LAST:event_m6ActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
}//GEN-LAST:event_btnCloseActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        // TODO add your handling code here:
        try {
            dateSelect(Hom.dateInt, Color.black);
            Hom.dateInt = cmbDate.getSelectedIndex();
            Hom.monthInt = cmbMonth.getSelectedIndex();
            switch (Hom.monthInt) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (Hom.dateInt < 32) {
                        dateSetting();

                    } else {
                        lblNote.setText("Invalid date!!");
                        cmbDate.setSelectedIndex(0);
                    }
                    break;

                case 4:
                case 6:
                case 9:
                case 11:
                    if (Hom.dateInt < 31) {
                        dateSetting();

                    } else {
                        lblNote.setText("Invalid date!!");
                        cmbDate.setSelectedIndex(0);
                    }
                    break;
                case 2:
                    if ((Hom.yearInt % 4 == 0 && Hom.yearInt % 100 != 0 || Hom.yearInt % 400 == 0) && Hom.dateInt < 29) {
                        dateSetting();

                    }
                    else if((Hom.yearInt % 4 != 0 && Hom.yearInt % 100 == 0 || Hom.yearInt % 400 != 0) && Hom.dateInt < 28){
                        dateSetting();
                    }
                    else {
                        lblNote.setText("Invalid date!!");
                        cmbDate.setSelectedIndex(0);
                    }
                    break;
                default:
                    dateSetting();
            }
        } catch (Exception e) {
            lblNote.setText("Some fields empty!!");
            cmbDate.setSelectedIndex(0);
            cmbMonth.setSelectedIndex(0);
            txtYear.setText("");
        }
    }//GEN-LAST:event_btnGoActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        // TODO add your handling code here:
        if(!m1.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m1.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m1.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m1ActionPerformed

    private void m5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m5ActionPerformed
        // TODO add your handling code here:
        if(!m5.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m5.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m5.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m5ActionPerformed

    private void m33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m33ActionPerformed
        // TODO add your handling code here:
        if(!m33.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m33.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m33.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m33ActionPerformed

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        // TODO add your handling code here:
        if(!m2.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m2.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m2.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m2ActionPerformed

    private void m4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m4ActionPerformed
        // TODO add your handling code here:
        if(!m4.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m4.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m4.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m4ActionPerformed

    private void m7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m7ActionPerformed
        // TODO add your handling code here:
        if(!m7.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m7.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m7.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m7ActionPerformed

    private void m8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m8ActionPerformed
        // TODO add your handling code here:
        if(!m8.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m8.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m8.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m8ActionPerformed

    private void m9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m9ActionPerformed
        // TODO add your handling code here:
        if(!m9.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m9.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m9.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
        else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m9ActionPerformed

    private void m10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m10ActionPerformed
        // TODO add your handling code here:
        if(!m10.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m10.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m10.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
    }//GEN-LAST:event_m10ActionPerformed

    private void m11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m11ActionPerformed
        // TODO add your handling code here:
         if(!m11.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m11.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m11.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m11ActionPerformed

    private void m12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m12ActionPerformed
        // TODO add your handling code here:
         if(!m12.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m12.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m12.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m12ActionPerformed

    private void m13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m13ActionPerformed
        // TODO add your handling code here:
         if(!m13.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m13.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m13.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m13ActionPerformed

    private void m14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m14ActionPerformed
        // TODO add your handling code here:
         if(!m14.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m14.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m14.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m14ActionPerformed

    private void m15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m15ActionPerformed
        // TODO add your handling code here:
         if(!m15.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m15.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m15.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
        }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m15ActionPerformed

    private void m17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m17ActionPerformed
        // TODO add your handling code here:
         if(!m17.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m17.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m17.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m17ActionPerformed

    private void m16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m16ActionPerformed
        // TODO add your handling code here:
         if(!m16.getText().equals("")){
        this.setVisible(false);
                Manual man=new Manual(user,Integer.parseInt(m16.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m16.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m16ActionPerformed

    private void m18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m18ActionPerformed
        // TODO add your handling code here:
         if(!m18.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m18.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m18.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m18ActionPerformed

    private void m19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m19ActionPerformed
        // TODO add your handling code here:
         if(!m19.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m19.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m19.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m19ActionPerformed

    private void m20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m20ActionPerformed
        // TODO add your handling code here:
         if(!m20.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m20.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m20.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m20ActionPerformed

    private void m21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m21ActionPerformed
        // TODO add your handling code here:
         if(!m21.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m21.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m21.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m21ActionPerformed

    private void m22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m22ActionPerformed
        // TODO add your handling code here:
         if(!m22.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m22.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m22.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m22ActionPerformed

    private void m23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m23ActionPerformed
        // TODO add your handling code here:
         if(!m23.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m23.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m23.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m23ActionPerformed

    private void m24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m24ActionPerformed
        // TODO add your handling code here:
         if(!m24.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m24.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m24.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m24ActionPerformed

    private void m25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m25ActionPerformed
        // TODO add your handling code here:
         if(!m25.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m25.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m25.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m25ActionPerformed

    private void m26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m26ActionPerformed
        // TODO add your handling code here:
         if(!m26.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m26.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m26.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m26ActionPerformed

    private void m27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m27ActionPerformed
        // TODO add your handling code here:
         if(!m27.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m27.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m27.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m27ActionPerformed

    private void m28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m28ActionPerformed
        // TODO add your handling code here:
         if(!m28.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m28.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m28.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m28ActionPerformed

    private void m29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m29ActionPerformed
        // TODO add your handling code here:
         if(!m29.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m29.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m29.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m29ActionPerformed

    private void m30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m30ActionPerformed
        // TODO add your handling code here:
         if(!m30.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m30.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m30.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m30ActionPerformed

    private void m31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m31ActionPerformed
        // TODO add your handling code here:
         if(!m31.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m31.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m31.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m31ActionPerformed

    private void m32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m32ActionPerformed
        // TODO add your handling code here:
         if(!m32.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m32.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m32.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m32ActionPerformed

    private void m34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m34ActionPerformed
        // TODO add your handling code here:
         if(!m34.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m34.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m34.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m34ActionPerformed

    private void m35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m35ActionPerformed
        // TODO add your handling code here:
         if(!m35.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m35.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m35.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m35ActionPerformed

    private void m36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m36ActionPerformed
        // TODO add your handling code here:
         if(!m36.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m36.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m36.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m36ActionPerformed

    private void m37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m37ActionPerformed
        // TODO add your handling code here:
         if(!m37.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m37.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m37.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m37ActionPerformed

    private void m38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m38ActionPerformed
        // TODO add your handling code here:
         if(!m38.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m38.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m38.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m38ActionPerformed

    private void m39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m39ActionPerformed
        // TODO add your handling code here:
         if(!m39.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m39.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m39.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m39ActionPerformed

    private void m40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m40ActionPerformed
        // TODO add your handling code here:
         if(!m40.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m40.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m40.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m40ActionPerformed

    private void m41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m41ActionPerformed
        // TODO add your handling code here:
         if(!m41.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m41.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m41.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m41ActionPerformed

    private void m42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m42ActionPerformed
        // TODO add your handling code here:
         if(!m42.getText().equals("")){
        this.setVisible(false);
        Manual man=new Manual(user,Integer.parseInt(m42.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),Integer.parseInt(m42.getText()),Hom.monthInt,Integer.parseInt(lblYear.getText()),0,0);
        man.setVisible(true);
         }
         else
              JOptionPane.showMessageDialog(this, "Invalid date!!Retry..");

    }//GEN-LAST:event_m42ActionPerformed

    private void btnTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
       Text t=new Text(user);
       t.setVisible(true);
    }//GEN-LAST:event_btnTextActionPerformed

    private void btnTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodayActionPerformed
        // TODO add your handling code here:
        cmbDate.setSelectedIndex(0);
        cmbMonth.setSelectedIndex(0);
        txtYear.setText("");
        dateSelect(Hom.dateInt,Color.black);
        Hom.monthInt = Hom.currentMonth;
        Hom.yearInt = Hom.currentYear;
        setMnY();
        Hom.setCal(Hom.monthInt, Hom.yearInt,Hom.findC(Hom.monthInt,Hom.yearInt));
        setM();
        dateSelect(Hom.dateInt,Color.BLUE);
}//GEN-LAST:event_btnTodayActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        Home h=new Home();
        this.setVisible(false);
        h.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                String username = null;
                new StoreRetrieve(username).setVisible(true);
               // new StoreRetrieve(String username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnNextYear;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnPreviousYear;
    private javax.swing.JButton btnText;
    private javax.swing.JButton btnToday;
    private javax.swing.JComboBox cmbDate;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblYear;
    private javax.swing.JButton m1;
    private javax.swing.JButton m10;
    private javax.swing.JButton m11;
    private javax.swing.JButton m12;
    private javax.swing.JButton m13;
    private javax.swing.JButton m14;
    private javax.swing.JButton m15;
    private javax.swing.JButton m16;
    private javax.swing.JButton m17;
    private javax.swing.JButton m18;
    private javax.swing.JButton m19;
    private javax.swing.JButton m2;
    private javax.swing.JButton m20;
    private javax.swing.JButton m21;
    private javax.swing.JButton m22;
    private javax.swing.JButton m23;
    private javax.swing.JButton m24;
    private javax.swing.JButton m25;
    private javax.swing.JButton m26;
    private javax.swing.JButton m27;
    private javax.swing.JButton m28;
    private javax.swing.JButton m29;
    private javax.swing.JButton m3;
    private javax.swing.JButton m30;
    private javax.swing.JButton m31;
    private javax.swing.JButton m32;
    private javax.swing.JButton m33;
    private javax.swing.JButton m34;
    private javax.swing.JButton m35;
    private javax.swing.JButton m36;
    private javax.swing.JButton m37;
    private javax.swing.JButton m38;
    private javax.swing.JButton m39;
    private javax.swing.JButton m4;
    private javax.swing.JButton m40;
    private javax.swing.JButton m41;
    private javax.swing.JButton m42;
    private javax.swing.JButton m5;
    private javax.swing.JButton m6;
    private javax.swing.JButton m7;
    private javax.swing.JButton m8;
    private javax.swing.JButton m9;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
String user;
}
