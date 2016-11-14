
package librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class deletemembers {
       /*
    * A local variable is created for each column in the memberDetails table
    */
    int rowLocal;
    String firstName ;
    String lastName ;
    String em;
    String username;
    String password;
    String idDet;
    String memiD;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    boolean checked = false;
    JFrame manageaFrame;
    
    public deletemembers(){
         /*
        * the deletemember method is called
        */
        deletemem();
    }
    public void deletemem (){
                                /*
        * The row selected by user is inputted
        * All the details in that row are inputted and stored in local variables
        */
        rowLocal = manageMembers.row;
        idDet = manageMembers.data[rowLocal][0] ;
        firstName = manageMembers.data[rowLocal][1];
        lastName = manageMembers.data[rowLocal][2];
        memiD = manageMembers.data [rowLocal][3];
        em = manageMembers.data[rowLocal][4];
        username = manageMembers.data[rowLocal][5];
        password = manageMembers.data[rowLocal][6];
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
                                
                                if (memiD.equals(idlocal)){
                                    
                                        
                                       checked = true;
                                        
                                                                       
                                }
                                
                        }
                        resultSet.close();
                                statement.close();
                                connection.close();
            /*
            * Connection is established with MemberDetails
            */
              if (checked == false){    
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
                            /*
                        * Each row is iterated
                            * If the memberID in the database is equal to selected row member id
                                * The last name in the database is equal to the selected last name
                                    * that row is deleted
                                    * Success message is printed
                        * All connections are closed
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String first = resultSet.getString("FirstName");
                                String last = resultSet.getString("LastName");
                                String email = resultSet.getString("Email");
                                String user = resultSet.getString("Username");
                                String pass = resultSet.getString("Password");
                                String idDetails = resultSet.getString ("MemberID");
                                
                                if (memiD.equals(idDetails)){
                                    if (lastName.equals(last)){
                                        
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
                                * Manage Members is Called is called
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
                new manageMembers ();
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
                                
              }else{
        manageaFrame = new JFrame ();
        manageaFrame.setUndecorated(true);
        manageaFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        manageaFrame.setTitle ("Delete Books");
        manageaFrame.setSize (700,100);
        manageaFrame.setVisible(true);
        manageaFrame.setLocationRelativeTo(null);
        Container contentPane = manageaFrame.getContentPane();
        JPanel myPanel = new JPanel();
        JLabel mes = new JLabel ("This user still has some books checked out. \n Please Check them in before deleting.");
        JButton back = new JButton("Back");
       back.addActionListener(new ActionListener() {
           
     public void actionPerformed (ActionEvent ae){
       String event = ae.getActionCommand();
        
        if (event.equals ("Back")){
            try {
                new manageMembers ();
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
