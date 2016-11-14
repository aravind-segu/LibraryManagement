package librarymanagement;


import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LibraryManagement extends JFrame implements ActionListener  {
    
    static Connection connect;
    static Statement statement;
    static ResultSet result;
    static JFrame frame;
    
    
    public LibraryManagement (){
        
            /*
            * A JMenuItem is defined but not initialized
                * This is for the ease of the number of items that will be defined later
            * A JMenuBar is defined and initialized by making an object called menuBar
                * The menuBar is set
            */
       
            JMenuItem item;
            
            JMenuBar  menuBar = new JMenuBar();
            setJMenuBar (menuBar);
            /*
            * The first JMenu is file
                * file is added to menuBar
            * the item defined earlier is now initialized to "Log-Out"
            * the item defined earlier is now initialized to "Exit"
                  * The actionListener is added to all the items and all items are added to the file menu
            */
        
            JMenu  file = new JMenu ("File");
            menuBar.add(file);
            
                   item = new JMenuItem ("Log-Out");
                item.addActionListener (this);
                file.add (item);
                
                item = new JMenuItem ("Exit");
                item.addActionListener (this);
                file.add (item);
                
                
            /*
            * The next JMenu is Books
                * Books is added to menuBar
            * the item defined earlier is now initialized to "Add Books"
            * the item defined earlier is now initialized to "Manage Books"
            * the item defined earlier is now initialized to "Renew Books"
                * The actionListener is added to all the items and all items are added to the edit menu
            */    
        
            JMenu Books = new JMenu ("Books");
            menuBar.add (Books);
            
                item = new JMenuItem ("Add Books");
                item.addActionListener (this);
                Books.add (item);
                
                item = new JMenuItem ("Manage Books");
                item.addActionListener (this);
                Books.add (item);
                
                item = new JMenuItem ("Renew Books");
                item.addActionListener (this);
                Books.add (item);
              /*
            * The next JMenu is Members
                * Books is added to menuBar
            * the item defined earlier is now initialized to "Add Members"
            * the item defined earlier is now initialized to "Manage Members"
                * The actionListener is added to all the items and all items are added to the edit menu
            */
        
            JMenu Members = new JMenu ("Members");
            menuBar.add (Members);
            
                item = new JMenuItem ("Add Members");
                item.addActionListener (this);
                Members.add (item);
                
                item = new JMenuItem ("Manage Members");
                item.addActionListener (this);
                Members.add (item);
            /*
            * The next JMenu is Search
                * search is added to menuBar
            * the item defined earlier is now initialized to "Search Catalogue"
                * The actionListener is added to all the items and all items are added to the sort menu
            */  
        
            JMenu search = new JMenu ("Search");
            menuBar.add (search);
            
                item = new JMenuItem ("Search Catalogue");
                item.addActionListener (this);
                search.add (item);
                              
            /*
            * The last JMenu is Loan
                * Loan is added to menuBar
            * the item defined earlier is now initialized to "Checkout-Books"
            * the item defined earlier is now initialized to "Return-Books"
            */    
        
            JMenu Loan = new JMenu ("Loan");
            menuBar.add (Loan);
            
                item = new JMenuItem ("Checkout-Books");
                item.addActionListener (this);
                Loan.add (item);
                
                item = new JMenuItem ("Return-Books");
                item.addActionListener (this);
                Loan.add (item);
                
                JMenu Help = new JMenu ("Help");
                menuBar.add (Help);
            
                item = new JMenuItem ("Help File");
                item.addActionListener (this);
                Help.add (item);
                
               
                
                         
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String event = e.getActionCommand();
            // A switch loop is started with the parameter as event
            switch (event){
               
                /*
                * For each option created in the menu bar the appropriate object of the class is created by calling the constructor
                * When each object is created the mainWindow is disposed
                * If an error occurs an error message is printed. 
                */
                
                case "Add Books": {
                    try{
                     new addBooks();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Manage Books": {
                    try{
                     new manageBooks();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Add Members": {
                    try{
                     new addMembers();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Manage Members": {
                    try{
                     new manageMembers();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Checkout-Books": {
                    try{
                     new checkout();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Return-Books": {
                    try{
                     new checkin();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                case "Search Catalogue": {
                    try{
                     new search();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                 case "Exit": {
                    try{
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                 case "Log-Out": {
                    try{
                        new login();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                 case "Renew Books": {
                    try{
                        new renew();
                     MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
                  case "Help File": {
                    try{
                        new help();
                    // MainWindow.window.dispose();
                     break;
                        }catch (Exception x){
                            System.out.println ("Error in Calling");
                        }
                  }
            default: System.out.println ("Error in Switch. Sorry!");
            }
    }
    
    


    public static void main(String[] args) {
        /*
        * The login object created, intiating the program.
        */
        
        new login ();
        
 
}

         
}
