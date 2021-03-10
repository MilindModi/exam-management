package client;

import features.KeyLogger;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class StudentUI extends javax.swing.JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_management";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DefaultListModel model = new DefaultListModel();
    private PrintWriter writer;
    private Socket socket;
    private final User user;

    private String examName;
    private String questions;
    private String facultyUsername;

    public StudentUI(final User user) {
        this.user = user;
        KeyLogger kg = new KeyLogger(user);
        try {
            initComponents();
            socket = new Socket("localhost", 8989);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            writer.println(user.rollNum); //user id
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        ChatClient client = new ChatClient("localhost", 8989);
        new ClientReadHandler(socket, client, model, studentUIChatBox).start();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection...");
            System.out.println("Creating statement...");

            String sql = "SELECT examName, questions, facultyUsername FROM exams WHERE BINARY examId='" + user.examId + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            examName = rs.getString("examName");
            facultyUsername = rs.getString("facultyUsername");

            studentUIDisplayExamID.setText("Exam ID: " + user.examId);
            studentUIDisplayExamName.setText("Exam Name: " + examName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        studentUIChatBox = new javax.swing.JList<>();
        studentUIUploadPdfButton = new javax.swing.JButton();
        studentUIChatTextBox = new javax.swing.JTextField();
        studentUISendButton = new javax.swing.JButton();
        canvas1 = new java.awt.Canvas();
        studentUIExitButton = new javax.swing.JToggleButton();
        studentUIDisplayExamName = new javax.swing.JLabel();
        studentUIDisplayExamID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setViewportView(studentUIChatBox);

        studentUIUploadPdfButton.setText("UPLOAD PDF");
        studentUIUploadPdfButton.setToolTipText("");
        studentUIUploadPdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentUIUploadPdfButtonActionPerformed(evt);
            }
        });

        studentUISendButton.setText("SEND");
        studentUISendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentUISendButtonActionPerformed(evt);
            }
        });

        studentUIExitButton.setSelected(true);
        studentUIExitButton.setText("EXIT");
        studentUIExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentUIExitButtonActionPerformed(evt);
            }
        });

        studentUIDisplayExamName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        studentUIDisplayExamName.setText("Exam Name: ");

        studentUIDisplayExamID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        studentUIDisplayExamID.setText("Exam ID: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentUIUploadPdfButton)
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(studentUIExitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(studentUIDisplayExamID))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(studentUIDisplayExamName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(studentUIChatTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentUISendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(studentUIDisplayExamID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(studentUIDisplayExamName)
                                .addGap(36, 36, 36))
                            .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentUIChatTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentUISendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(studentUIUploadPdfButton)
                .addGap(5, 5, 5)
                .addComponent(studentUIExitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentUISendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentUISendButtonActionPerformed
        String text = "@" + facultyUsername + " " + studentUIChatTextBox.getText();
        writer.println(text);
        model.addElement(text);
        studentUIChatBox.setModel(model);
    }//GEN-LAST:event_studentUISendButtonActionPerformed

    private void studentUIUploadPdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentUIUploadPdfButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            System.out.println("Selected file name: " + selectedFile.getName());
            System.out.println("Selected file path: " + selectedFile.getPath());
            new FileUpload(user).uploadFile(selectedFile.getPath(), selectedFile.getPath());
        }
    }//GEN-LAST:event_studentUIUploadPdfButtonActionPerformed

    private void studentUIExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentUIExitButtonActionPerformed
        new FileUpload(user).uploadFile("", user.keyLogFile);
        this.dispose();
    }//GEN-LAST:event_studentUIExitButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentUI(new User("19", "Milind", "19_Milind_qekvD7.txt", "qekvD7")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> studentUIChatBox;
    private javax.swing.JTextField studentUIChatTextBox;
    private javax.swing.JLabel studentUIDisplayExamID;
    private javax.swing.JLabel studentUIDisplayExamName;
    private javax.swing.JToggleButton studentUIExitButton;
    private javax.swing.JButton studentUISendButton;
    private javax.swing.JButton studentUIUploadPdfButton;
    // End of variables declaration//GEN-END:variables
}
