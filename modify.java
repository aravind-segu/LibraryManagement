
package librarymanagement;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;


/**
 *
 * @author mallikarjuna
 */
public class modify extends LibraryManagement implements ActionListener{
    /*
    * Connection variables are defined
    * Variables are created for each column in Book Details
    * A TextField is created for each column in BookDetails
    * Booleans are defined false for further checking
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String book ;
    String authordetails ;
    String yeardetails;
    String publisherdetails ;
    String pagesdetails ;
    String BookIDdetails ;
    String Copiesdetails ;
    String ShelfNumberdetails ;
    String Availabledetails ;
    String idDetails;
    Container contentPane;
    JPanel myPanel;
    JTextField title;
    JTextField numberCopiesText;
    JTextField shelfNumberText;
    JTextField authorDetails;
    JTextField yearText;
    JTextField publisherText;
    JTextField numberOfPagesText;
    JTextField numberText;
    JFrame modifyFrame;
    boolean copiestext = false;
    boolean shelfText = false;
    boolean year = false;
    boolean pages = false;
    public modify (){
        data();
    }
    
    public void data (){
        /*
        * The row selected by the user is retreived 
        * The data in that row is stored for later user
        */
        int rowLocal = manageBooks.row;
        
        idDetails = manageBooks.data[rowLocal][0] ;
        book = manageBooks.data[rowLocal][1];
        authordetails = manageBooks.data[rowLocal][2];
        yeardetails = manageBooks.data[rowLocal][3];
        publisherdetails = manageBooks.data[rowLocal][4];
        pagesdetails = manageBooks.data[rowLocal][5];
        BookIDdetails = manageBooks.data[rowLocal][6];
        Copiesdetails =manageBooks.data[rowLocal][7];
        ShelfNumberdetails = manageBooks.data[rowLocal][9];
        Availabledetails = manageBooks.data[rowLocal][10];
        
