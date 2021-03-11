package client.student;

import client.LoginScreen;
import client.User;
import client.chat.ChatClient;
import client.chat.ClientReadHandler;
import logger.KeyLogger;
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
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class StudentUI extends javax.swing.JFrame {

    //FOR LOCALHOST
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_management";
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";

    //FOR AWS
//    private static final String DB_URL = "jdbc:mysql://exam-management-aws.cpyjaypv4zdd.us-east-1.rds.amazonaws.com/exam_management";
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String USER = "admin";
//    private static final String PASSWORD = "7801898047";
    
    private static String DB_URL ;
    private static String JDBC_DRIVER ;
    private static String USER ;
    private static  String PASSWORD ;
    
    private DefaultListModel model = new DefaultListModel();
    private PrintWriter writer;
    private Socket socket;
    private final User user;

    private String examName;
    private String questions;
    private String facultyUsername;

    public StudentUI(final User user) {
        
        //Do not delete this lines
//        GraphicsEnvironment graphics =
//        GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice device = graphics.getDefaultScreenDevice();
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
         //till here

        FileReader reader;  
        try {
            reader = new FileReader("src/database.properties");
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

        studentUIDisplayQuestion.setHighlighter(null);
        
        ChatClient client = new ChatClient("localhost", 8989);
        new ClientReadHandler(socket, client, model, studentUIChatBox,null).start();
        loadDataFromDatabase();
        
//        device.setFullScreenWindow(this); do not delete  this line
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
            questions = rs.getString("questions");

            studentUIDisplayExamID.setText("Exam ID: " + user.examId);
            studentUIDisplayRollNum.setText("Roll Num: " + user.rollNum);
            studentUIDisplayExamName.setText("Exam Name: " + examName);
            studentUIDisplayQuestion.setText(questions);
            
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
        studentUIDisplayRollNum = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentUIDisplayQuestion = new javax.swing.JTextArea();

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

        studentUIDisplayRollNum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        studentUIDisplayRollNum.setText("Roll Num:");

        studentUIDisplayQuestion.setEditable(false);
        studentUIDisplayQuestion.setColumns(20);
        studentUIDisplayQuestion.setRows(5);
        studentUIDisplayQuestion.setDragEnabled(true);
        jScrollPane2.setViewportView(studentUIDisplayQuestion);

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
                .addContainerGap(367, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentUIDisplayExamID)
                            .addComponent(studentUIDisplayExamName)
                            .addComponent(studentUIDisplayRollNum)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(canvas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(studentUIDisplayExamID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(studentUIDisplayExamName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentUIDisplayRollNum)
                                .addGap(18, 18, 18)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentUIChatTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentUISendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
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
            JOptionPane.showMessageDialog(this,"File uploaded sucessfully");
        }
    }//GEN-LAST:event_studentUIUploadPdfButtonActionPerformed

    private void studentUIExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentUIExitButtonActionPerformed
      

        int opt=JOptionPane.showConfirmDialog(this,"Are you sure?","Exit",JOptionPane.YES_NO_OPTION);  
        if(opt == JOptionPane.YES_OPTION){  
            new FileUpload(user).uploadFile("", "local\\"+user.keyLogFile);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
            System.exit(0);
       }  
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
                new StudentUI(new User("99", "mn", "5_Milind_qekvD7.txt", "HQUxLt")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> studentUIChatBox;
    private javax.swing.JTextField studentUIChatTextBox;
    private javax.swing.JLabel studentUIDisplayExamID;
    private javax.swing.JLabel studentUIDisplayExamName;
    private javax.swing.JTextArea studentUIDisplayQuestion;
    private javax.swing.JLabel studentUIDisplayRollNum;
    private javax.swing.JToggleButton studentUIExitButton;
    private javax.swing.JButton studentUISendButton;
    private javax.swing.JButton studentUIUploadPdfButton;
    // End of variables declaration//GEN-END:variables
}
