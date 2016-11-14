
package librarymanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author mallikarjuna
 */
public class MainWindow extends LibraryManagement implements ActionListener {
   
JMenuItem item;
static LibraryManagement window = new LibraryManagement();  
    
    public MainWindow (){
   
            /*
        * A window is created and is centered
        */
        
           
        window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        window.setTitle ("Library Management");
        window.setSize (800,600);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
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
                
                
     
                
}
    

}