        guiModify();
        
    }
    
    public void guiModify (){
       /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
        modifyFrame = new JFrame ();
        modifyFrame.setUndecorated(true);
        modifyFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        modifyFrame.setTitle ("Modify Books");
        modifyFrame.setSize (400,600);
        modifyFrame.setVisible(true);
        modifyFrame.setLocationRelativeTo(null);
        
        /*
        * Three panels are defined
        */
        contentPane = modifyFrame.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
        /*
        * The top panel is given a GridBaglayout
        * The title is added
        * Directions are added
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Modify Records");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the new information of the Book and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
        
        /*
        * The info layot is given a GridBagLayout
        * Each column in the BookDetaisl table is given a label and a text box is added beside the label
        * The textbox is filled with the data fromt the local variables
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel Booktitle = new JLabel ("Book Title:");
        info.add (Booktitle , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        title = new JTextField (20);
        title.setText(book);
        info.add (title , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel author = new JLabel ("Author:");
        info.add (author , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
         authorDetails = new JTextField (20);
         authorDetails.setText(authordetails);
        info.add (authorDetails , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel year = new JLabel ("Year:");
        info.add(year,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
         yearText = new JTextField (20);
         yearText.setText(yeardetails);
        info.add(yearText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel publisher  = new JLabel ("Publisher:");
        info.add(publisher,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        publisherText = new JTextField (20);
        publisherText.setText(publisherdetails);
        info.add(publisherText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel numberOfPages  = new JLabel ("Number Of Pages:");
        info.add(numberOfPages,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
         numberOfPagesText = new JTextField (20);
         numberOfPagesText.setText(pagesdetails);
        info.add(numberOfPagesText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel number  = new JLabel ("Book ID: ");
        info.add(number,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
         numberText = new JTextField (20);
         numberText.setText(BookIDdetails);
         numberText.setEditable(false);
        info.add(numberText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel numberCopies  = new JLabel ("Number of Copies: ");
        info.add(numberCopies,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        numberCopiesText = new JTextField (20);
        numberCopiesText.setText(Copiesdetails);
        info.add(numberCopiesText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        JLabel shelfNumber  = new JLabel ("Shelf Number: ");
        info.add(shelfNumber,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 9;
        shelfNumberText = new JTextField (20);
        shelfNumberText.setText(ShelfNumberdetails);
        info.add(shelfNumberText,gbc);
        
        /*
        * Two Buttons Back and Modify are added to the bottom panel
        */
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Modify");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
       /*
       * All three panels are added to myPanel
       */
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       modifyFrame.validate ();
    }
     public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If the event is equal to Back
        * The MainWindow is called
        * The current Frame is disposed
        */
        if (event.equals ("Back To Main")){
            try {
                new MainWindow ();
                modifyFrame.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        if (event.equals ("Modify")){
            try {
                 /*
                 * A string object is created for each text box in the add books section
                 * Copies Book, Shelf Number, Year and Pages are taken and are each turned into integers
                 * If this process happens with no errors the code proceeds
                 * However if this process results in an error, the booleans for each one are defined to be true
                 * In the end if statement, either of the booleans are true
                    * A try again message is printed
                    * the constructor is called
                    * The current frame is disposed
                 * else
                    * The check method is called
                 */
         String titleBook = title.getText();  
         String CopiesBook = numberCopiesText.getText();
         String shelfBook = shelfNumberText.getText();
         String authorBook = authorDetails.getText();
         String yearBook = yearText.getText();
         String publisherBook = publisherText.getText();
         String pagesBook = numberOfPagesText.getText();
         String id ="";
         try {
             Integer.parseInt(CopiesBook);
             
         }catch (Exception ae){
            // JOptionPane.showMessageDialog(null, "Please Enter a number in the Copies Section");
             copiestext = true;
             }
         try {
             Integer.parseInt(shelfBook);
             
         }catch (Exception ae){
            //JOptionPane.showMessageDialog(null, "Please Enter a number in the Shelf Number Section");
             shelfText = true;
             }
         try {
             Integer.parseInt(yearBook);
             
         }catch (Exception ae){
             
             year = true;
                  }
         try {
             Integer.parseInt(pagesBook);
             
         }catch (Exception ae){
             //JOptionPane.showMessageDialog(null, "Please Enter a number in the Pages Section");
             pages = true;
             }
         if (pages == false & year == false & shelfText == false & copiestext == false){
                check();
         }else {
             JOptionPane.showMessageDialog(null, "Please Ensure only numbers are entered under pages, year, shelf number and copies");
             new modify();
             modifyFrame.dispose();
         }
            } catch (Exception f) {
                System.out.println(" Sorry, Error Occured");
            }
         
            }
        }
     public void check(){
                   /*
                 * A string object is created for each text box in the add books section
                 * Title, Author and Publisher are each taken to see if they are empty
                 * If this process happens with no errors the code proceeds
                 * However if this process results in an error, the booleans for each one are defined to be true
                 * In the end if statement, either of the booleans are true
                    * A try again message is printed
                    * the constructor is called
                    * The current frame is disposed
                 * else
                    * bkid method is called
                 */
        
         String titleBook = title.getText();  
          String CopiesBook = numberCopiesText.getText();
          String shelfBook = shelfNumberText.getText();
          String authorBook = authorDetails.getText();
          String yearBook = yearText.getText();
          String publisherBook = publisherText.getText();
          String pagesBook = numberOfPagesText.getText();
          boolean error = false;
         if (titleBook.equals("")){
              JOptionPane.showMessageDialog(null, "Book Title Left Blank. Please Try Again.");
              error = true;
              new modify();
              modifyFrame.dispose();
         }
         if (authorBook.equals("")){
              JOptionPane.showMessageDialog(null, "Author Left Blank. Please Try Again.");
              error = true;
              new modify();
              modifyFrame.dispose();
         }
         if (publisherBook.equals("")){
              JOptionPane.showMessageDialog(null, "Publisher Left Blank Left Blank. Please Try Again.");
              new modify();
              error = true;
              modifyFrame.dispose();
         }
         if (error == false){
         modifyBook();
         }
     }
    
     
     public void modifyBook (){
         /*
         * The modified data is retrieved and stored in local variables
         */
         book = title.getText();
         Copiesdetails= numberCopiesText.getText();
         ShelfNumberdetails= shelfNumberText.getText();
         authordetails=    authorDetails.getText();
        yeardetails= yearText.getText();
        publisherdetails = publisherText.getText();
        pagesdetails =numberOfPagesText.getText();
        BookIDdetails =numberText.getText();
       
        int idNumber = Integer.parseInt(idDetails);
        int finalNumber = idNumber;
     try{
         /*
         * Connection is established
         */
         connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,ShelfNumber,Available FROM BookDetails");
               
			while (resultSet.next()) {
                            /*
                             * If bookId equals the book Id in database
                             * The row is updated
                            */
                            String BookID = resultSet.getString("BookID");
                                
                              if (BookIDdetails.equals(BookID)){ 
                                  resultSet.updateString("BookTitle", book);
                                  resultSet.updateString("Author", authordetails);
                                  resultSet.updateString("Year", yeardetails);
                                  resultSet.updateString("Publisher", publisherdetails);
                                  resultSet.updateString("Pages",pagesdetails);
                                  resultSet.updateString("BookID", BookIDdetails);
                                  resultSet.updateString("Copies", Copiesdetails);
                                  resultSet.updateString("ShelfNumber", ShelfNumberdetails);
                                  
                                  resultSet.updateRow();
                                         
     }
                              
                        }
                        /*
                        resultSet.close();
                        statement.close();
                        connection.close();
                                */
                        //JOptionPane.showMessageDialog(null, "Successfully Modified " + book);
                        
                              new manageBooks();
                              modifyFrame.dispose();
} catch (Exception e){
    System.out.println (e);
}
     
     }
}
