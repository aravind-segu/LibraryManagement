
package librarymanagement;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class deletebooks extends LibraryManagement implements ActionListener{
    /*
    * A local variable is created for each column in the bookDetails table
    */
    JFrame manageaFrame;
    int rowLocal;
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
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    boolean checked = false;
    
    public deletebooks(){
        /*
        * the deletebook method is called
        */
        deletebook();
    }
    public void deletebook (){
        /*
        * The row selected by user is inputted
        * All the details in that row are inputted and stored in local variables
        */
        rowLocal = manageBooks.row;
        
        idDetails = manageBooks.data[rowLocal][0] ;
        book = manageBooks.data[rowLocal][1];
        authordetails = manageBooks.data[rowLocal][2];
        yeardetails = manageBooks.data[rowLocal][3];
        publisherdetails = manageBooks.data[rowLocal][4];
        pagesdetails = manageBooks.data[rowLocal][5];
        BookIDdetails = manageBooks.data[rowLocal][6];
        Copiesdetails =manageBooks.data[rowLocal][7];
        ShelfNumberdetails = manageBooks.data[rowLocal][8];
        Availabledetails = manageBooks.data[rowLocal][9];
        try {
                            
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
               
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                String lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                String btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                
                                if (BookIDdetails.equals(bID)){
                                                                           
                                       checked = true;
                                        
                                                               
                                }
                                
                        }
                                resultSet.close();
                                statement.close();
                                connection.close();   
                                
          if (checked == false){                      
            /*
            * Connection is established with BookDetails
            */
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,ShelfNumber,Available FROM BookDetails");
                        /*
                        * Each row is iterated
                            * If the bookId in the database is equal to selected row book id
                                * The book title in the database is equal to the selected bookTitle
                                    * that row is deleted
                                    * Success message is printed
                        * All connections are closed
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
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                
                                if (BookIDdetails.equals(BookID)){
                                    if (book.equals(bookInput)){
                                        
                                        resultSet.deleteRow();
                                        
                                        break;
                                    }
                                }
                             
    }
                           resultSet.close();
                                statement.close();
                                connection.close();
                                
                            /*
                                * That bookiD is searched in memCheckouts tables
                                * And that specific row is deleted
                                * Connections are closed
                                * Manage Books is called
                      */
                                manageaFrame = new JFrame ();
        manageaFrame.setUndecorated(true);
        manageaFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageaFrame.setTitle ("Delete Books");
        manageaFrame.setSize (150,100);
        manageaFrame.setVisible(true);
        manageaFrame.setLocationRelativeTo(null);
        Container contentPane = manageaFrame.getContentPane();
        JPanel myPanel = new JPanel();
        JLabel mes = new JLabel ("Successfully Deleted");
        JButton back = new JButton("Back");
       back.addActionListener(new ActionListener() {
           
     public void actionPerformed (ActionEvent ae){
       String event = ae.getActionCommand();
        
        if (event.equals ("Back")){
            try {
                new manageBooks ();
                manageaFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
                
        }
                }
       });
       myPanel.add(mes);
       myPanel.add(back);
       contentPane.add(myPanel);
       manageaFrame.validate();
                                
          }
          else {
        manageaFrame = new JFrame ();
        manageaFrame.setUndecorated(true);
        manageaFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageaFrame.setTitle ("Delete Books");
        manageaFrame.setSize (700,100);
        manageaFrame.setVisible(true);
        manageaFrame.setLocationRelativeTo(null);
        Container contentPane = manageaFrame.getContentPane();
        JPanel myPanel = new JPanel();
        JLabel mes = new JLabel ("This Book has been checked out by other users. Please Check-in this book before Deleting.");
        JButton back = new JButton("Back");
       back.addActionListener(new ActionListener() {
           
     public void actionPerformed (ActionEvent ae){
       String event = ae.getActionCommand();
        
        if (event.equals ("Back")){
            try {
                new manageBooks ();
                manageaFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
                
        }
                }
       });
       myPanel.add(mes);
       myPanel.add(back);
       contentPane.add(myPanel);
       manageaFrame.validate();
          }
        }catch (Exception a){
            System.out.println (a);
        }
}
}
