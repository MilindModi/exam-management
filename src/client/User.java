/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author MODI
 */
public class User {
   public final String rollNum;
   public final String name;
   public final String keyLogFile;
   public final String examId;

   
   public User(String rollNum, String name, String keyLogFile, String examId) {
       this.rollNum = rollNum;
       this.name = name;
       this.keyLogFile = keyLogFile;
       this.examId = examId;
   }
}
