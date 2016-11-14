
package librarymanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import static librarymanagement.LibraryManagement.frame;
import static librarymanagement.login.nameEntry;

public class addBooks extends LibraryManagement implements ActionListener{
    /*
    * The connections are established using connection, statement and resultSet
    * A text field is created for each of the information required:
        * Title, #copies, #shelf, #year, Publisher, Aughor, #numberID
    * 4 booleans are defined false for future checking purposes
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    static boolean newUser;
    static boolean loginCorrect = false;
    static JTextField title;
    static JTextField numberCopiesText;
    static JTextField shelfNumberText;
    static JTextField authorDetails;
    static JTextField yearText;
    static JTextField publisherText;
    static JTextField numberOfPagesText;
    static JTextField numberText;
    boolean copiestext = false;
    boolean shelfText = false;
    boolean year = false;
    boolean pages = false;
    Container contentPane;
    JPanel myPanel;
    JFrame addBooksframe;
    int bkID;
    int countid;
    addBooks (){
        // The addBooks method is called
        addBooksGui ();
    }
    public void addBooksGui (){
        
        /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
        addBooksframe = new JFrame ();
        addBooksframe.setUndecorated(true);
        addBooksframe.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        addBooksframe.setTitle ("Add Books");
        addBooksframe.setSize (400,600);
        addBooksframe.setVisible(true);
        addBooksframe.setLocationRelativeTo(null);
      
        
        /*
        * The main Panel is initialized
        * It is set to Border Layout
        * Secondary pannels of top and info are created
        */    
        contentPane = addBooksframe.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
        
        /*
        * At 0,0 in the top Panel the title of Add Books is added
        * At 0,2 in the top Panel the instructions are added
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Add Books");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the information of the Book and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
        
        /*
        * The info is given a GridBag Layout
        * At 0,0 the Book Title Label and at 0,1 the subsequent text box is placed.
        * At 1,0 the Author Label and at 1,1 the subsequent text box is placed.
        * At 2,0 the Year Label and at 2,1 the subsequent text box is placed.
        * At 3,0 the Publisher Label and at 3,1 the subsequent text box is placed.
        * At 4,0 the Number of Pages Label and at 4,1 the subsequent text box is placed.
        * At 5,0 the Number of Copies Label and at 5,1 the subsequent text box is placed.
        * At 6,0 the Shelf Number Label and at 6,1 the subsequent text box is placed.
        * At 7,1 the button, Add Book is placed
        * At 8,1 the Back Button is placed
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
        info.add (title , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel author = new JLabel ("Author:");
        info.add (author , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        authorDetails = new JTextField (20);
        info.add (authorDetails , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel year = new JLabel ("Year:");
        info.add(year,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        yearText = new JTextField (20);
        info.add(yearText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel publisher  = new JLabel ("Publisher:");
        info.add(publisher,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        publisherText = new JTextField (20);
        info.add(publisherText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel numberOfPages  = new JLabel ("Number Of Pages:");
        info.add(numberOfPages,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        numberOfPagesText = new JTextField (20);
        info.add(numberOfPagesText,gbc);
                
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel numberCopies  = new JLabel ("Number of Copies: ");
        info.add(numberCopies,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        numberCopiesText = new JTextField (20);
        info.add(numberCopiesText,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel shelfNumber  = new JLabel ("Shelf Number: ");
        info.add(shelfNumber,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        shelfNumberText = new JTextField (20);
        info.add(shelfNumberText,gbc);
        
        
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Add Book");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
            
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       addBooksframe.validate ();
    }
    
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        
        /*
        * If the even is equal to Back
        * The MainWindow is called
        * The current Frame is disposed
        */
        if (event.equals ("Back To Main")){
            try {
               new MainWindow ();
               addBooksframe.dispose();
            } catch (Exception f) {
                System.out.println("Sorry, Error Occured");
            }
        }
       if (event.equals ("Add Book")){
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
             new addBooks();
             addBooksframe.dispose();
         }
            } catch (Exception f) {
                System.out.println(" Sorry, Error Occured");
            }
        }
    }
    public void check (){
        
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
              new addBooks();
              addBooksframe.dispose();
         }
         if (authorBook.equals("")){
              JOptionPane.showMessageDialog(null, "Author Left Blank. Please Try Again.");
              error = true;
              new addBooks();
              addBooksframe.dispose();
         }
         if (publisherBook.equals("")){
              JOptionPane.showMessageDialog(null, "Publisher Left Blank Left Blank. Please Try Again.");
              new addBooks();
              error = true;
              addBooksframe.dispose();
         }
         if (error == false){
         bkid();
         }
    }
    public void bkid(){
        /*
        * An id and BookID string is declared
        * Connection is established with the BookDetails table in the database
        * The last BookID is retrieved as string, turned into an integer and is stored into bkID
            * The bkID is increased by one
        * All connections are closed
        * The add method is called
        */
         String id = "";
        try{
            String BookID = "";
        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT * FROM UserDetails WHERE Username = " + Username);
                        resultSet = statement.executeQuery("SELECT ID,BookID FROM BookDetails ORDER BY BookID");
			while (resultSet.next()) {
                                id = resultSet.getString("ID");
                               
                                BookID = resultSet.getString("BookID");
                               
                        }
                        countid = Integer.parseInt(id);
                        countid++;
                        bkID = Integer.parseInt(BookID);
                        bkID++;
                        resultSet.close();
			statement.close();
			connection.close();
                        
                        add();
		}
		catch (SQLException SQLe) {
			System.out.println("UserDetails.java\n" + SQLe.toString());
		}
    }
    
    public void add (){
        /*
        * All data entered in each textbox is stored in string variables
        * the bkid and countid integers are changed into strings
        * Connection is established
        * The data is added into the table BookDetails
        * Connection is closed
        * A success Message is shown
        * All text boxes are cleared
        * The constructor is called
        * The current frame is disposed
        */
    String titleBook = title.getText();  
    String CopiesBook = numberCopiesText.getText();
    String shelfBook = shelfNumberText.getText();
    String authorBook = authorDetails.getText();
    String yearBook = yearText.getText();
    String publisherBook = publisherText.getText();
    String pagesBook = numberOfPagesText.getText();
    String numberBook = String.valueOf(bkID);
    String numbercount = String.valueOf(countid);
    String copieschecked = "0";
    String sql = "INSERT INTO BookDetails" 
               + "( ID, BookTitle, Author, Year, Publisher, Pages, BookID, Copies,CopiesChecked,ShelfNumber ) VALUES" 
               + "(?,?,?,?,?,?,?,?,?,?)";
    
    try{
                                        Connection con=DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
                                        PreparedStatement ps =  con.prepareStatement(sql);
                                        ps.setString(1, numbercount);
                                        ps.setString(2, titleBook);
                                        ps.setString(3, authorBook);
                                        ps.setString(4, yearBook);
                                        ps.setString(5, publisherBook);
                                        ps.setString(6, pagesBook);
                                        ps.setString(7, numberBook);
                                        ps.setString(8, CopiesBook);
                                        ps.setString(9, copieschecked);
                                        ps.setString(10, shelfBook);
                                        ps.executeUpdate();      
                                        
                                        ps.close();
                                        con.close();
                                        
                                }catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, ex.toString());
                                }
    
           
    String message = titleBook + "Successfully Added";
    JOptionPane.showMessageDialog(null, message);
    
    title.setText("");
    numberCopiesText.setText("");
    shelfNumberText.setText("");
    authorDetails.setText("");
    yearText.setText("");
    publisherText.setText("");
    numberOfPagesText.setText("");
 
    
   
    
    }
}
