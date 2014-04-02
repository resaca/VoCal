/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Home.java
 *
 * Created on Mar 27, 2011, 3:34:49 PM
 */

/**
 *
 * @author Rahana, Anu, Sebin, Pratheesh
 */
 

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Home extends javax.swing.JFrame {
        DateFormat year=new SimpleDateFormat("yyyy");
        DateFormat month=new SimpleDateFormat("MM");
        DateFormat date=new SimpleDateFormat("dd");
        DateFormat hour=new SimpleDateFormat("hh");
        DateFormat minute=new SimpleDateFormat("mm");
        DateFormat sec=new SimpleDateFormat("ss");
        Date dates=new Date();
        String Year=year.format(dates);
        String Month=month.format(dates);
        String Date=date.format(dates);
        String Hour=hour.format(dates);
        String Minute=minute.format(dates);
        String Sec=sec.format(dates);
        int dateInt=Integer.parseInt(Date);
        int monthInt=Integer.parseInt(Month);
        int currentMonth=monthInt;
        int yearInt = Integer.parseInt(Year);
        int currentYear=yearInt;
        int offset=0,leap=0;
        int i=0,j=0,k=0,c;
        String[] datesss=new String[42];


    /** Creates new form Home2 */
    public Home() {
        VocalDao vo=new VocalDao("Vo-Cal");
        vo.connect();
        initComponents();
        looks=UIManager.getInstalledLookAndFeels();
        changethelookandfeel(3);
        //setMnY();
        //setCal(monthInt,yearInt,findC(monthInt, yearInt));
        //setM();
        
    }
    private UIManager.LookAndFeelInfo looks[];
    private void changethelookandfeel(int value)
    {
        try
        {
            UIManager.setLookAndFeel(looks[value].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int findC(int a,int b){
            switch(a)
            {
                case 1:case 10: c=1;break;
                case 2:case 11:case 3: c=4;break;
                case 4:case 7:c=0;break;
                case 5:c=2;break;
                case 6:c=5;break;
                case 8:c=3;break;
                case 9:case 12:c=6;break;
            }
            if(b>=1){
               offset=b-1;
               if(((b%4==0&&b%100!=0)||b%400==0)&&a<=2)
               for(i=b;i>=1;i--){
                   if((i%4==0&&i%100!=0)||i%400==0){
                       leap=((i-8)/4);
                       break;
                   }
               }
               else
                   for(i=b;i>=1;i--){
                   if((i%4==0&&i%100!=0)||i%400==0){
                       leap=((i-4)/4);
                       break;
                   }
               }
            }
                      
            c=c+offset+leap;
            c=c%7;
            
            if(((b%4==0&&b%100!=0)||b%400==0)&&b<=2)c=c-1;
            c=c+7;
            c=c%7;
            //System.out.println(c);
            return(c);
    }
public void setCal(int a,int b,int C){
    for(i=0;i<C;i++){
       datesss[i]="";
    }

    switch(a)
            {
                case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                    for(j=i,i=0;i<31;i++,j++)
                    {
                        datesss[j]=Integer.toString(i+1);
                    }
                        break;
                case 2:
                    if(b%4==0&&b%100!=0||b%400==0)
                    {
                        for(j=i,i=0;i<29;i++,j++)
                        {
                            datesss[j]=Integer.toString(i+1);
                        }
                    }
                    else
                    {
                       for(j=i,i=0;i<28;i++,j++)
                        {
                            datesss[j]=Integer.toString(i+1);
                        }
                    }
                    break;
                case 4:case 6:case 9:case 11:
                    for(j=i,i=0;i<30;i++,j++)
                    {
                        datesss[j]=Integer.toString(i+1);
                    }
                        break;
            }
    for(k=j;k<42;k++){
        datesss[k]="";
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        pas = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        lblCurrent = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vo-Cal");
        setBounds(new java.awt.Rectangle(450, 115, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Verdana", 0, 10));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnLogin.setText("Login");
        btnLogin.setToolTipText("Click here to login after entering username and password..");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("Click here to clear the entered username and password..");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblUsername.setText("User Name");

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblPassword.setText("Password");

        pas.setToolTipText("Enter your password here..");
        pas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasActionPerformed(evt);
            }
        });

        txtUsername.setToolTipText("Enter your username here..");
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnRegister.setText("Register");
        btnRegister.setToolTipText("Click here to create a new account..");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        lblCurrent.setFont(new java.awt.Font("Dialog", 1, 14));
        lblCurrent.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocal/Muziq.resized.jpg"))); // NOI18N
        jLabel1.setToolTipText("The Voice Calendar..");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel1.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsername)
                                            .addComponent(lblPassword))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnRegister)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLogin)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(pas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnLogin)
                    .addComponent(btnCancel))
                .addGap(12, 12, 12)
                .addComponent(lblCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Register reg=new Register();
        reg.setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        txtUsername.setText("");
        pas.setText("");

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            // TODO add your handling code here:
            String user = txtUsername.getText();
            String pass = pas.getText();
            //VocalDao lc=new VocalDao();
            //lc.connect();
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            Connection dbConnection = DriverManager.getConnection(strUrl);
            PreparedStatement stmt = dbConnection.prepareStatement("select * from logintable where (username='"+user+"') and (password='"+pass+"')");
            ResultSet rs = stmt.executeQuery();
            rs.next();
                String userdb=rs.getString("username");

                String passdb=rs.getString("password");
                

                if(user.equals(userdb)&&pass.equals(passdb)&&!user.isEmpty()){
                    this.setVisible(false);
                    StoreRetrieve st=new StoreRetrieve(user);
                    st.setVisible(true);
                }
                else
                {
                    lblCurrent.setText("Invalid username or password!!");
            txtUsername.setText("");
            pas.setText("");
                }

            //lc.disconnect();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            lblCurrent.setText("Invalid username or password!!");
            txtUsername.setText("");
            pas.setText("");
        }

}//GEN-LAST:event_btnLoginActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
        pas.requestFocus();
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        txtUsername.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void pasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasActionPerformed
        // TODO add your handling code here:
         
        btnLoginActionPerformed(evt);
    }//GEN-LAST:event_pasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                           }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCurrent;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField pas;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
