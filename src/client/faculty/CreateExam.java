package client.faculty;

import client.student.StudentUI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateExam extends javax.swing.JFrame {

//For Localhost
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_management";
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
    private final String EXAM_ID = getRandomID(6);

//FOR AWS SERVER
//    private static final String DB_URL = "jdbc:mysql://exam-management-aws.cpyjaypv4zdd.us-east-1.rds.amazonaws.com/exam_management";
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String USER = "admin";
//    private static final String PASSWORD = "7801898047";
    private static String DB_URL;
    private static String JDBC_DRIVER;
    private static String USER;
    private static String PASSWORD;

    public CreateExam() {
        initComponents();
        loadDatabaseProperties();
        createExamID.setText(EXAM_ID);
    }

    private void loadDatabaseProperties() {
        try (FileReader reader = new FileReader("src/database.properties")) {
            Properties p = new Properties();
            p.load(reader);
            DB_URL = p.getProperty("DB_URL");
            JDBC_DRIVER = p.getProperty("JDBC_DRIVER");
            USER = p.getProperty("USER");
            PASSWORD = p.getProperty("PASSWORD");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getRandomID(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    private static boolean isValid(String examName, String questions, String facultyName, String password) {
        if (examName == null || examName.equals("")) {
            return false;
        }

        if (questions == null || questions.equals("")) {
            return false;
        }

        if (facultyName == null || facultyName.equals("")) {
            return false;
        }

        if (password == null || password.equals("")) {
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        createExamID = new javax.swing.JTextField();
        createExamName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        createExamQuestions = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        createExamFacultyName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        createExamPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        createExamDuration = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Exam ID");

        createExamID.setEditable(false);
        createExamID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createExamIDActionPerformed(evt);
            }
        });

        createExamName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createExamNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Exam Name");

        jLabel3.setText("Questions");

        createExamQuestions.setColumns(20);
        createExamQuestions.setRows(5);
        jScrollPane1.setViewportView(createExamQuestions);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Create Exam");

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        createExamFacultyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createExamFacultyNameActionPerformed(evt);
            }
        });

        jLabel5.setText("Faculty Name");

        jLabel6.setText("Password");

        jLabel7.setText("Duration");

        createExamDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10 Sec", "30 Mins", "1 Hr", "1.5 Hr", "2 Hr" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(createExamFacultyName, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(createExamName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(createExamID, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(155, 155, 155)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(createExamPassword)
                            .addComponent(createExamDuration, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(createExamID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(createExamName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(createExamFacultyName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(createExamPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createExamDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createExamIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createExamIDActionPerformed
    }//GEN-LAST:event_createExamIDActionPerformed

    private void createExamNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createExamNameActionPerformed
    }//GEN-LAST:event_createExamNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String examName = createExamName.getText();
        String questions = createExamQuestions.getText();
        String facultyName = createExamFacultyName.getText();
        String password = createExamPassword.getText();
        String duration = createExamDuration.getSelectedItem().toString().trim();
        long seconds;

        switch (duration) {
            case "10 Sec":
                seconds = 10;
                break;
            case "30 Mins":
                seconds = 1800;
                break;
            case "1 Hr":
                seconds = 3600;
                break;
            case "1.5 Hr":
                seconds = 5400;
                break;
            case "2 Hr":
                seconds = 7200;
                break;
            default:
                seconds = 30;
        }

        if (!isValid(examName, questions, facultyName, password)) {
            System.out.println("Invalid Details");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection...");
            System.out.println("Creating statement...");
            String sql = "INSERT INTO exams VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, EXAM_ID);
            statement.setString(2, examName);
            statement.setString(3, questions);
            statement.setString(4, facultyName);
            statement.setString(5, password);
            statement.setLong(6, seconds);
            int i = statement.executeUpdate();
            System.out.println("Result: " + i);
            var facultyUI = new FacultyUI(EXAM_ID);
            facultyUI.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void createExamFacultyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createExamFacultyNameActionPerformed
    }//GEN-LAST:event_createExamFacultyNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateExam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> createExamDuration;
    private javax.swing.JTextField createExamFacultyName;
    private javax.swing.JTextField createExamID;
    private javax.swing.JTextField createExamName;
    private javax.swing.JPasswordField createExamPassword;
    private javax.swing.JTextArea createExamQuestions;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
