
package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class manageMembers extends LibraryManagement implements ActionListener{
    
      /*
    * The variables required for connection are established
    * An arrayList is created for every row in teh BookDetails tables
    * A two dimensional array of data is called
    * The manageFrame JFrame is defined
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    static ArrayList <String> firstName = new ArrayList<> ();
    static ArrayList <String> lastName = new ArrayList<> ();
    static ArrayList <String> Email = new ArrayList<> ();
    static ArrayList <String> Username = new ArrayList<> ();
    static ArrayList <String> Password = new ArrayList<> ();
    static ArrayList <String> memberID = new ArrayList<> ();
    static ArrayList <String> Numbers = new ArrayList <>();
    static int counter;
    static int row;
    static String [][] data;
    static JFrame manageMembersFrame;
    
    public manageMembers (){
        ArrayInput();
    }
    
    public void ArrayInput (){
        try {
             /*
            * The Array List is cleared
            * Counter is given one
            */
                                firstName.clear();
                                lastName.clear();
                                Email.clear();
                                Username.clear();
                                Password .clear();
                                memberID.clear();
                                Numbers.clear();
                                counter = 1;
                                /*
                                * Connection is established from MemberDetails
                                */
        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
                        			/*
                        * Each row is iterated
                        * The data in each column is stored in local variables
                        * the copies and copies remaining are turned to integers
                        * Connections are closed
                        * data array is initialized
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String first = resultSet.getString("FirstName");
                                String last = resultSet.getString("LastName");
                                String email = resultSet.getString("Email");
                                String user = resultSet.getString("Username");
                                String pass = resultSet.getString("Password");
                                String idDetails = resultSet.getString ("MemberID");
                                firstName.add (first);
                                lastName.add (last);
                                Email.add (email);
                                Username.add (user);
                                Password .add (pass);
                                memberID.add(idDetails);
                                                               
                                String count = String.valueOf(counter);
                                Numbers.add(count);
                                
                                counter++;
                                
                               
    }
                        resultSet.close();
			statement.close();
			connection.close();
                        data = new String [firstName.size()][8];
                        GUI();
    
} catch (Exception e){
    System.out.println (e);
}
    }
    
    public void GUI (){
       
               
       /*
        * Each arrayList is added to the two dimensional array data
        */ 
        for (int i = 0; i < firstName.size(); i++){
            String book = Numbers.get(i);
            data [i][0] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = firstName.get(i);
            data [i][1] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = lastName.get(i);
            data [i][2] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = memberID.get(i);
            data [i][3] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = Email.get(i);
            data [i][4] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = Username.get(i);
            data [i][5] = book;
        }
        for (int i =0; i < firstName.size(); i++){
            String book = Password.get(i);
            data [i][6] = book;
        }
         
        
        
        
        String [] columnNames = {"Number","First Name","Last Name","MemberID","Email","Username","Password"};
               /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
        manageMembersFrame = new JFrame ();
        
        manageMembersFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageMembersFrame.setTitle ("Manage Members");
        manageMembersFrame.setSize (800,800);
        manageMembersFrame.setVisible(true);
        manageMembersFrame.setLocationRelativeTo(null);
        Container contentPane = manageMembersFrame.getContentPane();
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
        * View Check Outs button is added to the bottom panel
        */
       JButton checkOuts = new JButton ("View Check Outs");
       checkOuts.addActionListener (this);
       bottom.add(checkOuts, BorderLayout.CENTER);
       /*
       * A button called Delete is added
       * The button is given actionListener
       * It is added to the Bottom Panel at Position East
       */
       JButton delbutton = new JButton ("Delete");
       delbutton.addActionListener (this);
       bottom.add(delbutton, BorderLayout.EAST);
            /*
        * Back button is added to the bottom panel
        */
       JButton babutton = new JButton ("Back");
       babutton.addActionListener (this);
       bottom.add(babutton, BorderLayout.EAST);
       /*
       * My Panel is added
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
        int col [] = {80,80,80,80,150,80,80};
        int x = table.getColumnCount()-1;
        TableColumn col0 = null;
        table.setPreferredScrollableViewportSize(new Dimension (575,575));
        for (int i =0; i < 7 ; i++){
        col0 = table.getColumnModel().getColumn(i);
        col0.setPreferredWidth (col [i]);
        
        }
        JScrollPane scroll = new JScrollPane (table);
       
        
       myPanel.add(scroll);
       contentPane.add(myPanel);
       manageMembersFrame.validate();
}
     @Override
    public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
          /*
        * If the modify button is pressed the modify object is made
        */
        if (event.equals ("Modify")){
            try {
                new modifyMembers ();
                manageMembersFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
             /*
        * If the delete button is pressed the deletebooks object is made
        */
        if (event.equals ("Delete")){
            try {
                new deletemembers();
                manageMembersFrame.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
                  /*
        * If the back button is pressed the mainWindow object is made
        */    
        if (event.equals ("Back")){
            try {
                new MainWindow ();
                manageMembersFrame.dispose();
                manageMembersFrame.setVisible(false);
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
             /*
        * If the viewCheckOuts button is pressed the viewCheck object is made
        */
        if (event.equals ("View Check Outs")){
            try {
                new viewCheck ();
                manageMembersFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
}