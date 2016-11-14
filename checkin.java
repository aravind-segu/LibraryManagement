package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

public class checkin extends LibraryManagement implements ActionListener{
    
    /*
    * Connectfion is established to sql
    * a text field is created for each information
    * JFrame is initialized for GUI,
    * The date format is set and the current date is created with this format
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    JFrame checkinFrame;
    Container contentPane;
    JPanel myPanel;
    JTextField book;
    JTextField name;
    JTextField date;
    JTextField duedate;
    DateFormat currentdate = DateFormat.getDateInstance();
    Date today = new Date();
    String todayDate = currentdate.format(today);
    boolean foundbook = false;
    boolean foundmem = false;
    String booktitle;
    int copiesrem;
    String first;
   
    public checkin(){
        /*
        * The GUI method is called
        */
        GUI();
    }
    public void GUI(){
             /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
         checkinFrame = new JFrame();
         checkinFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         checkinFrame.setTitle ("Check-Out");
         checkinFrame.setSize (400,400);
         checkinFrame.setVisible(true);
         checkinFrame.setLocationRelativeTo(null);
        
        /*
         * Thre panels are defined, bottom, top and info
         * The mainPanel is instantiated and is given border Layot
         */
        contentPane =  checkinFrame.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
       /*
        * Top Panel is given GridBag Layout
        * At (0,0) the title is printed
        * At (0,1) the the directions are printed
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Check-In Book");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the information and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
       
        /*
        * Info Panel is given GridBag Layout
        * At (0,2) the Book ID label and a textebox are printed
        * At (0,3) the MemberID and textbos are printed
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel Booktitle = new JLabel ("Book ID:");
        info.add (Booktitle , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        book = new JTextField (20);
        info.add (book , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel author = new JLabel ("Member ID:");
        info.add (author , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        name = new JTextField (20);
        info.add (name , gbc);
        
               
       /*
        * The bottom is given border Layout
        * At West the Back Button is printed
        * At East the Checkin button is shown
        */         
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Check-In");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
       /*
       * All other panels are added to the main panel
       * The JFrame is validated
       */     
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
      checkinFrame.validate ();
                
    }
    @Override
 public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If the check-In button is pressed the check method is called and the current Frame is disposed. 
        */
        if (event.equals ("Check-In")){
            try {
                 check();
                 checkinFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        /*
        * If the Back button is pressed the user is taken to the Main Window. 
        */
        if (event.equals ("Back To Main")){
            try {
                new MainWindow ();
                checkinFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
}
 public void check (){
     try {
         /*
         * The info entered in textboxes is retrieved and is stored in temporary string variables
         * Connection is established with memCheckouts table from the database
         */
         String idInput = book.getText();
         String idmem = name.getText();
         String lastname = "";
         String btitle = "";
 
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
               
			while (resultSet.next()) {
                            /*
                            * All data in a particular column is retrieved and is stored in temporary variable
                            * If the book ID entered is equal to the book ID in database the foundbook boolean is true
                                * If the Mem ID entered is equal to the memID in database the foundmem boolean is true
                            * All connections are closed
                            */
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                
                                if (bID.equals(idInput)){
                                foundbook = true;
                                    if (idlocal.equals(idmem)){
                                    foundmem = true;
                                    break;
                                    }
                                }
                                
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        
                        /*
                        * If both booleans are true the inItem method is called
                        * If mem is true and book is false, the BookID not found is printed
                        * If member id is false, and book is true, MemberID not found is printed
                        * If botha are false, both not found is printed
                        */
                        if (foundmem == true & foundbook == true){
                            inItem();
                        }
                          if (foundmem == true & foundbook == false){
                            JOptionPane.showMessageDialog(null, "Book ID: "+ idInput + " not Found");
                            new checkin ();
                            checkinFrame.dispose();
                        }
                        if (foundmem == false & foundbook == true){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idmem + " not found." );
                            new checkin ();
                            checkinFrame.dispose();
                        }
                        if (foundmem == false & foundbook == false){
                            JOptionPane.showMessageDialog(null, "Member: " + idmem + " did not checkout Book: " + idInput + "." );
                            new checkin();
                            checkinFrame.dispose();
                        }
     }catch (Exception a){
         System.out.println (a);
     }
 }
 public void inItem (){
     try{
         /*
         * The info entered in textboxes is retrieved and is stored in temporary string variables
         * Connection is established with memCheckouts table from the database
         */
         String idInput = book.getText();
         String idmem = name.getText();
 
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
               
			while (resultSet.next()) {
                               /*
                            * All data in a particular column is retrieved and is stored in temporary variable
                            * If the book ID entered is equal to the book ID in database the foundbook boolean is true
                                * If the Mem ID entered is equal to the memID in database the foundmem boolean is true
                                    * that row is deleted
                            */
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                String lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                String btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                
                                if (bID.equals(idInput)){
                                    if (idlocal.equals(idmem)){
                                        
                                        resultSet.deleteRow();
                                        
                                        break;
                                    }
                                }
                                    
                                       
                        
 } /*
                        * Copreduce method is called
                        */
                        copreduce();
                        //renewFrame.dispose();
     }catch (Exception exa){
                     System.out.println (exa);
                     }

}
 public void copreduce (){
     try{
                  /*
         * The info entered in textboxes is retrieved and is stored in temporary string variables
         * Connection is established with BookDetails table from the database
         */
         String idInput = book.getText();
         String idmem = name.getText();
         
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,CopiesChecked,ShelfNumber,Available FROM BookDetails");
               
			while (resultSet.next()) {
                              /*
                            * All data in a particular column is retrieved and is stored in temporary variable
     
                            */
                                int id = resultSet.getInt("ID");
                                String bookInput = resultSet.getString("BookTitle");
                                String author = resultSet.getString("Author");
                                String year = resultSet.getString("Year");
                                String publisher = resultSet.getString("Publisher");
                                String pages = resultSet.getString("Pages");
                                String BookID = resultSet.getString("BookID");
                                String Copies = resultSet.getString("Copies");
                                String copiesrema = resultSet.getString("CopiesChecked");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                /*
                                * If the bookID's match
                                */
                              if (idInput.equals(BookID)){
                                
                                  /*
                                  * The copies remaining in increased
                                  * The data base is updated
                                  */
                                  int cop = Integer.parseInt(copiesrema);
                                  cop--;
                                  String copies = String.valueOf(cop);
                                  resultSet.updateString("BookTitle", bookInput);
                                  resultSet.updateString("Author", author);
                                  resultSet.updateString("Year", year);
                                  resultSet.updateString("Publisher", publisher);
                                  resultSet.updateString("Pages",pages);
                                  resultSet.updateString("BookID", BookID);
                                  resultSet.updateString("Copies", Copies);
                                  resultSet.updateString("CopiesChecked", copies);
                                  resultSet.updateString("ShelfNumber", ShelfNumber);
                                  resultSet.updateString("Available", "Yes");
                                  resultSet.updateRow();
                                          
                                          break;
                                  
     }
                              
                        }
                        /*
                        * The connections are closed
                        * a new checkin frame is called
                        * The current frame is disposed
                        */
                              resultSet.close();
                              statement.close();
                              connection.close();
                              JOptionPane.showMessageDialog(null, "Successfully Checked In" );
                              new checkin();
                              checkinFrame.dispose();
} catch (Exception e){
    System.out.println (e);
}
 }
 
 }



