/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import client.User;
import client.student.FileUpload;
import java.awt.AWTException; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.IOException; 
import java.io.File; 
import javax.imageio.ImageIO; 

// Current Time
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class


public class Screenshot implements Runnable{
	public static final long serialVersionUID = 1L; 
        private final User user;
        public Screenshot(User user){
           this.user = user;
        }
	public  String getCurrentDateTime(){
		LocalDateTime myDateObj = LocalDateTime.now();  
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH.mm.ss");  
		String formattedDate = myDateObj.format(myFormatObj);  
		return formattedDate;
	}

	public  void screenshot_task(String current_date_time){
		try {  
			//  Dont remove this, it shows
			// error: exception InterruptedException is never thrown 
			// in body of corresponding try statement
			Thread.sleep(120);

			Robot r = new Robot(); 

			// It saves screenshot to desired path 
			String path = System.getProperty("user.dir") +  "/local/"+ user.rollNum;
                        File dir = new File(path);
                         if (!dir.exists()) {
                            dir.mkdirs();
                         }
			String fileName = user.rollNum + "_"+current_date_time + ".jpg";

			String full_path = path +"/" + fileName;

			// Used to get ScreenSize and capture image 
			Rectangle capture = 
			new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 

			BufferedImage Image = r.createScreenCapture(capture); 
			ImageIO.write(Image, "jpg", new File(full_path)); 
			System.out.println("Screenshot saved"); 

			new FileUpload(this.user).uploadFile("",full_path);
//			new Thread(runns).start();
		
		} 
		catch (AWTException | IOException | InterruptedException ex) { 
			System.out.println(ex); 
		}
	}
	public void run(){
		for (int i = 0; i < 3 ;i++ ) {		
			try {
				Thread.sleep(8000);
			} 
			catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
			String current_date_time = getCurrentDateTime();
			screenshot_task(current_date_time);
		} 
	}
} 

