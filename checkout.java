
package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

public class checkout extends LibraryManagement implements ActionListener{
    
    /*
    * Connectfion is established to sql
    * a text field is created for each information
    * JFrame is initialized for GUI,
    * The date format is set and the current date is created with this format
    */
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    JFrame checkoutFrame;
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
    public checkout (){
        /*
        * The checkoutBook method is called
        */
        checkoutBook();
    }
    public void checkoutBook(){
        /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
       
        checkoutFrame = new JFrame();
        checkoutFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        checkoutFrame.setTitle ("Check-Out");
        checkoutFrame.setSize (400,600);
        checkoutFrame.setVisible(true);
        checkoutFrame.setLocationRelativeTo(null);
        contentPane = checkoutFrame.getContentPane();
        /*
         * Thre panels are defined, bottom, top and info
         * The mainPanel is instantiated and is given border Layot
         */
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
        JLabel welcome = new JLabel ("Checkout");
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
        * At (0,4) the current date is printed and this is not editable
        * At (0,5) the due date of the book is printed which is 3 weeks fromt the current date
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
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel year = new JLabel ("Date");
        info.add(year,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        date = new JTextField (20);
        date.setText(todayDate);
        date.setEditable(false);
        info.add(date,gbc);
        
        Date current = new Date();
        Date increment = DateUtils.addDays(current, 21);
        String reportDate = currentdate.format(increment);
       
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel publisher  = new JLabel ("Due Date:");
        info.add(publisher,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        duedate = new JTextField (20);
        duedate.setText(reportDate);
        duedate.setEditable(false);
        info.add(duedate,gbc);
        
                
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Check-Out");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
            
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       checkoutFrame.validate ();
                
    }
    @Override
 public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * when the check-out button is pressed the check method is called
        */
        if (event.equals ("Check-Out")){
            try {
                check();
                checkoutFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        /*
        * If the Back button is pressed the user is taken to the Main Window. 
        */
           if (event.equals ("Back To Main")){
            try {
                new MainWindow();
                checkoutFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
}
 public void check (){
     try {
          /*
         * The info entered in textboxes is retrieved and is stored in temporary string variables
         * Connection is established with BookDetails table from the database
         */
         String idInput = book.getText();
         String idmem = name.getText();
 
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (/*resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE*/);
			
                        resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,CopiesChecked,ShelfNumber,Available FROM BookDetails");
                        /*
                         * Each row in the table is iterated through
                         * Each data in the column is fetched and it is stored in temporary string variables
                         * if the BookID equals the id inputted
                            * the foundbook variable is declared true   
                            * the loop is broken
                        * All Connections are closed
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String bookInput = resultSet.getString("BookTitle");
                                String author = resultSet.getString("Author");
                                String year = resultSet.getString("Year");
                                String publisher = resultSet.getString("Publisher");
                                String pages = resultSet.getString("Pages");
                                String BookID = resultSet.getString("BookID");
                                String Copies = resultSet.getString("Copies");
                                String copiesrem1 = resultSet.getString ("CopiesChecked");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                
                                if (BookID.equals(idInput)){
                                foundbook = true;
                                break;
                                }
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        
                        /*
                        * Connection is made with MemberDetails tables in the database
                        */
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
                        
                        /*
                        * The MemberDetails table is iterated by row and data in each column is stored
                        * If the memberid entered by the librarian is equal to the database
                            * The foundmem variable is delared true
                        * All connections are closed
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                first = resultSet.getString("FirstName");
                                String last = resultSet.getString("LastName");
                                String email = resultSet.getString("Email");
                                String user = resultSet.getString("Username");
                                String pass = resultSet.getString("Password");
                                String idDetails = resultSet.getString ("MemberID");
                                
                               if ((idmem.equals (idDetails))){
                                   foundmem = true;
                                   }
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        /*
                        * If both variables are true,the book method is called
                        * If foundmem is true and foundbook is false
                            * BookID not found is printed
                            * A checkout object is created
                            * The current frame is disposed
                        * If foundmem is false and foundbook is true
                            * MemberID not found is printed
                            * A checkout object is created
                            * The current frame is disposed
                        * If foundmem is false and foundbook is false
                            * BookID or MemberID not found is printed
                            * A checkout object is created
                            * The current frame is disposed
                        */
                        if (foundmem == true & foundbook == true){
                            book();
                        }
                          if (foundmem == true & foundbook == false){
                            JOptionPane.showMessageDialog(null, "BOOKID: " + idInput + " not found." );
                            new checkout ();
                            checkoutFrame.dispose();
                        }
                        if (foundmem == false & foundbook == true){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idmem + " not found." );
                            new checkout ();
                            checkoutFrame.dispose();
                        }
                        if (foundmem == false & foundbook == false){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idmem + " not found. \n BookID: " + idInput + " not found." );
                            new checkout();
                            checkoutFrame.dispose();
                        }
     }catch (Exception a){
         System.out.println (a);
     }
 }
 public void book (){
     try{
         /*
         * The bookid entered by the user is retrieved and is stored in local variables
         */
         String idInput = book.getText();
         
                        /*
         * Connection is established with Book Deatils table
         */    
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,CopiesChecked,ShelfNumber,Available FROM BookDetails");
                        /*
                         * Each row in the table is iterated through
                         * Each data in the column is fetched and it is stored in temporary string variables
                         * if the BookID equals the id inputted
                            * the string copies is changed to int copies  
                            * the string copies remaining is also changed to integer and increased by one
                            * if copies remaining is greater than copies
                                * Book not available is printed
                            * if copies checked-out is equal to copies
                                * The data in the specific row is updated
                            * else
                                * The data in the specific row is updated
                        * All Connections are closed
                        */
			while (resultSet.next()) {
                            
                                int id = resultSet.getInt("ID");
                                
                                String bookInput = resultSet.getString("BookTitle");
                                String author = resultSet.getString("Author");
                                String year = resultSet.getString("Year");
                                String publisher = resultSet.getString("Publisher");
                                String pages = resultSet.getString("Pages");
                                String BookID = resultSet.getString("BookID");
                                String Copies = resultSet.getString("Copies");
                                String copiesrem1 = resultSet.getString ("CopiesChecked");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                
                                String Yes = "Yes";
                                String No = "No";
                                
                                if (BookID.equals(idInput)){
                                    int copies = Integer.parseInt (Copies);
                                copiesrem = Integer.parseInt(copiesrem1);
                                copiesrem = copiesrem + 1;
                                String Copiesdetails = String.valueOf(copiesrem);
                                            if (copiesrem > copies){
                                                JOptionPane.showMessageDialog(null, "BOOK: " + bookInput + " is not Available" );
                                                foundbook = false;
                                                new checkout();
                                                checkoutFrame.dispose();
                                                break;
                                            }
                                            
                                            if (Copiesdetails.equals(Copies)){
                                                resultSet.updateString("BookTitle", bookInput);
                                                resultSet.updateString("Author", author);
                                                resultSet.updateString("Year", year);
                                                resultSet.updateString("Publisher", publisher);
                                                resultSet.updateString("Pages",pages);
                                                resultSet.updateString("BookID", BookID);
                                                resultSet.updateString("Copies", Copies);
                                                resultSet.updateString("CopiesChecked", Copiesdetails);
                                                resultSet.updateString("ShelfNumber", ShelfNumber);
                                                resultSet.updateString("Available", No);
                                                resultSet.updateRow();
                                                foundbook = true;
                                                booktitle = bookInput;
                                                
                                                //JOptionPane.showMessageDialog(null, "BOOK: " + bookInput + "Successfully Checked Out \n Copies Remaining = 0 " );
                                                break;
                                            }
                                            
                                  resultSet.updateString("BookTitle", bookInput);
                                  resultSet.updateString("Author", author);
                                  resultSet.updateString("Year", year);
                                  resultSet.updateString("Publisher", publisher);
                                  resultSet.updateString("Pages",pages);
                                  resultSet.updateString("BookID", BookID);
                                  resultSet.updateString("Copies", Copies);
                                  resultSet.updateString("CopiesChecked", Copiesdetails);
                                  resultSet.updateString("ShelfNumber", ShelfNumber);
                                  resultSet.updateString("Available", Yes);
                                  resultSet.updateRow();
                                  foundbook  = true;
                                  booktitle = bookInput;
                                  
                                  //JOptionPane.showMessageDialog(null, "BOOK: " + bookInput + " successfully Checked Out \n Copies Remaining = " + copiesrem );
                                break;
             }
                                
                                 
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        
                        if (foundbook == true){
                            member();
                        }
                        /*
                        if (foundbook == false){
                            JOptionPane.showMessageDialog(null, "Sorry BookID not found");
                                 new MainWindow();
                                                checkoutFrame.dispose();
                        }
                                */
                        
     }catch (Exception exa){
                     System.out.println (exa);
                     }
}
 public void member (){
     try{
         /*
         * All the data entered by user is stored in local variables
         */
         String idInput = name.getText();
         String bookID = book.getText();
         String dateb = date.getText();
         String rdate = duedate.getText();
         String renew = "0";
         String first = "";
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                first = resultSet.getString("FirstName");
                                String last = resultSet.getString("LastName");
                                String email = resultSet.getString("Email");
                                String user = resultSet.getString("Username");
                                String pass = resultSet.getString("Password");
                                String idDetails = resultSet.getString ("MemberID");
                                
                               if ((idInput.equals (idDetails))& foundbook == true){
                                    /*
                                   * The memCheckouts table is entered
                                   * The information is entered into mem checkouts
                                   * The row is updated
                                   * connections are closed
                                   * Success message is printed
                                   */                                 
                                    String sql = "INSERT INTO memCheckouts" 
                                    + "(MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed) VALUES" 
                                    + "(?,?,?,?,?,?,?)";
                                   
                                   Connection con=DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
                                        PreparedStatement ps =con.prepareStatement(sql);
                                        ps.setString(1, idInput);
                                        ps.setString(2, last);
                                        ps.setString(3, bookID);
                                        ps.setString(4, booktitle);
                                        ps.setString(5, dateb);
                                        ps.setString(6, rdate);
                                        ps.setString(7, renew);
                                        ps.executeUpdate();      
                                        ps.close();
                                        con.close();
                                        foundmem = true;
                                        break;
                               }
                                   
                                
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        if (foundmem == true & foundbook == true){
                            JOptionPane.showMessageDialog(null, "Book " + booktitle + " successfully checked out to " + first + "\n Total Copies Checked-Out = " + copiesrem );
                            new checkout();
                            checkoutFrame.dispose();
                        }
                        /*
                        if (foundmem == true & foundbook == false){
                            JOptionPane.showMessageDialog(null, "BOOKID: " + bookID + " not found." );
                            new MainWindow ();
                            checkoutFrame.dispose();
                        }
                        if (foundmem == false & foundbook == true){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idInput + " not found." );
                            new MainWindow ();
                            checkoutFrame.dispose();
                        }
                        if (foundmem == false & foundbook == false){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idInput + " not found. \n BookID: " + bookID + " not found." );
                            new MainWindow ();
                            checkoutFrame.dispose();
                        }
                                */
     }catch (Exception e){
         System.out.println (e);
     }
         
         
 }
     
 }
