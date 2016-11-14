/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;
import javax.swing.*;
 import java.awt.*;
 import java.sql.*;
 import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class Publishersearch extends LibraryManagement implements ActionListener{
    /*
    * The Connection, Statement and Result are predifined for the future sql statements
    * A frame, manageFrame is defined for future GUI
    * An ArrayList is created for each details the program stores for the books
    */
     Connection connection;
    Statement statement;
    ResultSet resultSet;
    JFrame manageFrame;
    int row;
    int size;
    boolean found = false;
    static ArrayList <String> bookArray = new ArrayList<> ();
    static ArrayList <String> authorArray = new ArrayList<> ();
    static ArrayList <String> yearArray = new ArrayList<> ();
    static ArrayList <String> publisherArray = new ArrayList<> ();
    static ArrayList <String> pagesArray = new ArrayList<> ();
    static ArrayList <String> BookIDArray = new ArrayList<> ();
    static ArrayList <String> CopiesArray = new ArrayList<> ();
    static ArrayList <String> CopiesRemArray = new ArrayList<> ();
    static ArrayList <String> ShelfNumberArray = new ArrayList<> ();
    static ArrayList <String> AvailableArray = new ArrayList<> ();
    static ArrayList <String> Numbers = new ArrayList <>();
    String [][] data;
    
    public Publishersearch (){
        /*
        * The GUI method is called
        */
        GUI();
    }
     public void GUI(){
    try {
        bookArray.clear();
                                authorArray.clear();
                                yearArray.clear();
                                publisherArray.clear();
                                pagesArray .clear();
                                BookIDArray.clear();
                                CopiesArray.clear();
                                CopiesRemArray.clear();
                                ShelfNumberArray.clear();
                                AvailableArray.clear();
                                Numbers.clear();
                                int idfinal = 1;
                                
         /*
        * A String inputField is defined and data entered by the user in the search command is stored in this string
        * The Connection with the database is established
        * resultSet contains sql to allow the program to retrieve all data, row by row from BookDetails
        */
             String inputField = search.input.getText();
             connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,CopiesChecked,ShelfNumber,Available FROM BookDetails");
                        /*
                        * The while loop iterates through each row of Table, BookDetails
                             * All data from each column is retrieved and stored in seperate strings
                             * An if loop determines if the input Text of user matches the Publisher of the data
                                    * If found the data from each column is added to its specific ArrayList
                                    * boolean found is made true
                        * All connections are closed
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String book = resultSet.getString("BookTitle");
                                String author = resultSet.getString("Author");
                                String year = resultSet.getString("Year");
                                String publisher = resultSet.getString("Publisher");
                                String pages = resultSet.getString("Pages");
                                String BookID = resultSet.getString("BookID");
                                String Copies = resultSet.getString("Copies");
                                String CopiesRem = resultSet.getString ("CopiesChecked");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                        
                                String publisherl = publisher.toLowerCase();
                                String inputl = inputField.toLowerCase();
                        if (publisherl.contains(inputl)){
                            
                             int cop = Integer.parseInt(CopiesRem);
                                int copi = Integer.parseInt(Copies);
                                 String finalid = String.valueOf(idfinal);
                            if (cop == copi){
                                bookArray.add (book);
                                authorArray.add (author);
                                yearArray.add (year);
                                publisherArray.add (publisher);
                                pagesArray .add (pages);
                                BookIDArray.add (BookID);
                                CopiesArray.add (Copies);
                                CopiesRemArray.add (CopiesRem);
                                ShelfNumberArray.add (ShelfNumber);
                                AvailableArray.add ("No");
                                }
                                else{
                                bookArray.add (book);
                                authorArray.add (author);
                                yearArray.add (year);
                                publisherArray.add (publisher);
                                pagesArray .add (pages);
                                BookIDArray.add (BookID);
                                CopiesArray.add (Copies);
                                CopiesRemArray.add(CopiesRem);
                                ShelfNumberArray.add (ShelfNumber);
                                AvailableArray.add ("Yes");
                                }
                                Numbers.add(finalid);
                                idfinal++;
                                found = true;
                        }
                        }
                        
                            
                        resultSet.close();
                        statement.close();
                        connection.close();
    /*
                        *If found is true
                            * All the data in the individual arrayList is added to the 2D data Array
    */  
   if (found == true){
          data = new String [bookArray.size()][11];
          
      for (int i = 0; i < bookArray.size(); i++){
            String book = Numbers.get(i);
            data [i][0] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = bookArray.get(i);
            data [i][1] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = authorArray.get(i);
            data [i][2] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = yearArray.get(i);
            data [i][3] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = publisherArray.get(i);
            data [i][4] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = pagesArray.get(i);
            data [i][5] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = BookIDArray.get(i);
            data [i][6] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = CopiesArray.get(i);
            data [i][7] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = CopiesRemArray.get(i);
            data [i][8] = book;
        }
        for (int i =0; i < bookArray.size(); i++){
            String book = ShelfNumberArray.get(i);
            data [i][9] = book;
        }
          for (int i =0; i < bookArray.size(); i++){
            String book = AvailableArray.get(i);
            data [i][10] = book;
        }
              /*
            * All Column Names are listed for output
            *  The frame is initialized
            * The Close option is removed
            * Title is set
            * Size is set to 400x400
            * visibility defined to be true 
            * It is placed at the center of the screen
          */           
        String [] columnNames = {"Number","Title","Author","Year","Publisher","Pages","BookID","Copies","Copies Checked-Out","ShelfNumber","Availablity"};
        size = 200 + (bookArray.size()*9);
        manageFrame = new JFrame ();
        manageFrame.setUndecorated(true);
        manageFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageFrame.setTitle ("Search Result");
        manageFrame.setSize (1250,size);
        manageFrame.setVisible(true);
        manageFrame.setLocationRelativeTo(null);
        Container contentPane = manageFrame.getContentPane();
        /*
        * A main panel is declared
        * A bottom is declared and a back button is added
        * The bottom panel is added to main panel
        */
        JPanel myPanel = new JPanel();
        JPanel bottom = new JPanel();
        JButton back = new JButton ("Back");
       back.addActionListener (this);
       bottom.add(back, BorderLayout.CENTER);
       myPanel.add(bottom, BorderLayout.CENTER);
           //A table is created and a mouseClick event is created
       JTable table = new JTable(data, columnNames);
       	table.addMouseListener (new MouseAdapter ()
		{
		    public void mouseClicked (MouseEvent e)
		    {
				 row = table.getSelectedRow ();
                         
		   }
		}
		);
        
        
        /*
        * Specific column widths are specified
        * The table is printed
        */
        int col [] = {80,150,150,50,150,50,100,80,100,100,80};
        int x = table.getColumnCount()-1;
        TableColumn col0 = null;
        table.setPreferredScrollableViewportSize(new Dimension (1025,size));
        for (int i =0; i < 11 ; i++){
        col0 = table.getColumnModel().getColumn(i);
        col0.setPreferredWidth (col [i]);
        
        }
        JScrollPane scroll = new JScrollPane (table);
       
        
       myPanel.add(scroll);
       contentPane.add(myPanel);
       manageFrame.validate();
          /*
       * If the boolean found is false, a ID not Found message is printed
       */
                        }else {JOptionPane.showMessageDialog(null, "Publisher not found" );
                        new search ();}
         }catch (Exception e){
             System.out.println (e);
         }
     }
    public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        
       /*
        * If the Back button is clicked a new Search window is opened and the current frame is disposed. 
        */
          if (event.equals ("Back")){
            try {
                new search ();
                manageFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
       
     
    }
}
