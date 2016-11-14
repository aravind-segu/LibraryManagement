
package librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class addMembers extends LibraryManagement implements ActionListener {
    
     /*
    * The connections are established using connection, statement and resultSet
    * A text field is created for each of the information required:
        * Title, #copies, #shelf, #year, Publisher, Aughor, #numberID
    * 4 booleans are defined false for future checking purposes
    */
    static Container contentPane;
    JPanel myPanel;
    JTextField firstName;
    JTextField lastName;
    JTextField email;
    JTextField userName;
    JTextField password;
    JFrame frameaddMembers;
    int memberID;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    addMembers (){
        // The addMembers method is called        
        addMembersGui();
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If the Add Member button is pressed
            * check method is called
        * If the Back to Main button is pressede
            * The MainWindow Is Called
        */
        if (event.equals ("Add Member")){
            try {
                check();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (event.equals ("Back To Main")){
            try {
                new MainWindow();
                frameaddMembers.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
    
    public void addMembersGui() {
        /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
       frameaddMembers= new JFrame ();
        frameaddMembers.setUndecorated(true);
        frameaddMembers.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frameaddMembers.setTitle ("New Member");
        frameaddMembers.setSize (400,600);
        frameaddMembers.setVisible(true);
        frameaddMembers.setLocationRelativeTo(null);
        
        /*
        * The main Panel is initialized
        * It is set to Border Layout
        * Secondary pannels of top and info are created
        */  
        
        contentPane = frameaddMembers.getContentPane();
        myPanel = new JPanel();      
        myPanel.setLayout(new BorderLayout());
        JPanel top = new JPanel ();
        JPanel info = new JPanel();
        JPanel bottom = new JPanel();
        
        /*
        * At 0,0 in the top Panel the title of Add Members is added
        * At 0,2 in the top Panel the instructions are added
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Add Members");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the information of the Member and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
        
        /*
        * The info is given a GridBag Layout
        * At 0,0 the First Name Label and at 0,1 the subsequent text box is placed.
        * At 1,0 the Last Name and at 1,1 the subsequent text box is placed.
        * At 2,0 the Email Label and at 2,1 the subsequent text box is placed.
        * At 3,0 the Username Label and at 3,1 the subsequent text box is placed.
        * At 4,0 the Password Label and at 4,1 the subsequent text box is placed.
        * At 5,1 the button, Add Book is placed
        * At 6,1 the Back Button is placed
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel MemberName = new JLabel ("First Name:");
        info.add (MemberName , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        firstName = new JTextField (20);
        info.add (firstName , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lastNameLabel = new JLabel ("Last Name:");
        info.add (lastNameLabel , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        lastName = new JTextField (20);
        info.add (lastName , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel Email = new JLabel ("Email:");
        info.add(Email,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
         email = new JTextField (20);
        info.add(email,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel Username  = new JLabel ("Username:");
        info.add(Username,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        userName = new JTextField (20);
        info.add(userName,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel passWord  = new JLabel ("Password:");
        info.add(passWord,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        password = new JTextField (20);
        info.add(password,gbc);
        
       /*
        * In the bottom panel the Backa and Add buttons are added
        */    
        
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Add Member");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
       /*
       * All secondary Panels are added to my panels
            * top to north, info to center, bottom to south
       */     
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       
        frameaddMembers.validate ();
             
    }
    public void check (){
             /*
                 * A string object is created for each text box in the add books section
                 * First, Last, Email, username and password are each taken to see if they are empty
                 * If this process happens with no errors the code proceeds
                 * However if this process results in an error, the booleans for each one are defined to be true
                 * In the end if statement, either of the booleans are true
                    * A try again message is printed
                    * the constructor is called
                    * The current frame is disposed
                 * else
                    * memid method is called
                 */
        String first = firstName.getText();
        String last = lastName.getText();
        String Email = email.getText();
        String user = userName.getText();
        String pass = password.getText();
        boolean error = false;
        if (first.equals("")){
             JOptionPane.showMessageDialog(null, "First Name Left Blank, Try Again");
             error = true;
             new addMembers();
             frameaddMembers.dispose();
        }
         if (last.equals("")){
             JOptionPane.showMessageDialog(null, "Last Name Left Blank, Try Again");
             error = true;
             new addMembers();
             frameaddMembers.dispose();
        }
          if (Email.equals("")){
             JOptionPane.showMessageDialog(null, "Email Left Blank, Try Again");
             error = true;
             new addMembers();
             frameaddMembers.dispose();
        }
           if (user.equals("")){
             JOptionPane.showMessageDialog(null, "Username Left Blank, Try Again");
             error = true;
             new addMembers();
             frameaddMembers.dispose();
        }
            if (pass.equals("")){
             JOptionPane.showMessageDialog(null, "Password Left Blank, Try Again");
             error = true;
             new addMembers();
             frameaddMembers.dispose();
        }
            if (error == false){
                memID();
            }
            
    }
     public void memID(){
         /*
        * An id and BookID string is declared
        * Connection is established with the MemberDetails table in the database
        * The last memID is retrieved as string, turned into an integer and is stored into memberID
            * The memberID is increased by one
        * All connections are closed
        * The add method is called
        */
        try{
        String memID = "";
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT * FROM UserDetails WHERE Username = " + Username);
                        resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,MemberID,Email,Username,Password FROM MemberDetails");
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String book = resultSet.getString("FirstName");
                                String author = resultSet.getString("LastName");
                                                         
                                memID = resultSet.getString("MemberID");
                                String Copies = resultSet.getString("Email");
                                String ShelfNumber = resultSet.getString("Username");
                                String pass = resultSet.getString("Password");
                        }
                        memberID = Integer.parseInt(memID);
                        memberID++;
                        resultSet.close();
			statement.close();
			connection.close();
                        
                        addMember();
		}
		catch (SQLException SQLe) {
			System.out.println("UserDetails.java\n" + SQLe.toString());
		}
    }
    public void addMember () throws SQLException{
        /*
        * All data entered in each textbox is stored in string variables
        * the bkid and countid integers are changed into strings
        * Connection is established
        * The data is added into the table MemberDetails
        * Connection is closed
        * A success Message is shown
        * All text boxes are cleared
        * The constructor is called
        * The current frame is disposed
        */
        String first = firstName.getText();
        String last = lastName.getText();
        String Email = email.getText();
        String user = userName.getText();
        String pass = password.getText();
        String numberBook = String.valueOf(memberID);
        
       String sql = "INSERT INTO MemberDetails" 
               + "( FirstName, LastName,MemberID, Email, Username, Password) VALUES" 
               + "(?,?,?,?,?,?)";
    
    try{
                                        Connection con=DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
                                        PreparedStatement ps =con.prepareStatement(sql);
                                        ps.setString(1, first);
                                        ps.setString(2, last);
                                        ps.setString (3, numberBook);
                                        ps.setString(4, Email);
                                        ps.setString(5, user);
                                        ps.setString(6, pass);
                                        ps.executeUpdate();      
                                        
                                        ps.close();
                                        con.close();
                                        
                                }catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, ex.toString());
                                }
    
           
    String message = "Registration Successful";
    JOptionPane.showMessageDialog(null, message);
    
    new MainWindow ();
    frameaddMembers.dispose();
    
    
}
}
