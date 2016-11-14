
package librarymanagement;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.table.TableColumn;

public class manageBooks extends LibraryManagement implements ActionListener {
    
    /*
    * The variables required for connection are established
    * An arrayList is created for every row in teh BookDetails tables
    * A two dimensional array of data is called
    * The manageFrame JFrame is defined
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
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
    static int counter;
    static int row;
    static int size;
    static String [][] data;
    static JFrame manageFrame;
    public manageBooks (){
        ArrayInput();
    }
    
    public void ArrayInput (){
        try {
            /*
            * The Array List is cleared
            * Counter is given one
            */
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
                                counter = 1;
                                /*
                                * Connection is established from BookDetails
                                */
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,BookTitle,Author,Year,Publisher,Pages,BookID,Copies,CopiesChecked,ShelfNumber,Available FROM BookDetails ORDER BY BookID");
			/*
                        * Each row is iterated
                        * The data in each column is stored in local variables
                        * the copies and copies remaining are turned to integers
                        * If the copies checked out is equal to copies 
                            * the local variables are added to arrayLists  
                        else
                            * The local variables are added to arrayLists with available as no
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
                                String CopiesRem = resultSet.getString("CopiesChecked");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                int cop = Integer.parseInt(CopiesRem);
                                int copi = Integer.parseInt(Copies);
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
                                
                                String count = String.valueOf(counter);
                                Numbers.add(count);
                                
                                counter++;
                                
                               
    }
                        resultSet.close();
			statement.close();
			connection.close();
                        data = new String [bookArray.size()][11];
                        GUI();
    
} catch (Exception e){
    System.out.println (e);
}
    }
    
    public void GUI (){
       
       /*
        * Each arrayList is added to the two dimensional array data
        */ 
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
        size = 200 + (bookArray.size()*9);
        String [] columnNames = {"Number","Title","Author","Year","Publisher","Pages","BookID","Copies","Copies Checked-Out","ShelfNumber","Availablity"};
         /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
        manageFrame = new JFrame ();
        manageFrame.setUndecorated(true);
        manageFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageFrame.setTitle ("Manage Books");
        manageFrame.setSize (1350,size);
        manageFrame.setVisible(true);
        manageFrame.setLocationRelativeTo(null);
        Container contentPane = manageFrame.getContentPane();
         /*
        * Two Panels are defined main and bottom
        */       
        JPanel myPanel = new JPanel ();
        JPanel bottom = new JPanel ();
        
        /*
        * Modify button is added to the bottom panel
        */
       JButton button = new JButton ("Modify");
       button.addActionListener (this);
       bottom.add(button, BorderLayout.WEST);
       
       /*
       * A button called Delete is added
       * The button is given actionListener
       * It is added to the Bottom Panel at Position East
       */
       JButton delbutton = new JButton ("Delete");
       delbutton.addActionListener (this);
       bottom.add(delbutton, BorderLayout.EAST);
       /*
       * Back button is added to bottom panel
       */
       JButton back = new JButton ("Back To Main");
       back.addActionListener (this);
       bottom.add(back, BorderLayout.CENTER);
       /*
       * The bottom is added to myPanel
       */
       myPanel.add(bottom, BorderLayout.CENTER);
        /*
       * A table is created
       */
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
        * The columns are given specific widths
        * These widths are given to each column
        * A scroll Pane is addde
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
}
     @Override
    public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If the modify button is pressed the modify object is made
        */
        if (event.equals ("Modify")){
            try {
                new modify ();
                manageFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        /*
        * If the back button is pressed the mainWindow object is made
        */
          if (event.equals ("Back To Main")){
            try {
                new MainWindow ();
                manageFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
       /*
        * If the delete button is pressed the deletebooks object is made
        */
        if (event.equals ("Delete")){
            try {
                new deletebooks();
                manageFrame.dispose ();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
                
    }
}