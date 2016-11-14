
package librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.*;
public class viewCheck extends LibraryManagement implements ActionListener {
    /*
    * A local variable is created for every column in the MemberDetails Table
    * The connection statements are established
    * The booleans and Arraylists are also defined
    */
    JFrame viewCheck;
    String firstName ;
    String lastName ;
    String email;
    String username;
    String password;
    String idDetails;
    String memname;
    String lastname;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String memberID;
    static int mem;
    String finalmemberID;
    boolean found = false;
    boolean borrowed = false;
    static int row;
    static ArrayList <String> BookID = new ArrayList<> ();
    static ArrayList <String> BookTitle = new ArrayList<> ();
    static ArrayList <String> DateBorrowed = new ArrayList<> ();
    static ArrayList <String> ReturnDate = new ArrayList<> ();
    static ArrayList <String> Renewed = new ArrayList<> ();
   static String [][] checkoutData;
    
    
    public viewCheck (){
       
        check();
    }
    public void check(){
        /*
        * The Array Lists are Cleared
        */
        BookID.clear();
        BookTitle.clear();
        DateBorrowed.clear();
        ReturnDate.clear();
        Renewed.clear();
        
        /*
        * The row selected by the user is stored and the details from that index number are retrieved
        */
        int rowLocal = manageMembers.row;
        idDetails = manageMembers.data[rowLocal][0] ;
        firstName = manageMembers.data[rowLocal][1];
        lastName  = manageMembers.data[rowLocal][2];
        memberID  = manageMembers.data [rowLocal][3];
        email     = manageMembers.data[rowLocal][4];
        username  = manageMembers.data[rowLocal][5];
        password  = manageMembers.data[rowLocal][6];
        
        mem = Integer.parseInt(memberID);
        try{
            /*
            * Connection is established
            * if the memberID is equal to the entered memberid
            * found is declared true  and the data method is called
            */
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement ();
			
                        resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
               
			while (resultSet.next()) {
                            
                                int id = resultSet.getInt("ID");
                                memname = resultSet.getString ("FirstName");
                                String finalnumber = resultSet.getString ("MemberID");
                                
                                /*
                                String bookInput = resultSet.getString("BookTitle");
                                String author = resultSet.getString("Author");
                                String year = resultSet.getString("Year");
                                String publisher = resultSet.getString("Publisher");
                                String pages = resultSet.getString("Pages");
                                String BookID = resultSet.getString("BookID");
                                String Copies = resultSet.getString("Copies");
                                String ShelfNumber = resultSet.getString("ShelfNumber");
                                String Available = resultSet.getString("Available");
                                    */
                                
                              if (finalnumber.equals(memberID)){ 
                                  finalmemberID = finalnumber;
                                  found = true;
                                  break;
                             }
                 }
                        if (found == false){
                            JOptionPane.showMessageDialog(null, "Error! Member Not Found");
                            new manageMembers();
                            }
                        if (found == true){
                            data();
                        }
        }
        catch (Exception e){
            System.out.println (e);
        }
}
    public void data (){
        /*
        * Connection is established with memCheckouts
        * While the loop iterates
            * The details are added to each arrayList if the memberID is equal to the local Id
        */
        try {
        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement ();
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
               
			while (resultSet.next()) {
                            
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                String btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                
                              if (idlocal.equals (finalmemberID)){ 
                                    BookID.add(bID);
                                    BookTitle.add(btitle);
                                    DateBorrowed.add(bdate);
                                    ReturnDate.add(rdate);
                                    Renewed.add(trenew);
                                    borrowed = true;
                             }
                 }
                        resultSet.close();
                            statement.close();
                            connection.close();
                        /*
                        * If borrowed is true
                        * The tableGUI method is called
                        * If borrowed is false 
                        * No Checkouts message is printed
                        */
                      //  if (borrowed == true){
                            checkoutData = new String [BookID.size()][5];
                            tableGUI();
                            
                      //  }
                        /*
                        if (borrowed == false){
                            try {
                            JOptionPane.showMessageDialog(null, "No Checkouts by " + memname);
                                        
                            new manageMembers();
                             manageMembers.manageMembersFrame.dispose();
                            //viewCheck.dispose();
                            }catch (Exception e){
                                System.out.println(e);
                            }
                               
                            
                            
                        }
                        */
    } catch (Exception a){
        System.out.println (a);
    }
}
    public void tableGUI(){
         for (int i = 0; i < BookID.size(); i++){
            String book = BookID.get(i);
            checkoutData [i][0] = book;
        }
        for (int i = 0; i < BookID.size(); i++){
            String book = BookTitle.get(i);
            checkoutData [i][1] = book;
        }
       for (int i = 0; i < BookID.size(); i++){
            String book = DateBorrowed.get(i);
            checkoutData [i][2] = book;
        }
        for (int i = 0; i < BookID.size(); i++){
            String book = ReturnDate.get(i);
            checkoutData [i][3] = book;
        }
        for (int i = 0; i < BookID.size(); i++){
            String book = Renewed.get(i);
            checkoutData [i][4] = book;
        }
        String [] columnNames = {"BookID","Book Title","Date Borrowed","Return Date","Times Renewed"};
        
        viewCheck = new JFrame ();
        
        viewCheck .setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        viewCheck .setTitle ("Checkout Details of " + lastname);
        viewCheck .setSize (450,450);
        viewCheck .setVisible(true);
        viewCheck.setLocationRelativeTo(null);
        Container contentPane = viewCheck .getContentPane();
                
        JPanel myPanel = new JPanel ();
        JPanel bottom = new JPanel ();
        
       JButton button = new JButton ("Renew");
       button.addActionListener (this);
       bottom.add(button, BorderLayout.WEST);
       
           
       JButton babutton = new JButton ("Back");
       babutton.addActionListener (this);
       bottom.add(babutton, BorderLayout.EAST);
       
       myPanel.add(bottom, BorderLayout.CENTER);
        
        JTable table = new JTable(checkoutData, columnNames);
       	table.addMouseListener (new MouseAdapter ()
		{
		    public void mouseClicked (MouseEvent e)
		    {
				row = table.getSelectedRow ();
                         
		   }
		}
		);
        int col [] = {80,80,80,80,80};
        int x = table.getColumnCount()-1;
        TableColumn col0 = null;
        table.setPreferredScrollableViewportSize(new Dimension (420,420));
        for (int i =0; i < 5 ; i++){
        col0 = table.getColumnModel().getColumn(i);
        col0.setPreferredWidth (col [i]);
        
        }
        JScrollPane scroll = new JScrollPane (table);
       
       if (borrowed==false){
           JLabel notf = new JLabel ("No Checkouts by " + memname);
           myPanel.add(notf);
           contentPane.add(myPanel);
           viewCheck.validate();
       }
       
        if (borrowed == true){
       myPanel.add(scroll);
       contentPane.add(myPanel);
       viewCheck .validate();
        }
    }
    public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        
        if (event.equals ("Back")){
            try {
                new manageMembers();
                viewCheck.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        if (event.equals ("Renew")){
            try {
                new renewViewCheck();
                viewCheck.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
}