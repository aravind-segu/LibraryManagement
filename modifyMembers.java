
package librarymanagement;
 import javax.swing.*;
 import java.awt.*;
 import java.sql.*;
 import java.awt.event.*;
public class modifyMembers extends LibraryManagement implements ActionListener {
     /*
    * Connection variables are defined
    * Variables are created for each column in MemberDetails
    * A TextField is created for each column in MemberDetails
    * Booleans are defined false for further checking
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String firstName ;
    String lastName ;
    String email;
    String username;
    String password;
    String mid;
    String memid;
    Container contentPane;
    JPanel myPanel;
    String idDetails;
    JTextField first;
    JTextField last;
    JTextField Email;
    JTextField user;
    JTextField pass;
    JTextField membeid;
    JTextField id;
    JFrame modifyMembersFrame;
    
    public modifyMembers (){
        data();
    }
    
    public void data (){
        /*
        * The row selected by the user is retreived 
        * The data in that row is stored for later user
        */
        int rowLocal = manageMembers.row;
        idDetails = manageMembers.data[rowLocal][0] ;
        firstName = manageMembers.data[rowLocal][1];
        lastName = manageMembers.data[rowLocal][2];
        memid = manageMembers.data[rowLocal][3];
        email = manageMembers.data[rowLocal][4];
        username = manageMembers.data[rowLocal][5];
        password = manageMembers.data[rowLocal][6];
        
        guiModify();
        
    }
    
    public void guiModify (){
         /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
        modifyMembersFrame = new JFrame ();
        
        modifyMembersFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        modifyMembersFrame.setTitle ("Modify Member Details");
        modifyMembersFrame.setSize (400,600);
        modifyMembersFrame.setVisible(true);
        modifyMembersFrame.setLocationRelativeTo(null);
        /*
        * Three panels are defined
        */
        contentPane = modifyMembersFrame.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
        
        /*
        * The top panel is given a GridBaglayout
        * The title is added
        * Directions are added
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Modify Records");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
        
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the new information of the Member and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
        /*
        * The info layot is given a GridBagLayout
        * Each column in the BookDetaisl table is given a label and a text box is added beside the label
        * The textbox is filled with the data fromt the local variables
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel Booktitle = new JLabel ("First Name:");
        info.add (Booktitle , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        first = new JTextField (20);
        first.setText(firstName);
        info.add (first , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel author = new JLabel ("Last Name:");
        info.add (author , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        last = new JTextField (20);
        last.setText(lastName);
        info.add (last , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel membid = new JLabel ("MemberID:");
        info.add (membid , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        membeid = new JTextField (20);
        membeid.setText(memid);
        membeid.setEditable(false);
        info.add (membeid , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel year = new JLabel ("Email:");
        info.add(year,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        Email = new JTextField (20);
        Email.setText(email);
        info.add(Email,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel publisher  = new JLabel ("Username:");
        info.add(publisher,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        user = new JTextField (20);
        user.setText(username);
        info.add(user,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel numberOfPages  = new JLabel ("Password:");
        info.add(numberOfPages,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        pass = new JTextField (20);
        pass.setText(password);
        info.add(pass,gbc);
        
              
         /*
        * Two Buttons Back and Modify are added to the bottom panel
        */
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Modify");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
        /*
       * All three panels are added to myPanel
       */     
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       modifyMembersFrame.validate ();
    }
    @Override
     public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If the event is equal to Back
        * The MainWindow is called
        * The current Frame is disposed
        */
        if (event.equals ("Back To Main")){
            try {
                new MainWindow ();
                modifyMembersFrame.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        /*
        * If the event is equal to Modify
        * The modifyMember is called
        * The current Frame is disposed
        */
        if (event.equals ("Modify")){
            try {
                modifymember();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
     
     public void modifymember (){
             /*
         * The modified data is retrieved and stored in local variables
         */
         firstName=first.getText();
         lastName=last.getText();
         email= Email.getText();
         username=user.getText();
         password=pass.getText();
         mid = membeid.getText();
         
       
        int idNumber = Integer.parseInt(idDetails);
        int finalNumber = idNumber+1;
     try{
         connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
               
			while (resultSet.next()) {
                                /*
                             * If bookId equals the book Id in database
                             * The row is updated
                            */
                                String mem = resultSet.getString("MemberID");
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
                                
                              if (mem.equals(mid)){ 
                                  resultSet.updateString("FirstName", firstName);
                                  resultSet.updateString("LastName", lastName);
                                  resultSet.updateString("Email", email);
                                  resultSet.updateString("Username", username);
                                  resultSet.updateString("Password",password);
                                  
                                  
                                  resultSet.updateRow();
                                          
     }
                              
                        }
                        
                              new manageMembers();
                              modifyMembersFrame.dispose();
} catch (Exception e){
    System.out.println (e);
}
     
     }  
}
